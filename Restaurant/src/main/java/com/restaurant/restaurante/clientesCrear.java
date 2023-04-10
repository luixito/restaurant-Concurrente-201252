package com.restaurant.restaurante;

import com.restaurant.HelloController;
import javafx.scene.layout.AnchorPane;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class clientesCrear extends Random implements Runnable{
    private AnchorPane anchor;
    private Restaurante restaurant;
    private HelloController controller;
    public clientesCrear(AnchorPane anchor, Restaurante restaurante, HelloController controller){
        this.anchor = anchor;
        this.restaurant =restaurante;
        this.controller = controller;
    }
    private Cliente cliente;
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            cliente =new Cliente(anchor,restaurant);
            Thread Hcliente = new Thread(cliente);
            Hcliente.setName("Cliente "+(i+1));
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Hcliente.setDaemon(true);
            Hcliente.start();
        }
    }

}
