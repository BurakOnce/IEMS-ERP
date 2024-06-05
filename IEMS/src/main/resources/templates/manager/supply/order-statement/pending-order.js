
    var globalCompanyId;

    $(document).ready(function() {
        // companyId'yi alma işlemi
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/profile/current-user-id",
            success: function(userId) {
                console.log("Current user ID:", userId);

                // Kullanıcının companyId'sini alma işlemi
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/appUser/free/getCompanyId?userId=" + userId,
                    success: function(companyId) {
                        console.log("Company ID:", companyId);
                         globalCompanyId = companyId;

                        // Sağlanan URL'den verileri al
                        $.ajax({
                            url: 'http://localhost:8080/appSupply/free/getSupply/pending?companyId='+globalCompanyId,
                            type: 'GET',
                            success: function(data) {
                                console.log("Received data:", data);

                                // Tabloya verileri ekle
                                var table = $('#example').DataTable();
                                table.clear().draw(); // Önceki verileri temizle
                                $.each(data, function(index, supply) {
                                    var companyName = supply.company ? supply.company.name : 'N/A';

                                    table.row.add([
                                        supply.id,
                                        supply.productName,
                                        supply.contactPersonName,
                                        supply.contactPersonPhone,
                                        supply.city,
                                        supply.categories,
                                        supply.quantity,
                                        supply.quantityUnits.join(", "),
                                        companyName,
                                        supply.orderDate,
                                        supply.deliveryTimeInDays,
                                        supply.open ? 'Yes' : 'No' // 'open' durumunu metin olarak ekleyin
                                    ]).draw(false);
                                });
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
