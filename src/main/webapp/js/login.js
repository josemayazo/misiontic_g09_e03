let form = document.getElementById("loginForm");
form.onsubmit = function (event) {
    event.preventDefault();
    authUser(event);
}

function authUser(event) {
    // event.preventDefault();
    console.log("Entró a authUser");


    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    // if (isAnEmail(username) & username !== "" & password !== "") {


    let formdata = new FormData();
    formdata.append("email", username);
    formdata.append("password", password);

    let requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/servi2/ServletUserLogin", requestOptions)
        .then(response => response.text())
        .then(result => {
            // check if user creds are ok!
            let parsedResult = JSON.parse(result);
            console.log(parsedResult);
            if (parsedResult) {
                document.getElementById("login-error").classList.add("d-none");
                if (parsedResult["exists"]) {
                    // Redirect user to
                    if (parsedResult.user.type === "A") {
                        document.location.href = "home_prestador.html?user=" + parsedResult.user.id;
                    } else {
                        document.location.href = "userhome.html?user=" + parsedResult.user.id;
                    }
                } else {
                    document.getElementById("login-error").classList.remove("d-none");
                    document.getElementById("login-error").value = "Email o contraseña incorrecta";
                }
            }
        })
        .catch(error => {
            document.getElementById("login-error").classList.remove("d-none");
            document.getElementById("login-error").value = "Ocurrió un error";
            // document.getElementsByClassName("error-message").classList.remove("d-none");
            // document.getElementsByClassName("error-message").value = "Ups! Ocurri\oacute; un error, lo sentimos, intente nuevamente.";
        });
    // } else {
    //     Document.getElementsByClassName("error-message").classList.remove("d-none");
    //     document.getElementsByClassName("error-message").value = "Ingrese un correo electr\oacute;nico v\aacutelido";
    // }
}

function isAnEmail(username) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(username)) {
        return true;
    }
    return false;
}