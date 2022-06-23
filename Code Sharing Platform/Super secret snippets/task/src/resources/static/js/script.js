function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": Number(document.getElementById("time_restriction").value),
        "views": Number(document.getElementById("views_restriction").value)
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onload = function() {
        if (xhr.status == 200) {
            alert("Success!");
            document.getElementById('result').innerHTML = xhr.response;
            // alert(xhr.response);

            // clear the form fields
            clear();
        } else {
            alert(`Error ${xhr.status}: ${xhr.statusText}`);
        }
    }

    xhr.send(json);
}

function clear() {
    let code = document.getElementById("code_snippet");
    let time = document.getElementById("time_restriction");
    let views = document.getElementById("views_restriction");

    code.value = "";
    time.value = views.value = "0";
}