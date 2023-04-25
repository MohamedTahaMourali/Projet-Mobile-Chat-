package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView mConversationListView;
    private CustomAdapter mConversationListAdapter;
    private List<Conversation> mConversationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Récupération de la liste des conversations depuis une source de données (par exemple une base de données)
        mConversationList = getConversationList();

        // Récupération de la vue de la liste des conversations
        mConversationListView = findViewById(R.id.conversationsList);

        // Création de quelques conversations


        // Création de l'adaptateur pour la liste des conversations
        mConversationListAdapter = new CustomAdapter(this, mConversationList);

        // Configuration de la vue de la liste des conversations avec l'adaptateur
        mConversationListView.setAdapter(mConversationListAdapter);

        // Ajout d'un listener sur la vue de la liste des conversations pour ouvrir l'interface de discussion correspondante
        mConversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversation conversation = mConversationList.get(position);
                Intent intent = new Intent(HomeActivity.this, ConversationActivity.class);
                intent.putExtra("conversation", conversation.toString());
                startActivity(intent);
            }
        });
    }

    private List<Conversation> getConversationList() {
        // TODO : récupérer la liste des conversations depuis une source de données
        List<Conversation> conversationList = new ArrayList<>();

        // Exemple de création de conversation
        Conversation conversation1 = new Conversation("John", "Salut, ça va ?");

        conversation1.setLastMessage("Dernier message de la conversation 1");

        conversationList.add(conversation1);

        Conversation conversation2 = new Conversation("Mary", "Hey, comment ça va ?");
        conversation2.setLastMessage("Dernier message de la conversation 2");

        conversationList.add(conversation2);

        return conversationList;
    }
}

