<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<#assign
    headText = "Login Page"
>
<#if message??>
    <div class="alert alert-${messageType}" role="alert">
        ${message}
    </div>
<#else>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
</#if>
<@l.login "/login" headText false />
</@c.page>
