<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#if isAdmin>
        <form method="post" action="/book" enctype="multipart/form-data">
            <#if book.imageName??>
                <div>
                    <img src="/img/${book.imageName}">
                </div>
            </#if>
            <input type="text" name="name" placeholder="Название" value="${book.name}">
            <input type="text" name="author" placeholder="Автор" value="${book.author}">
            <div><label>Описание:</label></div>
            <div>
                <textarea name="descriptions" maxlength="2048" cols="100%">${book.descriptions}</textarea>
            </div>


            <#if book.filename??>
                <div>
                    <a href="/book/download/${book.filename}">Скачать</a>
                </div>
            <#else>
                <div>No file</div>
            </#if>
            <#if isAdmin||isTeacher>
                <div>
                    <label>Изменить обложку</label>
                    <input type="file" name="image">
                </div>
                <div>
                    <label>Изменить файл</label>
                    <input type="file" name="file">
                </div>
            </#if>
            <input type="hidden" name="bookId" value="${book.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary bt-3">Сохранить</button>
        </form>
    <#else>
        <#include "parts/bookProfileUser.ftl">
    </#if>
</@c.page>