<template>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body >
        <div class="box" flex gap8 lt-md:flex-col-reverse>
            <div w="5/8" lt-md:w-full>
                <div style="max-width: 1500px; margin-right: 20px; background-color: #ffffff;">
                    <div style="border: 1px solid #666; padding: 20px; margin: 20px 0; text-align: left;">
                        <h2 style="font-size: 24px;">关于 Idleness Limit Exceeded</h2>
                        <h2 style="font-size: 15px; color: #0077b6;">admin 发表于 2023 年 9 月 27 日</h2>
                        <p style="font-size: 16px;">由于目前评测机使用的是不太行的云服务器，容易发生输入或者由于频繁清空输出缓冲区（std::flush，std::endl）的超时；</p>
                        <p style="font-size: 16px;">评测沙盒实现问题，存在实际是TLE的误报可能，但是运行的真实物理时间限制为标称的两倍，因此ILE/TLE都必然是由于超时带来，请确认自己的代码。</p>
                        <p style="font-size: 16px;">建议使用 scanf，printf，或者关闭 cin 和 cout 流的同步。</p>
                        <!-- <a href="#" style="background-color: #0077b6; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 5px;">开始评测</a> -->
                    </div>
                    <div style="border: 1px solid #666; padding: 20px; margin: 20px 0; text-align: left;">
                        <h2 style="font-size: 24px;">返回的评测结果具体含义和可能出现的原因？</h2>
                        <h2 style="font-size: 15px; color: #0077b6;">admin 发表于 2023 年 9 月 23 日</h2>
                        <p style="font-size: 16px;">Waiting：提交的代码尚未开始运行，可能是正在等待空闲的评测机，或者评测机正在和主服务器通信；</p>
                        <p style="font-size: 16px;">Compiling：提交的代码正在进行编译；</p>
                        <p style="font-size: 16px;">Running：提交的代码正在运行，若提交的代码长时间保持以上这三种等待状态，请及时联系管理员处理；</p>
                        <p style="font-size: 16px;">Accepted： 提交的代码运行正常，正确通过了所有测试数据；</p>
                        <p style="font-size: 16px;">Wrong Answer：提交的代码运行正常，但是没有正确通过所有测试数据，请检查你的算法是否正确，是否忽略了某些边界情况等等；</p>
                        <p style="font-size: 16px;">Time Limit Exceeded：提交的代码由于超过 CPU 运行时间限制导致运行失败，请检查你的算法的时间复杂度等是否正确；</p>
                        <p style="font-size: 16px;">Idleness Limit Exceeded：提交的代码在内核态运行了过长时间，超过评测最大时间限制导致运行失败，可能原因是输入输出超时，频繁清空输出缓冲区（std::flush，std::endl），或者使用 sleep 等函数）；</p>
                        <p style="font-size: 16px;">Memory Limit Exceeded：提交的代码由于超过运行内存限制导致运行失败，请检查你是否创建了过大的数组，或者动态分配了过多的内存空间；</p>
                        <p style="font-size: 16px;">Partially Correct：在 OI 赛制下，提交返回了多种不同的错误结果；</p>
                        <p style="font-size: 16px;">Output Limit Exceeded：提交的代码由于输出过多导致运行失败，请检查你的输出代码是否陷入了死循环等情况；</p>
                        <p style="font-size: 16px;">Runtime Error：提交的代码由于某些错误导致运行失败；</p>
                        <p style="font-size: 16px;">Compile Error：提交的代码编译失败，请在本地开发环境确认代码能否正确编译，你可以在提交详情中查看到具体的编译错误信息（或者编译警告信息）；</p>
                        <p style="font-size: 16px;">System Error/Judge Error/Testcase Error：评测机内部出错，请及时反馈给管理员。</p>
                        <!-- <a href="#" style="background-color: #0077b6; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 5px;">查看竞赛</a> -->
                    </div>
                    <div style="border: 1px solid #666; padding: 20px; margin: 20px 0; text-align: left;">
                        <h2 style="font-size: 24px;">欢迎使用NJUST OJ！</h2>
                        <h2 style="font-size: 15px; color: #0077b6;">admin 发表于 2023 年 9 月 18 日</h2>
                        <p>NJUST OJ目前仍在开发过程中，如果有遇到BUG或者功能建议，可以向管理员进行反馈。</p>
                    </div>
                </div>
            </div>
            <div w="3/8" lt-md:w-full space-y-8>
                <br>
                <el-card shadow="hover">
                    <div>
                        <h3 border="b-1 base" p4 text-xl font-bold>公告牌</h3>
                        <div p4>
                        <p>NJUST OJ是一个开源的在线评测系统，目前仍在开发过程中。</p>
                        <p mt4>
                            <img src="../../assets/fish.png"/>
                        </p>
                        </div>
                    </div>
                </el-card>
                <br>
                <el-card shadow="hover">
                    <div>
                        <h3 border="b-1 base" p4 text-xl font-bold>牛逼网友</h3>
                        <div p4>
                            <p>NJUST OJ为刷题数排名前10%的网友授予“牛逼网友”称号。</p>
                        </div>
                        <table border="1" cellpadding="10" align="center">
                            <thead>
                                <tr>
                                <th>排名</th>
                                <th>ID</th>
                                <th>刷题数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                <td>1</td>
                                <td>Hpsuda</td>
                                <td>10</td>
                                </tr>
                                <tr>
                                <td>2</td>
                                <td>ast</td>
                                <td>7</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </el-card>
                <br>
                <el-card shadow="hover">
                    <div>
                        <h3 border="b-1 base" p4 text-xl font-bold>今日运势</h3>
                        <div p4>
                        <p>今日您的运势如下。</p>
                        <p mt4>
                            <img src="../../assets/luck.png" width="300" height="200"/>
                        </p>
                        </div>
                    </div>
                </el-card>
                <br>
                <el-card shadow="hover">
                    <div>
                        <h3 border="b-1 base" p4 text-xl font-bold>联系管理</h3>
                        <div p4>
                        <p>NJUST OJ系统管理员联系方式见下。</p>
                        <p>123456@163.com 猪师傅</p>
                        <p>2179870889@qq.com 鼠鼠</p>
                        </div>
                    </div>
                </el-card>
            </div>
        </div>
        
    </body>
    </html>
    
    </template>
    
    
    
    <style>
    .box {
        display: flex;
    }
    </style>