// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarDatos();
  $('#usuario').DataTable();
  actualizarEmailUsuarios();
});

function actualizarEmailUsuarios(){
    document.getElementById('txt-email-usuarios').outerHTML = localStorage.email;
    }

async function cargarDatos(){


  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();


    let listadoHtml='';
    for(let usuario of usuarios){
    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let botonEditar = '<a href="#" onclick="editarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fa-solid fa-user-pen"></i></a>';


    let usuarioHTML='<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td><td>'+botonEliminar+'</td></tr>'
    listadoHtml += usuarioHTML;
    }
document.querySelector('#usuarios tbody').outerHTML=listadoHtml;

}

function getHeaders(){

    return{
             'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization': localStorage.token
                                   }

}

async function eliminarUsuario(id){
    if (!confirm('Desea eliminar el usuario?')){
    return;
    }

 const request = await fetch('api/usuarios/' +id, {
    method: 'DELETE',
    headers: getHeaders()
  });
    location.reload()
}

async function editarUsuario(){
    let datos={};
    datos.nombre= document.getElementById('txtName').value;
    datos.apellido= document.getElementById('txtLastName').value;
    datos.email= document.getElementById('txtEmail').value;
    datos.password= document.getElementById('txtPassword').value;
    datos.telefono = document.getElementById('txtPhone').value;

    let repetirPassword = document.getElementById('txtRepeatPassword').value;

    if (repetirPassword != datos.password){
        alert('password and repeat password are diferents');
        return;
    }

  const request = await fetch('api/usuarios', {
    method: 'PUT',
    headers: getHeaders(),
    body: JSON.stringify(datos)
  });
  const usuarios = await request.json();

    alert("your information changed correct");
    window.location.href = "login.html"

   }
