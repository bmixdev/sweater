<#import "parts/common.ftl" as c>
<@c.page>
<table class="table">
    <caption>List of users</caption>
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <th scope="row">${user?index + 1}</th>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}" class="btn btn-outline-success">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>