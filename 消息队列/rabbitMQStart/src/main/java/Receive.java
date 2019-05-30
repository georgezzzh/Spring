import com.rabbitmq.client.*;

public class Receive {
    private final static String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            int i=Integer.parseInt(message);
            System.out.println(" [x] Received '" + i*i + "'");
            if(i>5){
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
