<#import "parts/common.ftl" as c>
<@c.page>
SubGroup profile
<form action="new" method="post">
    <div><label> SubGroup Name : <input type="text" name="name"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" class="btn btn-primary" value="Sign In"/></div>
</form>
</@c.page>