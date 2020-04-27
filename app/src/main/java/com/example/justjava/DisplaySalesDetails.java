package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplaySalesDetails extends AppCompatActivity {

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sales_details);

    // an intent to receive values

        Intent intent = getIntent();
        message = intent.getStringExtra("db");
        TextView salesView = (TextView) findViewById(R.id.display_text);
        salesView.setText(message);

    }

    // new method to go to main activity

    public void reOrder(View view){
        final Intent mainActivity = new Intent (this, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
    }
}
