// Calibration tool for modular robots
// Copyright Filippo Sanfilippo
#include <SoftwareServo.h>
 
SoftwareServo servoleft;
SoftwareServo servoright;

#define servoleftpin 6 
//left wheel
#define servorightpin 5 
//right wheel
 
void setup()
{
  servoleft.attach(servoleftpin);
  servoright.attach(servorightpin);
  servoleft.setMaximumPulse(2200);
  servoright.setMaximumPulse(2200);
  Serial.begin(9600);
  Serial.println("Ready");
}
 
void loop()
{
  static int value = 0;
 
  if ( Serial.available()) {
    char ch = Serial.read();
    switch(ch) {
      case 'a':
        value = 0;
        servoleft.write(value);
        servoright.write(value);
        Serial.println("0 degrees");
        break;
      case 'b':
        value = 90;
        servoleft.write(value);
        servoright.write(value);
        Serial.println("90 degrees");
        break;
      case 'c':
        value = 180;
        servoleft.write(value);
        servoright.write(value);
        Serial.println("180 degrees");
        break;
    }
  }
  SoftwareServo::refresh();
}
