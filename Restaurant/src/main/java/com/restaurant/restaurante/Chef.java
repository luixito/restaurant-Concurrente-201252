package com.restaurant.restaurante;

public class Chef implements Runnable{
    private Restaurante restaurante;
    public Chef(Restaurante restaurante){
        this.restaurante=restaurante;
    }
    @Override
    public void run() {
        while(true){
            restaurante.Cocina();
        }
    }
}

