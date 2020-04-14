<#import "parts/common.ftl" as c>
<@c.page>
        <form method="post" action="/book">
            <input type="text" name="name" placeholder="Название" value="${book.name}">
            <input type="text" name="author" placeholder="Автор" value="${book.author}">
            <input type="text" name="description" placeholder="Описание" value="${book.descriptions}">
            <div>
                <a href="/book/download/${book.filename}">Скачать</a>
            </div>
            <input type="hidden" name="bookId" value="${book.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Сохранить</button>
        </form>
</@c.page>