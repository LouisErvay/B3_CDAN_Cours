// using System;
// using System.Collections.Generic;
// using System.Linq;

// class Program
// {
//     static void Main(string[] args)
//     {
//         List<Employee> employees = new List<Employee>{
//             new Employee{Id = 1, Name = "John", Department = "IT"},
//             new Employee{Id = 2, Name = "Jane", Department = "HR"},
//             new Employee{Id = 3, Name = "Jim", Department = "IT"},
//             new Employee{Id = 4, Name = "Jill", Department = "HR"},
//             new Employee{Id = 5, Name = "Jack", Department = "IT"},
//             new Employee{Id = 6, Name = "Jill", Department = "HR"},
//             new Employee{Id = 7, Name = "Jack", Department = "IT"},
//         };

//         List<Project> projects = new List<Project>{
//             new Project{Id = 1, Name = "Project 1", ResponsableId = 1, Budget = 100000},
//             new Project{Id = 2, Name = "Project 2", ResponsableId = 2, Budget = 200000},
//             new Project{Id = 3, Name = "Project 3", ResponsableId = 3, Budget = 300000},
//             new Project{Id = 4, Name = "Project 4", ResponsableId = 1, Budget = 400000},
//             new Project{Id = 5, Name = "Project 5", ResponsableId = 2, Budget = 500000},
//         };

//         var jointure = from project in projects
//                        join employee in employees on project.ResponsableId equals employee.Id
//                        select new
//                        {
//                            projectName = project.Name,
//                            projectBudget = project.Budget,
//                            projectResponsableId = project.ResponsableId,
//                            employeeName = employee.Name,
//                            employeeDepartment = employee.Department
//                        };

//         Console.WriteLine("Tous les projets :");
//         foreach (var item in jointure)
//         {
//             Console.WriteLine($"Project {item.projectName}, responsable {item.employeeName}, département {item.employeeDepartment}");
//         }

//         var projectFromIT = jointure.Where(item => item.employeeDepartment == "IT");
//         Console.WriteLine("Projets de l'IT :");
//         foreach (var item in projectFromIT)
//         {
//             Console.WriteLine($"Project {item.projectName}, responsable {item.employeeName}, département {item.employeeDepartment}");
//         }

//         var projectByResponsable = jointure.GroupBy(item => item.projectResponsableId);
//         Console.WriteLine("Projets par responsable :");
//         foreach (var item in projectByResponsable)
//         {
//             Console.WriteLine($"Responsable : {item.Key}, nombre de projets : {item.Count()}, budget total : {item.Sum(p => p.projectBudget)}");
//         }

//     }
// }

// public class Employee
// {
//     public required int Id { get; set; }
//     public required string Name { get; set; }
//     public required string Department { get; set; }
// }

// public class Project
// {
//     public required int Id { get; set; }
//     public required string Name { get; set; }
//     public int ResponsableId { get; set; }
//     public int Budget { get; set; }
// }