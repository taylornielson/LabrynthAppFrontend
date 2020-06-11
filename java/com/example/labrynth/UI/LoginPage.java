package com.example.labrynth.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.labrynth.MainActivity;
import com.example.labrynth.Models.Cache;
import com.example.labrynth.Models.Proxy;
import com.example.labrynth.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginPage extends Fragment {
    static final String tag = "login";
    private Boolean doingAsyncTask = false;
    EditText username;
    EditText password;
    Boolean usernameChanged;
    Boolean passwordChanged;
    String usernameString = "";
    String passwordString = "";
    Button login;
    Button register;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        username = (EditText) v.findViewById(R.id.LoginUsername);
        password = (EditText) v.findViewById(R.id.LoginPassword);
        login = (Button) v.findViewById(R.id.LoginButton);
        register = (Button) v.findViewById(R.id.RegisterButton);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    usernameChanged = true;

                } else {
                    usernameChanged = false;
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    passwordChanged = true;

                } else {
                    passwordChanged = false;
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginPage.this.doSignIn();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPage.this.doRegister();
            }
        });
        return v;
    }
    public void doSignIn() {
        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
        if(usernameString.length() < 1){
            Toast.makeText(getActivity().getApplicationContext(),"Username is empty",Toast.LENGTH_LONG).show();
        }
        else if(passwordString.length() < 6){
            Toast.makeText(getActivity().getApplicationContext(),"Typed password isn't long enough",Toast.LENGTH_LONG).show();
        }
        else {
            if (doingAsyncTask) return;
            Log.e(tag, "doing login");
            usernameString = username.getText().toString();
            passwordString = password.getText().toString();
            doingAsyncTask = true;
            LoginTask loginTask = new LoginTask(usernameString, passwordString);
            loginTask.execute();
        }
    }
    public void doRegister() {
        if (doingAsyncTask) return;
        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
        if (passwordString.length() < 6){
            Toast.makeText(getActivity().getApplicationContext(),"Password must be 6 characters or longer", Toast.LENGTH_LONG).show();
        }
        else {
            doingAsyncTask = true;
            Log.e(tag, "made it to register task");
            RegisterTask registerTask = new RegisterTask(usernameString, passwordString);
            registerTask.execute();
        }
    }

    public void loginFailed() {
        doingAsyncTask = false;
        Log.e(tag, "failing login");
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Login Failed", Toast.LENGTH_LONG);
        toast.show();
    }

    public void loginFailed(String s) {
        doingAsyncTask = false;
        Log.e(tag, "failing login");
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.show();
    }

    public void loginSuccessfull() {
        UserDataLoadTask userDataLoadTask = new UserDataLoadTask();
        userDataLoadTask.execute();
        Log.e(tag, "successful login");
    }

    public class LoginTask extends AsyncTask<URL, Integer, JSONObject> {
        private String userText;
        private String passText;
        private String tag = "Login";

        public LoginTask(String userText, String passText) {
            this.userText = userText;
            this.passText = passText;
        }

        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject body = new JSONObject();
            try {
                body.put("userName", userText);
                body.put("password", passText);
            } catch (JSONException e) {
                Log.e(tag, "Json Error:" + e.getMessage());
            }
            JSONObject returnObject = Proxy.doPostAction("user/login", body);//Proxy.doPostAction("user/login", body);
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
                try {
                    loginFailed(result.getString("message"));
                } catch (JSONException e) {
                    loginFailed();
                    e.printStackTrace();
                }
            } else {
                    Cache cache = Cache.getInstance();
                    cache.userName = userText;
                loginSuccessfull();
            }
        }
    }


    public class RegisterTask extends AsyncTask<URL, Integer, JSONObject> {
        private String userText;
        private String passText;
        private String tag = "Register";

        public RegisterTask(String userText, String passText) {
            this.userText = userText;
            this.passText = passText;
            this.tag = tag;
        }


        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject body = new JSONObject();
            try {
                body.put("userName", userText);
                body.put("password", passText);
            } catch (JSONException e) {
                Log.e(tag, "Json Error:" + e.getMessage());
            }
            Log.e(tag,"Made it to before postAction");
            JSONObject returnObject = Proxy.doPostAction("user/register", body);//("user/register", body);
            if (returnObject != null)
                Log.e(tag, "did post action, res: " + returnObject.toString());
            else {
                Log.e(tag, " res is null");
                return new JSONObject();
            }
            return returnObject;
        }

        public void onPostExecute(JSONObject result) {
            if (result.has("message")) {
                Log.e(tag, "onPostExecute: failed");
                loginFailed();
            } else {
                Cache cache = Cache.getInstance();
                cache.userName = userText;
                loginSuccessfull();
            }
        }
    }

    public class UserDataLoadTask extends AsyncTask<URL, Integer, Boolean>
    {
        public static final String tag = "userDatatask";

        @Override
        public Boolean doInBackground(URL... urls)
        {
            return Cache.loadCache();
        }

        public void onProgressUpdate(Integer... progress)
        {

        }

        public void onPostExecute(Boolean success)
        {
            doingAsyncTask = false;
            if (!success) {
                Log.e(tag,"userDatafail");
                loginFailed();
            }
            else {
                Cache cache = Cache.getInstance();
                usernameString = username.getText().toString();
                ((MainActivity)getActivity()).showLoggedIn(usernameString);
            }
        }
    }

}
