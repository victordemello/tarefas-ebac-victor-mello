package org.mod24task.services;

import org.mod24task.Entities.Contract;
import org.mod24task.dao.IContractDao;
import org.mod24task.exceptions.EntityNotFoundException;

public class ContractService implements IContractService {
    private IContractDao contractDao;

    public ContractService(IContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public String saveContract(Contract contract) {
        // Salva o contrato e retorna uma mensagem de sucesso
        contractDao.save(contract);
        return "Save Contract success."; // Retorna a mensagem esperada
    }

    @Override
    public Contract findById(Long id) {
        // Tenta encontrar o contrato pelo ID
        Contract contract = contractDao.findById(id);
        if (contract == null) {
            return null; // Se não encontrar, retorna null
        }
        return contract; // Se encontrado, retorna o contrato
    }

    @Override
    public void deleteContract(Long id) {
        // Chama o DAO para deletar o contrato
        contractDao.deleteContract(id);
    }

    // Método adicional para atualizar o contrato
    @Override
    public Contract updateContract(Long id, Contract updatedContract) {
        // Tenta atualizar o contrato, se ele existir
        return contractDao.updateContract(id, updatedContract);
    }
}


