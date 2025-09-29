from enum import Enum
from typing import Type, Callable

from .neurones import SigmoidNeuron, TanhNeuron, ReluNeuron, Neuron

class NeuronType(Enum):
    SIGMOID = SigmoidNeuron
    TANH = TanhNeuron
    RELU = ReluNeuron
    
    def create(self, input_count: int) -> Neuron:
        return self.value(input_count)