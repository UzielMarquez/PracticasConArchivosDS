import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Histograma {
    public static void main(String[] args) {

        String ubicacion = "C:\\Users\\Uziel\\OneDrive\\Escritorio\\ArchivosTareas\\divina_comedia_sp.txt";
        HashMap<Integer, Integer> histograma = new HashMap<>();

        try{

            BufferedReader reader = new BufferedReader(new FileReader(ubicacion));

            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.split("\\s=");

                for (String palabra : palabras) {
                    palabra = palabra.replaceAll("[^a-zA-Z]", "");
                    int longitud = palabra.length();

                    if(longitud >= 2 && longitud <= 10){

                        histograma.put(longitud, histograma.getOrDefault(longitud, 0)+1);

                    }
                }
            }

            reader.close();

            for (int longitud = 2; longitud <= 10; longitud++){
                int freciencia = histograma.getOrDefault(longitud, 0);
                System.out.printf("palabras con "+"%d caracteres: %s%n", longitud, "▉".repeat(freciencia) );
            }

        }catch (IOException e){

            System.out.println("No fue posible leer el archivo: " + e.getMessage());

        }
    }
}
