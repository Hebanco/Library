<#import "parts/common.ftl" as c>
    <#include "parts/security.ftl">
<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/user">
                <input type="text" name="filter" value="${filter}" placeholder="Имя для поиска"/>
                <button type="submit"  class="btn btn-secondary">Найти</button>
            </form>
        </div>
    </div>

<table>
    <thead>
    <tr>
        <th>Имя</th>
        <th>Логин</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.fio}</td>
            <td class="ml-3">${user.username}</td>
            <#if isAdmin || isUserOverseer || isLessonOverseer>
                <td><a href="/user/profile/${user.id}" class="btn btn-info">Профиль пользовтеля</a> </td>
            </#if>
        </tr>
        <#else>
            <td>Нет подходящих пользователей</td>
    </#list>
    </tbody>
</table>
</@c.page>