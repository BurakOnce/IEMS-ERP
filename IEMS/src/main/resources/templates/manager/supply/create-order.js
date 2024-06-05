
    var globalCompanyId;

    window.onload = function() {
        // Tarih elemanını seç
        var orderDateInput = document.getElementById('orderDate');
        // Bugünün tarihini al
        var today = new Date().toISOString().split('T')[0];
        // Bugünün tarihini input değeri olarak ayarla
        orderDateInput.value = today;


    };

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/profile/current-user-id",
        success: function(userId) {
            // Kullanıcı ID'sini yazdır
            console.log("Current user ID:", userId);

            // Kullanıcı adını al
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/appUser/free/getCompanyId?userId=" + userId,
                success: function(companyId) {
                    // Şirket ID'sini yazdır
                    console.log("Company ID:", companyId);
                    globalCompanyId=companyId;
                },
                error: function(xhr, textStatus, errorThrown) {
                    console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
                    // Hata durumlarını burada yönetebilirsiniz
                }
            });
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
            // Hata durumlarını burada yönetebilirsiniz
        }
    });




    function createSupply() {
        console.log("Global Company ID:", globalCompanyId);

        var checkbox = $("#open").is(":checked");

        var supplyData = {
            productName: $("#productName").val(),
            contactPersonName: $("#contactPersonName").val(),
            city: $("#city").val(),
            contactPersonPhone: $("#contactPersonPhone").val(),
            companyAddress: $("#companyAddress").val(),
            categories: $("#categories").val(),
            orderDate: $("#orderDate").val(),
            deliveryTimeInDays: $("#deliveryTimeInDays").val(),
            quantityUnits: $("#quantityUnits").val().split(","), // Split authorities by comma
            quantity: $("#quantity").val(),
            orderNotes: $("#orderNotes").val(),
            company: { id: globalCompanyId }, // Company ID'sini doğrudan tanımla
            open: checkbox
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/appSupply/manager/createSupply",
            data: JSON.stringify(supplyData),
            dataType: 'json',

            success: function(data) {
                showInfo();
            },

            error: function(xhr, textStatus, errorThrown) {
                alert('Error! Status: ' + xhr.status + ' - ' + errorThrown);
                // Hata durumlarını burada yönetebilirsiniz
            }
        });
    }


    function resetForm() {
        $('input[type="text"]').val('');
        $('input[type="date"]').val('');
        $('textarea').val('');
        $('input[type="checkbox"]').prop('checked', false);
        $('#quantityUnits').val('');

    }
    function showInfo(){
        var alertDiv = document.querySelector('.alert');
        alertDiv.style.display = 'block';

        // 3 saniye sonra uyarıyı kapat
        setTimeout(function() {
            alertDiv.style.opacity = "0";
            setTimeout(function() {
                alertDiv.style.display = "none";
                alertDiv.style.opacity = "1"; // Yeniden göster
            }, 600);
        }, 3000);
    }
