package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class GetBiersServices extends IntentService {
    private static final String ACTION_GET_ALL_BIERS = "org.esiea.fagot_perrault.inf3044_fagot_perrault.action.FOO";
    public static final String TAG="GetBiersServices";
    public GetBiersServices() {
        super("GetBiersServices");
    }
    public static void startActionGetBiers(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        intent.setAction(ACTION_GET_ALL_BIERS);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL_BIERS.equals(action)) {
                handleActionGetBiers();
            }
        }
    }
    private void handleActionGetBiers() {
        Log.i(TAG,"handleActionGetBiers");
        URL url=null;
        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "bieres.json"));
                Log.d(TAG, "Bieres json downloaded !");
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.BIERS_UPDATE));
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("Votre sélection de bières a été téléchargée")
                        .setContentTitle("INF3044_FAGOT_PERRAULT");
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(0,builder.build());
            }
            else{
                Log.w(TAG,"erreur"+conn.getResponseCode());
                NotificationCompat.Builder builder2 = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("Veuillez vous connecter à internet pour télécharger le fichier JSON")
                        .setContentTitle("INF3044_FAGOT_PERRAULT");
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(0,builder2.build());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void copyInputStreamToFile(InputStream in,File file){
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

