## 树莓派编程 ##

### 1、串口编程 ###

树莓派串口编程其实就是对Linux进行串口编程，调用的也是其相关API，这里不做解释，代码可以在src下找到，其中有两个文件，一个是serial.h，第二个是serial.c。这里只介绍函数的作用，至于怎么用，在serial.c里面注释掉的main函数中有，在后面的例子中也会结合使用。

serial.h文件内容如下：

	#ifndef __SERIAL_H
	#define __SERIAL_H
	//宏定义
	#define FALSE  -1
	#define TRUE   0
	
	int UART0_Open(int fd,char* port);
	void UART0_Close(int fd);
	int UART0_Set(int fd,int speed,int flow_ctrl,int databits,int stopbits,int parity);
	int UART0_Init(int fd, int speed,int flow_ctrl,int databits,int stopbits,int parity);
	int UART0_Recv(int fd, char *rcv_buf,int data_len);
	int UART0_Send(int fd, char *send_buf,int data_len);
	
	#endif

UART0_Open可以打开一个串口，UART0_Close关闭一个串口，UART0_Set重新设置一个串口的参数，UART0_Init初始化一个串口，UART0_Recv串口接收数据，UART0_Send串口发送数据。

### 2、TCP/IP、多线程编程 ###

这一部分相对麻烦，如果没有学过计算机网络、操作系统，没有过Linux环境编程经验不适合研究代码，当然，这里也给出源码，对于大部分读者，只需要拷贝到树莓派然后编译运行即可，不需要理解其中的细节，等后面有精力和兴趣，系统学习过UNIX及其网络编程，自然可以理解这些代码。这部分代码将与上一节代码结合，完成下一节的任务。

代码在src文件夹下，一共给出了四个文件，wrap.h、wrap.c、server.c和client.c，其中wrap.h和wrap.c封装了套接字API，做了一些出错处理，也增强了部分功能。server.c是服务端代码，client.c是客户端测试代码，调试服务端用的，具体怎么使用下一节会有说明。

### 3、树莓派通过串口控制小车 ###

有了前面几章的基础之后，可以将树莓派和小车连接了，连接后小车如下：

![](https://i.imgur.com/rNsxHJM.jpg)

![](https://i.imgur.com/RoMutqX.jpg)

![](https://i.imgur.com/eGpitDA.jpg)

至于如何摆放，这个要看个人喜好。

接下来，你需要将src目录下的源代码通过U盘或者SD卡拷贝到树莓派中，我这里是这样的：

![](https://i.imgur.com/9wSE2eD.jpg)

然后编译：

![](https://i.imgur.com/J3iFlnR.jpg)

最后生成了一个server和client可执行文件，一切顺利后：

![](https://i.imgur.com/C3zdHTk.jpg)

由于我们的server是多线程的，所以这里开了两个客户端连接发送数据也没有问题。如果你一切顺利，运行客户端发送1-5五个字符，应该能看到小车的运动了。