package org.acme;

import io.quarkus.runtime.QuarkusApplication;

public class MyApp implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        return 0;
    }

    public String returnTitle(String title, int position){
        int pTemp = Math.max(position, 2);
        if(position > 5){
            pTemp = 5;
        }
        return "#".repeat(pTemp) + title;
    }

    public String returnTitle(){
        return returnTitle("carmageddon", 5);
    }

    /*
    Réaliser une fonction qui appelle la fonction returnTitle et anime le titre du jeu. Utiliser pour la
    version console la fonction flush et \r.
    La taille maximum du titre sera de 100 caractères
     */
    public void animeTitle(){
        String title = returnTitle();
        for(int i = 0; i < 100; i++){
            System.out.print(title.substring(0, i) + "\r");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
