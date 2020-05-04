<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
    currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = currentUser.getUsername()
    fio = currentUser.getFio()
    isAdmin = currentUser.isAdmin()
    isTeacher = currentUser.isTeacher()
    currentUserId = currentUser.getId()
    >
<#else>
    <#assign
    name = "anon"
    isAdmin = false
    isTeacher = false
    currentUserId = -1
    >
</#if>