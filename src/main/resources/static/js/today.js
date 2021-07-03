$(function() {

    $('.arrow-left').click(function() {
        console.log('arrow-leftクリックされました！');
        $('.localNavigation').toggleClass('isActive');
    })

    $('.trash').click(function() {
        console.log('trashクリックされました！');
        $('#delForm').submit();
    })




    $('.finish').click(function() {
        console.log('finishクリックされました！');
        console.log(this)
        var finishTaskId = this.getAttribute("id")
        console.log(finishTaskId)
        $(this).children('input[name="finish-check_"]').prop('checked', true);

        $("#doneForm_" + finishTaskId).submit();
    })


    $("#add-task").keydown(function(event) {
        if (event.key === "Enter") {
            console.log("Enterキーが押されました");

            var addTask = $('#add-task').val();
            console.log(addTask)
            if (addTask != "") {
                $('#createForm').submit();
            } else {
                console.log("textボックスが空");
                // 警告を出す
                alert('空欄です！');

                // 処理を中断
                return false;
            }
        }
    });

    // テキストボックスにフォーカス時、フォームの背景色を変化
    $('#add-task').focusin(function(e) {
            console.log('フォーカスされました。');
            $('.add-task').toggleClass('isActive');
            $('.icon').toggleClass('isActive');
            console.log(this)

        })
        .focusout(function(e) {
            console.log('フォーカスが外れました。');
            $('.add-task').toggleClass('isActive');
            $('.icon').toggleClass('isActive');
            console.log(this)
        });
});