package com.telran.a16_03_20.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.telran.a16_03_20.R;
import com.telran.a16_03_20.presentation.auth.view.AuthFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            AuthFragment fragment = new AuthFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, fragment)
                    .commit();
        }
    }
}
