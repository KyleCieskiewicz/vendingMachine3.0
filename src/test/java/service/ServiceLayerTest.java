/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AuditDao;
import dao.AuditDaoStubImpl;
import dao.Dao;
import dao.DaoStubImpl;
import dao.PersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecieskiewicz
 */
public class ServiceLayerTest {

    private ServiceLayer service;

    public ServiceLayerTest() throws DuplicateIdException, DataValidationException, PersistenceException {

//        Dao dao = new DaoStubImpl();
//        AuditDao auditDao = new AuditDaoStubImpl();
//
//        service = new ServiceLayerImpl(dao, auditDao);
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", ServiceLayer.class);
        
//         Item item = new Item("2");
//        item.setName("Zero");
//        item.setPrice(BigDecimal.valueOf(1.20));
//        item.setInventory(14);
//        service.createItem(item);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createItem method, of class ServiceLayer.
     */
    @Test
    public void testCreateItem() throws Exception {

        Item item = new Item("2");
        item.setName("Zero");
        item.setPrice(BigDecimal.valueOf(1.20));
        item.setInventory(14);
        service.createItem(item);
    }

    @Test
    public void testCreateItemDuplicateId() throws Exception {
        Item item = new Item("1");
        item.setName("Zero");
        item.setPrice(BigDecimal.valueOf(1.20));
        item.setInventory(14);

        try {
            service.createItem(item);
            fail("Expected DuplicateIdException was not thrown.");
        } catch (DuplicateIdException e) {
            return;
        }

    }

    @Test
    public void testCreateItemInvalidData() throws Exception {
        Item item = new Item("1");
        item.setName("");
        item.setPrice(BigDecimal.valueOf(1.20));
        item.setInventory(14);

        try {
            service.createItem(item);
            fail("Expected DuplicateIdException was not thrown.");
        } catch (DuplicateIdException e) {
            return;
        }

    }

    /**
     * Test of getAllItems method, of class ServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {

        assertEquals(1, service.getAllItems().size());

    }

    /**
     * Test of getItem method, of class ServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {

        Item item = service.getItem("1");
        assertNotNull(item);
        item = service.getItem("9");
        assertNull(item);
    }

    /**
     * Test of removeItem method, of class ServiceLayer.
     */
    @Test
    public void testRemoveItem() throws Exception {

        Item item = service.removeItem("1");
        assertNotNull(item);
        item = service.removeItem("9");
        assertNull(item);
    }

    /**
     * Test of setUserChange method, of class ServiceLayer.
     */
    @Test
    public void testSetUserChange() {
        
        BigDecimal userChange = new BigDecimal(".50");
        
        assertEquals(new BigDecimal(".50"), userChange);
    }

    /**
     * Test of setUserSelection method, of class ServiceLayer.
     */
    @Test
    public void testSetUserSelection() throws Exception {
        
        Item item = service.getItem("1");
        
        assertNotNull(item.getItemId());
    }

    /**
     * Test of vendItem method, of class ServiceLayer.
     */
//    @Test
//    public void testVendItem() throws Exception {
//        
//        
//    }

    /**
     * Test of checkEnoughMoney method, of class ServiceLayer.
     */
//    @Test
//    public void testCheckEnoughMoney() throws Exception {
//    }

    /**
     * Test of returnUserChange method, of class ServiceLayer.
     */
    @Test
    public void testReturnUserChange() {
        
        assertNull(service.returnUserChange());
    }

}
