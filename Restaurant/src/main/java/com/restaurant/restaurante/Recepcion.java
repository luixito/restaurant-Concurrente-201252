package com.restaurant.restaurante;

public class Recepcion implements Runnable{
    private Restaurante restaurante;
    public Recepcion(Restaurante restaurante){
        this.restaurante= restaurante;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restaurante.recepcion();

        }
    }
}
