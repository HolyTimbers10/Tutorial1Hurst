package com.example.waterfir.beautifulbulldogt1;

import android.app.Application;

import io.realm.Realm;

//this will intialize realm upon the opening of the application to apply this application
//file to the app need to go to the manifest folder Androidmanifest.xml
public class BulldogApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
    }
}
