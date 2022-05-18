//burger
const menuBurger = document.querySelector('.menu-burger');
const menuBody = document.querySelector('.menu-body');
const headerLocker = document.querySelector('header');
menuBurger.addEventListener("click", function (e) {
    document.body.classList.toggle('lock');
    headerLocker.classList.toggle('lock');
    menuBody.classList.toggle('active');
});