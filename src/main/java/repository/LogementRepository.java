package repository;

import java.util.ArrayList;
import java.util.List;
import entite.Logement;
import entite.Logement.TypeL;

public class LogementRepository {

	private final List<Logement> logements;

	public LogementRepository() {
		logements = new ArrayList<>();
		logements.add(new Logement(1, "27, Rue des roses", "El Ghazela", "Ariana", TypeL.Studio, "cuisine équipée", 300f));
		logements.add(new Logement(5, "58, Rue des roses", "El Ghazela", "Ariana", TypeL.EtageVilla, "cuisine équipée", 450f));
		logements.add(new Logement(2, "201, Résidence Omrane4", "Riadh El Andalous", "Ariana", TypeL.Appartement, "chauffage central, ascenseur, climatisation", 700f));
		logements.add(new Logement(3, "540, Résidence Les Tulipes", "El Aouina", "Ariana", TypeL.Appartement, "S+2, chauffage central, ascenseur, climatisation", 500f));
		logements.add(new Logement(4, "78, Rue des Oranges", "Bardo", "Tunis", TypeL.EtageVilla, "chauffage central, ascenseur, climatisation", 400f));
	}

	public List<Logement> getAllLogements() {
		return logements;
	}

	public List<Logement> getLogementsByType(Logement.TypeL type) {
		List<Logement> liste = new ArrayList<>();
		for (Logement l : logements) {
			if (l.getTypeL().equals(type)) {
				liste.add(l);
			}
		}
		return liste;
	}

	public void saveLogement(Logement logement) {
		logements.add(logement);
	}

	public Logement getLogementsByReference(int reference) {
		for (Logement l : logements) {
			if (l.getReference() == reference) {
				return l;
			}
		}
		return null;
	}
}
