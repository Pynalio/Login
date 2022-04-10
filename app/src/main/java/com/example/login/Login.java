package com.example.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {
    //Step 1: Declaration
    EditText etLogin, etPassword;
    Button bLogin;
    TextView tvRegister,GPS;
    FusedLocationProviderClient fusedLocationProviderClient;
    String y;


    private FirebaseAuth mAuth;
    private LocationRequest locationRequest;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPS=(TextView) findViewById(R.id.gps);
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(Login.this);


        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);



        mAuth = FirebaseAuth.getInstance();
        //Step 2: Recuperation des ids
        etLogin = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);



        //Step 3: Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signInWithEmailAndPassword(etLogin.getText().toString(),etPassword.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Question Q1=new Question(1,"A cette intersection, je laisse la priorité à droite :","non",0,"oui","non"
                                    );

                                    Question Q2=new Question(1,"Merci de choisir une réponse S.V.P !","À droite",0,"oui","non"
                                    );
                                    Question Q3=new Question(1,"Merci de choisir une réponse S.V.P ! :","non",0,"oui","non"
                                    );
                                    Question Q4=new Question(1,"A cette intersection, je laisse la priorité à droite :","oui",0,"oui","non"
                                    );
                                    Question Q5=new Question(1,"Merci de choisir une réponse S.V.P !","non",0,"oui","non"
                                    );

// Add a new document with a generated ID
                                    db.collection("Questions").document("Q1")
                                            .set(Q1);
                                    db.collection("Questions").document("Q2")
                                            .set(Q2);
                                    db.collection("Questions").document("Q3")
                                            .set(Q3);
                                    db.collection("Questions").document("Q4")
                                            .set(Q4);
                                    db.collection("Questions").document("Q5")
                                            .set(Q5);





                                    Log.i("TAG", "signInWithEmail:success");
                                    Intent i =new Intent(Login.this, MainActivity.class);
                                    y=etLogin.getText().toString();
                                    i.putExtra("mail",y);
                                    startActivity(i);




                                    }else {
                                        ActivityCompat.requestPermissions(Login.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);

                                    }









                                }



                        });






                //Step 4: Traitement
                /*if (etLogin.getText().toString().equals("toto") && etPassword.getText().toString().equals("123")){
                    startActivity(new Intent(Login.this, Quiz1.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login or password incorrect !",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }





        }








