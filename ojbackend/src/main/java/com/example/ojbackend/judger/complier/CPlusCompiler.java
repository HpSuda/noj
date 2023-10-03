package com.example.ojbackend.judger.complier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CPlusCompiler {
    private final static String CplusplusFILEPATH="C:/Users/22845/Desktop/NOJ/ojbackend/src/main/resources/static/judge_test/temp_execute_files/";
    private final static String CplusplusFILENAME="main.cpp";
    private final static String CplusplusEXE="main.exe";

    private String inputData; //输入数据
    private String outputData; //输出数据
    private String errorMsg; //程序错误信息

    private String Res;

    public CPlusCompiler() {
        this.inputData="";
        this.errorMsg="";
    }
    public CPlusCompiler(String inputData) {
        super();
        this.inputData = inputData;
        this.errorMsg="";
    }
    public CPlusCompiler(String inputData, String outputData, String errorMsg) {
        super();
        this.inputData = inputData;
        this.outputData = outputData;
        this.errorMsg = errorMsg;
    }

    public void complier() {
        // 进入c++代码存放文件夹
        // 使用g++命令来编译c++文件,来生成一个exe文件
        // 直接运行exe文件

        // cmd命令：cd /d d:\javaworkspace\complier\c++Code
        // cmd命令：g++ main.cpp
        // cmd命令：main.exe
        StringBuffer errorInfo = new StringBuffer();
        Process p=null;
        try {
            //1.编译C++文件

            p = Runtime.getRuntime().exec("g++ "+CplusplusFILEPATH+CplusplusFILENAME+" -o "+CplusplusFILEPATH+"main");
            // 获取进程的错误流
            final InputStream is1 = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1,Charset.forName("GBK")));
                        String line1 = null;
                        while ((line1 = br1.readLine()) != null) {
                            if (line1 != null) {
                                errorInfo.append(line1 + "\n");
                            }
                        }
                        if(!errorInfo.toString().equals("")) {
                            errorMsg=errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
            //2.如果没错的话就运行exe文件
            System.out.println(errorInfo);
            if (errorInfo.toString().equals("")) {
                try {
                    Process process = Runtime.getRuntime().exec("cmd /C cd " + CplusplusFILEPATH+ " && " + CplusplusEXE + " <1.in >1.out");
                    System.out.println("111");
                    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    bout.write(this.inputData);
                    bout.close();
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
                    boolean isTimeLimitNoExceeded = process.waitFor(10, TimeUnit.SECONDS);
                    if(!isTimeLimitNoExceeded){
                        process.destroy();
                        errorMsg = "Time Limit Exceeded";
                        return;
                    }
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    //获得错误信息
                    String errorMessage = errorReader.readLine();
                    errorReader.close();
                    if (errorMessage != null) {
                        process.destroy();
                        errorMsg = "Idleness Limit Exceeded";
                        return;
                    }
                    String line = null;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line);
                    }
                    this.outputData=b.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                errorMsg = "Compiler Error";
            }
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        System.out.println("start");
        try {
            Process process = Runtime.getRuntime().exec("cmd /C cd " + CplusplusFILEPATH + " && g++ checker.cpp -o checker && checker.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getInputData() {
        return inputData;
    }
    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
    public String getOutputData() {
        return outputData;
    }
    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRes() { return Res; }
    public void setRes(String Res) {
        this.Res = Res;
    }
}
