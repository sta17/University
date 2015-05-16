//from: http://www.josephdattilo.com/learn-position-servo-degrees-control-timing-arduino/

#include <Servo.h>
#include <Arduino.h>

#define servoleftpin 6
//left wheel
#define servorightpin 5
//right wheel

static int pin = servoleftpin;

Servo myServo;
byte myServoCalibration[2] = {0, 180};
//you can change the above calibration code to
//match your servos range

void setup()
{
  myServo.attach(pin);//set to your PWM servo pin
  //this attaches the servo to pin 6 on your Arduino
  Serial.begin(9600);
  Serial.println("Ready");
}


void loop()
{
  //now we sweep from -90 degrees to 90 degrees, under the assumption that
  //the servo calibration puts us between 0 and 180 degrees motion.
  //We consider 0 to be -90 degrees, and 180 to be 90 degrees.
  for (int servoangle = -90; servoangle <= 90; servoangle++)
  {
    WriteDegreesToServo(myServo, myServoCalibration, servoangle);
    delay(15);
  }
}

//in this example we pass a reference to our calibration array along with the servo
//and the position itself
void WriteDegreesToServo(Servo servoInQuestion, byte* ServoCalibration, int myDegrees)
{
  //note that we are using the Arduino map function to change our calibrated data
  //into a range from -90 to 90 degrees.
  servoInQuestion.write(map(myDegrees, -90, 90, ServoCalibration[0], ServoCalibration[1]));
}

