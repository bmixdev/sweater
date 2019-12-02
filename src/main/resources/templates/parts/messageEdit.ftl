<!-- Modal -->
<div class="modal fade" id="editMessage" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="messageTag">Редактировать запись</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="form-horizontal" role="form" action="/editMessage" method="post">
            <div class="modal-body">
                    <input type="hidden" value="messageId" name="messageId"/>
                    <input type="hidden" value="${_csrf.token}" name="_csrf"/>

                    <div class="form-group">
                        <label class="col-sm control-label" for="tagMessage">Tag</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="tagMsg" id="tagMessage" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm control-label" for="textMessage">Text Message</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="textMsg" id="textMessage" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm control-label" for="authorMessage">Author Message</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="authorMsg" id="authorMessage" />
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/modalEditMessage.js"></script>