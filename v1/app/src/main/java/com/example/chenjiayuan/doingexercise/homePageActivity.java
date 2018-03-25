package com.example.chenjiayuan.doingexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class homePageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView profileNameTextView;
    private TextView profileEmailTextView;
    private TextView profilePhoneTextView;
    private TextView profileAvailStaTextView;
    private Button makeAppointBtn;
    private Button goSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        profileNameTextView = (TextView) findViewById(R.id.profileNameTextView);
        profileEmailTextView = (TextView) findViewById(R.id.profileEmailTextView);
        profilePhoneTextView = (TextView) findViewById(R.id.profilePhoneTextView);
        profileAvailStaTextView = (TextView) findViewById(R.id.profileAvailStaTextView);
        makeAppointBtn = (Button) findViewById(R.id.makeAppointBtn);
        goSetBtn = (Button) findViewById(R.id.goSetBtn);

        toolbar = (Toolbar) findViewById(R.id.homePageToolbar);
        toolbar.setTitle(getResources().getString(R.string.homePageToolbar_name));

        try {
            byte[] bjson = profileSettingActivity.sessionManager.takeSess();
            String json = profileSettingActivity.sessionManager.decypher(bjson);
            JSONObject jsonObject = new JSONObject(json);
            profileNameTextView.setText(jsonObject.getString("name"));
            profilePhoneTextView.setText(jsonObject.getString("phone"));
            profileEmailTextView.setText(jsonObject.getString("email"));
            profileAvailStaTextView.setText(jsonObject.getString("availSta"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        makeAppointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appointmentIntent = new Intent(getApplicationContext(),appointmentActivity.class);
                startActivity(appointmentIntent);
            }
        });



        goSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSetIntent = new Intent(getApplicationContext(),profileSettingActivity.class);
                startActivity(goSetIntent);
            }
        });
    }
}
