
    $(document).ready(function() {
        // Sağlanan URL'den verileri al
        $.ajax({
            url: 'http://localhost:8080/appUser/admin/getUser/all',
            type: 'GET',
            success: function(data) {
                // Alınan verileri tabloya ekle
                $.each(data, function(index, users) {
                    $('#example tbody').append('<tr>' +
                        '<td>' + users.id + '</td>' +
                        '<td>' + users.name + '</td>' +
                        '<td>' + users.username + '</td>' +
                        '<td>' + users.city + '</td>' +
                        '<td>' + users.town + '</td>' +
                        '<td>' + users.authorities.join(", ") + '</td>' +
                        '<td>' + (users.company ? users.company.name : 'N/A') + '</td>' +
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
