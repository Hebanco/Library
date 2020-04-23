<#import "parts/common.ftl" as c>
<#import "parts/passwordRecovery.ftl" as pr>

<@c.page>
    <#if message??>
        ${message}
    </#if>
    <#if username??>
        <@pr.passwordChange username/>
    </#if>
</@c.page>