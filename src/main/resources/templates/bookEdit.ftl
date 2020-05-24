<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#if isAdmin>
        <form method="post" action="/book" enctype="multipart/form-data">
            <#if book.imageName??>
                <div>
                    <img src="/img/${book.imageName}">
                </div>
                <div>
                    <#if isAdmin||isLessonOverseer>
                        <a href="/book/deleteImage/${book.id}" class="btn btn-warning mt-2">Удалить обложку</a>
                    </#if>
                </div>
            </#if>
            <div><label>Название:</label></div>
            <input type="text" name="name" placeholder="Название" value="${book.name}">

            <div><label class="mt-3">Автор:</label></div>
            <input type="text" name="author" placeholder="Автор" value="${book.author}">

            <div><label>Описание:</label></div>
            <div>
                <textarea name="description" maxlength="2048" cols="100%">${book.description}</textarea>
            </div>


            <#if book.filename??>
                <div>
                    <a href="/book/download/${book.filename}">Скачать</a>
                    <#if isAdmin||isLessonOverseer>
                        <a href="/book/deleteFile/${book.id}" class="btn btn-warning ml-5">Удалить файл</a>
                    </#if>
                </div>
            <#else>
                <div>No file</div>
            </#if>
            <#if isAdmin||isTeacher>
                <div class="container">
                    <div class="row mt-3">
                        <div class="col">
                            <label>Изменить обложку: </label>
                            <input type="file" name="image">
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <label>Изменить файл:</label>
                        <input type="file" name="file">
                    </div>
                    <#--                    <div class="col">-->
                    <#--                        <#if isAdmin||isLessonOverseer>-->
                    <#--                            <a href="/book/deleteFile/${book.id}" class="btn btn-warning ml-5">Удалить файл</a>-->
                    <#--                        </#if>-->
                    <#--                    </div>-->
                </div>
                </div>

            </#if>
            <input type="hidden" name="bookId" value="${book.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-primary bt-3">Сохранить</button>
                    </div>
                    <div class="col">
                    </div>
                    <div class="col">
                    </div>
                    <div class="col">
                        <#if isAdmin||isUserOverseer>
                            <a href="/book/delete/${book.id}" class="btn btn-danger ml-5">Удалить книгу</a>
                        </#if>
                    </div>
                </div>
            </div>

        </form>
    <#else>
        <#include "parts/bookProfileUser.ftl">
    </#if>
</@c.page>