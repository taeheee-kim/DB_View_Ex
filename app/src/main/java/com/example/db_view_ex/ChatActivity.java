package com.example.db_view_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //선언과 생성
        DatabaseReference myRef = database.getReference("message"); //message라는 곳에 접근하기

        myRef.setValue("Hello, World!"); //값을 집어넣기
    }
}