package com.qee.business.server;

import com.qee.business.handler.BusinessHandler;
import com.qee.protocal.authentication.AuthorityCertificationResponseHanlder;
import com.qee.protocal.decoder.ByteBuf2NettyMessageDecoder;
import com.qee.protocal.encoder.NettyMessage2ByteBufEncoder;
import com.qee.protocal.heartbeat.HeartBeatCheckResponseHandler;
import com.qee.protocal.model.Pair;
import com.qee.protocal.server.NettyProtocalServerTemplate;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Service("server")
public class Server extends NettyProtocalServerTemplate {


    @Autowired
    public Server(@Value("${host}") String host, @Value("${port}") int port) {
        super(host, port);
    }

    @Override
    public List<Pair<String, ChannelHandler>> addIOChannelHandlers() {
        List<Pair<String, ChannelHandler>> ret = new ArrayList<>();
        ret.add(new Pair("log", new LoggingHandler(LogLevel.INFO)));
        ret.add(new Pair("decoder", new ByteBuf2NettyMessageDecoder(6 * 1024, 4, 4, -8, 0, true)));
        ret.add(new Pair("encoder", new NettyMessage2ByteBufEncoder()));
        //ret.add(new Pair("timeout", new ReadTimeoutHandler(50)));
        //ret.add(new Pair("authority", new AuthorityCertificationResponseHanlder()));
      //  ret.add(new Pair("hearbeat", new HeartBeatCheckResponseHandler()));
        return ret;
    }

    @Override
    public List<Pair<String, ChannelHandler>> addBusinessChannelHandlers() {
        List<Pair<String, ChannelHandler>> ret = new ArrayList<>();
        ret.add(new Pair<String, ChannelHandler>("business", new BusinessHandler()));
        return ret;
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
        super.start();

    }

    @Override
    public void doSimpleBussiness(Channel channel) {

    }
}
