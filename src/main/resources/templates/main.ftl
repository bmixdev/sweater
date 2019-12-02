<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" value="${filter!}" class="form-control" placeholder="Search by Tag"/>
            <button type="submit" class="btn btn-primary ml-2"><span class="glyphicon glyphicon-glass" aria-hidden="true"></span>Найти</button>
        </form>
    </div>
</div>
<p>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddMsg" role="button" aria-expanded="false" aria-controls="collapseAddMsg">
        Add new message
    </a>
</p>
<div class="collapse <#if message??>show</#if>" id="collapseAddMsg">
    <div class="card card-body">
        <form method="post" enctype="multipart/form-data">
            <input type="text"
                   name="text"
                   placeholder="Введите сообщения"
                   class="form-control m-2 ${(textError??)?string('is-invalid','')}"
                   value="<#if message??>${message.text}</#if>"/>
            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
            <input type="text"
                   name="tag"
                   placeholder="Тэг"
                   class="form-control m-2 ${(tagError??)?string('is-invalid','')}"
                   value="<#if message??>${message.tag}</#if>"
            />
            <#if tagError??>
            <div class="invalid-feedback">
                ${tagError}
            </div>
            </#if>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="customFile" name="file" onchange="this.style.color = 'black';" >
                <label class="custom-file-label m-2" for="customFile">Выбрать файл</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary m-3"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Добавить</button>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>
    <div class="card text-center shadow-lg p-3 my-5 bg-white rounded">
        <div class="card-header d-flex bd-highlight">
            <div class="p-2 w-100 bd-highlight">${message.tag}</div>
        </div>
        <div class="card-body">
            <#if message.filename??>
            <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <p class="card-text">${message.text}</p>
        </div>
        <div class="card-footer text-muted">
            <span>${message.authorName}</span>
        </div>
    <div class="container">
        <div class="row mt-2">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-outline-success btn-sm ml-3" data-toggle="modal" data-target="#editMessage"
                    data-message-id="${message.id}"
                    data-tag="${message.tag}"
                    data-msg-text="${message.text}"
                    data-msg-author="${message.authorName}">Edit</button>
            <form action="/main/deleteMsg" method="POST">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" value="${message.id}" name="messageId"/>
                <button class="btn btn-outline-danger btn-sm ml-3" type="submit">Delete</button>
            </form>
        </div>
    </div>
    </div>

    <#include "parts/messageEdit.ftl">
    <#else>
    No Message
    </#list>
</div>
</@c.page>
