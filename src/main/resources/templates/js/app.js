//function to load products
var loadProducts = function() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/products');
    xhr.send();
    xhr.onloadend = function () {
        var jsonResponse = JSON.parse(xhr.responseText);
        document.getElementById("productsTableBody").innerHTML = ""; //Clear previous rows if there are rows already.
        if(jsonResponse && jsonResponse.length && jsonResponse.length > 0) {
            //Found products data
            for(var i = 0; i < jsonResponse.length; i++) {
               document.getElementById("productsTableBody").insertAdjacentHTML("beforeend", `
                    <tr>
                        <td>`+jsonResponse[i]["id"]+`</td>
                        <td>`+jsonResponse[i]["title"]+`</td>
                        <td>`+jsonResponse[i]["company"]+`</td>
                        <td>`+jsonResponse[i]["quantity"]+`</td>
                        <td><a href="#" onclick="editProduct(this, `+jsonResponse[i]["id"]+`);">Edit</a>&nbsp;&nbsp;<a href="#" onclick="deleteProduct(`+jsonResponse[i]["id"]+`);">Delete</a></td>
                    </tr>
               `);
            }
        }
    };
};

var deleteProduct = function(id) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', '/api/products/'+id);
    xhr.send();

    xhr.onloadend = function(){
        loadProducts();
    }
}

var editProduct = function(self, id) {
    if(self.innerHTML != "Save"){
        //Algorithm in mind,
        //Find table data siblings, and then replace them with inputs, so we can be able to edit them.
        var row = self.parentNode.parentNode;
        var sibling = row.firstChild.nextSibling;
        self.innerHTML = "Save";
        while (sibling) {
            sibling = sibling.nextSibling;
            if (sibling && sibling.nodeType && sibling.nodeType == 1 && sibling != self.parentNode) {
                sibling.innerHTML = "<input class='savedVariables' type='text' value='"+sibling.innerHTML+"' />";
            }
        }
    }else{
        var variables = document.querySelectorAll('.savedVariables');

        var data = {"id":id, "title": variables[0].value, "company": variables[1].value, "quantity": variables[2].value};

        var xhr = new XMLHttpRequest();
        xhr.open('PUT', '/api/products/'+id, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));

        xhr.onloadend = function () {
            loadProducts();
        };

    }
}


//Load products even before window.onload
loadProducts();

window.onload = function() {
    //Add form handler.
    var form = document.getElementById("addProduct");
    form.onsubmit = function (e) {
        e.preventDefault();

        var data = {};
        for (var i = 0; i < form.length; i++) {
            var input = form[i];
            if (input.name) {
              data[input.name] = input.value;
            }
        }

        var xhr = new XMLHttpRequest();
        xhr.open(form.method, form.action, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));

        xhr.onloadend = function () {
            var jsonResponse = JSON.parse(xhr.responseText);

            if(jsonResponse)
                loadProducts();
            //console.log(jsonResponse);
        };
    };

    //On export CSV click
    document.getElementById("exportProducts").onclick = function() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/api/products');
        xhr.send();
        xhr.onloadend = function () {
            var jsonResponse = JSON.parse(xhr.responseText);
            if(jsonResponse && jsonResponse.length && jsonResponse.length > 0) {
                var csvFormat = "data:text/csv;charset=utf-8,id,title,company,quantity\r\n";

                jsonResponse.forEach(function(rowData) {
                    //let row = rowData.join(",");
                    var row = "";
                    for (const [key, value] of Object.entries(rowData)) {
                        row += value+",";
                    }
                    row = row.slice(0, -1); //Remove extra ,

                    csvFormat += row + "\r\n";
                });
                var encodedUri = encodeURI(csvFormat);

                var link = document.createElement("a");
                link.setAttribute("href", encodedUri);
                link.setAttribute("download", "products.csv");
                document.body.appendChild(link);
                link.click();

            }else {
                alert("There are no data to be exported!");
            }
        };
    }
};