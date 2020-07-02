import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Arrays; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_200108b extends PApplet {



int nPoints = 100;
int pointSize = 5;
int multiplier = 2;


public void setup() {
  
  background(0);
}

public float[][][] init() {
  float step=TWO_PI/nPoints;
  int diameter = 500;
  int radius = diameter/2;
  int halfSize = nPoints/2;
  float xCoords[] = new float[nPoints];
  float yCoords[] = new float[nPoints];
  float xC[] = new float[nPoints];
  float yC[] = new float[nPoints];
  float longXC[] = new float[multiplier*nPoints];
  float longYC[] = new float[multiplier*nPoints];
  float lines[][][] = new float[nPoints][2][2];
  
  float d=0;
  for(int i=0;i<nPoints;i++) {
    xCoords[i] = (1000/2)+radius*cos(d);
    yCoords[i] = (500/2)-radius*sin(d)+50;
    d+=step;
    
  }
  
  //shift points' coordinates by half
  System.arraycopy(xCoords,halfSize,xC,0,halfSize);
  System.arraycopy(yCoords,halfSize,yC,0,halfSize);
  System.arraycopy(xCoords,0,xC,halfSize,halfSize);
  System.arraycopy(yCoords,0,yC,halfSize,halfSize);
  //create long x and y coordinate arrays by concatenation
  for(int i=1;i<multiplier;i++) {
    System.arraycopy(xC,0,longXC,0,nPoints);
    System.arraycopy(xC,0,longXC,nPoints*i,nPoints);
    System.arraycopy(yC,0,longYC,0,nPoints);
    System.arraycopy(yC,0,longYC,nPoints*i,nPoints);
  }
  
  for(int i=0;i<nPoints;i++) {
    
    lines[i][0][0] = xC[i];
    lines[i][0][1] = yC[i];
    
    lines[i][1][0] = longXC[i*multiplier];
    lines[i][1][1] = longYC[i*multiplier];
  }
  return lines;
}

float[][][] ls = init();


public void draw() {
  
  background(0);
  
  textSize(24);
  text("Control",20,60);
  textSize(18);
  text("\u2192" + "   increase points",20,100);
  text("\u2190" + "   decrease points",20,120);
  text("\u2191" + "   increase multiplier",20,140);
  text("\u2193" + "   decrease multiplier",20,160);
  text("Points: " + nPoints,800,100);
  text("Multiplier: " + multiplier,800,120);
  
  
  if(keyPressed) {
    if(key == CODED) {
      if(keyCode == RIGHT){
        nPoints+=2;
      }
      else if(keyCode == LEFT & nPoints>=8) {
        nPoints-=2;
      }
      ls = init();
      }
  }
  
  for(int i=0;i<nPoints;i++) {
 
    }
  
  for(int i=0;i<nPoints;i++) {
    stroke(RGB,120,200,255);
    line(ls[i][0][0],ls[i][0][1],ls[i][1][0],ls[i][1][1]);
  }
 }
 
public void keyPressed() {
    if(key == CODED) {
      if(keyCode == UP) {
        multiplier+=1;
      }
      else if(keyCode == DOWN & multiplier>2) {
        multiplier-=1;
      }
      ls = init();
    }
}

  public void settings() {  size(1000,600,P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_200108b" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
