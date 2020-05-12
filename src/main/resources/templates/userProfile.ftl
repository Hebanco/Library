<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    ${message?ifExists}
    <form action="/user/profileSave" method="post">
        <#if isAdmin || isUserOverseer>
            <input type="text" class="form-control mb-5" name="fio" id = "inputFio" placeholder="ФИО" value="${user.fio!""}"/>
            <input type="text" class="form-control mb-3" name="username" id = "inputUsername" placeholder="Логин" value="${user.username!""}"/>
        <#else>
            <h5>${user.fio}</h5>
            <h5>${user.username}</h5>
        </#if>

        <div>
            <label for="inputPassword"> Пароль:  </label>
            <input type="password" class="form-control mb-3" name="password" id = "inputPassword" placeholder="Пароль"/>
        </div>
        <div>
            <label for="inputEmail">Почта: </label>
            <input type="email" class="form-control mb-3" name="email" id = "inputEmail" placeholder="some@some.com" value="${user.email!""}"/>
        </div>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if (isAdmin||isLessonOverseer) && user.isTeacher()><a href="/lesson/new?teacherId=${user.id}" class="btn btn-info mb-3">добавить предмет</a></#if>
        <#if isAdmin||isUserOverseer>
            <#include "parts/rolesEdit.ftl">
        </#if>
        <div class="mx-auto" style="width: 15%">
            <button type="submit" class="btn btn-primary">Сохранить</button>

        </div>
        <div>
            <#if isAdmin||isUserOverseer>
                <div class="container">
                    <div class="row">
                        <div class="col">
                        </div>
                        <div class="col">
                        </div>
                        <div class="col">
                        </div>
                        <div class="col">
                            <a href="/user/delete/${user.id}" class="btn btn-danger ml-5">Удалить пользователя</a>
                        </div>
                    </div>
                </div>
            </#if>
        </div>
    </form>
</@c.page>
