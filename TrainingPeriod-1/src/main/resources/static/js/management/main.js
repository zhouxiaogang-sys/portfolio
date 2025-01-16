$(document).ready(function () {
    // タブの切り替え
    $(document).on('click', '.tab', function () {
        $('.tab').removeClass('active');
        $(this).addClass('active');

        const taskType = $(this).data('task');
        $('.tab-content').removeClass('active');
        $(`#${taskType}-content`).addClass('active');
    });

    // エディターへリダイレクト
    $(document).on('click', '.edit-button', function () {
        const fileId = $(this).closest('.file-detail').attr('id');
        if (fileId) {
            window.open(`/edit/${fileId}`, '_blank');
        }
    });

    // Historyへリダイレクト
    $(document).on('click', '.history-button', function () {
        const fileId = $(this).closest('.file-detail').attr('id');
        if (fileId) {
            window.open(`/history/${fileId}`, '_blank');
        }
    });

    // 手順書を削除
    deleteGuideBook();

    //登録データチェック
    validationRegisterData();

    //手順書の新規登録
    registerGuideBook();

    /*ファイル詳細表示アニメーション*/
    toggleAnimation();
});

function registerGuideBook() {
    $(document).on('click', '.register-btn', function () {
        const fileId = $('#registerFileId').val();
        const fileName = $('#registerFileName').val();
        const task = $('#registerTask').val();
        const sortOrder = $('#registerSortOrder').val();
        const author = $('#registerAuthor').val();

        // 基本的な入力チェック
        if (!fileId || !fileName || !task || !sortOrder || !author) {
            $('#errorMessage').html('<span>すべての項目を入力してください。</span>').show();
            return;
        }

        const jsonData = {
            fileId: fileId,
            fileName: fileName,
            task: task,
            sortOrder: parseInt(sortOrder),
            author: author,
        }

        fetch("/management/register", {
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
                alert("登録成功");
            })
            .catch(error => {
                $('#errorMessage').html(`<span>${error.message}</span>`).show();
            });
    })
}

function validationRegisterData() {
    const rules = {
        registerFileId:{
            required: true,
            minlength: 5,
            maxlength: 50,
            pattern: /^[a-zA-Z0-9-]+$/
        },
        registerFileName:{
            required: true,
            minlength: 5,
            maxlength: 50,
        },
        registerTask:{
            required: true,
            pattern: /^[a-zA-Z]+$/
        },
        registerSortOrder:{
            required: true,
            pattern: /^[0-9]+$/
        },
        registerAuthor:{
            required: true,
            maxlength: 50
        }
    };

    const errorMessages = {
        registerFileId: {
            required: "*ファイルIDを入力してください",
            minlength: "*5文字以上のIDを入力してください",
            maxlength: "*50文字以下のIDを入力してください",
            pattern: "*数字、英字、ハイフンのみ入力ください"
        },
        registerFileName: {
            required: "*ファイル名を入力してください",
            minlength: "*5文字以上のファイル名を入力してください",
            maxlength: "*50文字以下のファイル名を入力してください"
        },
        registerTask: {
            required: "*所属課題を選択してください",
            pattern: "*所属課題を選択してください"
        },
        registerSortOrder: {
            required: "*表示順番を入力してください",
            pattern: "*数字のみ入力してください"
        },
        registerAuthor: {
            required: "*作成者を入力してください",
            maxlength: "*50文字以下の作成者を入力してください"
        }
    };

    function validateField(field) {
        const $field = $(field);
        const fieldName = $field.attr(`id`);
        const value = $field.val();
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
        $(`#${fieldName}--error`).text(error);
        if (error) {
            $field.addClass(`invalid`);
            return false;
        } else {
            $field.removeClass(`invalid`);
            return true;
        }
    }

    $(document).on(`blur`, `input, select`, function () {
        validateField(this);
    })
}

function deleteGuideBook() {
    $(document).on('click', '.delete-button', function () {
        const fileId = $(this).closest('.file-detail').attr('id');

        if (!confirm('本当に削除しますか？')) {
            return;
        }

        if (fileId) {
            fetch(`/management/delete/${fileId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                }
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
                    alert("削除が完了しました。");
                    location.reload();
                })
                .catch(error => {
                    alert("削除に失敗しました：" + error.message);
                });
        }
    });
}

function loadContent(contentName) {
    $.ajax({
        url: '/management/load-content',
        data: {content: contentName},
        success: function (response) {
            $('#mainContent').html(response);
        },
        error: function (xhr) {
            if (xhr.status === 401) {
                window.location.href = '/';
            }
        }
    });
}

function toggleAnimation() {
    $(document).on('click', '.content-link', function (e) {
        e.preventDefault();
        const fileId = $(this).data('file-id');
        $(`#${fileId}`).slideToggle({
            duration: 100,
            easing: 'linear'
        });
    });
}
