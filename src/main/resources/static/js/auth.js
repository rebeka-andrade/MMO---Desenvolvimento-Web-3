document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();

  const user = {
    username: document.getElementById("name").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value
  };

  const response = await fetch("http://localhost:8080/auth/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(user)
  });

  if (response.ok) {
    alert("Cadastro realizado!");
    window.location.href = "login.html";
  } else {
    alert("Erro ao cadastrar.");
  }
});


ddocument.getElementById("loginForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams({
      username: email,
      password: password
    })
  });

  if (response.ok) {
    window.location.href = "gamer.html";
  } else {
    alert("Credenciais inválidas.");
  }
});