package com.example.eapa;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


 
public class politica extends Activity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.privacidade);
        
        View v = findViewById(R.id.politicaBt1);

        v.setOnClickListener(this);
    }
@Override
public void onClick(View arg0) {
	if(arg0.getId() == R.id.politicaBt1){
		this.finish();
	}
}

}