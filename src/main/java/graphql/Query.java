package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.RendezVous;
import entite.Logement;
import repository.RendezVousRepository;
import repository.LogementRepository;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final RendezVousRepository rendezVousRepository;
    private final LogementRepository logementRepository;

    public Query(RendezVousRepository rendezVousRepository, LogementRepository logementRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.logementRepository = logementRepository;
    }

    public List<RendezVous> getAllrendezVous() {
        return rendezVousRepository.getAllRendezVous();
    }

    public RendezVous getRendezVousById(int id) {
        return rendezVousRepository.getAllRendezVous()
                .stream()
                .filter(rdv -> rdv.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<RendezVous> getRendezVousByLogement(int reference) {
        return rendezVousRepository.findByLogementRef(reference);
    }
    public List<Logement> getAllLogements() {
        return logementRepository.getAllLogements();
    }

   public Logement getLogementByReference(int reference) {
        return logementRepository.getLogementsByReference(reference);
    }

    public List<Logement> getLogementsByType(String type) {
        Logement.TypeL typeL;
        try {
            typeL = Logement.TypeL.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return logementRepository.getLogementsByType(typeL);
    }
}
