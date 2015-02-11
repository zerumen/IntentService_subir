package com.example.pc.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button start;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.button);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MiService.class);
                i.putExtra("iteraciones",10);
                startService(i);
            }
        });
        IntentFilter filter=new IntentFilter();
        filter.addAction(MiService.ACTION_PROGRESO);
        filter.addAction(MiService.ACTION_FIN);
        ProgressReceiver rcv=new ProgressReceiver();
        registerReceiver(rcv,filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class ProgressReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            if(intent.getAction().equals(MiService.ACTION_PROGRESO)){
                int prog=intent.getIntExtra("progreso",0);
                progress.setProgress(prog);
            }
            else if(intent.getAction().equals(MiService.ACTION_FIN)){
                Toast.makeText(MainActivity.this,"Archivo Subido!",Toast.LENGTH_LONG).show();
            }
        }
    }

}
