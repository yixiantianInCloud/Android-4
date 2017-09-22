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

public class NewTestActivity extends Activity implements OnClickListener {
	
	TextView xfqMesg;
    Button xfqTestBtN1;
    Button xfqTestBtN2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        xfqMesg = (TextView) findViewById(R.id.xfqMessage);
	
	    xfqTestBtN1 = (Button)findViewById(R.id.xfqTestBtN1);
	    xfqTestBtN1.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View v)  {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", "mailbox is fangqing_xi@163.com ");
                //设置返回数据
                NewTestActivity.this.setResult(33, intent);
                //关闭Activity
                NewTestActivity.this.finish();
                xfqMesg.setText("onClick button1");
                Log.d("xfq_test  new ACT","button1 return 33");
            }
       });
	    

	    xfqTestBtN2 = (Button)findViewById(R.id.xfqTestBtN2);
	    xfqTestBtN2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v)  {
	            //数据是使用Intent返回
	            Intent intent = new Intent();
	            //把返回数据存入Intent
	            intent.putExtra("result", "mailbox is fangqing_xi@163.com ");
	            //设置返回数据
	            NewTestActivity.this.setResult(44, intent);
	            //关闭Activity
	            NewTestActivity.this.finish();
	            xfqMesg.setText("onClick button2");
	            Log.d("xfq_test  new ACT","button2 return 44");
	        }
	   });
   } //end of onCreate


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		xfqMesg.setText("onClick @Override");
		Log.d("xfq_test  new ACT","onClick @Override");
	}
	
}
