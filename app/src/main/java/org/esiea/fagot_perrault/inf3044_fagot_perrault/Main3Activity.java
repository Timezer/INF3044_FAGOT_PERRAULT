package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main3Activity extends AppCompatActivity {
    public static final String TAG = "Biers_Udate";
    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11_BIERS_UPDATE";
    public RecyclerView rv_biere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        IntentFilter intentfilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentfilter);
        rv_biere = (RecyclerView) findViewById(R.id.rv_biere);
        rv_biere.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_biere.setAdapter(new BiersAdapter(getBiersFromFile()));
    }

    public JSONArray getBiersFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte [] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        }catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        }catch (JSONException e ) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    @Override
    public void onStart () {
        super.onStop();
    }

    @Override
    public void onStop () {
        super.onStop();
    }

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive (Context context, Intent intent) {
            Log.d(TAG, intent.getAction());
            BiersAdapter a = (BiersAdapter)rv_biere.getAdapter();
            a.setNewBier();
        }
    }

    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BiersHolder> {
        private JSONArray bier;
        public BiersAdapter(JSONArray bier) {
            this.bier = bier;
        }
        public void setNewBier() {
            this.bier = getBiersFromFile();
            notifyDataSetChanged();
        }
        @Override
        public BiersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BiersHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_biere_element, parent, false));
        }
        @Override
        public void onBindViewHolder(BiersHolder holder, int position) {
            try {
                JSONObject jo = bier.getJSONObject(position);
                holder.name.setText(jo.getString("name"));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public int getItemCount() {
            return bier.length();
        }
        public class BiersHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public BiersHolder(View itemView) {
                super(itemView);
                this.name = (TextView) itemView.findViewById(R.id.rv_biere_element_name);
            }
        }
    }



}
