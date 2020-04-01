<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
</head>
<body>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Sing Out"/>
    </form>
</div>

<div>Books list</div>
<div id="books-list">
    <form method="post" action="filter">
        <input type="text" name="filter"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Найти</button>
    </form>

    <#list books as book>
        <div>
            <span>#{book.id}</span>
            <span>${book.name}</span>
            <span>${book.author}</span>
            <a>${book.descriptions}</a>
        </div>
    <#else>
        No books
    </#list>

</div>
<a href="/newBook">Добавить книгу</a>
</body>
</html>