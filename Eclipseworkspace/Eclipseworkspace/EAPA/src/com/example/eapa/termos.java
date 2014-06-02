package com.example.eapa;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


 
public class termos extends Activity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.termos);
        

	        View v = findViewById(R.id.termosBt1);

	        v.setOnClickListener(this);
	    }
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.termosBt1){
			this.finish();
		}
	}

}
