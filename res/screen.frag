uniform sampler2D firstGrassTexture;

uniform sampler2D secondGrassTexture;

uniform sampler2D maskTexture;

void main()

{

 gl_FragColor = mix(texture2D(firstGrassTexture,gl_TexCoord[0].xy), texture2D(secondGrassTexture,gl_TexCoord[0].xy), texture2D(maskTexture,gl_TexCoord[0].xy).a); 
}