package org.acme.service;

import org.acme.model.Combattant;

import java.util.HashMap;
import java.util.Map;

public class CombattantService {
    private Map<Integer, Combattant> combattants;

    public CombattantService() {
        combattants = new HashMap<>();
        // Initialisation des combattants dans la carte
        combattants.put(1, new Combattant("Combattant1", 100, 10, 1, 1, 0, 0));
        combattants.put(2, new Combattant("Combattant2", 100, 10, 1, 1, 10, 0));
    }

    public void moveRight(int combattantId) {
        Combattant combattant = combattants.get(combattantId);
        if (combattant != null) {
            combattant.moveForward();
            // Vous pouvez également implémenter des vérifications pour éviter que le combattant ne sorte de la carte
        }
    }

    public void moveLeft(int combattantId) {
        Combattant combattant = combattants.get(combattantId);
        if (combattant != null) {
            combattant.moveBackward();
            // Vous pouvez également implémenter des vérifications pour éviter que le combattant ne sorte de la carte
        }
    }

    public void attack(int attackerId, int targetId) {
        Combattant attacker = combattants.get(attackerId);
        Combattant target = combattants.get(targetId);
        if (attacker != null && target != null) {
            attacker.attack(target);
        }
    }

    // Vous pouvez ajouter d'autres méthodes pour d'autres fonctionnalités liées aux combattants
}
