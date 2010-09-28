/**
 * 
 */
package net.frontlinesms.android;

import net.frontlinesms.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * @author aga
 *
 */
public class SplashScreenActivity extends Activity {
	protected static final long SPLASH_TIME = 5000;
	
	private boolean alive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		new Thread() {
			public void run() {
				alive = true;
				long endTime = System.currentTimeMillis() + SPLASH_TIME;
				try {
					do {
						sleep(100);
					} while(alive && System.currentTimeMillis() < endTime);
				} catch(InterruptedException ex) {
				} finally {
					startActivity(new Intent(SplashScreenActivity.class.getPackage().getName() + ".KeywordListActivity"));
					finish();
				}
			}
		}.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		alive = false;
		return true;
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		alive = false;
		return true;
	}
}
