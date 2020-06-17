#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

uniform sampler2D texture;
uniform vec2 texOffset;

varying vec4 vertColor;
varying vec4 vertTexCoord;

varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main() {
  vec2 tc4 = vertTexCoord.st + vec2(         0.0,          0.0);

  vec4 col4 = texture2D(texture, tc4);

  float gray = 0.212*col4.rgb[0] + 0.701*col4.rgb[1] + 0.087*col4.rgb[2];

  vec3 grayscale = vec3(gray);

  gl_FragColor = vec4(grayscale, 1.0);
}