<#import "parts/common.ftl" as c>
<@c.page>
    Lesson profile

    <form method="post" action="/lesson/new">
        <div><label> Lesson Name : <input type="text" name="name"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><label> Teacher: ${teacher.username} </label></div>
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <div><input type="submit" value="Create"/></div>
    </form>

</@c.page>