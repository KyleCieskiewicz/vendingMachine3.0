/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;
import service.DataValidationException;
import service.DuplicateIdException;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.ServiceLayer;
import ui.View;

/**
 *
 * @author kylecieskiewicz
 */
public class Controller {

    private View view;
    private ServiceLayer service;

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                listItems();
//                enterChange();
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
//                        listItems();
                        enterChange();
                        break;
                    case 2:
                        createItem();
                        break;
                    case 3:
                        removeItem();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    public void enterChange() throws PersistenceException, NoItemInventoryException, DataValidationException, DuplicateIdException {
        double userChange = 0;
        double userChangeTotal = 0;
        String userMenuSelectionInput;
        BigDecimal userMenuSelectionPrice, theUserChange;
        BigDecimal correctAmount = new BigDecimal("0");
        Change controllerChange;

        do {

            boolean keepGoing = true;
            while (keepGoing) {

                userChange = printAndGetChangeFromView();

                if (userChange == 1) {
                    keepGoing = false;
                } else if (!(userChange == .05 || userChange == .1 || userChange == .25)) {
                    view.displayIncorrectAmount();
                } else {

                    userChangeTotal += userChange;
                    view.displayAmountEntered(userChangeTotal);
                }
            }

            theUserChange = service.setUserChange(userChangeTotal);
            userMenuSelectionInput = view.getItemChoice();
            
            
            try {
            userMenuSelectionPrice = service.setUserSelection(userMenuSelectionInput);
            } catch (NoItemInventoryException ex) {
                view.displayErrorMessage(ex.getMessage());
            }

            try {
                controllerChange = service.checkEnoughMoney();


                view.displayChange(controllerChange);
//                break;
                theUserChange = correctAmount;

            } catch (InsufficientFundsException ex) {
                view.displayErrorMessage(ex.getMessage());

            }

        } while (!(theUserChange.equals(correctAmount)));

//        run();

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private double printAndGetChangeFromView() {
        return view.printAndGetChange();
    }


    private void createItem() throws PersistenceException {
        view.displayCreateItemBanner();
        boolean hasErrors = false;
        do {
            Item currentItem = view.getNewItemInfo();
            
            try {
            service.createItem(currentItem);
            view.displayCreateSuccessBanner();
            hasErrors = false;
            } catch (DuplicateIdException | DataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listItems() throws PersistenceException, DuplicateIdException, DataValidationException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem() throws PersistenceException, DuplicateIdException, DataValidationException {
        view.displayDisplayItemBanner();
        String itemId = view.getItemIdChoice();
        Item item = service.getItem(itemId);
        view.displayItem(item);
    }

    private void removeItem() throws PersistenceException, DuplicateIdException, DataValidationException {
        view.displayRemoveItemBanner();
        String itemId = view.getItemIdChoice();
        service.removeItem(itemId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
