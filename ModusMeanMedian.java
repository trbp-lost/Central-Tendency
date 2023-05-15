import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ModusMeanMedian {
    private static Scanner input = new Scanner(System.in);
    private static Map<String,Integer> kumpulanData = new HashMap<>();
    private static Map<String,Integer> tempDic = new HashMap<>();
    private static List<String> data = new ArrayList<>();
    private static List<String> tempList = new ArrayList<>();

    private static int frekuensi;

    private static List<String> Modus(Map<String,Integer> data){
        TempClear();

        int nilaiTerbanyak=Integer.MIN_VALUE;
        String namaData=null;

        for (Map.Entry<String, Integer> dataD : kumpulanData.entrySet()) {
            if (dataD.getValue() >= nilaiTerbanyak) {
                nilaiTerbanyak = dataD.getValue();
            }
        }
        for (Map.Entry<String, Integer> dataD : kumpulanData.entrySet()) {
            if (dataD.getValue() >= nilaiTerbanyak && tempList.contains(dataD.getValue().toString())==false) {
                namaData = dataD.getKey();
                tempList.add(namaData);
            }
        }
        return tempList;
    }

    private static float Mean(List<String> data){
        TempClear();

        int count = 0;
        float sum = 0;

        for (int i = 0; i < data.size(); i++) {
            sum += Integer.valueOf(data.get(i));
            count++;
        }
        
        if (count > 0) {
            sum = sum / data.size();
        }else sum = 0;

        return sum;
    }
    
    private static int Median(List<String> data){
        TempClear();

        System.out.println(data.toString());
        tempList.addAll(data);
        tempList.sort(null);

        if (tempList.isEmpty()==true) {
            System.err.println("\nMedian Temp List is \"Empty\"");
            System.exit(3);
        }

        if (tempList.size() % 2 == 0) {
            int middleIndex1 = tempList.size() / 2;
            int middleIndex2 = middleIndex1 - 1;
            int hasil = Math.round(((Integer.parseInt(tempList.get(middleIndex1)) + Integer.parseInt(tempList.get(middleIndex2))) / 2));
            return Integer.parseInt(tempList.get(hasil));
        } else {
            int middleIndex = tempList.size() / 2;
            return (int)Integer.parseInt(tempList.get(middleIndex));
        }
    }

    private static Map<String,Integer> ListData(){
        for (int i = 0; i < data.size(); i++) {
            int count=1;

            for (int j = 0; j < i; j++) {
                if (data.get(i).equals(data.get(j))) count++;
            }
            for (int k = 0; k < data.size(); k++) {
                kumpulanData.put(data.get(i).toString(), count);
            }
        }

        System.out.println("\nDaftar Tabel : "+kumpulanData.toString());
        System.out.println("Banyak Tabel : "+kumpulanData.size());

        return kumpulanData;
    }

    private static void TempClear(){
        tempList.clear();
        tempDic.clear();
    }
    
    private static void ResetData(){
        TempClear();
        kumpulanData.clear();
        data.clear();
        frekuensi=0;
        input.reset();
    }

    private static void Hasil(){
        System.out.printf("\nModusnya adalah  :\t %s",Modus(kumpulanData));
        System.out.printf("\nMeannya adalah   :\t %.3f",Mean(data));
        System.out.printf("\nMediannya adalah :\t %d",Median(data));
    }

    private static void Menu(){
        ResetData();

        System.out.print("\nMasukkan banyak data : ");
        frekuensi = input.nextInt();
        
        for (int i = 0; i < frekuensi; i++) {
            System.out.printf("Masukkan data ke-%d : ",(i+1));
            data.add(input.next());
        }

        ListData();
        Hasil();
        
        kumpulanData.clear();
        data.clear();
    }

    private static void ask(){
        String ask;

        System.out.print("\nIngin Lanjut? [y/n] : ");
        ask = input.next();

        if (ask.equals("y") || ask.equals("Y")) {
            Menu();
        } else if (ask.equals("n") || ask.equals("N")) {
            input.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        do {
            Menu();
            ask();
        } while (true);
    }
}
