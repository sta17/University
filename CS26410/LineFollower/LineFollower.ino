// ### header files ###
#include <Arduino.h>
#include <Servo.h>
#include <EEPROM.h>

// ### defines and constant values ###
#define SERVO_LEFT_PIN 5
//left wheel  <-
#define SERVO_RIGHT_PIN 6
//right wheel ->
#define BUTTON_PIN_LEFT 2
//switch also
#define BUTTON_PIN_RIGHT 4
//switch also
#define LED_GREEN_PIN 7
#define LED_RED_PIN 13
#define LED_YELLOW_PIN 12
//the LED lights
#define LIGHT_DETECTOR_LEFT_PIN A0
#define LIGHT_DETECTOR_MIDDLE_PIN A1
#define LIGHT_DETECTOR_RIGHT_PIN A2
//the LED lights black value
#define LIGHT_DETECTOR_LEFT_BLACK_VALUE 400
#define LIGHT_DETECTOR_MIDDLE__BLACK_VALUE 400
#define LIGHT_DETECTOR_RIGHT_BLACK_VALUE 400
//need to be tested.
// ID of the settings block
#define CONFIG_VERSION "ls1"
// Tell it where to store your config data in EEPROM
#define CONFIG_START 32

//Storage Struct for EEPROM
struct StoreStruct {
  // This is for mere detection if they are your settings
  char version[4];
  // The variables of your settings
  int LEFT, RIGHT, BLACK;
} calibrations = {
  CONFIG_VERSION,
  // The default values
  86, 83, 1150
};

// ### NOTES ###
//A0, A1, A2 are LDR, read from analog input pins
//Buttons have high as pressed
//and high, when nothing, opposite of button

// ### variables ###
int leftbrightness = 0;
int middlebrightness = 0;
int rightbrightness = 0;
Servo servoleft;
Servo servoright;

// ### Function declerations ###
void saveCalibrations();
void loadCalibrations();
void calibrate();
void lightwave();

// ### Setup ###
void setup() {
  // ### Servos ###
  servoleft.attach(SERVO_LEFT_PIN);
  servoright.attach(SERVO_RIGHT_PIN);
  servoright.write(83);
  servoleft.write(86);

  // ### Pin Outputs/Inputs ###
  pinMode(LED_GREEN_PIN, OUTPUT);
  pinMode(LED_RED_PIN, OUTPUT);
  pinMode(LED_YELLOW_PIN, OUTPUT);
  pinMode(LIGHT_DETECTOR_LEFT_PIN, INPUT);
  pinMode(LIGHT_DETECTOR_MIDDLE_PIN, INPUT);
  pinMode(LIGHT_DETECTOR_RIGHT_PIN, INPUT);
  pinMode(BUTTON_PIN_LEFT, INPUT);
  pinMode(BUTTON_PIN_RIGHT, INPUT);

  // ### Other ###
  loadCalibrations();
  Serial.begin(9600);
  Serial.println("Ready");
}

// ### Loop ###
void loop() {
  // ### Static Variables ###

  // ### Main Code ###
  leftbrightness = analogRead(LIGHT_DETECTOR_LEFT_PIN);
  middlebrightness = analogRead(LIGHT_DETECTOR_MIDDLE_PIN);
  rightbrightness = analogRead(LIGHT_DETECTOR_RIGHT_PIN);

  int buttonStateleft = digitalRead(BUTTON_PIN_LEFT);
  if (buttonStateleft == LOW) {
    calibrate();
  }

  Serial.println(calibrations.BLACK);
  //Serial.println(leftbrightness);
  if (leftbrightness < LIGHT_DETECTOR_LEFT_BLACK_VALUE) {
    delay(1000);
    digitalWrite(LED_GREEN_PIN, HIGH);
  } else {
    delay(1000);
    digitalWrite(LED_GREEN_PIN, LOW);
  }
  if (middlebrightness < LIGHT_DETECTOR_MIDDLE__BLACK_VALUE) {
    delay(1000);
    digitalWrite(LED_YELLOW_PIN, HIGH);
  } else {
    delay(1000);
    digitalWrite(LED_YELLOW_PIN, LOW);
  }
  if (rightbrightness < LIGHT_DETECTOR_RIGHT_BLACK_VALUE) {
    delay(1000);
    digitalWrite(LED_RED_PIN, HIGH);
  } else {
    delay(1000);
    digitalWrite(LED_RED_PIN, LOW);
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

    int buttonStateright = digitalRead(BUTTON_PIN_RIGHT);
    if (buttonStateright == LOW) {
      calibrationoptions++;
    }

    if (calibrationoptions == 1) {
      digitalWrite(LED_GREEN_PIN, HIGH);
    } else if (calibrationoptions == 4) {
      digitalWrite(LED_RED_PIN, HIGH);
    }

    int buttonStateleft = digitalRead(BUTTON_PIN_LEFT);
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

          int buttonStateright = digitalRead(BUTTON_PIN_RIGHT);
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

              int buttonStateright = digitalRead(BUTTON_PIN_RIGHT);
              if (buttonStateright == LOW) {
                stopvalue++;
                if (stopvalue > 180) {
                  stopvalue = 0;
                }
                Serial.println(stopvalue);
                servoleft.write(stopvalue);
              }

              int buttonStateleft = digitalRead(BUTTON_PIN_LEFT);
              if (buttonStateleft == LOW) {
                calibrations.RIGHT = stopvalue;
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

              int buttonStateright = digitalRead(BUTTON_PIN_RIGHT);
              if (buttonStateright == LOW) {
                stopvalue++;
                if (stopvalue > 180) {
                  stopvalue = 0;
                }
                Serial.println(stopvalue);
                servoright.write(stopvalue);
              }
              int buttonStateleft = digitalRead(BUTTON_PIN_LEFT);
              if (buttonStateleft == LOW) {
                calibrations.LEFT = stopvalue;
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
          leftbrightness = analogRead(LIGHT_DETECTOR_LEFT_PIN);
          middlebrightness = analogRead(LIGHT_DETECTOR_MIDDLE_PIN);
          rightbrightness = analogRead(LIGHT_DETECTOR_RIGHT_PIN);
          
          int buttonStateright = digitalRead(BUTTON_PIN_RIGHT);
              if (buttonStateright == LOW) {
                black+=20;
                if (black > 1000) {
                  black = 350;
                }
                Serial.println(black);
              }
          
          if (leftbrightness < black) {
            delay(1000);
            digitalWrite(LED_GREEN_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_GREEN_PIN, LOW);
          }
          if (middlebrightness < black) {
            delay(1000);
            digitalWrite(LED_YELLOW_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_YELLOW_PIN, LOW);
          }
          if (rightbrightness < black) {
            delay(1000);
            digitalWrite(LED_RED_PIN, HIGH);
          } else {
            delay(1000);
            digitalWrite(LED_RED_PIN, LOW);
          }
          int buttonStateleft = digitalRead(BUTTON_PIN_LEFT);
          if (buttonStateleft == LOW) {
            calibrations.BLACK = black;
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
