<h3>${subGroup.name}</h3>
<div>
    <#list books as book>
        <div class="mt-3">
            <#if book.imageName??>
                    <img src="/img/${book.imageName}" width="7%" height="7%">
            </#if>
            ${book.name}
            ${book.author}
            <a href="/book/${book.id}">Открыть</a>
        </div>
    <#else>
        <div>Книг нету</div>
    </#list>
</div>