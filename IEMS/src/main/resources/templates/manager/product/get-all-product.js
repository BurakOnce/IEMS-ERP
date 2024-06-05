

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

                        $(document).ready(function() {
                            console.log("Company ID in function:", globalCompanyId);

                            // Sağlanan URL'den verileri al
                            $.ajax({
                                url: 'http://localhost:8080/appProduct/manEmp/getProduct/my?companyId='+globalCompanyId,
                                type: 'GET',
                                success: function(data) {

                                    // Add retrieved data to the table
                                    $.each(data, function(index, product) {
                                        var companyName = product.company ? product.company.name : 'N/A';

                                        $('#example tbody').append('<tr>' +
                                            '<td>' + product.id + '</td>' +
                                            '<td>' + product.name + '</td>' +
                                            '<td>' + product.category + '</td>' +
                                            '<td>' + product.quantityUnits.join(", ") + '</td>' +
                                            '<td>' + product.stock + '</td>' +
                                            '<td>' + product.demand + '</td>' +
                                            '<td>' + product.barcode + '</td>' +
                                            '<td>' + product.discount + '</td>' +
                                            '<td>' + product.price + '</td>' +
                                            '<td><button type="button" class="btn ml-auto descriptionButton" onclick="descriptionButton(' + product.id + ')"><i class="bi bi-info-circle"></i> Note</button></td>'+
                                            '<td>' + companyName + '</td>' +
                                            '</tr>');
                                    });

                                    // DataTable'ı başlat
                                    $('#example').DataTable();

                                },
                                error: function(xhr, status, error) {
                                    console.error(xhr.responseText);
                                }
                            });
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

    function descriptionButton(id) {
        $.ajax({
            url: 'http://localhost:8080/appProduct/manEmp/getDescription?productId=' + id,
            type: 'GET',
            success: function(description) {
                // Notu modal içerisinde göster
                $('#noteModalBody').text(description); // Notu modal içeriğine yerleştir
                $('#noteModal').modal('show'); // Modalı göster
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert('Error occurred while retrieving the note.');
            }
        });
    }
