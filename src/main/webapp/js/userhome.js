let servicesContainer = document.getElementById("serivesContainer");


function loadServices() {
    // Fetch
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch("http://localhost:8080/servi2/ServletListServices", requestOptions)
        .then(response => response.text())
        .then(result => {
            let parsedResult = JSON.parse(result);

            if (parsedResult["services"].length > 0) {
                parsedResult["services"].forEach(service => {
                    let card = `
                    <div class="col-sm-6 mb-4 mt-4">
                    <div class="card">
                        <div class="card-body">
    
                            <h5 class="card-titles form-check-inline">${service.name}</h5>
                            <p class="card-text"> ${service.serviceProvider.name + " " + service.serviceProvider.lastname} - ${service.city}
                                <b>
                                    <p class="card-text"> $ ${service.value.toLocaleString('es-CO')}
                                    </p>
                                </b>
                                <a href="servi.html?service=${service.id}" class="btn btn-primary" serviceId=${service.id}>Ver m&aacute;s</a>
                        </div>
                    </div>
                </div>
                    `;
                    // innerHTML no es recomendado!
                    servicesContainer.innerHTML += card;
                });
            }

        })
        .catch(error => console.log('error', error));
}

loadServices();