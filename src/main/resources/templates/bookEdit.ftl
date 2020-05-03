<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <form method="post" action="/book" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Название" value="${book.name}">
        <input type="text" name="author" placeholder="Автор" value="${book.author}">
        <input type="text" name="descriptions" placeholder="Описание" <#if book.descriptions??>value="${book.descriptions}"</#if>>
        <#if book.filename??>
            <div>
                <a href="/book/download/${book.filename}">Скачать</a>
            </div>
        <#elseif isAdmin>
            <div>
                <input type="file" name="file">
            </div>
        <#else>
            <div>No file</div>
        </#if>
        <input type="hidden" name="bookId" value="${book.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>