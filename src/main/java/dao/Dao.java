/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public interface Dao {

    Item addItem(String itemId, Item item)
            throws PersistenceException;

    List<Item> getAllItems()
            throws PersistenceException;

    Item getItem(String itemId)
            throws PersistenceException;

    Item removeItem(String itemtId)
            throws PersistenceException;

    public void removeInventory(String itemId)
            throws PersistenceException;


}
