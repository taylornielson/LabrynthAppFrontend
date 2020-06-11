package com.example.labrynth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.labrynth.UI.Game;
import com.example.labrynth.UI.GameLobby;
import com.example.labrynth.UI.LoginPage;
import com.example.labrynth.UI.NumPlayerPage;
import com.example.labrynth.UI.Startpage;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public String gamePlay = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new Startpage()).commit();
    }

    public void showNumPlayer(String s){
        gamePlay = s;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new NumPlayerPage()).commit();
    }

    public void showLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new LoginPage()).commit();

    }

    public void showPassGame(int i) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Game(i)).commit();
    }
    public void showLoggedIn(String s) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new GameLobby(s)).commit();
    }


}
