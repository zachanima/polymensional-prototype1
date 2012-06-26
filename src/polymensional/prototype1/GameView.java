package polymensional.prototype1;

import polymensional.prototype1.GameRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView {
  public GameView(Context context) {
    super(context);
    this.context = context;
  }
  
  public void initialize() {
    renderer = new GameRenderer(context);
    setRenderer(renderer);
  }
  
  private GameRenderer renderer;
  private Context context;
}
