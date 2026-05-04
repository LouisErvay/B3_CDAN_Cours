# Python avancé — fiche de référence (2026/05/04)

## Types de variables (et objets)

- **Immutables** : `int`, `float`, `bool`, `str`, `tuple`, `frozenset`, `bytes` — une « modification » crée un nouvel objet.
- **Mutables** : `list`, `dict`, `set`, `bytearray` — partagés par référence ; attention aux effets de bord en argument ou alias.
- **Vérification** : `type(x)` (classe exacte), `isinstance(x, T)` (héritage, unions `tuple` de types), `is` pour l’identité d’objet, pas l’égalité de valeur (`==`).

```python
s = "ab"; s2 = s + "c"              # immutable : nouvel objet str
a = [1]; b = a; b.append(2)         # mutable : a et b pointent la même liste
print(type(a), isinstance(a, (list, tuple)), a is b, a == [1, 2])
```

## Fonctions

- **Définition** : `def` crée une fonction ; portée lexicale (LEGB : local, enclosing, global, builtins).
- **Fonction « de classe »** : en pratique, **fonction statique** — `@staticmethod` : pas d’`self` ni de `cls` ; logique liée à la classe sans instance.
- **Méthode** : premier paramètre `self` (instance) ou `cls` (classe pour `@classmethod`) ; vit sur la classe, reçoit l’instance/classe à l’appel.
- **Arguments** : positionnels d’abord, puis `*args`, puis uniquement nommés (`*` seul ou paramètres après `*`), puis `**kwargs`. Valeurs par défaut évaluées **à la définition** (piège classique avec `def f(x=[])`) — préférer `None` + initialisation dans le corps.
- **Nommés** : `f(a=1, b=2)` ; déballage `f(**d)` si les clés correspondent aux paramètres.

```python
def outer():
    x = 1
    def inner():              # Enclosing dans LEGB
        return x
    return inner

class C:
    tag = "cls"
    def __init__(self, v): self.v = v
    def inst(self): return self.v           # self
    @classmethod
    def from_int(cls, n): return cls(str(n))  # cls
    @staticmethod
    def plain(): return C.tag               # ni self ni cls

def f(a, *args, b, c=0, **kw):            # b,c uniquement nommés après *
    return a, args, b, c, kw

def g(items=None):
    if items is None: items = []          # évite le défaut mutable
    items.append(1); return items

params = {"b": 1, "c": 3, "taille": 99}
f(0, 9, **{k: v for k, v in params.items() if k != "taille"})  # nommés + déballage
```

## Gestionnaires de contexte (`__enter__`, `__exit__`)

- **`with`** garantit un « cleanup » même en cas d’exception.
- Protocole : `__enter__` retourne souvent `self` (ou une ressource) ; `__exit__(exc_type, exc, tb)` : si retourne `True`, l’exception est **supprimée** ; sinon elle se propage après `__exit__`.
- Usage typique : fichiers, verrous, transactions. `contextlib.contextmanager` (generator avec `yield`) évite une classe si la logique est simple.

```python
from contextlib import contextmanager

class CM:
    def __enter__(self):
        return self
    def __exit__(self, exc_type, exc, tb):
        return True  # supprime l’exception éventuelle du bloc with

@contextmanager
def managed():
    print("enter"); yield 42; print("exit")  # yield = corps du with

with open(__file__, encoding="utf-8") as f:  # fichier + with
    _ = f.readline()
with managed() as x:
    assert x == 42
with CM():
    1 / 0  # avalé par __exit__ True
```

## Décorateurs et `__call__`

- **Décorateur** : `@deco` appliqué à `def f` ≈ `f = deco(f)` ; peut être paramétré : `@deco(args)` = `f = deco(args)(f)`.
- **Implémentation** : souvent une fonction ou une **classe** dont `__call__` renvoie la fonction wrappée ; le wrapper capture l’original.
- **`__call__`** : rend l’**instance** callable `obj()` ; à ne pas confondre avec l’appel de fonction classique. Indispensable pour des décorateurs à état (classe) ou des façades.
- Rappel utile : `functools.wraps` pour copier `__name__`, `__doc__` sur le wrapper.

```python
from functools import wraps

def deco(f):
    @wraps(f)
    def w(*a, **k): return f(*a, **k)
    return w

def repeat(n):
    def deco_p(f):
        @wraps(f)
        def w(*a, **k):
            for _ in range(n): f(*a, **k)
        return w
    return deco_p

class DecoCls:
    def __init__(self, f): self.f = f
    def __call__(self, *a, **k):          # instance callable
        return self.f(*a, **k)

@repeat(2)
@deco
def hi(): return "hi"
```

## Classes, abstraites (`abc`), `Protocol`

- **Classe** : attributs d’instance (souvent dans `__init__`), attributs de classe, héritage MRO consultable via `Class.__mro__`.
- **`abc.ABC` + `@abstractmethod`** : sous-classe **obligée** d’implémenter les méthodes marquées ; instanciation de la base abstraite interdite.
- **`typing.Protocol` (structural subtyping)** : « interface » implicite : toute classe avec les bons attributs/méthodes est compatible **sans** héritage explicite. Utile pour le typage statique (mypy, etc.) et le duck typing documenté. Ne crée pas de contrainte **runtime** par défaut (sauf `@runtime_checkable` pour `isinstance` restreint).

