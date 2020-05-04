<#import "parts/common.ftl" as c>
<@c.page>
    <h5>Список занятий</h5>
    <ul class="list-group">
    <#list lessons as lesson>

        <a href="/lesson/${lesson.id}" class="list-group-item">${lesson.name}</a>

<#--        <div>-->
<#--            <label>${lesson.name}</label>-->
<#--            <a href="/lesson/${lesson.id}">${lesson.name}</a>-->
<#--        </div>-->
        <#else>
        Занятий нету
    </#list>
    </ul>
</@c.page>