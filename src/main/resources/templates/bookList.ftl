<#import "parts/common.ftl" as c>
>
<@c.page>
    <div id="books-list">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/book/list">
                    <input type="text" name="filter" value="${filter}" placeholder="Search by name"/>
                    <button type="submit"  class="btn btn-secondary">Найти</button>
                </form>
            </div>
        </div>

        <#list books as book>
            <div>
                <span>#{book.id}</span>
                <span>${book.name}</span>
                <span>${book.author}</span>
                <#if book.descriptions??><a>${book.descriptions}</a></#if>
                <a href="/book/${book.id}">Edit</a>
            </div>
        <#else>
            No books
        </#list>

    </div>
    <a href="/book/new">Добавить книгу</a>
</@c.page>