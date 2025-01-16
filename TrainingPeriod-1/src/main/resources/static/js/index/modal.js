/*ログイン画面表示*/
function showLogin() {
    const sessionData = window.APP_DATA.sessionUsername;
    if (sessionData == null) {
        document.getElementById('loginModal').style.display = 'block';
        document.getElementById('username').focus();

        document.getElementById('username').value = '';
        document.getElementById('password').value = '';
        document.getElementById('errorMessage').style.display = 'none';
    } else {
        window.location.href = "/management";
    }
}

document.getElementById(`login-btn`).addEventListener("click", function () {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        const errorElement = document.getElementById('errorMessage');
        errorElement.innerHTML = '<span>ユーザー名とパスワードを入力してください</span>';
        errorElement.style.display = 'block';
        return;
    }

    const jsonData = {
        username: username,
        password: password
    }

    fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text || 'Network response was not ok');
                });
            }
            return response.text();
        })
        .then(result => {
            window.location.href = "/management";
        })
        .catch(error => {
            const errorElement = document.getElementById('errorMessage');
            errorElement.innerHTML = `<span>${error.message}</span>`;
            errorElement.style.display = 'block';
        });
});

/*ログインキャンセル*/
function closeLogin() {
    document.getElementById('loginModal').style.display = 'none';
}
