<#macro newSubGroup lesson nameError="">
    <form method="post" <#--action="${lesson}/addSubGroup"-->>
        <div>
            <label>New SubGroup:<input type="text" name="name"  placeholder="name"
                   class="form-control ${(nameError?has_content)?string('is-invalid', '')}"/></label>
            <#if nameError?has_content>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
            <input type="hidden" name="lessonId" value=${lesson}/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" class="btn btn-secondary" value="Add">
        </div>
    </form>
</#macro>