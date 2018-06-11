/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kylecieskiewicz
 */
public class DaoTest {

    private Dao dao = new DaoFileImpl();

    public DaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {

        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            dao.removeItem(item.getItemId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class Dao.
     */
    @Test
    public void testAddItem() throws Exception {
    }

    /**
     * Test of getAllItems method, of class Dao.
     */
    @Test
    public void testGetAllItems() throws Exception {

        Item item1 = new Item("1");
        item1.setName("Joe");
        item1.setPrice(BigDecimal.valueOf(.95));
        item1.setInventory(10);

        dao.addItem(item1.getItemId(), item1);

        Item item2 = new Item("2");
        item2.setName("John");
        item2.setPrice(BigDecimal.valueOf(1.05));
        item2.setInventory(15);

        dao.addItem(item2.getItemId(), item2);

        assertEquals(2, dao.getAllItems().size());

    }

    /**
     * Test of getItem method, of class Dao.
     */
    @Test
    public void testGetItem() throws Exception {

        Item item = new Item("1");
        item.setName("Joe");
        item.setPrice(BigDecimal.valueOf(.95));
        item.setInventory(10);

        dao.addItem(item.getItemId(), item);

        Item fromDao = dao.getItem(item.getItemId());

        assertEquals(item, fromDao);
    }

    /**
     * Test of removeItem method, of class Dao.
     */
    @Test
    public void testRemoveItem() throws Exception {
        
        Item item1 = new Item("1");
        item1.setName("Joe");
        item1.setPrice(BigDecimal.valueOf(.95));
        item1.setInventory(10);

        dao.addItem(item1.getItemId(), item1);

        Item item2 = new Item("2");
        item2.setName("John");
        item2.setPrice(BigDecimal.valueOf(1.05));
        item2.setInventory(15);

        dao.addItem(item2.getItemId(), item2);

        dao.removeItem(item1.getItemId());
        assertEquals(1, dao.getAllItems().size());
        assertNull(dao.getItem(item1.getItemId()));

        dao.removeItem(item2.getItemId());
        assertEquals(0, dao.getAllItems().size());
        assertNull(dao.getItem(item2.getItemId()));
    }

    /**
     * Test of removeInventory method, of class Dao.
     */
    @Test
    public void testRemoveInventory() throws Exception {
        
        Item item1 = new Item("1");
        item1.setName("Joe");
        item1.setPrice(BigDecimal.valueOf(.95));
        item1.setInventory(10);

        dao.addItem(item1.getItemId(), item1);
        dao.removeInventory(item1.getItemId());
        int test = item1.getInventory();
        
        
        Item item2 = new Item("2");
        item2.setName("Joe");
        item2.setPrice(BigDecimal.valueOf(.95));
        item2.setInventory(test);
        
        assertSame(item1.getInventory(), item2.getInventory());
    }

}
