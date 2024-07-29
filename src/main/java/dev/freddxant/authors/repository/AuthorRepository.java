package dev.freddxant.authors.repository;

import dev.freddxant.authors.entity.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {
    @Inject
    SessionFactory sessionFactory;

    public List<Author> findAllByCriteria(String search, int page, int size) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Author> authorList = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);

            List<Predicate> predicateList = new ArrayList<>();

            if (search != null && !search.isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
            }

            criteriaQuery.where(predicateList.toArray(new Predicate[0]));

            Query<Author> query = session.createQuery(criteriaQuery);
            query.setFirstResult(page * size);
            query.setMaxResults(size);

            authorList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return authorList;
    }

    public long countAllByCriteria(String search) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        long count = 0;

        try {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Author> root = criteriaQuery.from(Author.class);

            List<Predicate> predicateList = new ArrayList<>();

            if (search != null && !search.isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
            }

            criteriaQuery.select(criteriaBuilder.count(root)).where(predicateList.toArray(predicateList.toArray(new Predicate[0])));

            count = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return count;
    }
}
