package com.example.waterfir.beautifulbulldogt1;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//this will extend the realm object so it will convert the bulldog class into a realm object so it
//can be stored into the db
public class Bulldog extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private String age;
    private  byte[] image;//an image is stored as a byte array

    public  byte[] getImage(){
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public void setId(String s){
        this.id = s;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAge() {

        return age;
    }

    public void setAge(String age) {

        this.age = age;
    }
}
