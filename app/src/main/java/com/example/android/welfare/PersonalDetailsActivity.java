package com.example.android.welfare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;

public class PersonalDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        final Button buttonNext = findViewById(R.id.button_personal_details_next);
        buttonNext.setOnClickListener(onClickListener);


        //DATE PICKER
        Button btn = findViewById(R.id.activity_personal_button_dob);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");

            }
        });





        // SPINNER FOR GENDER SELECT
        Spinner spinner = findViewById(R.id.activity_personal_details_gender_select);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity_personal_spinner_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Context context = getApplicationContext();
                Object text =  parent.getItemAtPosition(pos);
                if(pos>0) {
                    Toast.makeText(context, (CharSequence) text, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                Context context = getApplicationContext();
//            Object text =  parent.getItemAtPosition(pos);
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        //SPINNER FOR DISTRICT SELECT
        Spinner spinner2 =  findViewById(R.id.activity_personal_details_district_select);

        String[] district = getResources().getStringArray(R.array.activity_personal_spinner_district);
        Arrays.sort(district);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,district);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(spinnerArrayAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos1, long id) {
                Context context = getApplicationContext();
                Object text =  parent.getItemAtPosition(pos1);
                if(pos1>0) {
                    Toast.makeText(context, (CharSequence) text, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });





    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        TextView textView = findViewById(R.id.activity_personal_textview_date);
        textView.setText(currentDateString);

    }




    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.button_personal_details_next): {
                    Intent familyDetailsIntent = new Intent(PersonalDetailsActivity.this,
                            FamilyDetailsActivity.class);
                    startActivity(familyDetailsIntent);
                }
                default: {}
            }
        }
    };

}