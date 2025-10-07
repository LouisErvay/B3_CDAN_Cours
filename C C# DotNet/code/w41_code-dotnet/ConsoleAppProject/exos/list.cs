// class Program
// {
//     static void Main(string[] args)
//     {
//         List<Task> tasks = new List<Task>();
//         tasks.Add(new Task("Sport", "Perso"));
//         tasks.Add(new Task("Cuisine", "Perso"));
//         tasks.Add(new Task("Code", "Pro"));
//         tasks.Add(new Task("Gaming", "Perso"));
//         tasks.Add(new Task("Lire", "Perso"));

//         tasks[0].SetIsDone(true);
//         foreach (Task task in tasks)
//         {
//             Console.WriteLine(task.GetName() + " : " + task.GetCategory() + " : " + task.GetIsDone());
//         }
//     }
// }
class Task
{
    private String Name;
    private String Category;
    private Boolean IsDone;

    public Task(String name, String category)
    {
        Name = name;
        Category = category;
        IsDone = false;
    }

    public string GetName()
    {
        return Name;
    }
    public string GetCategory()
    {
        return Category;
    }
    public Boolean GetIsDone()
    {
        return IsDone;
    }
    public void SetIsDone(Boolean isDone)
    {
        IsDone = isDone;
    }
}