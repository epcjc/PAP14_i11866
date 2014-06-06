package com.example.eapa;

//Imports
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


 
public class info extends Activity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.information);
        
		//get the Button reference
		//Button is a subclass of View
	        View v = findViewById(R.id.infoBt1);
	        View v1 = findViewById(R.id.infoBt2);
	        View v2 =findViewById(R.id.infoBt3);
		//set event listener
	        v.setOnClickListener(this);
	        v1.setOnClickListener(this);
	        v2.setOnClickListener(this);
	    }

	public void onClick(View arg0) {
		if (arg0.getId() == R.id.infoBt1){
			//define a new Intent for the second Activity
			Intent intent = new Intent(this,politica.class);
			//start the second Activity
			this.startActivity(intent);}
		
			else if (arg0.getId() == R.id.infoBt2){
			Intent intent2 = new Intent(this,termos.class);
			//start the second Activity
			this.startActivity(intent2);}
		
			else if (arg0.getId() == R.id.infoBt3){
				this.finish();
			}
			
		}
	}
	
    




 