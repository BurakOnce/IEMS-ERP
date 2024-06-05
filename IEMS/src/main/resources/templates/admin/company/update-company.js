
  $(document).ready(function() {
    // Sağlanan URL'den verileri al
    $.ajax({
      url: 'http://localhost:8080/appCompany/admin/getCompany/all',
      type: 'GET',
      success: function(data) {
        // Alınan verileri tabloya ekle
        $.each(data, function(index, company) {
          var deleteCompanyBtn = '<button class="btn btn-danger deleteCompanyBtn" data-company-id="' + company.id + '">Delete</button>';

          $('#example tbody').append('<tr>' +
                  '<td>' + company.id + '</td>' +
                  '<td>' + company.name + '</td>' +
                  '<td>' + company.sector + '</td>' +
                  '<td>' + company.town + '</td>' +
                  '<td>' + company.city + '</td>' +
                  '<td><button class="editButton" data-id="' + company.id + '">Düzenle</button></td>' + // Düzenleme butonu eklendi
                  '<td>' + deleteCompanyBtn + '</td>' +
                  '</tr>');
        });

        // DataTable'ı başlat
        var dataTable = $('#example').DataTable();

        // Düzenleme butonlarına tıklama olayını ekle
        $(document).on('click', '.editButton', function() {
          var id = $(this).data('id');

          // Company düzenlemek için AJAX isteği yap
          $.ajax({
            url: 'http://localhost:8080/appCompany/admin/getCompany/id?id=' + encodeURIComponent(id),
            type: 'GET',
            success: function(company) {
              // Company bilgilerini düzenleme formuna doldur
              $('#editCompanyId').val(company.id);
              $('#editCompanyName').val(company.name);
              $('#editCompanySector').val(company.sector);
              $('#editCompanyTown').val(company.town);
              $('#editCompanyCity').val(company.city);
              $('#editCompanyModal').show(); // Düzenleme modalını göster
              $('#example_wrapper').addClass('blur'); // Tablo konteynerini blurlu yap
            },
            error: function(xhr, status, error) {
              console.error(xhr.responseText);
            }
          });
        });

        // Kapatma düğmesine tıklama olayını ekle
        $('.close').click(function() {
          closeModal();
        });

        $('#editCompanyForm').on('submit', function(event) {
          event.preventDefault(); // Sayfanın yeniden yüklenmesini engelle

          // companyId'yi doğrudan form elemanlarından almak yerine güvenilir bir kaynaktan al
          var companyId = $('#editCompanyId').val();

          var updatedCompany = {
            id: companyId, // Güvenilir kaynaktan alınan companyId değerini kullan
            name: $('#editCompanyName').val(),
            sector: $('#editCompanySector').val(),
            town: $('#editCompanyTown').val(),
            city: $('#editCompanyCity').val(),
          };


          // companyId değerini ve diğer verileri doğru şekilde alıp güncellediğinizden emin olun
          $.ajax({
            url: 'http://localhost:8080/appCompany/admin/updateCompany?companyId=' + encodeURIComponent(companyId),
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updatedCompany),
            success: function(response) {
              console.log("Company başarıyla güncellendi.");
              // Güncellenen şirketin verilerini tabloda güncelle
              var rowData = dataTable.row($('button[data-id="' + companyId + '"]').closest('tr')).data();
              rowData[1] = updatedCompany.name;
              rowData[2] = updatedCompany.sector;
              rowData[3] = updatedCompany.town;
              rowData[4] = updatedCompany.city;
              dataTable.row($('button[data-id="' + companyId + '"]').closest('tr')).data(rowData).draw();
              showInfoUpdate();
              closeModal(); // Modalı kapat

            },
            error: function(xhr, status, error) {
              console.error("Company güncelleme işlemi başarısız oldu:", xhr.responseText);
            }
          });
        });

        $(document).on('click', '.deleteCompanyBtn', function() {
          var confirmation = confirm('Bu şirketi silmek istediğinize emin misiniz?');
          if (confirmation) {
            var name = $(this).closest('tr').find('td:eq(1)').text();
            var row = $(this).closest('tr'); // Satırı sakla
            // Silme işlemi için gerekli AJAX çağrısını yapın
            $.ajax({
              url: 'http://localhost:8080/appCompany/admin/deleteCompany?name=' + name,
              type: 'DELETE',
              success: function(data) {
                location.reload();
                alert("Şirket başarıyla silindi")
                        row.remove();
                // Kullanıcıyı veritabanından da silmek için AJAX çağrısı yapın
              },
              error: function(xhr, status, error) {
                console.error(xhr.responseText);
              }
            });
          }
        });

        // Kapatma işlevi
        function closeModal() {
          $('#editCompanyModal').hide(); // Düzenleme modalını gizle
          $('#example_wrapper').removeClass('blur'); // Tablo konteynerinin blurlu yapmayı kaldır
        }
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
      }
    });
  });
  function showInfoUpdate(){
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
