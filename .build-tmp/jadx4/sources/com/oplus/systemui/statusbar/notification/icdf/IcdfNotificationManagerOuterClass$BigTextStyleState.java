package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$BigTextStyleState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder {
    public static final int BITTEXT_FIELD_NUMBER = 3;
    private static final IcdfNotificationManagerOuterClass$BigTextStyleState DEFAULT_INSTANCE;
    public static final int EXPANDEDSUMMARYTEXT_FIELD_NUMBER = 2;
    public static final int EXPANDEDTITLE_FIELD_NUMBER = 1;
    private static volatile Parser<IcdfNotificationManagerOuterClass$BigTextStyleState> PARSER;
    private int bitField0_;
    private StringValue bitText_;
    private StringValue expandedSummaryText_;
    private StringValue expandedTitle_;

    static {
        IcdfNotificationManagerOuterClass$BigTextStyleState icdfNotificationManagerOuterClass$BigTextStyleState = new IcdfNotificationManagerOuterClass$BigTextStyleState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$BigTextStyleState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$BigTextStyleState.class, icdfNotificationManagerOuterClass$BigTextStyleState);
    }

    public static IcdfNotificationManagerOuterClass$BigTextStyleState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBitText(StringValue stringValue) {
        stringValue.getClass();
        this.bitText_ = stringValue;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpandedSummaryText(StringValue stringValue) {
        stringValue.getClass();
        this.expandedSummaryText_ = stringValue;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpandedTitle(StringValue stringValue) {
        stringValue.getClass();
        this.expandedTitle_ = stringValue;
        this.bitField0_ |= 1;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$BigTextStyleState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "expandedTitle_", "expandedSummaryText_", "bitText_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$BigTextStyleState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$BigTextStyleState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
    public StringValue getBitText() {
        StringValue stringValue = this.bitText_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
    public StringValue getExpandedSummaryText() {
        StringValue stringValue = this.expandedSummaryText_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
    public StringValue getExpandedTitle() {
        StringValue stringValue = this.expandedTitle_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
        public StringValue getBitText() {
            return ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).getBitText();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
        public StringValue getExpandedSummaryText() {
            return ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedSummaryText();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleStateOrBuilder
        public StringValue getExpandedTitle() {
            return ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedTitle();
        }

        public Builder setBitText(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setBitText(builder.build());
            return this;
        }

        public Builder setExpandedSummaryText(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(builder.build());
            return this;
        }

        public Builder setExpandedTitle(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(builder.build());
            return this;
        }

        public Builder setBitText(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setBitText(stringValue);
            return this;
        }

        public Builder setExpandedSummaryText(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(stringValue);
            return this;
        }

        public Builder setExpandedTitle(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigTextStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(stringValue);
            return this;
        }
    }
}
