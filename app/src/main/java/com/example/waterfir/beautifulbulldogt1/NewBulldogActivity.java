package com.example.waterfir.beautifulbulldogt1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class NewBulldogActivity extends AppCompatActivity {

    //ui elements
    private ImageButton imageButton;
    private TextView bulldogName;
    private TextView bulldogAge;
    private Bitmap imageBitmap;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //grab the layout
        setContentView(R.layout.activity_new_bulldog2);

        //view bindings
        bulldogName = (TextView) findViewById(R.id.bulldog_name);
        bulldogAge = (TextView) findViewById(R.id.bulldog_age);
        imageButton = (ImageButton) findViewById(R.id.image_button);
        saveButton = (Button) findViewById(R.id.save_button);

        //actions opens the users camera
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                //checks to make sure all these fields have strings and not uploading a blank image
                if(!bulldogName.getText().toString().matches("")
                    && !bulldogAge.getText().toString().matches("")
                    && imageButton.getDrawable() !=null){
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bulldog bulldog = new Bulldog();
                            bulldog.setAge(bulldogAge.getText().toString());
                            bulldog.setName(bulldogName.getText().toString());

                            //sets bulldog id and increments by 1
                            bulldog.setId(realm.where(Bulldog.class).findAllSorted("id").last().getId()+1);

                            //this saves image by getting drawable from imagebutton, compresses to byte array
                            //image is then set and assigned compressed byte array to bulldog image
                            BitmapDrawable image = (BitmapDrawable)imageButton.getDrawable();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte [] imageInByte = baos.toByteArray();
                            bulldog.setImage(imageInByte);
                            //this saves obj to realm and returns user to main activity
                            realm.copyToRealm(bulldog);
                            finish();

                        }
                    });

                }

            }
        });

    }

    //function called when app returns from the camera closes camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //this code allows us to gain access to the image and checks if image is okay
        if (requestCode ==1 && resultCode == RESULT_OK){
            //gets image from extras and assigns it to bitmap
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            imageButton.setImageBitmap(imageBitmap);}
        }
}


