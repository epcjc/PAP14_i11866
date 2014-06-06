package in.ac.srmuniv;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HttpPostActivity extends Activity {
    EditText e1,e2;
    ProgressDialog dialog = null;
    TextView tv;
    Context c;
    private static final int REGISTRATION_TIMEOUT = 3 * 1000;
    private static final int WAIT_TIMEOUT = 30 * 1000;
    private final HttpClient httpclient = new DefaultHttpClient();

    final HttpParams params = httpclient.getParams();
    HttpResponse response;
    private String content =  null;
    private boolean error = false;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState)                     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        Button b=(Button)findViewById(R.id.button1);
          e1=(EditText)findViewById(R.id.editText1);
          e2=(EditText)findViewById(R.id.editText2);
         
          pb=(ProgressBar)findViewById(R.id.progressBar1);
            pb.setVisibility(View.GONE);
          c=this;
       
    }

public void readWebpage(View view) {
    final String s1=e1.getText().toString();
    final String s2=e2.getText().toString();
    pb.setVisibility(View.VISIBLE);              
   
      
    new MyAsyncTask().execute(s1,s2);

  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.http_post, menu);
        return true;
    }
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
                  HttpEntity rp = response.getEntity();
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
   
}