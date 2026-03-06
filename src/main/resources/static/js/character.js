function showCharacterForm() {
    document.getElementById("characterForm").style.display = "block";
}

async function createCharacter() {
    const character = {
        name: document.getElementById("charName").value,
        role: document.getElementById("charRole").value,
        imageUrl: document.getElementById("charImage").value,
        game: document.getElementById("charGame").value
    };

    const username = localStorage.getItem("username");
    const password = localStorage.getItem("password");

    const response = await fetch("http://localhost:8080/characters", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa(username + ":" + password)
        },
        body: JSON.stringify(character)
    });

    if (response.ok) {
        alert("Personagem criado!");
        loadCharacters();
    } else {
        alert("Erro ao criar personagem. Código: " + response.status);
    }
}

async function loadCharacters() {
    const username = localStorage.getItem("username");
    const password = localStorage.getItem("password");

    const response = await fetch("http://localhost:8080/characters", {
        method: "GET",
        headers: {
            "Authorization": "Basic " + btoa(username + ":" + password)
        }
    });


    if (!response.ok) {
        alert("Erro ao carregar personagens. Código: " + response.status);
        return;
    }

    const characters = await response.json();
    const list = document.getElementById("characterList");
    list.innerHTML = "";
    characters.forEach(c => {
        const card = document.createElement("div");
        card.className = "character-card";
        card.innerHTML = `
      <img src="${c.imageUrl}" alt="${c.name}" onclick="openCharacter(${c.id})">
      <p>${c.name}</p>
    `;
        list.appendChild(card);
    });
}