package controller;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import model.*;

public class Controller {

    private Random random = new Random();
    private Node[] numeros = new Node[100];
    private Node[] original = new Node[100];
    private LinkedList todosPassos = new LinkedList();

    public void Controller(){

    }

    //Metodo que retorna uma lista encadeada com todas as etapas feitas no metodo de ordenacao
    //Essa lista que sera utilizada para exibir todas as etapas na interface
    public LinkedList getTodosPassos(){
        //bubbleSort();
        //selectionSort();
        //insertionSort();
        /*
        mergeSort(numeros, 0, numeros.length-1);
        Node[] finall = copiarObjeto(numeros);
        finall = deixaVerde(finall);
        todosPassos.addFirst(finall);
        */
        /*
        quickSort(numeros, 0, numeros.length-1);
        Node[] finall = copiarObjeto(numeros);
        finall = deixaVerde(finall);
        todosPassos.addFirst(finall);
        */
        /*
        heapSort(numeros);
        Node[] finall = copiarObjeto(numeros);
        finall = deixaVerde(finall);
        todosPassos.addFirst(finall);
        */
        return todosPassos;
    }

    public Node[] getNumeros(){
        return numeros;
    }

    //Inicializa o vetor com 100 numeros aleatorios
    public void sorteiaNum(){
        for(int i = 0; i < 100; i++){
            numeros[i] = new Node(random.nextInt(100)+1);
        }
        original = numeros;
    }

    private void bubbleSort(){
        todosPassos.addFirst(numeros.clone());
        for(int i = 0; i < numeros.length-1; i++){
            for(int j = 0; j < numeros.length-i-1; j++){
                Node[] aux1 = copiarObjeto(numeros);
                aux1[j].setCor(Color.BLUE);
                aux1[j+1].setCor(Color.BLUE);
                todosPassos.addFirst(aux1.clone());
                if(numeros[j].getAltura() > numeros[j+1].getAltura()){
                    swap(j, j+1);
                    Node[] aux2 = copiarObjeto(numeros);
                    aux2[j].setCor(Color.BLUE);
                    aux2[j+1].setCor(Color.BLUE);
                    todosPassos.addFirst(aux2.clone());
                }
            }
            Node[] aux1 = copiarObjeto(numeros);
            aux1[aux1.length-i-1].setCor(Color.GREEN);
            numeros[numeros.length-i-1].setCor(Color.GREEN);
            todosPassos.addFirst(aux1);
        }
        numeros[0].setCor(Color.GREEN);
        todosPassos.addFirst(numeros);
    }

    private void selectionSort(){
        todosPassos.addFirst(numeros.clone());
        int min, aux;
        for (int i = 0; i < (numeros.length-1); i++)
        {
            min = i;
            for (int j = (i+1); j < numeros.length; j++) {
                Node[] aux1 = copiarObjeto(numeros);
                aux1[min].setCor(Color.BLUE);
                aux1[j].setCor(Color.BLUE);
                todosPassos.addFirst(aux1.clone());
                if(numeros[j].getAltura() < numeros[min].getAltura()) {
                    min = j;
                }
            }
            if (numeros[i].getAltura() != numeros[min].getAltura()) {
                swap(i, min);
                Node[] aux1 = copiarObjeto(numeros);
                aux1[min].setCor(Color.BLUE);
                aux1[i].setCor(Color.BLUE);
                todosPassos.addFirst(aux1.clone());
            }
            Node[] aux1 = copiarObjeto(numeros);
            aux1[i].setCor(Color.GREEN);
            numeros[i].setCor(Color.GREEN);
            todosPassos.addFirst(aux1.clone());
        }
        numeros[numeros.length-1].setCor(Color.GREEN);
        todosPassos.addFirst(numeros.clone());
    }

    public void insertionSort(){
        Node[] aux = copiarObjeto(numeros);
        todosPassos.addFirst(aux);
        for (int i = 0; i<numeros.length; i++) {
            for (int j = i; j > 0; j--) {
                Node[] aux1 = copiarObjeto(numeros);
                aux1[j].setCor(Color.BLUE);
                aux1[j-1].setCor(Color.BLUE);
                numeros[j].setCor(Color.CYAN);
                numeros[j-1].setCor(Color.CYAN);
                todosPassos.addFirst(aux1.clone());
                if (numeros[j].getAltura() < numeros[j-1].getAltura()) {
                    swap(j , j-1);
                    Node[] aux2 = copiarObjeto(numeros);
                    aux2[j].setCor(Color.BLUE);
                    aux2[j-1].setCor(Color.BLUE);
                    todosPassos.addFirst(aux2.clone());
                }else{
                    break;
                }
            }
        }
        todosPassos.addFirst(numeros.clone());
        Node[] aux2 = copiarObjeto(numeros);
        aux2 = deixaVerde(aux2);
        todosPassos.addFirst(aux2);
    }

