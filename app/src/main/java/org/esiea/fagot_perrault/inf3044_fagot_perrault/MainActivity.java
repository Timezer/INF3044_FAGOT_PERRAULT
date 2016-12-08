package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "N'oubliez pas de visiter notre github !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        //Date et heure
        TextView tvDisplayDate = (TextView) findViewById(R.id.textMain_Date);
        TextView tvDisplayHour = (TextView) findViewById(R.id.textMain_Heure);
        TextView tvDisplaySecond = (TextView) findViewById(R.id.textMain_Second);

        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mn = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        tvDisplayDate.setText(new StringBuilder()
                .append(dd).append("-").append(mm + 1).append("-").append(yy));

        tvDisplayHour.setText(new StringBuilder()
                .append(hh).append("h").append(mn));

        tvDisplaySecond.setText(new StringBuilder()
                .append(ss).append(" s"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    int conteur_image = 0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_1) {
            ImageView image = (ImageView) findViewById(R.id.imageMain);
            if (conteur_image%2 == 0) {
                image.setImageResource(R.drawable.esiea_main);
                conteur_image = conteur_image + 1;
            }
            else if (conteur_image%2 == 1) {
                image.setImageResource(R.drawable.meme_study);
                conteur_image = conteur_image + 1;
            }
        }
        if (id == R.id.action_settings_2) {
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_a_propos) {
            Intent appel = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(appel);
        } else if (id == R.id.nav_json) {
            Intent appel2 = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(appel2);
        } else if (id == R.id.nav_toast) {

        } else if (id == R.id.nav_youtube) {
            goToUrl("https://www.youtube.com/watch?v=54h2RHNtDIU");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void refresh(View v) {
        TextView tvDisplayDate = (TextView) findViewById(R.id.textMain_Date);
        TextView tvDisplayHour = (TextView) findViewById(R.id.textMain_Heure);
        TextView tvDisplaySecond = (TextView) findViewById(R.id.textMain_Second);

        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mn = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        tvDisplayDate.setText(new StringBuilder()
                .append(dd).append("-").append(mm + 1).append("-").append(yy));

        tvDisplayHour.setText(new StringBuilder()
                .append(hh).append("h").append(mn));

        tvDisplaySecond.setText(new StringBuilder()
                .append(ss).append(" s"));

        Toast.makeText(getApplicationContext(),getString(R.string.msg_refresh), Toast.LENGTH_LONG).show();
    }

    public void bg_default(View v) {
        RelativeLayout mlayout1 = (RelativeLayout) findViewById(R.id.content_main);
        mlayout1.setBackgroundColor(Color.WHITE);
    }

    int conteur=0;
    public void bg_change(View v) {
        RelativeLayout mlayout2 = (RelativeLayout) findViewById(R.id.content_main);
        if (conteur%5 == 0) {
            mlayout2.setBackgroundColor(Color.BLUE);
            conteur = conteur + 1;
            return;
        }
        else if (conteur%5 == 1) {
            mlayout2.setBackgroundColor(Color.GRAY);
            conteur = conteur + 1;
            return;
        }
        else if (conteur%5 == 2) {
            mlayout2.setBackgroundColor(Color.GREEN);
            conteur = conteur + 1;
            return;
        }
        else if (conteur%5 == 3) {
            mlayout2.setBackgroundColor(Color.MAGENTA);
            conteur = conteur + 1;
            return;
        }
        else if (conteur%5 == 4) {
            mlayout2.setBackgroundColor(Color.YELLOW);
            conteur = conteur + 1;
            return;
        }
    }

}
