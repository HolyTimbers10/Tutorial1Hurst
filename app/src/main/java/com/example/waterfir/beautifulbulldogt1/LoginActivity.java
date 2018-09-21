package com.example.waterfir.beautifulbulldogt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
 //this is variable declarations
    private Button loginButton;
    private EditText emailField;
    private EditText passwordField;

    //this will initialize a couple of bulldogs to be used in our list

    public void populateBulldogs(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){

                Bulldog dog1 = new Bulldog();
                dog1.setId("1");
                dog1.setName("Spots");
                dog1.setAge("2");
                realm.copyToRealmOrUpdate(dog1);

                Bulldog dog2 = new Bulldog();
                dog2.setId("2");
                dog2.setName("Spike");
                dog2.setAge("12");
                realm.copyToRealmOrUpdate(dog2);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //this takes the declared vars and ties the buttons created in the activity_bulldog.xml to the
        //activity page (MVC)... Type casting the buttons lets app know what to look for and findViewById
        //identifies the buttons created.  This gives direct access to view the elements
        loginButton = (Button) findViewById(R.id.login_button);
        emailField = (EditText) findViewById(R.id.email_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Bulldog> dogs = realm.where(Bulldog.class).findAll();
        if(dogs.size() ==0) {populateBulldogs();}

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User user = new User();
                        user.setUsername(emailField.getText().toString());
                        realm.copyToRealmOrUpdate(user);

                        //intents perform transitions and open Android services like camera
                        //context is the current location, MainActivity.class is where you want to go
                        //so now the login button opens the main activity
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        //add a parameter to intent (emial) the getText()gets the user input.
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);//
                    }
                });
            }
        });
    }
}

