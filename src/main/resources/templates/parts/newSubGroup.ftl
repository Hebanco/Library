<#macro newSubGroup lesson>
    <form method="post" action="${lesson}/addSubGroup">
        <div>
            <label>Name:<input type="text" name="name"/></label>
            <input type="hidden" name="lessonId" value=${lesson}/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Add">
        </div>
    </form>
</#macro>