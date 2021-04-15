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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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

                final ListView listView=(ListView)findViewById(R.id.appointmentList);
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


        toBook(findViewById(R.id.bookAppointment));
        toEditProfile(findViewById(R.id.editProfile));
        bye(findViewById(R.id.logout));
        toPayment(findViewById(R.id.payment));

    }


    public void toBook(View v)
    {
        Button toBook = findViewById(R.id.bookAppointment);

        toBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(homePage.this, BookingAppointment.class);
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
                Intent intent = new Intent(homePage.this, CusomterProfile.class);
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
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(homePage.this, Payment.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
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