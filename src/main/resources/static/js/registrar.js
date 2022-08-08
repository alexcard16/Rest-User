// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function registrarUsuario(){
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
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const usuarios = await request.json();

    alert("Se ha registrado correctamente");
    window.location.href = "login.html"

   }
