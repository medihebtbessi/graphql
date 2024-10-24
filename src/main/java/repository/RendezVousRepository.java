package repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import entite.Logement;
import entite.RendezVous;

public class RendezVousRepository {
	private final List<RendezVous> listeRendezVous;
	 private final   LogementRepository logementRepository;


	public RendezVousRepository(LogementRepository logementRepository) {
		this.logementRepository = logementRepository;
		listeRendezVous = new ArrayList<>();
		listeRendezVous.add(new RendezVous(1, "31-10-2015", "15:30", logementRepository.getLogementsByReference(4), "55214078"));
		listeRendezVous.add(new RendezVous(2, "20-12-2015", "9:00", logementRepository.getLogementsByReference(1), "21300811"));
		listeRendezVous.add(new RendezVous(3, "17-09-2015", "9:15", logementRepository.getLogementsByReference(4), "98102102"));
	}



	public List<RendezVous> getAllRendezVous() {
		return this.listeRendezVous;
	}

	public boolean addRendezVous(RendezVous rendezVous) {
		int refLogement = rendezVous.getLogement().getReference();
		Logement logement = logementRepository.getLogementsByReference(refLogement);
		if (logement != null) {
			rendezVous.setLogement(logement);
			return listeRendezVous.add(rendezVous);
		}
		return false;
	}

	public List<RendezVous> findByLogementRef(int reference) {
		List<RendezVous> liste = new ArrayList<>();
		for (RendezVous r : listeRendezVous) {
			if (r.getLogement().getReference() == reference) {
				liste.add(r);
			}
		}
		return liste;
	}

	public boolean updateRendezVous(RendezVous rendezVous) {
		int index = findRendezVousIndexById(rendezVous.getId());
		if (index != -1) {
			Logement logement = logementRepository.getLogementsByReference(rendezVous.getLogement().getReference());
			if (logement != null) {
				rendezVous.setLogement(logement);
				listeRendezVous.set(index, rendezVous);
				return true;
			}
		}
		return false;
	}

	public Logement getLogementByRDV(int idRDV) {
		for (RendezVous r : listeRendezVous) {
			if (r.getId() == idRDV) {
				return r.getLogement();
			}
		}
		return null;
	}

	public boolean deleteRendezVous(int id) {
		Iterator<RendezVous> iterator = listeRendezVous.iterator();
		while (iterator.hasNext()) {
			RendezVous r = iterator.next();
			if (r.getId() == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	private int findRendezVousIndexById(int id) {
		for (int i = 0; i < listeRendezVous.size(); i++) {
			if (listeRendezVous.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
