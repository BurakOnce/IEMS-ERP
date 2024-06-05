
    var globalCompanyId;

    $(document).ready(function() {
        // companyId'yi alma işlemi
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/profile/current-user-id",
            success: function(userId) {
                console.log("Current user ID:", userId);

                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/appUser/free/getCompanyId?userId=" + userId,
                    success: function(companyId) {
                        console.log("Company ID:", companyId);
                        globalCompanyId = companyId;

                        // Sağlanan URL'den verileri al
                        $.ajax({
                            url: 'http://localhost:8080/appSupply/free/getSupply/open?companyId='+globalCompanyId,
                            type: 'GET',
                            success: function(data) {

                                $.each(data, function(index, supply) {
                                    var companyName = supply.company ? supply.company.name : 'N/A';


                                    $('#table tbody').append('<tr>' +
                                        '<td>' + supply.id + '</td>' +
                                        '<td>' + supply.productName + '</td>' +
                                        '<td>' + supply.contactPersonName + '</td>' +
                                        '<td>' + supply.contactPersonPhone + '</td>' +
                                        '<td>' + supply.categories + '</td>' +
                                        '<td>' + supply.quantity + '</td>' +
                                        '<td>' + supply.quantityUnits.join(", ") + '</td>' +
                                        '<td>' + companyName + '</td>' +
                                        '<td>' + supply.orderDate + '</td>' +
                                        '<td>' + supply.orderStatus.join(", ") + '</td>' +
                                        '<td><button type="button" class="btn closeSupplyBtn" onclick="closeSupply(' + supply.id + ')">Close</button></td>' +
                                        '<td><button type="button" class="btn cancelSupplyBtn" onclick="cancelSupply(' + supply.id + ')">Cancel</button></td>' +
                                        '</tr>');
                                });


                                $('#table').DataTable();

                            },
                            error: function(xhr, status, error) {
                                console.error(xhr.responseText);
                            }
                        });
                    },
                    error: function(xhr, textStatus, errorThrown) {
                        console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
                    }
                });
            },
            error: function(xhr, textStatus, errorThrown) {
                console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
            }
        });
    });

    function cancelSupply(id) {
        // Supply silme işlemi için AJAX isteği yapılabilir
        $.ajax({
            url: 'http://localhost:8080/appSupply/manager/deleteSupply?id=' + id,
            type: 'DELETE',
            success: function(response) {
                // Başarılı bir şekilde silindikten sonra tabloyu güncelleyebilirsiniz
                alert('Sipariş başarıyla silindi.');
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                // Hata durumunda kullanıcıya bir hata mesajı gösterebilirsiniz
                alert('Supply silinemedi. Bir hata oluştu.');
            }
        });
    }

    function closeSupply(id) {
        // Tıklanan satıra ait tedarik bilgilerini al

        // Kullanıcı id'sini al
        $.ajax({
            url: 'http://localhost:8080/appSupply/manager/closingSupply?id=' + id,
            type: 'GET',
            success: function(response) {
                alert('Supply başarıyla kapandı.');
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert('Error occurred while moving supply to unsuccessful.');
            }
        });



    }

