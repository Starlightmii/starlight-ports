package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$ContractedChild$Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$ContractedChildOrBuilder {
    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ContractedChildOrBuilder
    public StringValue getContractedContent() {
        return ((GeneratedMessageLite.Builder) this).instance.getContractedContent();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ContractedChildOrBuilder
    public StringValue getContractedTitle() {
        return ((GeneratedMessageLite.Builder) this).instance.getContractedTitle();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ContractedChildOrBuilder
    public BytesValue getLargeIcon() {
        return ((GeneratedMessageLite.Builder) this).instance.getLargeIcon();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ContractedChildOrBuilder
    public IcdfNotificationManagerOuterClass$ProgressState getProgress() {
        return ((GeneratedMessageLite.Builder) this).instance.getProgress();
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setContractedContent(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetContractedContent(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setContractedTitle(StringValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetContractedTitle(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setLargeIcon(BytesValue.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetLargeIcon(((GeneratedMessageLite.Builder) this).instance, builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setProgress(IcdfNotificationManagerOuterClass$ProgressState.Builder builder) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetProgress(((GeneratedMessageLite.Builder) this).instance, (IcdfNotificationManagerOuterClass$ProgressState) builder.build());
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setContractedContent(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetContractedContent(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setContractedTitle(StringValue stringValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetContractedTitle(((GeneratedMessageLite.Builder) this).instance, stringValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setLargeIcon(BytesValue bytesValue) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetLargeIcon(((GeneratedMessageLite.Builder) this).instance, bytesValue);
        return this;
    }

    public IcdfNotificationManagerOuterClass$ContractedChild$Builder setProgress(IcdfNotificationManagerOuterClass$ProgressState icdfNotificationManagerOuterClass$ProgressState) {
        copyOnWrite();
        IcdfNotificationManagerOuterClass.ContractedChild.-$$Nest$msetProgress(((GeneratedMessageLite.Builder) this).instance, icdfNotificationManagerOuterClass$ProgressState);
        return this;
    }
}
