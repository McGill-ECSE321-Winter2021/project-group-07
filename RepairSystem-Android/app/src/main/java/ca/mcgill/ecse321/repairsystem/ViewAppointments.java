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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ViewAppointments extends AppCompatActivity {
    TextView txtAppTime, txtServiceType,txtMechanic, txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);
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


        txtAppTime=(TextView)findViewById(R.id.appTime);
        txtServiceType=(TextView)findViewById(R.id.txtServiceType);
        txtMechanic=(TextView)findViewById(R.id.txtMechanic);
        txtStatus=(TextView)findViewById(R.id.txtStatus);


        Intent intent=getIntent();
        String appointmentId = getIntent().getStringExtra("select");
        String request="";
        request= request.concat(appointmentId);
        HttpUtils.get("appointment/" + request, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                try {

                    txtAppTime.setText( txtAppTime.getText()+" "+ response.getJSONObject("timeSlot").getString("startTime"));
                    JSONArray service = response.getJSONArray("services");
                    txtServiceType.setText(txtServiceType.getText()+" "+service.getJSONObject(0).getString("serviceType"));
                    txtStatus.setText(txtStatus.getText()+" "+response.getString("status"));

                    JSONArray mechanic= response.getJSONArray("mechanics");
                    String mechId = mechanic.getJSONObject(0).getString("id");
                    HttpUtils.get("mechanic/" + mechId, new RequestParams(), new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                        {
                            try {

                                txtMechanic.setText(txtMechanic.getText()+""+response.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}