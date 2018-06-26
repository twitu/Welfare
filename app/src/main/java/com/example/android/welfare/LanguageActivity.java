package com.example.android.welfare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.welfare.app", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_lang);

        Button langEnglishButton = findViewById(R.id.button_lang_english);
        langEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");

                sharedPreferences.edit().putBoolean("language", true).apply();

                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button langMalayalamButton = findViewById(R.id.button_lang_malayalam);
        langMalayalamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ml");

                sharedPreferences.edit().putBoolean("language", true).apply();

                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
