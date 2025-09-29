// class Program
// {
//     static void Main(string[] args)
//     {
//         List<INotifiable> notifications = new List<INotifiable>();
//         notifications.Add(new NotificationEmail(1, "Test"));
//         notifications.Add(new NotificationEmail(2, "Test 2"));
//         notifications.Add(new NotificationSMS(3, "Test 3", "1234567890"));
//         notifications.Add(new NotificationSMS(4, "Test 2 4", "1234567890"));

//         foreach (INotifiable notification in notifications)
//         {
//             notification.Envoyer();
//         }
//     }
// }
