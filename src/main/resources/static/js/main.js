// DO GET EMPLOYEES
function doGetEmployees(page) {
    var emplSearchName = $("#empNameSearch").val();
    $.ajax({
        type : "GET",
        url : "../employees?page=" + page + "&search=" + emplSearchName,
        success: function(result){
            $('#getResultDiv ul').empty();
            $('#customerTable tbody').empty();

            $.each(result, function(index, employee){

                var customerRow = '<tr>' +
                    '<td>' +
                    '<input type="hidden" value=' + employee.empID + '>'+
                    '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#viewModal">\n' +
                    '       View\n' +
                    '    </button></td>' +
                    '<td>' +
                    '<input type="hidden" value=' + employee.empID + '>'+
                    '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">\n' +
                    '        Edit\n' +
                    '    </button></td>' +
                    '<td>' + employee.empID + '</td>' +
                    '<td>' + employee.empName + '</td>' +
                    '<td>' + (employee.empActive ? "Yes" : "No") + '</td>' +
                    '<td>' + employee.empDepartment.name + '</td>' +
                    '<td class="text-center">' +
                    '<input type="hidden" value=' + employee.empID + '>' +
                    '<a>' +
                    '<span class="glyphicon glyphicon-remove"></span>' +
                    '</a>' +
                    '</td>' +
                    '</tr>';

                $('#customerTable tbody').append(customerRow);
            });

        },
        error : function(e) {
            $("#resultMsgDiv").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
doGetEmployees(0);

//DO GET DEPARTMENTS
function doGetDepartments() {
    $.ajax({
        type : "GET",
        url : "../departments",
        success: function(result){
            $('#empDepartmentForm').empty();
            $('#empDepartment').empty();

            $.each(result, function(index, department){
                var departmentsHtml =
                    '<option data-id='+ department.depID + ' value=' + department.name + '>' + department.name + '</option>'
                $('#empDepartmentForm').append(departmentsHtml);
                $('#empDepartment').append(departmentsHtml);
            });

        },
        error : function(e) {
            $("#resultMsgDiv").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
doGetDepartments();

// DO DELETE
$('#customerTable').on("click","a",function() {

    var employeeId = $(this).parent().find('input').val();
    var workingObject = $(this);
    $.ajax({
        type : "DELETE",
        url : "../employees/" + employeeId,
        success: function(){
            showSuccessMessage("Employee with ID " + employeeId + " deleted.");
            workingObject.closest("tr").remove();
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
});

// DO CREATE
function addEmpDepartment(){

    var formData = {
        name : $("#name").val(),
        };
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "../departments",
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            showSuccessMessage("Department " + result.name + " created." );
            doGetEmployees(0);
            $('#empDepartment').append('<option data-id='+ result.depID + ' value=' + result.name + '>' + result.name + '</option>');
        },
        error : function(e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

    $("#name").val('');
}

// DO CREATE
function addEmployee(){

    if (!$("#empName").val() || !$("#empDepartment").val()) {
        showErrorMessage("Employee name or department missing." )
        return;
    }

    var formData = {
        empName : $("#empName").val(),
        empActive : $('#empActive').prop('checked'),
        empDepartment : {
            depID: $("#empDepartment").find(':selected').data('id'),
            name: $("#empDepartment").val()
        }
    };
     $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "../employees",
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            showSuccessMessage("Employee " + result.empName + " created." )
            doGetEmployees(0);
        },
        error : function(e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

    resetData();

}

function resetData() {
    $("#empName").val('');
    $("#empActive").val('');
}

// DO VIEW
$('#customerTable').on("click","button",function() {

    var empID = $(this).parent().find('input').val();
    if (empID) {
        $('#viewModal .modal-body p:first-child').append('<span>Test '+ empID +'</span>');
        $.ajax({
            type : "GET",
            url : "../employees/" + empID,
            success: function(response){
                $('#viewModal .modal-body').append(
                    '<p>empID:'+ response.empID +'</p>\n' +
                    '<p>empName:'+ response.empName +'</p>\n' +
                    '<p>empActive:'+ (response.empActive ? "Yes" : "No") +'</p>\n' +
                    '<p>empDepartment:'+ response.empDepartment.name +'</p>'
                );
                $('#emplIdForm').text(response.empID);
                $('#empNameForm').val(response.empName);
                $('#empActiveForm').val(response.empActive);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});

$('#viewModal, #editModal').on('hidden.bs.modal', function () {
    $('#viewModal .modal-body').text('');
});

//DO EDIT
function editEmployee() {

    var formData = {
        empID : $('#emplIdForm').text(),
        empName : $("#empNameForm").val(),
        empActive :  $('#empActiveForm').prop('checked'),
        empDepartment : {
            depID: $("#empDepartment").find(':selected').data('id'),
            name: $("#empDepartmentForm").val()
        }
    };
    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "../employees/",
        data: JSON.stringify(formData),
        dataType : 'json',
        success: function(response){
            $('#editModal').modal('toggle');
            showSuccessMessage("Employee with ID " + response.empID + " updated." );
            doGetEmployees(0);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function showErrorMessage(message) {
    $("#resultMsgDiv").html("<p style='background-color:#d16625; color:white; padding:20px 20px 20px 20px'>" +
        message +
        "</p>");

    setTimeout(function(){ $("#resultMsgDiv").html('')}, 5000); // hide after 5 sec
}

function showSuccessMessage(message) {
    $("#resultMsgDiv").html("<p style='background-color:#92d141; color:white; padding:20px 20px 20px 20px'>" +
        message +
        "</p>");

    setTimeout(function(){ $("#resultMsgDiv").html('')}, 5000); // hide after 5 sec
}