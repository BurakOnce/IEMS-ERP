
  $(document).ready(function() {
    // Sağlanan URL'den verileri al
    $.ajax({
      url: 'http://localhost:8080/appSupply/free/getSupply/open-pending',
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
                  '<td><button type="button" class="btn noteButton" onclick="noteButton(' + supply.id + ')"><i class="bi bi-info-circle"></i></button></td>' +
                  '<td><button type="button" class="btn takeOrderButton" onclick="takeOrder(' + supply.id + ')">Take</button></td>' +
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

  function takeOrder(id) {
    // Tıklanan satıra ait tedarik bilgilerini al
    var supplyId = id;
    var supplierId;

    // Kullanıcı id'sini al
    $.ajax({
      url: 'http://localhost:8080/profile/current-user-id',
      type: 'GET',
      success: function(userId) {
        // Mevcut kullanıcı id'sini al ve supplierId'ye ata
        supplierId = userId;

        // POST isteği oluşturarak tedarikçi bilgilerini doldur
        $.ajax({
          url: 'http://localhost:8080/appSupply/supplier/updateSupplierAtSupply?supplyId=' + supplyId + '&supplierId=' + supplierId,
          type: 'POST',
          success: function(response) {
            // İşlem başarılı olduğunda alttaki URL'ye istek gönder
            $.ajax({
              url: 'http://localhost:8080/appSupply/supplier/toDurationSupply?id=' + supplyId,
              type: 'GET',
              success: function(response) {
                // İşlem başarılı olduğunda bildirim göster veya sayfayı yenile
                location.reload(); // Sayfayı yenilemek için, isteğe bağlı olarak
              },
              error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert('Error occurred while moving supply to duration.');
              }
            });
          },
          error: function(xhr, status, error) {
            console.error(xhr.responseText);
            alert('Error occurred while filling supplier information.');
          }
        });
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert('Error occurred while getting current user id.');
      }
    });
  }

  function noteButton(id) {
    $.ajax({
      url: 'http://localhost:8080/appSupply/supplier/getNote?supplyId=' + id,
      type: 'GET',
      success: function(note) {
        // Notu modal içerisinde göster
        $('#noteModalBody').text(note); // Notu modal içeriğine yerleştir
        $('#noteModal').modal('show'); // Modalı göster
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert('Error occurred while retrieving the note.');
      }
    });
  }



