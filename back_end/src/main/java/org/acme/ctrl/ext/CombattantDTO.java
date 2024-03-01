package org.acme.ctrl.ext;

public class CombattantDTO {

    private String marque;
    private String modele;
    private int vitesseMax;
    private int carburant = 60;

    public CombattantDTO(String marque, String modele, int vitesseMax, int carburant) {
        this.marque = marque;
        this.modele = modele;
        this.vitesseMax = vitesseMax;
        this.carburant = carburant;
    }
}
