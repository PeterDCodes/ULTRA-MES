package org.ultra.domains.MachineAssignments;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine-assignments")
public class MachineAssignmentController {

    @PostMapping
    public MachineAssignment makeMachineAssignment(@RequestParam("areaCode") int areaCode, @RequestParam("machineCode") int machineCode) {
        return MachineAssignmentHandler.makeMachineAssignment(areaCode, machineCode);
    }

    @GetMapping
    public List<MachineAssignment> getMachineAssignments() {
        return MachineAssignmentHandler.getMachineAssignments();
    }

    @GetMapping("/{assignmentCode}")
    public MachineAssignment getMachineAssignmentById(@PathVariable("assignmentCode") int assignmentCode) {
        return MachineAssignmentHandler.getMachineAssignmentById(assignmentCode);
    }

    @DeleteMapping("/{assignmentCode}")
    public void removeMachineAssignment(@PathVariable("assignmentCode") int assignmentCode) {
        MachineAssignmentHandler.removeMachineAssignment(assignmentCode);
    }
}
