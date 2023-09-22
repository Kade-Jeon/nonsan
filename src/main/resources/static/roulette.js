import "./styles.css";

var rouletter = {
    random: function () {
        var min = Math.ceil(0);
        var max = Math.floor(5);
        return Math.floor(Math.random() * (max - min)) + min;
    },
    start: function () {
        var btn = document.querySelector(".rouletter-btn");
        var panel = document.querySelector(".item-wrapper");

        panel.classList.add("on");
        btn.innerText = "stop";
    },
    stop: function () {
        var btn = document.querySelector(".rouletter-btn");
        var panel = document.querySelector(".item-wrapper");
        var deg = [60, 120, 180, 240, 300, 360];

        panel.style.transform = "rotate(" + deg[rouletter.random()] + "deg)";
        panel.classList.remove("on");
        btn.innerText = "start";
    }
};

document.addEventListener("click", function (e) {
    var target = e.target;
    if (target.tagName === "BUTTON") {
        target.innerText === "start" ? rouletter.start() : rouletter.stop();
    }
});

document.getElementById("app").innerHTML = `
<div class="rouletter">
    <div class="rouletter-bg">
        <div class="item-wrapper"></div>
    </div>
    <div class="rouletter-arrow"></div>
    <button class="rouletter-btn">start</button>
</div>
`;