def trace(method_reference: callable):
    def wrapper(*args, **kwargs):
        print("Executing super method: " + method_reference.__name__)
        result = method_reference(*args, **kwargs)
        print("Successfully terminated: " + method_reference.__name__)
        return result
    return wrapper


class CallCounter:
    def __init__(self, method_reference: callable):
        self.method_reference: callable = method_reference
        self.counter: int = 0
    
    def __call__(self, *args, **kwargs):
        self.counter += 1
        print(f"Appel n°{self.counter} de la méthode: {self.method_reference.__name__}")
        return self.method_reference(*args, **kwargs)

class CallMethodLimiter:
    def __init__(self, max_call: int = -1):
        self.max_call: int = max_call
        self.current_call_number: int = 0
    
    def __call__(self, method_reference: callable):
        def wrapper(*args, **kwargs):
            if (self.max_call == -1):
                return method_reference(*args, **kwargs)
            else:
                if self.current_call_number in range(self.max_call):
                    self.current_call_number += 1
                    return method_reference(*args, **kwargs)
                else:
                    raise Exception("Cannot call the method.")
        return wrapper