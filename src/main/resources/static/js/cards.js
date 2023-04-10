const button = document.getElementById("uploadButton");

button.addEventListener('click', async () => {
    console.log("hihi");
    const fileInput = document.getElementById("formFile");
    const file = fileInput.files[0];

    const formData = new FormData();
    formData.append("cardExcel", file, file.name);

    const requestOptions = {
        method: 'POST',
        body: formData,
        redirect: 'follow'
    };

    fetch(`${document.location.origin}/admin/addExcel`, requestOptions)
        .then(response => response.text())
        .then(_ => window.alert("업로드 성공!"))
        .catch(error => console.log('error', error));
})