PImage label;
PShape can;
float angle;

PShader selShader;

PShader edgeShader;
PShader edge1Shader;
PShader edge2Shader;
PShader sharpShader;
PShader boxBlurShader;
PShader gsShader;
PShader identityShader;

void setup() {
  size(640, 360, P3D);
  label = loadImage("lachoy.jpg");
  can = createCan(100, 200, 32, label);

  edgeShader = loadShader("edgefrag.glsl");
  edge1Shader = loadShader("edge1frag.glsl");
  edge2Shader = loadShader("edge2frag.glsl");
  sharpShader = loadShader("sharpfrag.glsl");
  boxBlurShader = loadShader("boxblurfrag.glsl");
  gsShader = loadShader("gsfrag.glsl");
  identityShader = loadShader("identityfrag.glsl");

  selShader = identityShader;
}

void draw() {    
  background(0);
  shader(selShader);
  translate(width/2, height/2);
  rotateY(angle);  
  shape(can);  
  angle += 0.01;
}

PShape createCan(float r, float h, int detail, PImage tex) {
  textureMode(NORMAL);
  
  PShape sh = createShape();
  sh.beginShape(QUAD_STRIP);
  sh.noStroke();
  sh.texture(tex);
  for (int i = 0; i <= detail; i++) {
    float angle = TWO_PI / detail;
    float x = sin(i * angle);
    float z = cos(i * angle);
    float u = float(i) / detail;
    sh.normal(x, 0, z);
    sh.vertex(x * r, -h/2, z * r, u, 0);
    sh.vertex(x * r, +h/2, z * r, u, 1);    
  }
  sh.endShape(); 
  return sh;
}

void keyPressed() {
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