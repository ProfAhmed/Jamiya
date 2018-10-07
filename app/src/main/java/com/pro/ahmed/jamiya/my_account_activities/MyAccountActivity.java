package com.pro.ahmed.jamiya.my_account_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pro.ahmed.jamiya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountActivity extends AppCompatActivity {

    @BindView(R.id.ivMenuMyAccount)
    ImageView ivMenu;
    @BindView(R.id.ivMyAccountMyAccount)
    ImageView ivMyAccount;
    @BindView(R.id.ivNotifyMyAccount)
    ImageView ivNotify;
    @BindView(R.id.ivNotifyCycleMyAccount)
    ImageView ivNotifyCycle;
    @BindView(R.id.ivBackMyAccount)
    ImageView ivBack;
    @BindView(R.id.relativeLayoutEditProfile)
    RelativeLayout relativeLayoutEditProfile;
    @BindView(R.id.relativeLayoutPaymentSettings)
    RelativeLayout relativeLayoutPaymentSettings;
    @BindView(R.id.relativeLayoutTransactions)
    RelativeLayout relativeLayoutTransactions;
    @BindView(R.id.relativeLayoutCrateGroup)
    RelativeLayout relativeLayoutCrateGroup;
    @BindView(R.id.relativeLayoutMyGroups)
    RelativeLayout relativeLayoutMyGroups;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        paymentSettings();
    }

    private void paymentSettings() {
        relativeLayoutPaymentSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAccountActivity.this, PaymentSettingsActivity.class));
            }
        });
    }
}
