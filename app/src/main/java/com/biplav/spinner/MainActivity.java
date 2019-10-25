package com.biplav.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spinner;
    private Spinner spinPlayer;
    private AutoCompleteTextView autoCompleteTextView;
    private TextView tv;
    private TextView tvtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables fullscreen
        setContentView(R.layout.activity_main);

        

        spinner=findViewById(R.id.spinnerCountry);
        spinPlayer=findViewById(R.id.spinnerPlayers);
        autoCompleteTextView=findViewById(R.id.actv);
        tv=findViewById(R.id.txtdate);
        tvtime=findViewById(R.id.tvtime);

        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTime();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });


        String countries[]={"Nepal","India"};
        final String indianplayers[]={"Virat","Dhoni"};
        final String nepaliplayers[]={"Paras","Sundeep","Gyanendra", "Sammer"};
        ArrayAdapter adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );
        spinner.setAdapter(adapter);
//

        ArrayAdapter<String> nepPlayerAdapter=new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                nepaliplayers
        );

        autoCompleteTextView.setAdapter(nepPlayerAdapter);
        autoCompleteTextView.setThreshold(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().toString().equals("India"))
                {
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            indianplayers
                    );
                    spinPlayer.setAdapter(arrayAdapter);
                }
                else{
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            nepaliplayers
                    );
                    spinPlayer.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

        private void loadDatePicker()
        {
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog=new DatePickerDialog(
                    this, this, year, month, day);
            datePickerDialog.show();

        }


        private void loadTime()
        {
            final Calendar calender=Calendar.getInstance();
            int hour= calender.get(Calendar.HOUR);
            int minute=calender.get(Calendar.MINUTE);
            int second=calender.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String amPm;
                    if(hourOfDay>=12)
                    {
                        hourOfDay-=12;
                        amPm= "Pm";
                    }
                    else
                    {
                        amPm="Am";
                    }
                    tvtime.setText(("Time is: " + hourOfDay + ":" + minute + " " + amPm).toString());
                }
            }, hour, minute, false);
            timePickerDialog
                    .show();
        }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date= "Month/Day/Year :" + month+ "/" + dayOfMonth + "/" + year;
        tv.setText(date);
    }
}
