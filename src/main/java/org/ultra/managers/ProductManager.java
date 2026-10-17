package org.ultra.managers;

import org.ultra.domains.Products.Product;
import org.ultra.domains.Products.ProductHandler;
import org.ultra.domains.MachineProducts.MachineProduct;
import org.ultra.domains.MachineProducts.MachineProductHandler;
import org.ultra.domains.MachineTypes.MachineTypeHandler;

import java.util.Scanner;

public class ProductManager {
    //Creates a new product
    public static Product createProduct() {
        Scanner scanner = new Scanner(System.in);
        String skuNumber = promptSkuNumber(scanner);
        String productName = promptProductName(scanner);
        return ProductHandler.makeProduct(skuNumber, productName);
    }

    //Associates a machine type with a product
    public static MachineProduct associateMachineTypeToProduct() {
        Scanner scanner = new Scanner(System.in);
        int productId;
        int machineTypeId;

        while (true) {
            productId = promptProductId(scanner);
            if (ProductHandler.getProductById(productId) != null) {
                break;
            } else {
                System.out.println("Invalid Product ID");
            }
        }

        while (true) {
            machineTypeId = promptMachineTypeId(scanner);
            if (MachineTypeHandler.getMachineTypeById(machineTypeId) != null) {
                break;
            } else {
                System.out.println("Invalid Machine Type ID");
            }
        }

        return MachineProductHandler.makeMachineProduct(machineTypeId, productId);
    }

    private static String promptSkuNumber(Scanner scanner) {
        System.out.print("Enter SKU number: ");
        return scanner.nextLine();
    }

    private static String promptProductName(Scanner scanner) {
        System.out.print("Enter product name: ");
        return scanner.nextLine();
    }

    private static int promptProductId(Scanner scanner) {
        System.out.print("Enter product id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int promptMachineTypeId(Scanner scanner) {
        System.out.print("Enter machine type id: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
