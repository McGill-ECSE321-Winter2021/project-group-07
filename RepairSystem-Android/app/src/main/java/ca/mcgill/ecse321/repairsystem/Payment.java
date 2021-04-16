package ca.mcgill.ecse321.repairsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Payment extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setInfo(findViewById(R.id.cNameText));
        toBook(findViewById(R.id.bookAppointment));
        toEditProfile(findViewById(R.id.editProfile));
        bye(findViewById(R.id.logout));
        toHome(findViewById(R.id.payment));
    }

    public void setInfo(View v) {
        final TextView tv_email = (TextView) findViewById(R.id.email);
        final TextView tv_address = (TextView) findViewById(R.id.address);
        final TextView tv_credit = (TextView) findViewById(R.id.credit);
        final TextView tv_debit = (TextView) findViewById(R.id.debit);

        RequestParams requestParams = new RequestParams();
        String request = "";
        String customerId = getIntent().getStringExtra("CUSTOMER_ID");
        request = request.concat(customerId);

        HttpUtils.get("customer/" +request, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
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

    public void bye(View v)
    {
        Button toLogOut = findViewById(R.id.logout);
        toLogOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Payment.this, MainActivity.class));
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
                Intent intent = new Intent(Payment.this, homePage.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
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
                Intent intent = new Intent(Payment.this, BookingAppointment.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }

    public void toEditProfile(View v)
    {
        Button toEdit = findViewById(R.id.editProfile);
        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(Payment.this, CusomterProfile.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }
}