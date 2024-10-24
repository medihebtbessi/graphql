package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

public class Mutation implements GraphQLMutationResolver {

    private final LogementRepository logementRepository;
    private final RendezVousRepository rendezVousRepository;

    public Mutation(LogementRepository logementRepository, RendezVousRepository rendezVousRepository) {
        this.logementRepository = logementRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    public Logement createLogement(int reference, String adresse, float prix) {
        Logement logement = new Logement(reference, adresse, null, null, null, null, prix);
        logementRepository.saveLogement(logement);
        return logement;
    }

    public RendezVous createRendezVous(int id, String date, String heure, String numTel, int refLog) {
        RendezVous rendezVous = new RendezVous(id, date, heure, logementRepository.getLogementsByReference(refLog), numTel);
        rendezVousRepository.addRendezVous(rendezVous);
        return rendezVous;
    }
}
