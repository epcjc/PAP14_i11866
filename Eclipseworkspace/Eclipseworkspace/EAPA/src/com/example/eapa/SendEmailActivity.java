package com.example.eapa;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class SendEmailActivity extends Activity {

	String nome;
	String numero;
	String mensagem;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questao);
        
        final EditText e_nome=(EditText) findViewById(R.id.editTextNome);
        final EditText e_numero=(EditText) findViewById(R.id.EditTextNumero);
        final EditText e_mensagem=(EditText) findViewById(R.id.editTextMessage);
        Button enviar=(Button) findViewById(R.id.buttonSend);
        //Button sair=(Button) findViewById(R.id.sairQuestao);
        
        enviar.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				
			nome = e_nome.getText().toString();
			numero = e_numero.getText().toString();
			mensagem = e_mensagem.getText().toString();
			new MyAsyncTask().execute(nome,numero,mensagem);
			Toast.makeText(getBaseContext(), "A sua questão foi enviada.",
	    			Toast.LENGTH_SHORT).show();
			((EditText) findViewById(R.id.editTextNome)).setText("");
			((EditText) findViewById(R.id.EditTextNumero)).setText("");
			((EditText) findViewById(R.id.editTextMessage)).setText("");
		}
	});
    }
 
    
    private class MyAsyncTask extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String s=postData(params);
            return s;
        }
        
        public String postData(String valueIWantToSend[]) {
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
   	nameValuePairs.add(new BasicNameValuePair("nome",nome));
   	nameValuePairs.add(new BasicNameValuePair("numero",numero));
   	nameValuePairs.add(new BasicNameValuePair("mensagem",mensagem));
    	
    	try
    	{
		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://projetos.epcjc.net/~i11866/PAP_android/insert.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	}
        catch(Exception e)
	{
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
			Toast.LENGTH_LONG).show();
	}     
        
        try
        {
            BufferedReader reader = new BufferedReader
			(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
	    {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
	    Log.e("pass 2", "connection success ");
	}
        catch(Exception e)
	{
            Log.e("Fail 2", e.toString());
	}     
        
	
    }
	return null;
    
      
}}}