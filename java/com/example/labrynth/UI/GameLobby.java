package com.example.labrynth.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labrynth.MainActivity;
import com.example.labrynth.R;

import java.util.ArrayList;

public class GameLobby extends Fragment {
    Button newGameButton;
    Button joinGameButton;
    String userName;
    RecyclerView currentTurn;
    RecyclerView notCurrentTurn;
    ArrayList<Integer> gameIDs1 = new ArrayList<>();
    ArrayList<String> gameNames1 = new ArrayList<>();
    ArrayList<String> turns1 = new ArrayList<>();

    public GameLobby(String s){
        userName = s;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiplayergamepage, container, false);
        newGameButton = (Button) v.findViewById(R.id.newMultiButton);
        joinGameButton = (Button) v.findViewById(R.id.joinMultiButton);
        fillArrays();
        currentTurn = v.findViewById(R.id.currentTurnRecycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(gameIDs1, gameNames1, turns1);
        currentTurn.setAdapter(adapter);
        currentTurn.setLayoutManager(new LinearLayoutManager(getContext()));

        notCurrentTurn = v.findViewById(R.id.notCurrentTurnRecycler);
        notCurrentTurn.setAdapter(adapter);
        notCurrentTurn.setLayoutManager(new LinearLayoutManager(getContext()));
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Pop.class );
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        return v;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        ArrayList<Integer> gameIDs;
        ArrayList<String> gameNames;
        ArrayList<String> turns;

        public RecyclerViewAdapter(ArrayList<Integer> gameIDs, ArrayList<String> gameNames, ArrayList<String> turns) {
            this.gameIDs = gameIDs;
            this.gameNames = gameNames;
            this.turns = turns;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_layout,parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Log.d("BINDER","Binding");
            holder.gameID.setText(Integer.toString(gameIDs.get(position)));
            holder.gameName.setText(gameNames.get(position));
            holder.turn.setText(turns.get(position));
            //TODO ADD ON CLICK LISTENER TO GO TO GAME
        }

        @Override
        public int getItemCount() {
            return gameIDs.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView gameID;
            TextView gameName;
            TextView turn;
            LinearLayout layout;
            public ViewHolder(View itemView){
                super(itemView);
                gameID = (TextView) itemView.findViewById(R.id.gameID);
                gameName = (TextView) itemView.findViewById(R.id.gameName);
                turn = (TextView) itemView.findViewById(R.id.turn);
                layout = (LinearLayout) itemView.findViewById(R.id.parentLayout);
            }
        }
    }

    private void fillArrays(){
        gameIDs1.add(1);
        gameIDs1.add(2);
        gameIDs1.add(3);
        gameIDs1.add(4);
        gameNames1.add("Taylor's game");
        gameNames1.add("Bob's game");
        gameNames1.add("Joe's game");
        gameNames1.add("Steve's game");
        turns1.add("Taylor (1)");
        turns1.add("Tay (1)");
        turns1.add("Tbob (1)");
        turns1.add("Thor (1)");

    }


}
