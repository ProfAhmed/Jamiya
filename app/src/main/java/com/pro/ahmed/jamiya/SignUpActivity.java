package com.pro.ahmed.jamiya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.ahmed.jamiya.data.DataProcessor;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUp();
    }

    private void signUp() {

        // to clear error image after checking
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbAgree.setError(null);
                }
            }
        });
        btnSignUpSignUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check required Data
                if (TextUtils.isEmpty(etSignUpName.getText().toString())) {
                    etSignUpName.setError(getResources().getString(R.string.etMessageError));

                } else if (TextUtils.isEmpty(etSignUpEmail.getText().toString())) {
                    etSignUpEmail.setError(getResources().getString(R.string.etMessageError));

                } else if (TextUtils.isEmpty(etSignUpMobile.getText().toString())) {
                    etSignUpMobile.setError(getResources().getString(R.string.etMessageError));

                } else if (TextUtils.isEmpty(etSignUpUserName.getText().toString())) {
                    etSignUpUserName.setError(getResources().getString(R.string.etMessageError));

                } else if (TextUtils.isEmpty(etSignUpPassword.getText().toString())) {
                    etSignUpPassword.setError(getResources().getString(R.string.etMessageError));
                } else if (!cbAgree.isChecked()) {
                    cbAgree.setError(getResources().getString(R.string.etMessageError));
                } else {
                    String name = etSignUpName.getText().toString().trim();
                    String email = etSignUpEmail.getText().toString().trim();
                    String mobile = etSignUpMobile.getText().toString().trim();
                    String userName = etSignUpUserName.getText().toString().trim();
                    String password = etSignUpPassword.getText().toString().trim();
                    int response = sendUserInfoToServer(name, email, mobile, userName, password);
                    if (response == 1) {
                        Toast.makeText(SignUpActivity.this, "Success ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private int sendUserInfoToServer(String name, String email, String mobile, String userName, String password) {
        //todo send request and save user id response in
        //DataProcessor.setInt("user_id", id);
        int response = 1;
        return response;
    }
}
