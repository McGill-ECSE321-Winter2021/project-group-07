package ca.mcgill.ecse321.repairsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import ca.mcgill.ecse321.repairsystem.HttpUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class BookingAppointment extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private String error = null;
    EditText etDate;
    TextView tvTimer2;
    int t2Hour, t2Minute;


    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }
    public void servicePopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);

        popup.inflate(R.menu.service_popup_menu);
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.item1){
            Toast.makeText(this, "item1 selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.item2){
            Toast.makeText(this, "item2 selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.item3){
            Toast.makeText(this, "item3 selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.item4){
            Toast.makeText(this, "item4 selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.CarRepair){
            Toast.makeText(this, "CarRepair selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.OilChange){
            Toast.makeText(this, "OilChange selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.RegularCheckup){
            Toast.makeText(this, "RegularCheckup selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.CarWash){
            Toast.makeText(this, "CarWash selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.TireChange){
            Toast.makeText(this, "TireChange selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.RoadsideAssistance){
            Toast.makeText(this, "RoadsideAssistance selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.Towing){
            Toast.makeText(this, "Towing selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.CarInspection){
            Toast.makeText(this, "CarInspection selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.Other){
            Toast.makeText(this, "Other selected", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etDate = findViewById(R.id.et_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvTimer2 = findViewById(R.id.tv_timer2);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toHome(findViewById(R.id.bookAppointment));
        toEditProfile(findViewById(R.id.editProfile));
        bye(findViewById(R.id.logout));
        toPayment(findViewById(R.id.payment));

        //refreshErrorMessage();
        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month= month+1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        tvTimer2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BookingAppointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;
                                String time = t2Hour + ":" + t2Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try{
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    tvTimer2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        },12,0,true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour, t2Minute);
                timePickerDialog.show();
            }
        });
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
/*
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
    }*/

    public void toHome(View v)
    {
        Button toHome = findViewById(R.id.home);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = getIntent().getStringExtra("customerId");
                Intent intent = new Intent(BookingAppointment.this, homePage.class);
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
                startActivity(new Intent(BookingAppointment.this, MainActivity.class));
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
                Intent intent = new Intent(BookingAppointment.this, Payment.class);
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
                Intent intent = new Intent(BookingAppointment.this, CusomterProfile.class);
                intent.putExtra("CUSTOMER_ID", customerId);
                startActivity(intent);
            }
        });
    }

}