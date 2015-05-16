// ### header files ###
#include <Arduino.h>
#include <SimpleTimer.h>
#include <Servo.h>
#include <EEPROM.h>

// ### defines and constant values ###
#define SERVO_LEFT_PIN 5
//left wheel  <-
#define SERVO_RIGHT_PIN 6
//right wheel ->
//#define SERVO_STOP_LEFT_VALUE 86
//stop value for left servo 84 old
//#define SERVO_STOP_RIGHT_VALUE 83
//stop value for right servo
#define BUTTON_LEFT_PIN 4
//switch also
#define BUTTON_RIGHT_PIN 2
//switch also
#define IR_RECIEVER_PIN 2
//IR LED reciever
#define IR_EMITTER_PIN 3
//the IR led/emitter.
#define LED_GREEN_PIN 7
#define LED_RED_PIN 13
#define LED_YELLOW_PIN 12
//the LED lights
#define LIGHT_DETECTOR_LEFT_PIN A0
#define LIGHT_DETECTOR_MIDDLE_PIN A1
#define LIGHT_DETECTOR_RIGHT_PIN A2
//the LED lights black value
//#define LIGHT_DETECTOR_LEFT_BLACK_VALUE 400
//#define LIGHT_DETECTOR_MIDDLE__BLACK_VALUE 400
//#define LIGHT_DETECTOR_RIGHT_BLACK_VALUE 400
// ID of the settings block
#define CONFIG_VERSION "ls1"
// Tell it where to store your config data in EEPROM
#define CONFIG_START 32

// ### NOTES ###
//A0, A1, A2 are LDR, read from analog input pins
//Buttons have high as pressed
//Use tone() for the IR LED - purpose is to make sure it only picks up signal it sends out.
//IR receiver is low when detects something,
//and high, when nothing, opposite of button
// do average of several line readings?

// ### Function Decleration ###
void lineFollowing();
void lineFollowing2();
void readIR();
void IRResponds();
void forwardServo();
void backwardServo();
void totalStopServo();
void rotate(int rotation, int directionint);
void rotateRight();
void rotateLeft();
void saveCalibrations();
void loadCalibrations();
void calibrate();
void lightwave();

// ### variables ###
Servo servoleft;
Servo servoright;
SimpleTimer IRtimer;  // timer that checks for obstacles
int distance = 0;     // Obstacle checker distance.
int leftdetector = 0;
int middledetector = 0;
int rightdetector = 0;
int LIGHT_DETECTOR_LEFT_BLACK_VALUE = 400;
int LIGHT_DETECTOR_MIDDLE__BLACK_VALUE = LIGHT_DETECTOR_LEFT_BLACK_VALUE;
int LIGHT_DETECTOR_RIGHT_BLACK_VALUE = LIGHT_DETECTOR_MIDDLE__BLACK_VALUE;
int SERVO_STOP_LEFT_VALUE = 86;
int SERVO_STOP_RIGHT_VALUE = 84;

//Storage Struct for EEPROM
struct StoreStruct {
  // This is for mere detection if they are your settings
  char version[4];
  // The variables of your settings
  int LEFT, RIGHT, BLACK;
} calibrations = {
  CONFIG_VERSION,
  // The default values
  86, 84, 400
};

// ### Setup ###
void setup() {
  Serial.begin(9600);
  // ### Servos ###
  servoleft.attach(SERVO_LEFT_PIN);
  servoright.attach(SERVO_RIGHT_PIN);

  // ### Pin Outputs/Inputs ###
  pinMode(IR_EMITTER_PIN, OUTPUT); // IR emitter LED on digital pin 3
  digitalWrite(IR_EMITTER_PIN, LOW); // setup IR LED as off
  pinMode(LED_GREEN_PIN, OUTPUT);
  pinMode(LED_RED_PIN, OUTPUT);
  pinMode(LED_YELLOW_PIN, OUTPUT);
  pinMode(LIGHT_DETECTOR_LEFT_PIN, INPUT);
  pinMode(LIGHT_DETECTOR_MIDDLE_PIN, INPUT);
  pinMode(LIGHT_DETECTOR_RIGHT_PIN, INPUT);
  pinMode(BUTTON_LEFT_PIN, INPUT);
  pinMode(BUTTON_RIGHT_PIN, INPUT);

  // ### Other ###
  //IRtimer.setInterval(5000, readIR); // update every 10 seconds
  loadCalibrations();

  LIGHT_DETECTOR_LEFT_BLACK_VALUE = calibrations.BLACK;
  LIGHT_DETECTOR_MIDDLE__BLACK_VALUE = LIGHT_DETECTOR_LEFT_BLACK_VALUE;
  LIGHT_DETECTOR_RIGHT_BLACK_VALUE = LIGHT_DETECTOR_LEFT_BLACK_VALUE;
  SERVO_STOP_LEFT_VALUE = calibrations.LEFT;
  SERVO_STOP_RIGHT_VALUE = calibrations.RIGHT;
}

