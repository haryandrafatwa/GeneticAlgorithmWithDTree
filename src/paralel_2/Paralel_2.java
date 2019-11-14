/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paralel_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author User
 */
public class Paralel_2 {
    
    static String convert(String str) 
    { 
  
        // Create a char array of given String 
        char ch[] = str.toCharArray(); 
        for (int i = 0; i < str.length(); i++) { 
  
            // If first character of a word is found 
            if (i == 0 && ch[i] != ' ' ||  
                ch[i] != ' ' && ch[i - 1] == ' ') { 
  
                // If it is in lower-case 
                if (ch[i] >= 'a' && ch[i] <= 'z') { 
  
                    // Convert into Upper-case 
                    ch[i] = (char)(ch[i] - 'a' + 'A'); 
                } 
            } 
  
            // If apart from first character 
            // Any one is in Upper-case 
            else if (ch[i] >= 'A' && ch[i] <= 'Z')  
  
                // Convert into Lower-Case 
                ch[i] = (char)(ch[i] + 'a' - 'A');             
        } 
  
        // Convert the char array to equivalent String 
        String st = new String(ch); 
        return st; 
    } 
    
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File("data_latih.csv"));
        scanner.useDelimiter(",");
        
        int x = 0;
        String[] array = new String[80];
        String[][] array_datalatih = new String[80][5];
        Kromosom kromLatihList[] = new Kromosom[80];
        
        while(scanner.hasNext()){
            array[x] = scanner.nextLine();
            array[x] = array[x].replaceAll(",", " ");
            //System.out.println(array[x]);
            array_datalatih[x] = array[x].split(" ");
            
            kromLatihList[x] = new Kromosom();
            
            kromLatihList[x].setSuhu(convert(array_datalatih[x][0]));
            kromLatihList[x].setWaktu(convert(array_datalatih[x][1]));
            kromLatihList[x].setLangit(convert(array_datalatih[x][2]));
            kromLatihList[x].setKelembapan(convert(array_datalatih[x][3]));
            kromLatihList[x].setTerbang(convert(array_datalatih[x][4]));
            x++;
        }
        
        scanner.close();
        
        int byk_kromosom = 10;
        
        Populasi populasi = new Populasi(byk_kromosom);
        Kromosom[] kromList = populasi.getKromList();
        
        System.out.println("Data Latih");
        for (int i = 0; i < kromLatihList.length; i++) {
            System.out.println(i+".\t[ "+kromLatihList[i].getSuhu()+"\t"+kromLatihList[i].getWaktu()+"\t\t"+kromLatihList[i].getLangit()+"\t\t"+kromLatihList[i].getKelembapan()+"\t\t"+kromLatihList[i].getTerbang()+"\t]");
        }
        
        System.out.println("");
        System.out.println("Dataset");
        for (int i = 0; i < kromList.length; i++) {
            System.out.println(i+"\t[ "+kromList[i].getSuhu()+"\t"+kromList[i].getWaktu()+"\t\t"+kromList[i].getLangit()+"\t\t"+kromList[i].getKelembapan()+"\t]");
        }
        
        System.out.println("");
        for (int i = 0; i < kromList.length; i++) {
            for (int j = 0; j < kromLatihList.length; j++) {
                if(kromList[i].getSuhu().equals(kromLatihList[j].getSuhu()) && kromList[i].getWaktu().equals(kromLatihList[j].getWaktu())
                        && kromList[i].getLangit().equals(kromLatihList[j].getLangit()) && kromList[i].getKelembapan().equals(kromLatihList[j].getKelembapan())){
                    System.out.print("Dataset "+i+" ditemukan pada Data Latih "+j);
                    System.out.println(", Terbang: "+kromLatihList[j].getTerbang());
                    kromList[i].setTerbang(kromLatihList[j].getTerbang());
                    break;
                }
            }
        }
        
        System.out.println("");
        System.out.println("Dataset");
        for (int i = 0; i < kromList.length; i++) {
            System.out.println("[ "+kromList[i].getSuhu()+"\t"+kromList[i].getWaktu()+"\t\t"+kromList[i].getLangit()+"\t\t"+kromList[i].getKelembapan()+"\t\t"+kromList[i].getTerbang()+"\t]");
        }
    }
}
class Kromosom{
    private String suhu;
    private String waktu;
    private String langit;
    private String kelembapan;
    private String terbang;
    private String array_suhu[] = {"Rendah", "Normal", "Tinggi"};
    private String array_waktu[] = {"Pagi","Siang","Sore","Malam"};
    private String array_langit[] = {"Cerah","Berawan","Rintik","Hujan"};
    private String array_kelembapan[] = {"Rendah", "Normal", "Tinggi"};

    public Kromosom() {
        this.suhu = array_suhu[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
        this.waktu = array_waktu[ThreadLocalRandom.current().nextInt(0, 3 + 1)];
        this.langit = array_langit[ThreadLocalRandom.current().nextInt(0, 3 + 1)];
        this.kelembapan = array_kelembapan[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
    }

    public String getSuhu() {
        return suhu;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getLangit() {
        return langit;
    }

    public String getKelembapan() {
        return kelembapan;
    }

    public String getTerbang() {
        return terbang;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setLangit(String langit) {
        this.langit = langit;
    }

    public void setKelembapan(String kelembapan) {
        this.kelembapan = kelembapan;
    }

    public void setTerbang(String terbang) {
        this.terbang = terbang;
    }
    
}

class Populasi{
    private Kromosom kromList[];

    public Populasi(int byk_kromosom) {
        kromList = new Kromosom[byk_kromosom];
        for (int i = 0; i < byk_kromosom; i++) {
            kromList[i] = new Kromosom();
        }
    }

    public Kromosom[] getKromList() {
        return kromList;
    }
}