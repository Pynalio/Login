package com.example.login;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.login.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    double x;
    double y;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editTextLatitude = findViewById(R.id.editText);
        editTextLongitude = findViewById(R.id.editText2);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document("zk").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document =task.getResult() ;
                            Map<String, Object> data2=document.getData();


                         /*   editTextLatitude.setText(document.getData().get("latitude").toString());
                            editTextLongitude.setText(document.getData().get("latitude").toString());*/

                            y=Double.parseDouble(document.getData().get("longitude").toString());
                            x=Double.parseDouble(document.getData().get("latitude").toString());
                            LatLng sydney = new LatLng(x,y);
                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                            Log.w("jadide", "Error getting documents."+document.getData().get("longitude").toString()+""+x );
/*
                            for (Map.Entry m : data2.entrySet()) {
                                if (m.getKey()=="longitude"){
                                     x=m.getValue().hashCode();
                                }
                                if (m.getKey()=="latitude"){
                                      y=m.getValue().hashCode();
                                }
                                Toast.makeText(getApplicationContext(),x+""+y,Toast.LENGTH_SHORT).show();

                            }
*/

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });




        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(x,y);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}