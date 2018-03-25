package com.example.jamesluo.gymer;

import android.content.Context;
import android.util.Log;

//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.MultipartBuilder;

import org.json.JSONObject;

import java.io.IOException;
import io.reactivex.Observable;

import javax.annotation.Nonnull;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import static java.security.AccessController.getContext;

/**
 * Created by jamesluo on 3/23/18.
 */

public class APIManager {
    private OkHttpClient client;
    private Context context;
    private String root;

    public APIManager(String url, Context c) {
        root = url;
        client = new OkHttpClient();
        context = c;
    }

    public Single<String> test(String user, String pw) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("q", "{test(query:\"妈卖批\")}")
                .add("k", "foo")
                .build();
        final Request request = new Request.Builder()
                .url(root + "/test")
                .post(requestBody)
                .build();
        //Observable<String> promise = new Observable();
        Single<String> s = new Single<String>() {
            @Override
            protected void subscribeActual(final SingleObserver<? super String> observer) {
        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                if (!response.isSuccessful()) {
                                                    throw new IOException("Unexpected code " + response);
                                                } else {
                                                    String json = response.body().string();
                                                    Log.d("connection result",json);
                                                    try {
                                                        JSONObject out = new JSONObject(json).getJSONObject("data");
                                                        final String x = out.getString("test");

                                                        observer.onSuccess(x);

                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        });
            }
        };
        return s;

    }

    public Single<JSONObject> login(String user, String pw) throws IOException {
        String query = "{login(email:\"" + user + "\",pw:\"" + pw + "\"){uid,sid}}";
        RequestBody requestBody = new FormBody.Builder()
                .add("q", query)
                .build();
        final Request request = new Request.Builder()
                .url(root + "/test")
                .post(requestBody)
                .build();
        //Observable<String> promise = new Observable();
        Single<JSONObject> s = new Single<JSONObject>() {
            @Override
            protected void subscribeActual(final SingleObserver<? super JSONObject> observer) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            String json = response.body().string();
                            Log.d("connection result",json);
                            try {
                                final JSONObject sess = new JSONObject(json).getJSONObject("data").getJSONObject("login");
                                //final String x = out.getString("test");

                                observer.onSuccess(sess);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        return s;

    }


    public Single<Integer> setAccount(String name,String email, String pw)throws IOException {
        String query = "{setAccount(name:\"" + name + "\","+"email:\"" + email + "\","+"pw:\"" + pw + "\")}";
        RequestBody requestBody = new FormBody.Builder()
                .add("q", query)
                .build();
        final Request request = new Request.Builder()
                .url(root + "/test")
                .post(requestBody)
                .build();
        //Observable<String> promise = new Observable();
        Single<Integer> s = new Single<Integer>() {
            @Override
            protected void subscribeActual(final SingleObserver<? super Integer> observer) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            String json = response.body().string();
                            Log.d("connection result",json);
                            try {
                                final JSONObject out = new JSONObject(json).getJSONObject("data");
                                final int x = out.getInt("setAccount");

                                observer.onSuccess(x);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        return s;
    }

}