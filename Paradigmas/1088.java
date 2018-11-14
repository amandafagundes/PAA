import java.util.Scanner;

/**
 *
 * @author amanda
 */
public class Teste2 {

    private static Scanner sc = new Scanner(System.in);
    private static int countSwaps = 0;
    
    public static void main(String args[]) {
        do {
            String input = sc.nextLine();
            if (input.equals("0")) {
                break;
            }
            String[] vector = input.split(" ");
            int[] A = new int[vector.length - 1];
            countSwaps = 0;
            
            for (int i = 0; i < A.length; i++) {
                A[i] = Integer.parseInt(vector[i + 1]);
            }
            
            mergeSort(A, 0, A.length - 1);
            
            if(countSwaps % 2 != 0){
                System.out.println("Marcelo");
            }else{
                System.out.println("Carlos");
            }
        } while (true);
    }

    static void mergeSort(int A[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            
            mergeSort(A, l, m);
            mergeSort(A, m + 1, r);

            merge(A, l, m, r);
        }
    }

    static void merge(int A[], int left, int mid, int right) {
        int L[] = new int[mid - left + 1];//do inicio ao meio + 1
        int R[] = new int[right - mid];//do meio ao final

        for (int i = 0; i < L.length; ++i) {
            L[i] = A[left + i];
        }
        for (int j = 0; j < R.length; ++j) {
            R[j] = A[mid + 1 + j];
        }

        int i = 0, j = 0;

        int k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {//se o da esquerda for menor, apenas copia para a posição adequada pois está ordenado
                A[k] = L[i];
                i++;
            } else {//se o da direita for menor, a troca ocorre mas não necessariamente entre vizinhos
                A[k] = R[j];
                j++;
                //conta o quão distante o elemento da esquerda está do elemento pelo qual será trocado
                //(indica quantas trocas em sequência teriam que ser realizadas para que o resultado fosse obtido)
                countSwaps += L.length - i; 
            }
            k++;
        }

        //o final de um vetor pode ser atingido antes do outro e o loop ser interrompido
        //por isso copia os elementos restantes no vetor original
        while (i < L.length) {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < R.length) {
            A[k] = R[j];
            j++;
            k++;
        }
    }
}