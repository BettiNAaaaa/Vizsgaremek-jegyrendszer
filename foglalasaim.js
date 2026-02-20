const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("menuOverlay");
const miniMenu = document.getElementById("miniMenu");

menuBtn.addEventListener("click", () => {
    overlay.classList.toggle("show");
    miniMenu.classList.toggle("show");
});

overlay.addEventListener("click", () => {
    overlay.classList.remove("show");
    miniMenu.classList.remove("show");
});