```python
from abc import ABC, abstractmethod
from typing import Protocol, runtime_checkable

class Base(ABC):
    count = 0                    # attribut de classe
    @abstractmethod
    def run(self) -> None: ...

class Impl(Base):
    def __init__(self): self.x = 1  # instance
    def run(self) -> None: Base.count += 1

class Readable(Protocol):
    def read(self, n: int) -> bytes: ...

@runtime_checkable
class Named(Protocol):
    name: str

class FileLike:
    name = "x"
    def read(self, n: int) -> bytes: return b""

print(Impl.__mro__, isinstance(FileLike(), Named))
```

## Exceptions

- Hiérarchie sous `BaseException` ; en pratique attraper `Exception` (ou plus spécifique), pas `BaseException` (interrompt `KeyboardInterrupt`, `SystemExit` si mal attrapé).
- `try` / `except T as e` / `else` (si pas d’exc) / `finally` (toujours). `raise` seul relance l’exception courante.
- **Chaînage** : `raise New from e` (cause explicite) vs `from None` (masquer le contexte).
- **Bon usage** : exceptions spécifiques, message clair ; ne pas utiliser le contrôle de flux ordinaire.

```python
def work():
    try:
        x = 1 / 0
    except ZeroDivisionError as e:
        raise RuntimeError("échec") from e   # cause chaînée
    except Exception:
        raise                                 # relance l’actuelle
    else:
        return x
    finally:
        pass  # toujours exécuté

def quiet():
    try:
        int("x")
    except ValueError as e:
        raise TypeError("msg") from None     # masque la chaîne
```

## Modules et packages

- **Module** = fichier `.py` ; **package** = répertoire avec `__init__.py` (souvent minimal ; peut rester vide en 3.3+ avec namespace packages, selon cas).
- **Import** : `import m`, `from p import x`, `from . import y` (relatif au package). Premier import exécute le module **une fois** ; réimport explicite via `importlib.reload`.
- **Chemins** : `sys.path` (entrées répertoires) ; variable d’env `PYTHONPATH` ; installation éditable (`pip install -e`) pour dev.
- **Exports** : convention `__all__` pour `from module import *`.

```python
import sys
import importlib

__all__ = ["PUBLIC"]   # filtre import *
PUBLIC = 1
import math            # module std
from math import pi    # symbole précis
# from . import sibling  # uniquement dans un package (pas au top-level script)

import math as m
importlib.reload(m)    # ré-exécute le module
_ = sys.path           # chemins de recherche (PYTHONPATH, site-packages, etc.)
```

## Threads

- **GIL** : un seul thread Python natif exécute du bytecode à la fois — les threads **ne parallélisent pas** le CPU-bound pur Python ; utiles pour I/O bloquant ou attentes.
- **`threading.Thread`** : `start()`, `join()` ; synchronisation : `Lock`, `RLock`, `Event`, `Condition`, `Semaphore`, `queue.Queue` (file thread-safe).
- **Données partagées** : même règles que le mutable global — besoin de verrous ou structures conçues pour.
- **CPU-bound** : préférer **`multiprocessing`** ou tâches natives hors GIL (extensions, autre processus).

```python
import threading, queue
from multiprocessing import Pool

lock = threading.Lock()
q: queue.Queue[int] = queue.Queue()

def worker():
    with lock:
        q.put(1)       # file thread-safe ; lock pour état partagé si besoin

t = threading.Thread(target=worker)
t.start(); t.join()

def cpu_square(x): return x * x
if __name__ == "__main__":
    with Pool(2) as p:           # CPU-bound : autre processus, pas le GIL
        print(p.map(cpu_square, range(4)))
```

## Sockets

- **API** : `socket` ; familles `AF_INET` / `AF_INET6`, types `SOCK_STREAM` (TCP), `SOCK_DGRAM` (UDP).
- **TCP** : `connect` / `bind`+`listen`+`accept` ; flux orienté connexion, fiable, ordonné.
- **UDP** : `sendto` / `recvfrom` ; pas de connexion, pas de garantie — dimensionner les buffers.
- Bloquant par défaut ; `settimeout`, `selectors` / `asyncio` pour modèles plus scalables.
- Bon réflexe : toujours fermer ou `with` sur les sockets ; gestion des erreurs réseau (`ConnectionError`, timeouts).

```python
import socket

HOST, PORT = "127.0.0.1", 65432

# TCP serveur (écoute) + client (connect)
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as srv:
    srv.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    srv.bind((HOST, PORT)); srv.listen(1)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cli:
        cli.settimeout(2.0)
        cli.connect((HOST, PORT))
        conn, _ = srv.accept()
        with conn:
            conn.sendall(b"hi")
            assert cli.recv(4) == b"hi"

# UDP : sendto / recvfrom, buffer borné
with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as tx, \
     socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as rx:
    rx.bind((HOST, 0)); tx.settimeout(1.0)
    tx.sendto(b"ping", (HOST, rx.getsockname()[1]))
    data, _ = rx.recvfrom(1024)  # buffer max 1024
    assert data == b"ping"
```
