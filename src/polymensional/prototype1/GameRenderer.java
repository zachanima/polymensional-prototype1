package polymensional.prototype1;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GameRenderer implements Renderer {
  public GameRenderer(Context context) {
    this.context = context;
    this.player = new Player();
  }
  
  public void update() {
    player.update();
  }
  
  public void draw3D(GL10 gl) {
    player.draw(gl);
  }
  
  public void draw2D(GL10 gl) {
    gl.glPushMatrix();
    gl.glTranslatef(1.0f, 1.0f, 0.0f);
    gl.glScalef(0.5f, 0.5f, 0.5f);
    player.draw(gl);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
    gl.glTranslatef(-1.0f, 1.0f, 0.0f);
    gl.glScalef(0.5f, 0.5f, 0.5f);
    player.draw(gl);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
    gl.glTranslatef(-1.0f, -1.0f, 0.0f);
    gl.glScalef(0.5f, 0.5f, 0.5f);
    player.draw(gl);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
    gl.glTranslatef(1.0f, -1.0f, 0.0f);
    gl.glScalef(0.5f, 0.5f, 0.5f);
    player.draw(gl);
    gl.glPopMatrix();
  }
  
  @Override
  public void onDrawFrame(GL10 gl) {
    update();
    
    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();
    gl.glTranslatef(0.0f, 0.0f, -5.0f);
    
    draw3D(gl);
    
    // Enable 2D.
    IntBuffer viewport = IntBuffer.allocate(4);
    gl.glGetIntegerv(GL11.GL_VIEWPORT, viewport);
    gl.glMatrixMode(GL10.GL_PROJECTION);
    gl.glPushMatrix();
    gl.glLoadIdentity();
    gl.glOrthox(0, 1280, 0, 720, -1, 1);
    gl.glMatrixMode(GL10.GL_MODELVIEW);
    gl.glPushMatrix();
    gl.glLoadIdentity();
    draw2D(gl);
    
    // Disable 2D.
    gl.glMatrixMode(GL10.GL_PROJECTION);
    gl.glPopMatrix();
    gl.glMatrixMode(GL10.GL_MODELVIEW);
    gl.glPopMatrix();
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    if (height == 0) { height = 1; } // Avoid division by 0.

    gl.glViewport(0, 0, width, height);
    gl.glMatrixMode(GL10.GL_PROJECTION);
    gl.glLoadIdentity();

    GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

    gl.glMatrixMode(GL10.GL_MODELVIEW);
    gl.glLoadIdentity();
  }

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    player.load(gl, this.context, R.drawable.ship);
    
    gl.glEnable(GL10.GL_TEXTURE_2D);
    gl.glShadeModel(GL10.GL_SMOOTH);
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
  }
 
  private Context context;
  private Player player;
}
