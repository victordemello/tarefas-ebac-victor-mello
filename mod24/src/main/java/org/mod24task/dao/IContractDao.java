package org.mod24task.dao;

import org.mod24task.Entities.Contract;

public interface IContractDao {
    void save(Contract contract);
    Contract findById(Long id);
    void deleteContract(Long id);
    Contract updateContract(Long id, Contract contract);
}
