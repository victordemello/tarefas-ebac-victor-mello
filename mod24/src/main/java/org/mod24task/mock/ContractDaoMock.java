package org.mod24task.mock;

import org.mod24task.Entities.Contract;
import org.mod24task.dao.IContractDao;

import java.util.HashMap;
import java.util.Map;

public class ContractDaoMock implements IContractDao {
    private final Map<Long, Contract> contractDatabase;

    // Constructor that initializes a simulated "database"
    public ContractDaoMock() {
        contractDatabase = new HashMap<>();

        // Simulating an already existing contract
        Contract contract = new Contract();
        contract.setId(1L);
        contract.setName("Test Contract");

        contractDatabase.put(contract.getId(), contract);
    }

    @Override
    public void save(Contract contract) {
        // Simulates saving to the "database"
        contractDatabase.put(contract.getId(), contract);
    }

    @Override
    public Contract findById(Long id) {
        // Simulates a database search
        return contractDatabase.get(id); // Returns null if the contract doesn't exist
    }

    @Override
    public void deleteContract(Long id) {
        // Simulates the deletion of a contract in the database
        if (contractDatabase.containsKey(id)) {
            contractDatabase.remove(id);
            System.out.println("Contract with ID " + id + " has been deleted.");
        } else {
            System.out.println("Contract with ID " + id + " not found for deletion.");
        }
    }

    @Override
    public Contract updateContract(Long id, Contract updatedContract) {
        // Simulates the process of updating a contract
        if (contractDatabase.containsKey(id)) {
            contractDatabase.put(id, updatedContract);
            return updatedContract;
        }
        return null;
    }
}

