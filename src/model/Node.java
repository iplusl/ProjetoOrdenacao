package model;

import java.awt.*;

public class Node {
    //Declaracao das variaveis da classe
    private int altura;
    private Color cor;

    //Contrutor que inicializa a altura e define a cor padrao em vermelho;
    public Node(int altura){
        this.altura = altura;
        cor = Color.RED;
    }

    //Construtor que inicializa a altura e a cor
    public Node(int altura, Color cor){
        this.altura = altura;
        this.cor = cor;
    }

    //Getters e setters
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
