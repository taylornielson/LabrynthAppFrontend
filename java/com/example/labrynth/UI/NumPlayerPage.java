package com.example.labrynth.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.labrynth.MainActivity;
import com.example.labrynth.R;

public class NumPlayerPage extends Fragment {
    //Button onePlayer;
    Button twoPlayer;
    Button threePlayer;
    Button fourPlayer;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numplayers, container, false);
        //onePlayer = (Button) v.findViewById(R.id.OnePlayerButton);
        twoPlayer = (Button) v.findViewById(R.id.TwoPlayersButton);
        threePlayer = (Button) v.findViewById(R.id.ThreePlayersButton);
        fourPlayer = (Button) v.findViewById(R.id.FourPlayersButton);
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((MainActivity)getActivity()).gamePlay == "pass"){
                    ((MainActivity)getActivity()).showPassGame(2);
                }
            }
        });
        threePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((MainActivity)getActivity()).gamePlay == "pass"){
                    ((MainActivity)getActivity()).showPassGame(3);
                }
            }
        });
        fourPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((MainActivity)getActivity()).gamePlay == "pass"){
                    ((MainActivity)getActivity()).showPassGame(4);
                }
            }
        });
        return v;
    }
}
