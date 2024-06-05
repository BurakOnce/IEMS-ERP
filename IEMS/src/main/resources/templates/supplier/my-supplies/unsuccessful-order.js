
    $(document).ready(function() {
        // Kullanıcı id'sini al
        $.ajax({
            url: 'http://localhost:8080/profile/current-user-id',
            type: 'GET',
            success: function(userId) {
                // Mevcut kullanıcı id'sini al ve supplierId'ye ata
                var supplierId = userId;

                // Sağlanan URL'den verileri al
                $.ajax({
                    url: 'http://localhost:8080/appSupply/supplier/getSuppliesBySupplierUnsuccessful?supplierId=' + supplierId,
                    type: 'GET',
                    success: function(data) {
                        // Alınan verileri tabloya ekle
                        $.each(data, function(index, supply) {
                            var companyName = supply.company ? supply.company.name : 'N/A';

                            $('#example tbody').append('<tr>' +
                                '<td>' + supply.id + '</td>' +
                                '<td>' + supply.productName + '</td>' +
                                '<td>' + supply.contactPersonName + '</td>' +
                                '<td>' + supply.contactPersonPhone + '</td>' +
                                '<td>' + supply.city + '</td>' +
                                '<td>' + supply.categories + '</td>' +
                                '<td>' + supply.quantity + '</td>' +
                                '<td>' + supply.quantityUnits.join(", ") + '</td>' +
                                '<td>' + companyName + '</td>' +
                                '<td>' + supply.orderDate + '</td>' +
                                '<td>' + supply.deliveryTimeInDays + '</td>' +
                                '<td>' + supply.orderStatus.join(", ") + '</td>' +
                                '</tr>');
                        });

                        // DataTable'ı başlat
                        $('#example').DataTable();
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert('Error occurred while getting current user id.');
            }
        });
    });
