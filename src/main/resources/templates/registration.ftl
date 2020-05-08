<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<#--    Add new user-->
    <#if message??>
        ${message}
    </#if>

    <#if success??>
        <div class="alert alert-success" role="alert">
            Пользователь создан
        </div>
    </#if>

    <@l.login "/registration" true/>
</@c.page>