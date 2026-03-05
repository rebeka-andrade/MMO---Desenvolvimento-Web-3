async function search() {

    const name = document.getElementById("search").value;

    const res = await fetch(API + "/characters/search?name=" + name);

    const characters = await res.json();

    const container = document.getElementById("characters");

    container.innerHTML = "";

    characters.forEach(c => {

        container.innerHTML += `
<div class="card bg-secondary mb-3">

<div class="card-body">

<h4>${c.nickname}</h4>

<p>${c.classType}</p>

<img src="${c.imageUrl}" width="80">

</div>

</div>
`;

    });

}