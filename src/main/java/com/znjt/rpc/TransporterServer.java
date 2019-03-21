package com.znjt.rpc;

import com.znjt.boot.Boot;
import com.znjt.exs.ExceptionInfoUtils;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by qiuzx on 2019-03-14
 * Company BTT
 * Depart Tech
 * @author qiuzx
 */
public class TransporterServer {
    private Logger logger = LoggerFactory.getLogger(TransporterServer.class);
    private Server server = null;
    private int RPC_PORT = 9898;
    private TransferProtoImpl4Server transporterServer;

    private void start() throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info("Begin Start RPC Server At " + RPC_PORT + " .");
        }
        transporterServer = new TransferProtoImpl4Server();
        server = ServerBuilder.forPort(RPC_PORT).addService(transporterServer).maxInboundMessageSize(Boot.FRAME_MAX_SIXE).build().start();
        if (logger.isInfoEnabled()) {
            logger.info("RPC Server Started Bind At " + RPC_PORT + " .");
        }
        //程序退出时关闭资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (logger.isInfoEnabled()) {
                logger.info("shutting down gRPC server since JVM is shutting down");
            }
            shutdown();
            if (logger.isInfoEnabled()) {
                logger.info("server shut down");
            }
        }));
    }

    private void await4terminal() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
        }
        if (logger.isInfoEnabled()) {
            logger.info("RPC Sever Stop Success");
        }
    }

    /**
     * 启动RPC Server
     *
     * @throws Exception
     */
    public void startServer(int port) {
        try {
            if(port>0) {
                RPC_PORT = port;
            }
            start();
            await4terminal();
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("RPC Server Started Failure Cause by " + ExceptionInfoUtils.getExceptionCauseInfo(e));
            }
        }
    }

    public void shutdown() {
        if (server != null) {
            if(logger.isInfoEnabled()) {
                logger.info("Begin to stop RPC Server...");
            }
            server.shutdown();
        }
    }
}
