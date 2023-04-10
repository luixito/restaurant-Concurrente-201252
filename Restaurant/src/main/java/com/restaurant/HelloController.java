package com.restaurant;

import com.restaurant.restaurante.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;


public class HelloController implements Observer {
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label satisfechos;
    @FXML
    private Label mesero;
    @FXML
    private Label chef;
    @FXML
    private Label recepcion;

    @FXML
    void Start() {
        satisfechos.setText("0");
        Restaurante restaurante = new Restaurante();
        restaurante.addObserver(this::update);
        Mesero waiter =new Mesero(anchor, restaurante);
        Recepcion recepcion =new Recepcion(restaurante);
        com.restaurant.restaurante.Chef chef = new Chef(restaurante);
        clientesCrear clientesCrear = new clientesCrear(anchor, restaurante, this);
        Thread hiloWaiter = new Thread(waiter);
        Thread hiloReceptionist = new Thread(recepcion);
        Thread hiloChef = new Thread(chef);
        Thread hiloCreateClient = new Thread(clientesCrear);

        hiloCreateClient.setDaemon(true);
        hiloCreateClient.start();

        hiloChef.setDaemon(true);
        hiloChef.start();

        hiloReceptionist.setDaemon(true);
        hiloReceptionist.start();

        hiloWaiter.setDaemon(true);
        hiloWaiter.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        synchronized (this) {
            if (((String)arg).contains("Descanso")){
                Platform.runLater(()-> mesero.setText("Descansando"));
            }else if (((String)arg).contains("mesereando")){
                Platform.runLater(()-> mesero.setText("Atendiendo mesas"));
            }else if (((String)arg).contains("Cocinando")){
                Platform.runLater(()-> chef.setText("Cocinando"));
            }else if(((String)arg).contains("libre")){
                Platform.runLater(()-> chef.setText("Descansando"));
            }else if(((String)arg).contains("mesa")) {
                String[] cadena = ((String) arg).split(" ");
                int libre = Integer.parseInt(cadena[1]);
                System.out.println("Mesa libre: "+libre);
                Platform.runLater(()-> recepcion.setText("Mesa Libre: " + libre));
            }
            else {
                int dato= Integer.parseInt((String)arg);
                Platform.runLater(()-> satisfechos.setText(dato+""));
                if(dato==100){
                    System.out.println("Todos los clientes atendidos!!!!");
                    Platform.runLater(()-> satisfechos.setText("Todos los clientes atendidos!!!!"));

                }
            }
        }
    }
}