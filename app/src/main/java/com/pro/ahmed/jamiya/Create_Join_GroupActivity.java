package com.pro.ahmed.jamiya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.pro.ahmed.jamiya.group_activities.GroupActivity;
import com.pro.ahmed.jamiya.help_classes.HelpClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Create_Join_GroupActivity extends AppCompatActivity {

    @BindView(R.id.ivCreateGroup)
    ImageView ivCreateGroup;
    @BindView(R.id.btnJoinGroup)
    Button btnJoinGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__join__group);
        ButterKnife.bind(this);
        creatGroup();
        joinGroup();
    }

    private void creatGroup() {
        ivCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(Create_Join_GroupActivity.this, GroupActivity.class);
            }
        });
    }

    private void joinGroup() {
        btnJoinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo go to join group page
            }
        });
    }

}
