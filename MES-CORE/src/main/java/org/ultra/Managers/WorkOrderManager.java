package org.ultra.Managers;

import org.ultra.Domains.Products.ProductUtils;
import org.ultra.Domains.WorkOrders.WorkOrder;
import org.ultra.Domains.WorkOrders.WorkOrderHandler;

import java.sql.SQLException;
import java.util.Scanner;

public class WorkOrderManager {

    //Creates a new work order
    public static WorkOrder createWorkOrder() throws SQLException {

        //Placeholder vars
        int product;
        int quantity;
        final int statusCode = 1;


        //Prompt user for a product type
        while(true){
            product = promptProduct();
            if(ProductUtils.isValidProduct(product)){
                break;
            }else{
                System.out.println("Invalid Product ID");
            };
        }

        //Prompt user for a quantity
        quantity = promptQuantity();
        //Set a status code

        try{
            return WorkOrderHandler.makeWorkOrder(product, quantity, statusCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };


    public static WorkOrder closeWorkOrder(int orderId) {
        return WorkOrderHandler.changeOrderStatus(orderId, 3);
    }

    public static WorkOrder startWorkOrder(int orderId) {
        return WorkOrderHandler.changeOrderStatus(orderId, 2);
    }

    //Deletes a work order




    private static int promptProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        return scanner.nextInt();
    }

    private static int promptQuantity(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter qty: ");
        return scanner.nextInt();
    }

}
