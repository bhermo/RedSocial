package com.example.redsocial.Model;

import java.util.Map;

public class MessageSend extends Message {
    private Map hora;

    public MessageSend() {
    }

    public MessageSend(Map hora) {
        this.hora = hora;
    }

    public MessageSend(String mensaje, String nombre, String fotoPerfil, String type_mensaje, Map hora) {
        super(mensaje, nombre, fotoPerfil, type_mensaje);
        this.hora = hora;
    }

    public MessageSend(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, Map hora) {
        super(mensaje, urlFoto, nombre, fotoPerfil, type_mensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
