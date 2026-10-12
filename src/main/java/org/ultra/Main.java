package org.ultra;


import org.ultra.Domains.OrderStatusCodes.OrderStatusCodeHandler;
import org.ultra.Domains.Products.ProductHandler;
import org.ultra.Domains.Sites.SiteHandler;
import org.ultra.Domains.WorkOrders.WorkOrder;
import org.ultra.Managers.WorkOrderManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        //Workflow 1 - Create a work order
        try{
            WorkOrder newOrder = WorkOrderManager.createWorkOrder();
            System.out.println(newOrder);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}