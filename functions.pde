void printText() {
  fill(222,98,16);
  textSize(12);
  textAlign(LEFT);
  text("Press A. Later, press S.",20,20);
}
  



//checks if Jerry needs to be flashing or faded and acts appropriately
void fadeCheck() {
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
void redFlash() {
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
void jerryShow() {  
  image(seinfeld, 0, 0);
}

// begin fading process and play chimes at end
void jerryFade() {
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
void jerryBack() {
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

