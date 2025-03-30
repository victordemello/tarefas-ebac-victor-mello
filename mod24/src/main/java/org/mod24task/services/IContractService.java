package org.mod24task.services;

import org.mod24task.Entities.Contract;
import org.mod24task.dao.IContractDao;
import org.mod24task.exceptions.EntityNotFoundException;

public interface IContractService {
    String saveContract(Contract contract);
    Contract findById(Long id);
    Contract updateContract(Long id, Contract contract);
    void deleteContract(Long id);

}
