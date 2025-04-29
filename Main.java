package org.example;

import java.util.Arrays;

class UretimHattiOptimizasyonu {

    public static void main(String[] args) {

        int[][] isSüreleri = {
                {7, 40},
                {6, 5},
                {8, 2},
                {12,3}
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


        int[][] dp = new int[isSayisi][makineSayisi];


        for (int makine = 0; makine < makineSayisi; makine++) {
            dp[0][makine] = isSüreleri[0][makine];
        }


        
        for (int is = 1; is < isSayisi; is++) {
            for (int mevcutMakine = 0; mevcutMakine < makineSayisi; mevcutMakine++) {
                int minSüre = Integer.MAX_VALUE;


                for (int oncekiMakine = 0; oncekiMakine < makineSayisi; oncekiMakine++) {
                    int süre = dp[is - 1][oncekiMakine]  //Önceki işin hangi makinede bitirildiği bilgisi.
                            + gecisMaliyetleri[oncekiMakine][mevcutMakine] //Makine değişimi için geçiş maliyeti.
                            + isSüreleri[is][mevcutMakine]; //İşin güncel makinedeki süresi.
                    if (süre < minSüre) {
                        minSüre = süre;
                    }
                }

                dp[is][mevcutMakine] = minSüre;
            }
        }


        return Arrays.stream(dp[isSayisi - 1]).min().orElseThrow();
    }
}
