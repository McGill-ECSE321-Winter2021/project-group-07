package ca.mcgill.ecse321.repairsystem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import cz.msebera.android.httpclient.Header;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.StrictMode;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

public class CusomterProfile extends AppCompatActivity {
  //  Class attributes
    private String error = null;

    /**
     * Called on the creation of the activity. Sets the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);
        final TextView tv_name = (TextView) findViewById(R.id.cNameText);
        final TextView tv_password = (TextView) findViewById(R.id.cPasswordText);
        final TextView tv_phone = (TextView) findViewById(R.id.cPhoneText);
        final TextView tv_email = (TextView) findViewById(R.id.cEmailText);
        final TextView tv_address = (TextView) findViewById(R.id.cAddressText);
        final TextView tv_credit = (TextView) findViewById(R.id.cCreditText);
        final TextView tv_debit = (TextView) findViewById(R.id.cDebitText);

        //TODO: get current customer info
        tv_name.setText(""/*get current customer name*/);
        tv_password.setText(""/*get current customer password*/);
        tv_phone.setText(""/*get current customer phone*/);
        tv_email.setText(""/*get current customer email*/);
        tv_address.setText(""/*get current customer address*/);
        tv_credit.setText(""/*get current customer credit*/);
        tv_debit.setText(""/*get current customer debit*/);
    }

    public void returnToMain(View v) {
        finish();
    }

    public void updateProfile(View v){
        error = "";
        final TextView tv_name = (TextView) findViewById(R.id.cNameText);
        final TextView tv_password = (TextView) findViewById(R.id.cPasswordText);
        final TextView tv_phone = (TextView) findViewById(R.id.cPhoneText);
        final TextView tv_email = (TextView) findViewById(R.id.cEmailText);
        final TextView tv_address = (TextView) findViewById(R.id.cAddressText);
        final TextView tv_credit = (TextView) findViewById(R.id.cCreditText);
        final TextView tv_debit = (TextView) findViewById(R.id.cDebitText);

        //TODO: get current customer info
        /*print values for debugging*/
        System.out.println("name: " + ""/*get current customer name*/);
        System.out.println("password: " +""/*get current customer name*/);
        System.out.println("phone: "  +""/*get current customer phone*/);
        System.out.println("email: "  +""/*get current customer email*/);
        System.out.println("address: "  +""/*get current customer address*/);
        System.out.println("credit: "  +""/*get current customer credit*/);
        System.out.println("debit: "  +""/*get current customer debit*/);

    }


}
