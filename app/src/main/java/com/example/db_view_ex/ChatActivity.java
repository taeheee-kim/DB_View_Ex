package com.example.db_view_ex;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    EditText add_comment;
    String postbox_comment,taxi_comment;
    Button btn_add_comment;
    RadioGroup rgroup;
    RadioButton rbtn_postbox,rbtn_taxi;
    DatabaseReference myRef;
    DatabaseReference myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        add_comment = findViewById(R.id.add_comment);
        btn_add_comment = findViewById(R.id.btn_add_comment);
        rgroup = findViewById(R.id.rgroup);
        rbtn_postbox = findViewById(R.id.rbtn_postbox);
        rbtn_taxi = findViewById(R.id.rbtn_taxi);

            rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    if(checkedId==R.id.rbtn_postbox){
                        btn_add_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myRef = FirebaseDatabase.getInstance().getReference("택배");
                                String key_post = myRef.child("택배 멘트").push().getKey();
                                postbox_comment = add_comment.getText().toString();
                                Map<String,Object> commentUpdates_post = new HashMap<>();
                                commentUpdates_post.put(key_post,postbox_comment);
                                myRef.updateChildren(commentUpdates_post);
                            }
                        });

                    }else if(checkedId==R.id.rbtn_taxi){

                        btn_add_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myRef2 = FirebaseDatabase.getInstance().getReference("택시");
                                String key_taxi = myRef2.child("택시 멘트").push().getKey();
                                taxi_comment = add_comment.getText().toString();
                                Map<String,Object> commentUpdates_taxi = new HashMap<>();
                                commentUpdates_taxi.put(key_taxi,taxi_comment);
                                myRef2.updateChildren(commentUpdates_taxi);
                            }
                        });

                    }
                }
            });

    }
}