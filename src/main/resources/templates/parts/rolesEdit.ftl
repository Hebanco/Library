<#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role.getRusName()}</label>
    </div>
</#list>