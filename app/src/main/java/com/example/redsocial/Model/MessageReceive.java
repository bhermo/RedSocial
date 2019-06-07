package com.example.redsocial.Model;

import android.util.Log;

public class MessageReceive extends Message {
    private Long hora;

    public MessageReceive() {
    }

    public MessageReceive(Long hora) {
        this.hora = hora;
    }

    public MessageReceive(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, Long hora) {
        super(mensaje, urlFoto, nombre, fotoPerfil, type_mensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
