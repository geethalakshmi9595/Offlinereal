package com.ench.geetha9529.offlinereal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ench.geetha9529.offlinereal.Model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

public class Main2Activity extends AppCompatActivity
{
TextView t11;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t11=(TextView)findViewById(R.id.textView2);
        Realm.init(this);
        realm=Realm.getDefaultInstance();
        RealmResults<Person> results =realm.where(Person.class).findAll();
        for(int i=0;i<results.size();i++)
        {
            t11.append("\n"+"Name:" + results.get(i).getName() +"\n"+ "Email:" + results.get(i).getEmailID() +"\n"+ "Phonenumber:" +results.get(i).getPhonenumber() +"\n"+ "Password:" + results.get(i).getPassword()+"\n\n\n");
        }
    }
}


