package com.jikexueyuan.game2048;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	GameView view;
	
	public MainActivity() {
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//to call connection service
		startService(new Intent(this,ConnectionService.class));
		
		
		
		tvScore = (TextView) findViewById(R.id.tvScore);
		
		
		registerReceiver(receiver, new IntentFilter("myproject"));
		
	}
	
	
	


	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle!=null) {
				
				//extra data inserted into the fired intent
				String data = bundle.getString("data");
				Log.i("data in main class", data);
				
				
				if ("stomp".equalsIgnoreCase(data)) {
					view.swipeLeft();	
				}
				
				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
			}else{
				Log.i("data in main class", "bundle null");
				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
			}
			//handleResult(bundle);
		}

		
	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clearScore(){
		score = 0;
		showScore();
	}
	
	public void showScore(){
		tvScore.setText(score+"");
	}
	
	public void addScore(int s){
		score+=s;
		showScore();
	}

	private int score = 0;
	private TextView tvScore;
	
	private static MainActivity mainActivity = null;
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}

}
