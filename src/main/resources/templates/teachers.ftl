<#import "parts/common.ftl" as c>
<@c.page>

    <#list teachers as teacher>

        <div>
            <a href="/lesson/list/${teacher.id}">${teacher.fio}</a>
        </div>

        <#else>
        Учителей нету
    </#list>
</@c.page>