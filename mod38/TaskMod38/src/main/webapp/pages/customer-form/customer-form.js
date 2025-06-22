document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("customerForm");
    const responseMessage = document.getElementById("responseMessage");

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const customerData = {
            firstName: form.firstName.value,
            lastName: form.lastName.value,
            email: form.email.value
        };

        fetch("/TaskMod38-1.0-SNAPSHOT/api/customers/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customerData)
        }).then(response => {
            if (response.ok) {
                responseMessage.innerHTML = '<div class="alert alert-success">Cliente cadastrado com sucesso!</div>';
                form.reset();
            } else {
                responseMessage.innerHTML = '<div class="alert alert-danger">Erro ao cadastrar cliente.</div>';
            }
        }).catch(() => {
            responseMessage.innerHTML = '<div class="alert alert-danger">Erro de rede.</div>';
        });
    });
});
