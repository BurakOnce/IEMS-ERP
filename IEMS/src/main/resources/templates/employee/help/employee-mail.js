
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

                    showInfo();
                    },
                error: function(error){
                    console.error('Hata:', error);
                    // Gönderimde hata oluştuğunda kullanıcıya bilgi verilebilir.
                }
            });
        });
    });

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
