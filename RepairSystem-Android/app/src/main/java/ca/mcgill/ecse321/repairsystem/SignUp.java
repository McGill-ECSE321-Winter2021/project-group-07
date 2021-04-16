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
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class SignUp extends AppCompatActivity {

    String error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signUp( findViewById(R.id.button_signUp));
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }


    public void signUp(View v) {
        Button toSignUp = (Button) findViewById(R.id.button_signUp);

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = "";
                final EditText email = (EditText) findViewById(R.id.new_email);
                final EditText password = (EditText) findViewById(R.id.new_password);
                final EditText name = (EditText) findViewById(R.id.new_name);
                final EditText phone = (EditText) findViewById(R.id.new_phone);
                final EditText address = (EditText) findViewById(R.id.new_address);
                final EditText credit = (EditText) findViewById(R.id.credit);
                final EditText debit = (EditText) findViewById(R.id.debit);
                TextView tv = findViewById(R.id.error);

                String request = "";
                request = request.concat(name.getText().toString());
                request =  request.concat("?password="+password.getText().toString());
                request =  request.concat("&phone="+phone.getText().toString());
                request =  request.concat("&email="+email.getText().toString());
                request =  request.concat("&credit="+credit.getText().toString());
                request = request.concat("&debit="+debit.getText().toString());
                request = request.concat("&address="+address.getText().toString());

                HttpUtils.post("customer/" + request, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Intent intent = new Intent(SignUp.this, homePage.class);
                        try {
                            intent.putExtra("customerId", response.getString("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            error += errorResponse.get("").toString();
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }
                        refreshErrorMessage();
                    }
                });
            }
        });

    }


}