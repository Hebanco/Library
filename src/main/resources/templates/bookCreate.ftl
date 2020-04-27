<#import "parts/common.ftl" as c>
<@c.page>
    <form method="post" action="/book/new" enctype="multipart/form-data">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               value="<#if book??>${book.name}</#if>" name="name" placeholder="Название">
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <input type="text" class="form-control" name="author" placeholder="Автор">
        <div class="invalid-feedback">
            Please choose a username.
        </div>
        <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
               name="description" placeholder="Описание" >
        <#if descriptionError??>
            <div class="invalid-feedback">
                ${descriptionError}
            </div>
        </#if>
        <div>
            <input type="file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>