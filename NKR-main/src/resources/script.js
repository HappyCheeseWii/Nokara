const formatos = [
    { codigo: "FPJ-01", nombre: "Reporte de Iniciación", slug: "Reporte_Iniciacion" },
    { codigo: "FPJ-02", nombre: "Formato Único de Noticia Criminal", slug: "Noticia_Criminal" },
    { codigo: "FPJ-03", nombre: "Informe Ejecutivo", slug: "Informe_Ejecutivo" },
    { codigo: "FPJ-04", nombre: "Actuación del Primer Responsable", slug: "Primer_Responsable" },
    { codigo: "FPJ-05", nombre: "Captura en Flagrancia", slug: "Captura_Flagrancia" },
    { codigo: "FPJ-06", nombre: "Acta Derechos del Capturado", slug: "Derechos_Capturado" },
    { codigo: "FPJ-07", nombre: "Rótulo Cadena de Custodia", slug: "Rotulo_Cadena_Custodia" },
    { codigo: "FPJ-08", nombre: "Registro de Cadena de Custodia", slug: "Cadena_Custodia" },
    { codigo: "FPJ-09", nombre: "Acta de Inspección a Lugares", slug: "Inspeccion_Lugares" },
    { codigo: "FPJ-10", nombre: "Acta Inspección Técnica a Cadáver", slug: "Inspeccion_Cadaver" },
    { codigo: "FPJ-11", nombre: "Informe Investigador de Campo", slug: "Investigador_Campo" },
    { codigo: "FPJ-12", nombre: "Solicitud Análisis EMP - EF", slug: "Solicitud_Emp_Ef" },
    { codigo: "FPJ-13", nombre: "Informe Investigador de Laboratorio", slug: "Investigador_Laboratorio" },
    { codigo: "FPJ-14", nombre: "Formato de Entrevista", slug: "Entrevista" },
    { codigo: "FPJ-15", nombre: "Formato de Declaración Jurada", slug: "Declaracion_Jurada" },
    { codigo: "FPJ-16", nombre: "Bosquejo Topográfico", slug: "Bosquejo_Topografico" },
    { codigo: "FPJ-17", nombre: "Plano Topográfico", slug: "Plano_Topografico" },
    { codigo: "FPJ-18", nombre: "Ficha Técnica Fotográfica / Videográfica", slug: "Ficha_Fotografica" },
    { codigo: "FPJ-19", nombre: "Informe de Registro y Allanamiento", slug: "Informe_Allanamiento" },
    { codigo: "FPJ-20", nombre: "Acta de Allanamiento y Registro", slug: "Acta_Allanamiento" },
    { codigo: "FPJ-21", nombre: "Acta Reconocimiento Personas", slug: "Reconocimiento_Personas" },
    { codigo: "FPJ-22", nombre: "Acta Inspección a Vehículo", slug: "Inspeccion_Vehiculo" },
    { codigo: "FPJ-23", nombre: "Acta de Entrega de Bienes", slug: "Entrega_Bienes" },
    { codigo: "FPJ-24", nombre: "Formato Vigilancia y Seguimiento", slug: "Vigilancia_Seguimiento" },
    { codigo: "FPJ-25", nombre: "Inventario de Pertenencias", slug: "Inventario_Pertenencias" },
    { codigo: "FPJ-26", nombre: "Formato Fuentes no Formales", slug: "Fuentes_No_Formales" },
    { codigo: "FPJ-27", nombre: "Interrogatorio al Indiciado", slug: "Interrogatorio_Indiciado" },
    { codigo: "FPJ-28", nombre: "Acta de Consentimiento", slug: "Acta_Consentimiento" },
    { codigo: "FPJ-29", nombre: "Informe de Captura", slug: "Informe_Captura" },
    { codigo: "FPJ-30", nombre: "Formato Acta de Entrega", slug: "Acta_Entrega" },
    { codigo: "FPJ-31", nombre: "Derechos y Deberes de Víctimas", slug: "Derechos_Victimas" },
    { codigo: "FPJ-32", nombre: "Formato de Claqueta", slug: "Claqueta" },
    { codigo: "FPJ-33", nombre: "Actuaciones en Allanamiento", slug: "Actuaciones_Allanamiento" },
    { codigo: "FPJ-34", nombre: "Formato de Arraigo", slug: "Arraigo" },
    { codigo: "FPJ-35", nombre: "Formato de Citación", slug: "Citacion" },
    { codigo: "FPJ-36", nombre: "Solicitud Historia Clínica", slug: "Solicitud_Historia_Clinica" },
    { codigo: "FPJ-37", nombre: "Solicitud Antecedentes Judiciales", slug: "Solicitud_Antecedentes" },
    { codigo: "FPJ-38", nombre: "Solicitud Información Pública / Privada", slug: "Informacion_Publica" },
    { codigo: "FPJ-39", nombre: "Valoración Médico Legal", slug: "Valoracion_Medico_Legal" },
    { codigo: "FPJ-40", nombre: "Solicitud Defensoría", slug: "Solicitud_Defensoria" },
    { codigo: "FPJ-41", nombre: "Solicitud Registro Videográfico", slug: "Registro_Videografico" },
    { codigo: "FPJ-42", nombre: "Acta Destrucción/Devolución EMP-EF", slug: "Destruccion_Devolucion" },
    { codigo: "FPJ-43", nombre: "Lista de Chequeo Investigativa", slug: "Lista_Chequeo" }
];

