package com.qee.business.handler;

import com.qee.api.model.Response;
import com.qee.business.invoke.ModuleCmdInvoker;
import com.qee.business.invoke.ModuleCmdInvokerManager;
import com.qee.business.service.impl.DefaultSessionImpl;
import com.qee.business.service.session.Session;
import com.qee.protocal.constant.NettyMessageConstant;
import com.qee.protocal.message.NettyCustomHeader;
import com.qee.protocal.message.NettyCustomMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * Created by zhuqi on 2017/9/10.
 */
public class BusinessHandler extends SimpleChannelInboundHandler<NettyCustomMessage> {

    private static final String MODULE_ID = "moduleId";

    private static final String COMMAND_ID = "commandId";


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyCustomMessage msg) throws Exception {
        if (msg.getCustomHeader().getType() == NettyMessageConstant.CUSTOMER_BUSINESS_TYPE) {

            Session session = handle(ctx.channel(), msg);
            ModuleCmdInvoker invoker = ModuleCmdInvokerManager.getInvoker(session.getModuleId(), session.getCommandId());
            if (invoker != null) {
                Response response = invoker.invoke(session.getArgument());
                if (response != null && response.isSuccess() && response.getData() != null) {
                    session.write(response);
                }
            }

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private Session handle(Channel channel, NettyCustomMessage msg) {
        NettyCustomHeader customHeader = msg.getCustomHeader();
        DefaultSessionImpl session = new DefaultSessionImpl(channel, msg.getBodyMessage());
        if (customHeader != null) {
            Map<String, Object> attachment = customHeader.getAttachment();
            if (attachment != null && attachment.size() > 0) {
                Object moduleId = attachment.get(MODULE_ID);
                Integer mId = 0;
                Integer cId = 0;
                if (moduleId instanceof Number) {
                    mId = (Integer) moduleId;
                }
                Object commandId = attachment.get(COMMAND_ID);
                if (commandId instanceof Number) {
                    cId = (Integer) commandId;
                }
                session.setModuleCmd(mId.shortValue(), cId.shortValue());
            }
        }
        return session;

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
