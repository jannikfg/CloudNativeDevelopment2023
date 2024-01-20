const Minio = require("minio");
const { IncomingForm } = require("formidable");
const Transform = require("stream").Transform;

const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.ACCESS_KEY_SECRET;
const bucket = process.env.BUCKET_NAME;
const region = process.env.REGION;
const s3_endpoint = process.env.S3_ENDPOINT;

const minioclient = new Minio.Client({
  endPoint: s3_endpoint,
  port: 9000,
  useSSL: false,
  accessKey: accessKeyId,
  secretKey: secretAccessKey,
});

function getImageAsBase64(bucket, object) {
  return new Promise((resolve, reject) => {
    minioclient.getObject(bucket, object, (err, dataStream) => {
      if (err) {
        reject(err);
        return;
      }

      const chunks = [];
      dataStream.on("data", (chunk) => chunks.push(chunk));
      dataStream.on("end", () => {
        const buffer = Buffer.concat(chunks);
        const base64Image = buffer.toString("base64");
        resolve(base64Image);
      });
    });
  });
}

const createBucketIfNotExistant = () => {
  minioclient.bucketExists(bucket, (err, exists) => {
    if (err) {
      console.error(err);
      return;
    }

    if (exists) {
      console.log(`Bucket '${bucket}' already exists.`);
    } else {
      
      minioclient.makeBucket(bucket, region, (err) => {
        if (err) {
          console.error(err);
          return;
        }

        console.log(`Bucket '${bucket}' created successfully.`);
      });
    }
  });
};

const downloadFromS3 = async (key) => {
  try {
    const imageAsBase64 = getImageAsBase64(bucket, key)
      .then((base64Image) => {
        return base64Image;
      })
      .catch((error) => {
        console.error(error);
        throw error;
      });

    return imageAsBase64;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

const uploadToS3 = async (req, id) => {
  return new Promise((resolve, reject) => {
    let options = {
      maxFileSize: 100 * 1024 * 1024,
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

        minioclient.putObject(bucket, id, this._writeStream, (err, etag) => {
          if (err) {
            form.emit("error", err);
          }
          console.log("File uploaded successfully.");
          form.emit("data", { name: "complete", value: etag });
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

module.exports = { uploadToS3, downloadFromS3, createBucketIfNotExistant };
