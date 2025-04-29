# Production-Line-Optimization

Bu algoritma bir çok iş ve bu işleri yapabilecek makinelerin olduğu ortamda hangi makinenin hangi işleri yapacağını seçip en düşük maliyeti hesaplamayı amaçlar. Bunun için her iş-makine kombinasyonunda o ana kadar elde edilebilecek en kısa toplam süreyi tutan iki boyutlu bir “dp” tablosu oluşturulur.


Kullanımı: 9. satırdan başlayan is sureleri dizisindeki süslü parantez içindeki değerler sol makine 1 sağ makine 2 olmak üzere istenilen maliyet değeriyle değiştirilebilir. Aynı şekilde 17 ve 18. satırdaki geçiş maliyerleri değerleri de değiştirilebilir. Ya da hem iş hem geçiş masrafı kısımlarına iki bölümdeki makine sayısı da aynı olacak şekilde ekleme veya çıkarma yapılabilir.

    Başlangıç Durumu
    İlk iş için makinelere doğrudan atanabilecek süreler, geçiş maliyeti olmadan dp[0][makine] = işSüreleri[0][makine] şeklinde tabloya yazılır.

    Alt Problemin Tanımı
    dp[i][j] değeri, “i. işi j. makinede bitirdiğimizde toplam sürenin alabileceği en küçük değer” olarak tanımlanır.

    Durum Geçişi
    Her yeni iş (i), tüm makinelerde (mevcutMakine) değerlendirildiğinde, önceki işin (i–1) hangi makinede bittiğine bakılarak şöyle bir hesaplama yapılır:

süre = dp[i–1][oncekiMakine]
      + geçişMaliyetleri[oncekiMakine][mevcutMakine]
      + işSüreleri[i][mevcutMakine]

Bu üç bileşen, önceki kademeden itibaren birikmiş zaman, makine değiştirme maliyeti ve o işin makinedeki işlem süresi toplamını verir. En küçük süre, dp[i][mevcutMakine] olarak saklanır.

Sonuç
Tüm işler ve makineler değerlendirildikten sonra, son işin tamamlanma süresinin en düşük değeri, yani Arrays.stream(dp[sonİş]).min() ile bulunur. Bu değer, verilen iş süreleri ve geçiş maliyetleri göz önüne alındığında ulaşılabilecek minimum toplam süreyi verir.
