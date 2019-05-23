package com.example.redsocial.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redsocial.Model.Message;
import com.example.redsocial.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<HolderMessagesAdapter> {

    private List<Message> listMessage = new ArrayList<>();
    private Context c;

    public MessagesAdapter(Context c){
        this.c=c;

    }

    public void addMessage(Message m){
        listMessage.add(m);
        notifyItemInserted(listMessage.size());
    }


    @Override
    public HolderMessagesAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,viewGroup,false);
        return new HolderMessagesAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessagesAdapter holderMessagesAdapter, int i) {
        holderMessagesAdapter.getNombre().setText(listMessage.get(i).getNombre());
        holderMessagesAdapter.getMensaje().setText(listMessage.get(i).getMensaje());
        holderMessagesAdapter.getHora().setText(listMessage.get(i).getHora());
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
}
