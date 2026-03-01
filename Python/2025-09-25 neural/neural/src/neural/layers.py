from abc import ABC
from typing import List

from .neurones import Neuron

class Layer(ABC):
    def __init__(self, neurones: List[Neuron]):
        self.neurons: List[Neuron] = neurones

class InputLayer(Layer):
    def __init__(self, neurones: List[Neuron]):
        super().__init__(neurones)

class HiddenLayer(Layer):
    def __init__(self, neurones: List[Neuron]):
        super().__init__(neurones)

class OutputLayer(Layer):
    def __init__(self, neurones: List[Neuron]):
        super().__init__(neurones)
