from parameter import Parameter;
#Python class of a machine

#Two types of machines will be used
    #widget
    #gadget

class Machine:

    def __init__(self):

        self.code = None
        self.type = None
        self.name = None
        self.params: list[Parameter] = []

    def setCode(self, code):
        self.code = code;

    def setName(self, name):
        self.name = name;

    #Add param to the machine
    def addParam(self, param_name, param_unit, min, max):
        newParam = Parameter(param_name, param_unit, min, max);

        self.params.append(newParam)


    #Produces a record instance for all params od the machine
    def cycle(self, rounds=1):

        for _ in range(rounds):
            for p in self.params:
                print(p.makeRecord());





