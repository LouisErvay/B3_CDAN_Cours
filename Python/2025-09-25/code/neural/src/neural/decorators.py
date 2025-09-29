from typing import Callable, Any

def trace(method_reference: Callable) -> Callable:
    def wrapper(*args, **kwargs) -> Any:
        print(f"Calling {method_reference.__name__} with args: {args}, kwargs: {kwargs}")
        return method_reference(*args, **kwargs)
    return wrapper