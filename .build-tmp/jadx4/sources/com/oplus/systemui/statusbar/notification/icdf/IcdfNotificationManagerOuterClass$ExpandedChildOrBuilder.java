package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.StringValue;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder extends MessageLiteOrBuilder {
    int getActionsCount();

    List<IcdfNotificationManagerOuterClass$IcdfAction> getActionsList();

    IcdfNotificationManagerOuterClass$BigPictureStyleState getBigPictureStyleState();

    IcdfNotificationManagerOuterClass$BigTextStyleState getBigTextStyleState();

    IcdfNotificationManagerOuterClass$InboxStyleState getInboxStyleState();

    IcdfNotificationManagerOuterClass$MessagingStyleState getMessagingStyleState();

    IcdfNotificationManagerOuterClass.NotificationStyle getNotificationStyle();

    int getNotificationStyleValue();

    int getReplyContentCount();

    List<StringValue> getReplyContentList();
}
