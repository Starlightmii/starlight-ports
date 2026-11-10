package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Parser;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$ProgressState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$ProgressStateOrBuilder {
    private static final IcdfNotificationManagerOuterClass$ProgressState DEFAULT_INSTANCE;
    public static final int INDETERMINATE_FIELD_NUMBER = 1;
    public static final int MAX_FIELD_NUMBER = 3;
    private static volatile Parser<IcdfNotificationManagerOuterClass$ProgressState> PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int bitField0_;
    private BoolValue indeterminate_;
    private Int32Value max_;
    private Int32Value value_;

    static {
        IcdfNotificationManagerOuterClass$ProgressState icdfNotificationManagerOuterClass$ProgressState = new IcdfNotificationManagerOuterClass$ProgressState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$ProgressState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$ProgressState.class, icdfNotificationManagerOuterClass$ProgressState);
    }

    public static IcdfNotificationManagerOuterClass$ProgressState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIndeterminate(BoolValue boolValue) {
        boolValue.getClass();
        this.indeterminate_ = boolValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMax(Int32Value int32Value) {
        int32Value.getClass();
        this.max_ = int32Value;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValue(Int32Value int32Value) {
        int32Value.getClass();
        this.value_ = int32Value;
        this.bitField0_ |= 2;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$ProgressState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "indeterminate_", "value_", "max_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$ProgressState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$ProgressState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
    public BoolValue getIndeterminate() {
        BoolValue boolValue = this.indeterminate_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
    public Int32Value getMax() {
        Int32Value int32Value = this.max_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
    public Int32Value getValue() {
        Int32Value int32Value = this.value_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$ProgressStateOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
        public BoolValue getIndeterminate() {
            return ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).getIndeterminate();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
        public Int32Value getMax() {
            return ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).getMax();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressStateOrBuilder
        public Int32Value getValue() {
            return ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).getValue();
        }

        public Builder setIndeterminate(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setIndeterminate(builder.build());
            return this;
        }

        public Builder setMax(Int32Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setMax(builder.build());
            return this;
        }

        public Builder setValue(Int32Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setValue(builder.build());
            return this;
        }

        public Builder setIndeterminate(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setIndeterminate(boolValue);
            return this;
        }

        public Builder setMax(Int32Value int32Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setMax(int32Value);
            return this;
        }

        public Builder setValue(Int32Value int32Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$ProgressState) ((GeneratedMessageLite.Builder) this).instance).setValue(int32Value);
            return this;
        }
    }
}
