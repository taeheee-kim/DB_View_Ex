package com.example.db_view_ex;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
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

        ExpandableListView ListView = (ExpandableListView)findViewById(R.id.ListView);
        final ArrayList<position> positions = getData();

        myAdapter adapter = new myAdapter(this,positions);
        ListView.setAdapter(adapter);

        ListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),positions.get(groupPosition).comments.get(childPosition),Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
    private ArrayList<position> getData(){
        position p1 = new position("택배");
        p1.comments.add("누구세요?");
        p1.comments.add("문 앞에 놓아주세요");
        p1.comments.add("문 앞에 있습니다");

        position p2 = new position("택시");
        p2.comments.add("집 앞으로 나갈까?");
        p2.comments.add("어디쯤이니?");
        p2.comments.add("몇 분 남았어? 내리기 5분 전에 연락해");

        ArrayList<position> allcomment = new ArrayList<>();
        allcomment.add(p1);
        allcomment.add(p2);

        return allcomment;
    }

}