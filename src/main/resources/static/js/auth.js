document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();
  const user = {
    username: document.getElementById("name").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value
  };

  const response = await fetch("http://localhost:8080/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

  if (response.ok) {
    alert("Cadastro realizado com sucesso!");
    window.location.href = "index.html";
  } else {
    alert("Erro ao cadastrar usuário.");
  }
});

document.getElementById("loginForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  const response = await fetch("http://localhost:8080/characters", {
    method: "GET",
    headers: {
      "Authorization": "Basic " + btoa(username + ":" + password)
    }
  });

  if (response.ok) {
    alert("Login realizado com sucesso!");
    localStorage.setItem("username", username);
    localStorage.setItem("password", password);
    window.location.href = "gamer.html";
  } else {
    alert("Credenciais inválidas.");
  }
});