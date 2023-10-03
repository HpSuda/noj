package com.example.ojbackend.judger.complier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class JavaCompiler {
    private final static String jAVAFILEPATH="ojbackend/src/main/resources/static/judge_test/temp_execute_files/";
    private final static String JAVAFILENAME="main.java";
    private final static String jAVAFILECLASNAME="main";

    private String inputData; //输入数据
    private String outputData; //输出数据
    private String errorMsg; //程序错误信息

    public JavaCompiler() {
        this.inputData="";
        this.errorMsg="";
    }
    public JavaCompiler(String inputData) {
        super();
        this.inputData = inputData;
        this.errorMsg="";
    }
    public JavaCompiler(String inputData, String outputData, String errorMsg) {
        super();
        this.inputData = inputData;
        this.outputData = outputData;
        this.errorMsg = errorMsg;
    }

    public void complier() {
        // 进入java代码存放文件夹
        // 使用javac命令来编译java文件,来生成一个class文件
        // 使用java命令来运行类

        // cmd命令：cd /d d:\javaworkspace\complier\javaCode
        // cmd命令：javac Main.java
        // cmd命令：java Main
        StringBuffer errorInfo = new StringBuffer();
        Process p=null;
        try {
            //1.编译java文件
            p = Runtime.getRuntime().exec("javac "+JAVAFILENAME, null, new File(jAVAFILEPATH));
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
            //2.如果没错的话就运行java文件
            if (errorInfo.toString().equals("")) {
                try {
                    Process process = Runtime.getRuntime().exec("java "+jAVAFILECLASNAME, null, new File(jAVAFILEPATH));
                    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    bout.write(this.inputData);
                    bout.close();
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
                    boolean isTimeLimitNoExceeded = process.waitFor(1, TimeUnit.SECONDS);

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
                        errorMsg = "Runtime Error";
                        return;
                    }
                    String line = null;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line + "\n");
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
}
