package com.example.projetmobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {



    // Nom de la base de données
    private static final String DATABASE_NAME = "userss.db";

    // Version de la base de données
    private static final int DATABASE_VERSION = 2;

    // Nom de la table des utilisateurs
    public static final String TABLE_NAME = "userss";

    // Noms des colonnes de la table des utilisateurs
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";

    // Requête SQL de création de la table des utilisateurs
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT , " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT)";

    public DatabaseHelper(Context context, Object o, Object o1, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cette méthode est appelée si la version de la base de données change.
        // On peut ajouter du code ici pour mettre à jour la structure de la base de données.
    }

    public void addUser(String email, String password, String firstName, String lastName) {
        // Ouvrir la base de données en écriture
        SQLiteDatabase db = this.getWritableDatabase();

        // Créer un nouvel enregistrement avec les données de l'utilisateur
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);

        // Insérer l'enregistrement dans la base de données
        db.insert(TABLE_NAME, null, values);

        // Fermer la base de données
        db.close();
    }
    public String getUsername(String email) {
        // Ouvrir la base de données en lecture
        SQLiteDatabase db = this.getReadableDatabase();

        // Sélectionner l'utilisateur avec l'email fourni
        String[] columns = {COLUMN_FIRST_NAME};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        String limit = "1";
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);

        // Récupérer le prénom de l'utilisateur s'il existe
        String firstName = null;
        if (cursor.moveToFirst()) {
            firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        return firstName;
    }


    public boolean getUser(String email, String password) {
        // Ouvrir la base de données en lecture
        SQLiteDatabase db = this.getReadableDatabase();

        // Sélectionner l'utilisateur avec l'email et le mot de passe fournis
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        String limit = "1";
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);

        // Vérifier si l'utilisateur existe
        boolean exists = (cursor.getCount() > 0);

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        return exists;
    }


}
