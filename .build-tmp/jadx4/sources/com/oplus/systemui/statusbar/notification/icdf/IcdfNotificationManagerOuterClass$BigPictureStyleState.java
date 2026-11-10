package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BytesValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$BigPictureStyleState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder {
    public static final int BIGLARGEICON_FIELD_NUMBER = 3;
    public static final int BIGPICTURE_FIELD_NUMBER = 4;
    private static final IcdfNotificationManagerOuterClass$BigPictureStyleState DEFAULT_INSTANCE;
    public static final int EXPANDEDSUMMARYTEXT_FIELD_NUMBER = 2;
    public static final int EXPANDEDTITLE_FIELD_NUMBER = 1;
    private static volatile Parser<IcdfNotificationManagerOuterClass$BigPictureStyleState> PARSER;
    private BytesValue bigLargeIcon_;
    private BytesValue bigPicture_;
    private int bitField0_;
    private StringValue expandedSummaryText_;
    private StringValue expandedTitle_;

    static {
        IcdfNotificationManagerOuterClass$BigPictureStyleState icdfNotificationManagerOuterClass$BigPictureStyleState = new IcdfNotificationManagerOuterClass$BigPictureStyleState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$BigPictureStyleState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$BigPictureStyleState.class, icdfNotificationManagerOuterClass$BigPictureStyleState);
    }

    public static IcdfNotificationManagerOuterClass$BigPictureStyleState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBigLargeIcon(BytesValue bytesValue) {
        bytesValue.getClass();
        this.bigLargeIcon_ = bytesValue;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBigPicture(BytesValue bytesValue) {
        bytesValue.getClass();
        this.bigPicture_ = bytesValue;
        this.bitField0_ |= 8;
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
                return new IcdfNotificationManagerOuterClass$BigPictureStyleState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"bitField0_", "expandedTitle_", "expandedSummaryText_", "bigLargeIcon_", "bigPicture_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$BigPictureStyleState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$BigPictureStyleState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
    public BytesValue getBigLargeIcon() {
        BytesValue bytesValue = this.bigLargeIcon_;
        return bytesValue == null ? BytesValue.getDefaultInstance() : bytesValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
    public BytesValue getBigPicture() {
        BytesValue bytesValue = this.bigPicture_;
        return bytesValue == null ? BytesValue.getDefaultInstance() : bytesValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
    public StringValue getExpandedSummaryText() {
        StringValue stringValue = this.expandedSummaryText_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
    public StringValue getExpandedTitle() {
        StringValue stringValue = this.expandedTitle_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
        public BytesValue getBigLargeIcon() {
            return ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).getBigLargeIcon();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
        public BytesValue getBigPicture() {
            return ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).getBigPicture();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
        public StringValue getExpandedSummaryText() {
            return ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedSummaryText();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleStateOrBuilder
        public StringValue getExpandedTitle() {
            return ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedTitle();
        }

        public Builder setBigLargeIcon(BytesValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setBigLargeIcon(builder.build());
            return this;
        }

        public Builder setBigPicture(BytesValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setBigPicture(builder.build());
            return this;
        }

        public Builder setExpandedSummaryText(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(builder.build());
            return this;
        }

        public Builder setExpandedTitle(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(builder.build());
            return this;
        }

        public Builder setBigLargeIcon(BytesValue bytesValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setBigLargeIcon(bytesValue);
            return this;
        }

        public Builder setBigPicture(BytesValue bytesValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setBigPicture(bytesValue);
            return this;
        }

        public Builder setExpandedSummaryText(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(stringValue);
            return this;
        }

        public Builder setExpandedTitle(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$BigPictureStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(stringValue);
            return this;
        }
    }
}
