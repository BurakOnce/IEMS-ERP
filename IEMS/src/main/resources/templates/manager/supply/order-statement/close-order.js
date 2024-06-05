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
                url: 'http://localhost:8080/appSupply/free/getSupply/close?companyId='+globalCompanyId,
                type: 'GET',
                success: function(data) {

                  // Alınan verileri tabloya ekle
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
                            '<td><button type="button" class="btn btn-danger deleteSupplyBtn" onclick="deleteSupply(' + supply.id + ')">Delete</button></td>' +
                            '<td><button type="button" class="btn publicationSupplyBtn" onclick="publicationSupply(' + supply.id + ')">Public</button></td>' +
                            '<td><button type="button" class="btn confirmationSupplyBtn" onclick="confirmationSupply(' + supply.id + ')">Confirm</button></td>' +
                            '</tr>');
                  });

                  // DataTable'ı başlat
                  $('#table').DataTable();

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



  function deleteSupply(id) {
    // Supply silme işlemi için AJAX isteği yapılabilir
    $.ajax({
      url: 'http://localhost:8080/appSupply/manager/deleteSupply?id=' + id,
      type: 'DELETE',
      success: function(response) {
        // Başarılı bir şekilde silindikten sonra tabloyu güncelleyebilirsiniz
        // Örneğin, sayfayı yenileyebilirsiniz:
        location.reload();
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        // Hata durumunda kullanıcıya bir hata mesajı gösterebilirsiniz
        alert('Supply silinemedi. Bir hata oluştu.');
      }
    });
  }

  function publicationSupply(id) {
    $.ajax({
      url: 'http://localhost:8080/appSupply/manager/publicationSupply?id=' + id,
      type: 'GET', // Varsayılan olarak bu isteği PUT metodunu kullanıyorum, ancak isteğinize uygun olarak değiştirebilirsiniz
      success: function(response) {
        $.ajax({
          url: 'http://localhost:8080/appSupply/manager/toPendingSupply?id=' + id,
          type: 'GET',
          success: function(response) {
            $.ajax({
              url: 'http://localhost:8080/appSupply/manager/removeSupplierFromSupply?supplyId=' + id,
              type: 'GET',
              success: function(response) {
                alert('Supply başarıyla yayınlandı.');
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
            alert('Error occurred while moving supply to unsuccessful.');
          }
        });
        // Başarılı bir şekilde güncellendikten sonra gerekli işlemleri yapabilirsiniz
        // Örneğin, bir mesaj gösterebilir veya tabloyu yenileyebilirsiniz
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        // Hata durumunda kullanıcıya bir hata mesajı gösterebilirsiniz
        alert('Supply yayınlanamadı. Bir hata oluştu.');
      }
    });
  }

  function confirmationSupply(id) {
    // Tıklanan satıra ait tedarik bilgilerini al

    // Kullanıcı id'sini al
    $.ajax({
      url: 'http://localhost:8080/appSupply/manager/toConfirmedSupply?id=' + id,
      type: 'GET',
      success: function(response) {
        alert('Supply başarıyla onaylandı.');
        location.reload();
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert('Error occurred while moving supply to unsuccessful.');
      }
    });



  }
