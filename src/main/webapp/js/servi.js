let serviceTitle = document.getElementById("serviceTitle");
let serviceProviderName = document.getElementById("serviceProviderName");
let serviceDescription = document.getElementById("serviceDescription");
let serviceValue = document.getElementById("serviceValue");





let params = new URLSearchParams(document.location.search);
let serviceId = params.get("service");

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

                document.getElementById("serviceTitle").textContent = service.name;
                document.getElementById("serviceProviderName").textContent = service.serviceProvider.name + " " + service.serviceProvider.lastname;
                document.getElementById("serviceDescription").textContent = service.description;
                document.getElementById("serviceValue").textContent = "$" + service.value.toLocaleString('es-CO');
            }

        })
        .catch(error => console.log('error', error));
}

loadServiceData();