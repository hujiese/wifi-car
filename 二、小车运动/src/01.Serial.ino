void setup() {
   Serial.begin(9600); //set up serial library baud rate to 9600
}

void loop() {
   if(Serial.available()) { //if number of bytes (characters) available for reading from serial port
      char c = Serial.read();
      Serial.print(c); //print I received
   }
}
