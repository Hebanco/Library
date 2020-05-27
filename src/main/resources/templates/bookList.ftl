<#import "parts/common.ftl" as c>

<@c.page>
    <a href="/book/new" class="btn btn-primary mb-3">Добавить книгу</a>

    <div >
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/book/list">
                    <input type="text" name="filter" value="${filter}" placeholder="Название для поиска"/>
                    <button type="submit"  class="btn btn-secondary">Найти</button>
                </form>
            </div>
        </div>
        <div id="books-list">
            <#list books as book>
                <div data-id="${book.id} ">
                    <#if book.imageName??>
                        <img src="/img/${book.imageName}"
                        width="7%"
                        height="7%" class="mt-3"/>
                    </#if>
<#--                    <span id="">#{book.id}</span>-->
                    <span id = "name">${book.name}</span>
                    <span id="author">${book.author}</span>
                    <a href="/book/${book.id}">Открыть</a>
                </div>
            <#else>
                Книг нету
            </#list>
        </div>
    </div>

</@c.page>