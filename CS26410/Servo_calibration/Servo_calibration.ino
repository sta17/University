// Calibration tool for modular robots
// Copyright Filippo Sanfilippo
// From: http://filipposanfilippo.inspitivity.com/robotics/item/motor-calibration-for-modular-robot/178
#include <Arduino.h>
#include <Servo.h>
#include <string.h>

#define SERVO_LEFT_PIN 5
//left wheel  <-
#define SERVO_RIGHT_PIN 6
//right wheel ->
#define SERVO_STOP_LEFT_VALUE 86
//stop value for left servo
#define SERVO_STOP_RIGHT_VALUE 84

Servo servo;

#define pin SERVO_LEFT_PIN
#define stopvalue SERVO_STOP_LEFT_VALUE

void setup(){
  servo.attach(pin);
  servo.write(stopvalue);  // change this value to achieve minimum rotation!
  
  Serial.begin(9600);
  Serial.println("Ready");
}

void loop(){
  static int value = 0;
  static int standstill = 85;

  if ( Serial.available()) {
    char ch = Serial.read();
    switch (ch) {
      case 'a':
        value = 0;
        servo.write(value);
        Serial.println("0 degrees");
        break;
      case 'b':
        value = 90;
        servo.write(value);
        Serial.println("90 degrees");
        break;
      case 'c':
        value = 180;
        servo.write(value);
        Serial.println("180 degrees");
        break;
      case 'd':
        servo.write(standstill);
        Serial.println(standstill);
        standstill++;
        break;
      case 'e':
        value = 173;
        servo.write(value);
        Serial.println("173 degrees");
        break;
     case 'f':
        value = -7;
        servo.write(value);
        Serial.println("-7 degrees");
        break;
      case 'g':
        value = 83;
        servo.write(value);
        Serial.println("83 degrees");
        break;
      case 'h':
        value = 84;
        servo.write(value);
        Serial.println("84 degrees");
        break;
    case 't':
        value = 45;
        servo.write(value);
        Serial.println("45 degrees");
        break;
    }
  }
}
