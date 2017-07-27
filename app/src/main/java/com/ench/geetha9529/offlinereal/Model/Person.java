package com.ench.geetha9529.offlinereal.Model;

import io.realm.RealmObject;

/**
 * Created by enchanter20 on 3/7/17.
 */

public class Person extends RealmObject {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String Name,EmailID,Phonenumber,Password;

    @Override
    public String toString() {
        return "Person{" + "Name='" + Name + '\'' + ", EmailID='" + EmailID + '\'' + ", Phonenumber='" + Phonenumber + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public static void deleteFromRealm(int i) {
    }
}
