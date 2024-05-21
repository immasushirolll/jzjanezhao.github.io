function showContent(contentId) {
    // Hide all content elements
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    
    // Show the selected content
    var selectedContent = document.getElementById(contentId);
    if (selectedContent) {
        selectedContent.style.display = 'block';
    }
}

function toggleContent() {
    var content = document.getElementById("content");
    if (content.style.display === "block") {
        content.style.display = "none";
    } else {
        content.style.display = "block";
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    const button = document.querySelector('.rect-button');
    const dropdownContent = document.querySelector('.dropdown-content');

    button.addEventListener('click', () => {
        dropdownContent.classList.toggle('show');
    });

    // Close the dropdown if the user clicks outside of it
    window.addEventListener('click', (event) => {
        if (!event.target.matches('.rect-button')) {
            if (dropdownContent.classList.contains('show')) {
                dropdownContent.classList.remove('show');
            }
        }
    });
});
