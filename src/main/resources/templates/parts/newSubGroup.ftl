<#macro newSubGroup lesson>
    <form method="post" action="${lesson}/addSubGroup">
        <div>
            <label>New SubGroup:<input type="text" name="name" placeholder="name"/></label>
            <input type="hidden" name="lessonId" value=${lesson}/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" class="btn btn-secondary" value="Add">
        </div>
    </form>
</#macro>