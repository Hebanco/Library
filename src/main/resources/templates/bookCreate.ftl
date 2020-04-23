<#import "parts/common.ftl" as c>
<@c.page>
    <form method="post" action="/book/new" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Название">
        <input type="text" name="author" placeholder="Автор">
        <input type="text" name="description" placeholder="Описание" >
        <div>
            <input type="file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>