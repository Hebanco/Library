<#import "parts/common.ftl" as c>
<@c.page>
    <#if success??>
        <div class="alert alert-success" role="alert">
            Книга добавлена
        </div>
    </#if>

    <form method="post" action="/book/new" enctype="multipart/form-data">

        <div><label>Название:</label></div>
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               value="<#if book??>${book.name}</#if>" name="name" placeholder="Название">
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <div><label class="mt-3">Автор:</label></div>
        <input type="text" class="form-control ${(authorError??)?string('is-invalid', '')}"
               name="author" placeholder="Автор">
        <#if authorError??>
            <div class="invalid-feedback">
                ${authorError}
            </div>
        </#if>
        <div><label class="mt-3">Описание:</label></div>
        <div>
            <textarea name="description"  maxlength="2048" cols="100%"></textarea>
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