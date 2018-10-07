package com.pro.ahmed.jamiya;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.pro.ahmed.jamiya.data.DataProcessor;
import com.pro.ahmed.jamiya.group_activities.GroupActivity;
import com.pro.ahmed.jamiya.help_classes.HelpClass;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etLoginUsername)
    EditText etLoginUsername;
    @BindView(R.id.etLoginPassword)
    EditText etLoginPassword;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnSignUpLoginScreen)
    Button btnSignUpLoginScreen;
    @BindView(R.id.tvLanguage)
    TextView tvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        forgotPassword();
        toSignUpPage();
        userLogin();
        changLang();
    }

    private void forgotPassword() {
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForgotRequestToServer();
            }
        });
    }

    private void sendForgotRequestToServer() {
    }

    private void toSignUpPage() {
        btnSignUpLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(LoginActivity.this, SignUpActivity.class);
            }
        });
    }

    private void userLogin() {
        // check required Data
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etLoginUsername.getText().toString())) {
                    etLoginUsername.setError(getResources().getString(R.string.etMessageError));

                } else if (TextUtils.isEmpty(etLoginPassword.getText().toString())) {
                    etLoginPassword.setError(getResources().getString(R.string.etMessageError));
                } else {
                    String userName = etLoginUsername.getText().toString().trim();
                    String password = etLoginPassword.getText().toString().trim();
                    String token = FirebaseInstanceId.getInstance().getToken();
                    if (token != null) {
                        sendTokenToServer(token);
                    }
                    int response = sendLoginRequestToServer(userName, password, token);
                    if (response == 1) {
                        // success login
                        //DataProcessor.getInstance(LoginActivity.this).setBool("check_login", true);
                        //HelpClass.startNewActivity(LoginActivity.this, Create_Join_GroupActivity.class);
                    }
                }

                HelpClass.startNewActivity(LoginActivity.this, Create_Join_GroupActivity.class);

            }
        });
    }

    private void sendTokenToServer(String token) {
        // send token when user login to update it if he is login from another device
    }

    private int sendLoginRequestToServer(String userName, String password, String token) {
        // return response if is user is registered or not ;
        return 0;
    }

    private void changLang() {
        tvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad; // to set selected Language
                languageToLoad = Locale.getDefault().getISO3Language();
                Log.v("LanguagePrefs", languageToLoad);
                if (languageToLoad.equals("eng")) {
                    languageToLoad = "ar";
                    Log.v("LocalLanguageClick", Locale.getDefault().getISO3Language());
                } else {
                    languageToLoad = "en";
                    Log.v("LocalLanguageElseClick", Locale.getDefault().getISO3Language());
                }
                setLangToLoad(languageToLoad); // to change Localization
            }
        });
    }

    private void setLangToLoad(String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        resetView(); // to reset Views
    }

    private void resetView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

