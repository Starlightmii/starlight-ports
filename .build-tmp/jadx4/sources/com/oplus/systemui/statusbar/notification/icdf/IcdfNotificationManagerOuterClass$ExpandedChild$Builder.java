package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.StringValue;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$ExpandedChild$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder {
    public final void addActions(IcdfNotificationManagerOuterClass$IcdfAction icdfNotificationManagerOuterClass$IcdfAction) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$maddActions(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$IcdfAction);
    }

    public final void addReplyContent(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$maddReplyContent(((GeneratedMessageLite.Builder) this).instance, stringValue);
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public int getActionsCount() {
        return ((GeneratedMessageLite.Builder) this).instance.getActionsCount();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public List<IcdfNotificationManagerOuterClass$IcdfAction> getActionsList() {
        return Collections.unmodifiableList(((GeneratedMessageLite.Builder) this).instance.getActionsList());
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public IcdfNotificationManagerOuterClass$BigPictureStyleState getBigPictureStyleState() {
        return ((GeneratedMessageLite.Builder) this).instance.getBigPictureStyleState();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public IcdfNotificationManagerOuterClass$BigTextStyleState getBigTextStyleState() {
        return ((GeneratedMessageLite.Builder) this).instance.getBigTextStyleState();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public IcdfNotificationManagerOuterClass$InboxStyleState getInboxStyleState() {
        return ((GeneratedMessageLite.Builder) this).instance.getInboxStyleState();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public IcdfNotificationManagerOuterClass$MessagingStyleState getMessagingStyleState() {
        return ((GeneratedMessageLite.Builder) this).instance.getMessagingStyleState();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public IcdfNotificationManagerOuterClass.NotificationStyle getNotificationStyle() {
        return ((GeneratedMessageLite.Builder) this).instance.getNotificationStyle();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public int getNotificationStyleValue() {
        return ((GeneratedMessageLite.Builder) this).instance.getNotificationStyleValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public int getReplyContentCount() {
        return ((GeneratedMessageLite.Builder) this).instance.getReplyContentCount();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChildOrBuilder
    public List<StringValue> getReplyContentList() {
        return Collections.unmodifiableList(((GeneratedMessageLite.Builder) this).instance.getReplyContentList());
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setBigPictureStyleState(IcdfNotificationManagerOuterClass$BigPictureStyleState.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetBigPictureStyleState(((GeneratedMessageLite.Builder) this).instance, (IcdfNotificationManagerOuterClass$BigPictureStyleState) builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setBigTextStyleState(IcdfNotificationManagerOuterClass$BigTextStyleState.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetBigTextStyleState(((GeneratedMessageLite.Builder) this).instance, (IcdfNotificationManagerOuterClass$BigTextStyleState) builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setInboxStyleState(IcdfNotificationManagerOuterClass$InboxStyleState.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetInboxStyleState(((GeneratedMessageLite.Builder) this).instance, (IcdfNotificationManagerOuterClass$InboxStyleState) builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setMessagingStyleState(IcdfNotificationManagerOuterClass$MessagingStyleState.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetMessagingStyleState(((GeneratedMessageLite.Builder) this).instance, (IcdfNotificationManagerOuterClass$MessagingStyleState) builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle notificationStyle) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetNotificationStyle(((GeneratedMessageLite.Builder) this).instance, notificationStyle);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setNotificationStyleValue(int i) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetNotificationStyleValue(((GeneratedMessageLite.Builder) this).instance, i);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setBigPictureStyleState(IcdfNotificationManagerOuterClass$BigPictureStyleState icdfNotificationManagerOuterClass$BigPictureStyleState) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetBigPictureStyleState(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$BigPictureStyleState);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setBigTextStyleState(IcdfNotificationManagerOuterClass$BigTextStyleState icdfNotificationManagerOuterClass$BigTextStyleState) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetBigTextStyleState(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$BigTextStyleState);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setInboxStyleState(IcdfNotificationManagerOuterClass$InboxStyleState icdfNotificationManagerOuterClass$InboxStyleState) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetInboxStyleState(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$InboxStyleState);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ExpandedChild$Builder setMessagingStyleState(IcdfNotificationManagerOuterClass$MessagingStyleState icdfNotificationManagerOuterClass$MessagingStyleState) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ExpandedChild.-$$Nest$msetMessagingStyleState(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$MessagingStyleState);
        return this;
    }
}
