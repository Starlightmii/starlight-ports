package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.StringValue;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder extends MessageLiteOrBuilder {
    StringValue getConversationTitle();

    int getHistoricMessagesCount();

    List<IcdfNotificationManagerOuterClass.IcdfMessage> getHistoricMessagesList();

    int getMessagesCount();

    List<IcdfNotificationManagerOuterClass.IcdfMessage> getMessagesList();

    IcdfNotificationManagerOuterClass.IcdfPerson getPerson();
}
