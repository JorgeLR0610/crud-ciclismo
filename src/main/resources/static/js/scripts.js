document.addEventListener('DOMContentLoaded', function () {
    // Initialize Animations
    const animatedElements = document.querySelectorAll('.animate-on-scroll');

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('animate-fade-in-up');
                observer.unobserve(entry.target);
            }
        });
    }, {
        threshold: 0.1
    });

    animatedElements.forEach(el => observer.observe(el));

    // Real-time Search Functionality
    const searchInput = document.getElementById('searchInput');
    const searchSelect = document.getElementById('searchSelect');
    const table = document.querySelector('table');

    if (searchInput && table) {
        const filterTable = () => {
            const filter = searchInput.value.toLowerCase();
            const rows = table.getElementsByTagName('tr');

            // Default to searching all columns if no select or no data-col-index
            let colIndex = -1;
            if (searchSelect) {
                const selectedOption = searchSelect.options[searchSelect.selectedIndex];
                if (selectedOption && selectedOption.hasAttribute('data-col-index')) {
                    colIndex = parseInt(selectedOption.getAttribute('data-col-index'));
                }
            }

            // Start from 1 to skip the header row
            for (let i = 1; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName('td');
                let match = false;

                if (colIndex >= 0 && colIndex < cells.length) {
                    // Search specific column
                    const cell = cells[colIndex];
                    if (cell) {
                        const textValue = cell.textContent || cell.innerText;
                        if (textValue.toLowerCase().indexOf(filter) > -1) {
                            match = true;
                        }
                    }
                } else {
                    // Search all columns
                    for (let j = 0; j < cells.length; j++) {
                        if (cells[j]) {
                            const textValue = cells[j].textContent || cells[j].innerText;
                            if (textValue.toLowerCase().indexOf(filter) > -1) {
                                match = true;
                                break;
                            }
                        }
                    }
                }

                if (match) {
                    rows[i].style.display = "";
                    rows[i].classList.add('animate-fade-in');
                } else {
                    rows[i].style.display = "none";
                    rows[i].classList.remove('animate-fade-in');
                }
            }
        };

        searchInput.addEventListener('keyup', filterTable);
        if (searchSelect) {
            searchSelect.addEventListener('change', filterTable);
        }
    }
});
