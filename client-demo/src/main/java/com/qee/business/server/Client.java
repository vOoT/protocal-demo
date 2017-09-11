package com.qee.business.server;


import com.qee.api.model.Request;
import com.qee.api.model.RequestParam;
import com.qee.business.test.User;
import com.qee.protocal.authentication.AuthorityCertificationRequestHanlder;
import com.qee.protocal.client.NettyProtocalClientTemplate;
import com.qee.protocal.constant.NettyMessageConstant;
import com.qee.protocal.decoder.ByteBuf2NettyMessageDecoder;
import com.qee.protocal.encoder.NettyMessage2ByteBufEncoder;
import com.qee.protocal.heartbeat.HeartBeatCheckRequestHandler;
import com.qee.protocal.message.NettyCustomHeader;
import com.qee.protocal.message.NettyCustomMessage;
import com.qee.protocal.model.Pair;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Service("client")
@EnableAsync
public class Client extends NettyProtocalClientTemplate {

    private static final String MODULE_ID = "moduleId";

    private static final String COMMAND_ID = "commandId";


    @Autowired
    public Client(@Value("${host}") String host, @Value("${port}") int port) {
        super(host, port);
    }

    public Client(int localPort, String host, int port) {
        super(localPort, host, port);
    }

    @Override
    public List<Pair<String, ChannelHandler>> addIOChannelHandlers() {
        List<Pair<String, ChannelHandler>> ret = new ArrayList<>();
        ret.add(new Pair("log", new LoggingHandler(LogLevel.INFO)));
        ret.add(new Pair("decoder", new ByteBuf2NettyMessageDecoder(6 * 1024, 4, 4, -8, 0, true)));
        ret.add(new Pair("encoder", new NettyMessage2ByteBufEncoder()));
        //  ret.add(new Pair("timeout", new ReadTimeoutHandler(50)));
        //ret.add(new Pair("authority", new AuthorityCertificationRequestHanlder()));
        //  ret.add(new Pair("hearbeat", new HeartBeatCheckRequestHandler()));
        return ret;
    }

    @Override
    public List<Pair<String, ChannelHandler>> addBusinessChannelHandlers() {
        return null;
    }

    /**
     * 这里需要异步启动，不然会阻塞
     *
     * @throws InterruptedException
     */
    @Override
    @Async
    public void start() throws InterruptedException {
        System.out.println("the thread name is" + Thread.currentThread().getName() + " :server is starting ....");
        super.connect();

    }

    @Override
    public void stop() {

    }

    @Override
    public void doSimpleBussiness(Channel channel) {
        /*Scanner scanner = new Scanner(System.in);
        NettyCustomMessage message = new NettyCustomMessage();
        NettyCustomHeader header = new NettyCustomHeader();
        header.setType(NettyMessageConstant.CUSTOMER_BUSINESS_TYPE);
        Map<String, Object> attachment = new HashMap<>();
        Random random = new Random();
        while (true) {
            int num = random.nextInt(4);
            if (num == 1) {
                attachment.put(MODULE_ID, 1);
                attachment.put(COMMAND_ID, 1);
                header.setAttachment(attachment);
                message.setCustomHeader(header);

                RequestParam requestParam1 = new RequestParam(1, UUID.randomUUID().toString(), 0);

                RequestParam[] all = new RequestParam[1];
                all[0] = requestParam1;
                Request request = new Request();
                request.setParams(all);
                message.setBodyMessage(JSONObject.fromObject(request).toString());
                channel.writeAndFlush(message);
            } else if (num == 2) {

                attachment.put(MODULE_ID, 1);
                attachment.put(COMMAND_ID, 2);
                header.setAttachment(attachment);
                message.setCustomHeader(header);
                message.setBodyMessage(null);
                channel.writeAndFlush(message);
            } else if (num == 3) {
                attachment.put(MODULE_ID, 1);
                attachment.put(COMMAND_ID, 3);
                header.setAttachment(attachment);
                message.setCustomHeader(header);
                User user = new User(new Random().nextInt(), UUID.randomUUID().toString());
                RequestParam requestParam1 = new RequestParam(1, "teacherId", 0);
                RequestParam requestParam2 = new RequestParam(2, user, 1, User.class);
                RequestParam[] all = new RequestParam[2];
                all[0] = requestParam1;
                all[1] = requestParam2;
                Request request = new Request();
                request.setParams(all);
                message.setBodyMessage(JSONObject.fromObject(request).toString());
                channel.writeAndFlush(message);
            }
        }*/

    }
}
