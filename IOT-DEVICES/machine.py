from parameter import Parameter;
from datetime import datetime, UTC

#Python class of a machine

#Two types of machines will be used
    #widget
    #gadget

class Machine:

    def __init__(self, code, type, name):

        self.code = code
        self.type = type
        self.name = name
        self.params: list[Parameter] = []

    #Add param to the machine
    def addParam(self, param_name, param_unit, min, max):
        newParam = Parameter(param_name, param_unit, min, max);

        self.params.append(newParam)


    #Produces a record instance for all params of the machine
    def cycle(self, rounds=1):

        cycles = []

        for _ in range(rounds):

            #Set simulates measurements
            measurements = []
            for p in self.params:
                measurements.append(p.makeRecord());


            #Set simulated timestamp
            timestamp = datetime.now(UTC).timestamp()

            cycle = {
                "machine_code": self.code,
                "machine_type": self.type,
                "machine_name": self.name,
                "timestamp": timestamp,
                "measurements": measurements
            }

            cycles.append(cycle)


        return cycles;





