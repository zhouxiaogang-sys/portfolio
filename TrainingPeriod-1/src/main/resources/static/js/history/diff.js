// バージョン間の差分を表示
async function showDiff(oldContent, newContent) {
    try {
        // 差分データの取得
        const response = await fetch('/compare', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                oldVersion: oldContent,
                newVersion: newContent
            })
        });

        if (!response.ok) {
            throw new Error('比較に失敗しました');
        }

        const diffLines = await response.json();
        const diffContent = document.getElementById('diffContent');

        // 差分の統計情報を計算
        const stats = {
            additions: diffLines.filter(line => line.startsWith('+')).length,
            deletions: diffLines.filter(line => line.startsWith('-')).length
        };

        // 差分表示のHTML生成
        // [...HTMLの生成コード...]
        let html = `
            <div class="diff-header">
                <div class="file-info">
                    <span class="file-name">File Changes</span>
                    <div class="file-stats">
                        <span class="additions">+${stats.additions}</span>
                        <span class="deletions">-${stats.deletions}</span>
                    </div>
                </div>
                <div class="diff-stats">
                    <div class="diff-stat-box">
                        ${generateStatBars(stats.additions, stats.deletions)}
                    </div>
                </div>
            </div>
            <div class="diff-content">`;

        let oldLineNum = 1;
        let newLineNum = 1;


        diffLines.forEach(line => {
            const content = escapeHtml(line.slice(1));
            const prefix = line[0];

            if (prefix === '+') {
                html += `
                    <div class="diff-line diff-addition">
                        <div class="line-number old"></div>
                        <div class="line-number new">${newLineNum++}</div>
                        <div class="line-content"><span class="diff-mark">+</span>${content}</div>
                    </div>`;
            } else if (prefix === '-') {
                html += `
                    <div class="diff-line diff-deletion">
                        <div class="line-number old">${oldLineNum++}</div>
                        <div class="line-number new"></div>
                        <div class="line-content"><span class="diff-mark">-</span>${content}</div>
                    </div>`;
            } else {
                html += `
                    <div class="diff-line">
                        <div class="line-number old">${oldLineNum++}</div>
                        <div class="line-number new">${newLineNum++}</div>
                        <div class="line-content"><span class="diff-mark"> </span>${content}</div>
                    </div>`;
            }
        });

        html += '</div>';
        diffContent.innerHTML = html;

        document.getElementById('normalView').style.display = 'none';
        document.getElementById('diffView').style.display = 'block';
    } catch (error) {
        console.error('差分表示エラー:', error);
        alert('バージョン比較に失敗しました');
    }
}

// 統計バーの生成
function generateStatBars(additions, deletions) {
    const total = additions + deletions;
    const additionWidth = total ? Math.max((additions / total) * 100, 0) : 0;
    const deletionWidth = total ? Math.max((deletions / total) * 100, 0) : 0;

    return `
        <div class="stat-bar">
            <div class="stat-addition" style="width: ${additionWidth}%"></div>
            <div class="stat-deletion" style="width: ${deletionWidth}%"></div>
        </div>
    `;
}

// 比較ボタンのイベントリスナー設定
document.getElementById('compareButton').addEventListener('click', () => {
    const oldIndex = document.getElementById('oldVersion').value;
    const newIndex = document.getElementById('newVersion').value;

    if (!oldIndex || !newIndex) {
        alert('比較するバージョンを選択してください');
        return;
    }

    const oldContent = versionsData[oldIndex].content;
    const newContent = versionsData[newIndex].content;
    showDiff(oldContent, newContent);
});