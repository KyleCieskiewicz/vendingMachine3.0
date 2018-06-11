/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public class DaoStubImpl implements Dao {

    private Item onlyItem;
    private List<Item> itemList = new ArrayList<>();

    public DaoStubImpl() {
        onlyItem = new Item("1");
        onlyItem.setName("Doritos Ranch");
        onlyItem.setPrice(BigDecimal.valueOf(.95));
        onlyItem.setInventory(10);

        itemList.add(onlyItem);
    }

    @Override
    public Item addItem(String itemId, Item item) throws PersistenceException {
        if (itemId.equals(onlyItem.getItemId())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        return itemList;
    }

    @Override
    public Item getItem(String itemId) throws PersistenceException {
        if (itemId.equals(onlyItem.getItemId())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item removeItem(String itemId) throws PersistenceException {

        if (itemId.equals(onlyItem.getItemId())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void removeInventory(String itemId) throws PersistenceException {

        if(itemId.equals(onlyItem.getItemId())) {
            int itemInventory = onlyItem.getInventory();
        }
    }

}
