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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogEntry extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    String apiUrl="http://10.0.4.16:3000/api/students";
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

               //JSON object ceation
               JSONObject student=new JSONObject();
               try {
                   student.put("name",getName);
                   student.put("admission_number",getAdmNo);
                   student.put("system_number",getSysNo);
                   student.put("department",getDptmt);
               } catch (JSONException e) {
                   throw new RuntimeException(e);
               }

                //JSON object request creation
               JsonObjectRequest jsonObjReq=new JsonObjectRequest(
                       Request.Method.POST,
                       apiUrl,
                       student,
                       new Response.Listener<JSONObject>() {
                           @Override
                           public void onResponse(JSONObject response) {
                               Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                           }
                       }
               );

               //Request queue

               RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
               requestQueue.add(jsonObjReq);
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