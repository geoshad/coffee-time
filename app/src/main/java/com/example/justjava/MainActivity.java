package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int noOfCoffee = 0; // establish a global variable which can be used anywhere
    int priceOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // creating submitOrder button to display order

    public void submitOrder(View v) {

        // find out if user selects whipped cream topping

        CheckBox whippedCreamCheckBox = (CheckBox)
                findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // find out if user selects chocolate topping

        CheckBox chocolateCheckBox = (CheckBox)
                findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // call the method

        int totalPrice = calculateTotalPrice(hasWhippedCream, hasChocolate);

        // get the name of the user

        EditText nameOfUser = (EditText)
                findViewById(R.id.custname);
        String name = nameOfUser.getText().toString();

        // bundle the rest of the info into a String

        String message = "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add chocolate? " + hasChocolate + "\n" +
                "Quantity: " + noOfCoffee + "\n" +
                "Total: $" + totalPrice + "\n" +
                "Thank you!";

        // create a new intent to bundle off the values

        Intent intent = new Intent(this, DisplayOrderDetails.class);
        intent.putExtra("name", name);
        intent.putExtra("message", message);
        intent.putExtra("saleAmount", Integer.toString(totalPrice));
        startActivity(intent);

    }

    // create a method which calculates the total price taking the booleans as parameters

    public int calculateTotalPrice(boolean hwc, boolean hc) {
        if (hwc == true) {
            priceOfCoffee = priceOfCoffee + 1;
        }
        if (hc) { // does not say "== true", but is implicitly stated as true
            priceOfCoffee = priceOfCoffee + 2;
        }

        // calculate the total price

        int totalPrice = priceOfCoffee * noOfCoffee;
        return totalPrice;
    }

    // method to increase the number of coffee by one

    public void increment(View v) {
        noOfCoffee = noOfCoffee + 1;
        if (noOfCoffee >= 10) {
            noOfCoffee = 10;
        }
        display(noOfCoffee);
    }

    // method to decrease number of coffee by one

    public void decrement(View v) {
        noOfCoffee = noOfCoffee - 1;
        if (noOfCoffee <= 0) {
            noOfCoffee = 0;
        }
        display(noOfCoffee);

    }

    public void display(int number) {
        // display number of coffee
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view); // id from xml
        quantityTextView.setText("" + number);

    }

}
