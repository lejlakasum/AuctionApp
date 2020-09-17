package com.example.auctionapp.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BaseRepository<T>
        extends AbstractJpaDao<T> implements IBaseRepository<T>
{
}
