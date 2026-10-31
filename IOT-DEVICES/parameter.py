import random

class Parameter:

    def __init__(self, param_name, param_unit, min, max):
        self.name = param_name
        self.unit = param_unit
        self.min = min
        self.max = max
       


    #Makes a param with a name, unit and param range
    #returns a simple key value
    def makeRecord(self):

        #new Record stub
        record = {
            "name": self.name,
            "unit": self.unit
        }

        #Make random value in range of min-max
        val = round(random.uniform(self.min, self.max),2)

        record["value"] = val

        return record;



        