package com.epam.internal.dao;

import com.epam.internal.data.entities.Account;

public class AccountDao extends AbstractDao<Account> implements IGenericDao<Account> {

    AccountDao(Class<Account> persistentClass) {
        super(persistentClass);
    }

    @Override
    public Account findById(long id) {
        return findById(id);
    }

    @Override
    public void create(Account entity) {
        create(entity);
    }

    @Override
    public void delete(Account entity) {
        delete(entity);
    }

    @Override
    public void deleteById(long id) {
        deleteById(id);
    }
}
