package controller;

import java.util.Random;

public class Controller {

    Random random = new Random();
    int[] numeros = new int[100];
    int[] original = new int[100];

    public void Controller(){

    }

    public int[] getNumeros(){
        return numeros;
    }

    public void sorteiaNum(){
        for(int i = 0; i < 100; i++){
            numeros[i] = random.nextInt(100)+1;
        }
        original = numeros;
    }

    public void bubbleSort(){
        for(int i = 0; i < numeros.length-1; i++){
            for(int j = 0; j < numeros.length-i-1; j++){
                if(numeros[j] > numeros[j+1]){
                    swap(j, j+1);
                    return;
                }
            }
        }
    }

    private void swap(int i, int j){
        int aux = numeros[i];
        numeros[i] = numeros[j];
        numeros[j] = aux;
    }

}
