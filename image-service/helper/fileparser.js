const { Upload } = require("@aws-sdk/lib-storage");
const { S3Client } = require("@aws-sdk/client-s3");
const { IncomingForm } = require("formidable");
const Transform = require("stream").Transform;

const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.ACCESS_KEY_SECRET;
const Bucket = process.env.BUCKET_NAME;
const region = process.env.REGION;

const s3client = new S3Client({
  endpoint: "http://127.0.0.1:9000",
  credentials: {
    accessKeyId,
    secretAccessKey,
  },
  region,
});

const parsefile = async (req) => {
  return new Promise((resolve, reject) => {
    let options = {
      maxFileSize: 100 * 1024 * 1024, //100 MBs converted to bytes,
      allowEmptyFiles: false,
    };

    const form = new IncomingForm(options);

    form.parse(req, (err, fields, files) => {});

    form.on("error", (error) => {
      reject(error.message);
    });

    form.on("data", (data) => {
      if (data.name === "complete") {
        resolve(data.value);
      }
    });

    form.on("fileBegin", (formName, file) => {
      file.open = async function () {
        this._writeStream = new Transform({
          transform(chunk, encoding, callback) {
            callback(null, chunk);
          },
        });

        this._writeStream.on("error", (e) => {
          form.emit("error", e);
        });

        // upload to S3
        new Upload({
          client: s3client,
          params: {
            ACL: "public-read",
            Bucket,
            Key: `${Date.now().toString()}-${this.originalFilename}`,
            Body: this._writeStream,
          },
          tags: [], // optional tags
          queueSize: 4, // optional concurrency configuration
          partSize: 1024 * 1024 * 5, // optional size of each part, in bytes, at least 5MB
          leavePartsOnError: false, // optional manually handle dropped parts
        })
          .done()
          .then((data) => {
            form.emit("data", { name: "complete", value: data });
          })
          .catch((err) => {
            form.emit("error", err);
          });
      };

      file.end = function (cb) {
        this._writeStream.on("finish", () => {
          this.emit("end");
          cb();
        });
        this._writeStream.end();
      };
    });
  });
};

module.exports = parsefile;
