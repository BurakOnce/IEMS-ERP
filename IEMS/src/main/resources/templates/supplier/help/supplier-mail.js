
  $(document).ready(function(){
    $('#mailForm').submit(function(e){
      e.preventDefault();
      var formData = {
        to: $('#to').val(),
        subject: $('#subject').val(),
        body: $('#body').val()
      };
      $.ajax({
        url: 'http://localhost:8080/sendEmail',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(response){
          alert("Mail başarıyla gönderildi")
          // Başarılı gönderim durumunda kullanıcıya bilgi verilebilir.
        },
        error: function(error){
          console.error('Hata:', error);
          // Gönderimde hata oluştuğunda kullanıcıya bilgi verilebilir.
        }
      });
    });
  });
