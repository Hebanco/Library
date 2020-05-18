<#import "parts/common.ftl" as c>
<#import "parts/newSubGroup.ftl" as nsg>
<#include "parts/security.ftl">

<@c.page>
    <h4>${lesson.name}</h4>

    <#if isLessonOverseer || isAdmin || myLesson>
        <a href="/lesson/redact/${lesson.id}" class="btn btn-link">Редактировать</a>
    </#if>

    <table id="subGroups">
        <tbody>
        <#if myLesson || isAdmin || isLessonOverseer>
            <#if nameError??>
                <@nsg.newSubGroup "${lesson.id}" nameError/>
            <#else>
                <@nsg.newSubGroup "${lesson.id}"/>
            </#if>
        </#if>
        <h5>Список подгрупп</h5>
        <#list subGroups as subGroup>
            <tr>
                <td><a href="/subGroup/${subGroup.id}">${subGroup.name}</a></td>

                <#if isLessonOverseer || isAdmin || myLesson>
                    <td><a href="/lesson/${lesson.id}/delete/${subGroup.id}"/>Удалить</td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>