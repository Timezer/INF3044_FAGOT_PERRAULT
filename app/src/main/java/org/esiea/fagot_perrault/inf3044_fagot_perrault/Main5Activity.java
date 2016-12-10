package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Main5Activity extends AppCompatActivity {

    public DatePickerDialog dpd = null;
    public TimePickerDialog tpd = null;

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
                choose2.setText(hour+":"+minute);
            }
        };
        tpd = new TimePickerDialog(this, otsl, hh, mn, true);
    }

    public void choose(View v) {
        dpd.show();
    }

    public void choose2(View v) {
        tpd.show();
    }

}
