package com.example.chenjiayuan.doingexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private TextView registerTextView;
    private Toolbar toolbar;
    private APIManager apiManager;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerTextView = (TextView) findViewById(R.id.registerTextView);
        sessionManager =  new SessionManager("suid",this);
        apiManager = new APIManager("http://162.243.172.39:3000",this.getBaseContext());

        toolbar = (Toolbar) findViewById(R.id.app_name);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),registerActivity.class);
                startActivity(startIntent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                try {
                    Single<JSONObject> s = apiManager.login(username,password);
                    Disposable dis = s.subscribeWith(new DisposableSingleObserver<JSONObject>(){
                        @Override
                        public void onSuccess(JSONObject sess) {
                            try {
                                String uid = sess.getString("uid");
                                Log.d("returned",uid);
                                if (uid.equals("false")) {
                                    Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(loginIntent);
                                } else {
                                    String sid = sess.getString("sid");
                                    byte[] bsess = sessionManager.encypher(sess.toString());
                                    sessionManager.storeSess(bsess);
                                    Intent homePageIntent = new Intent(getApplicationContext(),homePageActivity.class);
                                    startActivity(homePageIntent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            // handle the error case
                            e.printStackTrace();
                        }

                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
