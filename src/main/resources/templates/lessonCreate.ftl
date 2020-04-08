<#import "parts/common.ftl" as c>
<@c.page>
    Lesson profile

    <form action="/lesson/teacherFilter" method="post">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Search teacher</button>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>

    <form action="newLesson" method="post">
        <div><label> Lesson Name : <input type="text" name="name"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><label> Teacher: <input type="text" name="teacher"/></label></div>

        <table>
            <tbody
            <#list possibleTeacher as pTeacher>
                <tr>
                    <td>${pTeacher.username}</td>
                </tr>
            <#else>
                No Teachers
            </#list>
            </tbody>
        </table>

        <div><input type="submit" value="Create"/></div>
    </form>
</@c.page>