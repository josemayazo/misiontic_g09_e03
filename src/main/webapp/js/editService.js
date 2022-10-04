let params = new URLSearchParams(document.location.search);
let serviceId = params.get("service");

let buttonSave = document.getElementById("saveChanges");
let buttonDelete = document.getElementById("deleteService");

buttonSave.onclick = function (event) {
    event.preventDefault();
    sendChanges();
}

buttonDelete.onclick = function (event) {
    event.preventDefault();
    deleteService();
}


function getServiceById(services) {
    services.forEach(service => {
        if (service.id == serviceId) {
            return service;
        }
    });
}

function loadServiceData() {

    // Fetch
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/servi2/ServletListServices`, requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);

            if (parsedResult["services"].length > 0) {
                let service = parsedResult["services"].find((serv) => serv.id == serviceId);
                console.log(service);

                document.getElementById("service_name").value = service.name;
                // document.getElementById("serviceProviderName").value = service.serviceProvider.name + " " + service.serviceProvider.lastname;
                document.getElementById("service_desc").value = service.description;
                document.getElementById("valor").value = service.value;
                document.getElementById("city").value = service.city;

            }
        })
        .catch(error => console.log('error', error));
}


function sendChanges() {
    let formdata = new FormData();
    formdata.append("serviceId", serviceId);
    formdata.append("serviceName", document.getElementById("service_name").value);
    formdata.append("serviceDescription", document.getElementById("service_desc").value);
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

    fetch("http://localhost:8080/servi2/ServletEditService", requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);
            if (parsedResult["updated"]) {
                let editResultConfirm = confirm("Servicio actualizado");
                if (editResultConfirm) {
                    history.back();
                }
            }
        })
        .catch(error => console.log('error', error));
}

function deleteService() {
    let requestOptions = {
        method: 'POST',
        redirect: 'follow'
    }

    fetch(`http://localhost:8080/servi2/ServletDeleteService?serviceId=${serviceId}`, requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);
            console.log(parsedResult);
            if (parsedResult.deleted) {
                let editResultConfirm = confirm("Servicio Eliminado");
                if (editResultConfirm) {
                    history.back();
                }
            }

        })
        .catch(error => console.log('error', error));
}


loadServiceData();