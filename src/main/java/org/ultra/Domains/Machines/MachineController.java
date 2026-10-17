package org.ultra.Domains.Machines;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @PostMapping
    public Machine makeMachine(@RequestParam("machineName") String machineName, @RequestParam("machineType") int machineType) {
        return MachineHandler.makeMachine(machineName, machineType);
    }

    @GetMapping
    public List<Machine> getMachines() {
        return MachineHandler.getMachines();
    }

    @GetMapping("/{machineCode}")
    public Machine getMachineById(@PathVariable("machineCode") int machineCode) {
        return MachineHandler.getMachineById(machineCode);
    }

    @DeleteMapping("/{machineCode}")
    public void removeMachine(@PathVariable("machineCode") int machineCode) {
        MachineHandler.removeMachine(machineCode);
    }
}
