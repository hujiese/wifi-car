## 准备工作 ##

这里默认读者具有一定的电脑使用经验，如果几乎不怎么会电脑和安装软件，建议在有一定电脑最好是计算机相关专业经验的朋友协助。

### 1、开发环境 ###

这里选用的系统为Windows 10，但是还需要其他的环境Arduino IDE + eclipse adt + Raspberry pi 3b+

- Arduino IDE下载地址： https://www.arduino.cc/en/Main/Software
- Arduino IDE安装教程： https://www.w3cschool.cn/arduino/arduino_installation.html
- Arduino 参考手册： https://www.w3cschool.cn/arduino/

- 安卓开发环境eclipse adt下载地址： https://pan.baidu.com/s/1mhHi72K

- 树莓派系统镜像下载： https://www.raspberrypi.org/downloads/
- 树莓派刷机方法： https://blog.csdn.net/weixin_41656968/article/details/79592624

需要注意的是，eclipse要想使用，首先需要安装JDK并配置环境变量，JDK建议使用JDK8，JDK8下载地址：
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html 这里选择Windows 版本，至于是x86还是x64，根据下载的eclipse版本决定。至于如何配置JDK8环境变量，网上有很多文章，这里给出一个参考： https://blog.csdn.net/yangsummer2426/article/details/80499775

### 2、硬件设备 ###

#### Arduino UNO R3开发板一块： ####

![](https://i.imgur.com/hq91hpy.jpg)

注：穷党可以购买这种非官方的板子，和官方板子没有区别，但是更便宜，笔者用的也是这种板子。

#### Raspberry pi3 B+（树莓派3B+）一块：####

![](https://i.imgur.com/etJqvr8.jpg)

####  L298N电机驱动模块一个： ####

![](https://i.imgur.com/pFe1ksM.jpg)

####  充电宝一个： ####

![](https://i.imgur.com/frA2nEw.jpg)

注意：这里的充电宝要使用小而轻的那种，以减小小车负重。

#### UltraFire 3.7v充电电池两个 ####

![](https://i.imgur.com/IAYlbWr.jpg)

注意：需要自备该充电器和电池座。

#### 小车底盘和电机一套： ####

![](https://i.imgur.com/qfW2jRh.jpg)

注意：四轮车底盘都大同小异

#### 普通USB摄像头一个： ####

![](https://i.imgur.com/jokyBrn.png)

注意：USB摄像头很大程度决定了图传的质量，可酌情购买

#### 发光二极管若干： ####

![](https://i.imgur.com/CN6S7G0.jpg)

#### 杜邦线： ####

![](https://i.imgur.com/MLsFY4f.jpg)

注意：为了方便，最好准备三种“母头-母头”、“公头-公头”和“公头-母头”杜邦线

#### 面包板一块（可选）： ####

![](https://i.imgur.com/3intTrw.jpg)

注意：面包板在这里用得不多，只是为了方便测试Led和电机，在最后的作品里不会出现面包板

#### CH340 USB转TTL to UART 串口工具 ####

![](https://i.imgur.com/XnNyEJR.png)

注意： 穷党可以购买下面这个，只要几块钱：

![](https://i.imgur.com/GZFol5k.jpg)