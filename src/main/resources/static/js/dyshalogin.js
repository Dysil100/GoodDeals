const avatar = document.querySelector('.avatar');
const emailInput = document.querySelector('#email');
const passwordInput = document.querySelector('#password');
const showPassword = document.querySelector('#show-password');

// Animate Avatar Eyes
emailInput.addEventListener('input', () => {
    const lastLetter = emailInput.value.slice(-1).toLowerCase();
    const eye = avatar.contentDocument.querySelector('#eye');

    if (!eye) {
        return;
    }

    const x = (lastLetter.charCodeAt(0) - 97) * 2.5;
    const y = (Math.random() - 0.5) * 5;

    gsap.to(eye, {
        duration: 0.2,
        attr: { transform: `translate(${x}, ${y})` },
        ease: 'power1.inOut'
    });
});

// Animate Avatar Eyes Closing
passwordInput.addEventListener('focus', () => {
    const eye = avatar.contentDocument.querySelector('#eye');

    if (!eye) {
        return;
    }

    gsap.to(eye, {
        duration: 0.2,
        attr: { r: 0 },
        ease: 'power1.inOut'
    });
});

// Animate Avatar Eyes Opening
showPassword.addEventListener('click', () => {
    const eye = avatar.contentDocument.querySelector('#eye');

    if (!eye) {
        return;
    }

    const r = passwordInput.type === 'password' ? 5 : 0;

    gsap.to(eye, {
        duration: 0.2,
        attr: { r },
        ease: 'power1.inOut'
    });

    passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
});

// Handle Form Submit
const form = document.querySelector('.login-form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    // Perform validation here and show error messages if necessary

    if (email === 'example@mail.com' && password === 'password') {
        alert('Connexion réussie !');
    } else {
        alert('Adresse e-mail ou mot de passe incorrect.');
    }
});