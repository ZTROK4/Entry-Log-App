package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogEntry extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_entry);
       ed1=(EditText) findViewById(R.id.name);
       ed2=(EditText) findViewById(R.id.admno);
       ed3=(EditText) findViewById(R.id.sysno);
       ed4=(EditText) findViewById(R.id.dptmt);
       b1=(AppCompatButton) findViewById(R.id.addbtn);
       b2=(AppCompatButton) findViewById(R.id.logoutbtn);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String getName=ed1.getText().toString();
               String getAdmNo=ed2.getText().toString();
               String getSysNo=ed3.getText().toString();
               String getDptmt=ed4.getText().toString();

               Toast.makeText(getApplicationContext(),"Added to log"+getName+getAdmNo+getSysNo+getDptmt,Toast.LENGTH_SHORT).show();
           }
       });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
               SharedPreferences.Editor editor= preferences.edit();
               editor.clear();
               editor.apply();
               Intent i=new Intent(getApplicationContext(), MainActivity.class);
               startActivity(i);
           }
       });

    }
}