    public void mergeSort(Node[] vetor, int l, int r){
        if (l < r) {

            int m = l + (r - l) / 2;

            mergeSort(vetor, l, m);
            mergeSort(vetor, m + 1, r);

            merge(vetor, l, m, r);
        }
    }

    private void merge(Node[] vetor, int start, int mid, int end){
        int start2 = mid + 1;
        if (vetor[mid].getAltura() <= vetor[start2].getAltura()) {
            return;
        }

        while (start <= mid && start2 <= end) {

            if (vetor[start].getAltura() <= vetor[start2].getAltura()) {
                start++;
            }
            else {
                Node value = vetor[start2];
                int index = start2;

                while (index != start) {
                    vetor[index] = vetor[index - 1];
                    Node[] aux1 = copiarObjeto(numeros);
                    aux1[index].setCor(Color.BLUE);
                    aux1[index-1].setCor(Color.BLUE);
                    todosPassos.addFirst(aux1.clone());
                    index--;
                }
                vetor[start] = value;

                start++;
                mid++;
                start2++;
            }
        }
    }

    public void quickSort(Node[] arr, int low, int high){
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition (Node[] arr, int low, int high){
        Node[] aux = copiarObjeto(numeros);

        int pivot = arr[high].getAltura();
        aux[pivot-1].setCor(Color.ORANGE);
        todosPassos.addFirst(aux.clone());
        int i = (low - 1);

        for (int j = low; j <= high- 1; j++)
        {
            if (arr[j].getAltura() <= pivot)
            {
                i++;
                swap(i, j);
                Node[] aux1 = copiarObjeto(aux);
                aux1[i].setCor(Color.BLUE);
                aux1[j].setCor(Color.BLUE);
                todosPassos.addFirst(aux1.clone());
            }
        }
        swap(i+1, high);
        Node[] aux1 = copiarObjeto(aux);
        aux1[i+1].setCor(Color.BLUE);
        aux1[high].setCor(Color.BLUE);
        todosPassos.addFirst(aux1.clone());
        return (i+1);
    }

    public void heapSort(Node[] arr){
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            Node[] aux = copiarObjeto(numeros);
            aux[i].setCor(Color.BLUE);
            aux[0].setCor(Color.BLUE);
            todosPassos.addFirst(aux.clone());
            Node temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            Node[] aux1 = copiarObjeto(numeros);
            aux1[i].setCor(Color.CYAN);
            aux1[0].setCor(Color.CYAN);
            todosPassos.addFirst(aux.clone());

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private void heapify(Node[] arr, int n, int i){
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l].getAltura() > arr[largest].getAltura())
            largest = l;

        if (r < n && arr[r].getAltura() > arr[largest].getAltura())
            largest = r;

        if (largest != i) {
            Node[] aux = copiarObjeto(numeros);
            aux[i].setCor(Color.BLUE);
            aux[largest].setCor(Color.BLUE);
            todosPassos.addFirst(aux.clone());
            Node swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            Node[] aux1 = copiarObjeto(numeros);
            aux1[i].setCor(Color.CYAN);
            aux1[largest].setCor(Color.CYAN);
            todosPassos.addFirst(aux1.clone());

            heapify(arr, n, largest);
        }
    }

    //Metodo que copia um vetor de Nodes em um novo ponteiro
    private Node[] copiarObjeto(Node[] velho){
        Node[] novo = new Node[velho.length];
        for(int i = 0; i < velho.length; i++){
            novo[i] = new Node(velho[i].getAltura(), velho[i].getCor());
        }
        return novo;
    }

    private Node[] copiarPedaco(Node[] original, int de, int ate){
        Node[] novo = new Node[ate-de];
        for(int i = 0; i < novo.length; i++){
            novo[i] = new Node(original[de].getAltura());
            de++;
        }
        return novo;
    }

    //Metodo que troca duas posisoes no vetor
    private void swap(int i, int j){
        Node aux = numeros[i];
        numeros[i] = numeros[j];
        numeros[j] = aux;
    }

    private Node[] deixaVerde(Node[] numbers){
        for(int i = 0; i < numbers.length; i++){
            numbers[i].setCor(Color.GREEN);
        }
        return  numbers;
    }
}
