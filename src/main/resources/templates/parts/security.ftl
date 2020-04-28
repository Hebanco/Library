<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
    currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = currentUser.getUsername()
    isAdmin = currentUser.isAdmin()
    currentUserId = currentUser.getId()
    >
<#else>
    <#assign
    name = "anon"
    isAdmin = false
    currentUserId = -1
    >
</#if>