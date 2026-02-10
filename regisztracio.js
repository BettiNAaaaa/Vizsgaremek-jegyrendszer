document.addEventListener('DOMContentLoaded', function() {
    initPasswordToggles();
    initRegister();
});

function initPasswordToggles() {
    const toggles = document.querySelectorAll('.toggle-icon');
    toggles.forEach(function(icon) {
        icon.addEventListener('click', function() {
            const input = icon.previousElementSibling;
            if (!input) return;

            input.type = input.type === 'password' ? 'text' : 'password';
            icon.src = input.type === 'text' ? 'eye-off.png' : 'eye.png';
        });
   });
}

function initRegister() {
    const btn = document.getElementById('registerBtn');
    const msg = document.getElementById('message');
    function doRegister() {
        const nameInput = document.getElementById('name');
        const emailInput = document.getElementById('email');
        const passwordInput = document.getElementById('password');
        const password2Input = document.getElementById('password2');

        const name = nameInput ? nameInput.value.trim() : '';
        const email = emailInput ? emailInput.value.trim() : '';
        const pass1 = passwordInput ? passwordInput.value : '';
        const pass2 = password2Input ? password2Input.value : '';

        if (msg) msg.textContent = '';

        if (name === '' || email === '' || pass1 === '' || pass2 === '') {
            if (msg) {
                msg.style.color = 'red';
                msg.textContent = 'Minden mezőt ki kell tölteni!';
            } else {
                console.warn('message element not found');
            }
            return;
        }

        if (pass1 !== pass2) {
            if (msg) {
                msg.style.color = 'red';
                msg.textContent = 'A két jelszó nem egyezik!';
            }
            return;
        }

        if (msg) {
            msg.style.color = 'green';
            msg.textContent = 'Sikeres regisztráció!';
        }

        alert('Regisztrált név: ' + name + '\nE-mail: ' + email);
    }

    if (btn) {
        btn.addEventListener('click', doRegister);
    } else {
        console.warn('registerBtn elem nem található ');
    }

   
window.registerUser = doRegister;
window.togglePassword = function(id, img) {
    const input = document.getElementById(id);
        if (!input) return;
        input.type = input.type === 'password' ? 'text' : 'password';
        if (img) img.src = input.type === 'text' ? 'eye-off.png' : 'eye.png';
    };
}