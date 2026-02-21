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

document.addEventListener("keydown", (e) => {
  if(e.key === "Escape"){
    overlay.classList.remove("show");
    miniMenu.classList.remove("show");
  }
});

/* kijelentkezés gomb – itt majd a saját logikád */
document.getElementById("logoutBtn").addEventListener("click", () => {
  // Példa: helyi tároló törlése (ha nálad login adatok ott vannak)
  // localStorage.removeItem("loggedInUser");

  // átirányítás bejelentkezés oldalra
  window.location.href = "bejelentkezes.html";
});