public interface INotifiable
{
    int Destinataire { get; set; }

    void Envoyer();
}

public abstract class Notification : INotifiable
{
    public int Destinataire { get; set; }

    public void Envoyer()
    {
        FormaterMessage();
        Console.WriteLine("Message envoyé à {0}", Destinataire);
    }

    public abstract void FormaterMessage();

}

public class NotificationEmail : Notification
{

    public NotificationEmail(int destinataire, String sujet)
    {
        Destinataire = destinataire;
        Sujet = sujet;
    }

    public String Sujet;

    public override void FormaterMessage()
    {
        Console.WriteLine("Email formaté pour {0} avec le sujet {1}", Destinataire, Sujet);
    }
}

public class NotificationSMS : Notification
{

    public NotificationSMS(int destinataire, String message, String numeroEmeteur)
    {
        Destinataire = destinataire;
        Message = message;
        NumeroEmeteur = numeroEmeteur;
    }

    public String Message;

    public String NumeroEmeteur;

    public override void FormaterMessage()
    {
        Console.WriteLine("SMS formaté pour {0} avec le message {1} et le numéro émeteur {2}", Destinataire, Message, NumeroEmeteur);
    }
}


// List<INotifiable> notifications = new List<INotifiable>();
// notifications.Add(new NotificationEmail(1, "Test"));
// notifications.Add(new NotificationEmail(2, "Test 2"));
// notifications.Add(new NotificationSMS(3, "Test 3", "1234567890"));
// notifications.Add(new NotificationSMS(4, "Test 2 4", "1234567890"));

// foreach (INotifiable notification in notifications)
// {
//     notification.Envoyer();
// }