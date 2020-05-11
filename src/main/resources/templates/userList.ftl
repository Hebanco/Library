<#import "parts/common.ftl" as c>
    <#include "parts/security.ftl">
<@c.page>
<table>
    <thead>
    <tr>
        <th>Имя</th>
        <th>Роли</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
<#--            <td><#list user.roles as role>${role}<#sep>, </#list> </td>-->
            <#if isAdmin || isUserOverseer><td><a href="/user/${user.id}">Изменить роли</a> </td></#if>
            <#if isAdmin || isUserOverseer || isLessonOverseer><td><a href="/user/profile/${user.id}">Профиль пользовтеля</a> </td></#if>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>