<#import "login.ftl" as l>
<#include "security.ftl">


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Библиотека</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/lesson/list">Занятия<span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/user/teachers">Преподаватели<span class="sr-only">(current)</span></a>
            </li>

            <#if isTeacher>
                <li class="nav-item">
                    <a class="nav-link" href="/lesson/myLessons">Мои занятия<span class="sr-only">(current)</span></a>
                </li>
            </#if>
            <#if isAdmin || isUserOverseer || isLessonOverseer>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Пользователи
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/user">Список пользователей</a>
                        <#if isAdmin || isUserOverseer><a class="dropdown-item" href="/registration">Новый пользователь</a></#if>
                    </div>
                </li>
            </#if>

            <#if isAdmin || isTeacher || isLibrarian>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Книги
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/book/list">Список книг</a>
                        <a class="dropdown-item" href="/book/new">Новая книга</a>
                    </div>
                </li>
            </#if>

<#--            <#if isAdmin || isLessonOverseer>-->
<#--                <li class="nav-item dropdown">-->
<#--                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
<#--                        Отчеты-->
<#--                    </a>-->
<#--                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">-->
<#--                        <a class="dropdown-item" href="/report/lessons">Занятия и преподаватели</a>-->
<#--                        <a class="dropdown-item" href="/report/books">Книги</a>-->
<#--                        <a class="dropdown-item" href="/report/files">Файлы</a>-->
<#--                    </div>-->
<#--                </li>-->
<#--            </#if>-->
        </ul>

        <div class="navbar-text mr-3"><#if currentUser??> <a class="nav-link" href="/user/myProfile">${fio}</a><#else>${name}</#if></div>
        <@l.logout/>
    </div>
</nav>