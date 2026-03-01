from threading import Thread
from queue import Queue
from time import sleep
from datetime import datetime


class Mob(Thread):
    def __init__(self, name: str, queue: Queue):
        super().__init__()
        self.name = name
        self.queue = queue

    def run(self):
        while True:
            task = self.queue.get()
            if task is None:
                print(f"{self.name} quitte le combat.")
                self.queue.task_done()
                break

            print(f"{self.name} attaque {task} à {datetime.now().strftime('%H:%M:%S')}")
            sleep(1)  # simulate du travail
            print(f"{self.name} a fini d'attaquer {task}")
            self.queue.task_done()


class Fight:
    def __init__(self, fighters: list[str]):
        self.queue = Queue()
        self.mobs = [Mob(name, self.queue) for name in fighters]

    def start(self):
        for mob in self.mobs:
            mob.start()

        # Ajouter des tâches (ennemis à attaquer)
        enemies = ["Goblin", "Orc", "Troll", "Dragon"]
        for enemy in enemies:
            self.queue.put(enemy)

        # Bloque jusqu’à ce que toutes les tâches soient traitées
        self.queue.join()

        # Arrêt des threads
        for _ in self.mobs:
            self.queue.put(None)
        for mob in self.mobs:
            mob.join()

        print("Le combat est terminé !")
