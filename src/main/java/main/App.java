/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import dao.AuditDao;
import dao.AuditDaoFileImpl;
import dao.Dao;
import dao.DaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.ServiceLayer;
import service.ServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.View;

/**
 *
 * @author kylecieskiewicz
 */
public class App {

    public static void main(String[] args) throws InsufficientFundsException, NoItemInventoryException {
//        UserIO myIo = new UserIOConsoleImpl();
//        View myView = new View(myIo);
//        Dao myDao = new DaoFileImpl();
//        AuditDao myAuditDao = new AuditDaoFileImpl();
//        ServiceLayer myService = new ServiceLayerImpl(myDao, myAuditDao);
//        Controller controller = new Controller(myService, myView);
//        controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller
                = ctx.getBean("controller", Controller.class);
        controller.run();
    }

}
