package com.example.labrynth.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.labrynth.MainActivity;
import com.example.labrynth.R;

public class Startpage extends Fragment {
    static final String LOG_TAG = "Startpage:";
    Button online;
    Button passNPlay;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        online = (Button)v.findViewById(R.id.OnlinePlay);
        passNPlay = v.findViewById(R.id.PassandPlayButton);

        online.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //savedInstanceState.putBoolean("online", true);
                startLogin();
            }
        });
        passNPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startNumPlayerPage("pass");
            }
        });
        return v;
    }
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.i(LOG_TAG, "in onAttachFragment(Fragment)");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "in onActivityCreated(Bundle)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "in onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "in onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "in onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "in onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(LOG_TAG, "in onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "in onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(LOG_TAG, "in onDetach()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG, "in onSaveInstanceState(Bundle)");
    }
    private void startNumPlayerPage(String s)
    {
        ((MainActivity)getActivity()).showNumPlayer(s);
    }
    private void startLogin()
    {
        ((MainActivity)getActivity()).showLogin();
    }


}


