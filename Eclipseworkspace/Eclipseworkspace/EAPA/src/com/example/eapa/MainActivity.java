package com.example.eapa;
//Imports
import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;





public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;
   
    public WebView WebViewCreditos;
	public WebView WebViewEquipamentos;
	public WebView WebViewTab3;
	private static final int RESULT_SETTINGS = 1;
	

	
	///////////////////////////////////////////////////////
	//A App procura por atualizações 
	///////////////////////////////////////////////////////
	public void atualizar(){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        mensagem.setTitle("Atualizar");
        mensagem.setIcon(android.R.drawable.ic_dialog_info);
        mensagem.setMessage("Não existem atualizações disponíveis neste momento.");
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
	// Opções do menu
	////////////////////////////////////////////////////////
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	
	    	case R.id.atualizar: 
	    		atualizar();
            return true;
	    	
	    	case R.id.sair:
	            System.exit(0);
	        case R.id.action_settings:
	        	Intent i = new Intent(this, UserSettingActivity.class);
				startActivityForResult(i, RESULT_SETTINGS);
	        	//this.startActivity(i);
				return true;
	        case R.id.info:
	        	Intent intent2 = new Intent(this,info.class);
				//start the info Activity
				this.startActivity(intent2);
				return true;
	        case R.id.questoes:
	        	Intent intent3 = new Intent(this,SendEmailActivity.class);
				//start the info Activity
				this.startActivity(intent3);
				return true;
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// teste definições
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			
			showUserSettings();
			break;

		}

	}
	
	private void showUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		StringBuilder builder = new StringBuilder();

		builder.append("\n Username: "
				+ sharedPrefs.getString("prefUsername", "NULL"));

		builder.append("\n Send report:"
				+ sharedPrefs.getBoolean("prefSendReport", false));

		/*builder.append("\n Sync Frequency: "
				+ sharedPrefs.getString("prefSyncFrequency", "NULL"));*/

		TextView settingsTextView = (TextView) findViewById(R.id.infoTextView1);

		settingsTextView.setText(builder.toString());
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //showUserSettings();


        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        
  
