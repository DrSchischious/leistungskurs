package blatt16;

import blatt07.ArbeitMitArrays;

import java.io.*;
import java.nio.ByteBuffer;

public class Zipper {

    public static byte[] toByteArray(long l) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(l);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }


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

    public static void demush(String file) {
        File mush = new File(file);
        if (mush.isFile()) {
            if (mush.getName().contains(".mush")) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mush);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    byte[] buffer = bis.readAllBytes();
                    //blatt07.ArbeitMitArrays.printArray(buffer);

                    int i = 0;
                    while (i < buffer.length) {
                        //First byte is length
                        long len = buffer[i];
                        i++;

                        int j = 0;
                        //Read the Filename
                        String s = "";
                        while (j < len) {
                            s += (char)buffer[i];
                            j++;
                            i++;
                        }
                        //FileWriter
                        System.out.println(new File(mush.getParent()).getPath()+"\\"+s);

                        FileOutputStream fos = new FileOutputStream(new File(mush.getParent()).getPath()+"\\"+s);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);

                        //Write File!
                        //Amount of Bytes
                        len = buffer[i];

                        j = 0;
                        long k = 0;

                        byte[] fileSize = new byte[buffer[i]];
                        i++;

                        while (j < len) {
                            fileSize[j] = buffer[i];
                            i++;
                            j++;
                        }

                        k = bytesToLong(fileSize);
                        System.out.println(k);
                        //Read amount of bytes
                        for (int p = 0; p < k; p++) {
                            bos.write(bis.read());
                        }

                        bos.close();
                        fos.close();
                        //System.out.println(s);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }




            }
        }
    }

    public static void mush(String directory, String mushName) {


        //Get all Files in Directory
        File dir = new File(directory);
        if (dir.isDirectory()) {
            File mush = new File(directory+"\\"+mushName+".mush");
            FileOutputStream mushWriter = null;
            try {
                mushWriter = new FileOutputStream(mush);
                BufferedOutputStream buffedMushWriter = new BufferedOutputStream(mushWriter);
                //good
                //Get all Sizes (and Filetypes) (and and Names)

                ArbeitMitArrays.printArray(dir.list());

                for(File f : dir.listFiles()) {
                    //Amount of Bytes of Filename
                    //Then Filename
                    if (!f.getName().contains(".mush") && !f.isDirectory()) {
                        System.out.println(f.getName().getBytes().length);
                        buffedMushWriter.write(f.getName().getBytes().length);
                        System.out.println(f.getName());
                        buffedMushWriter.write(f.getName().getBytes());





                        //Amount of Bytes of Size
                        //Then Size

                        System.out.println(toByteArray(f.length()).length);
                        buffedMushWriter.write(toByteArray(f.length()).length);

                        System.out.println(f.length());
                        buffedMushWriter.write(toByteArray(f.length()));

                        //Complete File
                        FileInputStream fis = new FileInputStream(f.getAbsolutePath());
                        BufferedInputStream bis = new BufferedInputStream(fis);

                        buffedMushWriter.write(bis.readAllBytes());


                        bis.close();
                        fis.close();
                    }


                }

                buffedMushWriter.close();
                //Save all Info in Filestart

                //Mush together
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            //no directory
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
        //divide("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\Really\\[HorribleSubs] JoJo's Bizarre Adventure - Golden Wind - 37 [1080p].mkv",20);
        //conquer("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\Really\\[HorribleSubs] JoJo's Bizarre Adventure - Golden Wind - 37 [1080p].mkv");
        //mush("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\NewDir","ha");

        demush("C:\\Users\\DanielSchisch.AzureAD\\Desktop\\NewDir\\ha.mush");
    }
}
