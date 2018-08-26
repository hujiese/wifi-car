## WiFI-Camera ##

树莓派是款卡片电脑，可以嵌入树莓派系统（你可以把它当作Debian Linux系统，其实本质也是如此，如果你有Ubuntu的使用经验，那更好），至于如何刷系统，第一张已经有介绍了，这里只介绍这个小项目需要用到的一些东西，更多有趣的玩意读者可以参考其他书籍和资料。

### 0、树莓派小技巧 ###

#### 如果你有VGA或者HDMI液晶显示器，可以跳过该节 ####

树莓派的确是个好东西，但我们买来的树莓派往往是一块单板。按照我们平日使用手机电脑经验，树莓派还需要一个显示器或者终端设备与用户交互。如果条件允许的话，你可能手头上会有一台液晶显示器，现在的液晶显示器也有两种，一种是传统的VGA接口的，如果你的电脑支持，应该可以看到一个有着三排插针的插头，那便是和VGA显示器连接的接口；当然，如果你条件更优越，拥有一个支持HDMI接口的液晶显示器，那更好。

两种接口的显示器有什么不同呢？可以直接告诉你，HDMI的显示效果比VGA好很多，当然价格也更贵。树莓派不支持VGA接口显示器，所以如果你需要让树莓派用上你的VGA液晶显示器，你需要买一根VGA转HDMI接口的线，这种线也不贵，但多少有些麻烦。如果你有HDMI接口液晶显示器那就不存在这个问题了。

那么，我们考虑最坏的一种打算--读者没有任何液晶显示器。

好的，下面我将介绍一种方法让读者在没有液晶显示器的情况下也能拥有树莓派的人机交互界面，至于为什么这样，这里暂时不作解释，如果想知道原理，可以参考讲计算机网络TCP/IP协议的书籍和资料。

#### （1）准备工作 ####

为了达成目的，我们需要下面这几件东西：

##### 树莓派一个 #####

