package com.example.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {


    int id;
    String quest;
    String TrueAnswer;

    int Image;
    String A1,A2;

    public Question(int id, String quest, String trueAnswer, int image, String a1, String a2) {
        this.id = id;
        this.quest = quest;
        TrueAnswer = trueAnswer;
        Image = image;
        A1 = a1;
        A2 = a2;

    }


    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getTrueAnswer() {
        return TrueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        TrueAnswer = trueAnswer;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }


    public static void setQuestion(Dao Dao) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Questions")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            task.getResult().toObjects(Question.class);
                            List<Question> QuestionList = new ArrayList<>();
                            for(QueryDocumentSnapshot doc : task.getResult()) {
                                Question q = doc.toObject(Question.class);

                                QuestionList.add(q);
                            }
                            Dao.onCallback(QuestionList);


                        } else {
                            Log.i("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });








    }




}
