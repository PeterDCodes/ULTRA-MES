package org.ultra;


import org.ultra.Domains.OrderStatusCodes.OrderStatusCodeHandler;
import org.ultra.Domains.Products.ProductHandler;
import org.ultra.Domains.Sites.SiteHandler;
import org.ultra.Domains.WorkOrders.WorkOrder;
import org.ultra.Domains.WorkOrders.WorkOrderHandler;
import org.ultra.Managers.WorkOrderManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        try{
            //Workflow 1 - Create a work order
            WorkOrder newOrder = WorkOrderManager.createWorkOrder();
            System.out.println(newOrder);

            //Workflow 2 - Complete that work order
            WorkOrderManager.closeWorkOrder(newOrder.getOrderId());
        }catch(Exception e){
            System.out.println(e.toString());
        }


    }
}