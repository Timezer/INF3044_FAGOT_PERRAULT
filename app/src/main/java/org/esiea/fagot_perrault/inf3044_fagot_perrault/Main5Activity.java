package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Main5Activity extends AppCompatActivity {

    public DatePickerDialog dpd = null;
    public TimePickerDialog tpd = null;
    public String testdate = "null";
    public String testheure = "null";
    public String msg = "Sans message";
    public static final int DIALOG_ALERT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        final Button choose = (Button) findViewById(R.id.choose);
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                choose.setText(day+"/"+(month+1)+"/"+year);
            }
        };
        dpd = new DatePickerDialog(this, odsl, yy, mm, dd);

        final Calendar c2 = Calendar.getInstance();
        int hh = c2.get(Calendar.HOUR_OF_DAY);
        int mn = c2.get(Calendar.MINUTE);
        final Button choose2 = (Button) findViewById(R.id.choose2);
        TimePickerDialog.OnTimeSetListener otsl = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timepicker, int hour, int minute) {
                choose2.setText(hour+"H"+minute);
            }
        };
        tpd = new TimePickerDialog(this, otsl, hh, mn, true);
    }

    public void choose(View v) {
        dpd.show();
        testdate = "ok";
    }

    public void choose2(View v) {
        tpd.show();
        testheure = "ok";
    }

    protected Dialog onCreateDialog(int id) {
        final EditText et = (EditText) findViewById(R.id.calendar_edittext);
        final Button date = (Button) findViewById(R.id.choose);
        final Button heure = (Button) findViewById(R.id.choose2);
        switch (id) {
            case DIALOG_ALERT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Voulez-vous ajouter l'évenement\n"
                        + "\"" + et.getText() + "\"" + " à votre calendrier\n" +
                        "pour le " + date.getText() + " à " + heure.getText() + " ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Valider", new OkOnClickListener());
                builder.setNegativeButton("Annuler", new CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }

    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Enregistrement annulé", Toast.LENGTH_SHORT).show();
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Main5Activity.this.finish();
        }
    }

    public void validate(View v) {
        if (testdate == "ok" && testheure == "ok") {
            showDialog(DIALOG_ALERT);
        }
        else if (testdate == "null") {
            Toast.makeText(getApplicationContext(), "Veuillez choisir une date", Toast.LENGTH_SHORT).show();
        }
        else if (testheure == "null") {
            Toast.makeText(getApplicationContext(), "Veuillez choisir une heure", Toast.LENGTH_SHORT).show();
        }

    }

}
