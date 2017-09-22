package com.hmkcode.android;

import java.io.File;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button btnTackPic;
	Button xfqTestBt1;
	Button xfqTestBt2;
	TextView tvHasCamera, tvHasCameraApp;
	ImageView ivThumbnailPhoto;
	Bitmap bitMap;
	static int TAKE_PICTURE = 9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		// Get reference to views
		tvHasCamera = (TextView) findViewById(R.id.tvHasCamera);
		tvHasCameraApp = (TextView) findViewById(R.id.tvHasCameraApp);
		btnTackPic = (Button) findViewById(R.id.btnTakePic);
		xfqTestBt1  = (Button) findViewById(R.id.xfqTestBt1);
		xfqTestBt2  = (Button) findViewById(R.id.xfqTestBt2);
		ivThumbnailPhoto = (ImageView) findViewById(R.id.ivThumbnailPhoto);

		// Does your device have a camera?
		if(hasCamera()){
			tvHasCamera.setBackgroundColor(0xFF00CC00);
			tvHasCamera.append(" -> You have Camera");
			//tvHasCamera.setText("You have Camera");
		}
		
		// Do you have Camera Apps?
		if(hasDefualtCameraApp(MediaStore.ACTION_IMAGE_CAPTURE)){
			tvHasCameraApp.setBackgroundColor(0xFF00CC00);
			tvHasCameraApp.append(" -> You have Camera Apps");
			//tvHasCameraApp.setText("You have Camera Apps");
		}
		
		// add onclick listener to the button
		btnTackPic.setOnClickListener(this);
		
		xfqTestBt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivityForResult (new Intent(MainActivity.this, NewTestActivity.class), 11);
           }
        });

		xfqTestBt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                 startActivityForResult (new Intent(MainActivity.this, NewTestActivity.class), 22);
            }
        });
	}
	
	// on button "btnTackPic" is clicked
	@Override
	public void onClick(View view) {
		
		// create intent with ACTION_IMAGE_CAPTURE action 
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		// to save picture remove comment
		/*File file = new File(Environment.getExternalStorageDirectory(),
		"my-photo.jpg");
		Uri photoPath = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPath); */
		
		// start camera activity;
		//得到新打开的   相机Activity 关闭后、返回的数据。
        //第二个参数为请求码，可以根据业务需求自己编号
		Log.d("xfq_test", " 准备 打开 相机应用 ");
	    startActivityForResult(intent, TAKE_PICTURE);
	    Log.d("xfq_test", "startActivityForResult -- Camrea opened call back?");
	}
	
	// The Android Camera application encodes the photo in the return Intent delivered to onActivityResult() 
	// as a small Bitmap in the extras, under the key "data"
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//requestCode 是用来标识请求的来源
        //resultCode  是用来标识返回的数据来自哪一个activity
		 //switch(requestCode){  
         //case 1:  
         //来自按钮1的请求，作相应业务处理  
         //case 2:  
         //来自按钮2的请求，作相应业务处理  
          //} 
		 //super.onActivityResult(requestCode, resultCode, intent);
		
		Log.d("xfq_test", "onActivityResult: Camrea requestCode = " + requestCode
				  + "  resultCode=?RESULT_OK  " + resultCode + " =? "+ RESULT_OK);


		switch (requestCode) {
		case 11:
			tvHasCamera.append(" -> start New ACT 11");
			break;
		case 22:
			tvHasCamera.append(" -> start New ACT 22");
			break;
		case 9:
			tvHasCamera.append(" -> start Camera");
			break;
		default:
			break;
		}

		switch (resultCode) {
		case 33:
			tvHasCamera.append(" -> New ACT Button1 return");
			break;
		case 44:
			tvHasCamera.append(" -> New ACT Button2 return");
			break;
		case 0:
			tvHasCamera.append(" -> Camera return");
			break;
		default:
			break;
		}

		if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
		    Log.d("xfq_test", "onActivityResult: Camrea do something....");
			// get bundle
			Bundle extras = intent.getExtras();
			
			// get 
			bitMap = (Bitmap) extras.get("data");
			ivThumbnailPhoto.setImageBitmap(bitMap);
			
			tvHasCameraApp.setText("won't here? fangqing_xi@163.com ");
		}
	}

	// method to check you have a Camera
	private boolean hasCamera(){
		return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}
	
	// method to check you have Camera Apps
	private boolean hasDefualtCameraApp(String action){
		final PackageManager packageManager = getPackageManager();
	    final Intent intent = new Intent(action);
	    List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
	    
	    return list.size() > 0;

	}
}
