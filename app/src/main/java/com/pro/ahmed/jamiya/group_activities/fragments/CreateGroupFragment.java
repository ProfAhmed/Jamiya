package com.pro.ahmed.jamiya.group_activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pro.ahmed.jamiya.MoreActivity;
import com.pro.ahmed.jamiya.R;
import com.pro.ahmed.jamiya.help_classes.HelpClass;
import com.pro.ahmed.jamiya.my_account_activities.MyAccountActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        menu();
        myAccountActivity();
        notification();
        back();
        checkNotification();

        next();

        return view;
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InviteFragment(); // start CategoriesFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerGroupActivity, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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

}
