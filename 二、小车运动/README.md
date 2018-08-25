## 小车运动 ##

本文档默认读者已经具备了C/C++等任意一种编程语言基础和Arduino基础，如果没有任何基础，阅读下面文章比较吃力，可以参考该教程： https://www.w3cschool.cn/arduino/ 。如果只是想做这辆小车而不做其他深入，只需要阅读该文章的前两个章节即可，本文也有相当部分内容是参考这份开源的教程。如果之前有使用Ardunio编程的经验，本文内容可以快速浏览甚至跳过。

### 1、Arduino基础 ###

####（1）Arduino 单板资源 ####

在本节中，我们将了解Arduino板上的不同组件。将学习Arduino UNO板，因为它是Arduino板系列中最受欢迎的。此外，它是开始使用电子和编码的最佳板。有些板看起来与下面给出的有些不同，但多数Arduino中的这些组件大部分是共同的，只要你熟悉一种，其他的都类似。

![](https://i.imgur.com/haiynWy.png)

- 1.电源USB

Arduino板可以通过使用计算机上的USB线供电。你需要做的是将USB线连接到USB接口。

- 2.电源（桶插座）

Arduino板可以通过将其连接到电源插口直接从交流电源供电。

- 3.稳压器

稳压器的功能是控制提供给Arduino板的电压，并稳定处理器和其他元件使用的直流电压。

- 4.晶体振荡器

晶振帮助Arduino处理时间问题。Arduino如何计算时间？答案是，通过使用晶体振荡器。在Arduino晶体顶部打印的数字是16.000H9H。它告诉我们，频率是16,000,000赫兹或16MHz。

- 5.Arduino重置

你可以重置你的Arduino板，例如从一开始就启动你的程序。可以通过两种方式重置UNO板。首先，通过使用板上的复位按钮（17）。其次，你可以将外部复位按钮连接到标有RESET（5）的Arduino引脚。

- 6、7、8、9.引脚（3.3，5，GND，Vin）

	- 3.3V（6） - 提供3.3输出电压
	
	- 5V（7） - 提供5输出电压
	
	- 使用3.3伏和5伏电压，与Arduino板一起使用的大多数组件可以正常工作。
	
	- GND（8）（接地） - Arduino上有几个GND引脚，其中任何一个都可用于将电路接地。
	
	- VVin（9） - 此引脚也可用于从外部电源（如交流主电源）为Arduino板供电。

- 10.模拟引脚

Arduino UNO板有六个模拟输入引脚，A0到A5。这些引脚可以从模拟传感器（如湿度传感器或温度传感器）读取信号，并将其转换为可由微处理器读取的数字值。

- 11.微控制器

每个Arduino板都有自己的微控制器（11）。你可以假设它作为板的大脑。Arduino上的主IC（集成电路）与板对板略有不同。微控制器通常是ATMEL公司的。在从Arduino IDE加载新程序之前，你必须知道你的板上有什么IC。此信息位于IC顶部。有关IC结构和功能的更多详细信息，请参阅数据表。

- 12.ICSP引脚

大多数情况下，ICSP（12）是一个AVR，一个由MOSI，MISO，SCK，RESET，VCC和GND组成的Arduino的微型编程头。它通常被称为SPI（串行外设接口），可以被认为是输出的“扩展”。实际上，你是将输出设备从属到SPI总线的主机。

- 13.电源LED指示灯

当你将Arduino插入电源时，此LED指示灯应亮起，表明你的电路板已正确通电。如果这个指示灯不亮，那么连接就出现了问题。

- 14.TX和RX LED

在你的板上，你会发现两个标签：TX（发送）和RX（接收）。它们出现在Arduino UNO板的两个地方。首先，在数字引脚0和1处，指示引脚负责串行通信。其次，TX和RX LED（13）。发送串行数据时，TX LED以不同的速度闪烁。闪烁速度取决于板所使用的波特率。RX在接收过程中闪烁。

- 15.数字I/O

Arduino UNO板有14个数字I/O引脚（15）（其中6个提供PWM（脉宽调制）输出），这些引脚可配置为数字输入引脚，用于读取逻辑值（0或1） ；或作为数字输出引脚来驱动不同的模块，如LED，继电器等。标有“〜”的引脚可用于产生PWM。

- 16.AREF

AREF代表模拟参考。它有时用于设置外部参考电压（0至5伏之间）作为模拟输入引脚的上限。

####（2）Arduino I/O函数 ####

Arduino板上的引脚可以配置为输入或输出。我们将在这些模式下解释引脚的功能。重要的是要注意，大多数Arduino模拟引脚可以按照与数字引脚完全相同的方式进行配置和使用。

##### 引脚配置为INPUT #####

Arduino引脚默认配置为输入，因此在使用它们作为输入时，不需要使用 pinMode()显式声明为输入。以这种方式配置的引脚被称为处于高阻抗状态。输入引脚对采样电路的要求非常小，相当于引脚前面的100兆欧的串联电阻。

这意味着将输入引脚从一个状态切换到另一个状态所需的电流非常小。这使得引脚可用于诸如实现电容式触摸传感器或读取LED作为光电二极管的任务。

被配置为pinMode（pin，INPUT）的引脚（没有任何东西连接到它们，或者有连接到它们而未连接到其他电路的导线），报告引脚状态看似随机的变化，从环境中拾取电子噪音或电容耦合附近引脚的状态。

##### 上拉电阻 #####

如果没有输入，上拉电阻通常用于将输入引脚引导到已知状态。这可以通过在输入端添加上拉电阻（到5V）或下拉电阻（接地电阻）来实现。10K电阻对于上拉或下拉电阻来说是一个很好的值。

###### 使用内置上拉电阻，引脚配置为输入 ######
Atmega芯片内置了2万个上拉电阻，可通过软件访问。通过将pinMode()设置为INPUT_PULLUP可访问这些内置上拉电阻。这有效地反转了INPUT模式的行为，其中HIGH表示传感器关闭，LOW表示传感器开启。此上拉的值取决于所使用的微控制器。在大多数基于AVR的板上，该值保证在20kΩ和50kΩ之间。在Arduino Due上，它介于50kΩ和150kΩ之间。有关确切的值，请参考板上微控制器的数据表。

当将传感器连接到配置为INPUT_PULLUP的引脚时，另一端应接地。在简单开关的情况下，这会导致当开关打开时引脚变为高电平，当按下开关时引脚为低电平。上拉电阻提供足够的电流来点亮连接到被配置为输入的引脚的LED。如果项目中的LED似乎在工作，但很昏暗，这可能是发生了什么。

控制引脚是高电平还是低电平的相同寄存器（内部芯片存储器单元）控制上拉电阻。因此，当引脚处于INPUT模式时，配置为有上拉电阻导通的引脚将被开启；如果引脚通过pinMode()切换到OUTPUT模式，引脚将配置为高电平。这也适用于另一个方向，如果通过pinMode()切换到输入，则处于高电平状态的输出引脚将设置上拉电阻。

###### 示例： ######

	pinMode(3,INPUT) ; // set pin to input without using built in pull up resistor
	pinMode(5,INPUT_PULLUP) ; // set pin to input using built in pull up resistor

##### 引脚配置为OUTPUT #####

通过pinMode()配置为OUTPUT的引脚被认为处于低阻抗状态。这意味着它们可以向其他电路提供大量的电流。Atmega引脚可以向其他器件/电路提供（提供正电流）或吸收（提供负电流）高达40mA（毫安）的电流。这是足以点亮LED或者运行许多传感器的电流（不要忘记串联电阻），但不足以运行继电器，螺线管或电机。

试图从输出引脚运行高电流器件，可能损坏或破坏引脚中的输出晶体管，或损坏整个Atmega芯片。通常，这会导致微控制器中出现“死”引脚，但是剩余的芯片仍然可以正常工作。因此，最好通过470Ω或1k电阻将OUTPUT引脚连接到其他器件，除非特定应用需要从引脚吸取最大电流。

##### pinMode()函数 #####

pinMode()函数用于将特定引脚配置为输入或输出。可以使用INPUT_PULLUP模式启用内部上拉电阻。此外，INPUT模式显式禁止内部上拉。

pinMode()函数语法：

	void setup () {
	   pinMode (pin , mode);
	}

- pin - 你希望设置模式的引脚的编号
- mode - INPUT，OUTPUT或INPUT_PULLUP。

实例：

	int button = 5 ; // button connected to pin 5
	int LED = 6; // LED connected to pin 6
	
	void setup () {
	   pinMode(button , INPUT_PULLUP); 
	   // set the digital pin as input with pull-up resistor
	   pinMode(button , OUTPUT); // set the digital pin as output
	}
	
	void setup () {
	   If (digitalRead(button ) == LOW) // if button pressed {
	      digitalWrite(LED,HIGH); // turn on led
	      delay(500); // delay for 500 ms
	      digitalWrite(LED,LOW); // turn off led
	      delay(500); // delay for 500 ms
	   }
	}


