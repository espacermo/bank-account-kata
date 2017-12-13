// Application constants
var accountApiUri = "api/accounts/";
var accountNumber = "ACC-H2G2";

// Error display
function displayError(message) {
    $("#errorMessage").text(message);
    $(".alert-danger").show();
}

function clearError() {
    $("#errorMessage").text("");
    $(".alert-danger").hide();
}

// Table Operation
var updateOperation = function() {
    clearError();

    $.ajax({
        url : accountApiUri + accountNumber + "/operations"
    }).done(function(data) {
        $(".table-row").remove();
        for (var i = 0; i < data.length; i++) {
            var clone = $("#table-template").clone().attr("class", "table-row");
            clone.find(".date").html(data[i]["date"]);
            clone.find(".amount").html(data[i]["amount"]);
            clone.find(".operationType").html(data[i]["operationType"]);
            clone.appendTo("#operationTable");
        }
    }).fail(function(jqXHR, textStatus) {
        displayError(jqXHR.responseText);
    });
};

// Account action
var requestAccount = function(url, value) {
    clearError();

    var param = {
        "value" : value
    };

    $.ajax({
        url : url,
        method : "PUT",
        headers : {
            "Content-Type" : "application/json"
        },
        dataType : "json",
        data : JSON.stringify(param)
    }).done(function(data) {
        updateOperation();
    }).fail(function(jqXHR, textStatus) {
        var message = (jqXHR.status === 400 ? "Wrong amount" : jqXHR.responseText);
        displayError(message);
    });
};

// On document ready
$(document).ready(function() {
    clearError();

    $("#accountInfo").text(accountNumber);

    $("#depositButton").click(function() {
        var url = accountApiUri + accountNumber + "/operations/deposit";
        requestAccount(url, $("#amountInput").val());
    });

    $("#withdrawButton").click(function() {
        var url = accountApiUri + accountNumber + "/operations/withdraw";
        requestAccount(url, $("#amountInput").val());
    });

    updateOperation();
});