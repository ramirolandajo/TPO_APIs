package com.uade.api.models;

public enum TipoUsuario {
    ADMIN, DUENIO, INQUILINO;
    public static String converToString(TipoUsuario user) {
        switch (user) {
            case ADMIN -> {
                return "ADMIN";
            }
            case DUENIO -> {
                return "DUENIO";
            }
            case INQUILINO -> {
                return "INQUILINO";
            }
            default -> {
                assert (false);
                return null;
            }
        }
    }
}
