package peuplement;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modele.Action;
import modele.Role;
import modele.Utilisateur;

public class PeupleurInitial {

	public PeupleurInitial() {
		super();
	}

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("peuplement");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Utilisateur defaultadmin = new Utilisateur("default-admin@depot-vente.fr", "admin", "default-admin", "default-admin", new Date(), "default-admin");
				
		Action action1 = new Action("rédiger", "rédiger une annonce", new Date(), "default-admin");
		Action action2 = new Action("modifier", "modifier une annonce", new Date(), "default-admin");
		Action action3 = new Action("supprimer", "supprimer une annonce", new Date(), "default-admin");
		Action action4 = new Action("publier", "rédiger une annonce", new Date(), "default-admin");
		Action action5 = new Action("vendre", "rédiger une annonce", new Date(), "default-admin");
		Action action6 = new Action("gérer magasins", "gérer les magasins faisant partie du réseau dépôt-vente", new Date(), "default-admin");
		Action action7 = new Action("gérer accès", "gérer les accès et leur authentification", new Date(), "default-admin");
		Action action8 = new Action("consulter", "consulter les indicateurs statistiques", new Date(), "default-admin");
			
		Role roleannonceur = new Role("annonceur", "vendeur particulier ou magasin", new Date(), "default-admin");
		Role rolemagasin = new Role("magasin", "magasin faisant partie du réseau dépôt-vente", new Date(), "default-admin");
		Role roleadmin = new Role("administrateur", "administrateur du système d'information", new Date(), "default-admin");
		
		roleannonceur.ajoutAction(action1);
		roleannonceur.ajoutAction(action2);
		roleannonceur.ajoutAction(action3);
		rolemagasin.ajoutAction(action4);
		rolemagasin.ajoutAction(action5);
		roleadmin.ajoutAction(action6);
		roleadmin.ajoutAction(action7);
		roleadmin.ajoutAction(action8);
		
		roleadmin.ajoutUtilisateur(defaultadmin);
		
		em.persist(roleannonceur);
		em.persist(rolemagasin);
		em.persist(roleadmin);

		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
