<#import "parts/common.ftl" as c>
<@c.page>
Lesson profile
<form action="newLesson" method="post">
    <div><label> Lesson Name : <input type="text" name="name"/> </label></div>
    <div><label> Teacher: <input type="text" name="teacherId"/></label>

    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</@c.page>