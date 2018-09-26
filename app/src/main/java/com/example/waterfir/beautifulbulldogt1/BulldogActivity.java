package com.example.waterfir.beautifulbulldogt1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.jar.Attributes;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {
    private TextView textView;
    private TextView dogNameView;
    private TextView dogAgeView;
    private EditText voteRating;
    private Realm realm;
    private User user;
    private Button voteButton;
    private Vote vote;
    private ImageView imageView2;
    private ImageView saveButton;
    private Bulldog bulldog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this grabs a textview and hooks it up to the new bulldog activity.
        setContentView(R.layout.activity_bulldog);

        //this will grab a textview
        textView = (TextView) findViewById(R.id.textView);
        realm = Realm.getDefaultInstance();

        dogAgeView = (TextView) findViewById(R.id.dog_age);
        dogNameView = (TextView) findViewById(R.id.dog_name);
        voteRating = (EditText) findViewById(R.id.vote_count);
        voteButton = (Button) findViewById(R.id.vote_button);
        imageView2 = (ImageView) findViewById(R.id.imageView);
        voteButton = (Button) findViewById(R.id.vote_button);





        //this will allow us to pass the selected bulldog to the bulldog activity
        String id = (String) getIntent().getStringExtra("bulldog");
        final Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        dogNameView.setText(bulldog.getName());
        dogAgeView.setText(bulldog.getAge());

        if(bulldog.getImage() != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(bulldog.getImage(),0,
                    bulldog.getImage().length);
            imageView2.setImageBitmap(bmp);
        }


        Realm realm = Realm.getDefaultInstance();
        String username = (String) getIntent().getStringExtra("username");
        user = realm.where(User.class).equalTo("username", username).findFirst();

        voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vote vote = new Vote();
                        vote.setRating(Integer.parseInt(voteRating.getText().toString()));
                        vote.setBulldog(bulldog);
                        vote.setOwner(user);
                        realm.copyToRealm(vote);
                        finish();//this dismisses the vote
                    }
                });
            }
        });
    }

}
