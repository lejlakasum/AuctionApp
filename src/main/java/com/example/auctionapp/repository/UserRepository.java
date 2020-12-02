package com.example.auctionapp.repository;

import com.example.auctionapp.model.Bid;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


public class UserRepository extends BaseRepository<UserAccount> {

    public UserRepository(Class<UserAccount> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
    }

    public UserAccount findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserAccount> q = cb.createQuery(UserAccount.class);
        Root<UserAccount> resource = q.from(UserAccount.class);
        q.select(resource);
        Predicate predicateForEmail = cb.equal(resource.get("userRegisterInformation").get("email"), email);
        q.where(predicateForEmail);

        UserAccount result = entityManager.createQuery(q).getSingleResult();

        return result;
    }

    public List<Bid> getBidsByUser(Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bid> q = cb.createQuery(Bid.class);
        Root<Bid> resource = q.from(Bid.class);
        q.select(resource);

        Predicate predicateForUserId = cb.equal(resource.get("userAccount").get("id"), userId);
        q.where(predicateForUserId);

        List<Bid> result = entityManager.createQuery(q).getResultList();

        return result;
    }

    public List<Product> getProductsByUser(Long userId, Boolean active) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForUserId = cb.equal(resource.get("userAccount").get("id"), userId);
        Predicate predicateForEndDate = active ? cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now(ZoneOffset.UTC))
                                               : cb.lessThan(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now(ZoneOffset.UTC));

        q.where(cb.and(predicateForUserId, predicateForEndDate));

        List<Product> result = entityManager.createQuery(q).getResultList();

        return result;
    }
}
