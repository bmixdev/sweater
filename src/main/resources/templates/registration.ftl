<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
${message?ifExists}
<#assign
    headText = "Add new user"
>
<@l.login "/registration" headText true />
</@c.page>