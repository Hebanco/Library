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
        <#if authorError??>
            <div class="invalid-feedback">
                ${authorError}
            </div>
        </#if>
        <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
               name="descriptions" placeholder="Описание" >
        <#if descriptionsError??>
            <div class="invalid-feedback">
                ${descriptionsError}
            </div>
        </#if>
        <div>
            <input type="file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>