package com.pro.ahmed.jamiya;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pro.ahmed.jamiya.data.DataProcessor;
import com.pro.ahmed.jamiya.data.api.APIService;
import com.pro.ahmed.jamiya.data.api.ApiUtils;
import com.pro.ahmed.jamiya.data.models.NewUser;
import com.pro.ahmed.jamiya.help_classes.HelpClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pro.ahmed.jamiya.help_classes.HelpConstants.API_KEY;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.CHECK_LOGIN;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.LOGIN_USER_ID;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.etSignUpName)
    EditText etSignUpName;
    @BindView(R.id.etSignUpEmail)
    EditText etSignUpEmail;
    @BindView(R.id.etSignUpMobile)
    EditText etSignUpMobile;
    @BindView(R.id.etSignUpUserName)
    EditText etSignUpUserName;
    @BindView(R.id.etSignUpPassword)
    EditText etSignUpPassword;
    @BindView(R.id.cbAgree)
    CheckBox cbAgree;
    @BindView(R.id.tvTermsAndConditions)
    TextView tvTermsAndConditions;
    @BindView(R.id.btnSignUpSignUpScreen)
    Button btnSignUpSignUpScreen;
    private Snackbar snack;
    private APIService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mService = ApiUtils.getAPIService();

        signUp();
    }

    private void signUp() {

        snack = Snackbar.make(findViewById(android.R.id.content), getString(R.string.etMessageError), Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.white));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        // to clear error image after checking
        /*cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbAgree.setError(null);
                }
            }
        });*/
        btnSignUpSignUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check required Data
                if (TextUtils.isEmpty(etSignUpName.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etSignUpEmail.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etSignUpMobile.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etSignUpUserName.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etSignUpPassword.getText().toString())) {
                    snack.show();
                } else if (!cbAgree.isChecked()) {
                    snack.show();
                } else {
                    String name = etSignUpName.getText().toString().trim();
                    String email = etSignUpEmail.getText().toString().trim();
                    String mobile = etSignUpMobile.getText().toString().trim();
                    String userName = etSignUpUserName.getText().toString().trim();
                    String password = etSignUpPassword.getText().toString().trim();
                    NewUser newUser = new NewUser(0, name, "",
                            0, "", 1,
                            mobile, email, userName,
                            password, "");
                    sendnewUserToServer(newUser);
                }
            }
        });
    }

    private void sendnewUserToServer(NewUser newUser) {

        mService.addUser(newUser, API_KEY).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int id;
                if (response.isSuccess()) {
                    if (response.body() != null) {
                        String resp = response.body().toString();
                        id = Integer.parseInt(resp);
                        Log.v("SignUpUserId: ", String.valueOf(id));

                        if (id > 0) {
                            // success SignUp
                            DataProcessor.getInstance(SignUpActivity.this).setBool(CHECK_LOGIN, true);
                            DataProcessor.getInstance(SignUpActivity.this).setInt(LOGIN_USER_ID, id);
                            HelpClass.startNewActivity(SignUpActivity.this, Create_Join_GroupActivity.class);
                            finish();
                        } else if (id == -3) {
                            //Phone exist
                        } else if (id == -4) {
                            //Email exist
                        } else if (id == -5) {
                            //UserName exist
                        } else if (id == -4) {
                            //Password doesn't correct
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("SignUpError", t.toString());
            }
        });
    }
}
