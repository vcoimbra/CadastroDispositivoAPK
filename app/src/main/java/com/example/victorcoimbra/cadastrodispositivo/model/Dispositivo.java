package com.example.victorcoimbra.cadastrodispositivo.model;

/**
 * Created by Victor Coimbra on 03/03/2018.
 */

public class Dispositivo {

    private String id;
    private String uuid;
    private String modelo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
