package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayOrderDetails extends AppCompatActivity {

    String name;
    String message;
    String strTotalPrice;

    // creating an object of the CoffeeDBHandler.

    CoffeeDBHandler dbHandler = new CoffeeDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);

        // create an intent to catch the bundled Strings - and display it!

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        strTotalPrice = intent.getStringExtra("saleAmount");

        String output = "Name: " + name + "\n" + message; // getting the final output ready

        TextView displayTextView = (TextView)  // create an object of the TextView displayed
                findViewById(R.id.display_text);
        displayTextView.setText(output.toString()); // display the final output

    }

    // method to open Gmail

    public void openEmail (View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }

    }

    // method to save data in SQLite db
    public void addButtonClicked(View view){
        Order order = new Order(name, Integer.parseInt(strTotalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "Data saved!", Toast.LENGTH_SHORT).show();

    }

    // method to generate a sales report in a new activity
    public void salesReport(View view){
        String dbString = dbHandler.databaseToString();
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }

}
