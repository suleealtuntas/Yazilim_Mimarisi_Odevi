# Proje Başlığı

:information_source: **Dersin Kodu:** [16303](https://ebp.klu.edu.tr/Ders/dersDetay/YAZ16303/716026/tr)  
:information_source: **Dersin Adı:** [YAZILIM MİMARİSİ VE TASARIMI](https://ebp.klu.edu.tr/Ders/dersDetay/YAZ16303/716026/tr)  
:information_source: **Dersin Öğretim Elemanı:** Öğr. Gör. Dr. Fatih BAL  [Github](https://github.com/balfatih)   |    [Web Sayfası](https://balfatih.github.io/)
   
---

## Grup Bilgileri

| Öğrenci No | Adı Soyadı       | Bölüm          		   | Proje Grup No | Grup Üyelerinin Github Profilleri                 |
|------------|------------------|--------------------------|---------------|---------------------------------------------------|
| 1210505043 | Şule ALTUNTAŞ			 | Yazılım Mühendisliği     | PROJE_13      | [Github](https://github.com/suleealtuntas)     |
| 1210505022 | Ecem Çağıl Dizi  | Yazılım Mühendisliği     | PROJE_13      | [Github](https://github.com/ecemcagildizi)     |
| 1210505014 | Gülbahar TÜRÜT   | Yazılım Mühendisliği     | PROJE_13      | [Github](https://github.com/1210505014)     |

---

## Proje Açıklaması

Yapmış olduğumuz proje bir üniversitenin kütüphane sistemidir. Bu proje içerisinde yapılan kütüphane sistemi herhangi bir üniversitede kullanılacak biçimde tasarlanmıştır. Sade bir biçimde tasarlandığı için sistemi kullanan kullanıcıya kullanım kolaylığı sağlar. Bu projenin amacı kütüphane sistemine giren Öğrenci, Kütüphane Personeli ve Öğretim Görevlileri şeklindeki kullanıcı türleri arasında erişim hakkını sınırlamak ve her kullanıcıya yönelik kısıtlama vermektir. Projenin çalışması tek bir anasayfadan sağlanmaktadır. OturumApplication.java sayfasının çalıştırılmasıyla ekrana gelen sayfada oturum açma ve üye olma kısımları vardır. Eğer herhangi bir kullanıcı sisteme üye değilse ve bu durumda oturum açmaya çalışırsa hata alır.Sisteme üye olan bir kullanıcının oturum açma sayfasında Eposta adresini veya şifresini yanlış girmesi halinde sistem yine hata verir ve kullanıcıyı sisteme kabul etmez. Kullanıcı sisteme doğru bir biçimde giriş yaparsa anasayfaya aktarılır ve bu sayfadan kendisine ait olan üye bilgilerini, ödünç aldığı kitapları ve ödünç aldığı kitapları iade edip etmediği gibi durumları ve iade ettiği kitapları ve bu kitapların mevcut kütüphanedeki durumları gibi bilgilere erişebilir. Öğretim Görevlisi ve Öğrenci türündeki kullanıcılar için proje bu şekilde çalışmaktadır. Ancak sisteme giriş yapan kullanıcı türü Kütüphane Personeli ise o zaman kullanıcı admin paneli sayfasına aktarılır. Bu sayfada Kütüphane Görevlisi tipindeki kullanıcıya kitap ekleme, kitap silme ve kitap güncelleme yetkisi verilmiştir.
- Kullanılan Design Pattern'ler:

- 1- Singleton Design Pattern: Veri tabanı bağlantısı yapmak için kullanıldı. Bu sayede veri tabanı bağlantı nesnemizden ihtiyaç olduğu durumda bir tane üretmiş olduk.

- 2- Observer Design Pattern: Oturum işlemlerinde herhangi bir hata veya eksiklik durumunda bildirim vermesi için kullanıldı. Oturum işlemi esnasında yanlış bilgi girildiğinde veya kullanıcı türü seçilmediği taktirde bir hata bildirimi fırtmış olduk.

- 3- Dependency Injection Design Pattern: Projemizin belirli kısımlarında nesneye olan bağımlılıkları azaltmak için kullanıldı. Nesne kullanımı maliyetli bir işlem olduğundan bu maliyeti en aza indirmek amacı ile kullanılmıştır.

- 4- Strategy Design Pattern: Bu desen farklı kullanıcı tipleri için kullanıcı bilgileri çekmek ve kullanmak için kullanıldı. Projede öğrenciler, öğretim üyeleri ve kütüphane görevlileri olmak üzere üç adet kullanıcı tipi vardır. Bu kullanıcı tipleri veri tabanında hepsi ayrı olmak üzere tablolarda saklanır. Bu tablolardan verileri çekebilmek ve hepsini bir arada kullanabilmek için bu design pattern'i kullanılmıştır.

- 5- Data Access Object Design Pattern: Veri tabanı ile proje arasında entity katmanı oluşturarak bağ kazanmak için kullanıldı. Bu sayede verilerle proje ile veri tabanı arasında bir köprü olmuş oldu. Bu sayede veri tabanındaki veriler etkin bir şekilde kullanmış olduk.

---

## Proje Dosya Yapısı

  - **/src**
    - **/main**
      - **/images**
      - **/java**
        - **/Connection**
        - **/Entities**
          - **/Abstract**
          - **/Concrete**
        - **/org**
          - **/example**
             - **/flora_kutuphane**
                 - **/Kitaplar**
                 - **/KitaplarDAO**
                 - **/Kullanicilar**
                 - **/OturumIslemleri**
      - **/resources**
        - **/org**
          - **/example**
            - **/flora_kutuphane**
  - **/Lib**
  - `README.md`
  - `LICENSE`


---

## Kurulum

- 1- Terminal veya Komut İstemcisini açın.
- 2- "git clone https://github.com/1210505014/Yuri_Kutuphane.git" komutunu kullanarak projeyi bilgisayarınıza klonlayın.
- 3- Projede charm-glisten-6.0.2 kütüphanesi kullanıldığı için bunu projenize dahil etmeniz gerekiyor. Bu işlem şu şekildedir:
- -   3.1- Projenizi açtığınız zaman "File" sekmesinden "Project Structure" sekmesine tıklıyoruz.
-  -  3.2- Açılan alanda soldaki menüden "Libraries" sekmesini seçiyoruz.
-  -  3.3- Açılan alanda sol üst köşedeki "+" butonuna tıklayıp kütüphane yolunu seçiyoruz.
-  (Kütüphane projenin içinde "Lib/charm-glisten-6.0.2.jar" yolunda bulunuyor.)
-  Bu adımları takip ederek projeyi indirebilir ve çalıştırabilirsiniz.

---

## Kullanım

- 1- Herhangi bir kişi projeyi indirdikten sonra ilk başta projeyi İntellij Idea ile açması gerekir.

- 2- Projeyi kullanmak isteyen kişinin Intellij Idea geliştirme ortamının uygun ve güncel sürümlere sahip olması gerekir yoksa hata alır ve projeyi başlatamaz.

- 3- Tüm bunları hallettikten sonra projenin src dizini altında bulunan java dizini içerisinde OturumController.java sayfasını bulup çalıştırması gerekir.

- 4- Proje çalıştığı zaman ekrana Oturum sayfası gelir. Kişi sistemi ilk defa kullandığı için otutum hesabı yoktur o yüzden ekranı sağa çekerek Üye Olma sayfasını açmalıdır.

- 5- Bu sayfada ilk başta kütüphane sistemi için Öğrenci, Öğretim Görevlisi veya Kütüphane Görevlileri şeklinde belirlenmiş olan kullanıcı türlerinden kendisi için uygun olanını seçip istenilen bilgilerin hepsini girmelidir ve sonrasında Üye Ol butonuna tıklamalıdır.

- 6- Ekranı sola çekip Oturum bilgilerini girdikten sonra sisteme başarılı bir biçimde giriş yapabilir.

- 7- Eğer kişi sisteme üye olurken kullanıcı türünü Öğrenci veya Öğretim Görevlisi olarak seçtiyse anasayfaya aktarılır.

- 8- Bu sayfadan kendisine ait olan üye bilgilerini, ödünç aldığı kitapları ve ödünç aldığı kitapları iade edip etmediği gibi durumları ve iade ettiği kitapları ve bu kitapların mevcut kütüphanedeki durumları gibi bilgilere erişebilir.

- 9- Eğer kişi sisteme üye olurken kullanıcı türünü Kütüphane Personeli olarak seçtiyse admin paneli sayfasına aktarılır.

- 10- Bu sayfada Kütüphane Görevlisi tipindeki kişiye kitap ekleme, kitap silme ve kitap güncelleme yetkisi verilmiştir. Kişi bu sayfada bulunan ekle, sil ve düzenle butonlarına tıklayarak yapmak istediği işlemi gerçekleştirebilir ve yaptığı değişikliklerin sonucu aynı sayfa içerisinde görüntüleyebilir.

---

## Katkılar

Projenin yapım aşamasında birkaç kaynaktan faydalandık. Bu kaynaklar:

- 1- https://www.youtube.com/playlist?list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev
Javafx ile ilgili temel işlemleri sayfa yapısını ve sayfa geçişlerini yapmayı öğrenmek için bu çalma listesinden faydalandık.

- 2- https://emrecelen.com.tr/javafx-nedir/
Javafx ile ilgili tasarım örneği görmek ve yazılı bir kaynağı kullanmak için bu web sitesini kullandık.

- 3- https://medium.com/s%C4%B1f%C4%B1rdan-i%CC%87leri-d%C3%BCzeye-java-e%C4%9Fitim-serisi/jdbc-ile-veritaban%C4%B1-i%CC%87%C5%9Flemleri-e7348de4c88c
JDBC ile projeye veri tabanını bağlamak ve sorgu oluşturmak için bu içeriği kullandık.

- 4- https://www.btkakademi.gov.tr/portal/course/c-7008
Design pattern ile ilgili bilgiler ve örnek projelerden yararlanmak için bu BTK Akademi eğitim videolarını kullandık.

- 5- https://github.com/balfatih/YAZ16303_Yazilim_Mimarisi_ve_Tasarimi
Design pattern ile ilgili örnek ve bilgi olarak derste öğrendiğimiz ve uyguladığımız bilgilerden faydalandık.

---

## İletişim

- Şule ALTUNTAŞ: 1210505043@ogr.klu.edu.tr
- Ecem Çağıl DİZİ: 1210505022@ogr.klu.edu.tr
- Gülbahar TÜRÜT: 1210505014@ogr.klu.edu.tr
