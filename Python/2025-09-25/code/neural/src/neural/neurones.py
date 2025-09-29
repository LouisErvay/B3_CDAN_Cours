from abc import ABC, abstractmethod
from typing import List
from math import exp, tanh
from random import uniform

class Neuron(ABC):
    def __init__(self, input_neurones_connection_count: int):
        self.weights: List[float] = [uniform(0, 1) for _ in range(input_neurones_connection_count)]

        self.bias: float = uniform(0, 1)

    @abstractmethod
    def activate(self, input_value: float) -> float: ...

    @abstractmethod
    def derivate(self, input_value: float) -> float: ...

class ReluNeuron(Neuron):
    def activate(self, input_value: float) -> float:
        return max(0, input_value)

    def derivate(self, input_value: float) -> float:
        return 1 if input_value > 0 else 0

class SigmoidNeuron(Neuron):
    def activate(self, input_value: float) -> float:
        return 1 / (1 + exp(-input_value))
    
    def derivate(self, input_value: float) -> float:
        return self.activate(input_value) * (1 - self.activate(input_value))

class TanhNeuron(Neuron):
    def activate(self, input_value: float) -> float:
        return tanh(input_value)
    
    def derivate(self, input_value: float) -> float:
        return 1 - self.activate(input_value) ** 2
