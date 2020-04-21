<#import "parts/common.ftl" as c>
<@c.page>
    <form method="post" action="/book">
        <input type="text" name="name" placeholder="Название" value="${book.name}">
        <input type="text" name="author" placeholder="Автор" value="${book.author}">
        <input type="text" name="description" placeholder="Описание" value="${book.descriptions}">
        <#if book.filename??>
            <div>
                <a href="/book/download/${book.filename}">Скачать</a>
            </div>

            <#else>
            <div>No file</div>
        </#if>
        <input type="hidden" name="bookId" value="${book.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>