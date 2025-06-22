<%--
  Created by IntelliJ IDEA.
  User: mello
  Date: 6/22/25
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Cadastrar Novo Cliente</h2>
    <form id="customerForm">
        <div class="mb-3">
            <label for="firstName" class="form-label">Nome</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Sobrenome</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">E-mail</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>
    <div id="responseMessage" class="mt-3"></div>
</div>

<script src="${pageContext.request.contextPath}/pages/customer-form/customer-form.js"></script>
</body>
</html>

