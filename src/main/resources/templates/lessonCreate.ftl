<#import "parts/common.ftl" as c>
<@c.page>
    <#if success??>
        <div class="alert alert-success" role="alert">
            Пользователь создан
        </div>
    </#if>
    <form method="post" action="/lesson/new">
        <div><label> Название занятия:
                <input type="text" name="name" placeholder="Название"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"/>
            </label>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><label> Преподаватель: ${teacher.fio} </label></div>
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <div><input type="submit" value="Создать" class="btn btn-primary"/></div>
    </form>
</@c.page>