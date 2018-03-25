package com.example.jamesluo.gymer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.Provider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jamesluo on 3/24/18.
 */

public class SessionManager  {
    private String alias;
    byte[] iv;
    Context c;
    public SessionManager(String a, Context c){
        alias = a;
        this.c =c;
    }
    public void storeSess(byte[] s){
        SharedPreferences.Editor editor = c.getSharedPreferences("Gymer_sess", MODE_PRIVATE).edit();
        editor.putString("usid", new String(Base64.encode(s,0)));
        editor.apply();
    }
    public byte[] takeSess() throws UnsupportedEncodingException{
        SharedPreferences prefs = c.getSharedPreferences("Gymer_sess", MODE_PRIVATE);
        return Base64.decode(prefs.getString("usid", null).getBytes("UTF-8"),0);
    }
    public byte[] encypher(String json)throws Exception{
        final KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

        final KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(alias,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build();
        keyGenerator.init(keyGenParameterSpec);
        final SecretKey secretKey = keyGenerator.generateKey();

        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        iv = cipher.getIV();

        byte[] encryption = cipher.doFinal(json.getBytes("UTF-8"));
        return encryption;
    }
    public String decypher(byte[] x) throws Exception{
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore
                .getEntry(alias, null);

        final SecretKey secretKey = secretKeyEntry.getSecretKey();
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        final GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        final byte[] decodedData = cipher.doFinal(x);
        final String unencryptedString = new String(decodedData, "UTF-8");
        return unencryptedString;
    }
}
