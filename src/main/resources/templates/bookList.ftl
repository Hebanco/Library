<#import "parts/common.ftl" as c>
>
<@c.page>
    <a href="/book/new" class="btn btn-primary mb-3">Добавить книгу</a>

    <div id="books-list">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/book/list">
                    <input type="text" name="filter" value="${filter}" placeholder="Название для поиска"/>
                    <button type="submit"  class="btn btn-secondary">Найти</button>
                </form>
            </div>
        </div>

        <#list books as book>
            <div>
                <#if book.imageName??>
                    <img src="/img/${book.imageName}">
                </#if>
                <span>#{book.id}</span>
                <span>${book.name}</span>
                <span>${book.author}</span>
                <a href="/book/${book.id}">Открыть</a>
            </div>
        <#else>
            Книг нету
        </#list>
    </div>

</@c.page>