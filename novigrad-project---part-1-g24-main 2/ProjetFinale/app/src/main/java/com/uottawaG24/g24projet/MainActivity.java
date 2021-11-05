package com.uottawaG24.g24projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText inputUsername, inputPassWord;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputPassWord = findViewById(R.id.inputPassword);
        inputUsername = findViewById(R.id.inputUsername);

        //REFERENCE TO DATABASE!!
        reference = FirebaseDatabase.getInstance().getReference("credentials");

        //Ajoute les 3 roles dans le spinner de l'interface
        Spinner dropdown = findViewById(R.id.userTypeDropdown);
        String[] roles = new String[]{"Client", "Employee", "Administrator"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        dropdown.setAdapter(adapter);

        //BOUTON REGISTER
        Button RButton = (Button) findViewById(R.id.btnRegister);
        RButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uName = inputUsername.getText().toString().toLowerCase();
                String uPassword = inputPassWord.getText().toString();
                String usertype = dropdown.getSelectedItem().toString();

                if (usertype.equals("Administrator")) {
                    Toast.makeText(MainActivity.this, "Vous ne pouvez pas créer de compte Admin", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(uName, uPassword, usertype);
                    reference.child("Utilisateurs").child(usertype).child(uName).setValue(user)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(MainActivity.this, "La registration a été faite avec succes", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }
                            });

                    User user1 = new User(uName, uPassword, usertype);
                    reference.child("Utilisateurs").child(usertype).child(uName).setValue(user)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(MainActivity.this, "La registration a été faite avec succes", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });

        //BOUTON LOGIN
        Button LButton = (Button) findViewById(R.id.btnLogin);

        //LButton.setOnClickListener(new View.OnClickListener() {

        LButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent registeredIntent = new Intent(getApplicationContext(), MainActivity.class);
                EditText userNameView = (EditText) findViewById(R.id.inputUsername);
                String username = userNameView.getText().toString();
                EditText passwordView = (EditText) findViewById(R.id.inputPassword);
                String password = passwordView.getText().toString();
                Spinner roleView = (Spinner) findViewById(R.id.userTypeDropdown);
                String role = roleView.getSelectedItem().toString();






                if (username == "admin" && password == "admin" && role.equals("Administrator")) {
                    Toast.makeText(MainActivity.this, "La login admin a été fait avec succes", Toast.LENGTH_SHORT).show();
                }
                if (username.equals("") && password.equals("")) {
                    Toast.makeText(MainActivity.this, "SVP entrer des valeur possibles", Toast.LENGTH_SHORT).show();
                }
                if (username == "client" && password == "client" && role.equals("Client")) {
                    Toast.makeText(MainActivity.this, "Le login Client a ete fait avec succes", Toast.LENGTH_SHORT).show();
                }
                if (username == "employee" && password == "employee" && role == "Employee") {
                    Toast.makeText(MainActivity.this, "Le login Employee a ete fait avec succes", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Svp entrer un nouveau username ou password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



        private void switchActivity () {
            Intent switchIntent = new Intent(this, MainActivity2.class);
            startActivity(switchIntent);

        }
    }
