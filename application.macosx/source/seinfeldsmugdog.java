import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class seinfeldsmugdog extends PApplet {

/* @pjs preload = "seinfeld.jpg"; */


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


public void setup () {
  size(418, 409);
  minim = new Minim(this);
  alarm = minim.loadFile("alarm.wav");
  chimes = minim.loadFile("chimes.mp3");
  seinfeldnoise = minim.loadFile("seinfeldnoise.mp3");
  smugdog = loadImage("smugdog.jpg");
  seinfeld = loadImage("seinfeld.jpg");
}

public void draw() {
  background(smugdog);
  jerryShow();

  fadeCheck();
  jerryBack();
  printText();
}

public void keyTyped() {
  if (key == 'a' || key == 'A') {
    redFlashCounter = 10*flashDelay;
    alarm.play(0);
    akey = true;
  }

  if (key == 's' || key == 'S') {
    skey = true;
  }
}

public void printText() {
  fill(222,98,16);
  textSize(12);
  textAlign(LEFT);
  text("Press A. Later, press S.",20,20);
}
  



//checks if Jerry needs to be flashing or faded and acts appropriately
public void fadeCheck() {
  if (redFlashCounter > 0) {
    redFlashCounter--;
  }
  if (redFlashCounter % flashDelay == 0) {
    if (redFlashCounter !=0) {
      redFlash();
    }
  }
  if (redFlashCounter <= 0 && akey && fader > 0) {
    fader--;
    jerryFade();
  }

  if (fader == 0) {
    akey = false;
  }
}


// makes Jerry flash red and white if called
public void redFlash() {
  if (redJerry) {
    tint(255, 0, 0);
    image(seinfeld, 0, 0);
    redJerry = false;
  }
  else {
    noTint();
    image(seinfeld, 0, 0);
    redJerry = true;
  }
}

// makes Jerry show up
public void jerryShow() {  
  image(seinfeld, 0, 0);
}

// begin fading process and play chimes at end
public void jerryFade() {
  if (fader > 0 && fader < 255) {
    tint(255, fader);
    jerryShow();
    fader--;
    delay(5);
  }

  if (fader < 15) {
    chimes.play();
    chimes = minim.loadFile("chimes.mp3");
  }
}



// makes jerry fade back in from dog picture, plays seinfeld noise at end
public void jerryBack() {
  if (fader < 255 && skey) {
    fader+=2;
    tint(255, fader);
    jerryShow();

    if (fader >= 200 && skey) {
      seinfeldnoise.play();
    }
    if (fader >= 255 && skey) {
      seinfeldnoise = minim.loadFile("seinfeldnoise.mp3");
      skey = false;
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "seinfeldsmugdog" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
