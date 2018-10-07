package com.pro.ahmed.jamiya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.pro.ahmed.jamiya.help_classes.HelpClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity {

    @BindView(R.id.relativeLayoutHome)
    RelativeLayout rlHome;
    @BindView(R.id.relativeLayoutAboutApp)
    RelativeLayout rlAbout;
    @BindView(R.id.relativeLayoutTermsAndConditions)
    RelativeLayout rlterms;
    @BindView(R.id.relativeLayoutContactUs)
    RelativeLayout rlContacUs;
    @BindView(R.id.relativeLayoutLangMore)
    RelativeLayout rlLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        home();
        about();
        contactUs();
    }

    private void home() {
        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(MoreActivity.this, Create_Join_GroupActivity.class);
            }
        });
    }

    private void about() {
        rlAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(MoreActivity.this, AboutActivity.class);
            }
        });
    }

    private void contactUs() {
        rlContacUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(MoreActivity.this, ContactUsActivity.class);
            }
        });
    }
}
