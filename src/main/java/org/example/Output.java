package org.example;

import java.util.List;

public class Output {
    private List<Usuario> usuarioList;

    public Output() {
    }

    public Output(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
}
