// Captura do characterId da URL
const params = new URLSearchParams(window.location.search);
const characterId = params.get("id");

// Carregar dados do personagem principal
async function loadCharacter() {
  const response = await fetch(`http://localhost:8080/characters/${characterId}`, { credentials: "include" });
  if (!response.ok) return;

  const character = await response.json();
  document.getElementById("characterName").innerText = character.name || "Desconhecido";
  document.getElementById("characterImage").src = character.imageUrl || "default.png";
  document.getElementById("gameName").innerText = character.game?.name || "";
}

// Criar postagem
async function createPost() {
  const content = document.getElementById("postContent").value;
  if (!content.trim()) return alert("Digite algo para postar!");

  const response = await fetch(`http://localhost:8080/posts`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ content }),
    credentials: "include"
  });

  if (response.ok) {
    document.getElementById("postContent").value = "";
    loadFeed();
  } else {
    alert("Erro ao criar postagem.");
  }
}

// Curtir postagem
async function likePost(postId) {
  await fetch(`http://localhost:8080/posts/${postId}/like`, { method: "POST", credentials: "include" });
  loadFeed();
}

// Comentar postagem
async function commentPost(postId) {
  const text = document.getElementById("comment-" + postId).value;
  if (!text.trim()) return;

  await fetch(`http://localhost:8080/comments/${postId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ content: text }),
    credentials: "include"
  });

  document.getElementById("comment-" + postId).value = "";
  loadFeed();
}

// Carregar feed dos personagens que sigo + próprio personagem
async function loadFeed() {
  const response = await fetch(`http://localhost:8080/posts/feed`, { credentials: "include" });
  if (!response.ok) return;

  const posts = await response.json();
  const list = document.getElementById("feedList");
  list.innerHTML = "";

  posts.forEach(p => {
    const name = p.characterName || "Desconhecido";
    const image = p.characterImageUrl || "default.png";

    list.innerHTML += `
      <div class="card p-3 mb-2 d-flex align-items-start">
        <img src="${image}" width="60" class="rounded-circle me-2">
        <div>
          <h5>${name}</h5>
          <p>${p.content}</p>
          <button onclick="likePost(${p.id})" class="btn btn-sm btn-primary">Curtir</button>
          <input type="text" id="comment-${p.id}" class="form-control mt-2" placeholder="Comentar...">
          <button onclick="commentPost(${p.id})" class="btn btn-sm btn-secondary mt-1">Comentar</button>
        </div>
      </div>`;
  });
}

// Buscar personagens
async function searchCharacters() {
  const query = document.getElementById("searchInput").value;
  const response = await fetch(`http://localhost:8080/characters/search?name=${query}`, { credentials: "include" });
  if (!response.ok) { alert("Erro na busca."); return; }

  const results = await response.json();
  const list = document.getElementById("searchResults");
  list.innerHTML = "";

  results.forEach(c => {
    const img = c.imageUrl || "default.png";
    list.innerHTML += `
      <div class="character-card card p-2 mb-2 d-flex align-items-center">
        <img src="${img}" width="60" class="rounded-circle me-2">
        <div>
          <p>${c.name}</p>
          <button class="btn btn-sm btn-primary" onclick="followCharacter(${c.id})">Seguir</button>
          <button class="btn btn-sm btn-secondary" onclick="viewProfile(${c.id})">Ver perfil</button>
        </div>
      </div>`;
  });
}

// Seguir personagem
async function followCharacter(targetId) {
  const response = await fetch(`http://localhost:8080/characters/follow/${targetId}`, { method: "POST", credentials: "include" });
  if (response.ok) {
    alert("Agora você segue esse personagem");
    loadFeed();
  } else {
    alert("Erro ao seguir o personagem");
  }
}

// Abrir perfil
function viewProfile(id) {
  window.location.href = `profile.html?id=${id}`;
}

// Carregar lives
async function loadLives() {
  const response = await fetch("http://localhost:8080/lives/active", { credentials: "include" });
  if (!response.ok) return;

  const lives = await response.json();
  const list = document.getElementById("liveList");
  list.innerHTML = "";

  lives.forEach(live => {
    const img = live.characterImageUrl || "default.png";
    const name = live.characterName || "Desconhecido";

    list.innerHTML += `
      <div class="card mb-2 p-2 d-flex align-items-start">
        <img src="${img}" width="50" class="rounded-circle me-2">
        <div>
          <h5>${live.title}</h5>
          <p>${name}</p>
          <span class="badge bg-danger">AO VIVO</span>
        </div>
      </div>`;
  });
}

// Iniciar live
async function startLive() {
  const title = document.getElementById("liveTitle").value;
  if (!title.trim()) return;

  await fetch(`http://localhost:8080/lives/start/${characterId}?title=${title}`, { method: "POST", credentials: "include" });
  alert("Live iniciada!");
  loadLives();
}

// Mostrar personagens que sigo
async function showFollowing() {
  const response = await fetch(`http://localhost:8080/characters/${characterId}/following`, { credentials: "include" });
  if (!response.ok) return;

  const characters = await response.json();
  const list = document.getElementById("followingList");
  list.innerHTML = "";

  characters.forEach(c => {
    const img = c.imageUrl || "default.png";
    list.innerHTML += `
      <div class="card p-2 mb-2 d-flex align-items-center">
        <img src="${img}" width="50" class="rounded-circle me-2">
        <div>
          <h5>${c.name}</h5>
          <button onclick="viewProfile(${c.id})">Ver perfil</button>
        </div>
      </div>`;
  });
}

// Inicialização
loadCharacter();
loadFeed();
loadLives();