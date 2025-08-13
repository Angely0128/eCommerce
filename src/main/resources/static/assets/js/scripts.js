// Preloader
window.addEventListener('load', function () {
    const preloader = document.getElementById('preloader');
    setTimeout(function () {
        preloader.classList.add('hide');
    }, 1000);
});

// Modo oscuro
const darkModeToggle = document.getElementById('darkModeToggle');
const body = document.body;

// Comprobar si hay una preferencia guardada
const isDarkMode = localStorage.getItem('darkMode') === 'true';
if (isDarkMode) {
    body.classList.add('dark-mode');
    darkModeToggle.innerHTML = '<i class="bi bi-sun"></i>';
}

darkModeToggle.addEventListener('click', () => {
    body.classList.toggle('dark-mode');
    const isDarkMode = body.classList.contains('dark-mode');
    localStorage.setItem('darkMode', isDarkMode);
    darkModeToggle.innerHTML = isDarkMode ? '<i class="bi bi-sun"></i>' : '<i class="bi bi-moon"></i>';
});

// Botón Volver Arriba
const backToTopBtn = document.querySelector('.back-to-top');

window.addEventListener('scroll', function () {
    if (window.pageYOffset > 300) {
        backToTopBtn.classList.add('show');
    } else {
        backToTopBtn.classList.remove('show');
    }
});

backToTopBtn.addEventListener('click', function (e) {
    e.preventDefault();
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});

// Añadir al carrito
const addToCartButtons = document.querySelectorAll('.add-to-cart');
const cartCount = document.querySelector('.cart-count');

let count = parseInt(cartCount.textContent);

addToCartButtons.forEach(button => {
    button.addEventListener('click', () => {
        count++;
        cartCount.textContent = count;

        // Animación
        button.innerHTML = '<i class="bi bi-check"></i> Añadido';
        button.classList.add('btn-success');
        button.classList.remove('btn-primary');

        setTimeout(() => {
            button.innerHTML = '<i class="bi bi-cart-plus me-1"></i> Añadir al carrito';
            button.classList.remove('btn-success');
            button.classList.add('btn-primary');
        }, 2000);
    });
});

// DataTables
$(document).ready(function () {
    const table = $('#miTabla').DataTable({
        // Habilitar ordenamiento con íconos triangulares
        ordering: true,
        // Configuración de lenguaje (español)
        language: {
            url: "https://cdn.datatables.net/plug-ins/2.0.7/i18n/es-ES.json"
        },
        // Añadir filtros por columna
        initComplete: function () {
            this.api().columns().every(function () {
                const column = this;
                const title = $(column.header()).text();

                // Crear elementos de filtro solo para las columnas que queremos
                if (title === "Categoría" || title === "Disponibilidad") {
                    const select = $('<select class="form-select form-select-sm mb-2"><option value="">Filtrar ' + title + '</option></select>')
                        .appendTo($(column.header()).empty())
                        .on('change', function () {
                            const val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );

                            column
                                .search(val ? '^' + val + '$' : '', true, false)
                                .draw();
                        });

                    // Obtener valores únicos para la columna
                    column.data().unique().sort().each(function (d, j) {
                        select.append('<option value="' + d + '">' + d + '</option>');
                    });
                }
            });
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Inicializar tooltips
    const tooltip = new bootstrap.Tooltip(document.querySelector('.whatsapp-btn'));

    // Mantener centrado vertical durante el scroll
    window.addEventListener('scroll', function () {
        const btn = document.querySelector('.whatsapp-btn');
        btn.style.top = '50%';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Inicializar tooltips
    const tooltip = new bootstrap.Tooltip(document.querySelector('#darkModeToggle'));

    // Mantener centrado vertical durante el scroll
    window.addEventListener('scroll', function () {
        const btn = document.querySelector('#darkModeToggle');
        btn.style.top = '50%';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Inicializar tooltips
    const tooltip = new bootstrap.Tooltip(document.querySelector('#cartToggle'));

    // Mantener centrado vertical durante el scroll
    window.addEventListener('scroll', function () {
        const btn = document.querySelector('#cartToggle');
        btn.style.top = '50%';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Inicializar tooltips
    const tooltip = new bootstrap.Tooltip(document.querySelector('#loginToggle'));

    // Mantener centrado vertical durante el scroll
    window.addEventListener('scroll', function () {
        const btn = document.querySelector('#loginToggle');
        btn.style.top = '50%';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Inicializar tooltips
    const tooltip = new bootstrap.Tooltip(document.querySelector('#searchToggle'));

    // Mantener centrado vertical durante el scroll
    window.addEventListener('scroll', function () {
        const btn = document.querySelector('#searchToggle');
        btn.style.top = '50%';
    });
});

