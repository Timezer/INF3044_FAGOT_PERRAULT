package org.esiea.fagot_perrault.inf3044_fagot_perrault;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final EditText edittext1 = (EditText) findViewById(R.id.edittext1);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str1 = edittext1.getText().toString();
                Toast msg1 = Toast.makeText(getBaseContext(),str1,Toast.LENGTH_LONG);
                msg1.show();
            }
        });

        final EditText edittext2 = (EditText) findViewById(R.id.edittext2);
        Button btn2 = (Button) findViewById(R.id.button2);
        final TextView textview3 = (TextView) findViewById(R.id.textView3);
        final TextView textview4 = (TextView) findViewById(R.id.textView4);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str2 = edittext2.getText().toString();
                if (str2 != "") {
                    textview3.setText("Votre numéro est le :");
                    textview4.setText(str2);
                    edittext2.setText("");
                } else if (str2 == "") {
                    textview3.setText(str2);
                    textview4.setText(str2);
                    edittext2.setText("");
                }
            }
        });

    }

}
