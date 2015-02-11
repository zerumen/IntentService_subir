package com.example.pc.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Pc on 11/02/2015.
 */
public class MiService extends IntentService {
    public static final String ACTION_PROGRESO="net.sgoliver.intent.action.PROGRESO";
    public static final String ACTION_FIN ="net.sgoliver.intent.action.FIN";
    public MiService(){
        super("MiService");
    }
    @Override
    protected void onHandleIntent(Intent intent)
    {
        int iter = intent.getIntExtra("iteraciones", 0);

        for(int i=1; i<=iter; i++) {
            tareaLarga();

            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            sendBroadcast(bcIntent);
        }

        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Toast.makeText(getApplicationContext(),"Iniciando Servicio",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent,flags,startId);
    }
}
