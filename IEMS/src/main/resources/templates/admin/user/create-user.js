
  function createUser() {
    var userData = {
      name: $("#name").val(),
      username: $("#username").val(),
      password: $("#password").val(),
      town: $("#town").val(),
      city: $("#city").val(),
      authorities: $("#authorities").val().split(",")
    };

    var companyId = $("#company").val();
    if (companyId) {
      userData.company = { id: companyId };
    }

    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/appUser/admin/addNewUser",
      data: JSON.stringify(userData),
      dataType: 'json',

      success: function (data) {
        showInfo();
      },

      error: function (xhr, textStatus, errorThrown) {
        alert('Error! Status: ' + xhr.status + ' - ' + errorThrown);
      }
    });
  }

  function showInfo(){
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

