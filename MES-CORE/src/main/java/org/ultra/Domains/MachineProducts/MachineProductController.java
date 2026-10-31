package org.ultra.Domains.MachineProducts;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine-products")
public class MachineProductController {

    @PostMapping
    public MachineProduct makeMachineProduct(@RequestParam("typeCode") int typeCode, @RequestParam("productCode") int productCode) {
        return MachineProductHandler.makeMachineProduct(typeCode, productCode);
    }

    @GetMapping
    public List<MachineProduct> getMachineProducts() {
        return MachineProductHandler.getMachineProducts();
    }

    @GetMapping("/{machineProductCode}")
    public MachineProduct getMachineProductById(@PathVariable("machineProductCode") int machineProductCode) {
        return MachineProductHandler.getMachineProductById(machineProductCode);
    }

    @DeleteMapping("/{machineProductCode}")
    public void removeMachineProduct(@PathVariable("machineProductCode") int machineProductCode) {
        MachineProductHandler.removeMachineProduct(machineProductCode);
    }
}
