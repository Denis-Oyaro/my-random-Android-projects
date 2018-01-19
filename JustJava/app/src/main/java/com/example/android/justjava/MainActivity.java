package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    final int MAXCUPS = 100;
    final int MINCUPS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkboxView = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkboxView.isChecked();

        checkboxView = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkboxView.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);

        EditText nameText = (EditText) findViewById(R.id.name_input);
        String name = nameText.getText().toString();

        String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate,name);

        /* using the email intent (recommended for this app) */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.order_summary_email_subject,name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
       if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage);

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < MAXCUPS) {
            quantity = quantity + 1;
        } else {
            CharSequence text = "Cannot order more than " + MAXCUPS + " cups of coffee";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity > MINCUPS) {
            quantity = quantity - 1;
        } else {
            CharSequence text = "Cannot order less than " + MINCUPS + " cups of coffee";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView)findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     * @return the total price
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    /**
     * generates a summary of order
     * @parameter total price of order
     * @return order summary
     */
    private String createOrderSummary(int total,boolean hasWhippedCream,boolean hasChocolate,String name) {
        String summary = getString(R.string.order_summary_name,name) + "\n";
        summary += getString(R.string.order_summary_whipped_cream,hasWhippedCream) + "\n";
        summary += getString(R.string.order_summary_chocolate,hasChocolate) + "\n";
        summary += getString(R.string.order_summary_quantity,quantity) + "\n";
        summary += getString(R.string.order_summary_price,"$" + total)+ "\n";
        summary += getString(R.string.thank_you);
        return summary;
    }
}
