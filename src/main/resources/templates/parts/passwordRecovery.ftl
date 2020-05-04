<#macro emailInput>
    <form action="/recover" method="post">
        <div><label> Почта : <input type="email" name="email" placeholder="some@some.com"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Send</button>
    </form>
</#macro>


<#macro passwordChange username>
    <form action="/recover/newPassword" method="post">
        <div><label> Логин : ${username} </label></div>
        <div>
            <label for="inputPassword"> Пароль:  </label>
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   id = "inputPassword" placeholder="Пароль"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
            <div class="mt-2">
                <label for="inputPassword"> Пароль:  </label>
                <input type="password" name="password2"
                       class="form-control ${(password2Error??)?string('is-invalid', '')}"
                       id = "inputPassword" placeholder="Повторите пароль"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="username" value="${username}">
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</#macro>