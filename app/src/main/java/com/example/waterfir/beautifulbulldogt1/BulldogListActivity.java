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

        //this will find the text views button and text view is cast to let the app know
        //what kind of button it is
        textView = (TextView) findViewById(R.id.textView2);

        //this will set textView to our input email gets the email data
        String email = getIntent().getStringExtra("email");
        //textView is linked btwn the view and the controller
        textView.setText(email);
    }
}

