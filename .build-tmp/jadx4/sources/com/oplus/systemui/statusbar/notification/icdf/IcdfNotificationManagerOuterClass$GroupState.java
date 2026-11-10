package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$GroupState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$GroupStateOrBuilder {
    public static final int CHILDRENINGROUP_FIELD_NUMBER = 3;
    private static final IcdfNotificationManagerOuterClass$GroupState DEFAULT_INSTANCE;
    public static final int GROUPLINE_FIELD_NUMBER = 4;
    public static final int GROUPNAME_FIELD_NUMBER = 1;
    public static final int GROUPSUMMARY_FIELD_NUMBER = 2;
    private static volatile Parser<IcdfNotificationManagerOuterClass$GroupState> PARSER;
    private int bitField0_;
    private BoolValue childrenInGroup_;
    private IcdfNotificationManagerOuterClass.IcdfHybridNotification groupLine_;
    private StringValue groupName_;
    private BoolValue groupSummary_;

    static {
        IcdfNotificationManagerOuterClass$GroupState icdfNotificationManagerOuterClass$GroupState = new IcdfNotificationManagerOuterClass$GroupState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$GroupState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$GroupState.class, icdfNotificationManagerOuterClass$GroupState);
    }

    public static IcdfNotificationManagerOuterClass$GroupState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChildrenInGroup(BoolValue boolValue) {
        boolValue.getClass();
        this.childrenInGroup_ = boolValue;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGroupLine(IcdfNotificationManagerOuterClass.IcdfHybridNotification icdfHybridNotification) {
        icdfHybridNotification.getClass();
        this.groupLine_ = icdfHybridNotification;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGroupName(StringValue stringValue) {
        stringValue.getClass();
        this.groupName_ = stringValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGroupSummary(BoolValue boolValue) {
        boolValue.getClass();
        this.groupSummary_ = boolValue;
        this.bitField0_ |= 2;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$GroupState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"bitField0_", "groupName_", "groupSummary_", "childrenInGroup_", "groupLine_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$GroupState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$GroupState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
    public BoolValue getChildrenInGroup() {
        BoolValue boolValue = this.childrenInGroup_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
    public IcdfNotificationManagerOuterClass.IcdfHybridNotification getGroupLine() {
        IcdfNotificationManagerOuterClass.IcdfHybridNotification icdfHybridNotification = this.groupLine_;
        return icdfHybridNotification == null ? IcdfNotificationManagerOuterClass.IcdfHybridNotification.getDefaultInstance() : icdfHybridNotification;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
    public StringValue getGroupName() {
        StringValue stringValue = this.groupName_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
    public BoolValue getGroupSummary() {
        BoolValue boolValue = this.groupSummary_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$GroupStateOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
        public BoolValue getChildrenInGroup() {
            return ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).getChildrenInGroup();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
        public IcdfNotificationManagerOuterClass.IcdfHybridNotification getGroupLine() {
            return ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).getGroupLine();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
        public StringValue getGroupName() {
            return ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).getGroupName();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupStateOrBuilder
        public BoolValue getGroupSummary() {
            return ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).getGroupSummary();
        }

        public Builder setChildrenInGroup(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setChildrenInGroup(builder.build());
            return this;
        }

        public Builder setGroupLine(IcdfNotificationManagerOuterClass$IcdfHybridNotification$Builder icdfNotificationManagerOuterClass$IcdfHybridNotification$Builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupLine(icdfNotificationManagerOuterClass$IcdfHybridNotification$Builder.build());
            return this;
        }

        public Builder setGroupName(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupName(builder.build());
            return this;
        }

        public Builder setGroupSummary(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupSummary(builder.build());
            return this;
        }

        public Builder setChildrenInGroup(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setChildrenInGroup(boolValue);
            return this;
        }

        public Builder setGroupLine(IcdfNotificationManagerOuterClass.IcdfHybridNotification icdfHybridNotification) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupLine(icdfHybridNotification);
            return this;
        }

        public Builder setGroupName(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupName(stringValue);
            return this;
        }

        public Builder setGroupSummary(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$GroupState) ((GeneratedMessageLite.Builder) this).instance).setGroupSummary(boolValue);
            return this;
        }
    }
}
