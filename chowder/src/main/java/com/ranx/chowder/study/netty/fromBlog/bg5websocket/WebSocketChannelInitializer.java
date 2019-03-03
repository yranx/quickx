package com.ranx.chowder.study.netty.fromBlog.bg5websocket;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * @Description 服务端通道初始化
 * @author ranx
 * @date 2018年11月1日09:20:54
 *
 */
public class WebSocketChannelInitializer extends  ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec:针对http协议进行编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        /**
         * 作用是将一个Http的消息组装成一个完成的HttpRequest或者HttpResponse,那么具体的是什么取决于是请求还是响应，
         * 该Handler必须放在HttpServerCodec后的后面
         */
        pipeline.addLast("httpObjectAggregaor", new HttpObjectAggregator(8999));
        //用于处理websocket,/ws为访问websocket时的uri
        pipeline.addLast("webSocketSererProtocolHandler", new WebSocketServerProtocolHandler("/ws"));
        //自定义的Handler
        pipeline.addLast(new WebSocketHandler());
    }

}