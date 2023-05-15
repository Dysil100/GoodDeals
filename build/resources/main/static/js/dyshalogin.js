function handleEmailInput() {
    const avatar = document.querySelector('.avatar');
    const emailInput = document.getElementById('email');
    const emailValue = emailInput.value;
    const length = emailValue.length;
    const x = length * 2;
    const y = 50 - length;
    avatar.querySelector('.eye:nth-of-type(1)').setAttribute('cy', y);
    avatar.querySelector('.eye:nth-of-type(2)').setAttribute('cy', y);
    avatar.querySelector('.eye:nth-of-type(1)').setAttribute('cx', 45 + x);
    avatar.querySelector('.eye:nth-of-type(2)').setAttribute('cx', 75 + x);
}

function handlePasswordFocus() {
    const avatar = document.querySelector('.avatar');
    avatar.querySelector('.eye:nth-of-type(1)').classList.add('closed');
    avatar.querySelector('.eye:nth-of-type(2)').classList.add('closed');
}

function handlePasswordBlur() {
    const avatar = document.querySelector('.avatar');
    avatar.querySelector('.eye:nth-of-type(1)').classList.remove('closed');
    avatar.querySelector('.eye:nth-of-type(2)').classList.remove('closed');
    avatar.querySelector('.hand').style.opacity = 0;
    avatar.querySelector('.hand').style.transform = 'translateX(0)';
    avatar.querySelector('.eye').style.transform = 'scaleY(1)';
}

function handleShowPasswordClick() {
    const passwordInput = document.getElementById('password');
    passwordInput.type = document.getElementById('show-password').checked ? 'text' : 'password';
}