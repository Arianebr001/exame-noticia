const express = require("express");
const sqlite3 = require("sqlite3").verbose();
const bodyParser = require("body-parser");
const app = express();
const port = 3000;

app.use(bodyParser.json());

const db = new sqlite3.Database("./news.db");

db.serialize(() => {
  db.run(
     `CREATE TABLE IF NOT EXISTS news (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      title TEXT NOT NULL,
      content TEXT NOT NULL,
      author TEXT NOT NULL,
      published_at TEXT NOT NULL
    ) `
  );


});

app.get("/news", (req, res) => {
  db.all("SELECT * FROM news", (err, rows) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(rows);
  });
});

app.get("/news/:id", (req, res) => {
  const { id } = req.params;
  db.get("SELECT * FROM news WHERE id = ?", [id], (err, row) => {
    if (err) return res.status(500).json({ error: err.message });
    if (!row) return res.status(404).json({ error: "News not found" });
    res.json(row);
  });
});

app.post("/news", (req, res) => {
  const { title, content, author, published_at } = req.body;
  if (!title || !content || !author || !published_at) {
    return res.status(400).json({ error: "All fields are required" });
  }
  db.run(
    "INSERT INTO news (title, content, author, published_at) VALUES (?, ?, ?, ?)",
    [title, content, author, published_at],
    function (err) {
      if (err) return res.status(500).json({ error: err.message });
      res.status(201).json({ id: this.lastID });
    }
  );
});

app.put("/news/:id", (req, res) => {
  const { id } = req.params;
  const { title, content, author, published_at } = req.body;
  if (!title || !content || !author || !published_at) {
    return res.status(400).json({ error: "All fields are required" });
  }
  db.run(
    "UPDATE news SET title = ?, content = ?, author = ?, published_at = ? WHERE id = ?",
    [title, content, author, published_at, id],
    function (err) {
      if (err) return res.status(500).json({ error: err.message });
      if (this.changes === 0)
        return res.status(404).json({ error: "News not found" });
      res.json({ message: "News updated successfully" });
    }
  );
});

app.delete("/news/:id", (req, res) => {
  const { id } = req.params;
  db.run("DELETE FROM news WHERE id = ?", [id], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    if (this.changes === 0)
      return res.status(404).json({ error: "News not found" });
    res.json({ message: "News deleted successfully" });
  });
});


app.listen(port, () => {
  console.log( `Server running at http://localhost:${port} `);
});