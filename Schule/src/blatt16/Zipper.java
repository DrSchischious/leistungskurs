package blatt16;

import java.io.*;

public class Zipper {

    public static String partName(int index) {
        if (index < 10) {
            return "000"+index;
        } else if (index < 100) {
            return "00"+index;
        } else if (index < 1000) {
            return "0"+index;
        } else {
            return ""+index;
        }
    }

    public static void divide(String filepath, int cluster) {
        System.out.println("Start the Divide!");
        File f = new File(filepath);
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int index = 0;

            while (true) {
                File part = new File(filepath+"."+partName(index));
                FileOutputStream fos = new FileOutputStream(part);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                int rest = cluster*1024*1024; //In MB

                if (bis.available()>rest) {
                    byte[] b = new byte[rest];
                    bis.read(b, 0, rest);
                    bos.write(b);
                } else {
                    int n = bis.available();
                    byte[] b = new byte[n];
                    bis.read(b, 0, n);
                    bos.write(b);

                    bos.close();
                    break;
                }

                index++;

            }
            bis.close();



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void conquer(String filepath) {
        System.out.println("Start the Conquer!");
        int index = 0;
        try {
            FileOutputStream fos = new FileOutputStream(new File(filepath));
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            while(true) {
                File f = new File(filepath+"."+partName(index));
                try {
                    FileInputStream fis= new FileInputStream(f);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    while (bis.available() > 0) {
                        byte[] b = bis.readAllBytes();
                        bos.write(b);
                    }
                    bis.close();


                } catch (FileNotFoundException e) {
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                index++;
            }

            bos.close();



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        //C:\Users\DanielSchisch.AzureAD\Desktop\Really\[HorribleSubs] JoJo's Bizarre Adventure - Golden Wind - 37 [1080p].mkv
        //divide("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\Really\\[HorribleSubs] JoJo's Bizarre Adventure - Golden Wind - 37 [1080p].mkv",40);
        conquer("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\Really\\[HorribleSubs] JoJo's Bizarre Adventure - Golden Wind - 37 [1080p].mkv");
    }
}
