export function crearEdificio(valor){

    if(valor == 'edificio'){

        let inputEdificio = document.getElementById("direccion-edificio").value;
        alert("Desde createBuilding " + inputEdificio);
                
        return true
    }else if(valor == 'unidad'){

        let piso        = document.getElementById("piso").value;
        let numero      = document.getElementById("numero").value;
        let idDuenio    = document.getElementById("idDuenio").value;
        let idInquilino = document.getElementById("idInquilino").value;
        let idEdificio  = document.getElementById("idEdificio").value;

        alert(piso);
        alert(numero);
        alert(idDuenio);
        alert(idInquilino);
        alert(idEdificio);


        return true;
    }else if(valor == 'espacioComun'){

        let direccionEdificio = document.getElementById("idEdificio").value;
        let piso              = document.getElementById("piso").value;
        let descripcion       = document.getElementById("descripcion").value;

        alert(direccionEdificio);
        alert(piso);
        alert(descripcion);

        return true;
    }else{
        return false;
    }
}