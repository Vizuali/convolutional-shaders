import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class convolution extends PApplet {

PImage label;
PShape convolutionCan;
PShape originalCan;
float angle;

PShader selShader;

PShader edgeShader;
PShader edge1Shader;
PShader edge2Shader;
PShader sharpShader;
PShader boxBlurShader;
PShader gsShader;
PShader identityShader;

public void setup() {
  
  label = loadImage("lachoy.jpg");
  originalCan = createCan(100, 200, 32, label);
  convolutionCan = createCan(100, 200, 32, label);

  edgeShader = loadShader("edgefrag.glsl");
  edge1Shader = loadShader("edge1frag.glsl");
  edge2Shader = loadShader("edge2frag.glsl");
  sharpShader = loadShader("sharpfrag.glsl");
  boxBlurShader = loadShader("boxblurfrag.glsl");
  gsShader = loadShader("gsfrag.glsl");
  identityShader = loadShader("identityfrag.glsl");

  selShader = identityShader;
}

public void draw() {    
  background(0);
  shader(selShader);
  
  pushMatrix();
  translate(width / 4, height/2);
  rotateY(angle);  
  shape(originalCan);  
  angle += 0.01f;
  popMatrix();

  resetShader();
  pushMatrix();
  translate(3 * width / 4, height/2);
  rotateY(angle);  
  shape(convolutionCan);  
  angle += 0.01f;
  popMatrix();
}

public PShape createCan(float r, float h, int detail, PImage tex) {
  textureMode(NORMAL);
  
  PShape sh = createShape();
  sh.beginShape(QUAD_STRIP);
  sh.noStroke();
  sh.texture(tex);
  for (int i = 0; i <= detail; i++) {
    float angle = TWO_PI / detail;
    float x = sin(i * angle);
    float z = cos(i * angle);
    float u = PApplet.parseFloat(i) / detail;
    sh.normal(x, 0, z);
    sh.vertex(x * r, -h/2, z * r, u, 0);
    sh.vertex(x * r, +h/2, z * r, u, 1);    
  }
  sh.endShape(); 
  return sh;
}

public void keyPressed() {
  if (key == 'i') {
    println("Identity Shader");
    selShader = identityShader;
  } else if (key == 'e') {
    println("Edge Shader");
    selShader = edgeShader;
  } else if (key == 'r') {
    println("Edge1 Shader");
    selShader = edge1Shader;
  } else if (key == 't') {
    println("Edge2 Shader");
    selShader = edge2Shader;
  } else if (key == 's') {
    println("Sharp Shader");
    selShader = sharpShader;
  } else if (key == 'v') {
    println("BoxBlur Shader");
    selShader = boxBlurShader;
  } else if (key == 'g') {
    println("Gray Scale Shader");
    selShader = gsShader;
  }
}
  public void settings() {  size(640, 360, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "convolution" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
