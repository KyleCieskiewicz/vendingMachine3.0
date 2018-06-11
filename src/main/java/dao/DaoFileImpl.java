/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kylecieskiewicz
 */
public class DaoFileImpl implements Dao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    @Override
    public Item addItem(String itemId, Item item)
            //        Item newItem = items.put(itemId, item);
            //        return newItem;
            throws PersistenceException {
        Item newItem = items.put(itemId, item);
        write();
        return newItem;
    }

    @Override
    public List<Item> getAllItems()
            throws PersistenceException {
        load();

        return new ArrayList<Item>(items.values());

    }

    @Override
    public Item getItem(String itemId)
            throws PersistenceException {
        load();
        return items.get(itemId);
    }

    @Override
    public Item removeItem(String itemId)
            throws PersistenceException {
        Item removedItem = items.remove(itemId);
        write();
        return removedItem;
    }

    @Override
    public void removeInventory(String itemId)
            throws PersistenceException {

        Item itemBought = items.get(itemId);

        if ((itemBought.getInventory()) == 0) {
            itemBought.setInventory(0);
            write();
        } else {

            int itemInventory = itemBought.getInventory();
            if (itemInventory > 1) {
                itemInventory--;
                int updatedInventory = itemInventory;
                itemBought.setInventory(updatedInventory);
//        System.out.println(itemBought.getInventory());
                write();
            } else if (itemInventory == 1) {
                itemBought.setInventory(0);
                write();
            } else {
                itemBought.setInventory(0);
                write();
            }

        }

    }

    private void load() throws PersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);

            currentItem.setName(currentTokens[1]);
            currentItem.setPrice(new BigDecimal(currentTokens[2]));
            currentItem.setInventory(Integer.parseInt(currentTokens[3]));

            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private void write() throws PersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }

        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getInventory());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
