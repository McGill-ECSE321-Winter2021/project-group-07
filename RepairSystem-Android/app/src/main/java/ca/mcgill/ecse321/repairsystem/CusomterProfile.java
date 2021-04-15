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

        viewCrendentials(findViewById(R.id.cNameText));
        updateProfile(findViewById(R.id.cNameText));
        toBook(findViewById(R.id.bookAppointment));
        toHome(findViewById(R.id.home));
        bye(findViewById(R.id.logout));
        toPayment(findViewById(R.id.payment));
    }

    public void returnToMain(View v) {
        finish();
    }

    public void viewCrendentials(View v)
    {
        TextView tv_name = (TextView) findViewById(R.id.cNameText);
        final TextView tv_password = (TextView) findViewById(R.id.cPasswordText);
        final TextView tv_phone = (TextView) findViewById(R.id.cPhoneText);
        final TextView tv_email = (TextView) findViewById(R.id.cEmailText);
        final TextView tv_address = (TextView) findViewById(R.id.cAddressText);
        final TextView tv_credit = (TextView) findViewById(R.id.cCreditText);
        final TextView tv_debit = (TextView) findViewById(R.id.cDebitText);

        RequestParams requestParams = new RequestParams();
        String request = "";
        String customerId = getIntent().getStringExtra("CUSTOMER_ID");
        request = request.concat(customerId);

        HttpUtils.get("customer/" +request, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    tv_name.setText(response.getString("name"));
                    tv_password.setText(response.getString("password"));
                    tv_phone.setText(response.getString("phone"));
                    tv_email.setText(response.getString("email"));
                    tv_address.setText(response.getString("address"));
                    tv_credit.setText(response.getString("creditHash"));
                    tv_debit.setText(response.getString("debitHash"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get(" email and password ").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }

            }
        });

    }
    public void updateProfile(View v){
        error = "";
        Button toUpdate = findViewById(R.id.profileUpdateButton);
        final TextView tv_name = (TextView) findViewById(R.id.cNameText);
        final TextView tv_password = (TextView) findViewById(R.id.cPasswordText);
        final TextView tv_phone = (TextView) findViewById(R.id.cPhoneText);
        final TextView tv_email = (TextView) findViewById(R.id.cEmailText);
        final TextView tv_address = (TextView) findViewById(R.id.cAddressText);
        final TextView tv_credit = (TextView) findViewById(R.id.cCreditText);
        final TextView tv_debit = (TextView) findViewById(R.id.cDebitText);

        toUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request = "";
                request = request.concat(tv_email.getText().toString());
                request = request.concat("?newName="+tv_name.getText().toString());
                request = request.concat("&newPassword="+tv_password.getText().toString());
                request = request.concat("&newPhone="+tv_phone.getText().toString());
                request = request.concat("&newCredit="+tv_credit.getText().toString());
                request = request.concat("&newDebit="+tv_debit.getText().toString());
                request = request.concat("&newAddress="+tv_address.getText().toString());

                HttpUtils.put("customer/" + request, new RequestParams(), new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                    {
                        try {
                            tv_name.setText(response.getString("name"));
                            tv_password.setText(response.getString("password"));
                            tv_phone.setText(response.getString("phone"));
                            tv_email.setText(response.getString("email"));
                            tv_address.setText(response.getString("address"));
                            tv_credit.setText(response.getString("creditHash"));
                            tv_debit.setText(response.getString("debitHash"));
                            finish();
                            startActivity(getIntent());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            error += errorResponse.get("").toString();
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }
                    }
                });
            }
        });

    }

    public void toBook(View v)
    {
        Button toBook = findViewById(R.id.bookAppointment);

        toBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(CusomterProfile.this, BookingAppointment.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }

    public void toHome(View v)
    {
        Button toHome = findViewById(R.id.home);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(CusomterProfile.this, homePage.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }

    public void bye(View v)
    {
        Button toLogOut = findViewById(R.id.logout);
        toLogOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(CusomterProfile.this, MainActivity.class));
            }
        });
    }

    public void toPayment(View v){
        Button toPay = findViewById(R.id.payment);
        toPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(CusomterProfile.this, Payment.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }


}
