from typing import List

from .layers import Layer, InputLayer, HiddenLayer, OutputLayer
from .neurones import Neuron
from .enum import NeuronType

class NeuralNetworkBuilder:
    def __init__(self):
        self.__input_layer: InputLayer|None = None
        self.__hidden_layers: List[HiddenLayer] = []
        self.__output_layer: OutputLayer|None = None

    def with_input_layer(self, neurones_count: int, neuron_type: NeuronType = NeuronType.SIGMOID) -> 'NeuralNetworkBuilder':
        neurones: List[Neuron] = [neuron_type.create(0) for _ in range(neurones_count)]
        self.__input_layer = InputLayer(neurones)
        return self
    
    def with_hidden_layer(self, neurones_count: int, neuron_type: NeuronType = NeuronType.SIGMOID) -> 'NeuralNetworkBuilder':
        previous_layer_neurons_count = (len(self.__hidden_layers[-1].neurons) if len(self.__hidden_layers) > 0 else len(self.__input_layer.neurons))
        neurones: List[Neuron] = [neuron_type.create(previous_layer_neurons_count) for _ in range(neurones_count)]
        self.__hidden_layers.append(HiddenLayer(neurones))
        return self
        
    def with_hidden_layers(self, neurones_per_layer_count: int, layers_count: int, neuron_type: NeuronType = NeuronType.SIGMOID) -> 'NeuralNetworkBuilder':
        for _ in range(layers_count):
            self.with_hidden_layer(neurones_per_layer_count, neuron_type)
        return self
    
    def with_output_layer(self, neurones_count: int, neuron_type: NeuronType = NeuronType.SIGMOID) -> 'NeuralNetworkBuilder':
        previous_layer_neurons_count = (len(self.__hidden_layers[-1].neurons) if len(self.__hidden_layers) > 0 else len(self.__input_layer.neurons))
        neurones: List[Neuron] = [neuron_type.create(previous_layer_neurons_count) for _ in range(neurones_count)]
        self.__output_layer = OutputLayer(neurones)
        return self
        
    

    def build(self) -> 'NeuralNetwork':
        return NeuralNetwork(self.__input_layer, self.__hidden_layers, self.__output_layer)

class NeuralNetwork:
    def __init__(self, input_layer: InputLayer, hidden_layers: List[HiddenLayer], output_layer: OutputLayer):
        self.layers: List[Layer] = []
        self.layers.append(input_layer)
        self.layers.extend(hidden_layers)
        self.layers.append(output_layer)
    
    @staticmethod
    def builder() -> 'NeuralNetworkBuilder':
        return NeuralNetworkBuilder()

    def __enter__(self) -> 'NeuralNetwork':
        return self
    
    def __exit__(self, exc_type, exc_value, traceback) -> None:
        for layer in self.layers:
            del layer
        del self
