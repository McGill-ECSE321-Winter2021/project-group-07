package ca.mcgill.ecse321.repairsystem;

import android.content.Intent;
import android.os.Bundle;
import ca.mcgill.ecse321.repairsystem.HttpUtils;
import cz.msebera.android.httpclient.Header;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //refreshErrorMessage();
        goLogIn();
        goSignUp();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* private void refreshErrorMessage() {
        // set the error message
        TextView tvError = findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }*/

   public void goLogIn(){
        Button toLogIn = findViewById(R.id.startLogIn);

        //handle event list
       toLogIn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               startActivity(new Intent(MainActivity.this, LogIn.class));
           }
       });
    }

   public void goSignUp(){
       Button toSignUp = findViewById(R.id.startSignUp);
       //handle event list
       toSignUp.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               startActivity(new Intent(MainActivity.this, SignUp.class));
           }
       });
    }

  /*  public void logIn(View v) {
        error = "";
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        RequestParams requestParams = new RequestParams();
        requestParams.put("password", password.getText().toString());

        HttpUtils.get("customer/login/" + email.getText().toString(), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                //change page once logged
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get(" email and password ").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    public void signUp(View v) {
        error = "";
        final EditText email = (EditText) findViewById(R.id.new_email);
        final EditText password = (EditText) findViewById(R.id.new_password);
        final EditText name = (EditText) findViewById(R.id.new_name);
        final EditText phone = (EditText) findViewById(R.id.new_phone);
        final EditText address = (EditText) findViewById(R.id.new_address);
        final EditText credit = (EditText) findViewById(R.id.credit);
        final EditText debit = (EditText) findViewById(R.id.debit);

        RequestParams requestParams = new RequestParams();
        requestParams.put("name", name.getText().toString());
        requestParams.put("password", password.getText().toString());
        requestParams.put("phone", phone.getText().toString());
        requestParams.put("email", email.getText().toString());
        requestParams.put("credit", credit.getText().toString());
        requestParams.put("debit", debit.getText().toString());
        requestParams.put("address", address.getText().toString());

        HttpUtils.post("customer/" + name.getText().toString(), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
               setContentView(R.layout.login_page);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get(" email and password ").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }*/
}