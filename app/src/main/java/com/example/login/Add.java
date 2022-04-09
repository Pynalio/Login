package com.example.login;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import  com.google.firebase.firestore.FirebaseFirestore;



import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {
    Button bData,getbData;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Question> quest=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bData= findViewById(R.id.bdata);
        getbData=findViewById(R.id.bgetdata);


        bData.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                // Create a new user with a first, middle, and last name

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

            }
        });


        getbData.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Question.setQuestion(new Dao() {
                    @Override
                    public void onCallback(List<Question> QuestionList) {

                        quest=QuestionList;
                        startActivity(new Intent(Add.this, Quiz1.class).putExtra("quest",(Serializable) quest));
                        Log.d("TAG5555", ""+QuestionList);

                    }
                });

            }
        });




    }


    }
