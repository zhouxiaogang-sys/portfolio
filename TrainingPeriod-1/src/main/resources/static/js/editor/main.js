// エディター初期化
var editor = CodeMirror.fromTextArea(document.getElementById("editor"), {
    mode: 'markdown',
    theme: 'dark',
    lineNumbers: true,
    lineWrapping: true,
    height: "auto"
});
editor.setSize("100%", "calc(100% - 60px)");

const validation = validationEditObjectProperty();

// セーブ機能
document.getElementById("save").addEventListener("click", function () {
    if (!validation.validateAllFields()) {
        alert("入力内容に誤りがあります。");
        return;
    }

    const commitMessage = window.prompt("コミットメッセージを入力してください：");
    if (commitMessage === null) {
        return;
    }

    const content = editor.getValue();
    const fileNo = document.getElementById('fileNo').value;
    const fileId = document.getElementById("fileId").value;
    const fileName = document.getElementById("fileName").value;
    const task = document.getElementById("task").value;
    const sortOrder = document.getElementById("sortOrder").value;
    const author = document.getElementById("author").value;

    const jsonData = {
        fileNo: parseInt(fileNo),
        fileId: fileId,
        fileName: fileName,
        task: task,
        sortOrder: parseInt(sortOrder),
        author: author,
        content: content,
        commitMessage: commitMessage,
    }

    fetch("/save/content", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok')
            }
            return response.json();
        })
        .then(result => {
            alert("保存成功");
            window.close();
        })
        .catch(error => {
            alert("保存失敗：" + error);
        });
});

function validationEditObjectProperty() {
    const rules = {
        fileId: {
            required: true,
            minlength: 5,
            maxlength: 50,
            pattern: /^[a-zA-Z0-9-]+$/
        },
        fileName: {
            required: true,
            minlength: 5,
            maxlength: 50,
        },
        task: {
            required: true,
            pattern: /^[a-zA-Z]+$/
        },
        sortOrder: {
            required: true,
            pattern: /^[0-9]+$/
        },
        author: {
            required: true,
            maxlength: 50
        }
    };

    const errorMessages = {
        fileId: {
            required: "*ファイルIDを入力してください",
            minlength: "*5文字以上のIDを入力してください",
            maxlength: "*50文字以下のIDを入力してください",
            pattern: "*数字、英字、ハイフンのみ入力ください"
        },
        fileName: {
            required: "*ファイル名を入力してください",
            minlength: "*5文字以上のファイル名を入力してください",
            maxlength: "*50文字以下のファイル名を入力してください"
        },
        task: {
            required: "*所属課題を選択してください",
            pattern: "*所属課題を選択してください"
        },
        sortOrder: {
            required: "*表示順番を入力してください",
            pattern: "*数字のみ入力してください"
        },
        author: {
            required: "*作成者を入力してください",
            maxlength: "*50文字以下の作成者を入力してください"
        }
    };

    function validateField(field) {
        const fieldName = field.id;
        const value = field.value;
        const rule = rules[fieldName];
        const message = errorMessages[fieldName];
        let error = ``;

        if (rule.required && !value) {
            error = message.required;
        } else if (value) {
            if (rule.minlength && value.length < rule.minlength) {
                error = message.minlength;
            }
            if (rule.maxlength && value.length > rule.maxlength) {
                error = message.maxlength;
            }
            if (rule.pattern && !rule.pattern.test(value)) {
                error = message.pattern;
            }
        }
        const errorElement = document.getElementById(`${fieldName}--error`);
        if (errorElement) {
            errorElement.textContent = error;
        }
        if (error) {
            field.classList.add(`invalid`);
            return false;
        } else {
            field.classList.remove(`invalid`);
            return true;
        }
    }

    // 全フィールドのバリデーションを実行
    function validateAllFields() {
        const fields = document.querySelectorAll('#fileId, #fileName, #task, #sortOrder, #author');
        let isValid = true;
        fields.forEach(field => {
            if (!validateField(field)) {
                isValid = false;
            }
        });
        return isValid;
    }

    const fields = document.querySelectorAll('#fileId, #fileName, #task, #sortOrder, #author');
    fields.forEach(field => {
        field.addEventListener('blur', function () {
            validateField(this);
        });
    });

    return {validateField, validateAllFields};
}