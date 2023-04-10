package com.restaurant.restaurante;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cliente implements Runnable{
    private AnchorPane anchor;
    private Restaurante restaurante;
    private static String[] positions;
    public Cliente(AnchorPane anchor, Restaurante restaurante){
        this.anchor = anchor;
        this.restaurante = restaurante;
        positions = new String[16];
        positions[0] = "155 120";
        positions[1] = "250 120";
        positions[2] = "342 120";
        positions[3] = "442 120";

        positions[4] = "155 200";
        positions[5] = "250 200";
        positions[6] = "342 200";
        positions[7] = "442 200";

        positions[8] = "155 280";
        positions[9] = "250 280";
        positions[10] = "342 280";
        positions[11] = "442 280";

        positions[12] = "155 360";
        positions[13] = "250 360";
        positions[14] = "342 360";
        positions[15] = "442 360";
    }
    @Override
    public void run() {
        Circle client = new Circle(15, Color.rgb(231, 0, 22));


        Platform.runLater(() -> {
            client.setLayoutX(730);
            client.setLayoutY(480);
            anchor.getChildren().add(client);
        });

        int numMesa = restaurante.entradaClientes();
        String[] layout = positions[numMesa].split(" ");

        Platform.runLater(()-> {
            client.setLayoutX(Integer.parseInt(layout[0]));
            System.out.println(layout[0]+" "+layout[1]);
            client.setLayoutY(Integer.parseInt(layout[1]));
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restaurante.ordenar();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            restaurante.Comensales();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        restaurante.salir(numMesa);

    }
}
