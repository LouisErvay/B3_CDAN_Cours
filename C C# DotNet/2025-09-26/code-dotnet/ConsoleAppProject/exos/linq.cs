using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static void Main()
    {
        var ventes = new List<int> { 150, 45, 230, 80, 500, 15, 95, 300, 120, 60 };

        // 1. Filtrage
        var grossesVentes = ventes.Where(v => v > 100);

        // 2. Tri (et exécution immédiate)
        var grossesVentesTriees = grossesVentes.OrderBy(v => v).ToList();

        // 3. Comptage
        var nbPetitesVentes = ventes.Count(v => v <= 50);

        // 4. Projection
        var ventesAvecCommission = ventes.Select(v => v * 1.10).ToList();

        // Affichages
        Console.WriteLine("Grosses ventes (>100) triées : " + string.Join(", ", grossesVentesTriees));
        Console.WriteLine("Nombre de petites ventes (<=50) : " + nbPetitesVentes);
        Console.WriteLine("Ventes avec commission (+10%) : " + string.Join(", ", ventesAvecCommission));
    }
}
