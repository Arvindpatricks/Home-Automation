package com.craftydesigners.arvind.homeautomation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileInputStream;

public class Home extends AppCompatActivity {
    Switch fan,light,cooker,mixie;
    String text;
    String number;
Button sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sms=(Button)findViewById(R.id.button2);
        fan = (Switch)findViewById(R.id.fan);
        light =(Switch)findViewById(R.id.light);
        cooker = (Switch)findViewById(R.id.cooker);
        mixie =(Switch)findViewById(R.id.mixie);
        String temp="";
        try{
            FileInputStream fin = openFileInput("mydata");
            int c;

            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

        }
        catch(Exception e){
        }
        number=temp;
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fanstate =(fan.isChecked())?"1":"0";
                String lightstate =(light.isChecked())?"1":"0";
                String cookerstate =(cooker.isChecked())?"1":"0";
                String mixiestate =(mixie.isChecked())?"1":"0";
                text = "Fan : "+fanstate+"\n"+"Light : "+lightstate+"\nCooker : "+cookerstate+"\nMixie State :" + mixiestate;

                Toast.makeText(getBaseContext(), text,Toast.LENGTH_LONG).show();
                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(number, null, text, null, null);

            }
        });


    }

}
