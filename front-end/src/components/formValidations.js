export function validarUsuario(usuario) {
    if (usuario.length < 3) {
      return 'El nombre de usuario debe tener mas de 2 caracteres';
    }
    return null; 
  }
  
  export function validarContrasenia(contrasenia) {
    if (contrasenia.length <= 5) {
      return 'La contraseña debe tener al menos 5 caracteres y un numero.';
    }    

    // /\d/ valida que haya un digito en el parametro contrasenia
    if (!/\d/.test(contrasenia)) {
      return 'La contraseña debe contener al menos un número.';
    }

    return null; 
  }