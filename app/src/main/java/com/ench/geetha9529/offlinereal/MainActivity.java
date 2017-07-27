package com.ench.geetha9529.offlinereal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ench.geetha9529.offlinereal.Model.Person;

import io.realm.Realm;
import io.realm.RealmResults;



public class MainActivity extends AppCompatActivity {
EditText ename,eemail,eph,epass;
        Button badd,bview,bdelete;

    private Realm realm;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
           realm=Realm.getDefaultInstance();
            ename=(EditText)findViewById(R.id.editText1);
            eemail=(EditText)findViewById(R.id.editText3);
            eph=(EditText)findViewById(R.id.editText4);
            epass=(EditText)findViewById(R.id.editText5);

            badd=(Button)findViewById(R.id.button5);
            bview=(Button)findViewById(R.id.button7);
            bdelete=(Button)findViewById(R.id.button6);
            badd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
save_to_database(ename.getText().toString().trim(),eemail.getText().toString().trim(),eph.getText().toString().trim(),epass.getText().toString().trim());
                }
            });
            bdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String Name=ename.getText().toString();
//delete_from_database(Name);
                    Intent i=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(i);
                }
            });
            bview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              Intent i=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                }
            });
        }

//    private void delete_from_database(String Name)
//    {
//        final RealmResults<Person> person=realm.where(Person.class).equalTo("Name",Name).findAll();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                person.deleteFromRealm(0);
//            }
//        });
//    }

    private void save_to_database( final String Name,final String EmailID, final String Phonenumber,final String Password) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person person = bgRealm.createObject(Person.class);
                person.setName(Name);
                person.setEmailID(EmailID);
                person.setPhonenumber(Phonenumber);
                person.setPassword(Password);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v("Success","----ok------");
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("failed",error.getMessage());
                // Transaction failed and was automatically canceled.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
