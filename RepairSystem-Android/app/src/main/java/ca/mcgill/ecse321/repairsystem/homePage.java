package ca.mcgill.ecse321.repairsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class homePage extends AppCompatActivity {


    ArrayList<String> appointments = new ArrayList<String>();
    //String appointments[] = new String[100];
    String error = "";
    Boolean bookAppointmentIsVisible = false;
    Boolean makePaymentIsVisible = false;
    Boolean editProfileIsVisible = false;
    Boolean homePageIsVisible = true;
    View bookAppointmentView;
    View makePaymentView;
    View editProfileView;
    View homePageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookAppointmentView = findViewById(R.id.bookAppointmentView);
        //makePaymentView = findViewById(R.id.makePaymentView);
        //editProfileView = findViewById(R.id.editProfileView);
        homePageView = findViewById(R.id.homePageView);

        String customerId = getIntent().getStringExtra("customerId");
        String request = "";
        request = request.concat(customerId);
        HttpUtils.get("appointments/" + request, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {


                for(int i = 0; i < response.length(); i++)
                {
                    try {

                        String toAdd ="";
                        String ID = response.getJSONObject(i).getString("id");
                        toAdd = toAdd.concat(ID+"    "+"Start: ");
                        JSONObject timeSlot = response.getJSONObject(i).getJSONObject("timeSlot");
                        toAdd = toAdd.concat(timeSlot.getString("startTime"));
                        appointments.add(toAdd);
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                }

                final ListView listView=(ListView)findViewById(R.id.appointmentListHome);
                final StableArrayAdapter  adapdter = new StableArrayAdapter(homePage.this, android.R.layout.simple_list_item_1, appointments);
                listView.setAdapter(adapdter);
                String delims = "[    ]";

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String divide[] = listView.getItemAtPosition(i).toString().split(delims);
                        String select= divide[0];
                        Toast.makeText(homePage.this,"you have selected: "+divide[1], Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(homePage.this,ViewAppointments.class);
                        intent.putExtra("select", select);
                        startActivity(intent);
                    }
                });

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

        toBook(findViewById(R.id.bookAppointmentView));
        //toEditProfile(findViewById(R.id.editProfileView));
        //bye(findViewById(R.id.logout));
        //toPayment(findViewById(R.id.makePaymentView));
        toPayment(findViewById(R.id.homePageView));

    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void addMechanic(View v) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.selectMech_name);
        HttpUtils.post("persons/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                tv.setText("");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    public void toBook(View v)
    {
        Button toBook = findViewById(R.id.bookAppointment);

        toBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bookAppointmentIsVisible){
                    bookAppointmentView.setVisibility(View.VISIBLE);
                    //editProfileView.setVisibility(View.GONE);
                    //makePaymentView.setVisibility(View.GONE);
                    homePageView.setVisibility(View.GONE);
                    bookAppointmentIsVisible = true;
                    //makePaymentIsVisible = false;
                    //editProfileIsVisible = false;
                    homePageIsVisible = false;
                }
            }
        });
    }

    public void toEditProfile(View v)
    {
        Button toEdit = findViewById(R.id.editProfile);
        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editProfileIsVisible){
                    bookAppointmentView.setVisibility(View.GONE);
                    editProfileView.setVisibility(View.VISIBLE);
                    makePaymentView.setVisibility(View.GONE);
                    homePageView.setVisibility(View.GONE);
                    bookAppointmentIsVisible = false;
                    makePaymentIsVisible = false;
                    editProfileIsVisible = true;
                    homePageIsVisible = false;
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
                startActivity(new Intent(homePage.this, MainActivity.class));
            }
        });
    }

    public void toPayment(View v){
        Button toPay = findViewById(R.id.payment);
        toPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(!makePaymentIsVisible){
                    bookAppointmentView.setVisibility(View.GONE);
                    editProfileView.setVisibility(View.GONE);
                    makePaymentView.setVisibility(View.VISIBLE);
                    homePageView.setVisibility(View.GONE);
                    bookAppointmentIsVisible = false;
                    makePaymentIsVisible = true;
                    editProfileIsVisible = false;
                    homePageIsVisible = false;
                }
            }
        });
    }

    public void toHome(View v)
    {
        Button toHome = findViewById(R.id.home);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!homePageIsVisible){
                    bookAppointmentView.setVisibility(View.GONE);
                    //editProfileView.setVisibility(View.GONE);
                    //makePaymentView.setVisibility(View.GONE);
                    homePageView.setVisibility(View.VISIBLE);
                    bookAppointmentIsVisible = false;
                    //makePaymentIsVisible = false;
                    //editProfileIsVisible = false;
                    homePageIsVisible = true;
                }
            }
        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String>{
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}