//////////////////////////////////////////////////
//Aqui iniciam os fragments
//////////////////////////////////////////////////
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
            case 0:
                 fragment = new Creditos();
                 break;
            case 1:
                 fragment = new Equipamento();
                 break;
            case 2:
                 fragment = new Calendario();
                 break;
            case 3:
                 fragment = new Tab4();
                 break;
            default:
            	break;
            }
            
            Bundle args = new Bundle();
            args.putInt(Creditos.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
			
        }
        
        /////////////////////////////////////////////////////
        //Numero de fragmentos
        /////////////////////////////////////////////////////
        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        
        ///////////////////////////////////////////////////////////
        //Aqui é dado o titulo a cada fragmento 
        //////////////////////////////////////////////////////////
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                	return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }
    }

    
    /////////////////////////////////////////////////////////////
    //Fragment créditos (WebView e 3 btns)
    /////////////////////////////////////////////////////////////
    
    public static class Creditos extends Fragment {

    	public static final String ARG_SECTION_NUMBER = "section_number";
    	
        public Creditos() {
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View mainView1 = (View) inflater.inflate(R.layout.creditos2, container, false);
            final WebView WebViewCreditos = (WebView) mainView1.findViewById(R.id.fragment_creditos);
            // programar webview 
            WebViewCreditos.setWebViewClient(new WebViewClient());
            //WebViewCreditos.getSettings().setJavaScriptEnabled(true);
            WebViewCreditos.getSettings().setLoadWithOverviewMode(true);
            WebViewCreditos.getSettings().setUseWideViewPort(true);
            WebViewCreditos.getSettings().setBuiltInZoomControls(true); 
            WebViewCreditos.loadUrl("http://recursos.arvore.pt/equipamentos.php?idtipoequipamento=25");

            
            // programar o botão "VOLTAR" bt1
            final Button bt1 = (Button) mainView1.findViewById(R.id.My_btn1);
            bt1.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewCreditos.goBack();
            	}});
            // Início - BOTÃO 3
            final Button bt3 = (Button) mainView1.findViewById(R.id.My_btn3);
            bt3.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewCreditos.loadUrl("http://recursos.arvore.pt/");
            	}});
            // AVANÇAR bt2
            final Button bt2 = (Button) mainView1.findViewById(R.id.My_btn2);
            bt2.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {	
            	WebViewCreditos.goForward();
            	}});

            return mainView1;
        }
    }
    
    ///////////////////////////////////////////////////
    //Fragment Equipamentos
    ///////////////////////////////////////////////////
    
    public static class Equipamento extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
        public Equipamento() {
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View mainView2 = (View) inflater.inflate(R.layout.equipamentos, container, false);
            final WebView WebViewEquipamentos = (WebView) mainView2.findViewById(R.id.fragment_equipamentos);
          //webview
            WebViewEquipamentos.setWebViewClient(new WebViewClient());
            //WebView1.getSettings().setJavaScriptEnabled(true); //javascript
            WebViewEquipamentos.getSettings().setLoadWithOverviewMode(true);
            WebViewEquipamentos.getSettings().setUseWideViewPort(true);
            WebViewEquipamentos.getSettings().setBuiltInZoomControls(true); 
            WebViewEquipamentos.loadUrl("http://recursos.arvore.pt/equipamentos.php?idtipoequipamento=25");
            
         // programar o botão "VOLTAR" bt4
            final Button bt4 = (Button) mainView2.findViewById(R.id.My_btn4);
            bt4.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewEquipamentos.goBack();
            	}});
            // Início - BOTÃO 6
            final Button bt6 = (Button) mainView2.findViewById(R.id.My_btn6);
            bt6.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewEquipamentos.loadUrl("http://recursos.arvore.pt/equipamentos.php?idtipoequipamento=25");
            	}});
            // AVANÇAR bt 5
            final Button bt5 = (Button) mainView2.findViewById(R.id.My_btn5);
            bt5.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {	
            	WebViewEquipamentos.goForward();
            	}});
            
            return mainView2;
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Mais fragments
    ///////////////////////////////////////////////////////////////////////////
    
    public static class Calendario extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
        public Calendario() {
        	
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View mainView3 = (View) inflater.inflate(R.layout.tab3, container, false);
            final WebView WebViewTab3 = (WebView) mainView3.findViewById(R.id.fragment_tab3);
          //webview
            WebViewTab3.setWebViewClient(new WebViewClient());
            //WebView1.getSettings().setJavaScriptEnabled(true); //javascript
            WebViewTab3.getSettings().setLoadWithOverviewMode(true);
            WebViewTab3.getSettings().setUseWideViewPort(true);
            WebViewTab3.getSettings().setBuiltInZoomControls(true); 
            WebViewTab3.loadUrl("http://www.calendarr.com/portugal/");
            
         // programar o botão "VOLTAR" bt7
            final Button bt7 = (Button) mainView3.findViewById(R.id.My_btn7);
            bt7.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewTab3.goBack();
            	}});
            
            // ATUALIZAR - BOTÃO 9
            final Button bt9 = (Button) mainView3.findViewById(R.id.My_btn9);
            bt9.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            	WebViewTab3.loadUrl("http://www.calendarr.com/portugal/");
            	}});
            
            // AVANÇAR bt8 
            final Button bt8 = (Button) mainView3.findViewById(R.id.My_btn8);
            bt8.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            		
            	WebViewTab3.goForward();

            	}});
            
            return mainView3;
        }
    }
    
    /////////////////////////////////////////////////////////
    //Fragment 4 sem uso para já
    //////////////////////////////////////////////////////////
    
    public static class Tab4 extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
        public Tab4() {
        }
        
        //textview AJUDA #db0243- cor da arvore
        //@Override
        //   Bundle savedInstanceState) {
         // View mainView4 = inflater.inflate(R.layout.ajuda, container, false);
          //TextView ajuda= (TextView) mainView4.findViewById(R.id.fragment_info);
          //ajuda.setText(Html.fromHtml(
        	//	  "<h2>Nota:</h2>" +
        	//	  "<p>Esta aplicaÃ§Ã£o necessita de uma ligaÃ§Ã£o Ã¡ internet.</p>" +
        	//	  "<h2>Sobre:</h2>" +  
        	//	  "<img src='EAPA/res/drawable-mdpi/logo.jpeg'>" +
        	//	  "<h2>Contactos:</h2>"+
        	//	  "<p>2.</p>"+
        	//	  "<h2>LocalizaÃ§Ã£o:</h2>"+
        	//	  "<p>3. </p> ")); 
         // return mainView4;
       // }
    }

}

