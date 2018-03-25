package com.example.chenjiayuan.doingexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profileSettingActivity extends AppCompatActivity {

    private EditText profileNameEditText;
    private EditText profileEmailEditText;
    private EditText profilePhoneEditText;
    private EditText profileAvailStaEditText;

    private TextView profileNameTextView;
    private TextView profileEmailTextView;
    private TextView profilePhoneTextView;
    private TextView profileAvailStaTextView;
    private Button settingBtn;
    public static SessionManager sessionManager;
    public static Session session;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        profileNameEditText = (EditText) findViewById(R.id.profileNameTextView);
        profileEmailEditText = (EditText) findViewById(R.id.profileEmailTextView);
        profilePhoneEditText = (EditText) findViewById(R.id.profilePhoneTextView);
        profileAvailStaEditText = (EditText) findViewById(R.id.profileAvailStaTextView);


        settingBtn = (Button) findViewById(R.id.settingBtn);
        sessionManager =  new SessionManager("suid",this);

        toolbar = (Toolbar) findViewById(R.id.profileSettingToolbar);
        toolbar.setTitle(getResources().getString(R.string.profileSettingToolbar_name));

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = profileNameEditText.getText().toString();
                String email = profileEmailEditText.getText().toString();
                String phone = profilePhoneEditText.getText().toString();
                String availSta = profileAvailStaEditText.getText().toString();

                String json = "{name:\"" + name + "\", email:\"" + email + "\", phone:\"" + phone+ "\", availSta:\"" + availSta + "\"}";
                try {
                    byte[] bjson = sessionManager.encypher(json);
                    sessionManager.storeSess(bjson);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent startIntent = new Intent(getApplicationContext(),homePageActivity.class);
                startActivity(startIntent);
            }
        });

    }
}
