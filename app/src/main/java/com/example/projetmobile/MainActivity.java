package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.projetmobile.DatabaseHelper;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.R;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mSignInButton;

    // Déclaration de la base de données
    private DatabaseHelper dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des vues de la vue principale
        mEmailEditText = findViewById(R.id.edit_text_email);
        mPasswordEditText = findViewById(R.id.edit_text_password);
        mSignInButton = findViewById(R.id.button_login);

        // Initialisation de la base de données
        dbHandler = new DatabaseHelper(this, null, null, 2);

        // Ajout d'un listener sur le bouton de connexion
        Button btnCreateAccount = findViewById(R.id.button_create_account);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inscriptionActivity.class);

                startActivity(intent);
            }
        });
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                // Vérification si le compte existe dans la base de données
                if (dbHandler.getUser(email, password)) {
                    // Si les informations sont correctes, ouvrir l'interface d'accueil
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("email",email);

                    startActivity(intent);
                } else {
                    // Si les informations sont incorrectes, afficher un message d'erreur
                    Toast.makeText(MainActivity.this, "Adresse courriel ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
