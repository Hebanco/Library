<#import "parts/common.ftl" as c>
<@c.page>
        <form method="post" action="/book/new" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Название">
            <input type="text" name="author" placeholder="Автор" >
            <input type="text" name="description" placeholder="Описание" >
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Сохранить</button>
        </form>
</@c.page>