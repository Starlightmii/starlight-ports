package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfMessage$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder {
    public final void addMessagingMessage(IcdfNotificationManagerOuterClass.IcdfMessagingMessage icdfMessagingMessage) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessage.-$$Nest$maddMessagingMessage(((GeneratedMessageLite.Builder) this).instance, icdfMessagingMessage);
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder
    public int getMessagingMessageCount() {
        return ((GeneratedMessageLite.Builder) this).instance.getMessagingMessageCount();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder
    public List<IcdfNotificationManagerOuterClass.IcdfMessagingMessage> getMessagingMessageList() {
        return Collections.unmodifiableList(((GeneratedMessageLite.Builder) this).instance.getMessagingMessageList());
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder
    public IcdfNotificationManagerOuterClass.IcdfPerson getPerson() {
        return ((GeneratedMessageLite.Builder) this).instance.getPerson();
    }

    public IcdfNotificationManagerOuterClass$IcdfMessage$Builder setPerson(IcdfNotificationManagerOuterClass$IcdfPerson$Builder icdfNotificationManagerOuterClass$IcdfPerson$Builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessage.-$$Nest$msetPerson(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$IcdfPerson$Builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfMessage$Builder setPerson(IcdfNotificationManagerOuterClass.IcdfPerson icdfPerson) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessage.-$$Nest$msetPerson(((GeneratedMessageLite.Builder) this).instance, icdfPerson);
        return this;
    }
}
