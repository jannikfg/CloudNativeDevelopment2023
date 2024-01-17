FROM minio/minio:latest

COPY --from=docker.io/minio/mc:latest /usr/bin/mc /usr/bin/mc
RUN mkdir /data
RUN minio server /data & \
    server_pid=$!; \
    until mc alias set minio http://localhost:9000 minioadmin minioadmin; do \
        sleep 1; \
    done; \
    mc mb minio/image-bucket; \
    kill $server_pid

CMD ["minio", "server", "/data", "--address", ":9000", "--console-address", ":9090"]