##### digitalWrite()函数 #####

digitalWrite()函数用于向数字引脚写入HIGH或LOW值。如果该引脚已通过pinMode()配置为OUTPUT，则其电压将被设置为相应的值：HIGH为5V（或3.3V在3.3V板上），LOW为0V（接地）。如果引脚配置为INPUT，则digitalWrite()将启用（HIGH）或禁止（LOW）输入引脚的内部上拉。建议将pinMode()设置为INPUT_PULLUP，以启用 内部上拉电阻。

如果不将pinMode()设置为OUTPUT，而将LED连接到引脚，则在调用digitalWrite(HIGH)时，LED可能会变暗。在没有明确设置pinMode()时，digitalWrite()将启用内部上拉电阻，这就像一个大的限流电阻。

digitalWrite()函数语法：

	Void loop() {
	   digitalWrite (pin ,value);
	}

- pin - 你希望设置模式的引脚的编号
- value - HIGH或LOW。

实例：

	int LED = 6; // LED connected to pin 6
	
	void setup () {
	   pinMode(LED, OUTPUT); // set the digital pin as output
	}
	
	void setup () { 
	   digitalWrite(LED,HIGH); // turn on led
	   delay(500); // delay for 500 ms
	   digitalWrite(LED,LOW); // turn off led
	   delay(500); // delay for 500 ms
	}

