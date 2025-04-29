package org.example;

import java.util.Arrays;

class UretimHattiOptimizasyonu {

    public static void main(String[] args) {

        int[][] isSüreleri = {
                {7, 40},
                {6, 5},
                {8, 2}
        };

        int[][] gecisMaliyetleri = {
                {0, 30},
                {3, 0}
        };

        int minimumSüre = minimumSüreyiBul(isSüreleri, gecisMaliyetleri);
        System.out.println("Minimum Toplam Süre: " + minimumSüre + " dakika");
    }

    public static int minimumSüreyiBul(int[][] isSüreleri, int[][] gecisMaliyetleri) {
        int isSayisi = isSüreleri.length;
        int makineSayisi = isSüreleri[0].length;

        // DP tablosu: dp[i][j] = i. işi j. makinede bitirdiğimizdeki minimum süre
        int[][] dp = new int[isSayisi][makineSayisi];

        // İlk iş için başlangıç süreleri (geçiş maliyeti yok)
        for (int makine = 0; makine < makineSayisi; makine++) {
            dp[0][makine] = isSüreleri[0][makine];
        }
        //dp[i][j]: i. işi j. makinede bitirdiğimizdeki minimum süreyi tutar.
        //dp[i][j] hesaplanarak tüm işlerin ve makinelerin en iyi sıralaması yapılır.


        // Sonraki işler için DP
        for (int is = 1; is < isSayisi; is++) {
            for (int mevcutMakine = 0; mevcutMakine < makineSayisi; mevcutMakine++) {
                int minSüre = Integer.MAX_VALUE;

                // Önceki işin hangi makinede bittiğine bak
                for (int oncekiMakine = 0; oncekiMakine < makineSayisi; oncekiMakine++) {
                    int süre = dp[is - 1][oncekiMakine]  //Önceki işin hangi makinede bitirildiği bilgisi.
                            + gecisMaliyetleri[oncekiMakine][mevcutMakine] //Makine değişimi için geçiş maliyeti.
                            + isSüreleri[is][mevcutMakine]; //Bu işin şu anki makinede süresi.
                    if (süre < minSüre) {
                        minSüre = süre;
                    }
                }

                dp[is][mevcutMakine] = minSüre;
            }
        }

        // Son işin minimum süresini bul
        return Arrays.stream(dp[isSayisi - 1]).min().orElseThrow();   //En küçük süreyi bulur.
    }
}