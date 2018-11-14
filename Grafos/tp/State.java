/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp;

import static java.lang.Double.doubleToLongBits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author amanda
 */
public class State {

    public double id;
    public int d;
    public int[] empty_position;
    public String[][] matrix;

    public State() {

    }

    public State(State state) {
        this.empty_position = new int[]{state.empty_position[0], state.empty_position[1]};
        this.matrix = createMatrix(state.getMatrix());
        this.d = state.d + 1;
    }

    public State(int d, String[][] matriz, int[] posicao_vazia) {
        this.d = d;
        this.matrix = createMatrix(matriz);
        this.empty_position = new int[]{posicao_vazia[0], posicao_vazia[1]};
        this.id = generateId();
    }
    
    public State(String[][] matrix){
        this.matrix = matrix;
        this.id = generateId();
    }

    public State(int d, int ordem) {
        this.d = d;
        this.matrix = new String[ordem][ordem];
    }

    @Override
    public boolean equals(Object other) {
        if ((other == null) || !(other instanceof State)) {
            return false;
        }
        return ((State) other).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        long bits = doubleToLongBits(id);
        int hash = (int) (bits ^ (bits >>> 32));
        //System.out.println("hash: " + hash);
        return hash;
    }

    public String toString() {
        return "Hashcode: " + this.hashCode();
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int[] getEmpty_position() {
        return empty_position;
    }

    public void setEmpty_position(int[] empty_position) {
        this.empty_position = empty_position;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public double generateId() {
        String sid = "";
        for (int i = 0; i < this.matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                if (matrix[i][j].equals("0")) {
                    sid += ".";
                } else {
                    sid += matrix[i][j];
                }
            }
        }
        return new BigDecimal(sid).doubleValue();

    }

    private String[][] createMatrix(String[][] matriz) {
        String[][] copia = new String[matriz[0].length][matriz[1].length];
        for (int i = 0; i < copia[0].length; i++) {
            for (int j = 0; j < copia[1].length; j++) {
                copia[i][j] = matriz[i][j];
            }
        }
        return copia;
    }

    public boolean isFinal() {
        String s = "";
        for (int i = 0; i < this.matrix[0].length; i++) {
            for (int j = 0; j < this.matrix[1].length; j++) {
                s += matrix[i][j] + ",";
            }
        }

        if (s.equals(TP.final_state)) {
            return true;
        }

        return false;
    }

}
