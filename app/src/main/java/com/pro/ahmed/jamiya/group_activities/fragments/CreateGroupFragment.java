package com.pro.ahmed.jamiya.group_activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pro.ahmed.jamiya.Create_Join_GroupActivity;
import com.pro.ahmed.jamiya.MoreActivity;
import com.pro.ahmed.jamiya.R;
import com.pro.ahmed.jamiya.SignUpActivity;
import com.pro.ahmed.jamiya.data.DataProcessor;
import com.pro.ahmed.jamiya.data.api.APIService;
import com.pro.ahmed.jamiya.data.api.ApiUtils;
import com.pro.ahmed.jamiya.data.models.NewGroup;
import com.pro.ahmed.jamiya.help_classes.HelpClass;
import com.pro.ahmed.jamiya.help_classes.HelpConstants;
import com.pro.ahmed.jamiya.my_account_activities.MyAccountActivity;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pro.ahmed.jamiya.help_classes.HelpConstants.API_KEY;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.CHECK_LOGIN;
import static com.pro.ahmed.jamiya.help_classes.HelpConstants.LOGIN_USER_ID;

public class CreateGroupFragment extends Fragment {
    @BindView(R.id.ivMenuCreateGroup)
    ImageView ivMenu;
    @BindView(R.id.ivMyAccountCreateGroup)
    ImageView ivMyAccount;
    @BindView(R.id.ivNotifyCreateGroup)
    ImageView ivNotify;
    @BindView(R.id.ivNotifyCycleCreateGroup)
    ImageView ivNotifyCycle;
    @BindView(R.id.ivBackCreateGroup)
    ImageView ivBack;
    @BindView(R.id.etGroupName)
    EditText etGroupName;
    @BindView(R.id.iBtnCountOFMembersMines)
    ImageButton iBtnCountOFMembersMines;
    @BindView(R.id.iBtnCountOfMembersPlus)
    ImageButton iBtnCountOfMembersPlus;
    @BindView(R.id.tvCounterOfMembers)
    TextView tvCounterOfMembers;
    @BindView(R.id.etInputDate)
    EditText etInputDate;
    @BindView(R.id.spDuration)
    Spinner spDuration;
    @BindView(R.id.etInputInstallment)
    EditText etInputInstallment;
    @BindView(R.id.etInputTotal)
    EditText etTotal;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.btnNext)
    Button btnNext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int membersCount = 0;

    private Snackbar snack;

    private APIService mService;

    public CreateGroupFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CreateGroupFragment newInstance(String param1, String param2) {
        CreateGroupFragment fragment = new CreateGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);
        ButterKnife.bind(this, view);
        mService = ApiUtils.getAPIService();
        tvCounterOfMembers.setText(String.valueOf(membersCount));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menu();
        myAccountActivity();
        notification();
        back();
        checkNotification();
        membersCount();
        next();
    }

    private void menu() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(getActivity(), MoreActivity.class);
            }
        });
    }

    private void myAccountActivity() {
        ivMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpClass.startNewActivity(getActivity(), MyAccountActivity.class);
            }
        });
    }

    private void notification() {
        ivNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo go to notification page
            }
        });
    }

    private void back() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void next() {
        snack = Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.etMessageError), Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check required Data
                etInputDate.setText("2/2/2019");
                if (TextUtils.isEmpty(etGroupName.getText().toString())) {
                    snack.show();
                } else if (membersCount == 0) {
                    snack = Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.etMembersCountError), Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    snack.show();
                } else if (TextUtils.isEmpty(etInputDate.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etInputInstallment.getText().toString())) {
                    snack.show();
                } else if (TextUtils.isEmpty(etDescription.getText().toString())) {
                    snack.show();
                } else {
                    String groupName = etGroupName.getText().toString().trim();
                    int installment = Integer.parseInt(etInputInstallment.getText().toString().trim());
                    int total = membersCount * installment * 10;
                    etTotal.setText(String.valueOf(total));
                    String description = etDescription.getText().toString().trim();
                    int userId = DataProcessor.getInt(LOGIN_USER_ID);
                    NewGroup newGroup = new NewGroup(0, userId, groupName, description, membersCount, 10, 0, installment);
                    Fragment fragment = InviteFragment.newInstance(newGroup); // start CategoriesFragment
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerGroupActivity, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    private void membersCount() {
        iBtnCountOfMembersPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membersCount++;
                tvCounterOfMembers.setText(String.valueOf(membersCount));
            }
        });

        iBtnCountOFMembersMines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membersCount--;
                tvCounterOfMembers.setText(String.valueOf(membersCount));
            }
        });
    }

    private void checkNotification() {
        if (false) {
            ivNotifyCycle.setVisibility(View.GONE); //hide cycle
        } else {
            ivNotifyCycle.setVisibility(View.VISIBLE); //hide cycle
        }
    }

    private void createGroup(NewGroup newGroup) {

        mService.addGroup(newGroup, API_KEY).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int id;
                if (response.isSuccess()) {
                    if (response.body() != null) {
                        String resp = response.body().toString();
                        id = Integer.parseInt(resp);
                        Log.v("GroupID: ", String.valueOf(id));

                        if (id > 0) {
                            // success Create Group


                        } else if (id == -2) {
                            //Members count or Duration ==0 or
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
