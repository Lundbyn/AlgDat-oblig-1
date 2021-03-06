import java.util.*;

public class Oblig1 {

    public static void main(String[] args) {

    }

    //Oppgave 1)

    /*

    Teorisporsmaal:
   - Hvor mange sammenligninger (som funksjon av n) blir det for en tabell med n verdier.
   Funksjonen iterer gjennom arrayen en gang, hvor den kjorer en sammeligning per iterasjon.
   Siden for-loopen starter paa i = 1 blir antall sammenligninger = n - 1.

   - Naar blir det flest ombyttinger?
   Det blir flest ombyttinger dersom det storste tallet i arrayen er paa den posisjon 0.

   - Naar blir det faerrest?
   Det blir faerrest ombyttinger dersom det storste tallet i arrayen er paa siste posisjon (array.length - 1).

   - Hvor mange blir det i gjennomsnitt?
    Ved aa bruke ombyttinger-fuksjonen fant jeg at naar man bruker et stort tall (big O) gjor den nesten n ombyttinger
    Vi kan derfor si at gjennomsnittet er O(n).

    - Kan du paa grunnlag av dette si om metoden maks er bedre (eller daarligere) enn de maks-metodene vi har sett paa tidligere?
    Begge har en big O notation paa O(n), saa de er ca like raske i snitt. Denne maks funskjonen har flere operasjoner inne i
    if statementet enn de gamle, derfor er denne hakket daarligere enn de andre maks-fuksjonene.

    */

    //Boblesortering som sender det storste tallet i tabellen til siste posisjon i arrayen.
    public static int maks(int[] a) {
        if(a.length == 0) {
            throw new NoSuchElementException("Lengden paa arrayen kan ikke vaere 0");
        }
        for (int i = 1; i < a.length; ++i) {
            if(a[i] < a[i-1]) {
                bytt(a, i, i-1);
            }
        }
        return a[a.length-1];
    }

    //Sjekker hvor mange ombyttinger som blir utfort
    public static int ombyttinger(int[] a) {
        int antOmbyttinger = 0;
        for (int i = 1; i < a.length; ++i) {
            if(a[i] < a[i-1]) {
                bytt(a, i, i-1);
                antOmbyttinger++;
            }
        }
        return antOmbyttinger;
    }

    //Oppgave 2
    public static int antallUlikeSortert(int[] a) {
        if(a.length == 0) {
            return 0;       //Returnerer 0 dersom arrayen har lengde paa 0.
        }
        for(int i = 1; i < a.length; ++i) {
            if (a[i] < a[i-1]) {
                throw new IllegalStateException("Arrayet er ikke sortert");    //sjekker at arrayen er sortert i stigende rekkefolge
            }
        }
        int antUlike = 1;
        for(int i = 1; i < a.length; ++i) {
            if(a[i] != a[i-1]) {
                antUlike++;
            }
        }
        return antUlike;
    }

    //Oppgave 3
    public static int antallUlikeUsortert(int[] a) { //Funker ogsaa dersom arrayet er sortert
        int antUlike = 0;
        for(int i = 0; i < a.length; ++i) {
            boolean nyVerdi = true;
            for (int j = 0; j < i; ++j) {
                if(a[i] == a[j]) {
                    nyVerdi = false;
                    break;      //Break for aa ikke iterere videre dersom man finner en lik verdi.
                }
            }
            if(nyVerdi) {
                antUlike++;
            }
        }
        return antUlike;
    }

    //Oppgave 4
    public static void delsortering(int[] a) {
        if(a.length < 2) {
            return;
        }
        int v = 0;
        int h = a.length-1;
        while (true) {
            while (v < a.length && a[v] % 2 != 0 ) {
                v++;
            }
            while (h > 0 && a[h] % 2 == 0) {
                h--;
            }
            if(v >= h) {
                break;
            }
            if(v < h) {
                bytt(a, v, h);
            }
        }

        kvikksortering(a,0,h);
        kvikksortering(a, v, a.length - 1);
    }

    private static int parter(int[] a, int v, int h, int m)
    {
        while (true)
        {
            while (v <= h && a[v] < m) {
                v++;
            }
            while (v <= h && a[h] >= m) {
                h--;
            }

            if (v < h) {
                bytt(a,v,h);
                v++;
                h++;
            }
            else  return v;
        }
    }

    private static int sParter(int[] a, int v, int h, int skilleverdi)
    {
        bytt(a, skilleverdi, h);
        int skilleverdi_indeks = parter(a, v, h - 1, a[h]);
        bytt(a, skilleverdi_indeks, h);
        return skilleverdi_indeks;
    }

