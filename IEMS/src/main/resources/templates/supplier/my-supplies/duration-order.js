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
          url: 'http://localhost:8080/appSupply/supplier/getSuppliesBySupplierDuration?supplierId=' + supplierId,
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
                      '<td><button type="button" class="btn durationToCompleteButton" onclick="durationToComplete(' + supply.id + ')">Done</button></td>' +
                      '<td><button type="button" class="btn durationToUnsuccessfulButton" onclick="durationToUnsuccessful(' + supply.id + ')">Fail</button></td>' +
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

  function durationToComplete(id) {
    var supplyId = id;

    $.ajax({
      url: 'http://localhost:8080/appSupply/supplier/toCompleteSupply?id=' + supplyId,
      type: 'GET',
      success: function(response) {
        // İlk istek başarılı olduğunda, ikinci AJAX isteğini gerçekleştir
        $.ajax({
          url: 'http://localhost:8080/appSupply/supplier/closingSupply?id=' + supplyId,
          type: 'GET',
          success: function(response) {
            // İkinci istek de başarılı olduğunda bildirim göster veya sayfayı yenile
            location.reload();
          },
          error: function(xhr, status, error) {
            console.error(xhr.responseText);
            alert('Error occurred while moving supply to close.');
          }
        });
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert('Error occurred while moving supply to complete.');
      }
    });
  }


  function durationToUnsuccessful(id) {
    // Tıklanan satıra ait tedarik bilgilerini al
    var supplyId = id;

    // Kullanıcı id'sini al
    $.ajax({
      url: 'http://localhost:8080/appSupply/supplier/toUnsuccessfulSupply?id=' + supplyId,
      type: 'GET',
      success: function(response) {

        $.ajax({
          url: 'http://localhost:8080/appSupply/supplier/closingSupply?id=' + supplyId,
          type: 'GET',
          success: function(response) {
            // İşlem başarılı olduğunda bildirim göster veya sayfayı yenile
            location.reload(); // Sayfayı yenilemek için, isteğe bağlı olarak
          },
          error: function(xhr, status, error) {
            console.error(xhr.responseText);
            alert('Error occurred while moving supply to close.');
          }
        });
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert('Error occurred while moving supply to unsuccessful.');
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

