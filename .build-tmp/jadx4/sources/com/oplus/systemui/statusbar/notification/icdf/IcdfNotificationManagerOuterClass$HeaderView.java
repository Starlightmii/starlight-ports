package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$HeaderView extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$HeaderViewOrBuilder {
    private static final IcdfNotificationManagerOuterClass$HeaderView DEFAULT_INSTANCE;
    public static final int HEADERTEXTSECONDARY_FIELD_NUMBER = 5;
    public static final int HEADERTEXT_FIELD_NUMBER = 6;
    public static final int LABEL_FIELD_NUMBER = 1;
    private static volatile Parser<IcdfNotificationManagerOuterClass$HeaderView> PARSER = null;
    public static final int SHOWWHEN_FIELD_NUMBER = 2;
    public static final int SMALLICON_FIELD_NUMBER = 4;
    public static final int WHEN_FIELD_NUMBER = 3;
    private int bitField0_;
    private StringValue headerTextSecondary_;
    private StringValue headerText_;
    private StringValue label_;
    private BoolValue showWhen_;
    private BytesValue smallIcon_;
    private Int64Value when_;

    static {
        IcdfNotificationManagerOuterClass$HeaderView icdfNotificationManagerOuterClass$HeaderView = new IcdfNotificationManagerOuterClass$HeaderView();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$HeaderView;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$HeaderView.class, icdfNotificationManagerOuterClass$HeaderView);
    }

    public static IcdfNotificationManagerOuterClass$HeaderView getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderText(StringValue stringValue) {
        stringValue.getClass();
        this.headerText_ = stringValue;
        this.bitField0_ |= 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderTextSecondary(StringValue stringValue) {
        stringValue.getClass();
        this.headerTextSecondary_ = stringValue;
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabel(StringValue stringValue) {
        stringValue.getClass();
        this.label_ = stringValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowWhen(BoolValue boolValue) {
        boolValue.getClass();
        this.showWhen_ = boolValue;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmallIcon(BytesValue bytesValue) {
        bytesValue.getClass();
        this.smallIcon_ = bytesValue;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWhen(Int64Value int64Value) {
        int64Value.getClass();
        this.when_ = int64Value;
        this.bitField0_ |= 4;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$HeaderView();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005", new Object[]{"bitField0_", "label_", "showWhen_", "when_", "smallIcon_", "headerTextSecondary_", "headerText_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$HeaderView> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$HeaderView.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public StringValue getHeaderText() {
        StringValue stringValue = this.headerText_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public StringValue getHeaderTextSecondary() {
        StringValue stringValue = this.headerTextSecondary_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public StringValue getLabel() {
        StringValue stringValue = this.label_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public BoolValue getShowWhen() {
        BoolValue boolValue = this.showWhen_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public BytesValue getSmallIcon() {
        BytesValue bytesValue = this.smallIcon_;
        return bytesValue == null ? BytesValue.getDefaultInstance() : bytesValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
    public Int64Value getWhen() {
        Int64Value int64Value = this.when_;
        return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$HeaderViewOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public StringValue getHeaderText() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getHeaderText();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public StringValue getHeaderTextSecondary() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getHeaderTextSecondary();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public StringValue getLabel() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getLabel();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public BoolValue getShowWhen() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getShowWhen();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public BytesValue getSmallIcon() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getSmallIcon();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderViewOrBuilder
        public Int64Value getWhen() {
            return ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).getWhen();
        }

        public Builder setHeaderText(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setHeaderText(builder.build());
            return this;
        }

        public Builder setHeaderTextSecondary(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setHeaderTextSecondary(builder.build());
            return this;
        }

        public Builder setLabel(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setLabel(builder.build());
            return this;
        }

        public Builder setShowWhen(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setShowWhen(builder.build());
            return this;
        }

        public Builder setSmallIcon(BytesValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setSmallIcon(builder.build());
            return this;
        }

        public Builder setWhen(Int64Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setWhen(builder.build());
            return this;
        }

        public Builder setHeaderText(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setHeaderText(stringValue);
            return this;
        }

        public Builder setHeaderTextSecondary(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setHeaderTextSecondary(stringValue);
            return this;
        }

        public Builder setLabel(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setLabel(stringValue);
            return this;
        }

        public Builder setShowWhen(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setShowWhen(boolValue);
            return this;
        }

        public Builder setSmallIcon(BytesValue bytesValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setSmallIcon(bytesValue);
            return this;
        }

        public Builder setWhen(Int64Value int64Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$HeaderView) ((GeneratedMessageLite.Builder) this).instance).setWhen(int64Value);
            return this;
        }
    }
}
