package com.example.waterfir.beautifulbulldogt1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BulldogListActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);
        textView = (TextView) findViewById(R.id.textView2);

        String email = getIntent().getStringExtra("email");
        textView.setText(email);
    }
}
