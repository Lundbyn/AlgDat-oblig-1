import java.util.*;

public class Oblig1 {

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q'};
        System.out.println(Arrays.toString(a));
        rotasjon(a, 34);
        System.out.println("A: " + a.length);
        System.out.println(Arrays.toString(a));
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
    Ved å bruke ombyttinger-fuksjonen fant jeg at når man bruker et stort tall (big O) gjør den nesten n ombyttinger
    Vi kan derfor si at gjennomsnittet er O(n).
    - Kan du på grunnlag av dette si om metoden maks er bedre (eller dårligere) enn de maks-metodene vi har sett på tidligere?
    Begge har en big O notation på O(n), så de er ca like raske i snitt. Denne maks funskjonen har flere operasjoner inne i
    if statementet enn de gamle, derfor er denne hakket dårligere enn de andre maks-fuksjonene.
    */

    //Boblesortering som sender det storste tallet i tabellen til siste posisjon i arrayen.
    public static int maks(int[] a) {
        if(a.length == 0) {
            throw new NoSuchElementException("Lengden på arrayen kan ikke være 0");
        }
        for (int i = 1; i < a.length; ++i) {
            if(a[i] < a[i-1]) {
                bytt(a, i, i-1);
            }
        }
        return a[a.length-1];
    }

    //Sjekker hvor mange ombyttinger som blir utført
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
            return 0;       //Returnerer 0 dersom arrayen har lengde på 0.
        }
        for(int i = 1; i < a.length; ++i) {
            if (a[i] < a[i-1]) {
                throw new IllegalStateException("Arrayet er ikke sortert");    //sjekker at arrayen er sortert i stigende rekkefølge
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
    public static int antallUlikeUsortert(int[] a) { //Funker også dersom arrayet er sortert
        int antUlike = 0;
        for(int i = 0; i < a.length; ++i) {
            boolean nyVerdi = true;
            for (int j = 0; j < i; ++j) {
                if(a[i] == a[j]) {
                    nyVerdi = false;
                    break;      //Break for å ikke iterere videre dersom man finner en lik verdi.
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

    }

    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
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
            for (int j = i - k, n = i; j != i; j -= k)
            {
                if (j < 0) j += lengde;
                a[n] = a[j]; n = j;
            }
            a[i + k] = verdi;
        }
    }

    public static int euklids(int a, int k)
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
        int max = s[0].length();
        for(int i = 1; i < s.length; i++) {         //Finner lengden på lengste String
            if(s[i].length() > s[i-1].length()) {
                max = s[i].length();
            }
        }

        String flettet = "";
        for (int i = 0; i < max; i++) {             //itererer gjennom s like mange ganger som lengde på lengste String
            for(int j = 0; j < s.length; j++) {
                try {
                    flettet += s[j].charAt(i);      //Legger til tegn nr i fra hver String i den nye Stringen "flettet"
                }
                catch (IndexOutOfBoundsException e) {   //Dersom Stringen ikke har flere tegn går den til neste String
                    continue;
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
            throw new NoSuchElementException("Arrayen må inneholde minst 3 elementer");
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


    //Hjelpefunksjoner

    //Skriver ut en array
    public static void skriv(int[] a) {
        for(int i : a) {
            System.out.print(i + ", ");
        }
    }

    //lager en array fra 1 til n i tilfeldig rekkefølge
    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }

    //Bytter plass på 2 integer elementer i et array
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

    //Kopierer n elementer fra et array inn i et nytt array. Starter på indeks 0.
    public static int[] kopierArray(int[] a, int n) {
        int[] b = new int[n];
        for(int i = 0; i < n; ++i) {
            b[i] = a[i];
        }
        return b;
    }
}