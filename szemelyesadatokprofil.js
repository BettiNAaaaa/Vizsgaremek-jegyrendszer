// ===== HAMBURGER MENÜ (overlay + mini menü) =====
const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("menuOverlay");
const miniMenu = document.getElementById("miniMenu");

function openMenu(){
  overlay.classList.add("show");
  miniMenu.classList.add("show");
  overlay.setAttribute("aria-hidden", "false");
  miniMenu.setAttribute("aria-hidden", "false");
}

function closeMenu(){
  overlay.classList.remove("show");
  miniMenu.classList.remove("show");
  overlay.setAttribute("aria-hidden", "true");
  miniMenu.setAttribute("aria-hidden", "true");
}

menuBtn?.addEventListener("click", () => {
  const isOpen = overlay.classList.contains("show");
  if(isOpen) closeMenu();
  else openMenu();
});

overlay?.addEventListener("click", closeMenu);

document.addEventListener("keydown", (e) => {
  if(e.key === "Escape") closeMenu();
});

// ===== PROFILKÉP VÁLASZTÁS + ELŐNÉZET =====
const avatarInput = document.getElementById("avatarInput");
const avatarPreview = document.getElementById("avatarPreview");
const avatarFallback = document.getElementById("avatarFallback");

avatarInput?.addEventListener("change", (e) => {
  const file = e.target.files?.[0];
  if(!file) return;

  const url = URL.createObjectURL(file);
  avatarPreview.src = url;
  avatarPreview.style.display = "block";
  avatarFallback.style.display = "none";
});

// ===== CERUZA GOMB: mező szerkeszthetővé tétele =====
document.querySelectorAll(".edit-btn").forEach(btn => {
  btn.addEventListener("click", () => {
    const targetId = btn.getAttribute("data-target");
    const input = document.getElementById(targetId);
    if(!input) return;

    input.disabled = false;
    input.classList.add("is-editing");
    input.focus();

    // kurzor a végére
    const val = input.value;
    input.value = "";
    input.value = val;
  });
});

// ha elhagyod a mezőt, visszazárjuk (optional, de jól jön)
document.querySelectorAll(".data-input").forEach(input => {
  input.addEventListener("blur", () => {
    input.disabled = true;
    input.classList.remove("is-editing");
  });
});