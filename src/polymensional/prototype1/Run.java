package polymensional.prototype1;

import polymensional.prototype1.GameView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Run extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    gameView = new GameView(this);
    gameView.initialize();
    setContentView(R.layout.main);
  }


  @Override
  protected void onResume() {
    super.onResume();
    gameView.onResume();
  }


  @Override
  protected void onPause() {
    super.onPause();
    gameView.onPause();
  }
  

  private GameView gameView;
}