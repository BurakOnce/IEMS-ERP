# IEMS ERP

## Proje Özeti
Geliştirilen IEMS ERP sistemi, modern işletmelerin ihtiyaçlarına uygun olarak maliyet ve zaman tasarrufu sağlamak amacıyla tek bir platformdan kapsamlı kontrol sunmaktadır. Sistem, admin, manager, supplier ve employee olmak üzere dört ana bölümden oluşmakta ve her kullanıcıya farklı yetkiler ve sorumluluklar vermektedir. Envanter kontrolü ve tedarik zinciri yönetimi üzerine yoğunlaşan IEMS ERP, kaynakların daha verimli kullanılmasını hedeflemektedir. Intellij, Spring Boot ve MySQL kullanılarak geliştirilen sistem, esnek, ölçeklenebilir ve güvenilir bir yapıya sahiptir. Yerli şirketlerin döviz kuru dalgalanmalarından kaynaklanan maliyet baskılarını azaltarak, yerel çözümler geliştirmelerine yardımcı olmayı amaçlamaktadır.

## Veri Tabanı
IEMS ERP sistemi, kullanıcı, şirket, tedarik ve ürün gibi ana tablolar ve bu tablolar arasındaki ilişkileri içeren bir veri tabanı yapısına sahiptir. 
- **Kullanıcı Tablosu:** Kullanıcı bilgilerini ve rollerini içerir.
- **Şirket Tablosu:** Şirketlerin bilgilerini ve kullanıcılarla olan ilişkilerini tutar.
- **Tedarik Tablosu:** Ürün tedarik süreçlerini yönetir ve sipariş durumu gibi bilgileri içerir.
- **Ürün Tablosu:** Ürün detaylarını ve stok bilgilerini barındırır, her ürün bir şirkete ilişkilidir.
- **Enum'lar:** Role, QuantityUnit ve OrderStatus Enum'ları, sistemdeki yetkileri, miktar birimlerini ve sipariş durumlarını standartlaştırarak yönetimi kolaylaştırır.
![Veri tabanı](https://github.com/BurakOnce/IEMS-ERP/blob/main/Veri%20Taban%C4%B1.png)

### Login:
![Login](https://github.com/BurakOnce/IEMS-ERP/blob/main/Login.png)

## Admin
### Adminlerin Görevleri:
- **Kullanıcı Yönetimi:** Adminler, kullanıcı oluşturma, düzenleme ve silme işlemlerini gerçekleştirir. Kullanıcıların şirketle ilişkilendirilmesi "CompanyId" ile yapılır.
- **Şirket Yönetimi:** Adminler, şirket bilgilerini düzenleyebilir veya veri tabanından silebilir.
  
### Kullanıcı Oluşturma:
![Kullanıcı Oluşturma](https://github.com/BurakOnce/IEMS-ERP/blob/main/admin-ss/kullan%C4%B1c%C4%B1-olu%C5%9Ftur.png)
### Kullanıcı Düzenleme:
![Kullanıcı Düzenleme](https://github.com/BurakOnce/IEMS-ERP/blob/main/admin-ss/kullan%C4%B1c%C4%B1-d%C3%BCzenle.png)
### Kullanıcı Listesi:
![Kullanıcı Listesi](https://github.com/BurakOnce/IEMS-ERP/blob/main/admin-ss/kullan%C4%B1c%C4%B1-tablosu.png)


## Manager
### Manager Görevleri:
- **Ürün Yönetimi:** Manager, yeni ürün ekleyebilir, mevcut ürünleri düzenleyebilir ve ürün listesini görüntüleyebilir.
- **Sipariş Yönetimi:** Manager, açık ve kapalı siparişler oluşturabilir, siparişlerin durumunu değiştirebilir. Siparişlerin durumu “Pending”, “Duration” veya “Unsuccessful” olarak takip edilir.
- **Mail Gönderme:** Destek ekibiyle iletişime geçmek için "Send Mail" sayfasını kullanarak adminlere mesaj gönderebilir.

### Ürün Oluşturma:
![Ürün Oluşturma](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/%C3%BCr%C3%BCn%20olu%C5%9Ftur.png)
#### Ürün Modülü aynı zamanda "Employee"'nin de yetkileri içindedir.

### Sipariş Oluşturma :
![Sipariş Oluşturma](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/tedarik%20olu%C5%9Fturma.png)
#### Burada seçilen "Order Status" bölümü sayesinde verilen sipariş yayına sokulabilir. Yayına hazır değil ise "Close Orders" kısmına konur ve bekletilir.

### Kapalı Sipariş :
![Kapalı Sipariş](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/kapal%C4%B1-sipari%C5%9Fler.png)
#### Burada kapalı siparişler görüntülenir. Siparişlerin yanındaki butonlar sayesinde işlevler gerçekleştirilebilir. Sipariş yayına sokulabilir, silinebilir veya halihazırda tedarikçiler tarafından alınmış bir sipariş ise onaylanabilir.

### Açık Sipariş :
![Açık Sipariş](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/a%C3%A7%C4%B1k-sipari%C5%9Fler.png)
#### Bu sayfada ise yayında olan siparişler iptal edilebilir veya kapatılabilir.

### Bekleyen Sipariş :
![Bekleyen Sipariş](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/bekleyen-sipari%C5%9Fler.png)
#### Henüz tedarikçiler tarafından üstlenilmemiş siparişler burada gözükür.

### Onaylanan Sipariş :
![Onaylanan Sipariş](https://github.com/BurakOnce/IEMS-ERP/blob/main/manager-ss/onaylanan-sipari%C5%9Fler.png)
#### Temin edildiğini garanti ettiğiniz siparişlerinizi "Open Order" sekmesinden onaylayabilir ve "Confirmed Orders" sayfasına taşıyabilirsiniz.

## Supplier
### Supplier Görevleri:
- **Tedarik Yönetimi:** Supplier, kendi tedariklerinin durumunu (süreçte olan, tamamlanmış veya başarısız olmuş) izleyebilir ve onaylanmış siparişleri görüntüleyebilir.
- **Genel Sipariş Yönetimi:** Supplier, yayında olan siparişleri görebilir ve bu siparişleri seçerek üstlenebilir.
- **Sipariş Detayları:** Üstlendikleri siparişlerin detaylarını "My Supplies" sayfasında görüntüleyebilirler. Siparişlerin tamamlanma durumuna göre “Done” veya “Fail” butonlarını kullanabilirler. Onaylanmış siparişleri "Confirmed" sekmesinde görebilirler.
- **Mail Gönderme:** Destek ekibiyle iletişime geçmek için "Send Mail" sayfasını kullanarak adminlere mesaj gönderebilir.

### Açık Siparişler :
![Açık Siparişler](https://github.com/BurakOnce/IEMS-ERP/blob/main/supplier-ss/a%C3%A7%C4%B1k%20sipari%C5%9Fler.png)
#### Yayında olan siparişleri bu sayfada görüntüleyebilir ve "Take" butonuna tıklayıp üstlenebilirsiniz.

### Üstlenilen Siparişler :
![Üstlenilen Siparişler](https://github.com/BurakOnce/IEMS-ERP/blob/main/supplier-ss/%C3%BCstlenilen-sipari%C5%9Fler.png)
#### Üstlenilen siparişleri burada görüntüleyebilirsiniz. Ayrıca siparişi temin etme durumunuza göre "Done" veya "Fail" butonları ile sipariş durumunu yönetebilirsiniz.

## Employee
### Employee Görevleri:
- **Ürün Yönetimi:** Employee, yeni ürünler ekleyebilir, mevcut ürünleri güncelleyebilir ve ürün listesini görüntüleyebilir.
- **İletişim:** Sık sorulan sorulara erişebilir ve e-posta göndererek destek alabilir.
- **Mail Gönderme:** Destek ekibiyle iletişime geçmek için "Send Mail" sayfasını kullanarak adminlere mesaj gönderebilir.

### Ürün Yönetimi kısmı "Manager" ile aynı olduğundan buraya Ürün Modül fotoğrafları eklenmemiştir.

### Mail Gönderme :
![Mail Gönderme](https://github.com/BurakOnce/IEMS-ERP/blob/main/employee-ss/mail-g%C3%B6nderme.png)
#### Herhangi bir sorun ile karşılaşırsanız bu sayfadan admin'lerle iletişime geçebilirsiniz. (Tüm kullanıcılarda bulunur)

### Sıkça Sorulan Sorular :
![Sıkça Sorulan Sorular](https://github.com/BurakOnce/IEMS-ERP/blob/main/employee-ss/s%C4%B1k%C3%A7a-sorulan-sorular.png)
#### Program kullanılırken akla gelebilecek sorular. (Tüm kullanıcılarda rollerine göre farklılık gösterir)

![](https://github.com/BurakOnce/IEMS-ERP/assets/119293638/c7a591e0-e2bd-4034-8c38-9c5e11a2b7f0)

# IEMS-ERP bir bitirme projesidir.
### İstanbul Medeniyet Üniversitesi
### Mühendislik ve Doğa Bilimleri Fakültesi
#### Bilgisayar Mühendisliği 2024
#### Burak Önce 

