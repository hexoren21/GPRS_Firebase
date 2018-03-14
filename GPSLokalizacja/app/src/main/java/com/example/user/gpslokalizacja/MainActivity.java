package com.example.user.gpslokalizacja;

import android.*;
import android.location.Location;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView myTextField;
    Button myButton;
    String myStringData;
    String Time;

    Firebase myFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button) findViewById(R.id.button1);
        myTextField = (TextView) findViewById(R.id.textView);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);

        Firebase.setAndroidContext(this);
        String DeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        myFirebase = new Firebase("https://lokalizacja-gps.firebaseio.com/Users" + DeviceID);
       /* new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                myTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
                final String date = df.format(Calendar.getInstance().getTime()).toString();
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    myStringData = "Latitude: " + lat + ", Longitude: " + lon;
                    Firebase myNewChild = myFirebase.child(date);
                    myNewChild.setValue(myStringData);
                    Toast.makeText(MainActivity.this, myStringData + " is update with " + myStringData, Toast.LENGTH_SHORT).show();
                }
                else {
                        Toast.makeText(getApplicationContext(),"Brak wartosci!",Toast.LENGTH_SHORT).show();
                    }
                start();
            }

        }.start();
        */
    }

    public void WyslijDoSerwera(View view) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
        final String date = df.format(Calendar.getInstance().getTime()).toString();
        Firebase myNewChild = myFirebase.child(date);
        myStringData = "Test GPRS";
        myNewChild.setValue(myStringData);

    }
}
