package com.example.chargingstation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText carNumin;
    EditText carType;
    EditText phoneNum;
    EditText password;
    Button sSignUpbtn;
    Button sCancelbtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        carNumin = (EditText)findViewById(R.id.car_Numin);
        carType = (EditText)findViewById(R.id.car_ty);
        phoneNum = (EditText)findViewById(R.id.pho_N);
        password = (EditText)findViewById(R.id.passwordText);
        sSignUpbtn = (Button)findViewById(R.id.btn_sign);
        sCancelbtn = (Button)findViewById(R.id.btn_cancel_sign);

        sSignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new user with a first and last name
//                UpDate 회원추가
                Map<String, Object> user = new HashMap<>();
                user.put("userid",carNumin.getText().toString());
                user.put("kind", carType.getText().toString());
                user.put("phoneNumer",phoneNum.getText().toString());
                user.put("password", password.getText().toString());
                db.collection("user").document().set(user);
                finish();
            }
        });
        sCancelbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
