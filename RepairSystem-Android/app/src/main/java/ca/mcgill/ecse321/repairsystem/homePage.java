package ca.mcgill.ecse321.repairsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class homePage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    /*variables for book appointment and add timeslot*/
    final Context context = this;
    EditText etDate;
    TextView tvTimer2;
    int t2Hour, t2Minute;
    private FloatingActionButton button;
    private Button addTimeSlot;
    private Button selectMechBtn;
    private Button bookAppBtn;

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


    ArrayList<String> appointments = new ArrayList<String>();
    //String appointments[] = new String[100];
    String customerId;
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
        makePaymentView = findViewById(R.id.makePaymentView);
        editProfileView = findViewById(R.id.editProfileView);
        homePageView = findViewById(R.id.homePageView);

        bookAppointmentView.setVisibility(View.GONE);
        editProfileView.setVisibility(View.GONE);
        makePaymentView.setVisibility(View.GONE);
        homePageView.setVisibility(View.VISIBLE);

        ////////////////// BOOK APPOINTMENT //////////////////

        //date picker
        etDate = findViewById(R.id.et_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //time picker
        tvTimer2 = findViewById(R.id.tv_timer2);

        //add car
        button = (FloatingActionButton) findViewById(R.id.addCarBtn);

        //add Timeslot
        addTimeSlot = (Button) findViewById(R.id.addTimeSlot);

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
                        context, new DatePickerDialog.OnDateSetListener() {
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
                        context,
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


        customerId = getIntent().getStringExtra("customerId");
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
                final StableArrayAdapter  adapter = new StableArrayAdapter(homePage.this, android.R.layout.simple_list_item_1, appointments);
                listView.setAdapter(adapter);
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

        setInfo(findViewById(R.id.cNameText));
        viewCrendentials(findViewById(R.id.cNameText));
        updateProfile(findViewById(R.id.cNameText));
        toBook(findViewById(R.id.bookAppointmentView));
        toEditProfile(findViewById(R.id.editProfileView));
        bye(findViewById(R.id.logout));
        toPayment(findViewById(R.id.makePaymentView));
        toHome(findViewById(R.id.homePageView));

    }

    ////////////////// BOOK APPOINTMENT //////////////////

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


    public void setInfo(View v) {
        final TextView tv_email = (TextView) findViewById(R.id.email);
        final TextView tv_address = (TextView) findViewById(R.id.address);
        final TextView tv_credit = (TextView) findViewById(R.id.credit);
        final TextView tv_debit = (TextView) findViewById(R.id.debit);

        RequestParams requestParams = new RequestParams();
        String request = "";
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

    public void makePayment(View v) {
        Context context = getApplicationContext();
        CharSequence text = "Payment received!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
                String name = tv_name.getText().toString();
                for(int i = 0; i < name.length(); i++){
                    if(Character.compare(name.charAt(i),' ') == 0){
                        if(i == name.length()-1){
                            name = name.substring(0,name.length()-1) + "%20";
                        } else {
                            name = name.substring(0,i) + "%20" + name.substring(i+1,name.length());
                        }

                    }
                }
                String address = tv_address.getText().toString();
                for(int i = 0; i < address.length(); i++){
                    if(Character.compare(address.charAt(i),' ') == 0){
                        if(i == address.length()-1){
                            address = address.substring(0,address.length()-1) + "%20";
                        } else {
                            address = address.substring(0,i) + "%20" + address.substring(i+1,address.length());
                        }
                    }
                }
                request = request.concat(tv_email.getText().toString());
                request = request.concat("?newName="+name);
                request = request.concat("&newPassword="+tv_password.getText().toString());
                request = request.concat("&newPhone="+tv_phone.getText().toString());
                request = request.concat("&newCredit="+tv_credit.getText().toString());
                request = request.concat("&newDebit="+tv_debit.getText().toString());
                request = request.concat("&newAddress="+address);

                HttpUtils.put("customer/" + request, new RequestParams(), new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                    {
                        try{
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

    public void toBook(View v)
    {
        Button toBookHome = findViewById(R.id.bookAppointmentHome);
        Button toBookBook = findViewById(R.id.bookAppointmentBook);
        Button toBookPay = findViewById(R.id.bookAppointmentPay);
        Button toBookMake = findViewById(R.id.bookAppointmentEdit);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bookAppointmentIsVisible){
                    bookAppointmentView.setVisibility(View.VISIBLE);
                    editProfileView.setVisibility(View.GONE);
                    makePaymentView.setVisibility(View.GONE);
                    homePageView.setVisibility(View.GONE);
                    bookAppointmentIsVisible = true;
                    makePaymentIsVisible = false;
                    editProfileIsVisible = false;
                    homePageIsVisible = false;
                }
            }
        };

        toBookHome.setOnClickListener(listener);
        toBookBook.setOnClickListener(listener);
        toBookMake.setOnClickListener(listener);
        toBookPay.setOnClickListener(listener);

    }

    public void toEditProfile(View v)
    {
        Button toEditHome = findViewById(R.id.editProfileHome);
        Button toEditBook = findViewById(R.id.editProfileBook);
        Button toEditMake = findViewById(R.id.editProfileEdit);
        Button toEditPay = findViewById(R.id.editProfilePay);
        View.OnClickListener listener = new View.OnClickListener() {
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
        };

        toEditHome.setOnClickListener(listener);
        toEditBook.setOnClickListener(listener);
        toEditMake.setOnClickListener(listener);
        toEditPay.setOnClickListener(listener);
    }

    public void bye(View v)
    {
        Button toLogOutHome = findViewById(R.id.logoutHome);
        Button toLogOutPay = findViewById(R.id.logoutPay);
        Button toLogOutBook = findViewById(R.id.logoutBook);
        Button toLogOutEdit = findViewById(R.id.logoutEdit);
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(homePage.this, MainActivity.class));
            }
        };

        toLogOutHome.setOnClickListener(listener);
        toLogOutPay.setOnClickListener(listener);
        toLogOutBook.setOnClickListener(listener);
        toLogOutEdit.setOnClickListener(listener);
    }

    public void toPayment(View v){
        Button toPayHome = findViewById(R.id.paymentHome);
        Button toPayPay = findViewById(R.id.paymentPay);
        Button toPayBook = findViewById(R.id.paymentBook);
        Button toPayMake = findViewById(R.id.paymentEdit);

        View.OnClickListener listener = new View.OnClickListener(){
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
        };

        toPayHome.setOnClickListener(listener);
        toPayPay.setOnClickListener(listener);
        toPayBook.setOnClickListener(listener);
        toPayMake.setOnClickListener(listener);
    }

    public void toHome(View v)
    {
        Button toHomeHome = findViewById(R.id.homeHome);
        Button toHomeBook = findViewById(R.id.homeBook);
        Button toHomePay = findViewById(R.id.homePay);
        Button toHomeEdit = findViewById(R.id.homeEdit);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!homePageIsVisible){
                    bookAppointmentView.setVisibility(View.GONE);
                    editProfileView.setVisibility(View.GONE);
                    makePaymentView.setVisibility(View.GONE);
                    homePageView.setVisibility(View.VISIBLE);
                    bookAppointmentIsVisible = false;
                    makePaymentIsVisible = false;
                    editProfileIsVisible = false;
                    homePageIsVisible = true;
                }
            }
        };

        toHomeHome.setOnClickListener(listener);
        toHomeBook.setOnClickListener(listener);
        toHomePay.setOnClickListener(listener);
        toHomeEdit.setOnClickListener(listener);
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
