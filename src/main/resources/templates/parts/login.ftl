<#macro login path headText isRegisterForm >
<div class="card bg-light mb-3" style="max-width: 32rem;">
    <div class="card-header">${headText}</div>
    <div class="card-body">
        <form action="${path}" method="post">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">User Name :</label>
                <div class="col-sm-10">
                     <input type="text"
                            name="username"
                            class="form-control ${(usernameError??)?string('is-invalid','')}"
                            placeholder="User name"
                            value="<#if user??>${user.username}</#if>"
                     >
                     <#if usernameError??>
                         <div class="invalid-feedback">
                            ${usernameError}
                         </div>
                     </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Password:</label>
                <div class="col-sm-10">
                    <input type="password"
                           name="password"
                           class="form-control ${(passwordError??)?string('is-invalid','')}"
                           placeholder="Password"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Password:</label>
                <div class="col-sm-10">
                    <input type="password"
                           name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid','')}"
                           placeholder="Retype password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
            </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Email:</label>
                <div class="col-sm-10">
                    <input type="email"
                           name="email"
                           class="form-control ${(emailError??)?string('is-invalid','')}"
                           placeholder="some@some.com"
                           value="<#if user??>${user.email}</#if>"
                    />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <div class="g-recaptcha"
                     data-sitekey="6LfnSMUUAAAAAJadwQ-0p2vTjqhyChh3ZptSWcbQ"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </  div>
                </#if>
            </div>
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group row">
                <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-primary mb-2"><#if isRegisterForm>Create<#else>Sign In</#if></button>
                </div>
            </div>
        </form>
    </div>
</div>
</#macro>

<#macro logout>
<#if !Session.SPRING_SECURITY_CONTEXT??>
    <a class="btn btn-sm btn-outline-secondary" href="/login">Sign In</a>
<#else>
    <form action="/logout" method="post" class="form-inline">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-sm btn-outline-secondary">Sign Out</button>
    </form>
</#if>
</#macro>