document.addEventListener("DOMContentLoaded", () => {
    const toggle = document.querySelector("[data-menu-toggle]");
    const menu = document.getElementById("primary-navigation");

    if (!(toggle instanceof HTMLButtonElement) || !(menu instanceof HTMLElement)) {
        return;
    }

    toggle.addEventListener("click", () => {
        const isExpanded = toggle.getAttribute("aria-expanded") === "true";
        toggle.setAttribute("aria-expanded", String(!isExpanded));
        toggle.setAttribute("aria-label", isExpanded ? "Abrir menú" : "Cerrar menú");
        menu.classList.toggle("show", !isExpanded);
    });

    menu.querySelectorAll("a").forEach((link) => {
        link.addEventListener("click", () => {
            toggle.setAttribute("aria-expanded", "false");
            toggle.setAttribute("aria-label", "Abrir menú");
            menu.classList.remove("show");
        });
    });
});
