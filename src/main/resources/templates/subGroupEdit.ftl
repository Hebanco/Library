<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

    <#if myLesson || isAdmin>
        <form action="/subGroup/${subGroup.id}" method="get">
            <input type="text" name="filter" value="${filter}">
            <button type="submit" class="btn btn-secondary">Искать</button>
        </form>

        <form action="/subGroup" method="post">
            <input type="text" name="name" value="${subGroup.name}">
            <table>
                <tbody>
                <#list books as book>
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td><a href="/book/${book.id}">Открыть</a> </td>
                        <td><a href="/subGroup/delete/${subGroup.id}/${book.id}">Удалить</a> </td>
                    </tr>
                <#else>
                    <div>Книг нету</div>
                </#list>
                </tbody>
            </table>

            <div class="mt-4">Возможные книги:</div>
            <table>
                <tbody
                <#list possibleBook as pBook>

                    <tr>
                        <td>${pBook.name}</td>
                        <td>${pBook.author}</td>
                        <td><a href="/book/${pBook.id}">Открыть</a> </td>
                        <td><a href="/subGroup/add/${subGroup.id}/${pBook.id}">Добавить</a> </td>
                    </tr>
                <#else>
                    Книг нету
                </#list>
                </tbody>
            </table>
            <input type="hidden" value="${subGroup.id}" name="subGroupId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </form>
    <#else>
        <#include "parts/subGroupForUser.ftl">
    </#if>
</@c.page>