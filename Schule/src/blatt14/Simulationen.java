package blatt14;

import schisch_visualizer.SchischVisualizer;

public class Simulationen {

    /**
     * Befüllt einen zweidimensionalen char-Array mit einem übergebenen Symbol mit einer bestimmten Verteilungsdichte
     * @param charr zweidimensionaler char-Array
     * @param ein zu verteilendes Symbol
     * @param ws Verteilungsdichte in % (zwischen 0 und 1)
     */
    public static void fuellen(char[][] charr, char ein, double ws) {
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[0].length; j++) {
                if (Math.random() < ws) {
                    charr[i][j] = ein;
                }
            }
        }
    }

    /**
     * Befüllt einen zweidimensionalen char-Array mit einem übergebenen Symbol mit einer bestimmten Verteilungsdichte, wobei nur auf einem neutralen Element verteilt wird
     * @param charr zweidimensionaler char-Array
     * @param ein zu verteilendes Symbol
     * @param ws Verteilungsdichte in % (zwischen 0 und 1)
     * @param neutral neutrales Feld
     */
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

    /**
     * Platziert ein übergebenes Element auf einem zufälligen Feld im zweidimensionalen char-Array
     * @param charr zweidimensionaler char-Array
     * @param ein zu platzierendes Element
     * @param dopplung falls true, darf das Element nicht ein gleiches Element überschrieben
     */
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

    /**
     * Gibt das Element nördlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich nördlich vom Feld befindet
     */
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

    /**
     * Gibt das Element südlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich südlich vom Feld befindet
     */
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

    /**
     * Gibt das Element westlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich westlich vom Feld befindet
     */
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


    /**
     * Gibt das Element östlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich östlich vom Feld befindet
     */
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

    /**
     * Gibt das Element nordwestlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich nordwestlich vom Feld befindet
     */
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

    /**
     * Gibt das Element nordöstlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich nordöstlich vom Feld befindet
     */
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


    /**
     * Gibt das Element südwestlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich südwestlich vom Feld befindet
     */
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

    /**
     * Gibt das Element südöstlich von einer Position zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param rand falls true, darf über den Rand geschaut werden
     * @return char, der sich südöstlich vom Feld befindet
     */
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

    /**
     * Zählt das Aufkommen eines Suchelementes vierdirektional um das Feld herum und gibt die Anzahl zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param such Suchelement
     * @param rand falls true, darf über den Rand geschaut werden
     * @return Anzahl des Aufkommens des Suchelementes
     */
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

    /**
     * Zählt das Aufkommen eines Suchelementes achtdirektional um das Feld herum und gibt die Anzahl zurück.
     * @param arr zweidimensionaler char-Array
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @param such Suchelement
     * @param rand falls true, darf über den Rand geschaut werden
     * @return Anzahl des Aufkommens des Suchelementes
     */
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
