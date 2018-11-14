import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author amanda
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Queijo> queijos = new ArrayList<>();
    private static double dinheiro = 0;

    public static void main(String[] args) {
        String[] vector_info, vector_precos, vector_volumes;
        vector_info = sc.nextLine().split(" ");

        dinheiro = Double.parseDouble(vector_info[1]);

        vector_volumes = sc.nextLine().split(" ");

        vector_precos = sc.nextLine().split(" ");

        for (int i = 0; i < vector_precos.length; i++) {
            //cria o queijo e já calcula seu custo benefício
            Queijo queijo = new Queijo(Double.parseDouble(vector_precos[i]), Double.parseDouble(vector_volumes[i]), Double.parseDouble(vector_volumes[i]) / Double.parseDouble(vector_precos[i]));
            queijos.add(queijo);
        }

        Collections.sort(queijos);//ordena em ordem decrescente de custo benefício

        double qtd = escolheQueijos(queijos);
        int tam_base = (int) ((-1 + Math.sqrt(1 + 8 * qtd)) / 2);
        System.out.println(tam_base);
    }

    //busca sempre o queijo de maior custo benefício
    private static double escolheQueijos(ArrayList<Queijo> queijos) {
        double qtd = 0;
        while (dinheiro > 0 && queijos.size() > 0) {
            Queijo maior = queijos.remove(0);//remove o queijo de maior custo benefício do arranjo para que não seja verificado de novo
            if (maior.getPreco() > dinheiro) {//se o queijo de maior custo beneficio custar mais do que tenho
                qtd += (maior.getVolume() * dinheiro) / maior.getPreco();//regra de 3 para saber quanto queijo consigo comprar com o dinheiro disponível
                dinheiro = 0;
            } else {
                dinheiro -= maior.getPreco();
                qtd += maior.getVolume();
            }
        }
        return qtd;
    }

    private static class Queijo implements Comparable<Queijo> {

        private double preco;
        private double volume;
        private double custo_beneficio;

        public Queijo() {
        }

        public Queijo(double preco, double volume, double custo_beneficio) {
            this.preco = preco;
            this.volume = volume;
            this.custo_beneficio = custo_beneficio;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getCusto_beneficio() {
            return custo_beneficio;
        }

        public void setCusto_beneficio(double custo_beneficio) {
            this.custo_beneficio = custo_beneficio;
        }

        @Override
        public int compareTo(Queijo q) {
            if (this.custo_beneficio > q.custo_beneficio) {
                return -1;
            } else if (this.custo_beneficio < q.custo_beneficio) {
                return 1;
            }
            return 0;
        }

    }
}