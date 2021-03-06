package com.example.andrawtion;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity {

    long mSecondFingerDownTime = 0;
	
    int prog = 1;
    int tprog = prog;			//(prog+1)*10;
    
    int black = 7;
    int tblack = black;			//black*10;
    		
    AnimationDrawable picAnimation = new AnimationDrawable();
    ImageView imview;
    float x1,x2;
    final float MIN_DISTANCE = 150;
    TwoFingerDoubleTapListener multiTouchListener = new TwoFingerDoubleTapListener() {
        @Override
        public void onTwoFingerDoubleTap() {
            Toast.makeText(StartActivity.this, "Two Finger Double Tap", Toast.LENGTH_SHORT).show();
            if(picAnimation.isRunning())
  			  picAnimation.stop();
  		  else
  			  picAnimation.start();
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
//        picAnimation = drawAnimation();
		picAnimation = drawAnimationfromSD();
        imview = (ImageView) findViewById(R.id.imageView1);
        imview.setImageDrawable(picAnimation);
        picAnimation.stop();
        picAnimation.selectDrawable(0);
        


    }
    
    private AnimationDrawable drawAnimation() {
    	
        AnimationDrawable temp = new AnimationDrawable();
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0360), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), tblack*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0090), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), tblack*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0180), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), tblack*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0270), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), tblack*tprog);
        
        temp.setOneShot(false);
        
        return temp;
        
    }
    
    private AnimationDrawable drawAnimationfromSD() {
    	
    	AnimationDrawable temp = new AnimationDrawable();
    	
    	File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/animation_images");

    	if(path.exists() && path.isDirectory()) {
	    	File [] images = path.listFiles();
            Toast.makeText(StartActivity.this, "SD card images", Toast.LENGTH_SHORT).show();
	    	for (File file : images) {
	    		temp.addFrame(Drawable.createFromPath(file.getAbsolutePath()), tprog);
	    		temp.addFrame(getResources().getDrawable(R.drawable.blue), tblack);
			}
    	} else
    		temp = drawAnimation();

        
        temp.setOneShot(false);
        
        return temp;
        
    }
    
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("onOptionsItemSelected","yes");
		if(picAnimation.isRunning())
			  picAnimation.stop();
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			buildSettings();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void buildSettings() {
		if(picAnimation.isRunning())
			  picAnimation.stop();
		final AlertDialog.Builder sup = new AlertDialog.Builder(this);  
		sup.setTitle("Select Animation Level:");  
		
		LinearLayout lin = new LinearLayout(this);
		lin.setOrientation(LinearLayout.VERTICAL);
		
		final TextView scaleText = new TextView(this);
		scaleText.setText("Animation Speed: " + tprog + " ms");
		final SeekBar scaleSeek = new SeekBar(this);
		lin.addView(scaleText);
		lin.addView(scaleSeek);
		
		scaleSeek.setMax(100); 
		
		scaleSeek.setProgress(prog);  
		
		
		scaleSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {    
			@Override  
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
				prog = progress;
				tprog = prog;			//(prog+1)*10;
				scaleText.setText("Animation Speed: " + tprog + " ms");
			}  
			//methods to implement but not necessary to amend  
			@Override  
			public void onStartTrackingTouch(SeekBar seekBar) {}  
			@Override  
			public void onStopTrackingTouch(SeekBar seekBar) {}  
			});
		
		final TextView blackText = new TextView(this);
		blackText.setText("Blackout Duration: " + tblack + "ms");
		final SeekBar blackSeek = new SeekBar(this);
		lin.addView(blackText);
		lin.addView(blackSeek);

		blackSeek.setMax(100);  
		blackSeek.setProgress(tblack);  
		
		
		blackSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {  
			@Override  
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
				black = progress;
				tblack = black;			//black*10;;
				blackText.setText("Blackout Duration: " + tblack + "ms");
			}  
			//methods to implement but not necessary to amend  
			@Override  
			public void onStartTrackingTouch(SeekBar seekBar) {}  
			@Override  
			public void onStopTrackingTouch(SeekBar seekBar) {}  
			});
		 
		
		sup.setPositiveButton("OK", new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//picAnimation.invalidateSelf();					
				//picAnimation = drawAnimation();
				picAnimation = drawAnimationfromSD();
				imview.setImageDrawable(picAnimation);
		        picAnimation.stop();
		        picAnimation.selectDrawable(0);
				Toast.makeText(getApplicationContext(), "Saved",Toast.LENGTH_SHORT).show();
			}
		});
		
		sup.setView(lin);
		sup.show();
	}
	
	
	
	
    public boolean onTouchEvent(MotionEvent event) {
//	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
//		  if(picAnimation.isRunning())
//			  picAnimation.stop();
//		  else
//			  picAnimation.start();
//	    return true;
//	  } 
    	if(multiTouchListener.onTouchEvent(event))
            return true;
    	else{
    	if(event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN && event.getActionIndex()==1){
    	    mSecondFingerDownTime = System.currentTimeMillis();
    	}

    	if(event.getActionMasked() == MotionEvent.ACTION_POINTER_UP && event.getActionIndex()==1 ){
    	    if ((System.currentTimeMillis()-mSecondFingerDownTime) >= 200) {
    	        buildSettings();
    	    	Toast.makeText(getApplicationContext(), "Two Finger Long Press",Toast.LENGTH_SHORT).show();
    	    }
    	}
    	}
	  return super.onTouchEvent(event);
    }


}