####（3）Arduino串口通信 ####

今天，大多数Arduino板都是用几种不同的串行通信系统作为标准设备。

使用哪个系统取决于以下因素:

- 微控制器有多少个器件与数据交换？
- 数据交换的速度有多快？
- 这些设备之间的距离是多少？
- 是否需要同时发送和接收数据？

有关串行通信的最重要的事情之一是协议，应该严格遵守。它是一套规则，必须应用这些规则才能使设备正确地解释它们相互交换的数据。幸运的是，Arduino会自动处理这个问题，这样程序员/用户的工作就可以简化为简单的写（发送的数据）和读（接收的数据）

串行通信可以进一步分类为：

- 同步 - 同步的设备使用相同的时钟，它们的时序彼此同步。
- 异步 - 异步的设备具有各自的时钟，并由前一状态的输出触发。

很容易找出设备是否同步。如果给所有连接的设备提供相同的时钟，则它们是同步的。如果没有时钟线，它是异步的。

例如，UART（通用异步收发器）模块是异步的。

异步串行协议有一些内置的规则。这些规则只是有助于确保可靠且无误的数据传输的机制。这些避免外部时钟信号的机制是：

- Synchronization bits 同步位
- Data bits 数据位
- Parity bits 奇偶校验位
- Baud rate 波特率

同步位
同步位是与每个数据包传输的两个或三个特殊位。它们是起始位和停止位。正如它们的名称，这些位分别标记数据包的开始和结束。

起始位始终只有一个，但停止位的数量可以配置为一个或两个（尽管通常保持为1）。

起始位始终由从1到0的空闲数据线指示，而停止位将通过将线保持在1处而转换回空闲状态。



##### 同步位 #####
同步位是与每个数据包传输的两个或三个特殊位。它们是起始位和停止位。正如它们的名称，这些位分别标记数据包的开始和结束。

起始位始终只有一个，但停止位的数量可以配置为一个或两个（尽管通常保持为1）。

起始位始终由从1到0的空闲数据线指示，而停止位将通过将线保持在1处而转换回空闲状态。

