package com.pro.ahmed.jamiya.group_activities.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.pro.ahmed.jamiya.R;
import com.pro.ahmed.jamiya.data.models.NewGroup;

import java.nio.file.attribute.GroupPrincipal;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InviteFragment extends Fragment {

    @BindView(R.id.btnInvite)
    Button btnInvite;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivBackInviteFragment)
    ImageView ivBackInviteFragment;
    @BindView(R.id.rvInviteFragment)
    RecyclerView rvInviteFragment;
    private static final String GROUP_OBJ = "group_obj";

    // TODO: Rename and change types of parameters
    private NewGroup groupObj;


    public InviteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static InviteFragment newInstance(NewGroup groupObj) {
        InviteFragment fragment = new InviteFragment();
        Bundle args = new Bundle();
        args.putSerializable(GROUP_OBJ, groupObj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            groupObj = (NewGroup) getArguments().getSerializable(GROUP_OBJ);
            Log.v("GroupIDInvite", groupObj.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invite, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back();
        invite();
    }

    private void back() {
        ivBackInviteFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

    }

    private void invite() {
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ArrangementFragment.newInstance(groupObj); // start CategoriesFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerGroupActivity, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
