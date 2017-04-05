package com.epam.internal.daos;

import com.epam.internal.models.Account;
import com.epam.internal.models.Outcome;
import com.epam.internal.models.Outcome_;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class OutcomeDao extends GenericDao<Outcome> {

    public OutcomeDao() {
        super(Outcome.class);
    }

    public List<Outcome> getAllAccountsOutcomes(Account account) {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Outcome> query = criteriaBuilder.createQuery(Outcome.class);
        Root<Outcome> root = query.from(Outcome.class);

        query.where(criteriaBuilder.equal(root.get(Outcome_.account), account));
        List<Outcome> outcomes = session.createQuery(query).getResultList();
        session.close();
        return outcomes;
    }

    public List<Outcome> getIncomesInAccountByDate(Account account, Date date) {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Outcome> query = criteriaBuilder.createQuery(Outcome.class);
        Root<Outcome> incomeRoot = query.from(Outcome.class);

        Predicate equalAccount = criteriaBuilder.equal(incomeRoot.get(Outcome_.account), account);
        Predicate equalDate = criteriaBuilder.equal(incomeRoot.get(Outcome_.date), date);
        query.where(criteriaBuilder.and(equalAccount, equalDate));
        List<Outcome> outcomesByDate = session.createQuery(query).getResultList();
        session.close();
        return outcomesByDate;
    }
}
