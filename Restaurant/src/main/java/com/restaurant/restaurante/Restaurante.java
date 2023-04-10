package com.restaurant.restaurante;

import java.util.Observable;

public class Restaurante extends Observable {
    public boolean client;
    public int numClient;
    public int order;
    public int comida;
    public int ordenes;
    public int count=0;
    public boolean confirm;
    public int maxnumClient;
    public boolean[] tables;

    public Restaurante(){
        tables = new boolean[26];

        for (int i=0; i<16; i++) {
            tables[i] = false;
        }
    }


    public int entradaClientes(){
        int numMesa =0;
        try {
            synchronized (this) {
                numClient++;
                maxnumClient++;
                while (maxnumClient==16) {
                    wait();
                }
                client=true;
                for (int i=0; i<16; i++) {
                    if(!tables[i]) {
                        numMesa = i;
                        tables[i] = true;
                        i=100;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers("mesa " + numMesa);
        return numMesa;
    }
    public void ordenar(){
        synchronized (this) {
            order++;
            notifyAll();
        }
    }

    public void ordenTomada(){
        boolean aux = false;
        synchronized (this) {
            if (order<=0){
                notifyObservers("Descanso");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                aux = true;
                notifyObservers("mesereando");
                ordenes++;
                order--;
            }
            setChanged();
            notifyAll();
        }
        if (aux){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Cocina() {
        boolean comanda = false;
        synchronized (this) {
            if (ordenes > 0) {
                comida++;
                ordenes--;
                comanda = true;
            }
            if (comanda) {
                setChanged();
                notifyObservers("Cocinando");
            }else {
                notifyObservers("libre");
            }
            notifyAll();

        }
    }



    public synchronized void Comensales() throws InterruptedException {
        while (comida <= 0) {
            wait();
        }
        comida--;
        Thread.sleep(1500);
    }

    public void salir(int numMesaLibre){
        synchronized (this) {
            if(!confirm){
                confirm=true;
            }else{
                numClient--;
                maxnumClient--;
                client=false;
                System.out.println("Cliente sale");
            }
            tables[numMesaLibre] = false;
            notifyAll();
            count++;
            setChanged();
            notifyObservers(""+count);
        }
    }

    public void recepcion(){
        synchronized (this) {
            while(numClient < 1 || client){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }


}