![](https://i.imgur.com/j4IARMR.jpg)

##### 网线一根 #####

![](https://i.imgur.com/CjeIBj6.jpg)

##### 串口工具一个 #####

![](https://i.imgur.com/Lp6EpJ9.jpg)

#### 注意：如果你是第一次使用树莓派，这样做不会成功的，你需要做如下操作： ####

![](https://i.imgur.com/38KYtHi.png)

树莓派默认上面那些硬件选项是Disabled的，你需要一个液晶显示器将他们（至少是Serial）设置为Enabled。使能后注意要重启树莓派。

#### （2）通过串口登陆树莓派 ####

首先需要知道树莓派的管脚有哪些，这里要用到串口，至于串口方面的知识，在上一章中已经在Arduino中讨论和使用过了，这里将更加深入地了解串行通信。

下面是树莓派的管脚图：

![](https://i.imgur.com/fcTEXai.png)

这里将使用树莓派物理引脚的6、8和10三个，分别连接串口工具的GND、RXD和TXD，连接如下所示：

![](https://i.imgur.com/3dfRewx.jpg)

打开设备管理器，查看端口号。这里需要注意的是，在串口助手连接电脑时可能会出现安装驱动过程，不必惊慌，同意即可。

端口在我的电脑上查看如下：

![](https://i.imgur.com/AyhRSzV.png)

我这里是COM4，这个需要记下。当然，如果你的串口驱动安装失败，那么在显示端口的那一条将出现黄色感叹号，至于处理方法，自行百度，很简单。

我们还需要下载一个工具，叫putty:

![](https://i.imgur.com/xjSn4f8.png)

这里选择Serial，填入COM4，设置波特率115200，点击open即可：

![](https://i.imgur.com/wFBVY3u.png)

需要登录，这里用户名是pi，密码是raspberry。

登录树莓派后，在树莓派终端输入ifconfig命令，结果如下：

![](https://i.imgur.com/4t6cDnP.png)

eth0是树莓派的物理网卡，也就是接网线的那个网卡；lo为本地回环；wlan0为无限网卡，无线上网则显示IP。从上面可以看出，目前树莓派还没有联网。所以没有IP，这里需要用到树莓派的eth0网卡。

接下来需要用网线将树莓派和电脑连接。

先看看没有连接树莓派电脑的IP，打开cmd，输入ipconfig：

![](https://i.imgur.com/7vQuQgF.png)

可以看到“以太网适配器”没有联网，没有IP。

#### （3）VNC Viewer访问树莓派 ####

将网线连接树莓派和电脑以后：

![](https://i.imgur.com/H4TM6lf.png)

树莓派eth0网络信息IP地址为169.254.243.195，子网掩码为255.255.0.0

而电脑的则为：

![](https://i.imgur.com/EeNk0wx.png)

以太网适配器IP为169.254.30.111，子网掩码为255.255.0.0

接下来修改树莓派IP，输入sudo ifconfig eth0 169.254.30.112

![](https://i.imgur.com/Wk2NITR.png)

将IP的前三个段设置为和PC一样的数字。

接下来还需要下载VNC Viewer软件，新建一个连接，输入IP为树莓派IP，端口为5900，名字任取，这里设置如下：

![](https://i.imgur.com/xZm2X4b.png)

点击OK，然后双击图标，弹出对话框要求输入密码：

![](https://i.imgur.com/H6wdbFw.png)

密码是raspberry。

然后你可以看到这个画面：

![](https://i.imgur.com/iNWJeBg.png)

接下来需要设置树莓派分辨率，按照下面图的步骤来做：

![](https://i.imgur.com/2fEdpKQ.png)

然后弹出如下对话框，选中resolution：

![](https://i.imgur.com/SML3GOl.png)

弹出如下对话框：

![](https://i.imgur.com/JCxSysB.png)

然后根据你自己的喜好来设置分辨率即可：

![](https://i.imgur.com/70UQ1rO.png)

设置完后重启即可：

![](https://i.imgur.com/RZIez9c.png)

重启后重新用上面的方法连接树莓派，你可以看到如下场景：

![](https://i.imgur.com/TcYpqqw.jpg)

至此，该部分结束。

### 1、WIFI-IP摄像头 ###

#### （1）mjpeg-streamer打造网络摄像头 ####

如果你按照上一步中：

![](https://i.imgur.com/38KYtHi.png)

使能了Camera，那么可以继续下去了，如果没有，赶紧设置好，使能摄像头并重启，下面存在默认你设置使能了摄像头。

当然，你的树莓派也需要连接wifi，如何连接？很简单：

![](https://i.imgur.com/Lce6Jhr.png)

连接好后，开启一个终端，首先安装依赖库：

	sudo apt-get update
	sudo apt-get install subversion
	sudo apt-get install libjpeg8-dev
	sudo apt-get install imagemagick
	sudo apt-get install libv4l-dev
	sudo apt-get install cmake
	sudo apt-get install git

git开源的project到本地，编译：

	sudo git clone https://github.com/jacksonliam/mjpg-streamer.git
	cd mjpg-streamer/mjpg-streamer-experimental
	make all
	sudo make install

注意，这一步需要让树莓派插入USB摄像头后才可以运行，否则报错。如果是普通的USB摄像头，这个时候应该可以使用了，在命令行输入：

	./mjpg_streamer -i "./input_uvc.so" -o "./output_http.so -w ./www"

然后打开浏览器，输入localhost:8080，然后你可以看到：

![](https://i.imgur.com/z5OS5C0.jpg)

#### （2）create_ap将树莓派打造成无线热点 ####

首先，你的树莓派还需要连接上网络，然后开启一个新终端，然后依次运行下面这几条命令：

	git clone https://github.com/oblique/create_ap.git
	cd create_ap
	sudo make install

接下来安装依赖库：

	sudo apt-get install util-linux procps hostapd iproute2 iw haveged dnsmasq

#### 然后让你的树莓派wifi不要连接任何网络 ####
一定要断开，否则会出错，至于为什么，可以看create_ap官网给出的解释： https://github.com/oblique/create_ap 。现在只需要按照我说的操作即可。此时的树莓派只能作为一个无限热点，然后在终端中输入：

	sudo ./create_ap -n wlan0 father myfather

其中father是热点名，myfather是热点密码，这两个可以根据个人喜好修改。

#### （3）打造wifi-camera ####

按照前面两步开启摄像头和无线热点后：

![](https://i.imgur.com/2tUG0qu.jpg)

你可以通过PC或者移动设备找到你的无限热点，这里是father：

![](https://i.imgur.com/DTf93sN.png)

连接，然后查询树莓派IP（ifconfig），这里关注wlan0的IP:

![](https://i.imgur.com/wnIGH2x.png)

记下这个IP：192.168.12.1，接下来打开你PC浏览器（这里以PC为例），输入192.168.12.1:8080，然后可以看到：

![](https://i.imgur.com/LNMB4wq.png)

至此，告一段落。