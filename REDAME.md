### Build
 Java 17
 Maven 3.8
### Run
java -jar db-cruder-0.0.1-SNAPSHOT.jar

### Prepare

## 1. Install Java 21 for Manjaro:
 sudo pacman -S jre21-openjdk 

## 2. Download kafka
https://kafka.apache.org/downloads

## 3. Install kafka
 tar -xzf kafka_2.13-4.0.0.tgz
 cd kafka_2.13-4.0.0
## 4. Configure kafka
- check ip
ip a
- edit kafka_2.13-4.0.0/config/server.properties set
advertised.listeners=PLAINTEXT://192.168.1.234:9092

## 5. Run kafka
 KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
 bin/kafka-server-start.sh config/server.properties

- check ports
ss -ntpl

## 6. Create topics
 bin/kafka-topics.sh --create --topic DBCruder --bootstrap-server localhost:9092
 bin/kafka-topics.sh --create --topic DBCruderErrors --bootstrap-server localhost:9092
- check topic
  bin/kafka-topics.sh --describe --topic DBCruder --bootstrap-server localhost:9092

## 7. Send message from file
bin/kafka-console-producer.sh --topic DBCruder --bootstrap-server 192.168.1.234:9092 < db_cruder_request.json

db_cruder_request.json
{"rootRecords": [{"actionType": "INSERT", "tableName": "CRE_FPS_REQ", "values": {"rtdm_id": "16737025", "form_id": "167370250", "applicationid": "3eaa0f89-74fd-4b93-bde1-02daad0b33f5", "sent_at":"2024-04-24'T'16:32:18.9984399" }, "dependentRecords": [] }}

## 8. Run App

## 9. Get a reply message
bin/kafka-console-consumer.sh --topic DBCruderErrors --from-beginning --bootstrap-server 192.168.1.234:9092


By the way!
You do not need to create a cluster to use Kafka for most development or small-scale purposes.
|Use Case               |Cluster Needed?            |Notes                                |
|-----------------------|---------------------------|-------------------------------------|
|Local development/test |No                         |Single broker is enough              |
|Simple production setup|No (not recommended for HA)|Single broker, but no fault tolerance|
|Scalable/fault-tolerant|Yes                        |Multi-node cluster recommended       |
