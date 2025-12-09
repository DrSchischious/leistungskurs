package kursarbeit01;

public class TimeAdjustment {

    public static void regulateTime(int[] cl) {
        //Breakpoint
        blatt07.ArbeitMitArrays.printArray(cl);
        while (Math.abs(cl[cl.length-1] - cl[0]) > 250) {
            adjustTime(cl);
            //Breakpoint
            blatt07.ArbeitMitArrays.printArray(cl);

        }
    }

    public static void adjustTime(int[] cl) {
        for (int i = 0; i < cl.length-1; i++) {
            int mid = (cl[i]+cl[i+1])/2;
            cl[i] = mid;
            cl[i+1] = mid;
        }
    }

    public static void main(String[] args) {
        int[] cl = new int[]{31000, 33000, 27000, 31000, 33000, 29000};
        regulateTime(cl);
    }
}
