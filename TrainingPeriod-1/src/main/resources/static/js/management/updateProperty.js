document.getElementById("save").addEventListener('click', function () {
    const jsonData = {};

    const inputs =document.querySelectorAll('input');

    inputs.forEach(input => {
        input.addEventListener('change', ()=>{
            jsonData[input.name] = input.value;
        })
    })

    fetch("/save/property", {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => response.json())
        .then(data => {console.log('保存成功', date);
        })
        .catch(error => console.log(error));
});

function showUpgrade() {
    document.getElementById('updateModal').style.display = 'block';
    let fileId = element.closest('fileId').value;
    document.getElementById('fileId').focus();

    document.getElementById('fileId').value = '';
    document.getElementById('task').value = '';
    document.getElementById('sort-order').value = '';
    document.getElementById('filename').value = '';
    document.getElementById('author').value = '';

    document.getElementById('errorMessage').style.display = 'none';
}

/*新規登録キャンセル*/
function closeRegister() {
    document.getElementById('registerModal').style.display = 'none';
}