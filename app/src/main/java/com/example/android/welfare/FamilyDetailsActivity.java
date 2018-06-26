package com.example.android.welfare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FamilyDetailsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getSharedPreferences("com.welfare.app", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString("loggedInID", "").isEmpty()) {
            //TODO: Remove the negation

            Intent loginIntent = new Intent(FamilyDetailsActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            setContentView(R.layout.activity_family_details);

            final Button buttonNext = findViewById(R.id.button_family_details_next);
            final Button buttonHome = findViewById(R.id.activity_button_home);


            buttonNext.setOnClickListener(onClickListener);
            buttonHome.setOnClickListener(onClickListener);

            final Toolbar toolbar = findViewById(R.id.activity_toolbar);
            toolbar.setTitle(getString(R.string.activity_family_details_heading));
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.button_family_details_next): {
                    Intent tradingDetailsIntent = new Intent(FamilyDetailsActivity.this,
                            TradingDetailsActivity.class);
                    startActivity(tradingDetailsIntent);
                    break;
                }
                case (R.id.activity_button_home): {
                    Intent homeIntent = new Intent(FamilyDetailsActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    };
}
