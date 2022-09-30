class Login {
    constructor(form, fields) {
        this.form = form;
        this.fields = fields;
        this.validateonSubmit();
    }

    validateonSubmit() {
        let self = this;
        this.form.addEventListener("submit", (e) => {
            e.preventDefault();
            var error = 0;
            self.fields.forEach((field) => {
                const input = document.querySelector(`#${field}`);
                if (self.validateFields(input) == false) {
                    error++;
                }
            });
            if (error == 0) { // if no error
                this.authUser();
                //localStorage.setItem("auth", 1);
                //this.form.submit();
            }
        });
    }

    validateFields(field) {
        if (field.value.trim() === "") {
            this.setStatus(
                field,
                `${field.previousElementSibling.innerText} cannot be blank`,
                "error"
            );
            return false;
        } else {
            if (field.type == "password") {
                if (field.value.length < 8) {
                    this.setStatus(
                        field,
                        `${field.previousElementSibling.innerText} must be at least 8 characters`,
                        "error"
                    );
                    return false;
                } else {
                    this.setStatus(field, null, "success");
                    return true;
                }
            } else {
                this.setStatus(field, null, "success");
                return true;
            }
        }
    }

    setStatus(field, message, status) {
        const errorMessage = field.parentElement.querySelector(".error-message");
        if (status == "success") {
            if (errorMessage) {
                errorMessage.innerText = "";
            }
            field.classList.remove("input-error");
        }
        if (status == "error") {
            errorMessage.innerText = message;
            field.classList.add("input-error");
        }
    }

    authUser() {
        let userName = document.getElementById("Usuario");
        let password = document.getElementById("Contrasena");

        let formdata = new FormData();
        formdata.append("email", userName);
        formdata.append("password", password);

        var requestOptions = {
            method: 'POST',
            body: formdata,
            redirect: 'follow'
        };

        fetch("./ServletUserLogin", requestOptions)
            .then(response => response.text())
            .then(result => {
                let parsedResult = JSON.parse(result);

                if (parsedResult["exists"] === true) {
                    let hashedEmail = await hashEmail(parsedResult["user"]["email"]);
                    // set location (home.html)
                    document.location.href += "home.html?user=" + hashedEmail;
                } else {
                    // show error
                }

            })
            .catch(error => console.log('error', error));
    }

}

const form = document.querySelector(".loginForm");
if (form) {
    const fields = ["username", "password"];
    const validator = new Login(form, fields);
}

//-------------------------

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("form-login").onsubmit = authUser();
}, false);


function authUser() {
    let userName = document.getElementById("Usuario");
    let password = document.getElementById("Contrasena");

    let formdata = new FormData();
    formdata.append("email", userName);
    formdata.append("password", password);

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("./ServletUserLogin", requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);

            if (parsedResult["exists"] === true) {
                let hashedEmail = await hashEmail(parsedResult["user"]["email"]);
                // set location (home.html)
                //document.location.href = "home.html?user=" + hashedEmail;
            } else {
                // show error
            }

        })
        .catch(error => console.log('error', error));
}


async function hashEmail(email) {
    const encoder = new TextEncoder();
    const data = encoder.encode(email);
    const hash = await crypto.subtle.digest('SHA-256', data);
    return hash;
}

