package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.BindableService;
import io.grpc.MethodDescriptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.internal.JsonUtil$;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import java.lang.invoke.VarHandle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.properties.NotNullVar$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class IcdfNotificationManagerGrpc$IcdfNotificationManagerImplBase implements BindableService {
    public final ServerServiceDefinition bindService() {
        ServiceDescriptor serviceDescriptor = IcdfNotificationManagerGrpc.getServiceDescriptor();
        ServerServiceDefinition.Builder builder = new ServerServiceDefinition.Builder();
        HashMap map = new HashMap();
        builder.methods = map;
        serviceDescriptor.getClass();
        builder.serviceName = serviceDescriptor.getName();
        VarHandle.storeStoreFence();
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfUpdateRankingMapMethod(), new ServerCalls.UnaryServerCallHandler(new IcdfNotificationManagerGrpc.MethodHandlers(this, 0), true));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfAddNotificationMethod(), new ServerCalls.UnaryServerCallHandler(new IcdfNotificationManagerGrpc.MethodHandlers(this, 1), true));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfDeleteNotificationMethod(), new ServerCalls.UnaryServerCallHandler(new IcdfNotificationManagerGrpc.MethodHandlers(this, 2), true));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfErrorMethod(), new ServerCalls.UnaryServerCallHandler(new IcdfNotificationManagerGrpc.MethodHandlers(this, 3), true));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfConsumerDeleteMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 4)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfConsumerDeleteAllMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 5)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfNotificationClickedMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 6)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfActionClickedMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 7)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfReplyMessageMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 8)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfHeadsUpUnpinnedMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 9)));
        builder.addMethod(IcdfNotificationManagerGrpc.getIcdfNotifyAliveMethod(), ServerCalls.asyncUnaryCall(new IcdfNotificationManagerGrpc.MethodHandlers(this, 10)));
        HashMap map2 = new HashMap(map);
        for (MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
            ServerMethodDefinition serverMethodDefinition = (ServerMethodDefinition) map2.remove(methodDescriptor.getFullMethodName());
            if (serverMethodDefinition == null) {
                JsonUtil$.ExternalSyntheticBUOutline0.m(methodDescriptor.getFullMethodName(), "No method bound for descriptor entry ");
                return null;
            }
            if (serverMethodDefinition.getMethodDescriptor() != methodDescriptor) {
                NotNullVar$.ExternalSyntheticBUOutline0.m("Bound method for ", methodDescriptor.getFullMethodName(), " not same instance as method in service descriptor");
                return null;
            }
        }
        if (map2.size() > 0) {
            JsonUtil$.ExternalSyntheticBUOutline0.m(((ServerMethodDefinition) map2.values().iterator().next()).getMethodDescriptor().getFullMethodName(), "No entry in descriptor matching bound method ");
            return null;
        }
        Map map3 = builder.methods;
        ServerServiceDefinition serverServiceDefinition = new ServerServiceDefinition();
        serverServiceDefinition.serviceDescriptor = serviceDescriptor;
        serverServiceDefinition.methods = Collections.unmodifiableMap(new HashMap(map3));
        VarHandle.storeStoreFence();
        return serverServiceDefinition;
    }

    public abstract void icdfActionClicked(IcdfNotificationManagerOuterClass$IcdfAction icdfNotificationManagerOuterClass$IcdfAction, StreamObserver streamObserver);

    public abstract void icdfAddNotification(Empty empty, StreamObserver streamObserver);

    public abstract void icdfConsumerDelete(StringValue stringValue, StreamObserver streamObserver);

    public abstract void icdfConsumerDeleteAll(Empty empty, StreamObserver streamObserver);

    public abstract void icdfDeleteNotification(Empty empty, StreamObserver streamObserver);

    public abstract void icdfError(Empty empty, StreamObserver streamObserver);

    public abstract void icdfHeadsUpUnpinned(StringValue stringValue, StreamObserver streamObserver);

    public abstract void icdfNotificationClicked(StringValue stringValue, StreamObserver streamObserver);

    public abstract void icdfNotifyAlive(Empty empty, StreamObserver streamObserver);

    public abstract void icdfReplyMessage(IcdfNotificationManagerOuterClass.ReplyMsg replyMsg, StreamObserver streamObserver);

    public abstract void icdfUpdateRankingMap(Empty empty, StreamObserver streamObserver);
}
