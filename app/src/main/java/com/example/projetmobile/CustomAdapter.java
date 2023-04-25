package com.example.projetmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Conversation> {

    private Context mContext;
    private List<Conversation> mConversations;

    public CustomAdapter(Context context, List<Conversation> conversations) {
        super(context, 0, conversations);
        mContext = context;
        mConversations = conversations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_conversation, parent, false);
        }

        Conversation currentConversation = mConversations.get(position);

        TextView nameTextView = listItem.findViewById(R.id.conversationName);
        nameTextView.setText(currentConversation.getName());

        TextView messageTextView = listItem.findViewById(R.id.conversationMessage);
        messageTextView.setText(currentConversation.getMessage());

        TextView timeTextView = listItem.findViewById(R.id.conversationTime);

        return listItem;
    }
}

