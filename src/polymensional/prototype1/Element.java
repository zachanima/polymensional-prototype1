package polymensional.prototype1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Element {
  public Element(float x, float y, float w, float h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    float vertices[] = {
        x,   -y,   0.0f,
        x,   -y-h, 0.0f,
        x+w, -y,   0.0f,
        x+w, -y-h, 0.0f
    };
    this.vertices = vertices;
    
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
    byteBuffer.order(ByteOrder.nativeOrder());
    vertexBuffer = byteBuffer.asFloatBuffer();
    vertexBuffer.put(vertices);
    vertexBuffer.position(0);
    
    byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
    byteBuffer.order(ByteOrder.nativeOrder());
    textureBuffer = byteBuffer.asFloatBuffer();
    textureBuffer.put(texture);
    textureBuffer.position(0);
  }

  public void draw(GL10 gl) {
    gl.glPushMatrix();
    gl.glTranslatef(-640.0f, 360.0f, -860.0f); // Normalise to 0,0..1280,720.
    
    gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
    
    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    
    gl.glFrontFace(GL10.GL_CW);
    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
    
    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    gl.glPopMatrix();
  }
  
  private float x;
  private float y;
  private float w;
  private float h;
  private FloatBuffer vertexBuffer;
  private FloatBuffer textureBuffer;
  private float vertices[] = {
    0.0f, 0.0f, 0.0f,
    0.0f, 0.0f, 0.0f,
    0.0f, 0.0f, 0.0f,
    0.0f, 0.0f, 0.0f
  };
  private float texture[] = {
      0.0f, 1.0f,
      0.0f, 0.0f,
      1.0f, 1.0f,
      1.0f, 0.0f
    };
  private int[] textures = new int[1];
}
