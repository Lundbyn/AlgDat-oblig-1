import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Oblig1 {

    public static void main(String[] args) {
        System.out.println("Hello world");

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
                int temp = a[i];
                a[i] = a[i-1];
                a[i-1] = temp;
            }
        }
        System.out.println();
        return a[a.length-1];
    }

    //Sjekker hvor mange ombyttinger som blir utført
    public static int ombyttinger(int[] a) {
        int antOmbyttinger = 0;
        for (int i = 1; i < a.length; ++i) {
            if(a[i] < a[i-1]) {
                int temp = a[i];
                a[i] = a[i-1];
                a[i-1] = temp;
                antOmbyttinger++;
            }
        }
        return antOmbyttinger;
    }


    //Skriver ut en array [FJERNES]
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

    //Bytter plass på 2 elementer i et array
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
