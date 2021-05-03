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

        myRef.setValue("Hello, JIWON"); //값을 집어넣기

        //1. RECYCLERVIEW - 반복
        //2. DB 내용을 넣는다
        //3. 상대방폰에 채팅 내용이 보임

        //1-1. RECYCLERVIEW - CHAT DATA
        // 1. MESSAGE, NICKNAME, ISMINE
    }
}