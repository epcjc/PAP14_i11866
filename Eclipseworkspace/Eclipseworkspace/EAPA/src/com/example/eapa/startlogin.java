package com.example.eapa;

//Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class startlogin extends Activity implements OnClickListener{
	//Aqui são criados vários objectos
	EditText e1,e2;
    ProgressDialog dialog = null;
    TextView tv;
    Context c;
    private final HttpClient httpclient = new DefaultHttpClient();
    final HttpParams params = httpclient.getParams();
    HttpResponse response;
    private ProgressBar pb;
    
    // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
    
  ////////////////////////////////////////////////////////
  //Aqui os objectos são identificados através do seu "ID"
  ////////////////////////////////////////////////////////
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Layout startlogin.xml em: EAPA/res/layout
        this.setContentView(R.layout.startlogin);
        
        //Botões e imageButtons
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
        
        //EditText email e pw
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pw);
        
        // ProgressBar
        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        c=this;  
        
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
    }
    
    
    //////////////////////////////////////////
    // Dialogo em caso de não existir ligação á internet
    //////////////////////////////////////////
    public void ShowDialog(){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        mensagem.setTitle("Conexão");
        mensagem.setIcon(android.R.drawable.ic_dialog_alert);
        mensagem.setMessage("Tem de estar ligado à internet para continuar a utilizar esta aplicação.");
        mensagem.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = mensagem.create();
        alert.show();
	}
    
    
    /////////////////////////////////////////////////////////
    //Http POST, login em segundo plano
    /////////////////////////////////////////////////////////
    
        private class MyAsyncTask extends AsyncTask<String, Integer, String>{
       

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub
                String s=postData(params);
                return s;
            }

            protected void onPostExecute(String result){
                pb.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
            protected void onProgressUpdate(Integer... progress){
                pb.setProgress(progress[0]);
            }

            public String postData(String valueIWantToSend[]) {
                // Create a new HttpClient and Post Header
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("https://posttestserver.com/post.php");
                 String origresponseText="";
                try {
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("param1",valueIWantToSend[0]));
                    nameValuePairs.add(new BasicNameValuePair("param2", valueIWantToSend[1]));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
             /* execute */
                    HttpResponse response = httpclient.execute(httppost);
                      response.getEntity();
    origresponseText=readContent(response);
                } 
          catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } 
          catch (IOException e) {
                    // TODO Auto-generated catch block
                }
              String responseText = origresponseText.substring(7, origresponseText.length());
                return responseText;
            }
           

        }
        String readContent(HttpResponse response)
        {
            String text = "";
            InputStream in =null;
             
            try {
                in = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                      sb.append(line + "\n");
                    }
                    text = sb.toString();
            } catch (IllegalStateException e) {
                e.printStackTrace();
               
            } catch (IOException e) {
                  e.printStackTrace();
            }
            finally {
                try {

                  in.close();
                } catch (Exception ex) {
                }
                }

    return text;
        }
    
    ////////////////////////////////////////////////////////////////////////
    //Recebe os dados de EditText e executa o POST
    ///////////////////////////////////////////////////////////////////////
	  public void entrar(){
		  	final String s1=e1.getText().toString();
			final String s2=e2.getText().toString();
			if(this.isEmpty(s1, s2)==false){
				TextView campos = (TextView)findViewById(R.id.camposvazios);
				campos.setText("");
				pb.setVisibility(View.VISIBLE);              
				new MyAsyncTask().execute(s1,s2);
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
 	 			startActivity(intent);
			} else{
				TextView campos = (TextView)findViewById(R.id.camposvazios);
				campos.setText("Preencha os campos necessários");
				
			}
			
	  }
	  
	  //////////////////////////////////////////////////////////
	  //Verifica se os campos email e pw estão preenchidos
	  //////////////////////////////////////////////////////////
	  
	   private boolean isEmpty(String email, String pw) {
	        if (email.length() > 0 && pw.length() > 0) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	  
    ///////////////////////////////////////////////////////////
    // Logotipos e botões 
	//////////////////////////////////////////////////////////
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.loginBtEntrar){
            // get Internet status
            isInternetPresent = cd.isConnectingToInternet();
            // check for Internet status
            if (isInternetPresent) {
                // Internet Connection is Present
                // make HTTP requests
            	entrar();
            } else {
                // Internet connection is not present
            		ShowDialog();

            }
		    
		}
		else if (arg0.getId() == R.id.imageButton1){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/arvorecgi"));
			startActivity(browserIntent);
		} else if (arg0.getId() == R.id.imageButton2){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Escola-%C3%81rvore/112003068874690"));
			startActivity(browserIntent);
		}else if (arg0.getId() == R.id.imageButton3){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.arvore.pt/"));
			startActivity(browserIntent);
		}
		else if (arg0.getId() == R.id.loginBtSair){
			super.finish();}}

}