<#import "parts/common.ftl" as c>
<@c.page>
    Lesson profile

    <form method="post" action="/lesson/new">
        <div><label> Lesson Name :
                <input type="text" name="name" placeholder="name"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"/>
            </label>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><label> Teacher: ${teacher.username} </label></div>
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <div><input type="submit" value="Create" class="btn btn-primary"/></div>
    </form>

</@c.page>