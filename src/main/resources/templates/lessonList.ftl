<#import "parts/common.ftl" as c>
<@c.page>
    <h5>Список занятий</h5>
    <ul class="list-group" id="lesson-list">
    <#list lessons as lesson>


        <a href="/lesson/${lesson.id}" class="list-group-item">${lesson.name}</a>

        <#else>
        Занятий нету
    </#list>
    </ul>
</@c.page>