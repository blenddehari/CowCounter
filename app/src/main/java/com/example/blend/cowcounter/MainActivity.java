package com.example.blend.cowcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String LOGGING_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(LOGGING_TAG,getLocalClassName() + ".onCreate is called!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AddButton = (Button) findViewById(R.id.button_add);

        EditText editText_breed = (EditText) findViewById(R.id.editText_breed);
        editText_breed.setText("breed", TextView.BufferType.EDITABLE);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(LOGGING_TAG, getLocalClassName() + "Goodbye!");

            }
        });
    }
}
