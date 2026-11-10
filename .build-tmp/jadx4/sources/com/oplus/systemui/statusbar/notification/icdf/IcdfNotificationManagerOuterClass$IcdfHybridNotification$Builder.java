package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfHybridNotificationOrBuilder {
    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfHybridNotificationOrBuilder
    public StringValue getText() {
        return ((GeneratedMessageLite.Builder) this).instance.getText();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfHybridNotificationOrBuilder
    public StringValue getTitle() {
        return ((GeneratedMessageLite.Builder) this).instance.getTitle();
    }

    public IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder setText(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfHybridNotification.-$$Nest$msetText(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder setTitle(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfHybridNotification.-$$Nest$msetTitle(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder setText(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfHybridNotification.-$$Nest$msetText(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder setTitle(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfHybridNotification.-$$Nest$msetTitle(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }
}
