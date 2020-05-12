<#import "parts/common.ftl" as c>
<#import "parts/newSubGroup.ftl" as nsg>
<#include "parts/security.ftl">

<@c.page>
    <h4>${lesson.name}</h4>

    <table>
        <tbody>
        <#if myLesson || isAdmin>
            <#if nameError??>
                <@nsg.newSubGroup "${lesson.id}" nameError/>
            <#else>
                <@nsg.newSubGroup "${lesson.id}"/>
            </#if>

        </#if>
        <h5>Список подгрупп</h5>
        <#list subGroups as subGroup>
            <tr>
                <td><a href="/subGroup/${subGroup.id}">${subGroup.name}</a> </td>
                <#if myLesson || isAdmin>
                    <td><a href="/lesson/${lesson.id}/delete/${subGroup.id}"/>Удалить</td>
                </#if>

            </tr>
        </#list>
        </tbody>
    </table>

    <input type="hidden" value="${lesson.id}" name="userId">

    <div>
        <#if isAdmin||isLessonOverseer>
            <div class="container">
                <div class="row">
                    <div class="col">
                    </div>
                    <div class="col">
                    </div>
                    <div class="col">
                    </div>
                    <div class="col">
                        <a href="/lesson/delete/${lesson.id}" class="btn btn-danger ml-5">Удалить занятие</a>
                    </div>
                </div>
            </div>
        </#if>
    </div>

</@c.page>