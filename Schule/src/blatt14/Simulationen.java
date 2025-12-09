package blatt14;

import schisch_visualizer.SchischVisualizer;

public class Simulationen {

    public static void fuellen(char[][] charr, char ein, double ws) {
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[0].length; j++) {
                if (Math.random() < ws) {
                    charr[i][j] = ein;
                }
            }
        }
    }

    public static void fuellen(char[][] charr, char ein, double ws, char neutral) {
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[0].length; j++) {
                if (charr[i][j] == neutral) {
                    if (Math.random() < ws) {
                        charr[i][j] = ein;
                    }
                }

            }
        }
    }

    public static void platzieren(char[][] charr, char ein, boolean dopplung) {
        if (!dopplung) {
            int x = (int)(Math.random()*charr.length);
            int y = (int)(Math.random()*charr[0].length);
            charr[x][y] = ein;
        }
        while (dopplung) {
            int x = (int)(Math.random()*charr.length);
            int y = (int)(Math.random()*charr[0].length);
            if (charr[x][y] != ein) {
                charr[x][y] = ein;
                break;
            }
        }
    }

    public static char getNorden(char[][] arr, int x, int y, boolean rand) {
        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }
        if (!rand && y == 0) {
            return '-';
        }

        if (y == 0) {
            y = arr[x].length - 1;
        } else {
            y--;
        }

        return arr[x][y];
    }

    public static char getSueden(char[][] arr, int x, int y, boolean rand) {
        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && y == arr[x].length-1) {
            return '-';
        }

        if (y == arr[x].length-1) {
            y = 0;
        } else {
            y++;
        }
        return arr[x][y];
    }

    public static char getWesten(char[][] arr, int x, int y, boolean rand) {
        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && x == 0) {
            return '-';
        }

        if (x == 0) {
            x = arr.length - 1;
        } else {
            x--;
        }
        return arr[x][y];
    }

    public static char getOsten(char[][] arr, int x, int y, boolean rand) {
        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && x == arr.length-1) {
            return '-';
        }

        if (x == arr.length - 1) {
            x = 0;
        } else {
            x++;
        }

        return arr[x][y];
    }

    public static char getNordWest(char[][] arr, int x, int y, boolean rand) {

        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && y == 0) {
            return '-';
        }
        if (!rand && x == 0) {
            return '-';
        }

        if (y == 0) {
            y = arr[x].length - 1;
        } else {
            y--;
        }
        if (x == 0) {
            x = arr.length - 1;
        } else {
            x--;
        }

        return arr[x][y];
    }

    public static char getNordOst(char[][] arr, int x, int y, boolean rand) {

        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && y == 0) {
            return '-';
        }
        if (!rand && x == arr.length-1) {
            return '-';
        }

        if (y == 0) {
            y = arr[x].length - 1;
        } else {
            y--;
        }
        if (x == arr.length - 1) {
            x = 0;
        } else {
            x++;
        }

        return arr[x][y];
    }

    public static char getSuedWest(char[][] arr, int x, int y, boolean rand) {

        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && y == arr[x].length-1) {
            return '-';
        }
        if (!rand && x == 0) {
            return '-';
        }

        if (y == arr[x].length-1) {
            y = 0;
        } else {
            y++;
        }
        if (x == 0) {
            x = arr.length - 1;
        } else {
            x--;
        }

        return arr[x][y];
    }

    public static char getSuedOst(char[][] arr, int x, int y, boolean rand) {

        //Check for valid input
        if (x < 0 || x > arr.length - 1) {
            return '-';
        }
        if (y < 0 ||  y > arr[0].length - 1) {
            return '-';
        }

        if (!rand && y == arr.length-1) {
            return '-';
        }
        if (!rand && x == arr.length-1) {
            return '-';
        }

        if (y == arr.length - 1) {
            y = 0;
        } else {
            y++;
        }
        if (x == arr.length - 1) {
            x = 0;
        } else {
            x++;
        }

        return arr[x][y];
    }

    public static int zaehlenVier(char[][] arr, int x, int y, char such, boolean rand) {
        int count = 0;
        if (getNorden(arr,x,y,rand) == such) {
            count++;
        }
        if (getSueden(arr,x,y,rand) == such) {
            count++;
        }
        if (getWesten(arr,x,y,rand) == such) {
            count++;
        }
        if (getOsten(arr,x,y,rand) == such) {
            count++;
        }
        return count;
    }

    public static int zaehlenAcht(char[][] arr, int x, int y, char such, boolean rand) {
        int count = 0;
        if (getNorden(arr,x,y,rand) == such) {
            count++;
        }
        if (getSueden(arr,x,y,rand) == such) {
            count++;
        }
        if (getWesten(arr,x,y,rand) == such) {
            count++;
        }
        if (getOsten(arr,x,y,rand) == such) {
            count++;
        }

        if (getNordOst(arr,x,y,rand) == such) {
            count++;
        }
        if (getNordWest(arr,x,y,rand) == such) {
            count++;
        }
        if (getSuedOst(arr,x,y,rand) == such) {
            count++;
        }
        if (getSuedWest(arr,x,y,rand) == such) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        char[][] charr = new char[10][10];
        fuellen(charr,' ',1);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);

        System.out.println(getNorden(charr,2,0,true));
        System.out.println(getNorden(charr,2,2,true));
        sv.start();
    }
}
