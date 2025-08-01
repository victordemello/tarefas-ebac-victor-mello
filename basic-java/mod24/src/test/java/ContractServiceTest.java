import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mod24task.Entities.Contract;
import org.mod24task.dao.ContractDao;
import org.mod24task.dao.IContractDao;
import org.mod24task.mock.ContractDaoMock;
import org.mod24task.services.ContractService;
import org.mod24task.services.IContractService;

import java.util.Optional;

/**
 * @author victor.mello
 */
public class ContractServiceTest {

    private IContractDao contractDAOMock;
    private IContractService contractService;

    @Before
    public void setUp() {
        contractDAOMock = new ContractDaoMock();
        contractService = new ContractService(contractDAOMock);
    }

    @Test
    public void shouldSaveContractSuccessfully() {
        Contract newContract = new Contract();
        String result = contractService.saveContract(newContract);
        Assert.assertEquals("Save Contract success.", result);
    }

    @Test
    public void shouldDeleteContractSuccessfully() {
        Long contractId = 1L;

        // 1. First, find the contract (it should exist before deletion)
        Contract contractBeforeDelete = contractDAOMock.findById(contractId);
        Assert.assertNotNull("Contract should exist before deletion", contractBeforeDelete);

        // 2. Delete the contract
        contractDAOMock.deleteContract(contractId);

        // 3. After deletion, try to find the contract (it should return null)
        Contract contractAfterDelete = contractDAOMock.findById(contractId);
        Assert.assertNull("Contract should be null after deletion", contractAfterDelete);
    }

    @Test
    public void shouldFindContractByIdSuccessfully() {
        Long contractId = 1L;
        Contract contract = contractService.findById(contractId);

        Assert.assertNotNull("Contract should not be null", contract);
        Assert.assertEquals("Contract ID is not as expected", Long.valueOf(1L), contract.getId());
    }

    @Test
    public void shouldUpdateContractSuccessfully() {
        Long contractId = 1L;

        // 1. First, find the existing contract before updating
        Contract contractBeforeUpdate = contractService.findById(contractId);
        Assert.assertNotNull("Contract should exist before update", contractBeforeUpdate);

        // 2. Modify the contract name to simulate an update
        contractBeforeUpdate.setName("Updated Contract Name");

        // 3. Update the contract using the service
        Contract updatedContract = contractService.updateContract(contractId, contractBeforeUpdate);

        // 4. Verify that the contract was updated correctly
        Assert.assertNotNull("Updated contract should not be null", updatedContract);
        Assert.assertEquals("Contract name was not updated correctly", "Updated Contract Name", updatedContract.getName());

        // 5. Verify that the contract ID remains the same
        Assert.assertEquals("Contract ID should be the same after update", contractId, updatedContract.getId());
    }
}

