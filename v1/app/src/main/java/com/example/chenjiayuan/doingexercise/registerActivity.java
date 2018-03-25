package com.example.chenjiayuan.doingexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class registerActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerBtn;
    private TextView loginTextView;
    private EditText phoneEditText;
    private EditText nameEditText;
    private Toolbar toolbar;
    private APIManager apiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        apiManager = new APIManager("http://162.243.172.39:3000",this.getBaseContext());

        toolbar = (Toolbar) findViewById(R.id.registerToolbar);
        toolbar.setTitle(getResources().getString(R.string.registerToolbar_name));

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String name = nameEditText.getText().toString();

                try {
                    Single<Integer> s = apiManager.setAccount(name,username,password);
                    Disposable dis = s.subscribeWith(new DisposableSingleObserver<Integer>(){
                        @Override
                        public void onSuccess(Integer todos) {
                            if (todos == 1) {
                                Intent homePageIntent = new Intent(getApplicationContext(),homePageActivity.class);
                                startActivity(homePageIntent);
                            } else {
                                Intent registerIntent = new Intent(getApplicationContext(),registerActivity.class);
                                startActivity(registerIntent);
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
