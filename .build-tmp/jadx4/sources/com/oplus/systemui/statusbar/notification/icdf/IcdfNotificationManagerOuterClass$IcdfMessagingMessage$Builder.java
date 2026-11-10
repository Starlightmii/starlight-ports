package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfMessagingMessageOrBuilder {
    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessagingMessageOrBuilder
    public BytesValue getDrawable() {
        return ((GeneratedMessageLite.Builder) this).instance.getDrawable();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessagingMessageOrBuilder
    public StringValue getText() {
        return ((GeneratedMessageLite.Builder) this).instance.getText();
    }

    public IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder setDrawable(BytesValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessagingMessage.-$$Nest$msetDrawable(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder setText(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessagingMessage.-$$Nest$msetText(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder setDrawable(BytesValue bytesValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessagingMessage.-$$Nest$msetDrawable(((GeneratedMessageLite.Builder) this).instance, bytesValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder setText(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.IcdfMessagingMessage.-$$Nest$msetText(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }
}