// ### Loop ###
void loop() {
  // ### Static Variables ###
  // ### Main Code ###
  int buttonStateleft = digitalRead(BUTTON_LEFT_PIN);
  if (buttonStateleft == LOW) {
    calibrate();
  }
  //IRtimer.run();
  //IRResponds();
  delay(50);
  //delay(500);
  lineFollowing2();
  //delay(500);
}

// ### Misc Functions ###

void lineFollowing2() {
  leftdetector = analogRead(LIGHT_DETECTOR_LEFT_PIN);
  middledetector = analogRead(LIGHT_DETECTOR_MIDDLE_PIN);
  rightdetector = analogRead(LIGHT_DETECTOR_RIGHT_PIN);
  if (middledetector > leftdetector && middledetector > rightdetector) {
    digitalWrite(LED_YELLOW_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, HIGH);
    digitalWrite(LED_RED_PIN, LOW);
    Serial.println(middledetector);
    forwardServo();
  } else {
    digitalWrite(LED_YELLOW_PIN, HIGH);
    digitalWrite(LED_GREEN_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, LOW);
    if (rightdetector > leftdetector) {
      Serial.println(leftdetector);
      rotateLeft();
    } else if (leftdetector > rightdetector) {
      Serial.println(rightdetector);
      rotateRight();
    } else {
      digitalWrite(LED_RED_PIN, HIGH);
      digitalWrite(LED_GREEN_PIN, LOW);
      digitalWrite(LED_YELLOW_PIN, LOW);
      totalStopServo();
    }
  }
}

void lineFollowing() {
  leftdetector = analogRead(LIGHT_DETECTOR_LEFT_PIN);
  middledetector = analogRead(LIGHT_DETECTOR_MIDDLE_PIN);
  rightdetector = analogRead(LIGHT_DETECTOR_RIGHT_PIN);
  if ((middledetector < LIGHT_DETECTOR_MIDDLE__BLACK_VALUE) || (middledetector < LIGHT_DETECTOR_MIDDLE__BLACK_VALUE && leftdetector < LIGHT_DETECTOR_LEFT_BLACK_VALUE && rightdetector < LIGHT_DETECTOR_RIGHT_BLACK_VALUE)) {
    digitalWrite(LED_YELLOW_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, HIGH);
    digitalWrite(LED_RED_PIN, LOW);
    Serial.println(middledetector);
    forwardServo();
  } else if (leftdetector < LIGHT_DETECTOR_LEFT_BLACK_VALUE || rightdetector < LIGHT_DETECTOR_RIGHT_BLACK_VALUE) {
    digitalWrite(LED_YELLOW_PIN, HIGH);
    digitalWrite(LED_GREEN_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, LOW);
    if (leftdetector < LIGHT_DETECTOR_LEFT_BLACK_VALUE) {
      Serial.println(leftdetector);
      rotateLeft();
    } else if (rightdetector < LIGHT_DETECTOR_RIGHT_BLACK_VALUE) {
      Serial.println(rightdetector);
      rotateRight();
    }
  } else {
    digitalWrite(LED_RED_PIN, HIGH);
    digitalWrite(LED_GREEN_PIN, LOW);
    digitalWrite(LED_YELLOW_PIN, LOW);
    totalStopServo();
  }
}

void readIR() {
  int ambientIR = 0;                // variable to store the IR coming from the ambient
  int obstacleIR = 0;               // variable to store the IR coming from the object
  digitalWrite(IR_EMITTER_PIN, LOW);    //turning the IR LEDs off to read the IR coming from the ambient
  delay(1);                        // minimum delay necessary to read values
  ambientIR = analogRead(IR_RECIEVER_PIN);   // storing IR coming from the ambient
  digitalWrite(IR_EMITTER_PIN, HIGH);   //turning the IR LEDs on to read the IR coming from the obstacle
  delay(1);                        // minimum delay necessary to read values
  distance = analogRead(IR_RECIEVER_PIN);  // storing IR coming from the obstacle
  digitalWrite(IR_EMITTER_PIN, LOW);
  distance = ambientIR - obstacleIR; // calculating changes in IR values and storing it for future average
  delay(3000);
}

