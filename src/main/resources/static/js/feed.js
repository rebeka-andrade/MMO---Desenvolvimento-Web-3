async function loadPosts() {

    const res = await fetch(API + "/posts/character/1");

    const posts = await res.json();

    const container = document.getElementById("posts");

    container.innerHTML = "";

    posts.forEach(p => {

        container.innerHTML += `
<div class="card bg-secondary mb-3">
<div class="card-body">

${p.content}

</div>
</div>
`;

    });

}

async function createPost() {

    const content = document.getElementById("postContent").value;

    await fetch(API + "/posts", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            content,
            character: { id: 1 }

        })

    });

    loadPosts();

}

loadPosts();