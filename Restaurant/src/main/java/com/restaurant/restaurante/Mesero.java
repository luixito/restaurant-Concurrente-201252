package com.restaurant.restaurante;

import javafx.scene.layout.AnchorPane;

public class Mesero implements Runnable{
    private Restaurante restaurante;
    private AnchorPane padre;
    public Mesero(AnchorPane padre, Restaurante restaurante){
        this.restaurante = restaurante;
        this.padre = padre;
    }
    @Override
    public void run() {
        while (true){
            restaurante.ordenTomada();
        }
    }
}
