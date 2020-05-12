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
        <input type="text" class="form-control mt-3 ${(authorError??)?string('is-invalid', '')}"
               name="author" placeholder="Автор">
        <#if authorError??>
            <div class="invalid-feedback">
                ${authorError}
            </div>
        </#if>
        <div><label class="mt-3">Описание:</label></div>
        <div>
            <textarea name="descriptions"  maxlength="2048" cols="100%"></textarea>
        </div>
        <div>
            <label class="mt-3">Обложка:</label>
            <input type="file" name="image">
        </div>
        <div>
            <label class="mt-3">Файл:</label>
            <input type="file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary mt-3">Сохранить</button>
    </form>
</@c.page>