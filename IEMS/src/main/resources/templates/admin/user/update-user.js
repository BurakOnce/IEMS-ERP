
  $(document).ready(function() {
    // Sağlanan URL'den verileri al
    $.ajax({
      url: 'http://localhost:8080/appUser/admin/getUser/all',
      type: 'GET',
      success: function(data) {
        // Alınan verileri tabloya ekle
        $.each(data, function(index, users) {
          var deleteButton = '<button class="btn btn-danger deleteButton" data-user-id="' + users.id + '">Delete</button>';

          $('#example tbody').append('<tr>' +
                  '<td>' + users.id + '</td>' +
                  '<td>' + users.name + '</td>' +
                  '<td>' + users.username + '</td>' +
                  '<td>' + users.city + '</td>' +
                  '<td>' + users.town + '</td>' +
                  '<td>' + users.authorities.join(", ") + '</td>' +
                  '<td>' + (users.company ? users.company.name : 'N/A') + '</td>' +
                  '<td><button class="editButton" data-username="' + users.username + '">Düzenle</button></td>' +
                  '<td>'  + deleteButton + '</td>' +
                  '</tr>');
        });
        // DataTable'ı başlat
        var dataTable = $('#example').DataTable();

        // Düzenleme butonlarına tıklama olayını ekle
        $(document).on('click', '.editButton', function() {
          var username = $(this).data('username');
          // Kullanıcıyı düzenlemek için AJAX isteği yap
          $.ajax({
            url: 'http://localhost:8080/appUser/admin/getUser/username?username=' + username,
            type: 'GET',
            success: function(users) {
              // Kullanıcı bilgilerini düzenleme formuna doldur
              $('#editUserId').val(users.id);
              $('#editUserName').val(users.name);
              $('#editUserUsername').val(users.username);
              $('#editUserCity').val(users.city);
              $('#editUserTown').val(users.town);
              $('#editUserAuthorities').val(users.authorities.join(", "));
              $('#editUserModal').show(); // Düzenleme modalını göster
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

        $('#editUserForm').on('submit', function(event) {
                  event.preventDefault(); // Sayfanın yeniden yüklenmesini engelle

                  var username = $('#editUserUsername').val();

                  // Güncelleme formunu göndermek için düğmeye tıklama olayını ekle
                var updatedUser = {
                  id: $('#editUserId').val(),
                  name: $('#editUserName').val(),
                  username: username,
                  city: $('#editUserCity').val(),
                  town: $('#editUserTown').val(),
                  authorities: $('#editUserAuthorities').val().split(", ")
                };

                // Kullanıcıyı güncellemek için AJAX isteği yap
                $.ajax({
                  url: 'http://localhost:8080/appUser/admin/updateUser?username=' + encodeURIComponent(username), // Kullanıcı adını URL'ye ekleyin
                  type: 'POST',
                  contentType: 'application/json',
                  data: JSON.stringify(updatedUser),
                  success: function(updatedUser) {
                    var rowData = dataTable.row($('button[data-username="' + username + '"]').closest('tr')).data();
                    rowData[1] = updatedUser.name;
                    rowData[2] = updatedUser.username;
                    rowData[3] = updatedUser.town;
                    rowData[4] = updatedUser.city;
                    rowData[5] = updatedUser.authorities.join(", ");

                    dataTable.row($('button[data-username="' + username + '"]').closest('tr')).data(rowData).draw();
                    showInfoUpdate();
                    closeModal(); // Modalı kapat
                  },
                  error: function(xhr, status, error) {
                    console.error("Kullanıcı güncelleme işlemi başarısız oldu:", xhr.responseText);
                  }
                });
              });



        $(document).on('click', '.deleteButton', function() {
          var confirmation = confirm('Bu kullanıcıyı silmek istediğinize emin misiniz?');
          if (confirmation) {
            var username = $(this).closest('tr').find('td:eq(2)').text(); // Kullanıcı adını al
            var row = $(this).closest('tr'); // Satırı sakla
            // Silme işlemi için gerekli AJAX çağrısını yapın
            $.ajax({
              url: 'http://localhost:8080/appUser/admin/deleteUser?username=' + username,
              type: 'DELETE',
              success: function(data) {
                // Silme başarılıysa, tablodan kullanıcıyı kaldır
                row.remove();
                alert("Kullanıcı başarıyla silindi")
              },
              error: function(xhr, status, error) {
                console.error(xhr.responseText);
              }
            });
          }
        });

        // Kapatma işlevi
        function closeModal() {
          $('#editUserModal').hide(); // Düzenleme modalını gizle
          $('#example_wrapper').removeClass('blur'); // Tablo konteynerinin blurlu yapmayı kaldır
        }
  },
        // Kapatma düğmesine tıklama olayını ekle
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
