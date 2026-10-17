package org.ultra.domains.MachineTypes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine-types")
public class MachineTypeController {

    @PostMapping
    public MachineType makeMachineType(@RequestParam("machineType") String machineType) {
        return MachineTypeHandler.makeMachineType(machineType);
    }

    @GetMapping
    public List<MachineType> getMachineTypes() {
        return MachineTypeHandler.getMachineTypes();
    }

    @GetMapping("/{typeCode}")
    public MachineType getMachineTypeById(@PathVariable("typeCode") int typeCode) {
        return MachineTypeHandler.getMachineTypeById(typeCode);
    }

    @DeleteMapping("/{typeCode}")
    public void removeMachineType(@PathVariable("typeCode") int typeCode) {
        MachineTypeHandler.removeMachineType(typeCode);
    }
}
