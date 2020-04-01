<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<form method="post">
    <input type="text" name="name" placeholder="Название">
    <input type="text" name="author" placeholder="Автор">
    <input type="text" name="description" placeholder="Описание">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>