document.addEventListener("DOMContentLoaded", () => {
    const contenedor = document.getElementById("contenedor-formatos");

    if (!contenedor) return;

    contenedor.innerHTML = "";

    formatos.forEach(item => {
        const tarjeta = document.createElement("div");
        tarjeta.classList.add("tarjeta-formato-fila");

        tarjeta.innerHTML = `
            <div class="info-formato-texto">
                <strong>${item.codigo}</strong> - ${item.nombre}
            </div>
            <div class="grupo-botones-descarga">
                <a href="/src/material/formatos/${item.slug}.docx" class="btn-descarga-item" download="${item.codigo}_${item.slug}.docx">Word</a>
                <a href="/src/material/formatos/${item.slug}.pdf" class="btn-descarga-item" download="${item.codigo}_${item.slug}.pdf">PDF</a>
            </div>
        `;

        contenedor.appendChild(tarjeta);
    });
});

class Usuario {
    constructor(correo, usuario, password) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }
    verDatos() {
        return `Correo: ${this.correo}, Usuario: ${this.usuario}, Password: ${this.password}`;
    }
}
const usuarios = [];

//para registro
const formularioRegistro = document.querySelector('#registro form');
formularioRegistro.addEventListener("submit", function (im) {

    im.preventDefault();

    const correo = formularioRegistro.querySelector('input[name="correo"]').value;
    const usuario = formularioRegistro.querySelector('input[name="user"]').value;
    const password = formularioRegistro.querySelector('input[name="password"]').value;

    const nUsuarios = new Usuario(correo, usuario, password);

    usuarios.push(nUsuarios);
    console.log(nUsuarios.verDatos());

    formularioRegistro.reset();
    window.location.hash = '#login';
});

//para inicio de sesion
const formularioLogin = document.querySelector('#login form');
formularioLogin.addEventListener("submit", function (im) {

    im.preventDefault();

    const correo = formularioLogin.querySelector('input[name="correo"]').value;
    const password = formularioLogin.querySelector('input[name="password"]').value;

    const eUsuario = usuarios.find(u =>
        (u.correo === correo || u.usuario === correo) && u.password === password
    );


    if (eUsuario) {
        console.log(eUsuario.verDatos());

        setTimeout(function () {
            window.location.href = "index.html";
        }, 1000); 

    } else {
        console.log("datos incorrectos")
    }
});

const dom = ["gmail.com", "hotmail.com", "outlook.com"];
const cCampos = document.querySelectorAll('input[type="email"]');

cCampos.forEach(function (correo) {
    const lst = document.createElement("div");
    lst.classList.add("ctns");

    correo.parentNode.insertBefore(lst, correo.nextSibling);

    correo.addEventListener("input", function () {
        lst.innerHTML = "";

        const txt = this.value;
        const pos = txt.indexOf("@");

        if (pos === -1) {
            return;
        }

        const usr = txt.substring(0, pos);
        const tCorreo = txt.substring(pos + 1);

        //mostrar la lista de opciones posibls
        dom.forEach(function (tCorreoA) {
            if (tCorreoA.startsWith(tCorreo)) {
                const opc = document.createElement("div");

                opc.textContent = usr + "@" + tCorreoA;

                //cargar la opcion selccionada
                opc.addEventListener("click", function () {
                    correo.value = usr + "@" + tCorreoA;
                    lst.innerHTML = '';
                });

                lst.appendChild(opc);
            }
        });
    });
});