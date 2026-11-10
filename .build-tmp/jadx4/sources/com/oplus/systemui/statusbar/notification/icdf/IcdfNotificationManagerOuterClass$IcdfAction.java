package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfAction extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$IcdfActionOrBuilder {
    public static final int ACTIONID_FIELD_NUMBER = 2;
    private static final IcdfNotificationManagerOuterClass$IcdfAction DEFAULT_INSTANCE;
    public static final int ICON_FIELD_NUMBER = 4;
    public static final int LABEL_FIELD_NUMBER = 3;
    public static final int NOTIFICATIONKEY_FIELD_NUMBER = 1;
    private static volatile Parser<IcdfNotificationManagerOuterClass$IcdfAction> PARSER = null;
    public static final int SMARTREPLY_FIELD_NUMBER = 5;
    private Int32Value actionId_;
    private int bitField0_;
    private BytesValue icon_;
    private StringValue label_;
    private StringValue notificationKey_;
    private BoolValue smartReply_;

    static {
        IcdfNotificationManagerOuterClass$IcdfAction icdfNotificationManagerOuterClass$IcdfAction = new IcdfNotificationManagerOuterClass$IcdfAction();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$IcdfAction;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$IcdfAction.class, icdfNotificationManagerOuterClass$IcdfAction);
    }

    public static IcdfNotificationManagerOuterClass$IcdfAction getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActionId(Int32Value int32Value) {
        int32Value.getClass();
        this.actionId_ = int32Value;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIcon(BytesValue bytesValue) {
        bytesValue.getClass();
        this.icon_ = bytesValue;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabel(StringValue stringValue) {
        stringValue.getClass();
        this.label_ = stringValue;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotificationKey(StringValue stringValue) {
        stringValue.getClass();
        this.notificationKey_ = stringValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmartReply(BoolValue boolValue) {
        boolValue.getClass();
        this.smartReply_ = boolValue;
        this.bitField0_ |= 16;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$IcdfAction();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004", new Object[]{"bitField0_", "notificationKey_", "actionId_", "label_", "icon_", "smartReply_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$IcdfAction> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$IcdfAction.class) {
                    try {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            default:
                throw null;
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
    public Int32Value getActionId() {
        Int32Value int32Value = this.actionId_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
    public BytesValue getIcon() {
        BytesValue bytesValue = this.icon_;
        return bytesValue == null ? BytesValue.getDefaultInstance() : bytesValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
    public StringValue getLabel() {
        StringValue stringValue = this.label_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
    public StringValue getNotificationKey() {
        StringValue stringValue = this.notificationKey_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
    public BoolValue getSmartReply() {
        BoolValue boolValue = this.smartReply_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfActionOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
        public Int32Value getActionId() {
            return ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).getActionId();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
        public BytesValue getIcon() {
            return ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).getIcon();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
        public StringValue getLabel() {
            return ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).getLabel();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
        public StringValue getNotificationKey() {
            return ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).getNotificationKey();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfActionOrBuilder
        public BoolValue getSmartReply() {
            return ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).getSmartReply();
        }

        public Builder setActionId(Int32Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setActionId(builder.build());
            return this;
        }

        public Builder setIcon(BytesValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setIcon(builder.build());
            return this;
        }

        public Builder setLabel(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setLabel(builder.build());
            return this;
        }

        public Builder setNotificationKey(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setNotificationKey(builder.build());
            return this;
        }

        public Builder setSmartReply(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setSmartReply(builder.build());
            return this;
        }

        public Builder setActionId(Int32Value int32Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setActionId(int32Value);
            return this;
        }

        public Builder setIcon(BytesValue bytesValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setIcon(bytesValue);
            return this;
        }

        public Builder setLabel(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setLabel(stringValue);
            return this;
        }

        public Builder setNotificationKey(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setNotificationKey(stringValue);
            return this;
        }

        public Builder setSmartReply(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfAction) ((GeneratedMessageLite.Builder) this).instance).setSmartReply(boolValue);
            return this;
        }
    }
}
