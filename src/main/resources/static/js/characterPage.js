const urlParams = new URLSearchParams(window.location.search);
const characterId = urlParams.get("id");

const email = localStorage.getItem("email");
const password = localStorage.getItem("password");
const authHeader = { "Authorization": "Basic " + btoa(email + ":" + password) };

// Criar postagem
async function createPost() {
  const content = document.getElementById("postContent").value;
  const response = await fetch(`http://localhost:8080/posts/${characterId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json", ...authHeader },
    body: JSON.stringify({ content })
  });

  if (response.ok) {
    alert("Postagem criada!");
    loadPosts();
  } else {
    alert("Erro ao criar postagem.");
  }
}

// Carregar feed de postagens
async function loadPosts() {
  const response = await fetch(`http://localhost:8080/posts/${characterId}`, {
    headers: authHeader
  });

  if (!response.ok) {
    alert("Erro ao carregar posts.");
    return;
  }

  const posts = await response.json();
  const list = document.getElementById("postList");
  list.innerHTML = "";
  posts.forEach(p => {
    list.innerHTML += `
      <div class="card mb-2">
        <div class="card-body">
          <h5>${p.character.name}</h5>
          <p>${p.content}</p>
          <button class="btn btn-outline-primary btn-sm">Curtir</button>
          <input type="text" placeholder="Comentar..." class="form-control mt-2">
        </div>
      </div>
    `;
  });
}

// Buscar personagens
async function searchCharacters() {
  const query = document.getElementById("searchInput").value;
  const response = await fetch(`http://localhost:8080/characters/search?name=${query}`, {
    headers: authHeader
  });

  if (!response.ok) {
    alert("Erro na busca.");
    return;
  }

  const results = await response.json();
  const list = document.getElementById("searchResults");
  list.innerHTML = "";
  results.forEach(c => {
    list.innerHTML += `
      <div class="character-card">
        <img src="${c.imageUrl}" alt="${c.name}" onclick="openProfile(${c.id})">
        <p>${c.name}</p>
      </div>
    `;
  });
}

function openProfile(id) {
  window.location.href = `profile.html?id=${id}`;
}

// Carregar posts ao abrir a página
loadPosts();