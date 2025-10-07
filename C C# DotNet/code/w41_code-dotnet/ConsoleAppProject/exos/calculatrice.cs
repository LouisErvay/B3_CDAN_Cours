// using System;

// class Program
// {
//     static void Main()
//     {
//         Console.WriteLine("=== Division de deux nombres ===");

//         try
//         {
//             Console.Write("Entrez le premier nombre (dividende) : ");
//             string input1 = Console.ReadLine() ?? String.Empty;
//             float nombre1 = TransformStringToFloat(input1);

//             Console.Write("Entrez le second nombre (diviseur) : ");
//             string input2 = Console.ReadLine() ?? String.Empty;
//             float nombre2 = TransformStringToFloat(input2);

//             float resultat = Divide(nombre1, nombre2);

//             Console.WriteLine($"\nRésultat : {nombre1} / {nombre2} = {resultat}");
//         }
//         catch (FormatException ex)
//         {
//             Console.WriteLine($"Erreur de format : {ex.Message}");
//         }
//         catch (DivideByZeroException ex)
//         {
//             Console.WriteLine($"Erreur mathématique : {ex.Message}");
//         }
//         catch (Exception ex)
//         {
//             Console.WriteLine($"Une erreur inattendue est survenue : {ex.Message}");
//         }
//         finally
//         {
//             Console.WriteLine("\nFin du programme.");
//         }

//         float TransformStringToFloat(string nombre)
//         {
//             if (!float.TryParse(nombre, out float nombreFloat))
//             {
//                 throw new FormatException("Le nombre n'est pas un float valide.");
//             }
//             return nombreFloat;
//         }

//         float Divide(float nombre1, float nombre2)
//         {
//             if (nombre2 == 0)
//             {
//                 throw new DivideByZeroException("Impossible de diviser par zéro.");
//             }
//             return nombre1 / nombre2;
//         }
//     }
// }
