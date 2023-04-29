package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.DatabaseHelper;

public class inscriptionActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword,editTextFirstName,editTextLastName;
    private Button buttonInscription;

    // Instancier la classe MyDBHandler
    private DatabaseHelper dbHandler = new DatabaseHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Récupérer les éléments d'interface utilisateur
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextFirstName=findViewById(R.id.edit_text_prenom);
        editTextLastName=findViewById(R.id.edit_text_nom);
        buttonInscription = findViewById(R.id.button_create_account);

        // Ajouter un écouteur d'événement pour le bouton d'inscription
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'adresse email et le mot de passe
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String firstname=editTextFirstName.getText().toString();
                String lastname= editTextLastName.getText().toString();



                // Ajouter l'utilisateur dans la base de données
                dbHandler.addUser(email, password,firstname,lastname);
                // Enregistrer les informations de l'utilisateur dans la base de données
// ...

// Définir les valeurs de nom et prénom dans l'Intent
                Intent intent1 = new Intent(inscriptionActivity.this, HomeActivity.class);
                intent1.putExtra("nom_utilisateur", firstname);
                intent1.putExtra("prenom_utilisateur", lastname);
                startActivity(intent1);


                // Retourner à la page MainActivity
                Intent intent = new Intent(inscriptionActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