void IRResponds() {
  digitalWrite(LED_YELLOW_PIN, LOW);
  digitalWrite(LED_RED_PIN, LOW);

  if (distance < 560) {
    digitalWrite(LED_GREEN_PIN, LOW);
    if (distance < 250) {
      digitalWrite(LED_RED_PIN, HIGH);
      totalStopServo();
    } else {
      digitalWrite(LED_YELLOW_PIN, HIGH);
      totalStopServo();
    }
  } else {
    lineFollowing();
  }
}

void calibrate() {
  int optionstate = 1;
  int servostate = 0;
  int lightstate = 0;
  int servostateleft = 0;
  int servostateright = 0;
  int calibrationoptions = 0;
  int servooptions = 0;

  lightwave();
  while (optionstate == HIGH) {
    digitalWrite(LED_GREEN_PIN, LOW);
    digitalWrite(LED_YELLOW_PIN, LOW);
    digitalWrite(LED_RED_PIN, LOW);

    int buttonStateright = digitalRead(BUTTON_RIGHT_PIN);
    if (buttonStateright == LOW) {
      calibrationoptions++;
    }

    if (calibrationoptions == 1) {
      digitalWrite(LED_GREEN_PIN, HIGH);
    } else if (calibrationoptions == 4) {
      digitalWrite(LED_RED_PIN, HIGH);
    }

    int buttonStateleft = digitalRead(BUTTON_LEFT_PIN);
    if (buttonStateleft == LOW) {
      if (calibrationoptions > 4) {
        calibrationoptions = 0;
      } else if (calibrationoptions == 1) {
        servostate = 1;
        int stopvalue = 80;

        Serial.println("Entered Servo State");
        lightwave();
        while (servostate == HIGH) {
          digitalWrite(LED_GREEN_PIN, LOW);
          digitalWrite(LED_YELLOW_PIN, LOW);
          digitalWrite(LED_RED_PIN, LOW);

          int buttonStateright = digitalRead(BUTTON_RIGHT_PIN);
          if (buttonStateright == LOW) {
            servooptions++;
          }

          if (servooptions == 2) {
            digitalWrite(LED_YELLOW_PIN, HIGH);
          } else if (servooptions == 3) {
            digitalWrite(LED_GREEN_PIN, HIGH);
            digitalWrite(LED_YELLOW_PIN, HIGH);
          }

          if (servooptions > 3) {
            servooptions = 0;
          } else if (servooptions == 2) {
            servostateleft = 1;
            stopvalue = calibrations.LEFT;

            Serial.println("Entered Servo State Left");
            lightwave();
            while (servostateleft == HIGH) {

              int buttonStateright = digitalRead(BUTTON_RIGHT_PIN);
              if (buttonStateright == LOW) {
                stopvalue++;
                if (stopvalue > 180) {
                  stopvalue = 0;
                }
                Serial.println(stopvalue);
                servoleft.write(stopvalue);
              }

              int buttonStateleft = digitalRead(BUTTON_LEFT_PIN);
              if (buttonStateleft == LOW) {
                calibrations.LEFT = stopvalue;
                SERVO_STOP_LEFT_VALUE = calibrations.LEFT;
                servostateleft = 0;
                servostate = 0;
                optionstate = 0;
                delay(500);
                break;
              }
            }
          } else if (servooptions == 3) {
            servostateright = 1;
            stopvalue = calibrations.RIGHT;

            Serial.println("Entered Servo State Right");
            lightwave();
            while (servostateright == HIGH) {

              int buttonStateright = digitalRead(BUTTON_RIGHT_PIN);
              if (buttonStateright == LOW) {
                stopvalue++;
                if (stopvalue > 180) {
                  stopvalue = 0;
                }
                Serial.println(stopvalue);
                servoright.write(stopvalue);
              }
              int buttonStateleft = digitalRead(BUTTON_LEFT_PIN);
              if (buttonStateleft == LOW) {
                calibrations.RIGHT = stopvalue;
                SERVO_STOP_RIGHT_VALUE = calibrations.RIGHT;
                servostateright = 0;
                servostate = 0;
                optionstate = 0;
                servooptions = 0;
                calibrationoptions = 0;
                delay(500);
                break;
              }
            }
          }
        }
      } else if (calibrationoptions == 4) {
        lightstate = 1;
        int black = calibrations.BLACK;
        Serial.println("Entered Light State");

        lightwave();
        while (lightstate == HIGH) {
          leftdetector = analogRead(LIGHT_DETECTOR_LEFT_PIN);
          middledetector = analogRead(LIGHT_DETECTOR_MIDDLE_PIN);
          rightdetector = analogRead(LIGHT_DETECTOR_RIGHT_PIN);

          int buttonStateright = digitalRead(BUTTON_RIGHT_PIN);
          if (buttonStateright == LOW) {
            black = black + 50;
            if (black > 1000) {
              black = 200;
            }
            Serial.println(black);
          }

          if (leftdetector < black) {
            delay(1000);
            digitalWrite(LED_GREEN_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_GREEN_PIN, LOW);
          }
          if (middledetector < black) {
            delay(1000);
            digitalWrite(LED_YELLOW_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_YELLOW_PIN, LOW);
          }
          if (rightdetector < black) {
            delay(1000);
            digitalWrite(LED_RED_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_RED_PIN, LOW);
          }
          int buttonStateleft = digitalRead(BUTTON_LEFT_PIN);
          if (buttonStateleft == LOW) {
            calibrations.BLACK = black;
            LIGHT_DETECTOR_LEFT_BLACK_VALUE = calibrations.BLACK;
            LIGHT_DETECTOR_MIDDLE__BLACK_VALUE = LIGHT_DETECTOR_LEFT_BLACK_VALUE;
            LIGHT_DETECTOR_RIGHT_BLACK_VALUE = LIGHT_DETECTOR_LEFT_BLACK_VALUE;
            lightstate = 0;
            optionstate = 0;
            calibrationoptions = 0;
            delay(500);
            break;
          }
        }
      }
    }
    delay(1000);
  }
  saveCalibrations();
  lightwave();
}

