package com.mateuyabar.android.pillownfc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mateuyabar.android.pillownfc.util.WriteTagHelper;

import java.util.Date;

/**
 * Sample activity
 */
public class PillowNfcSample extends ActionBarActivity {
	PillowNfcManager nfcManager;
	WriteTagHelper writeHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		
		nfcManager = new PillowNfcManager(this);
		nfcManager.onActivityCreate();
		
		nfcManager.setOnTagReadListener(new PillowNfcManager.TagReadListener() {
			@Override
			public void onTagRead(String tagRead) {
				Toast.makeText(PillowNfcSample.this, "tag read:"+tagRead, Toast.LENGTH_LONG).show();
			}
		});
		
		writeHelper= new WriteTagHelper(this, nfcManager);
		nfcManager.setOnTagWriteErrorListener(writeHelper);
		nfcManager.setOnTagWriteListener(writeHelper);
//		// If don't want to use the Write helper you can use the following code
//		nfcManager.setOnTagWriteListener(new TagWriteListener() {
//			@Override
//			public void onTagWritten() {
//				Toast.makeText(SampleActivity.this, "tag writen", Toast.LENGTH_LONG).show();
//			}
//		});
//		nfcManager.setOnTagWriteErrorListener(new TagWriteErrorListener() {
//			@Override
//			public void onTagWriteError(NFCWriteException exception) {
//				Toast.makeText(SampleActivity.this, exception.getType().toString(), Toast.LENGTH_LONG).show();
//			}
//		});
		
		Button writeButton = (Button) findViewById(R.id.write_button);
		writeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = new Date().toString();
				writeHelper.writeText(text);
//				// If don't want to use the Write helper you can use the following code
//				nfcManager.writeText(text);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		nfcManager.onActivityResume();
	}
	
	@Override
	protected void onPause() {
		nfcManager.onActivityPause();
		super.onPause();
	}
	
	@Override
	public void onNewIntent(Intent intent){
		nfcManager.onActivityNewIntent(intent);
	}
	
}
