package com.pro.ahmed.jamiya.group_activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.pro.ahmed.jamiya.R;
import com.pro.ahmed.jamiya.data.api.APIService;
import com.pro.ahmed.jamiya.data.api.ApiUtils;
import com.pro.ahmed.jamiya.data.models.NewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pro.ahmed.jamiya.help_classes.HelpConstants.API_KEY;


public class ArrangementFragment extends Fragment {

    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ivBackArrangementFragment)
    ImageView ivBack;
    @BindView(R.id.rvArrangementFragment)
    RecyclerView rvArrangement;

    private static final String GROUP_OBJ = "group_obj";
    private NewGroup groupObj;
    private APIService mService;

    public ArrangementFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ArrangementFragment newInstance(NewGroup groupObj) {
        ArrangementFragment fragment = new ArrangementFragment();
        Bundle args = new Bundle();
        args.putSerializable(GROUP_OBJ, groupObj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = ApiUtils.getAPIService();
        if (getArguments() != null) {
            groupObj = (NewGroup) getArguments().getSerializable(GROUP_OBJ);
            Log.v("GroupIDArrangement", groupObj.getName());
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back();
        saveGroup();
    }

    private void back() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });
    }

    private void saveGroup() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroup(groupObj);
            }
        });
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
                            getActivity().finish();

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
