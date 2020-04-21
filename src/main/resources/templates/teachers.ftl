<#import "parts/common.ftl" as c>
<@c.page>

    <#list teachers as teacher>

        <div>
            <a href="/lesson/list/${teacher.id}">${teacher.username}</a>
        </div>

        <#else>
        No teachers
    </#list>
</@c.page>