![](https://i.imgur.com/XqfhmNx.png)

##### 数据位 #####
每个分组中的数据量可以设置为5到9位的任意大小。当然，标准数据大小是基本8位字节，但其他大小有它们的用途。7位数据包的效率可能比8位高，特别是如果你只是传输7位ASCII字符。

##### 奇偶校验位 #####
用户可以选择是否应该有奇偶校验位，如果是，则奇偶校验应该是奇数还是偶数。如果数据位中的1的数目是偶数，则奇偶校验位为0。奇数的奇偶校验正好相反。

##### 波特率 #####
术语波特率用于表示每秒传输的位数[bps]。注意，它指的是位，而不是字节。协议通常要求每个字节与几个控制位一起传输。这意味着串行数据流中的一个字节可以包括11位。例如，如果波特率为300bps，则每秒可以传输最大37字节和最小27字节。

下面这个例子将展示如何使用串口：

	void setup() {
	   Serial.begin(9600); //set up serial library baud rate to 9600
	}
	
	void loop() {
	   if(Serial.available()) { //if number of bytes (characters) available for reading from serial port
	      char c = Serial.read();
	      Serial.print(c); //print I received
	   }
	}

运行结果如下：

![](https://i.imgur.com/5IEGH9F.png)

串口助手将打印我输入的内容。

### 2、Arduino控制LED ###

LED是用于许多不同应用的小型强光灯。首先，我们将学习闪烁LED，即微控制器的Hello World。它就像打开和关闭灯一样简单。建立这个重要的基线将为你提供坚实的基础，以实现更复杂的实验。

#### 必需的组件 ####

你将需要以下组件：

- 1 × Breadboard 面包板
- 1 × Arduino Uno R3
- 1 × LED
- 1 × 330Ω 电阻
- 2 × 跳线

按照电路图连接面包板上的组件，如下图所示：

![](https://i.imgur.com/XIZaKvA.png)

注意 - 要了解LED的极性，请仔细查看。两个腿中较短的，朝向灯泡的平坦边缘表示负极端子：

![](https://i.imgur.com/9EINGzG.png)

像电阻器这样的组件需要将其端子弯曲成90°角，以便恰当的适配面包板插座。你也可以将端子切短：

![](https://i.imgur.com/5ov9YAA.png)

#### Arduino代码 ####

	void setup() {  // initialize digital pin 2 as an output.
	   pinMode(2, OUTPUT);
	}
	
	// the loop function runs over and over again forever
	
	void loop() {
	   digitalWrite(2, HIGH); // turn the LED on (HIGH is the voltage level)
	   delay(1000); // wait for a second
	   digitalWrite(2, LOW); // turn the LED off by making the voltage LOW
	   delay(1000); // wait for a second
	}

#### 代码说明 ####

pinMode(2，OUTPUT) - 在使用Arduino的引脚之前，你需要告诉Arduino Uno R3它是INPUT还是OUTPUT。我们使用一个内置的“函数”pinMode()来做到这一点。

digitalWrite(2，HIGH) - 当使用引脚作为OUTPUT时，可以将其命令为HIGH（输出5伏）或LOW（输出0伏）。

#### 结果 ####

你应该看到你的LED打开和关闭。如果没有看到所需的输出，请确保你已正确组装电路，并已验证和将代码上传到电路板。

![](https://i.imgur.com/wq1k1tt.jpg)

![](https://i.imgur.com/aPbdhdv.jpg)

### 3、Arduino控制直流电机 ###

直流电机（DC—Direct Current motor）是最常见的电机类型。直流电动机通常只有两个引线，一个正极和一个负极。如果将这两根引线直接连接到电池，电机将旋转。如果切换引线，电机将以相反的方向旋转。

![](https://i.imgur.com/Cm9P6B3.png)

#### 警告 - 不要直接从Arduino板引脚驱动电机。这可能会损坏电路板。使用驱动电路或IC ####

#### 必需的组件 ####


- 1x Arduino UNO 板
- 1x L298N电机驱动模块
- 1x 小型6V直流电机

#### 连接图如下： ####

![](https://i.imgur.com/xB5b8fG.jpg)

- Arduino 3~管脚接L298N的IN1管脚
- Arduino 5~管脚接L298N的IN2管脚
- Arduino GND管脚接L298N的GND管脚
- Arduino 5V管脚接L298N的+12V管脚（一定是+12V管脚，至于为什么，L298N手册上有说明）
- 电机红线一端接L298N的OUT1
- 电机红线一端接L298N的OUT2

#### 程序 ####

	int motorPinL = 3;
	int motorPinR = 5;
	void setup() {
	
	}
	
	void loop() {
	   digitalWrite(motorPinL, HIGH);
	   digitalWrite(motorPinR, HIGH);
	}

如果一切正常，可以看到电机轮子转动，例如：

![](https://i.imgur.com/VHwxQaw.jpg)

### 4、Arduino控制小车 ###

这部分将不做实验，只进行电路连接和运动代码准备，具体将结合下一节内容。

线路连接图如下：

![](https://i.imgur.com/O00B7Cb.jpg)

![](https://i.imgur.com/wMRN8TQ.jpg)

下面将具体介绍如何连接各个管教：

- 电池盒正极接L298N的+12v端
- 电池盒负极接L298N的GND端
- Arduino 5V管脚接L298N的+5V端
- Arduino GND管脚接L298N的GND端
- Arduino ~3管脚接L298N的IN1管脚
- Arduino ~5管脚接L298N的IN2管脚
- Arduino ~6管脚接L298N的IN3管脚
- Arduino ~9管脚接L298N的IN4管脚
- 电机组右两端分别接L298N的OUT1和OUT2
- 电机组左两端分别接L298N的OUT3和OUT4
- Arduino GND管脚接串口模块GND端
- Arduino Tx->1端口接串口模块的RX端
- Arduino Rx->2端口接串口模块的TX端

注意：电机组如何接还需要读者自行尝试连接，知道前后左右运动同步，这个没有唯一标准，需要微调。

还需要注意的是左右两组电机中每一组电机两两的连接方法：

![](https://i.imgur.com/lZjXSLj.jpg)

同一组电机两两管脚交叉连接，如上图所示。

下面将给出控制前后左右停的控制代码片段：

	int left_up = 3;
	int left_down = 5;
	int right_up = 6;
	int right_down = 9;
	
	void stops(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 0);
	}
	void forword(void)
	{
	  digitalWrite(left_up, 1);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 1);
	  digitalWrite(right_down, 0);
	}
	
	void back(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 1);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 1);
	}
	
	void left(void)                                               
	{
	  digitalWrite(left_up, 1);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 1);
	}
	
	void right(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 1); 
	  digitalWrite(right_up, 1);
	  digitalWrite(right_down, 0);
	}


### 5、Arduino使用串口控制小车 ###

上一节中已经给Arduino连接了一个串口模块，这个串口模块是Arduino与PC或者树莓派通信的接口，可能你会问为什么不直接使用USB线连接，这里需要注意的是L298N模块，我们的Arduino电源5V是和L298N模块的+5V连接的，L298N模块在给它供电，所以这里不能再接一个电源，不能使用USB线。

电路连接图和上节一模一样，下面给出测试的代码：

	int left_up = 3;
	int left_down = 5;
	int right_up = 6;
	int right_down = 9;
	
	void stops(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 0);
	}
	void forword(void)
	{
	  digitalWrite(left_up, 1);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 1);
	  digitalWrite(right_down, 0);
	}
	
	void back(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 1);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 1);
	}
	
	void left(void)                                               
	{
	  digitalWrite(left_up, 1);
	  digitalWrite(left_down, 0);
	  digitalWrite(right_up, 0);
	  digitalWrite(right_down, 1);
	}
	
	void right(void)
	{
	  digitalWrite(left_up, 0);
	  digitalWrite(left_down, 1); 
	  digitalWrite(right_up, 1);
	  digitalWrite(right_down, 0);
	}
	void setup() { 
	  pinMode(left_up, OUTPUT); 
	  pinMode(left_down, OUTPUT);
	  pinMode(right_up, OUTPUT);
	  pinMode(right_down, OUTPUT);
	  Serial.begin(9600);
	}
	void delay_(int ms)//自制软件延时
	{
	        for(int i=0; i<ms; i++)
	        {
	           for(unsigned long j=0;j<1985;j++) ;
	        }        
	}
	void delay1()//自制软件延时1ms
	{
	        for(unsigned long j=0;j<1985;j++) ;    
	}
	
	void loop() { 
	  int i;
	  if(Serial.available())
	  {
	    i = Serial.read();
	    switch(i)
	    {
	          case '1':forword();break;
	          case '2':back();break;
	          case '3':left();delay(1000);stops();break;
	          case '4':right();delay(1000);stops();break;
	          case '5':stops();break;
	          default:forword();
	    }
	  }
	}

如果你的连接没有问题，那么通过Arduino IDE的串口监视器输入1-5中字符，小车将完成相印的动作。

如果你觉得这样很不方便，还需要连接电脑来调试，那么下面这个方法将给你带来更加激动的体验。

首先需要额外介绍一款蓝牙串口模块：

![](https://i.imgur.com/vFBmadf.png)
![](https://i.imgur.com/YXjy129.png)

你所购买的蓝牙模块最好和我的一模一样，不然后面的调试软件可能用起来会不怎么顺利。可以对比这个蓝牙模块和上节提到的串口模块，将串口模块和Arduino连接线一一对应接到蓝牙模块上，也就是说让蓝牙模块取代串口模块，注意RXD、TXD和GND不要接错了，然后可以下载安装附加资料里面的蓝牙调试软件，打开手机蓝牙开始“发短信”控制小车运动。