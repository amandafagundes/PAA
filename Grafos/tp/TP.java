/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amanda
 */
public class TP {

    private static int order;
    private static State current_state;
    private static LinkedHashSet<State> generated_states = new LinkedHashSet<State>();
    private static LinkedHashSet<State> visited_states = new LinkedHashSet<State>();
    private static boolean isFinal = false, finished = false;
    public static String final_state = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File input_file = new File(args[0]);
        File output_file = new File(args[1]);
        PrintWriter writer = new PrintWriter(output_file, "UTF-8");
        String input_string = "", output_string = "";
        BufferedReader br = new BufferedReader(new FileReader(input_file));
        String st;
        while ((st = br.readLine()) != null) {
            input_string += st;
        }

        String[] input_vector = input_string.split(",");
        order = (int) Math.sqrt(input_vector.length);
        String[][] matrix = new String[order][order];
        int[] empty_position = new int[2];

        //verifica se a instância é solucionável
        if (isSolvable(input_vector)) {
            //cria o estado final para verificação
            createFinalState(input_vector);

            //cria a matriz do estado inicial e a posição vazia
            int k = 0;
            for (int i = 0; i < order; i++) {
                for (int j = 0; j < order; j++) {
                    if (input_vector[k].equals("0")) {
                        empty_position[0] = i;
                        empty_position[1] = j;
                    }
                    matrix[i][j] = input_vector[k];
                    k++;
                }
            }
            //adiciona o novo estado ao conjunto de estados gerados a partir da matriz e posição vazia econtrados
            generated_states.add(new State(0, matrix, empty_position));

            Iterator<State> iterator = generated_states.iterator();//cria um iterator a partir do conjunto de estados

            while (iterator.hasNext() && !isFinal) {//o loop continua até que não exista mais estados a serem gerados ou até encontrar o estado final
                current_state = iterator.next();
                generated_states.remove(current_state);//remove o estado atual da fila
                if (current_state != null && !visited_states.contains(current_state)) {//verifica se não é nulo e se ainda não foi visitado
                    createStates();//expande os estados
                    visited_states.add(current_state);//adiciona o estado corrente ao conjunto de estados visitados
                }
                iterator = generated_states.iterator();//"recria" o iterator
            }
            output_string = "custo_total: " + current_state.d;
        } else {
            output_string = "Problema não solucionável";
        }
        System.out.println(output_string);//escreve no arquivo
        writer.println(output_string);
        writer.close();
    }

    /***
     * cria oes estados a partir da movimentação das peças para cima, baixo, esquerda direita
     * */
    private static void createStates() {

        try {
            generated_states.add(up());
            generated_states.add(down());
            generated_states.add(left());
            generated_states.add(right());
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private static State up() throws CloneNotSupportedException, NullPointerException {
        if (current_state.getEmpty_position()[0] == 0) {
            return null;
        }

        State new_state = new State(current_state);//cria um novo estado baseado nas propriedades do estado atual
        int[] pos_vazia = new_state.getEmpty_position();
        String aux = new_state.getMatrix()[pos_vazia[0] - 1][pos_vazia[1]];//pega o elemento armazenado na célula que será a nova posição vazia
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1]] = aux;//coloca o elemento armazenado em aux na posição que está atualmente vazia
        new_state.getMatrix()[new_state.getEmpty_position()[0] - 1][new_state.getEmpty_position()[1]] = "0";//coloca 0 na nova posição vazia
        new_state.setId(new_state.generateId());
        pos_vazia[0] -= 1;
        new_state.empty_position = pos_vazia;

        if (new_state.isFinal() && !finished) {//verifica se o estado criado é final
            isFinal = true;//se for o loop do main será interrompido
            current_state = new_state;
        }
        return new_state;
    }

    private static State down() throws CloneNotSupportedException, NullPointerException {
        if (current_state.getEmpty_position()[0] == current_state.getMatrix()[0].length - 1) {
            return null;
        }

        State new_state = new State(current_state);
        int[] pos_vazia = new_state.getEmpty_position();
        String aux = new_state.getMatrix()[pos_vazia[0] + 1][pos_vazia[1]];
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1]] = aux;
        new_state.getMatrix()[new_state.getEmpty_position()[0] + 1][new_state.getEmpty_position()[1]] = "0";
        new_state.setId(new_state.generateId());
        pos_vazia[0] += 1;
        new_state.empty_position = pos_vazia;

        if (new_state.isFinal() && !finished) {
            isFinal = true;
            current_state = new_state;
        }
        return new_state;
    }

    private static State left() throws CloneNotSupportedException, NullPointerException {
        if (current_state.getEmpty_position()[1] == 0) {
            return null;
        }

        State new_state = new State(current_state);
        int[] pos_vazia = new_state.getEmpty_position();
        String aux = new_state.getMatrix()[pos_vazia[0]][pos_vazia[1] - 1];
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1]] = aux;
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1] - 1] = "0";
        new_state.setId(new_state.generateId());
        pos_vazia[1] -= 1;
        new_state.empty_position = pos_vazia;

        if (new_state.isFinal() && !finished) {
            isFinal = true;
            current_state = new_state;
        }
        return new_state;
    }

    private static State right() throws CloneNotSupportedException, NullPointerException {
        if (current_state.getEmpty_position()[1] == current_state.getMatrix()[0].length - 1) {
            return null;
        }
        State new_state = new State(current_state);
        int[] pos_vazia = new_state.getEmpty_position();
        String aux = new_state.getMatrix()[pos_vazia[0]][pos_vazia[1] + 1];
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1]] = aux;
        new_state.getMatrix()[new_state.getEmpty_position()[0]][new_state.getEmpty_position()[1] + 1] = "0";
        new_state.setId(new_state.generateId());
        pos_vazia[1] += 1;
        new_state.empty_position = pos_vazia;

        if (new_state.isFinal() && !finished) {
            isFinal = true;
            current_state = new_state;
        }
        return new_state;
    }

    private static boolean isSolvable(String[] input) {
        int inversions = 0;
        int empty_line = -1;
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (Integer.parseInt(input[i]) != 0 && Integer.parseInt(input[j]) != 0) {
                    if (Integer.parseInt(input[i]) > Integer.parseInt(input[j])) {
                        inversions++;
                    }
                }
                if (empty_line == -1) {
                    if (input[i].equals("0")) {
                        empty_line = order - (int) (i / order);
                    } else if (input[j].equals("0")) {
                        empty_line = order - (int) (j / order);
                    }
                }
            }
        }

        if (order % 2 == 0) {
            if (empty_line % 2 == 0 && inversions % 2 == 1) {
                return true;
            }
            if (empty_line % 2 == 1 && inversions % 2 == 0) {
                return true;
            }
        } else {
            if (inversions % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    //cria o estado final a partir da ordenação de um array criado a partir do vetor de strings
    private static void createFinalState(String[] vetor) {
        ArrayList<Integer> array = new ArrayList<>();
        for (String s : vetor) {
            array.add(Integer.parseInt(s));
        }
        Collections.sort(array);
        for (int i : array) {
            final_state += i + ",";
        }
    }

}
