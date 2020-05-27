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
    isLibrarian = currentUser.isLibrarian()
    isLessonOverseer = currentUser.isLessonOverseer()
    isUserOverseer = currentUser.isUserOverseer()
    currentUserId = currentUser.getId()
    >
<#else>
    <#assign
    name = "Пользователь"
    isAdmin = false
    isTeacher = false
    isLibrarian = false
    isLessonOverseer = false
    isUserOverseer = false
    currentUserId = -1
    >
</#if>