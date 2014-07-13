package eu.itcsolutions.android.tutorial;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	EditText textMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		textMessage = (EditText) findViewById(R.id.editText1);
		
		 View v = findViewById(R.id.button1);
	        View v1 = findViewById(R.id.button2);
	        
		//set event listener
	        v.setOnClickListener(this);
	        v1.setOnClickListener(this);
	       
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
			if (arg0.getId() == R.id.button1){
				textMessage = (EditText) findViewById(R.id.editText1);
				String to = "g11019@epcjc.net";
				  //assunto e mensagem
				  String subject = "PEDIDO";
				  String message = textMessage.getText().toString();
				  Intent email = new Intent(Intent.ACTION_SEND);
				  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
				  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
				  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
				  email.putExtra(Intent.EXTRA_SUBJECT, subject);
				  email.putExtra(Intent.EXTRA_TEXT, message);

				  //need this to prompts email client only
				  email.setType("message/rfc822");
				  startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}
			
				else if (arg0.getId() == R.id.button2){
					this.finish();}
				//start the second Activity	
	}		
	}

