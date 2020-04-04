<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout/>
    </div>

    <div>Books list</div>
    <div id="books-list">
        <form method="get" action="/bookList">
            <input type="text" name="filter" value="${filter}"/>
            <button type="submit">Найти</button>
        </form>

        <#list books as book>
            <div>
                <span>#{book.id}</span>
                <span>${book.name}</span>
                <span>${book.author}</span>
                <a>${book.descriptions}</a>
            </div>
        <#else>
            No books
        </#list>

    </div>
    <a href="/newBook">Добавить книгу</a>
</@c.page>