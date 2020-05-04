<#import "parts/common.ftl" as c>
<@c.page>
    <form method="post" action="/lesson/new">
        <div><label> Название занятия:
                <input type="text" name="name" placeholder="название"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"/>
            </label>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><label> Преподаватель: ${teacher.username} </label></div>
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <div><input type="submit" value="Создать" class="btn btn-primary"/></div>
    </form>
</@c.page>