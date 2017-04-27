package serviceREST;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modele.Utilisateur;
import outils.UtilisateurDTO;
import outils.ListeUtilisateurDTO;
import ressources.FournisseurDePersistance;

@Path("listedto")
@ManagedBean("listeur")
public class ServiceREST {
    
    ListeUtilisateurDTO listedto; 
        
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ListeUtilisateurDTO getDTO() {
        listedto = new ListeUtilisateurDTO();
        peuplementListe();
        return listedto;        
    }
    
    private void peuplementListe() {
    
        EntityManager em = null;
    
        try {
                em = FournisseurDePersistance.getInstance().fournir();
                em.getTransaction().begin();
                // implémenter la requête native JPA avec le code SQL approprié afin de restaurer tous les utilisateurs dans une liste
                // todo
                Query requete = em.createNativeQuery("SELECT * FROM UTILISATEUR", Utilisateur.class);
                List<Utilisateur> resultat = requete.getResultList();
                // implémenter l'itération qui permet à partir des objets Utilisateur restaurés ci-dessus de remplir la liste des objets UtilisateurDTO de la classe ListeUtilisateurDTO
                // todo
                for(Utilisateur user : resultat){
                    listedto.getListedto().add(new outils.UtilisateurDTO(user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(), user.getRole().getRole()));
                }
                
                //liste.setListedto(listedto);
                em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        finally {
            try {
                    em.close();
                } catch (Exception e) {e.printStackTrace();
            }            
        }
    }
}
