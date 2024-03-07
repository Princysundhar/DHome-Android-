package com.example.dhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class change_password extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;
    String url;
    String password_pattern="[A-Za-z0-9]{3,8}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        e1 = findViewById(R.id.editTextTextPassword3);
        e2 = findViewById(R.id.editTextTextPassword4);
        e3 = findViewById(R.id.editTextTextPassword5);
        b1 = findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String crp = e1.getText().toString();
                String new_password = e2.getText().toString();
                String confirm_password = e3.getText().toString();
                int flag=0;
                if(crp.equalsIgnoreCase("")){
                    e1.setError("Null");
                    flag++;
                }
                if(!new_password.matches(password_pattern)){
                    e2.setError("Enter password with valid pattern");
                    flag++;
                }
//                if(new_password != confirm_password){
//                    e3.setError("confirm your password");
//                    flag++;
//                }
                if(flag==0) {
                    sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sh.getString("ipaddress", "");
                    url = sh.getString("url", "") + "android_change_password";

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    // response
                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                            Toast.makeText(change_password.this, "Updated", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Login.class);
                                            startActivity(i);

                                        } else if (jsonObj.getString("status").equalsIgnoreCase("No")) {
                                            Toast.makeText(change_password.this, "Already exist", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);

                                        } else if (jsonObj.getString("status").equalsIgnoreCase("mismatch")) {
                                            Toast.makeText(change_password.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);

                                        } else if (jsonObj.getString("status").equalsIgnoreCase("error")) {
                                            Toast.makeText(change_password.this, "Password error", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);

                                        }
                                        // }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            Map<String, String> params = new HashMap<String, String>();

//                        String id=sh.getString("uid","");
                            params.put("lid", sh.getString("lid", ""));
                            params.put("crp", crp);
                            params.put("new_password", new_password);
                            params.put("confirm_password", confirm_password);
//                params.put("mac",maclis);

                            return params;
                        }
                    };

                    int MY_SOCKET_TIMEOUT_MS = 100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);
                }


            }
        });
    }
}