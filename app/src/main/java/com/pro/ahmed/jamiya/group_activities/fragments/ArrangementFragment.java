package com.pro.ahmed.jamiya.group_activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.pro.ahmed.jamiya.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArrangementFragment extends Fragment {

    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ivBackArrangementFragment)
    ImageView ivBack;
    @BindView(R.id.rvArrangementFragment)
    RecyclerView rvArrangement;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ArrangementFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ArrangementFragment newInstance(String param1, String param2) {
        ArrangementFragment fragment = new ArrangementFragment();
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
        View view = inflater.inflate(R.layout.fragment_arrangement, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
