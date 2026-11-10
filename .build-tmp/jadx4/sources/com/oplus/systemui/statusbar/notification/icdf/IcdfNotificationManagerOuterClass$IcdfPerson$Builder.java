package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfPerson$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfPersonOrBuilder {
    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfPersonOrBuilder
    public BytesValue getIcon() {
        return ((GeneratedMessageLite.Builder) this).instance.getIcon();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfPersonOrBuilder
    public StringValue getName() {
        return ((GeneratedMessageLite.Builder) this).instance.getName();
    }

    public IcdfNotificationManagerOuterClass$IcdfPerson$Builder setIcon(BytesValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfPerson.-$$Nest$msetIcon(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfPerson$Builder setName(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfPerson.-$$Nest$msetName(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfPerson$Builder setIcon(BytesValue bytesValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfPerson.-$$Nest$msetIcon(((GeneratedMessageLite.Builder) this).instance, bytesValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfPerson$Builder setName(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfPerson.-$$Nest$msetName(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }
}
