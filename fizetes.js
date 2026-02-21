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

  // --- mentett kártya megjelenítés localStorage-ból ---
  function renderSavedCard(){
    const raw = localStorage.getItem("savedCard");
    const noCardText = document.getElementById("noCardText");
    const slot = document.getElementById("savedCardSlot");

    if(!raw){
      noCardText.style.display = "block";
      slot.innerHTML = "";
      return;
    }

    const card = JSON.parse(raw);
    noCardText.style.display = "none";

    slot.innerHTML = `
  <div class="saved-card">
    <div class="left">
      <span class="brand">
        <img src="mastercard.png" class="card-brand-img">
      </span>
      <span>•••• ${card.last4}</span>
    </div>
  </div>

        <div class="right">
          <span>${card.name}</span>
          <button class="trash-btn" type="button" title="Törlés">
             <img src="trash.png" alt="Törlés">
          </button>
        </div>
      </div>
    `;

    slot.querySelector(".trash-btn").addEventListener("click", () => {
      localStorage.removeItem("savedCard");
      renderSavedCard();
    });
  }

  renderSavedCard();