/*
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
                <a href="/nkr/assets/formatos/${item.codigo}.docx" class="btn-descarga-item" download="${item.codigo}_${item.slug}.docx">Word</a>
                <a href="/nkr/assets/formatos/${item.codigo}.pdf" class="btn-descarga-item" download="${item.codigo}_${item.slug}.pdf">PDF</a>
            </div>
        `;

        contenedor.appendChild(tarjeta);
    });
});
*/

/*class Usuario {
    constructor(correo, usuario, password) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }
    verDatos() {
        return `Correo: ${this.correo}, Usuario: ${this.usuario}, Password: ${this.password}`;
    }
}
const usuarios = [];*/

//para registro
/*
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
});*/


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