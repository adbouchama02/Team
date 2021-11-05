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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText inputUsername, inputPassWord;
    DatabaseReference reference;
    List<User> loginCredentialsListAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputPassWord = findViewById(R.id.inputPassword);
        inputUsername = findViewById(R.id.inputUsername);

        loginCredentialsListAdmin = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Utilisateurs");

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
                    String id = reference.push().getKey();
                    User loginCredentials = new User(uName,uPassword,usertype);

                    reference.child(id).setValue(loginCredentials);
                }
            }
        });

        //BOUTON LOGIN
        Button LButton = (Button) findViewById(R.id.btnLogin);


        //LButton.setOnClickListener(new View.OnClickListener() {

        LButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userNameView = (EditText) findViewById(R.id.inputUsername);
                String username = userNameView.getText().toString().toLowerCase();
                EditText passwordView = (EditText) findViewById(R.id.inputPassword);
                String password = passwordView.getText().toString();
                Spinner roleView = (Spinner) findViewById(R.id.userTypeDropdown);
                String role = roleView.getSelectedItem().toString();


                for (int i = 0; i < loginCredentialsListAdmin.size(); i++){
                    String databasePassword = loginCredentialsListAdmin.get(i).getPassword();
                    String databaseUsername = loginCredentialsListAdmin.get(i).getUserName();
                    String databaseType = loginCredentialsListAdmin.get(i).getType();

                    if ((password.equals(databasePassword) && username.equals(databaseUsername)) && role.equals(databaseType)) {

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "The email and the password you entered do not exist ", Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loginCredentialsListAdmin.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User credentials = postSnapshot.getValue(User.class);
                    loginCredentialsListAdmin.add(credentials);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
