package com.example.farmera;

//This is the first screen that appears when the app is opened. It takes care of authentication.
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        // init biometric
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "Error while authenticating " + errString , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Authentication successful" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FarmerMain.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Authentication failed" , Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint/Device Authentication")
                .setSubtitle("Welcome to the best farmer app in the world!")
                .setDeviceCredentialAllowed(true)
                .build();
    }

    public void Authenticate(android.view.View view) {

        biometricPrompt.authenticate(promptInfo);

    }

//    public void enterPin(android.view.View view) {
//
//        Intent intent = new Intent(MainActivity.this, EnterPin.class);
//        startActivity(intent);
//
//    }
//
//    public void aaja(android.view.View view) {
//        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
//        startActivity(intent);
//    }
//
}