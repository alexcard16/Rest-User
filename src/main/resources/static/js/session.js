// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function iniciarSession(){
    let datos={};
    datos.email= document.getElementById('txtInputEmail').value;
    datos.password= document.getElementById('txtInputPassword').value;

  const request = await fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const respuesta = await request.text();

  if(respuesta != "fail"){
    localStorage.token = respuesta;
    localStorage.email = datos.email;
    window.location.href = 'usuarios.html'
  }else{
    alert("password or email invalid")
  }

   }
