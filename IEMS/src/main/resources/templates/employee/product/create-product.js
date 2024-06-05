

  var globalCompanyId;

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/profile/current-user-id",
    success: function(userId) {
      // Kullanıcı ID'sini yazdır
      console.log("Current user ID:", userId);

      // Kullanıcı adını al
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/appUser/free/getCompanyId?userId=" + userId,
        success: function(companyId) {
          // Şirket ID'sini yazdır
          console.log("Company ID:", companyId);
          globalCompanyId=companyId;
        },
        error: function(xhr, textStatus, errorThrown) {
          console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
          // Hata durumlarını burada yönetebilirsiniz
        }
      });
    },
    error: function(xhr, textStatus, errorThrown) {
      console.error('Error! Status: ' + xhr.status + ' - ' + errorThrown);
      // Hata durumlarını burada yönetebilirsiniz
    }
  });




  function createProduct() {
    console.log("Global Company ID:", globalCompanyId);


    var productData = {
      name: $("#name").val(),
      description: $("#description").val(),
      category: $("#category").val(),
      stock: $("#stock").val(),
      demand: $("#demand").val(),
      barcode: $("#barcode").val(),
      discount: $("#discount").val(),
      price: $("#price").val(),
      quantityUnits: $("#quantityUnits").val().split(","), // Split authorities by comma
      company: { id: globalCompanyId }, // Company ID'sini doğrudan tanımla
    };

    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "http://localhost:8080/appProduct/manEmp/createProduct",
      data: JSON.stringify(productData),
      dataType: 'json',

      success: function(data) {
        if (data && data.message) {
          showInfo();
        } else {
          // Başarısız olduğunda yapılacak işlemler
        }
      },

      error: function(xhr, textStatus, errorThrown) {
        alert("Ürün ekleme başarısız");
        // Hata durumlarını burada yönetebilirsiniz
      }
    });
  }


  function resetForm() {
    $('input[type="text"]').val('');
    $('textarea').val('');
    $('#quantityUnits').val('');
    $('input[type="number"]').val('');


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
