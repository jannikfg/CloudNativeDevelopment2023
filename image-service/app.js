const express = require("express");
const app = express();
require("dotenv").config();

app.set("json spaces", 5); // to prettify json response

const ImageRoute = require("./routes/image-route");
app.use("/api/v1/images", ImageRoute);

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
