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
