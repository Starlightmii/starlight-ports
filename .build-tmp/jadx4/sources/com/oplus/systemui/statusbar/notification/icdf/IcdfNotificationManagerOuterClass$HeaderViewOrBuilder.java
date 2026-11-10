package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IcdfNotificationManagerOuterClass$HeaderViewOrBuilder extends MessageLiteOrBuilder {
    StringValue getHeaderText();

    StringValue getHeaderTextSecondary();

    StringValue getLabel();

    BoolValue getShowWhen();

    BytesValue getSmallIcon();

    Int64Value getWhen();
}
