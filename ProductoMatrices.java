import java.io.*;

public class ProductoMatrices {
    public static void main(String[] args) {

        try {

            FileInputStream fileInputStreamMa = new FileInputStream("C:\\Users\\Uziel\\Downloads\\a.mat"); // Abre el flujo
            int filasMa = fileInputStreamMa.read(); // lee las filas matriz a.mat
            int columnasMa = fileInputStreamMa.read(); // lee las columnas b.mat
            double[][] matriz_amat = datosMatriz(fileInputStreamMa, filasMa, columnasMa); // acomoda los datos de la matriz a.mat
            fileInputStreamMa.close(); // cierra el flujo

            FileInputStream fileInputStreamMb = new FileInputStream("C:\\Users\\Uziel\\OneDrive\\Escritorio\\ArchivosTareas\\b.mat"); // abre el flujo
            int filasMb = fileInputStreamMb.read(); // lee las filas matriz b.mat
            int columnasMb = fileInputStreamMb.read(); // lee columnas b.mat
            double[][] matriz_bmat = datosMatriz(fileInputStreamMb, filasMb, columnasMb);
            fileInputStreamMb.close(); // cierra el flujo

            double[][] productoMatrices = multiplicarMatrices(matriz_amat, matriz_bmat); // acomoda los datos del producto de la matriz b.mat y b.mat

            FileOutputStream archivoSalida = new FileOutputStream("c.mat"); // Nombre del archivo de salida de la matriz c.mat
            archivoSalida.write(productoMatrices.length);  // escribe el resultado del producto
            archivoSalida.write(productoMatrices[0].length);
            resultMatrizC(archivoSalida, productoMatrices);
            archivoSalida.close(); // cierra el flujo del archivo de salida


            FileInputStream fileInputStreamMc = new FileInputStream("c.mat"); // abre el flujo con el archivo c.mat
            int filasMc = fileInputStreamMc.read(); // entero que almacena los datos de las filas para c.mat
            int columnasMc = fileInputStreamMc.read(); // entero que almacena los datos de las columnas para c.mat
            double[][] matriz_cmat = datosMatriz(fileInputStreamMc, filasMc, columnasMc); // arreglo que crea la matriz
            fileInputStreamMc.close();

            //Imprimir matrices

            System.out.println("Matriz a (a.mat): ");
            imprimirMatrices(matriz_amat);

            System.out.println("Matriz b (b.mat): ");
            imprimirMatrices(matriz_bmat);

            System.out.println("Matriz c = ((Matriz a)(Matriz b)): ");
            imprimirMatrices(matriz_cmat);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodos
    private static double[][] multiplicarMatrices(double[][] A, double[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
    private static void resultMatrizC(OutputStream stream, double[][] matrix) throws IOException {
        DataOutputStream dataStream = new DataOutputStream(stream);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dataStream.writeDouble(matrix[i][j]);
            }
        }
    }
    private static void imprimirMatrices(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    private static double[][] datosMatriz(InputStream stream, int rows, int cols) throws IOException {
        double[][] matrix = new double[rows][cols];
        DataInputStream dataStream = new DataInputStream(stream);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = dataStream.readDouble();
            }
        }
        return matrix;
    }
}