# Changement de couleur et de forme

```mermaid
flowchart LR
    A[Rectangle]:::blue --> B((Cercle)):::red
    B --> C{{Hexagone}}:::green
    classDef blue fill:#cce5ff,stroke:#004085,stroke-width:2px;
    classDef red fill:#f8d7da,stroke:#721c24,stroke-width:2px;
    classDef green fill:#d4edda,stroke:#155724,stroke-width:2px;
```

# Diagramme de séquence

```mermaid
sequenceDiagram
    participant Alice
    participant Bob
    Alice->>Bob: Salut Bob, ça va ?
    Bob-->>Alice: Bien et toi ?
    Alice-xBob: Fin de conversation
```

# Diagramme de Gantt

```mermaid
gantt
    dateFormat  YYYY-MM-DD
    title Exemple de diagramme Gantt
    section Projet A
    Tâche 1        :a1, 2023-01-01, 7d
    Tâche 2        :after a1, 5d
    section Projet B
    Analyse        :2023-01-05, 3d
    Développement  :2023-01-10, 10d
```

# Diagramme d'état

```mermaid
stateDiagram-v2
    [*] --> Idle
    Idle --> Running : start
    Running --> Paused : pause
    Paused --> Running : resume
    Running --> [*] : stop
```

# Diagramme d'ER

```mermaid
erDiagram
    CLIENT ||--o{ COMMANDE : passe
    COMMANDE ||--|{ LIGNE_COMMANDE : contient
    PRODUIT ||--o{ LIGNE_COMMANDE : est_dans
```

# Roadmap

```mermaid
mindmap
  root((Idées))
    A(Travail)
      A1(Réunions)
      A2(Emails)
    B(Vie perso)
      B1(Sport)
      B2(Lecture)
```

# Diagramme Journey (User Routes)

```mermaid
journey
    title Expérience utilisateur
    section Achat
      Trouver produit: 5: Client
      Ajouter au panier: 4: Client
      Payer: 2: Client
    section Livraison
      Attente: 2: Client
      Réception: 5: Client
```

# Sous-graphes

```mermaid
flowchart TB
    subgraph Frontend
        A1[UI] --> A2[Formulaires]
    end
    subgraph Backend
        B1[(Base de données)]
        B2[API REST]
    end
    A2 --> B2 --> B1
```

# Zen-UML

```mermaid
zenuml
    title Order Service
    @Actor Client #FFEBE6
    @Boundary OrderController #0747A6
    @EC2 <<BFF>> OrderService #E3FCEF
    group BusinessService {
      @Lambda PurchaseService
      @AzureFunction InvoiceService
    }

    @Starter(Client)
    // `POST /orders`
    OrderController.post(payload) {
      OrderService.create(payload) {
        order = new Order(payload)
        if(order != null) {
          par {
            PurchaseService.createPO(order){
              OrderService.mdr
            }
            InvoiceService.createInvoice(order)
          }
        }
      }
    }

```
