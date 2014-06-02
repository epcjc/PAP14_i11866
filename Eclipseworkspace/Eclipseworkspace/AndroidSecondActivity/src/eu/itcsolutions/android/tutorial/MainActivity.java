package eu.itcsolutions.android.tutorial;


import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		//get the Button reference
		//Button is a subclass of View
		//buttonClick is defined in main.xml "@+id/buttonClick"
	        View v = findViewById(R.id.buttonClick);
		//set event listener
	        v.setOnClickListener(this);
	    }
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.buttonClick){
			EditText email = (EditText) findViewById(R.id.editText1);
		    EditText pw = (EditText) findViewById(R.id.editText2);
	        // Create a new HttpClient and Post Header
		    String stremail = email.getText().toString();
		    String strpw = pw.getText().toString();
		    if(stremail.length()==0 || strpw.length()==0){
		    
		    	Toast.makeText(this,"Boas", Toast.LENGTH_LONG).show();//define a new Intent for the second Activity
			//Intent intent = new Intent(this,SecondActivity.class);
	 
			//start the second Activity
			//this.startActivity(intent);
		}}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



}
