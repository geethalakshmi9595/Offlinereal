package com.ench.geetha9529.offlinereal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ench.geetha9529.offlinereal.Model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;



public class Main3Activity extends AppCompatActivity {
    private Realm realm;
    Button deletes;
    EditText edituser;
    TextView text1;
    Spinner spinner;
    ArrayAdapter adapter;
    ArrayList<String> listofNames;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner=(Spinner)findViewById(R.id.spinner1);
        text1=(TextView)findViewById(R.id.text12);
        listofNames=new ArrayList<>();
        deletes=(Button)findViewById(R.id.button);

        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
        RealmResults<Person> results = realm.where(Person.class).findAll();
        for(int i=0;i<results.size();i++){
            listofNames.add(results.get(i).getName());
        }
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listofNames);
        spinner.setAdapter(adapter);
        deletes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index=adapterView.getSelectedItemPosition();

                text1.setText(listofNames.get(index));
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        Main3Activity.this);
                builder.setTitle("Are you sure want to delete??");
                builder.setMessage("Please enter email");
                final EditText input = new EditText(context);
                builder.setView(input);
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String srt = input.getEditableText().toString();
                                Intent intent = getIntent();
                                final String emailid = intent.getStringExtra("emailid");
                                if(srt.equals(emailid))
                                {
                                    final RealmResults<Person> person=realm.where(Person.class).findAll();
                                    realm.executeTransaction(new Realm.Transaction()
                                    {
                                        @Override
                                        public void execute(Realm realm)
                                        {
                                            person.deleteFromRealm(0);
                                        }
                                    });

                                }


//                                Toast.makeText(context,srt,Toast.LENGTH_LONG).show();

                                //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.cancel();
                                //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                            }
                        });

                builder.show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}