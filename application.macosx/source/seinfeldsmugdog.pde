/* @pjs preload = "seinfeld.jpg"; */
import ddf.minim.*;

Minim minim;
AudioPlayer alarm;
AudioPlayer chimes;
AudioPlayer seinfeldnoise;

PImage smugdog;
PImage seinfeld;
int redtint = 255; // red value for alarm sequence
int fader = 255; // alpha value for jerry fade
boolean redJerry = true; // triggers Jerry's red tint if true
boolean akey; //tracks if 'A' key was pressed
boolean skey; //tracks if 'S' was pressed
int redFlashCounter = 0; // number of red flashes performed
int flashDelay = 30; // delay between flashes


void setup () {
  size(418, 409);
  minim = new Minim(this);
  alarm = minim.loadFile("alarm.wav");
  chimes = minim.loadFile("chimes.mp3");
  seinfeldnoise = minim.loadFile("seinfeldnoise.mp3");
  smugdog = loadImage("smugdog.jpg");
  seinfeld = loadImage("seinfeld.jpg");
}

void draw() {
  background(smugdog);
  jerryShow();

  fadeCheck();
  jerryBack();
  printText();
}

void keyTyped() {
  if (key == 'a' || key == 'A') {
    redFlashCounter = 10*flashDelay;
    alarm.play(0);
    akey = true;
  }

  if (key == 's' || key == 'S') {
    skey = true;
  }
}

