package com.example.eapa;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;




 
public class startlogin extends Activity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.startlogin);
        //LayoutInflater factory = getLayoutInflater();
	    //View regisText = factory.inflate(R.layout.loginbg, null);
        View entrar = findViewById(R.id.loginBtEntrar);
        View sair = findViewById(R.id.loginBtSair);
        View escola = findViewById(R.id.imageButton3);
        View youtube = findViewById(R.id.imageButton1);
        View facebook = findViewById(R.id.imageButton2);
        escola.setOnClickListener(this);
        facebook.setOnClickListener(this);
        youtube.setOnClickListener(this);
        entrar.setOnClickListener(this);
        sair.setOnClickListener(this);
        
        
        
    }
    
    public void showToast(){
    	Toast.makeText(this,"toast", Toast.LENGTH_LONG).show();
    	}
    
    private boolean isEmpty(EditText email, EditText pw) {
        if (email.getText().toString().trim().length() > 0 && pw.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    
    /////////////////////////////////////////////////////////
    //Http post || login
    /////////////////////////////////////////////////////////

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result) {
            //pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "command sent",
                    Toast.LENGTH_LONG).show();
        }

        protected void onProgressUpdate(Integer... progress) {
            //pb.setProgress(progress[0]);
        }

        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://recursos.arvore.pt/index.php");

            try {
            	EditText email = (EditText) findViewById(R.id.email);
			    EditText pw = (EditText) findViewById(R.id.pw);
            	ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
	 		    String stremail = email.getText().toString();
	 		    String strpw = pw.getText().toString();
	 	            pairs.add(new BasicNameValuePair("email", stremail));
	 			    pairs.add(new BasicNameValuePair("senha", strpw));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }
    
    ///////////////////////////////////////////////////////
    
    public void postData() {
   	
        // Create a new HttpClient and Post Header
	    	 try {
	 	        HttpClient client = new DefaultHttpClient();  
	 	        String postURL = "http://recursos.arvore.pt/index.php";
	 	        HttpPost post = new HttpPost(postURL);
	 	       EditText email = (EditText) findViewById(R.id.email);
			    EditText pw = (EditText) findViewById(R.id.pw);
	 	        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
	 		    String stremail = email.getText().toString();
	 		    String strpw = pw.getText().toString();
	 	            pairs.add(new BasicNameValuePair("email", stremail));
	 			    pairs.add(new BasicNameValuePair("senha", strpw));
	 	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(pairs,HTTP.UTF_8);
	 	            post.setEntity(ent);
	 	            HttpResponse responsePOST = client.execute(post);
	 	            HttpEntity resEntity = responsePOST.getEntity();  
	 	            Toast.makeText(startlogin.this,resEntity.toString(), Toast.LENGTH_LONG).show();
	 	            if (resEntity != null) {    
	 	                Log.i("RESPONSE",EntityUtils.toString(resEntity));
	 	                Toast.makeText(startlogin.this,resEntity.toString(), Toast.LENGTH_LONG).show();
	 	            }
	 	    } catch (Exception e) {
	 	        e.printStackTrace();
	 	        Toast.makeText(startlogin.this,e.toString(), Toast.LENGTH_LONG).show();
	 	    }
	    	 
	 		//Intent intent = new Intent(getBaseContext(), MainActivity.class);
	 		//startActivity(intent);
	 		//Toast.makeText(startlogin.this,"Login Efetuado com Sucesso! ", Toast.LENGTH_LONG).show();
	 		//finish();
    	}
	    
	  public void entrar(){
		    EditText email = (EditText) findViewById(R.id.email);
		    EditText pw = (EditText) findViewById(R.id.pw);
			if(this.isEmpty(email, pw)==false){
				TextView campos = (TextView)findViewById(R.id.camposvazios);
				campos.setText("");
 	            Toast.makeText(startlogin.this,"Aguarde... ", Toast.LENGTH_LONG).show();
 	           new MyAsyncTask().execute("hey");
 	          
			} else{
				TextView campos = (TextView)findViewById(R.id.camposvazios);
				campos.setText("Preencha os campos necessários");
				
			}
			
	  }
    
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.loginBtEntrar){
		    entrar();
		}
		else if (arg0.getId() == R.id.imageButton1){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(browserIntent);
		} else if (arg0.getId() == R.id.imageButton2){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(browserIntent);
		}else if (arg0.getId() == R.id.imageButton3){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(browserIntent);
		}
		else if (arg0.getId() == R.id.loginBtSair){
			super.finish();}}

}