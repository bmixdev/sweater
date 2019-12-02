<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#assign
    isDebugMode = true
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        name = "Гость"
        isAdmin = false
    >
</#if>