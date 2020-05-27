<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h3><a href="/lesson/${subGroup.lesson.id}">${subGroup.lesson.name}</a></h3>

    <#if myLesson || isAdmin>
        <form action="/subGroup/${subGroup.id}" method="get">
            <input type="text" name="filter" value="${filter}">
            <button type="submit" class="btn btn-secondary">Искать</button>
        </form>

        <form action="/subGroup/${subGroup.id}" method="post">
            <div class="mt-2">Название подгруппы:</div>
            <input type="text" name="name" value="${subGroup.name}">
            <div class="mt-4">Текущие книги:</div>

            <#list books as book>
                <div>
                    <#if book.imageName??>
                        <img src="/img/${book.imageName}" width="4%" height="4%">
                    </#if>
                    ${book.name}
                    ${book.author}
                    <a href="/book/${book.id}">Открыть</a>
                    <a href="/subGroup/delete/${subGroup.id}/${book.id}">Удалить</a> </td>
                </div>
            <#else>
                <div>Книг нету</div>
            </#list>


            <div class="mt-4">Возможные книги:</div>
            <#list possibleBook as pBook>

                <div class="mt-3">
                    <#if pBook.imageName??>
                        <img src="/img/${pBook.imageName}" width="4%" height="4%">
                    </#if>
                    ${pBook.name}
                    ${pBook.author}
                    <a href="/book/${pBook.id}">Открыть</a>
                    <a href="/subGroup/add/${subGroup.id}/${pBook.id}">Добавить</a> </td>
                </div>
            <#else>
                Книг нету
            </#list>
            <input type="hidden" value="${subGroup.id}" name="subGroupId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" class="btn btn-primary mt-2">Сохранить</button>
        </form>
    <#else>
        <#include "parts/subGroupForUser.ftl">
    </#if>
</@c.page>