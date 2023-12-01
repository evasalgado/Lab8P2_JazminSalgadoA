/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8p2_jazminsalgadoa;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 *
 * @author evaja
 */
public class AdministrarInicioSesion extends Thread {

    private ArrayList<jugador> listJ = new ArrayList<>();
    private File f = null;
    private JProgressBar buscar;
    private boolean avanzar;
    private boolean vive;

    public AdministrarInicioSesion(String path) {
        f = new File(path);
    }

    public AdministrarInicioSesion(JProgressBar buscar, boolean avanzar, boolean vive) {
        this.buscar = buscar;
        avanzar = true;
        vive = true;
    }

    public boolean isAvanzar() {
        return avanzar;
    }

    public void setAvanzar(boolean avanzar) {
        this.avanzar = avanzar;
    }

    public boolean isVive() {
        return vive;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public ArrayList<jugador> getListJ() {
        return listJ;
    }

    public void setListJ(ArrayList<jugador> listJ) {
        this.listJ = listJ;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public JProgressBar getBuscar() {
        return buscar;
    }

    public void setBuscar(JProgressBar buscar) {
        this.buscar = buscar;
    }

    public void setJugador(jugador j) {
        this.listJ.add(j);
    }

    @Override
    public String toString() {
        return "listJ=" + listJ;
    }

    @Override
    public void run() {
        while (vive) {
            if (avanzar) {
                buscar.setValue(buscar.getValue() + 1);
                if (buscar.getValue() == 10000) {
                    vive = false;
                }
            }
            try {
                Thread.sleep(0);
            } catch (Exception e) {
            }
        }
    }

    public void cargar() {
        try {
            listJ = new ArrayList<>();
            jugador temp;
            if (f.exists()) {
                FileInputStream entrada
                        = new FileInputStream(f);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
                    while ((temp = (jugador) objeto.readObject()) != null) {
                        listJ.add(temp);
                    }
                } catch (EOFException e) {
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribir() {
        FileOutputStream fw = null;
        ObjectOutputStream ow = null;
        try {
            fw = new FileOutputStream(f);
            ow = new ObjectOutputStream(fw);
            for (jugador o : listJ) {
                ow.writeObject(o);
            }
        } catch (Exception e) {
        } finally {
            try {
                ow.close();
                fw.close();
            } catch (Exception e) {
            }
        }
    }
}
