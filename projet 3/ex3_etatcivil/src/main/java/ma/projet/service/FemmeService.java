package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractFacade<Femme> {

    public FemmeService() {
        super(Femme.class);
    }

    // Méthode avec requête native pour le nombre d'enfants d'une femme entre deux dates

    public int getNombreEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        try (Session session = getSession()) {
            NativeQuery<Long> query = session.createNativeQuery(
                    "SELECT COALESCE(SUM(nombreEnfants), 0) FROM mariage " +
                            "WHERE femme_id = :femmeId AND dateDebut BETWEEN :dateDebut AND :dateFin");
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        }
    }

    // Méthode avec requête nommée pour les femmes mariées au moins deux fois

    public List<Femme> getFemmesMarieesDeuxFois() {
        try (Session session = getSession()) {
            Query<Femme> query = session.createNamedQuery("Femme.marieesDeuxFois", Femme.class);
            return query.list();
        }
    }

    // Méthode avec Criteria API pour le nombre d'hommes mariés à quatre femmes entre deux dates

    public long getHommesMarieesQuatreFemmes(Date dateDebut, Date dateFin) {
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Mariage> root = cq.from(Mariage.class);

            // Sous-requête pour compter les mariages par homme

            Subquery<Long> subquery = cq.subquery(Long.class);
            Root<Mariage> subRoot = subquery.from(Mariage.class);
            subquery.select(cb.count(subRoot.get("id")))
                    .where(cb.between(subRoot.get("dateDebut"), dateDebut, dateFin))
                    .groupBy(subRoot.get("homme"));

            // Compter les hommes avec exactement 4 mariages

            cq.select(cb.countDistinct(root.get("homme")))
                    .where(cb.equal(
                            cb.literal(4L),
                            subquery
                    ));

            return session.createQuery(cq).uniqueResult();
        }
    }

    // Méthode pour trouver la femme la plus âgée

    public Femme getFemmeLaPlusAgee() {
        try (Session session = getSession()) {
            Query<Femme> query = session.createQuery(
                    "FROM Femme ORDER BY dateNaissance ASC", Femme.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }
}
