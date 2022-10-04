let params = new URLSearchParams(document.location.search);
let userId = params.get("user");


let form = document.getElementById("createServiceForm");
form.onsubmit = function (event) {
    event.preventDefault();
    createService();
}


function createService() {
    let formdata = new FormData();
    formdata.append("userId", userId);
    formdata.append("serviceName", document.getElementById("service_name").value);
    formdata.append("serviceDescription", document.getElementById("serviceDesc").value);
    formdata.append("serviceCategory", document.getElementById("category").value);
    formdata.append("servicePhoneNumber", document.getElementById("phone").value);
    formdata.append("serviceCity", document.getElementById("city").value);
    formdata.append("serviceAddress", document.getElementById("address").value);
    formdata.append("serviceValue", document.getElementById("valor").value);

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/servi2/ServletCreateService", requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);

            if (parsedResult.inserted) {
                alert("Servicio creado!");
                history.back();
            } else {
                alert("Lo sentimos, ocurri&oacute; un error");
            }
        })
        .catch(error => console.log('error', error));
}