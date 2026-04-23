 function detectBrand(num){
    if(/^4/.test(num)) return "VISA";
    if(/^(5[1-5])/.test(num)) return "Mastercard";
    return "Kártya";
  }

  // --- formázás: kártyaszám szóközökkel ---
  const cardNumberInput = document.getElementById("cardNumber");
  cardNumberInput.addEventListener("input", () => {
    let v = cardNumberInput.value.replace(/\\D/g,"").slice(0,16);
    v = v.replace(/(.{4})/g, "$1 ").trim();
    cardNumberInput.value = v;
  });

  // --- formázás: lejárat MM/YY ---
  const expInput = document.getElementById("exp");
  expInput.addEventListener("input", () => {
    let v = expInput.value.replace(/\\D/g,"").slice(0,4);
    if(v.length >= 3) v = v.slice(0,2) + "/" + v.slice(2);
    expInput.value = v;
  });

  // --- mentés localStorage-ba ---
  document.getElementById("cardForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const lastName = document.getElementById("lastName").value.trim();
    const firstName = document.getElementById("firstName").value.trim();
    const email = document.getElementById("email").value.trim();

    const rawNumber = document.getElementById("cardNumber").value.replace(/\\s+/g,"");
    const exp = document.getElementById("exp").value.trim();
    const cvc = document.getElementById("cvc").value.trim();

    if(!lastName || !firstName || !email || rawNumber.length < 12 || exp.length < 4 || cvc.length < 3){
      alert("Kérlek töltsd ki helyesen az adatokat!");
      return;
    }

    const last4 = rawNumber.slice(-4);
    const brand = detectBrand(rawNumber);

    // (Fontos: teljes kártyaszámot ne tárolj élesben! Itt csak projekt.)
    const savedCard = {
      brand,
      last4,
      name: `${lastName} ${firstName}`,
      email,
      exp
    };

    localStorage.setItem("savedCard", JSON.stringify(savedCard));

    // vissza a fizetés/számlák oldalra
    window.location.href = "fizetes.html";
  });