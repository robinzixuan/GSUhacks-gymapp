package com.example.jamesluo.gymer;

import android.content.Context;
import android.content.Intent;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity {
    private String res;
    private Context context = this.getBaseContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView v = (TextView)findViewById(R.id.hw);
        APIManager am = new APIManager("http://10.0.2.2:3000",this.getBaseContext());
        try {
            Single<String> s = am.test("luodick@gmail.com", "1234567890");

        Disposable dis = s.subscribeWith(new DisposableSingleObserver<String>(){
            @Override
            public void onSuccess(String todos) {
                // work with the resulting todos
                Log.d("returned",todos);
                //setRes(todos);
                v.setText(todos);
            }

            @Override
            public void onError(Throwable e) {
                // handle the error case
                e.printStackTrace();
            }

        });
        }catch (Exception e){
            e.printStackTrace();
        }
//        Toast.makeText(MainActivity.this,"?",Toast.LENGTH_SHORT).show();
//        Intent in = new Intent(MainActivity.this,MapShit.class );
    }
    protected void onStart(){
        super.onStart();
        try {
            JSONObject sess = new JSONObject("{\"test1\":\"fuck\",\"test2\":\"xxx\"}");
            Log.d("Original sess", sess.toString());
            SessionManager sm = new SessionManager("usid",this);
            byte[] bsess = sm.encypher(sess.toString());
            sm.storeSess(bsess);
            Log.d("finish cyphering sess", "-------------------------------------------------");
            bsess = sm.takeSess();
            String out = sm.decypher(bsess);
            Log.d("fucked sess", out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setRes(String p){
        res = p;
        Toast.makeText(MainActivity.this,p,Toast.LENGTH_SHORT).show();
    }
}
