
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
                                url: 'http://localhost:8080/appProduct/manEmp/getProduct/my?companyId=' + globalCompanyId,
                                type: 'GET',
                                success: function(data) {
                                    // Add retrieved data to the table
                                    $.each(data, function(index, product) {
                                        var companyName = product.company ? product.company.name : 'N/A';

                                        $('#example tbody').append('<tr>' +
                                            '<td>' + product.id + '</td>' +
                                            '<td>' + product.name + '</td>' +
                                            '<td>' + product.category + '</td>' +
                                            '<td>' + product.quantityUnits.join(", ") + '</td>' +
                                            '<td>' + product.stock + '</td>' +
                                            '<td>' + product.demand + '</td>' +
                                            '<td>' + product.barcode + '</td>' +
                                            '<td>' + product.discount + '</td>' +
                                            '<td>' + product.price + '</td>' +
                                            '<td>' + companyName + '</td>' +
                                            '<td><button type="button" class="btn ml-auto descriptionButton" onclick="descriptionButton(' + product.id + ')"><i class="bi bi-info-circle"></i> Note</button></td>'+
                                            '<td><button type="button" class="btn updateProductButton" data-id="' + product.id + '">Update</button></td>' +
                                            '<td><button type="button" class="btn deleteProductButton" data-barcode="' + product.barcode + '">Delete</button></td>' +
                                            '</tr>');
                                    });

                                    // DataTable'ı başlat
                                    $('#example').DataTable();


                                    // Düzenleme butonlarına tıklama olayını ekle
                                    $(document).on('click', '.updateProductButton', function() {
                                        var productId = $(this).data('id');
                                        // Ürünü düzenlemek için AJAX isteği yap
                                        $.ajax({
                                            url: 'http://localhost:8080/appProduct/manEmp/getProduct?id=' + productId,
                                            type: 'GET',
                                            success: function(product) {
                                                // Ürün bilgilerini düzenleme formuna doldur
                                                $('#editProductId').val(product.id);
                                                $('#editProductName').val(product.name);
                                                $('#editProductCategory').val(product.category);
                                                $('#editProductQuantityUnits').val(product.quantityUnits.join(", "));
                                                $('#editProductStock').val(product.stock);
                                                $('#editProductDemand').val(product.demand);
                                                $('#editProductBarcode').val(product.barcode);
                                                $('#editProductDiscount').val(product.discount);
                                                $('#editProductPrice').val(product.price);
                                                $('#editProductDescription').val(product.description);
                                                $('#editProductModal').show(); // Düzenleme modalını göster
                                                $('#example_wrapper').addClass('blur'); // Tablo konteynerini blurlu yap

                                                // Güncelleme formunu göndermek için düğmeye tıklama olayını ekle
                                                $('#updateProductButton').unbind('click').click(function() {
                                                    var updatedProduct = {
                                                        id: $('#editProductId').val(),
                                                        name: $('#editProductName').val(),
                                                        category: $('#editProductCategory').val(),
                                                        quantityUnits: $('#editProductQuantityUnits').val().split(", "),
                                                        stock: $('#editProductStock').val(),
                                                        demand: $('#editProductDemand').val(),
                                                        barcode: $('#editProductBarcode').val(),
                                                        discount: $('#editProductDiscount').val(),
                                                        price: $('#editProductPrice').val(),
                                                        description: $('#editProductDescription').val()
                                                    };

                                                    // Ürünü güncellemek için AJAX isteği yap
                                                    $.ajax({
                                                        url: 'http://localhost:8080/appProduct/manEmp/updateProduct?barcode='+product.barcode,
                                                        type: 'POST',
                                                        contentType: 'application/json',
                                                        data: JSON.stringify(updatedProduct),
                                                        success: function(response) {
                                                            alert('Ürün başarıyla güncellendi');
                                                            $('#editProductModal').hide(); // Düzenleme modalını gizle
                                                            $('#example_wrapper').removeClass('blur'); // Tablo konteynerinin blurlu yapmayı kaldır
                                                            location.reload(); // Sayfayı yenile
                                                        },
                                                        error: function(xhr, status, error) {
                                                            console.error(xhr.responseText);
                                                            alert('Ürün güncellenirken hata oluştu');
                                                        }
                                                    });
                                                });
                                            },
                                            error: function(xhr, status, error) {
                                                console.error(xhr.responseText);
                                                alert('Ürün bilgileri alınırken hata oluştu');
                                            }
                                        });
                                    });


                                    $(document).on('click', '.deleteProductButton', function() {
                                        var confirmation = confirm('Bu ürünü silmek istediğinize emin misiniz?');
                                        if (confirmation) {
                                            var barcode = $(this).closest('tr').find('td:eq(6)').text(); // Barkod numarasını al

                                            // Silme işlemi için gerekli AJAX çağrısını yapın
                                            $.ajax({
                                                url: 'http://localhost:8080/appProduct/manEmp/deleteProduct?barcode=' + barcode,
                                                type: 'DELETE',
                                                success: function(data) {
                                                    // Silme başarılıysa, tabloyu yenile
                                                    location.reload(); // Sayfayı yenile
                                                },
                                                error: function(xhr, status, error) {
                                                    console.error(xhr.responseText);
                                                }
                                            });
                                        }
                                    });

                                    function closeModal() {
                                        $('#editProductModal').hide(); // Düzenleme modalını gizle
                                        $('#example_wrapper').removeClass('blur'); // Tablo konteynerinin blurlu yapmayı kaldır
                                    }

                                    $('.close').click(closeModal);
                                }
                            });
                        });
                            },
                        });
                },
        });
    });

    function descriptionButton(id) {
        $.ajax({
            url: 'http://localhost:8080/appProduct/manEmp/getDescription?productId=' + id,
            type: 'GET',
            success: function(description) {
                $('#noteModalBody').text(description);
                $('#noteModal').modal('show');
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert('Error occurred while retrieving the note.');
            }
        });
    }

    $(document).ready(function() {
        // Close button handling
        $(document).on('click', '.close', function() {
            $('#noteModal').modal('hide');
        });

        // Also handle clicking outside the modal to close it
        $(document).on('click', function(event) {
            if ($(event.target).closest('#noteModal .modal-content').length === 0) {
                $('#noteModal').modal('hide');
            }
        });
    });
