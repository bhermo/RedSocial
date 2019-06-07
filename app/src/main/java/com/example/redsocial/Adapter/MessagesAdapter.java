package com.example.redsocial.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.redsocial.Model.Message;
import com.example.redsocial.Model.MessageReceive;
import com.example.redsocial.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class MessagesAdapter extends RecyclerView.Adapter<HolderMessagesAdapter> {

    private List<MessageReceive> listMessage = new ArrayList<>();
    private Context c;

    public MessagesAdapter(Context c){
        this.c=c;

    }

    public void addMessage(MessageReceive m){
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
        if (listMessage.get(i).getType_mensaje().equals("2")){
            holderMessagesAdapter.getFotoMensaje().setVisibility(View.VISIBLE);
            holderMessagesAdapter.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(listMessage.get(i).getUrlFoto()).into(holderMessagesAdapter.getFotoMensaje());
        }else if (listMessage.get(i).getType_mensaje().equals("1")){
            holderMessagesAdapter.getFotoMensaje().setVisibility(View.GONE);
            holderMessagesAdapter.getMensaje().setVisibility(View.VISIBLE);
        }
        if (listMessage.get(i).getFotoPerfil().isEmpty()){
            holderMessagesAdapter.getFotoMensajePerfil().setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(c).load(listMessage.get(i).getFotoPerfil()).into(holderMessagesAdapter.getFotoMensajePerfil());
        }

        Long codigoHora = listMessage.get(i).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holderMessagesAdapter.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
}
