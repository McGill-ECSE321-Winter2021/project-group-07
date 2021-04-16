package ca.mcgill.ecse321.repairsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private Button button;
    private Button addTimeSlot;
    private Button selectMechBtn;
    private Button bookAppBtn;
    private EditText editTextMainScreen;
    private EditText editTextMainScreen2;

    /*variables for book appointment and add timeslot*/
    String startT= "";
    String endT= "";
    String startYmd= "";
    String startTime= "";
    String endTime= "";
    String service="";
    String timeSlotId="";
    String appId="";
    //TODO:CARID MECHID
    String carId="181807788";
    String mechId="3347453";

    final Context context = this;



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
            service="CarRepair";
        }else if(item.getItemId()==R.id.OilChange){
            Toast.makeText(this, "OilChange selected", Toast.LENGTH_SHORT).show();
            service="OilChange";
        }else if(item.getItemId()==R.id.RegularCheckup){
            Toast.makeText(this, "RegularCheckup selected", Toast.LENGTH_SHORT).show();
            service="RegularCheckup";
        }else if(item.getItemId()==R.id.CarWash){
            Toast.makeText(this, "CarWash selected", Toast.LENGTH_SHORT).show();
            service="CarWash";
        }else if(item.getItemId()==R.id.TireChange){
            Toast.makeText(this, "TireChange selected", Toast.LENGTH_SHORT).show();
            service="TireChange";
        }else if(item.getItemId()==R.id.RoadsideAssistance){
            Toast.makeText(this, "RoadsideAssistance selected", Toast.LENGTH_SHORT).show();
            service="RoadsideAssistance";
        }else if(item.getItemId()==R.id.Towing){
            Toast.makeText(this, "Towing selected", Toast.LENGTH_SHORT).show();
            service="Towing";
        }else if(item.getItemId()==R.id.CarInspection){
            Toast.makeText(this, "CarInspection selected", Toast.LENGTH_SHORT).show();
            service="CarInspection";
        }else if(item.getItemId()==R.id.Other){
            Toast.makeText(this, "Other selected", Toast.LENGTH_SHORT).show();
            service="Other";
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //date picker
        etDate = findViewById(R.id.et_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //time picker
        tvTimer2 = findViewById(R.id.tv_timer2);

        //add car
        button = (Button) findViewById(R.id.addCarBtn);

        //add Timeslot
        addTimeSlot = (Button) findViewById(R.id.addTimeSlot);
        editTextMainScreen2 = (EditText) findViewById(R.id.editTextResult2);


        //bottom menu bar
        toHome(findViewById(R.id.bookAppointment));
        toEditProfile(findViewById(R.id.editProfile));
        bye(findViewById(R.id.logout));
        toPayment(findViewById(R.id.payment));

        //select Mechanic
        selectMechBtn = (Button) findViewById(R.id.selectMechBtn);

        //book Appointment
        bookAppBtn  = (Button) findViewById(R.id.bookAppBtn);


        selectMechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = "";

                String request = "";
                request = request.concat(startTime);
                request =  request.concat("?endTime="+endTime);

                Context context = getApplicationContext();
                CharSequence text = "time slot Added!";
                int duration = Toast.LENGTH_SHORT;

                HttpUtils.post("/timeslot/" + request, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            timeSlotId = response.getString("id");
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        });

        //add car methods
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptView);

                final EditText carInput = (EditText) promptView.findViewById(R.id.carTypeInput);
                final EditText WinterTiresInput = (EditText) promptView.findViewById(R.id.WinterTiresInput);
                final EditText NumberofKilometersInput = (EditText) promptView.findViewById(R.id.NumberofKilometersInput);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Add Car", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                RequestParams requestParams = new RequestParams();
                                String request = "";
                                String customerId = getIntent().getStringExtra("CUSTOMER_ID");
                                request = request.concat(customerId);
                                request = request.concat("?carType="+carInput.getText().toString());
                                request = request.concat("&winterTires="+WinterTiresInput.getText().toString());
                                request = request.concat("&numOfKilometers="+NumberofKilometersInput.getText().toString());

                                Context context = getApplicationContext();
                                CharSequence text = "Car Added!";
                                int duration = Toast.LENGTH_SHORT;


                                HttpUtils.post("/car/" +request, requestParams, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                                       Toast toast = Toast.makeText(context, text, duration);
                                       toast.show();
                                        /*get car id of the added car
                                        try {
                                            Toast toast = Toast.makeText(context, response.getString("id"), duration);
                                            toast.show();
                                        }catch (JSONException e) {
                                            e.printStackTrace();
                                        }*/
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
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });
       




        //date set methods
        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month= month+1;
                        String date = day+"/"+month+"/"+year;
                        if (month<10 & day<10){
                            startYmd = year+"-0"+month+"-0"+day;
                        }
                        else if (month>=10 & day<10){
                            startYmd = year+"-"+month+"-0"+day;}
                        else if (month<10 & day>=10){
                            startYmd = year+"-0"+month+"-"+day;}
                        else {
                            startYmd = year+"-"+month+"-"+day;
                        }
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        //time set methods
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
                                if(t2Hour<10 & t2Minute<10){
                                    startT = "-0"+t2Hour + ":0" + t2Minute;
                                }else if(t2Hour<10 & t2Minute>=10){
                                    startT = "-0"+t2Hour + ":" + t2Minute;
                                }else if(t2Hour>=10 & t2Minute<10){
                                    startT = "-"+t2Hour + ":0" + t2Minute;
                                }else{
                                    startT = "-"+t2Hour + ":" + t2Minute;
                                }

                                if((hourOfDay+1)+1<10 & t2Minute<10){
                                    endT = "-0"+(hourOfDay+1) + ":0" + t2Minute;
                                }else if((hourOfDay+1)+1<10 & t2Minute>=10){
                                    endT = "-0"+(hourOfDay+1) + ":" + t2Minute;
                                }else if((hourOfDay+1)+1>=10 & t2Minute<10){
                                    endT = "-"+(hourOfDay+1) + ":0" + t2Minute;
                                }else{
                                    endT = "-"+(hourOfDay+1) + ":" + t2Minute;
                                }
                                endTime = startYmd+endT;
                                startTime = startYmd+startT;

                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try{
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    tvTimer2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },24,0,true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour, t2Minute);
                timePickerDialog.show();
            }
        });

        //add time slot methods
        addTimeSlot = (Button) findViewById(R.id.addTimeSlot);

        addTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = "";

                String request = "";
                request = request.concat(startTime);
                request =  request.concat("?endTime="+endTime);

                Context context = getApplicationContext();
                CharSequence text = "time slot Added!";
                int duration = Toast.LENGTH_SHORT;

                HttpUtils.post("/timeslot/" + request, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            timeSlotId = response.getString("id");
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        });


        //book appointment method
        bookAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //https://repairsystem-backend-g07.herokuapp.com/appointment/108960?timeSlotId=511806436&carId=181807788&services=CarRepair
            public void onClick(View v) {
                error = "";

                RequestParams requestParams = new RequestParams();
                String request = "";
                String customerId = getIntent().getStringExtra("CUSTOMER_ID");
                request = request.concat(customerId);
                request = request.concat("?timeSlotId="+timeSlotId);
                request = request.concat("&carId="+carId);
                request = request.concat("&services="+service);

                Context context = getApplicationContext();
                CharSequence text = "Appointment Booked!";
                int duration = Toast.LENGTH_SHORT;


                HttpUtils.post("/appointment/" + request, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            appId = response.getString("id");

                            //add mechanic to the appointment
                            RequestParams requestParams = new RequestParams();
                            String request2 = "";
                            request2 = request2.concat(mechId);
                            request2 = request2.concat("?appointmentId="+appId);
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            CharSequence finalText = request2.toString();;
                            HttpUtils.put("/appointment/addMechanic/" + request2, new RequestParams(), new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    refreshErrorMessage();
                                    Toast toast = Toast.makeText(context, finalText, duration);
                                    toast.show();
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

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
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



    /*Button Menu*/
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

    public void toEditProfile(View v){
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