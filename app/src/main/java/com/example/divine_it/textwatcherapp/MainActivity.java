package com.example.divine_it.textwatcherapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents other activity different from the main activity
 */
public class MainActivity extends AppCompatActivity
{
    boolean flag=false;
    private EditText name,phone,email,address;
    private ImageButton name_btn,phone_btn,email_btn,address_btn;
    String strSavedMem2;
    String strSavedMem1;
    String strSavedMem3;
    String strSavedMem4;

    String name1;
    String phone1;
    String email1;
    String address1;
    Button save;
    boolean test=false;

// First initialize the object of TextWatcher Method.

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        address = (EditText)findViewById(R.id.address);
        name_btn=(ImageButton)findViewById(R.id.name_btn);
        phone_btn=(ImageButton)findViewById(R.id.phn_btn);
        email_btn=(ImageButton) findViewById(R.id.email_btn);
        address_btn=(ImageButton)findViewById(R.id.adrress_btn);
        save=(Button)findViewById(R.id.save);



        name_btn.setVisibility(View.INVISIBLE);
        phone_btn.setVisibility(View.INVISIBLE);
        email_btn.setVisibility(View.INVISIBLE);
        address_btn.setVisibility(View.INVISIBLE);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                if(test) {
                    Toast.makeText(MainActivity.this, name1 + "\n" + phone1 + "\n" + email1 + "\n" + address1 + "\n", Toast.LENGTH_LONG).show();
                    SavePreferences("name", name.getText().toString());
                    SavePreferences("phone", phone.getText().toString());
                    SavePreferences("email", email.getText().toString());
                    SavePreferences("address", address.getText().toString());
                    LoadPreferences();
                }
                else
                {

                    Toast.makeText(MainActivity.this, name1 + "Please fill up all required field", Toast.LENGTH_LONG).show();


                }

            }
        });

// This method do main performance of share preference
        LoadPreferences();

      // This method check that Field is empty or  fill up by
      // correct Data
        checkFieldsForEmptyValues();

        name.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);
        address.addTextChangedListener(textWatcher);


    }






    private  void checkFieldsForEmptyValues(){

        name1 = name.getText().toString();
        phone1= phone.getText().toString();
        email1 = email.getText().toString();
        address1 = address.getText().toString();
        System.out.println("ggdgd    "+ name1 );
        System.out.println(phone1 );
        System.out.println(email1 );
        System.out.println(address1 );

        if(!isValidMobile(phone1))       {
            if(flag)
                phone.setError("Invalid Phone number");
            phone_btn.setVisibility(View.INVISIBLE);
            test=false;

        }
        else if(phone1.isEmpty())
        {    if(flag)
            phone.setError(" Phone number is empty");
            phone_btn.setVisibility(View.INVISIBLE);
            test=false;
        }

        else
        {

            phone_btn.setVisibility(View.VISIBLE);
            test=true;
        }


        if(name1.isEmpty())
        {    if(flag)
            name.setError("name is empty");
            name_btn.setVisibility(View.INVISIBLE);
            test=false;
        }
        else
        {

            name_btn.setVisibility(View.VISIBLE);
            test=true;
        }

        if (!isValidEmail(email1))
        {    if(flag)
            email.setError("Invalid Email");
            email_btn.setVisibility(View.INVISIBLE);
            test=false;
        }

        else if(email1.isEmpty())
        {    if(flag)
            email.setError(" Email is empty");
            email_btn.setVisibility(View.INVISIBLE);
            test=false;

        }



        else
        {

            email_btn.setVisibility(View.VISIBLE);
            test=true;
        }

        if(address1.isEmpty())
        {    if(flag)
            address.setError("Address is empty");
            address_btn.setVisibility(View.INVISIBLE);
            test=false;
        }
        else
        {

            address_btn.setVisibility(View.VISIBLE);
            test=true;
        }


    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }



    private void LoadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        strSavedMem1 = sharedPreferences.getString("name", "");
        strSavedMem2 = sharedPreferences.getString("phone", "");
        strSavedMem3 = sharedPreferences.getString("email", "");
        strSavedMem4 = sharedPreferences.getString("address", "");
        name.setText(strSavedMem1);
        phone.setText(strSavedMem2);

        email.setText(strSavedMem3);
        address.setText(strSavedMem4);



    }


    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
