from random import randint
from typing import Generator

from .networks import NeuralNetwork
from .neurones import SigmoidNeuron
from .enum import NeuronType
from .decorators import trace

@trace
def create_random_neural_network():
    return NeuralNetwork.builder() \
        .with_input_layer(randint(1, 10), NeuronType.SIGMOID) \
        .with_hidden_layers(randint(1, 15), randint(2, 10), NeuronType.SIGMOID) \
        .with_output_layer(randint(1, 10), NeuronType.SIGMOID) \
        .build()

def create_neural_network_generator() -> Generator[NeuralNetwork, None, None]:
    neural_network: NeuralNetwork = create_random_neural_network()
    while True:
        yield neural_network
        del neural_network
        neural_network = create_random_neural_network()

def main():
    create_random_neural_network()

if __name__ == "__main__":
    main()
