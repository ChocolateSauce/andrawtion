package com.example.andrawtion;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class StartActivity extends Activity {

    AnimationDrawable picAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
        
        ImageView pic = (ImageView) findViewById(R.id.imageView1);
        pic.setBackgroundResource(R.drawable.circle);
        picAnimation = (AnimationDrawable) pic.getBackground();
    }
    
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Log.d("onOptionsItemSelected","yes");
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			final Dialog sup = new Dialog(this);  
			sup.setTitle("Select Animation Level:");  
			sup.setContentView(R.layout.level_layout);
			
			final TextView levelTxt = (TextView)sup.findViewById(R.id.level_txt);  
			final SeekBar levelSeek = (SeekBar)sup.findViewById(R.id.level_seek); 
			
			
			levelSeek.setMax(100);  
			levelSeek.setProgress(100);  
			
			
			levelSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {  
				//change to progress  
				@Override  
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
				levelTxt.setText(Integer.toString(progress)+"%");  
				}  
				//methods to implement but not necessary to amend  
				@Override  
				public void onStartTrackingTouch(SeekBar seekBar) {}  
				@Override  
				public void onStopTrackingTouch(SeekBar seekBar) {}  
				});  
						
//			Button okBtn = (Button)sup.findViewById(R.id.level_ok); 
//			
//			OnClickListener lisn = new OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int chosenLevel = levelSeek.getProgress();  
//					sup.dismiss();  
//					
//				}
//			};
//			
//			 okBtn.setOnClickListener((android.view.View.OnClickListener) lisn);
			
			sup.show();
			
			
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
    public boolean onTouchEvent(MotionEvent event) {
	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
		  if(picAnimation.isRunning())
			  picAnimation.stop();
		  else
			  picAnimation.start();
	    return true;
	  }
	  return super.onTouchEvent(event);
    }


}
