<#import "parts/common.ftl" as c>
<@c.page>
SubGroup profile
<form action="newGroup" method="post">
    <div><label> Lesson Name : <input type="text" name="name"/> </label></div>
    <div><label> BookID: <input type="text" name="bookId"/></label>

    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#list subGroups as group>
        <strong>${group.name}</strong>
    </#list>
    <div><input type="submit" value="Sign In"/></div>
</form>
</@c.page>