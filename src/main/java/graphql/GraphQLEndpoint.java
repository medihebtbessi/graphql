package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.LogementRepository;
import repository.RendezVousRepository;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        
        LogementRepository logementRepo = new LogementRepository();
        RendezVousRepository rdvRepo = new RendezVousRepository(logementRepo);


        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(rdvRepo,logementRepo)
                        ,new Mutation(logementRepo, rdvRepo)
                )
                .build()
                .makeExecutableSchema();
    }
}
