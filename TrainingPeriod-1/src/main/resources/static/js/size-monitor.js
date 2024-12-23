// 创建监控器的HTML结构
const monitorHtml = `
  <div id="size-monitor" style="
    position: fixed;
    top: 1rem;
    right: 1rem;
    background-color: white;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 28rem;
    z-index: 9999;
  ">
    <h3 style="
      font-size: 1.125rem;
      font-weight: 600;
      margin-bottom: 0.75rem;
    ">尺寸变化监控</h3>
    <div id="changes-container" style="
      max-height: 24rem;
      overflow-y: auto;
    "></div>
  </div>
`;

// 添加监控器到页面
document.body.insertAdjacentHTML('beforeend', monitorHtml);

const changesContainer = document.getElementById('changes-container');
let changes = [];

// 存储元素的初始尺寸
const elementSizes = new Map();

// 检查并记录尺寸变化
function checkSizeChanges() {
    document.querySelectorAll('.task-content a, .each-content, .markdown-container, #content, .task-content, body, .layout, .sidebar, .main-content').forEach(element => {
        const rect = element.getBoundingClientRect();
        const key = `${element.tagName.toLowerCase()}${element.className ? '.' + element.className.split(' ').join('.') : ''}`;
        const currentSize = `${Math.round(rect.width)}x${Math.round(rect.height)}`;

        if (!elementSizes.has(key)) {
            elementSizes.set(key, currentSize);
            logChange(key, rect.width, rect.height, '初始尺寸');
        } else if (elementSizes.get(key) !== currentSize) {
            const [oldWidth, oldHeight] = elementSizes.get(key).split('x').map(Number);
            logChange(key, rect.width, rect.height, `变化: ${oldWidth}x${oldHeight} -> ${currentSize}`);
            elementSizes.set(key, currentSize);
        }
    });
}

// 记录变化
function logChange(elementInfo, width, height, message) {
    const change = {
        elementInfo,
        width: Math.round(width),
        height: Math.round(height),
        message,
        time: new Date().toLocaleTimeString()
    };

    changes.unshift(change);
    changes = changes.slice(0, 10);
    updateDisplay();
}

// 更新显示
function updateDisplay() {
    changesContainer.innerHTML = changes.map(change => `
    <div style="
      margin-bottom: 0.5rem;
      padding: 0.5rem;
      background-color: #f9fafb;
      border-radius: 0.25rem;
    ">
      <div style="font-size: 0.875rem;">
        <span style="font-weight: 500;">元素: </span>
        <span style="color: #2563eb;">${change.elementInfo}</span>
      </div>
      <div style="font-size: 0.875rem;">
        <span style="font-weight: 500;">尺寸: </span>
        ${change.width}x${change.height}px
      </div>
      <div style="font-size: 0.875rem;">
        <span style="font-weight: 500;">信息: </span>
        ${change.message}
      </div>
      <div style="
        font-size: 0.75rem;
        color: #6b7280;
      ">${change.time}</div>
    </div>
  `).join('');
}

// 初始检查
setTimeout(checkSizeChanges, 500);

// 监听点击事件
document.addEventListener('click', () => {
    // 延迟检查以捕获动画完成后的状态
    setTimeout(checkSizeChanges, 300);
});

// 监听内容加载
const originalLoadContent = window.loadContent;
window.loadContent = function(fileId) {
    originalLoadContent(fileId);
    setTimeout(checkSizeChanges, 500);
};

// 监听滚动事件
document.addEventListener('scroll', () => {
    setTimeout(checkSizeChanges, 300);
}, { passive: true });

// 定期检查（每秒）
setInterval(checkSizeChanges, 1000);