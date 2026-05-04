## Exercice 1

```
// Collection: people

// 1
db.people.find(
  { age: { $gte: 40 } },
  { _id: 0, user_id: 1, age: 1, status: 1 }
).sort({ status: -1 });

// 2
db.people.find(
  { status: { $in: ["A", "B"] } },
  { _id: 1, user_id: 1, age: 1 }
).sort({ age: 1 }).limit(5);

// 3
db.people.distinct("user_id", { status: "A", age: { $gte: 30, $lte: 60 } });

// 4
db.people.find(
  { status: "A", age: { $gt: 50 } },
  { _id: 1, user_id: 1, age: 1 }
).sort({ age: -1 });

// 5
db.people.find(
  { status: "A", user_id: { $regex: /^a/ }, age: { $lt: 60 } },
  { _id: 0, user_id: 1, status: 1, age: 1 }
);

// 6
db.people.find(
  { status: { $nin: ["A"] }, age: { $gte: 18 } },
  { _id: 0, user_id: 1, age: 1, status: 1 }
).sort({ user_id: 1 });

// 7
db.people.find(
  { status: "A", age: { $gte: 50, $lte: 70 } },
  { _id: 0, user_id: 1, age: 1 }
).sort({ age: 1 }).limit(10);

// 8
db.people.find(
  { $or: [{ status: "A" }, { age: { $gte: 45, $lte: 60 } }] },
  { _id: 0, user_id: 1, status: 1, age: 1 }
).sort({ status: 1, age: 1 });

// 9
db.people.find(
  { age: { $gt: 25 }, status: { $in: ["A", "C"] } }
).sort({ age: -1 });

// 10
db.people.find(
  { age: { $lt: 25 }, user_id: { $regex: /3$/ } },
  { _id: 0, user_id: 1, age: 1 }
);

// 11
db.people.find(
  { age: { $gte: 26, $lte: 50 }, status: { $ne: "X" } },
  { _id: 1, user_id: 1, age: 1 }
).sort({ user_id: -1 });

// 12
db.people.find(
  { user_id: { $regex: /^a/ }, age: { $gte: 30 } },
  { _id: 0, user_id: 1, age: 1 }
);

// 13
db.people.find(
  { status: "A", age: { $gt: 40 } },
  { _id: 0, user_id: 1, age: 1, status: 1 }
).sort({ age: 1, user_id: 1 });

// 14
db.people.find(
  { status: "A", age: { $lte: 60 } },
  { _id: 0, user_id: 1, age: 1 }
).sort({ age: -1, _id: -1 });

// 15
db.people.countDocuments({ status: "A" });

// 16
db.people.distinct("user_id", { age: { $gt: 30 } }).length;

// 17
db.people.aggregate([
  { $match: { age: { $gte: 30 } } },
  { $group: { _id: "$status", count_by_status: { $sum: 1 } } },
  { $project: { _id: 0, status: "$_id", count_by_status: 1 } }
]);

// 18
db.people.aggregate([
  { $match: { age: { $gt: 40 } } },
  { $group: { _id: { status: "$status", age: "$age" } } },
  { $project: { _id: 0, status: "$_id.status", age: "$_id.age" } }
]);
```