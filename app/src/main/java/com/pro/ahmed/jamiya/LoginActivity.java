package com.pro.ahmed.jamiya;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
import com.pro.ahmed.jamiya.data.api.APIService;
import com.pro.ahmed.jamiya.data.api.ApiUtils;
import com.pro.ahmed.jamiya.group_activities.GroupActivity;
import com.pro.ahmed.jamiya.help_classes.HelpClass;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pro.ahmed.jamiya.help_classes.HelpConstants.API_KEY;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.CHECK_LOGIN;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.LOGIN_USER_ID;

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

    private APIService mService;
    private Snackbar snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mService = ApiUtils.getAPIService();

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
        snack = Snackbar.make(findViewById(android.R.id.content), getString(R.string.etMessageError), Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.white));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(etLoginUsername.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etLoginPassword.getText().toString())) {
                    snack.show();
                } else {
                    String userName = etLoginUsername.getText().toString().trim();
                    String password = etLoginPassword.getText().toString().trim();
                    String token = FirebaseInstanceId.getInstance().getToken();
                    if (token == null) {
                        token = "";
                    }
                    sendLoginRequestToServer(userName, password, token);
                }
            }
        });
    }

    private void sendLoginRequestToServer(String userName, String password, String token) {
        // return response if is user is registered or not ;

        mService.getLoginUserId(userName, password, token, API_KEY).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                int id;
                if (response.isSuccess()) {
                    if (response.body() != null) {
                        String resp = response.body().toString();
                        String sub_resp = resp.substring(resp.indexOf("\"") + 1, resp.length() - 1);
                        id = Integer.parseInt(sub_resp);
                        Log.v("Sub_resp:", sub_resp);
                        Log.v("LoginUserId: ", String.valueOf(id));

                        if (id > 0) {
                            // success login
                            DataProcessor.getInstance(LoginActivity.this).setBool(CHECK_LOGIN, true);
                            DataProcessor.getInstance(LoginActivity.this).setInt(LOGIN_USER_ID, id);
                            HelpClass.startNewActivity(LoginActivity.this, Create_Join_GroupActivity.class);
                            finish();
                        } else if (id == -3) {
                            //UserName doesn't exist
                        } else if (id == -4) {
                            //Password doesn't correct
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("LoginUserIdError: ", t.toString());
            }
        });
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

