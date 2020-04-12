<#import "parts/common.ftl" as c>
<@c.page>
    User editor

    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <#if user.isTeacher()><a href="/lesson/new?teacherId=${user.id}">добавить предмет</a>
        <a href="/lesson/list/${user.id}">Lesson list</a></#if>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>