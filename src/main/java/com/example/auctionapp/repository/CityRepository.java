package com.example.auctionapp.repository;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.City;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CityRepository extends BaseRepository<City> {

    public CityRepository(Class<City> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
    }

    public City findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<City> q = cb.createQuery(City.class);
        Root<City> resource = q.from(City.class);
        q.select(resource);
        Predicate predicateForName = cb.equal(resource.get("name"), name);
        q.where(predicateForName);

        City result = entityManager.createQuery(q).getSingleResult();

        return result;
    }
}
