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
