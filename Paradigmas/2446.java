import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author amanda
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static int[] moedas;
    private static int valor_compra;
    private static int qtd_moedas;

    public static void main(String[] args) {
        String[] info_vector = sc.nextLine().split(" ");
        String[] moedas_vector = sc.nextLine().split(" ");

        valor_compra = Integer.parseInt(info_vector[0]);
        qtd_moedas = Integer.parseInt(info_vector[1]);

        moedas = new int[qtd_moedas + 1];
        moedas[0] = 0;
        for (int i = 1; i <= qtd_moedas; i++) {
            moedas[i] = Integer.parseInt(moedas_vector[i - 1]);
        }

        Arrays.sort(moedas);

        boolean resp = calculaTroco(moedas, valor_compra);
        if (resp) {
            System.out.println("S");
        } else {
            System.out.println("N");
        }
    }

    private static boolean calculaTroco(int[] moedas, int valor) {
        boolean matriz[][] = new boolean[moedas.length][valor + 1];

        for (int i = 0; i < moedas.length; i++) {//i representa as moedas
            for (int j = 0; j <= valor; j++) {//j representa os valores
                if (j == moedas[i] || j == 0 || (i > 0 && matriz[i - 1][j])) {
                    matriz[i][j] = true;
                } else if (j > moedas[i] && i != 0) {
                    int resto = j - moedas[i];
                    if (matriz[i - 1][resto]) {
                        matriz[i][j] = true;
                    } else {
                        matriz[i][j] = false;
                    }
                } else {
                    matriz[i][j] = false;
                }
            }

        }

        return matriz[moedas.length - 1][valor];
    }

}