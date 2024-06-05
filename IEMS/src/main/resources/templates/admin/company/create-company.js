
  function createCompany() {

    var companyData = {
      name: $("#name").val(),
      sector: $("#sector").val(),
      town: $("#town").val(),
      city: $("#city").val(),
    };

    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/appCompany/admin/createCompany",
      data: JSON.stringify(companyData),
      dataType: 'json',

      success: function (data) {
        if (data && data.message) {
          showInfo();

        } else {
          alert("Error: " + (data.message || "Company creation failed"));
        }
      },
      error: function (error) {
        console.log("An error occurred while creating the company: " + error.responseText);
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
