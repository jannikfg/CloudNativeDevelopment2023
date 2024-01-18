const express = require("express");
const router = express.Router();
const s3connection = require("../helper/s3connection");

router.post("/:id", async (req, res) => {
  const { id } = req.params;
  await s3connection
    .uploadToS3(req, id)
    .then((data) => {
      res.status(200).json({
        message: "Success",
        data,
      });
    })
    .catch((error) => {
      res.status(400).json({
        message: "An error occurred while trying to upload the image.",
        error,
      });
    });
});

router.get("/:key", async (req, res) => {
  const { key } = req.params;
  try {
    const data = await s3connection.downloadFromS3(key);
    res.writeHead(200, { "Content-Type": "image" });
    res.end(data);
  } catch (err) {
    res.status(500).send("Error fetching image.");
  }
});

module.exports = router;
