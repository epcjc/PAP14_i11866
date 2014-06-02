package com.example.eapa;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.View.OnClickListener;


 
public class settings extends PreferenceActivity implements OnClickListener{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.settings);
        View v = findViewById(R.id.settingsBt1);
        v.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.settingsBt1){
			this.finish();
		}
	}
}