import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String namafile = "C:/belajarjava/kodingan/UASLANJUT/src/trapezoid.csv";
        String pemisah = ",";
        List<BODY> LIST = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(namafile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] column = line.split(pemisah);
                BODY r = new BODY();

                r.A = Double.parseDouble(column[0]);
                r.B = Double.parseDouble(column[1]);
                r.tinggi = Double.parseDouble(column[2]);
                r.C = Math.sqrt(Math.pow((r.A - r.B)/2, 2) + Math.pow(r.tinggi, 2));

                r.keliling = r.A + r.B + 2 * r.C;
                r.luas = (r.A + r.B) * r.tinggi / 2;

                LIST.add(r);

                System.out.println("\nnilai A :" + r.A);
                System.out.println("\nnilai B :" + r.A);
                System.out.println("\nnilai C :" + r.A);
                System.out.println("\nnilai keliling :" + r.A);
                System.out.println("\nnilai luas :" + r.A);

            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + namafile);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading file: " + namafile);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
            e.printStackTrace();
        }

        sortKeliling(LIST);

        System.out.println("keliling:");
        for (BODY r : LIST) {
            System.out.println("\n Keliling = " + r.keliling);
        }
        String outputFileName = "C:/belajarjava/kodingan/UASLANJUT/src/EXTRACT.txt";
        masukinkefile(LIST, outputFileName);
    }

    public static void sortKeliling(List<BODY> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).keliling > list.get(j + 1).keliling) {
                    BODY temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void masukinkefile(List<BODY> list, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (BODY r : list) {
                bw.write("Keliling = " + r.keliling + "\n");
            }
            System.out.println("Sorted keliling values have been written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }
}

