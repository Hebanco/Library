<#import "parts/common.ftl" as c>
<@c.page>
    <#list lessons as lesson>
        <div>
            <label>${lesson.name}</label>
            <a href="/lesson/${lesson.id}">Edit</a>
        </div>
        <#else>
        No lesson
    </#list>
</@c.page>