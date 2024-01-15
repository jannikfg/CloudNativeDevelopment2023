# CloudNativeDevelopment2023

## Installation in einer VM

### Java Anwendungen

1. **Java installieren:**

   - Installation der Java-Laufzeitumgebung (JRE).

2. **Anwendung vorbereiten:**

   - Anwendung bauen, um die JAR-Datei zu erhalten.

3. **Dateien auf die VM übertragen:**

   - Kopieren der JAR auf die VM durch SCP, SFTP oder ähnliches.

4. **Anwendung starten:**
   - Starten der Anwendung durch den Befehl `java -jar your-application.jar` in der Kommandozeile.

### Node Anwendung

1. **Node installieren:**

   - Installation von Node.

2. **Dateien auf die VM übertragen:**

   - Kopieren der Anwendungsdateien auf die VM durch SCP, SFTP oder ähnliches.

3. **Abhängigkeiten installieren**

   - Navigation in das Anwendungsverzeichnis und Installation der Abhängigkeiten durch `npm install`.

4. **Anwendung starten:**
   - Starten der Anwendung durch den Befehl `npm start` in der Kommandozeile.

### Datenbank

1. **PostgreSQL installieren:**

   - Installation von PostgreSQL mit dem folgenden Befehl:

     ```bash
     sudo apt install postgresql
     ```

2. **Zugriff auf PostgreSQL:**

   - PostgreSQL erstellt standardmäßig einen Benutzer mit dem Namen "postgres". Mit diesem kann man sich erstmalig an der DB anmelden.

     ```bash
     sudo -u postgres psql
     ```

3. **Erstellen eines neuen Benutzer und einer Datenbank:**

   ```bash
   sudo -u postgres createuser --interactive
   sudo -u postgres createdb userservice
   ```

4. **Starten und Stoppen des PostgreSQL-Dienstes:**

   ```bash
   sudo service postgresql start
   sudo service postgresql stop
   sudo service postgresql restart
   ```

### Frontend

1. **Nginx installieren:**

   ```bash
   sudo apt install nginx
   ```

2. **Starten des Nginx-Dienst:**

   ```bash
   sudo service nginx start
   ```

3. **Build der SvelteKit-Applikation:**

   - Erstellen der statischen HTML-Dateien über den Befehl `npm run build`. Im Build-Ordner befinden sich im Anschluss die statischen Dateien.

4. **Deployen Sie die statische HTML-Seite:**

   - Platzieren der statischen HTML-Seite im Nginx-Dokumentenverzeichnis, das standardmäßig unter `/var/www/html` liegt.

     ```bash
     sudo rm -r /var/www/html/*
     sudo cp -r /build/* /var/www/html/
     ```

5. **Starten und Stoppen des Nginx-Dienstes:**

   ```bash
   sudo service nginx start
   sudo service nginx stop
   sudo service nginx restart
   ```

### Kafka

1. **Java installieren:**

   - Installation der Java-Laufzeitumgebung (JRE).

2. **ZooKeeper installieren:**

   - Apache Kafka benötigt ZooKeeper. Es kann Apache ZooKeeper separat installieren oder eine interne ZooKeeper-Instanz verwendet werden.

3. **Apache Kafka herunterladen:**

   ```bash
   wget https://downloads.apache.org/kafka/<version>/kafka_<version>.tgz
   tar -xzf kafka_<version>.tgz
   cd kafka_<version>
   ```

4. **Starten des Kafka-Server:**

   - Starten des Kafka-Servers und ZooKeeper:

     ```bash
     # Starten ZooKeeper
     bin/zookeeper-server-start.sh config/zookeeper.properties

     # Starten Kafka
     bin/kafka-server-start.sh config/server.properties
     ```

5. **Konfigurationen:**
   - Anpassen der Kafka-Konfigurationsdateien unter `config/server.properties`.
