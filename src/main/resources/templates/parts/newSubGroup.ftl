<#macro newSubGroup lesson nameError="">
    <form method="post" action="/lesson/${lesson}">
        <div>
            <label>Новая подгруппа:<input type="text" name="name"  placeholder="Название"
                   class="form-control ${(nameError?has_content)?string('is-invalid', '')}"/></label>
            <#if nameError?has_content>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
            <input type="hidden" name="lessonId" value=${lesson}/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-secondary">Добавить</button>
        </div>
    </form>
</#macro>