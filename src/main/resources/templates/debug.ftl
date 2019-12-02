<#import "parts/common.ftl" as c>
<@c.page>

<div class="shadow-sm p-3 mb-5 bg-yellow rounded">Отладка нового фукнционала</div>
</br>

<!--
<form action="/debug/sendMailGunMsg" method="post" class="form-inline">
    <input type="text" name="subject" placeholder="Введите тему сообщения">
    <input type="text" name="textMessage" placeholder="Введите текст сообщения">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-sm btn-outline-secondary">Send</button>
</form>
-->
<div class="card">
    <div class="card-header">
        <div class="row">
            <div class="col-sm-8">Проверка отправки mailGun.com</div>
            <div class="col-sm-4"><button class="btn btn-sm btn-outline-secondary" data-toggle="collapse" data-target="#sendEmail">Развернуть</button></div>
        </div>
    </div>
    <div id="sendEmail" class="collapse">
        <div class="card-body">
            <form action="/debug/sendMailGunMsg" method="post">
                <div class="form-group">
                    <label for="subject">Тема</label>
                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Введите тему сообщения">
                </div>
                <div class="form-group">
                    <label for="textMessage">Тест сообщения</label>
                    <input type="text" class="form-control" id="textMessage" name="textMessage" placeholder="Введите текст сообщения">
                </div>
                <#if messageSendMail??>
                    <div class="alert alert-dark" role="alert">
                        ${messageSendMail?ifExists}
                    </div>
                </#if>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
        </div>
    </div>
</div>

</@c.page>