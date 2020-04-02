<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>SubGroup</title>
</head>
<body>
SubGroup profile
<form action="newLesson" method="post">
    <div><label> Lesson Name : <input type="text" name="username"/> </label></div>
    <div><label> Teacher: <input type="text" name="teacherId"/></label>

    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#list subGroups as group>
        <strong>${group.name}</strong>
    </#list>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>