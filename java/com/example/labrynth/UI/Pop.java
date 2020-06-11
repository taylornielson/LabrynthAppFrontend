package com.example.labrynth.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.labrynth.MainActivity;
import com.example.labrynth.Models.Cache;
import com.example.labrynth.Models.Proxy;
import com.example.labrynth.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class Pop extends Activity {
    Button submitGameButton;
    EditText gameNameET;
    boolean nameFilled = false;
    boolean radioClicked = false;
    RadioGroup radioGroup;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    int numPlayers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);
        submitGameButton = (Button) findViewById(R.id.submitNewGame);
        gameNameET = (EditText) findViewById(R.id.newGameName);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radio2 = (RadioButton) findViewById(R.id.twoPlayerRadio);
        radio3 = (RadioButton) findViewById(R.id.threePlayerRadio);
        radio4 = (RadioButton) findViewById(R.id.fourPlayerRadio);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
        gameNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (submitGameButton.length() > 0) {
                    nameFilled = true;
                }
                if (radioClicked && nameFilled) {
                    submitGameButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.twoPlayerRadio) {
                    numPlayers = 2;
                } else if (checkedId == R.id.threePlayerRadio) {
                    numPlayers = 3;
                } else if (checkedId == R.id.fourPlayerRadio) {
                    numPlayers = 4;
                }
                radioClicked = true;
                if (radioClicked && nameFilled) {
                    submitGameButton.setEnabled(true);
                }
            }
        });

        submitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGameTask addGameTask = new AddGameTask();
                addGameTask.execute();
                gameNameET.setText("");
                radioGroup.clearCheck();
                radioClicked = false;
                nameFilled = false;
            }
        });
    }

        public class AddGameTask extends AsyncTask<URL, Integer, JSONObject> {
            private String tag = "Add task";

            public AddGameTask() {

            }

            @Override
            protected JSONObject doInBackground(URL... urls) {
                JSONObject body = new JSONObject();
                try {
                    body.put("name", gameNameET.getText());
                    body.put("numplayer", numPlayers);
                    body.put("turn", 1);
                    body.put("player1", getIntent().getStringExtra("username"));
                } catch (JSONException e) {
                    Log.e(tag, "Json Error:" + e.getMessage());
                }
                JSONObject returnObject = Proxy.doPostAction("addGame", body);//Proxy.doPostAction("user/login", body);
                if (returnObject != null)
                    Log.e(tag, "did post action, result: " + returnObject.toString());
                else {
                    Log.e(tag, " res is null");
                    return new JSONObject();
                }
                return returnObject;
            }

            public void onPostExecute(JSONObject result) {
                if (result.has("message")) {
                    Toast.makeText(getApplicationContext(),"Failed to Add Game",Toast.LENGTH_LONG).show();
                    } else {
                    Toast.makeText(getApplicationContext(), "Added Game", Toast.LENGTH_LONG).show();
                }
            }
        }



}