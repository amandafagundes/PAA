import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author amanda
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int text_size;
        int[] asc_table = new int[256];//tabela ASCII
        Scanner sc = new Scanner(System.in);

        while (true) {
            //inicialmente todos os caracteres são usados 0 vezes
            for (int i = 0; i < asc_table.length; i++) {
                asc_table[i] = 0;
            }

            int count = 0;//quantidade de caracteres da substring
            int max = 0;//tamanho da maior substring
            int start = 0;//início da substring

            int n_keys = Integer.parseInt(sc.nextLine());
            if (n_keys == 0) {
                break;
            }
            String text = sc.nextLine();

            text_size = text.length();

            int end = 0;
            while (end <= text_size - 1) {
                char character = text.charAt(end);
                //se o caracter atual ainda não tiver ocorrido antes
                if (asc_table[(int) character] == 0) {
                    count++;//incrementa a quantidade de caracteres
                }
                asc_table[(int) character] = asc_table[(int) character] + 1;//incrementa a quantidade de caracteres

                //enquanto o número de caracteres for além do permitido
                while (count > n_keys) {
                    char old = text.charAt(start);//pega o caracter atual do início da string
                    //se ele estiver presente na substring SOMENTE UMA VEZ, a quantidade de caracteres da substring é decrescida
                    if (asc_table[(int) old] == 1) {
                        count--;
                    }
                    asc_table[(int) old] = asc_table[(int) old] - 1;
                    start++;
                }

                if ((end - start + 1) > max) {
                    max = end - start + 1;
                }
                end++;
            }

            System.out.println(max);

        }

    }
}
