package com.uade.api.models;

public enum TipoUsuario {
    ADMIN, DUENIO, INQUILINO;

    public static TipoUsuario convertStringToEnum(Object s) throws Exception {
        String tipoUsuario = (String) s;
        switch (tipoUsuario) {
            case "ADMIN":
                return TipoUsuario.ADMIN;
            case "DUENIO":
                return TipoUsuario.DUENIO;
            case "INQUILINO":
                return TipoUsuario.INQUILINO;
            default:
                throw new Exception("No matches for string " + s);
        }
    }
}