    private static void kvikksortering(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) {
            return;
        }
        int k = sParter(a, v, h, (v + h)/2);
        kvikksortering(a, v, k - 1);
        kvikksortering(a, k + 1, h);
    }

    //Oppgave 5
    public static void rotasjon(char[] a) {
        for (int i = a.length - 1; i > 0; --i) {
            bytt(a, i, i-1);
        }
    }

    //Oppgave 6
    public static void rotasjon(char[] a, int k)    // 3. versjon
    {
        int lengde = a.length;
        if (lengde < 2) {
            return;
        }

        k = k % lengde;
        if (k < 0) {
            k += lengde;
        }

        int s = euklids(lengde, k);

        for (int i = 0; i < s; i++)
        {
            char verdi = a[i];
            int n = i;
            for (int j = i - k; j != i; j -= k)
            {
                if (j < 0) j += lengde;
                a[n] = a[j];
                n = j;
            }
            a[i + k] = verdi;
        }
    }

    public static int euklids(int a, int k) ///Finner storste felles divisor
    {
        if (k == 0) {
            return a;
        }
        else {
            return euklids(k, a % k);
        }
    }

    //Oppgave 7a
    public static String flett(String s, String t) {
        String flettet = "";
        int i = 0;
        while (i < s.length() && i < t.length()) {
            flettet += s.charAt(i);
            flettet += t.charAt(i);
            i++;
        }
        while (i < s.length()) {
            flettet += s.charAt(i++);
        }
        while (i < t.length()) {
            flettet += t.charAt(i++);
        }
        return flettet;
    }

    //Oppgave 7b
    public static String flett(String... s) {
        if(s.length < 1) {
            return "";
        }
        int max = s[0].length();
        for(int i = 1; i < s.length; i++) {         //Finner lengden paa lengste String
            if(s[i].length() > max) {
                max = s[i].length();
            }
        }


        String flettet = "";
        for (int i = 0; i < max; i++) {             //itererer gjennom s like mange ganger som lengde paa lengste String
            for(int j = 0; j < s.length; j++) {
                try {
                    flettet += s[j].charAt(i);
                } catch (StringIndexOutOfBoundsException e) {

                }
            }
        }
        return flettet;
    }

    //Oppgave 8
    public static int[] indekssortering(int[] a) {
        int[] indeks = new int[a.length];
        for(int i = 0; i < indeks.length; ++i) {
            indeks[i] = i;
        }
        for(int i = a.length - 1; i > 0; --i) {
            for(int j = 0; j < i; ++j) {
                if(a[indeks[i]] < a[indeks[j]]) {
                    bytt(indeks, i, j);
                }
            }
        }
        return indeks;
    }

    //Oppgave 9
    public static int[] tredjeMin(int[] a) {
        if(a.length < 3) {
            throw new NoSuchElementException("Arrayen maa inneholde minst 3 elementer");
        }
        int[] startIndekser = indekssortering(kopierArray(a, 3));

        int minstIndeks = startIndekser[0];
        int nestMinstIndeks = startIndekser[1];
        int tredjMinstIndeks = startIndekser[2];

        int minst = a[minstIndeks];
        int nestMinst = a[nestMinstIndeks];
        int tredjMinst = a[tredjMinstIndeks];

        for(int i = 3; i < a.length; i++) {
            if(a[i] < tredjMinst) {
                if(a[i] < nestMinst) {
                    if(a[i] < minst) {
                        tredjMinstIndeks = nestMinstIndeks;
                        nestMinstIndeks = minstIndeks;
                        minstIndeks = i;

                        tredjMinst = nestMinst;
                        nestMinst = minst;
                        minst = a[i];
                        continue;
                    }
                    tredjMinstIndeks = nestMinstIndeks;
                    nestMinstIndeks = i;

                    tredjMinst = nestMinst;
                    nestMinst = a[i];
                    continue;
                }
                tredjMinstIndeks = i;

                tredjMinst = a[i];
                continue;
            }
        }

        int[] minsteVerdier = {minstIndeks, nestMinstIndeks, tredjMinstIndeks};

        return minsteVerdier;
    }

    //Oppgave 10
    public static boolean inneholdt(String a, String b) {
        if(a.length() == 0) {
            return true;
        }
        if(b.length() == 0) {
            return false;
        }

        int[] a_ASCII = new int[a.length()];
        int[] b_ASCII = new int[b.length()];

        for(int i = 0; i < a_ASCII.length; ++i) {
            a_ASCII[i] = a.charAt(i);
        }

        for(int i = 0; i < b_ASCII.length; ++i) {
            b_ASCII[i] = b.charAt(i);

        }


        //Stack overflow :(
        //kvikksortering(a_ASCII, 0, a_ASCII.length - 1);
        //kvikksortering(b_ASCII, 0, b_ASCII.length - 1);

        flettesortering(a_ASCII);
        flettesortering(b_ASCII);

        int j = 0;
        for (int i = 0; i < b_ASCII.length; ++i) {
            if(a_ASCII[j] == b_ASCII[i]) {
                j++;
                if(j == a_ASCII.length) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void flettesortering(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i] = a[i];
        }
        flettesorter(a,b,0,n);
    }

    public static void flettesorter(int[] a, int[] b, int fra, int til) {
        if(til - fra < 2) {
            return;
        }
        int m = (fra + til) / 2;

        flettesorter(a,b,fra,m);
        flettesorter(a,b,m,til);

        if(a[m-1] > a[m]) {
            flettInt(a,b,fra,m,til);
        }
    }

    public static void flettInt(int[] a, int[] b, int fra, int m, int til) {
        int n = m - fra;
        for(int i = 0; i < n; ++i) {
            b[i] = a[i + fra];
        }

        int i = 0;
        int j = m;
        int k = fra;

        while (i < n && j < til) {
            if(b[i] <= a[j]) {
                a[k] = b[i];
                i++;

            }
            else {
                a[k] = a[j];
                j++;
            }
            k++;
            //a[k] = b[i] <= a[j] ? b[i] : a[j];
        }

        while (i < n) {
            a[k] = b[i];
            k++;
            i++;
        }
    }


    //Hjelpefunksjoner


    //lager en array fra 1 til n i tilfeldig rekkefolge
    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // loop som gaar n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }

    //Bytter plass paa 2 integer elementer i et array
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void bytt(char[] a, int i, int j)
    {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //Kopierer n elementer fra et array inn i et nytt array. Starter paa indeks 0.
    public static int[] kopierArray(int[] a, int n) {
        int[] b = new int[n];
        for(int i = 0; i < n; ++i) {
            b[i] = a[i];
        }
        return b;
    }
}