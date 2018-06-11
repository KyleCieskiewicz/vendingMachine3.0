/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public class View {

//    UserIO io = new UserIOConsoleImpl();
    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public Item getNewItemInfo() {
        String iD = io.readString("Please enter ID");
        String name = io.readString("Please enter Name");
        BigDecimal price = io.readBigDecimal("Please enter Price");
        int inventory = io.readInt("Please enter Inventory");
        Item currentItem = new Item(iD);
        currentItem.setName(name);
        currentItem.setPrice(price);
        currentItem.setInventory(inventory);
        return currentItem;
    }

    public int printMenuAndGetSelection() {
//        io.print("Press 1 to Enter Change");
//        io.print("1. List");
//        io.print("2. Create");
//        io.print("3. View");
//        io.print("4. Remove");
//        io.print("5. Exit");

        return io.readInt("Press 1 to insert money or 4 to exit.", 1, 4);
    }
    
    public double printAndGetChange() {
        io.print("");
        return io.readDouble("Enter $.05, $.10 or $.25 for change and then press enter or enter 1 to select item.");
    }

    public void displayCreateItemBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemId() + ": "
                    +  currentItem.getName() + ": $"
                    +  currentItem.getPrice() + ": "
                    + currentItem.getInventory() + " ");
        }
        io.readString("Press enter.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Items ===");
    }

    public void displayDisplayItemBanner() {
        io.print("=== Display Item ===");
    }

    public String getItemIdChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayItem(Item item) {
        if (item != null) {
            io.print(item.getItemId());
            io.print(item.getName());
            io.print(item.getPrice().toString());
            io.print(Integer.toString(item.getInventory()));
            io.print("");
        } else {
            io.print("No such item.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public String getItemChoice() {
        
        io.print("");
        return io.readString("Please enter the number for the item you would like.");
    }

    public void displayRemoveItemBanner() {
        io.print("=== Remove Item ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Item successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayOutOfStockMessage(){
        io.print("=== ITEM OUT OF STOCK CHOOSE ANOTHER ITEM ===");
    }
    
    public void displayIncorrectAmount() {
        io.print("");
        io.print("=== That is not a Quarter, Dime or Nickel. ===");
        io.print("");
    }
    
    public void displayAmountEntered(double amount) {
        io.print("");
        io.print("$" + amount);
        io.print("");
    }
    
    public void amountOwed(BigDecimal amountOwed) {
//        io.print(amountOwed);
        io.print("");
        System.out.println("You owe $" + amountOwed + " to purchase the item you selected.");
        io.print("");
    }
    
    public void displayChange(Change change){
        io.print("");
        io.print("Here's your change Quarters " + change.getQuarters() + ", Dimes " + change.getDimes() + " and Nickels " + change.getNickels() + ".");
        io.print("");
        
    }
    
    


}
