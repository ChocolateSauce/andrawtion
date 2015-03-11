package com.example.andrawtion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class StartActivity extends Activity {

    int prog = 1;
    int tprog = (prog+1)*10;
    int black = 8;
    int tblack = black*10;
    AnimationDrawable picAnimation = new AnimationDrawable();
    ImageView imview;
    float x1,x2;
    final float MIN_DISTANCE = 150;
    
    private String[] mPlanetTitles = {"hello", "hi", "ssup"};
    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
        picAnimation = drawAnimation();
        imview = (ImageView) findViewById(R.id.imageView1);
        imview.setImageDrawable(picAnimation);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navdraw_layout);
        mDrawerList = (LinearLayout) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }
    
    private AnimationDrawable drawAnimation() {
    	
    	int tprog = (prog+1)*10;
        AnimationDrawable temp = new AnimationDrawable();
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0360), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), black*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0090), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), black*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0180), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), black*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.pic_0270), 2*tprog);
        temp.addFrame(getResources().getDrawable(R.drawable.black), black*tprog);
        
        temp.setOneShot(false);
        
        return temp;
        
    }
    
    
    private LinearLayout createSettings(LinearLayout lin) {
		
		lin.setOrientation(LinearLayout.VERTICAL);
		
		final TextView scaleText = new TextView(this);
		scaleText.setText("Animation Speed: x" + tprog);
		final SeekBar scaleSeek = new SeekBar(this);
		lin.addView(scaleText);
		lin.addView(scaleSeek);
		
		scaleSeek.setMax(10); 
		
		scaleSeek.setProgress(prog);  
		
		
		scaleSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {    
			@Override  
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
				prog = progress;
				tprog = (prog+1)*10;
				scaleText.setText("Animation Speed: x" + tprog);
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

		blackSeek.setMax(10);  
		blackSeek.setProgress(black);  
		
		
		blackSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {  
			@Override  
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
				black = progress;
				tblack = black*10;
				blackText.setText("Blackout Duration: " + tblack + "ms");
			}  
			//methods to implement but not necessary to amend  
			@Override  
			public void onStartTrackingTouch(SeekBar seekBar) {}  
			@Override  
			public void onStopTrackingTouch(SeekBar seekBar) {}  
			});
    	return lin;
    	
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
			
			final AlertDialog.Builder sup = new AlertDialog.Builder(this);  
			sup.setTitle("Select Animation Level:");  
			
			LinearLayout lin = new LinearLayout(this);
			lin.setOrientation(LinearLayout.VERTICAL);
			
			final TextView scaleText = new TextView(this);
			scaleText.setText("Animation Speed: x" + tprog);
			final SeekBar scaleSeek = new SeekBar(this);
			lin.addView(scaleText);
			lin.addView(scaleSeek);
			
			scaleSeek.setMax(10); 
			
			scaleSeek.setProgress(prog);  
			
			
			scaleSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {    
				@Override  
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
					prog = progress;
					tprog = (prog+1)*10;
					scaleText.setText("Animation Speed: x" + tprog);
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

			blackSeek.setMax(10);  
			blackSeek.setProgress(black);  
			
			
			blackSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {  
				@Override  
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
					black = progress;
					tblack = black*10;
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
					picAnimation = drawAnimation();
					imview.setImageDrawable(picAnimation);
					Toast.makeText(getApplicationContext(), "Saved",Toast.LENGTH_LONG).show();
				}
			});
			
			sup.setView(lin);
			sup.show();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
    public boolean onTouchEvent(MotionEvent event) {
//	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
//		  if(picAnimation.isRunning())
//			  picAnimation.stop();
//		  else
//			  picAnimation.start();
//	    return true;
//	  }
    	switch(event.getAction())
        {
          case MotionEvent.ACTION_DOWN:
              x1 = event.getX();  
              if(picAnimation.isRunning())
    			  picAnimation.stop();
    		  else
    			  picAnimation.start();
              
          break;         
          case MotionEvent.ACTION_UP:
              x2 = event.getX();
              float deltaX = x2 - x1;
              if (Math.abs(deltaX) > MIN_DISTANCE)
              {
                Toast.makeText(this, "left2right swipe", Toast.LENGTH_SHORT).show ();
              }
              else
              {
                  // consider as something else - a screen tap for example
              }                          
          break;   
        } 
	  return super.onTouchEvent(event);
    }


}
