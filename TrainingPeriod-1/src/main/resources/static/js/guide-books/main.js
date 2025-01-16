let currentFileId = "";

function toggleSection(element) {
    const content = element.nextElementSibling;
    if (content.style.maxHeight) {
        content.style.maxHeight = null;
    } else {
        content.style.maxHeight = content.scrollHeight + "px";
    }
}

function loadContent(fileId) {
    currentFileId = fileId;
    fetch(`/content/${fileId}`, {
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
        .then(response => response.text())
        .then(content => {
            const contentDiv = document.getElementById('content');
            contentDiv.innerHTML = content;
        })
        .catch(error => console.error('Error:', error));

    backToTop();
}

/* back to topボタン */
const backToTopButton = document.querySelector('.back-to-top');
const mainContent = document.querySelector('.main-content')

mainContent.addEventListener('scroll', () => {

    const scrollPosition = document.querySelector('.main-content').scrollTop;

    if (scrollPosition > 100) {
        backToTopButton.classList.add('visible');
    } else {
        backToTopButton.classList.remove('visible');
    }
});

function backToTop() {
    document.querySelector('.main-content').scrollTo({
        top: 0,
        behavior: 'smooth'
    });
}

backToTopButton.addEventListener('click', backToTop);