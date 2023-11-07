const express = require("express");
const app = express();
require("dotenv").config();

app.set("json spaces", 5); // to prettify json response

const fileparser = require("./helper/fileparser");

app.post("/api/v1/images", async (req, res) => {
  await fileparser(req)
    .then((data) => {
      res.status(200).json({
        message: "Success",
        data,
      });
    })
    .catch((error) => {
      res.status(400).json({
        message: "An error occurred.",
        error,
      });
    });
});

app.get("/", (req, res) => {
  res.send(`
    <h2>File Upload With <code>"Node.js"</code></h2>
    <form action="api/v1/images" enctype="multipart/form-data" method="post">
      <div>Select a file: 
        <input name="file" type="file" id="image" />
      </div>
      <input type="submit" value="Upload" />
    </form>

  `);
});

const port = process.env.PORT || 3000;

app.listen(port, () => {
  console.log(`Server listening on port ${port}...`);
});
