package com.example.eapa;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
 
public class SendEmailActivity extends Activity {
 
	Button buttonSend;
	Button sair;
	EditText textSubject;
	EditText textMessage;
 
	
	// Recebe os dados como Assunto e mensagem
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questao);
		sair = (Button) findViewById(R.id.sairQuestao);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		textSubject = (EditText) findViewById(R.id.editTextSubject);
		textMessage = (EditText) findViewById(R.id.editTextMessage);
 
		
		//envia o email para o destinatário predifinido
		buttonSend.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				//destinatário
			  String to = "pepsi9492@hotmail.com";
			  //assunto e mensagem
			  String subject = textSubject.getText().toString();
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
		});
		
		// botão sair
		sair.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v1) {
				finish();
			  
			}
		});
		
		
	}
}