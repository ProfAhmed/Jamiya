package com.pro.ahmed.jamiya.group_activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pro.ahmed.jamiya.R;
import com.pro.ahmed.jamiya.group_activities.fragments.CreateGroupFragment;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Fragment fragment = new CreateGroupFragment(); // start CategoriesFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerGroupActivity, fragment);
        fragmentTransaction.commit();
    }
}
