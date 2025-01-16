let versionsData = [];

// 初期データの設定
document.addEventListener('DOMContentLoaded', async () => {
    const { initialHistory, currentFileId } = window.APP_DATA;

    if (initialHistory && initialHistory.length > 0) {
        showContent(initialHistory[0]);
    } else {
        const fileId = currentFileId || window.location.pathname.split('/').pop();
        try {
            await initializeHistoryList(fileId);  // 使用 await 等待 Promise
        } catch (error) {
            console.error('初期化エラー:', error);
        }
    }

    // 履歴アイテムのクリックイベントを設定
    document.querySelectorAll('.history-item').forEach(item => {
        item.addEventListener('click', () => {
            // アクティブ状態の更新
            document.querySelectorAll('.history-item').forEach(i =>
                i.classList.remove('active'));
            item.classList.add('active');

            // バージョン情報の取得と表示
            const version = item.getAttribute('data-version');
            const content = item.getAttribute('data-content');
            showContent({
                version: version,
                content: content,
                editor: item.querySelector('.author-info span').textContent.replace('編集者：', ''),
                createTime: item.querySelector('.create-time').textContent,
                commitMessage: item.querySelector('.commit-message').textContent
            });
        });
    });
});

// 履歴データをサーバーから取得
async function fetchHistoryData(fileId) {
    try {
        const response = await fetch(`/list/${fileId}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`記録取得失敗: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error('記録取得失敗：', error);
        throw error;
    }
}

// 選択されたバージョンの内容を表示
function showContent(history) {
    const contentHeader = document.getElementById('contentHeader');
    const contentBody = document.getElementById('contentBody');

    // ヘッダー情報の設定
    contentHeader.innerHTML = `
                <h3>Version ${history.version}</h3>
                <div class="author-info">
                    <span>編集者：${history.editor}</span>
                    <span>提出時間：${history.createTime}</span>
                </div>
                <div class="commit-message">${history.commitMessage}</div>
            `;

    // 内容の表示
    contentBody.textContent = history.content;

    // 表示モードの切り替え
    document.getElementById('normalView').style.display = 'block';
    document.getElementById('diffView').style.display = 'none';
}


document.addEventListener('DOMContentLoaded', () => {
    const pathParts = window.location.pathname.split('/');
    const fileId = pathParts[pathParts.length - 1];
    initializeHistoryList(fileId);
});

// 履歴一覧の初期化
async function initializeHistoryList(fileId) {
    const historyList = document.getElementById('historyList');
    historyList.innerHTML = '';

    try {
        const historyData = await fetchHistoryData(fileId);
        versionsData = historyData;

        // 履歴データの取得
        updateVersionSelects(historyData);

        // バージョン選択ドロップダウンの更新
        historyData.forEach(history => {
            const historyItem = document.createElement('div');
            const formattedTime = `${history.createTime}`.replace('T', '\n');
            historyItem.className = 'history-item';
            historyItem.innerHTML = `
                <div class="version-info">
                    <span class="version-number">Version ${history.version}</span><br>
                    <span class="create-time">${formattedTime}</span>
                </div>
                <div class="commit-message">${history.commitMessage}</div>
                <div class="author-info">
                    <span>編集者：${history.editor}</span>
                </div>
            `;

            // クリックイベントの設定
            historyItem.addEventListener('click', () => {
                document.querySelectorAll('.history-item').forEach(item => {
                    item.classList.remove('active');
                });

                historyItem.classList.add('active');

                showContent(history);
            });

            historyList.appendChild(historyItem);
        });

        // 最新バージョンの表示
        if (historyData.length > 0) {
            showContent(historyData[0]);
            historyList.firstChild.classList.add('active');
        }
    } catch (error) {
        console.error('記録ローディング失敗:', error);
        historyList.innerHTML = '<div class="error-message">記録ローディング失敗</div>';
    }
}

// 更新版本选择下拉框
function updateVersionSelects(historyData) {
    const oldSelect = document.getElementById('oldVersion');
    const newSelect = document.getElementById('newVersion');

    oldSelect.innerHTML = '<option value="">旧バージョンを選択</option>';
    newSelect.innerHTML = '<option value="">新バージョンを選択</option>';

    historyData.forEach((item, index) => {
        const option = `<option value="${index}">Version ${item.version} (${formatDate(item.createTime)})</option>`;
        oldSelect.insertAdjacentHTML('beforeend', option);
        newSelect.insertAdjacentHTML('beforeend', option);
    });
}
