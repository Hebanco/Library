<#include "security.ftl">
<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="inputName"> Логин: </label>
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   id = "inputName" placeholder="Логин"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div>
            <label for="inputPassword"> Пароль  </label>
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   id = "inputPassword" placeholder="Пароль"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <#if isRegisterForm>
            <div class="mt-2">
                <label for="inputFIO"> ФИО:  </label>
                <input type="text" name="fio" value="<#if user??>${user.fio}</#if>"
                       class="form-control ${(fioError??)?string('is-invalid', '')}"
                       id = "inputFIO" placeholder="ФИО"/>
                <#if fioError??>
                    <div class="invalid-feedback">
                        ${fioError}
                    </div>
                </#if>
            </div>
            <div class="mt-2">
                <label for="inputEmail">Почта </label>
                <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       id = "inputEmail" placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>

            <#list roles as role>
                <div>
                    <label><input type="checkbox" name="${role}"/>${role.getRusName()}</label>
                </div>
            </#list>
        </#if>
        <#if !isRegisterForm> <a href="/recover">Восстановление пароля</a></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="mx-auto" style="width: 15%"><button type="submit" class="btn btn-primary mt-4"><#if isRegisterForm>Создать <#else >Войти</#if></button></div>
    </form>
</#macro>


<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary"><#if currentUser??>Другой пользователь<#else>Войти</#if></button>
    </form>
</#macro>