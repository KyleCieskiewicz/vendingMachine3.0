/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.Controller;
import dao.AuditDao;
import dao.Dao;
import dao.PersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kylecieskiewicz
 */
public class ServiceLayerImpl implements ServiceLayer {

    private Dao dao;
    private AuditDao auditDao;

    BigDecimal userChange = BigDecimal.ZERO;
    BigDecimal userMenuSelectionPrice = BigDecimal.ZERO;
    private String userChoiceId;
    Change controllerChange;

    public ServiceLayerImpl(Dao dao, AuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;

    }

    @Override
    public void createItem(Item item) throws DataValidationException, DuplicateIdException, PersistenceException {

        if (dao.getItem(item.getItemId()) != null) {
            throw new DuplicateIdException(
                    "ERROR: Could not create tiem.  Item Id "
                    + item.getItemId()
                    + " already exists");
        }

        validateItemData(item);

        dao.addItem(item.getItemId(), item);

//        auditDao.writeAuditEntry(
//                "Item " + item.getItemId() + " CREATED.");

    }

    @Override
    public List<Item> getAllItems() throws DataValidationException, DuplicateIdException, PersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemId) throws DataValidationException, DuplicateIdException, PersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public Item removeItem(String itemId) throws DataValidationException, DuplicateIdException, PersistenceException {
        Item removedItem = dao.removeItem(itemId);
//        auditDao.writeAuditEntry("Item " + itemId + " REMOVED.");
        return removedItem;
    }

    private void validateItemData(Item item) throws
            DataValidationException {
        if (item.getItemId() == null
                || item.getItemId().trim().length() == 0
                || item.getName() == null
                || item.getName().trim().length() == 0
                || item.getPrice() == null //            || item.getInventory() == null
                ) {

            throw new DataValidationException(
                    "ERROR: All fields [ItemId, Name, Price] are required.");
        }

    }

    @Override
    public BigDecimal setUserChange(Double userChangeTotal) {
        userChange = new BigDecimal(userChangeTotal);

        return userChange;
    }

    @Override
    public BigDecimal setUserSelection(String userMenuSelectionInput) throws DataValidationException, DuplicateIdException, NoItemInventoryException, PersistenceException {

        int itemInventory;
        userChoiceId = userMenuSelectionInput;
        Item item = getItem(userMenuSelectionInput);

        itemInventory = item.getInventory();

        if (itemInventory <= 0) {
            userMenuSelectionPrice = BigDecimal.ZERO;
            throw new NoItemInventoryException("=== ITEM OUT OF STOCK CHOOSE ANOTHER ITEM ===");
        } else {

            userMenuSelectionPrice = item.getPrice();
        }

        return userMenuSelectionPrice;

    }

    @Override
    public void vendItem() throws PersistenceException {

        dao.removeInventory(userChoiceId);

    }

    @Override
    public Change checkEnoughMoney() throws InsufficientFundsException, PersistenceException {

        BigDecimal hundreds, conversionPlaceHolder;
        BigDecimal correctAmount = new BigDecimal("0");

        if (userChange.compareTo(userMenuSelectionPrice) < 0) {

            BigDecimal userChangeOwed = userMenuSelectionPrice.subtract(userChange);
            userChangeOwed = userChangeOwed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

            throw new InsufficientFundsException("You owe $" + userChangeOwed + " to purchase the item you selected.");

        }

        userChange = userChange.subtract(userMenuSelectionPrice);
        userChange = userChange.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        hundreds = new BigDecimal("100");
        conversionPlaceHolder = userChange.multiply(hundreds);
        int outOfDecimal = conversionPlaceHolder.intValue();

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (outOfDecimal >= 25) {
            quarters = quarters + 1;
            outOfDecimal = outOfDecimal - 25;
        }
        while (outOfDecimal >= 10) {
            dimes = dimes + 1;
            outOfDecimal = outOfDecimal - 10;
        }
        while (outOfDecimal >= 5) {
            nickels = nickels + 1;
            outOfDecimal = outOfDecimal - 5;
        }

        Change theChange = new Change();

        theChange.setQuarters(quarters);
        theChange.setDimes(dimes);
        theChange.setNickels(nickels);

        //                view.displayChange(theChange);
        vendItem();

        controllerChange = theChange;
        userChange = new BigDecimal("0");

        return theChange;
    }

    @Override
    public Change returnUserChange() {

        return controllerChange;

    }

}
