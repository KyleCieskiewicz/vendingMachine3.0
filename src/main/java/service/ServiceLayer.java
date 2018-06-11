/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public interface ServiceLayer {

    void createItem(Item item) throws
            DuplicateIdException,
            DataValidationException,
            PersistenceException;

    List<Item> getAllItems() throws
            DuplicateIdException,
            DataValidationException,
            PersistenceException;

    Item getItem(String itemId) throws
            DuplicateIdException,
            DataValidationException,
            PersistenceException;

    Item removeItem(String itemId) throws
            DuplicateIdException,
            DataValidationException,
            PersistenceException;

    public BigDecimal setUserChange(Double userChangeTotal);

    public BigDecimal setUserSelection(String userMenuSelectionInput) throws DataValidationException, DuplicateIdException,
            NoItemInventoryException, PersistenceException;

    public void vendItem() throws
            PersistenceException;

    public Change checkEnoughMoney() throws
            InsufficientFundsException, PersistenceException;

    public Change returnUserChange();

}