void lightwave() {
  digitalWrite(LED_GREEN_PIN, HIGH);
  digitalWrite(LED_YELLOW_PIN, HIGH);
  digitalWrite(LED_RED_PIN, HIGH);
  delay(1000);
  digitalWrite(LED_GREEN_PIN, LOW);
  digitalWrite(LED_YELLOW_PIN, LOW);
  digitalWrite(LED_RED_PIN, LOW);
}

void loadCalibrations() {
  // To make sure there are settings, and they are YOURS!
  // If nothing is found it will use the default settings.
  if (//EEPROM.read(CONFIG_START + sizeof(settings) - 1) == settings.version_of_program[3] // this is '\0'
    EEPROM.read(CONFIG_START + sizeof(calibrations) - 2) == calibrations.version[2] &&
    EEPROM.read(CONFIG_START + sizeof(calibrations) - 3) == calibrations.version[1] &&
    EEPROM.read(CONFIG_START + sizeof(calibrations) - 4) == calibrations.version[0])
  { // reads settings from EEPROM
    for (unsigned int t = 0; t < sizeof(calibrations); t++)
      *((char*)&calibrations + t) = EEPROM.read(CONFIG_START + t);
  } else {
    // settings aren't valid! will overwrite with default settings
    saveCalibrations();
  }
}

void saveCalibrations() {
  for (unsigned int t = 0; t < sizeof(calibrations); t++)
  { // writes to EEPROM
    EEPROM.write(CONFIG_START + t, *((char*)&calibrations + t));
    // and verifies the data
    if (EEPROM.read(CONFIG_START + t) != *((char*)&calibrations + t))
    {
      digitalWrite(LED_RED_PIN, HIGH);
      delay(1000);
      digitalWrite(LED_RED_PIN, LOW);
    }
  }
}

// ### Servo Functions ###

void forwardServo() {
  servoleft.write(0);
  servoright.write(180);
}

void backwardServo() {
  servoleft.write(180);
  servoright.write(0);
}

void totalStopServo() {
  servoright.write(SERVO_STOP_RIGHT_VALUE);
  servoleft.write(SERVO_STOP_LEFT_VALUE);
}

void rotateRight() {
  servoleft.write(0);
  servoright.write(0);
}

void rotateLeft() {
  servoleft.write(180);
  servoright.write(180);
}

//1 for left and 2 for right
void rotate(int rotation, int directionint) {
  int leftrotation;
  int rightrotation;
  leftrotation = SERVO_STOP_LEFT_VALUE;
  rightrotation = SERVO_STOP_RIGHT_VALUE;

  if (directionint == 2) {
    if (rotation = 90) {
      leftrotation = 180;
      rightrotation = 0;
    } else {
      leftrotation -= rotation;
      rightrotation -= rotation;
    }

    servoleft.write(leftrotation);
    servoright.write(rightrotation);
  } else if (directionint == 1) {
    if (rotation = 90) {
      leftrotation = 0;
      rightrotation = 180;
    } else {
      leftrotation -= rotation;
      rightrotation -= rotation;
    }

    servoleft.write(leftrotation);
    servoright.write(rightrotation);
  }
}
