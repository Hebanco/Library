<#import "parts/common.ftl" as c>
<#import "parts/newSubGroup.ftl" as nsg>
<#include "parts/security.ftl">

<@c.page>
    <form action="/lesson/redact/${lesson.id}" method="post">
        <#if isLessonOverseer || isAdmin>
            <input type="text" name="name" placeholder="Название"
                   class="form-control" value="${lesson.name}" />
            <div>
                <#if isAdmin||isLessonOverseer>
                    <div class="container mt-3">
                        <div class="row">
                            <div class="col">
                                <button type="submit" class="btn btn-primary bt-3">Сохранить</button>
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
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <#else>
            <h4>${lesson.name}</h4>
        </#if>
    </form>
    <table>
        <tbody>
<#--        <#if nameError??>-->
<#--            <@nsg.newSubGroup "${lesson.id}" nameError/>-->
<#--        <#else>-->
<#--            <@nsg.newSubGroup "${lesson.id}"/>-->
<#--        </#if>-->
        <h5>Список подгрупп</h5>
        <#list subGroups as subGroup>
            <tr>
                <td><a href="/subGroup/${subGroup.id}">${subGroup.name}</a> </td>
<#--                <td><a href="/lesson/${lesson.id}/delete/${subGroup.id}"/>Удалить</td>-->
            </tr>
        </#list>
        </tbody>
    </table>

<#--        <input type="hidden" value="${lesson.id}" name="userId">-->


</@c.page>