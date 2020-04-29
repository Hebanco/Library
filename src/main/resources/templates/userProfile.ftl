<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    ${message?ifExists}
    <form action="/user/profileSave" method="post">
        <#if isAdmin>
            <h5>${user.username}</h5>
        <#else>
            <input type="text" class="form-control mb-5" name="username" id = "inputUsername" placeholder="Username" value="${user.username!""}"/>
        </#if>
        <div>
            <label for="inputPassword"> Password:  </label>
            <input type="password" class="form-control mb-5" name="password" id = "inputPassword" placeholder="Password"/>
        </div>
        <div>
            <label for="inputEmail">Email: </label>
            <input type="email" class="form-control mb-5" name="email" id = "inputEmail" placeholder="some@some.com" value="${user.email!""}"/>
        </div>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="mx-auto" style="width: 15%"><button type="submit" class="btn btn-primary">Save</button></div>
    </form>
</@c.page>
