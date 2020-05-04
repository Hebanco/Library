<h3>${subGroup.name}</h3>
<table>
    <tbody>
    <#list books as book>
        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td><a href="/book/${book.id}">Открыть</a> </td>
        </tr>
    <#else>
        <div>Книг нету</div>
    </#list>
    </tbody>
</table>