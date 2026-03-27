document.addEventListener('DOMContentLoaded', function(){
    initPasswordToggles();
    initLogin();

});

function initPasswordToggles(){
    const toggles = document.querySelectorAll('.toggle-icon');
    toggles.forEach(function(icon){
        icon.addEventListener('click', function(){
            const input = icon.previousElementSibling;
            if(!input) return;

            input.type = input.type === 'password' ? 'text' : 'password';
            icon.src = input.type === 'text' ? 'eye-off.png' : 'eye.png';
        })

    });
}

function initLogin(){
    const btn = document.getElementById('loginBtn');
    const msg = document.getElementById('message');

function doLogin() {
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const name = nameInput ? nameInput.value.trim() : '';
    const email = emailInput ? emailInput.value.trim() : '';
    const pass = passwordInput ? passwordInput.value : '';

        if (msg) msg.textContent = '';

        if (name === '' || email === '' || pass === '') {
            if (msg) {
                msg.style.color = 'red';
                msg.textContent = 'Minden mezőt ki kell tölteni!';
            } else {
                console.warn('message element not found');
            }
            return;
        }

        if (msg) {
            msg.style.color = 'green';
            msg.textContent = 'Sikeres bejelentkezés!';
        }

        alert('Bejelentkezett név: ' + name + '\nE-mail: ' + email);
    }

    if (btn) {
        btn.addEventListener('click', doLogin);
    } else {
        console.warn('loginBtn elem nem található — nem sikerült csatolni a kattintás eseményt.');
    }

    
    window.loginUser = doLogin;
    window.togglePassword = function(id, img) {
        const input = document.getElementById(id);
        if (!input) return;
        input.type = input.type === 'password' ? 'text' : 'password';
        if (img) img.src = input.type === 'text' ? 'eye-off.png' : 'eye.png';
    };

}