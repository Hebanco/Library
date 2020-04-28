<#import "parts/common.ftl" as c>
    <#include "parts/security.ftl">
<@c.page>
    List of users

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list> </td>
            <td><a href="/user/${user.id}">Roles edit</a> </td>
            <#if isAdmin><td><a href="/user/profile/${user.id}">Profile edit</a> </td></#if>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>