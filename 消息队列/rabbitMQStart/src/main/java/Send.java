import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME="hello";

    public static void main(String[] args) throws java.io.IOException{
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");/*设置rabbitmq所在主机ip或主机名*/
        /*指定用户名和密码*/
        factory.setUsername("guest");
        factory.setPassword("guest");
        //指定端口
        factory.setPort(AMQP.PROTOCOL.PORT);
        //创建连接
        try{
            Connection connection=factory.newConnection();
            Channel channel=connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message="";
            for(int i=0;i<10;i++){
                message=Integer.toString(i);
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                System.out.println(" Sent " + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            channel.close();
            connection.close();
        }catch (TimeoutException e){
            e.printStackTrace();
        }

    }
}
