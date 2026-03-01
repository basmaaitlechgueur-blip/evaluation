package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.beans.Femme;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HommeService extends AbstractFacade<Homme> {

    public HommeService() {
        super(Homme.class);
    }

    // Méthode pour afficher les épouses d'un homme entre deux dates

    public List<Femme> getEpousesEntreDates(int hommeId, Date dateDebut, Date dateFin) {
        try (Session session = getSession()) {
            Query<Mariage> query = session.createQuery(
                    "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId " +
                            "AND m.dateDebut BETWEEN :dateDebut AND :dateFin", Mariage.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Mariage> mariages = query.list();
            List<Femme> epouses = new ArrayList<>();
            for (Mariage m : mariages) {
                epouses.add(m.getFemme());
            }
            return epouses;
        }
    }

    // Méthode pour afficher les détails des mariages d'un homme

    public void afficherMariagesDetails(int hommeId) {
        Homme homme = findById(hommeId);
        if (homme == null) {
            System.out.println("Homme non trouvé");
            return;
        }

        System.out.println("\nNom : " + homme.getNom() + " " + homme.getPrenom());

        // Séparer mariages en cours et échoués

        List<Mariage> mariagesEnCours = new ArrayList<>();
        List<Mariage> mariagesEchoues = new ArrayList<>();

        for (Mariage m : homme.getMariages()) {
            if (m.estEnCours()) {
                mariagesEnCours.add(m);
            } else {
                mariagesEchoues.add(m);
            }
        }

        // Afficher mariages en cours

        System.out.println("Mariages En Cours :");
        int index = 1;
        for (Mariage m : mariagesEnCours) {
            System.out.println(index++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    " Date Début : " + m.getDateDebut() +
                    " Nbr Enfants : " + m.getNombreEnfants());
        }

        // Afficher mariages échoués

        System.out.println("Mariages échoués :");
        index = 1;
        for (Mariage m : mariagesEchoues) {
            System.out.println(index++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    " Date Début : " + m.getDateDebut() +
                    " Nbr Enfants : " + m.getNombreEnfants());
        }
    }
}
