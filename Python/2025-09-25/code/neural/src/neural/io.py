from socket import socket as Socket, AF_INET, SOCK_STREAM
from ipaddress import IPv4Network
from threading import Thread

class PortsScanner:
    @staticmethod
    def scan(host: str, starting_port: int, ending_port: int) -> list[int]:
        opened_ports: list[int] = []
        
        for port in range(starting_port, ending_port + 1):
            with Socket(AF_INET, SOCK_STREAM) as client_socket:

                try:
                    client_socket.settimeout(0.2)
                    client_socket.connect((host, port))
                except:
                    pass
                else:
                    opened_ports.append(port)

        return opened_ports

class NetworkScanner:
    @staticmethod
    def scan(ip_address: str, cidr: int) -> list[str]:
        addresses: list[str] = []
        address_network = IPv4Network(f"{ip_address}/{cidr}")
        for host in address_network.hosts():
            for port in [22, 88, 135, 139, 445, 464, 593, 3268, 3269, 5722, 5785]:
                open_port = PortsScanner.scan(str(host), port, port)
                if (len(open_port) > 0):
                    addresses.append(host)
                    break
        return addresses

class TcpClientConnecion(Thread):
    def __init__(self, client_socket: Socket):
        Thread.__init__(self)
        self.client_socket: Socket = client_socket

    def run(self):
        try:
            while True:
                message = input("[SRVR] Message : ")
                self.client_socket.send(message.encode())
                received_message = self.client_socket.recv(1_024)
                print(f"[SRVR] Message from client: {received_message.decode()}")
        except Exception as exception:
            print(f"A error occured in connection: {exception}")
        finally:
            self.client_socket.close()

class TcpServer:

    def __init__(self, bind_address: str, bind_port: int):
        self.bind_address = bind_address
        self.bind_port = bind_port

    def start(self):
        with Socket(AF_INET, SOCK_STREAM) as server_socket:
            print(f"Binding to : tcp://{self.bind_address}:{self.bind_port}")
            try:
                server_socket.bind((self.bind_address, self.bind_port))
                server_socket.listen(5)
                while True:
                    print("Waiting for client connection...")
                    client_socket, client_info = server_socket.accept()
                    print("A new client is connected!")
                    connection = TcpClientConnecion(client_socket)
                    connection.start()

            except Exception as exception:
                print(f"A error occured: {exception}")

class TcpClient:
    def __init__(self, host: str, port: int):
        self.host: str = host
        self.port: int = port
        self.socket: Socket = None
    
    def __enter__(self) -> "TcpClient":
        self.socket = Socket(AF_INET, SOCK_STREAM)
        self.socket.connect((self.host, self.port))
        return self
    
    def __exit__(self, *args, **kwargs):
        self.disconnect()
    
    def disconnect(self):
        if (self.socket is not None):
            try:
                self.socket.close()
            except:
                pass
            finally:
                self.socket = None

    def is_connected(self) -> bool:
        return self.socket is not None

    def send(self, message: str):
        if (self.is_connected()):
            try:
                self.socket.send(message.encode())
            except Exception as error:
                print(f"Error while sending message: {error}")
                self.disconnect()
    
    def receive(self) -> str|None:
        message = None
        if (self.is_connected()):
            try:
                data = self.socket.recv(1_024)
            except Exception as error:
                print(f"Error while receiving data: {error}")
                self.disconnect()
            else:
                message = data.decode()
        return message