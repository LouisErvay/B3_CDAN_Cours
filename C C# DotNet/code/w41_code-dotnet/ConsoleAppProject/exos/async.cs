using System;
using System.Threading;
using System.Threading.Tasks;

class Program
{
    static async Task Main()
    {
        Console.WriteLine("Début de la requête...");

        using CancellationTokenSource cts = new CancellationTokenSource(2500);

        try
        {
            await FaireRequeteLenteAsync(cts.Token);
            Console.WriteLine("Requête terminée avec succès !");
        }
        catch (OperationCanceledException)
        {
            Console.WriteLine("Timeout dépassé : la requête a été annulée.");
        }

        Console.WriteLine("Fin du programme.");
    }

    static async Task FaireRequeteLenteAsync(CancellationToken token)
    {
        Console.WriteLine("Requête en cours (4 secondes simulées)...");
        await Task.Delay(4000, token);
        Console.WriteLine("Réponse reçue !");
    }
}
