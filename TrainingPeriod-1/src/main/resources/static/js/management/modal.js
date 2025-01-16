/*新規登録画面表示*/
function showRegister() {
    document.getElementById('registerModal').style.display = 'block';
    document.getElementById('register-fileId').focus();

    document.getElementById('register-fileId').value = '';
    document.getElementById('register-task').value = '';
    document.getElementById('register-sort-order').value = '';
    document.getElementById('register-filename').value = '';
    document.getElementById('register-author').value = '';

    document.getElementById('errorMessage').style.display = 'none';
}

/*新規登録キャンセル*/
function closeRegister() {
    document.getElementById('registerModal').style.display = 'none';
}

