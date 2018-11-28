package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Ordenador extends Canvas implements Runnable {

    private boolean isRunning;
    private Thread thread;

    public static JFrame frame;
    private final int width = 800;
    private final int height = 600;
    private BufferedImage image;

    private Node[] alturas;
    private LinkedList todosPassos;
    private Controller controller;

    //construtor que inicializará as variaveis utilizadas para a interface e sorteia os numeros para o grafico
    public Ordenador(){
        setPreferredSize(new Dimension(width, height));
        startFrame();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        controller = new Controller();
        controller.sorteiaNum();
    }

    public void startFrame(){
        frame = new JFrame("Ordenador");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Ordenador ordenador = new Ordenador();
        ordenador.start();
    }

    //metodo proprio da thread para inicializar a execucao da thread
    public synchronized void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    ////metodo proprio da thread para encerrar a execucao da thread
    public synchronized  void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //este metodo sera chamado dentro do loop da thread
    public void tick(){
        //Atualiza as informações do grafico

        if(todosPassos == null){
            todosPassos = controller.getTodosPassos();
        }else if (!todosPassos.isEmpty()){
            alturas = (Node[])todosPassos.pollLast();
        }
    }


    //este metodo sera chamado dentro do loop da thread e renderiza as coisas na tela
    public void render(){
        //Renderiza o grafico na tela
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);



        double escala = 5.8;
        int j = 0;
        for(double i = 10; i < width-11; i += 7.8){
            g.setColor(alturas[j].getCor());
            g.fillRect((int) i, (int) (height-(10+(alturas[j].getAltura()*escala))), (int) 6.5, (int)(alturas[j].getAltura()*escala));
            j++;
        }

        g.setColor(Color.BLACK);
        g.drawLine(10, height-10, width-10, height-10);
        g.drawLine(10, height-11, width-10, height-11);
        g.drawLine(10, height-12, width-10, height-12);

        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        bs.show();
    }

    //metodo proprio da thread para ficar no looping infinito
    public void run() {
        while(isRunning){
            tick();
            render();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stop();
    }
}
