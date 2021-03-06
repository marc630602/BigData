package atguigu.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class PartitionProducer {
    public static void main(String[] args) {
        //1.创建Kafka生产者配置信息
        Properties properties = new Properties();
        //2.指定连接的Kafka集群
        properties.put("bootstrap.servers", "hadoop102:9092");
        //3.ACK应答级别
        properties.put("acks", "all");
        //4.重试次数
        properties.put("retries", 3);
        //5.批次大小
        properties.put("batch.size", 16384);
        //6.等待时间
        properties.put("linger.ms", 1);
        //7.RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);
        //8.序列化key,value的类
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //9.添加分区器
        properties.put("partitioner.class","atguigu.partition.MyPartition");
        //9.创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        //10.发送数据
        for (int i = 0;i < 10;i++){
            producer.send(new ProducerRecord<String, String>("aaa", "atguigu--" + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (exception == null) {
                                System.out.println(metadata.partition()+ "======" + metadata.offset());
                            } else {
                                exception.printStackTrace();
                            }
                        }
                    });
        }

        //11.关闭资源
        producer.close();
    }
}
