package com.example.chargingstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    Button signbtn;
    EditText id;
    EditText password;
    int loginCnt = 0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText)findViewById(R.id.userIdText);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        signbtn = (Button)findViewById(R.id.signbtn);
        password = (EditText)findViewById(R.id.userPasswordText);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("user").whereEqualTo("userid",id.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            loginCnt++;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String str = document.getData().toString();
                                String[] arr = str.split(",");
                                Log.d("MainActivity", document.getId() + " => " + document.getData());
                                Log.d("MainActivity","split1=>"+arr[0].substring(10)); //비밀번호
                                Log.d("MainActivity","split12=>"+arr[3].substring(8)); //아이디
                                String uid = arr[3].substring(8);
                                String upass = arr[0].substring(10);
                                String edid = id.getText().toString()+"}";
                                String edpass = password.getText().toString();
                                Log.d("MainActivity","uid = "+uid+"upass = "+upass+"edid = "+edid+"edpass = "+edpass);
                                if(uid.equals(edid) && upass.equals(edpass)) {
                                    Log.d("MainActivity","성공!!");
                                    Toast.makeText(MainActivity.this, "로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                                    startActivity(intent);
                                } else {
                                    Log.d("MainActivity","실패!!");
                                    Toast.makeText(MainActivity.this, "비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
                                }

                            }
                        } else {
                            Log.d("MainActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
                if(loginCnt == 0) {
                    Toast.makeText(MainActivity.this, "아이디가 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
//
            }
        });

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });




    }
}
