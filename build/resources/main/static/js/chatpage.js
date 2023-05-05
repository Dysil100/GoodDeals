    document.addEventListener("DOMContentLoaded", function(event) {
    // Définition de la fonction pour récupérer les messages
    function updateMessages() {
        // Récupération des valeurs des champs "sender" et "receiver"
        const sender = document.getElementById("sender").value;
        const receiver = document.getElementById("receiver").value;

        // Appel de l'API RESTful avec fetch() pour récupérer les messages correspondants
        fetch(`/messages/${sender}/${receiver}`)
            .then(response => response.json()) // Transforme la réponse en objet JSON
            .then(data => {
                // Met à jour l'attribut "messages" du formulaire avec la réponse de l'API
                document.getElementById("chat-body").setAttribute("th:object", JSON.stringify(data));
            })
            .catch(error => console.error(error)); // Gestion des erreurs éventuelles
    }

    // Actualisation des messages toutes les deux secondes
    setInterval(updateMessages, 2000);
});