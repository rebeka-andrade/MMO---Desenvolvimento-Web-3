// mostrar formulário
function showCharacterForm() {
  const form = document.getElementById("characterForm");
  form.style.display = form.style.display === "none" ? "block" : "none";
}

async function createCharacter() {

  const character = {
    name: document.getElementById("charName").value,
    role: document.getElementById("charRole").value,
    imageUrl: document.getElementById("charImage").value,
    game: {
      id: document.getElementById("charGame").value
    }
  };

  const response = await fetch("http://localhost:8080/characters", {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(character)
  });

  if (response.ok) {
    alert("Personagem criado!");
    loadCharacters();
  } else {
    alert("Erro ao criar personagem");
  }
}

// carregar personagens
async function loadCharacters() {

  const response = await fetch("http://localhost:8080/characters", {
    credentials: "include"
  });

  if (!response.ok) {
    alert("Erro ao carregar personagens");
    return;
  }

  const characters = await response.json();

  const list = document.getElementById("characterList");
  list.innerHTML = "";

  characters.forEach(c => {
    list.innerHTML += `
      <div class="card mb-2 p-2 character-card"
       onclick="openCharacter(${c.id})">

      <img src="${c.imageUrl}" width="80">

      <h5>${c.name}</h5>
      <p>${c.role}</p>
      <p>${c.game.name}</p>

  </div>
    `;
  });
}

function openCharacter(characterId) {

  window.location.href = "character.html?id=" + characterId;

}

// carregar quando abrir página
loadCharacters();