package blatt10;

public class DNA {

    public static boolean istDNA(String dna) {

        char[] charr = dna.toCharArray();
        if (charr.length%2 != 0) {
            return false;
        }

        for (int i = 0; i < charr.length-1; i+=2) {
            if (charr[i] == 'A') {
                if (charr[i+1] != 'T') {

                    return false;
                }
            }

            if (charr[i] == 'T') {
                if (charr[i+1] != 'A') {

                    return false;
                }
            }

            if (charr[i] == 'G') {
                if (charr[i+1] != 'C') {

                    return false;
                }
            }

            if (charr[i] == 'C') {
                if (charr[i+1] != 'G') {

                    return false;
                }
            }


            if (charr[i] != 'A' && charr[i] != 'C' && charr[i] != 'G' && charr[i] != 'T') {

                return false;
            }


        }

        return true;
    }

    public static byte[] zuDNA(String dna) {
        if (istDNA(dna)) {
            char[] charr = dna.toCharArray();
            byte[] dnaByte =  new byte[charr.length];
            for (int i = 0; i < charr.length; i++) {
                if (charr[i] == 'A') {
                    dnaByte[i] = 0;
                }
                if (charr[i] == 'T') {
                    dnaByte[i] = 1;
                }
                if (charr[i] == 'G') {
                    dnaByte[i] = 2;
                }
                if (charr[i] == 'C') {
                    dnaByte[i] = 3;
                }

            }
            return dnaByte;
        }
        return null;
    }

    public static String zuDNA(byte[] dna) {
        if (dna.length % 2 != 0) {
            return null;
        }
        String d = "";

        for (int i = 0; i < dna.length; i++) {
            if (dna[i] == 0) {
                d = d + "A";
            }
            if (dna[i] == 1) {
                d = d + "T";
            }
            if (dna[i] == 2) {
                d = d + "G";
            }
            if (dna[i] == 3) {
                d = d + "C";
            }
            if (dna[i] < 0 || dna[i] > 3) {
                return null;
            }


        }
        return d;
    }

    public static boolean[] zuDNA_Bool(String dna) {
        boolean[] bool = new boolean[dna.length()*2];
        if (istDNA(dna)) {
            byte[] dnab = zuDNA(dna);
            for (int i = 0; i < dnab.length; i++) {
                if (dnab[i] == 0) {
                    bool[i*2] = false;
                    bool[i*2+1] = false;
                }

                if (dnab[i] == 1) {
                    bool[i*2] = false;
                    bool[i*2+1] = true;
                }

                if (dnab[i] == 2) {
                    bool[i*2] = true;
                    bool[i*2+1] = false;
                }

                if (dnab[i] == 3) {
                    bool[i*2] = true;
                    bool[i*2+1] = true;
                }


            }
            return bool;
        }
        return null;
    }

    public static void main(String[] args) {
        String dna = "ATGCTA";
        System.out.println(istDNA(dna));
        byte[] dnab = zuDNA(dna);
        blatt07.ArbeitMitArrays.printArray(dnab);
        System.out.println();
        String dnad = zuDNA(dnab);
        System.out.println(dnad);
        boolean[] bool = zuDNA_Bool(dna);
        blatt07.ArbeitMitArrays.printArray(bool);

    }
}
