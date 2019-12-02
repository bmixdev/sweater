<#import "parts/common.ftl" as c>
<@c.page>
<h5>${username}</h5>
${message?ifExists}
<div class="card bg-light mb-3" style="max-width: 32rem;">
    <div class="card-header">Редактирование профиля пользователя</div>
    <div class="card-body">
        <form method="post">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Password:</label>
                <div class="col-sm-10">
                    <input type="password" name="password" class="form-control" placeholder="Password"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Email:</label>
                <div class="col-sm-10">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary mb-2">Save</button>
        </div>
    </div>
    </form>
</div>
</div>
</@c.page>
