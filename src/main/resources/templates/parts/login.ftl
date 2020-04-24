<#--<#macro login path>-->
<#--    <form action=${path} method="post">-->
<#--        <div><label> User Name : <input type="text" name="username"/> </label></div>-->
<#--        <div><label> Password: <input type="password" name="password"/> </label></div>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <div><input type="submit" class="btn btn-primary" value="Sign In"/></div>-->
<#--    </form>-->
<#include "security.ftl">

<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="inputName"> User Name : </label>
            <input type="text" name="username" value="<#if user?? && !isRegisterForm>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   id = "inputName" placeholder="User name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div>
            <label for="inputPassword"> Password:  </label>
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   id = "inputPassword" placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <#if isRegisterForm>
<#--            <div class="mt-2">-->
<#--                <label for="inputPassword"> Password:  </label>-->
<#--                <input type="password" name="password2"-->
<#--                       class="form-control ${(password2Error??)?string('is-invalid', '')}"-->
<#--                       id = "inputPassword" placeholder="Retype password"/>-->
<#--                <#if password2Error??>-->
<#--                    <div class="invalid-feedback">-->
<#--                        ${password2Error}-->
<#--                    </div>-->
<#--                </#if>-->
<#--            </div>-->
            <div class="mt-2">
                <label for="inputEmail">Email: </label>
                <input type="email" name="email"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       id = "inputEmail" placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </#if>
        <#if !isRegisterForm> <a href="/recover">Password recovery</a></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="mx-auto" style="width: 15%"><button type="submit" class="btn btn-primary mt-4"><#if isRegisterForm>Create <#else >Sing In</#if></button></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary"><#if user??>Sing Out <#else>Log in</#if></button>
    </form>
</#macro>