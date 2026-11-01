
# main program used as a runner to produce gadgets.
# will instantiate and instance of a machine
# will produce fake records
# will publish records via MQTT
import json
import random
import time
from machine import Machine;
from mqtt_sender import publish_message;

#product ID
#timestamp
#param_name
#param_val
#param_unit



gadgetMachine = Machine(1, "gadget", "gadget-maker-1");

#Define Paramters to be used by the gadget maker
gadgetMachine.addParam("weight", "g", 7, 10)
gadgetMachine.addParam("length", "mm", 4, 6)
gadgetMachine.addParam("temp", "c", 150, 200)
gadgetMachine.addParam("diameter", "mm", 0, 2)
gadgetMachine.addParam("cut_speed", "ms", 15, 25)


#TODO. - simulate data streaming by randomly sending different cycles counts in random intervals

while(True):

    CYCLES = 1

    publish_message("mdata", (json.dumps(gadgetMachine.cycle(CYCLES))))

    rand_int = random.randint(2,5)
    time.sleep(rand_int);