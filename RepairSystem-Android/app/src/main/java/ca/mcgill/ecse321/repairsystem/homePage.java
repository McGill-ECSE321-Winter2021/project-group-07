package ca.mcgill.ecse321.repairsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class homePage extends AppCompatActivity {

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

        toBook(findViewById(R.id.bookAppointment));
        toEditProfile(findViewById(R.id.editProfile));
        bye(findViewById(R.id.logout));
    }

    public void toBook(View v)
    {
        Button toBook = findViewById(R.id.bookAppointment);

        toBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, BookingAppointment.class));
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
}