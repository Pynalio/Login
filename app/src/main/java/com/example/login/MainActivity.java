package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private TextView tvLatitude, tvLongitude;
    private Button g;
    private String x;
    double longitude;
    double latitude;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Bundle bundle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvLatitude = (TextView) findViewById(R.id.latitude);
        tvLongitude = (TextView) findViewById(R.id.longitude);
        g = (Button) findViewById(R.id.location);
            bundle = getIntent().getExtras();

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLocation();



                x=getIntent().getStringExtra("mail");



                Map<String, Object> user = new HashMap<>();

                user.put("email",x);
                user.put("longitude",longitude);
                user.put("latitude",latitude );
                user.put("location","https://www.google.com/maps/search/?api=1&query="+latitude+"%2C"+longitude+"&query_place_id=ChIJKxjxuaNqkFQR3CK6O1HNNqY" );

                db.collection("users").document("new").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("TAG", "DocumentSnapshot added with ID: ");
                    }


                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("TAG", "Error adding document", e);
                            }
                        });

                    startActivity(new Intent(MainActivity.this,Quiz1.class));
            }

            public void getLocation() {
                gpsTracker = new GpsTracker(MainActivity.this);
                if (gpsTracker.canGetLocation()) {
                    Log.i("GPS", "Error adding document");
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();

                } else {
                    gpsTracker.showSettingsAlert();
                }
            }
        });
    }
}