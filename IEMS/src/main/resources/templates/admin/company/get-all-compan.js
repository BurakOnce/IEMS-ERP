
    $(document).ready(function() {
        // Sağlanan URL'den verileri al
        $.ajax({
            url: 'http://localhost:8080/appCompany/admin/getCompany/all',
            type: 'GET',
            success: function(data) {
                // Alınan verileri tabloya ekle
                $.each(data, function(index, company) {


                    $('#example tbody').append('<tr>' +
                        '<td>' + company.id + '</td>' +
                        '<td>' + company.name + '</td>' +
                        '<td>' + company.sector + '</td>' +
                        '<td>' + company.town + '</td>' +
                        '<td>' + company.city + '</td>' +
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
