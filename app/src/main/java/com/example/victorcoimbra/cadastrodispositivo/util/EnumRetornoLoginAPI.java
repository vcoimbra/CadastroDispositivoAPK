package com.example.victorcoimbra.cadastrodispositivo.util;

/**
 * Created by Victor Coimbra on 02/03/2018.
 */

public enum EnumRetornoLoginAPI {

    USUARIO_AUTENTICADO(0),
    USUARIO_NAO_AUTORIZADO(-1),
    USUARIO_CADASTRADO(1),
    USUARIO_EXISTENTE(-2);

    private EnumRetornoLoginAPI(final Integer val) {
        this.valor = val;
    }

    private Integer valor;

    public Integer getValor() {
        return valor;
    }
}