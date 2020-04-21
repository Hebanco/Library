<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/subGroup/subGroupFilter" method="post">
        <input type="text" name="filter" value="${filter}">
        <button type="submit" class="btn btn-secondary">Search almost work</button>
        <input type="hidden" value="${subGroup.id}" name="subGroupId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>

    <form action="/subGroup" method="post">
        <input type="text" name="name" value="${subGroup.name}">
        <table>
            <tbody>
            <#list books as book>
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td><a href="/book/${book.id}">book profile</a> </td>
                    <td><a href="/subGroup/delete/${subGroup.id}/${book.id}">Delete</a> </td>
                </tr>
            <#else>
                <div>No books</div>
            </#list>
            </tbody>
        </table>

        <table>
            <tbody
            <#list possibleBook as pBook>

                <tr>
                    <td>${pBook.name}</td>
                    <td>${pBook.author}</td>
                    <td><a href="/book/${pBook.id}">book profile</a> </td>
                    <td><a href="/subGroup/add/${subGroup.id}/${pBook.id}">Add</a> </td>
                </tr>
            <#else>
                No Books
            </#list>
            </tbody>
        </table>
        <input type="hidden" value="${subGroup.id}" name="subGroupId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>