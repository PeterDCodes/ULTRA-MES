
# main program used as a runner to produce gadgets.
# will instantiate and instance of a machine
# will produce fake records
# will publish records via MQTT

from machine import Machine;

#product ID
#timestamp
#param_name
#param_val
#param_unit


gadgetMachine = Machine();
gadgetMachine.setName("GadgetMaker");

#Paramters
gadgetMachine.addParam("weight", "g", 1, 10)
gadgetMachine.addParam("length", "mm", 4, 6)
gadgetMachine.addParam("temp", "c", 150, 200)
gadgetMachine.addParam("diameter", "mm", 0, 2)
gadgetMachine.addParam("cut_speed", "ms", 15, 25)



