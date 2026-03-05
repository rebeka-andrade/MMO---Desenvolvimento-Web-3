async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(API + "/auth/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            email,
            password
        })

    });

    if (!res.ok) {

        const error = await res.text();
        alert(error);
        return;

    }

    const data = await res.json();

    localStorage.setItem("token", data.token);

    window.location = "feed.html";

}

async function register() {

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    await fetch(API + "/auth/register", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            username: name,
            email,
            password
        })

    });

    alert("Registrado!");

    window.location = "login.html";

}