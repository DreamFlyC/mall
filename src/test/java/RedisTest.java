import redis.clients.jedis.Jedis;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:56 2018/12/5
 * @ Description：Redis连接测试
 * @ Modified By：
 * @Version: 1.0$
 */

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("138.128.196.86");
        jedis.auth("742003942");
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
