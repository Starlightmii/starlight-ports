package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;
import com.oplus.transition.OplusAnimationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$IcdfNotification extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder {
    public static final int BANNERSHOW_FIELD_NUMBER = 12;
    public static final int CONTRACTEDCHILD_FIELD_NUMBER = 6;
    private static final IcdfNotificationManagerOuterClass$IcdfNotification DEFAULT_INSTANCE;
    public static final int EXPANDEDCHILD_FIELD_NUMBER = 7;
    public static final int GROUPSTATE_FIELD_NUMBER = 8;
    public static final int HEADERVIEW_FIELD_NUMBER = 5;
    public static final int ISCUSTOMCONTENT_FIELD_NUMBER = 9;
    public static final int ISVERIFICATION_FIELD_NUMBER = 10;
    public static final int KEY_FIELD_NUMBER = 4;
    public static final int NOTIFICATIONLEVEL_FIELD_NUMBER = 11;
    public static final int ONGOING_FIELD_NUMBER = 3;
    private static volatile Parser<IcdfNotificationManagerOuterClass$IcdfNotification> PARSER = null;
    public static final int PINNED_FIELD_NUMBER = 2;
    public static final int PKGNAME_FIELD_NUMBER = 1;
    public static final int USERIDENTIFIER_FIELD_NUMBER = 13;
    private BoolValue bannerShow_;
    private int bitField0_;
    private IcdfNotificationManagerOuterClass.ContractedChild contractedChild_;
    private IcdfNotificationManagerOuterClass.ExpandedChild expandedChild_;
    private IcdfNotificationManagerOuterClass$GroupState groupState_;
    private IcdfNotificationManagerOuterClass$HeaderView headerView_;
    private BoolValue isCustomContent_;
    private BoolValue isVerification_;
    private StringValue key_;
    private Int32Value notificationLevel_;
    private BoolValue onGoing_;
    private BoolValue pinned_;
    private StringValue pkgName_;
    private Int32Value userIdentifier_;

    static {
        IcdfNotificationManagerOuterClass$IcdfNotification icdfNotificationManagerOuterClass$IcdfNotification = new IcdfNotificationManagerOuterClass$IcdfNotification();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$IcdfNotification;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$IcdfNotification.class, icdfNotificationManagerOuterClass$IcdfNotification);
    }

    public static IcdfNotificationManagerOuterClass$IcdfNotification getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerShow(BoolValue boolValue) {
        boolValue.getClass();
        this.bannerShow_ = boolValue;
        this.bitField0_ |= OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContractedChild(IcdfNotificationManagerOuterClass.ContractedChild contractedChild) {
        contractedChild.getClass();
        this.contractedChild_ = contractedChild;
        this.bitField0_ |= 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpandedChild(IcdfNotificationManagerOuterClass.ExpandedChild expandedChild) {
        expandedChild.getClass();
        this.expandedChild_ = expandedChild;
        this.bitField0_ |= 64;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGroupState(IcdfNotificationManagerOuterClass$GroupState icdfNotificationManagerOuterClass$GroupState) {
        icdfNotificationManagerOuterClass$GroupState.getClass();
        this.groupState_ = icdfNotificationManagerOuterClass$GroupState;
        this.bitField0_ |= 128;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderView(IcdfNotificationManagerOuterClass$HeaderView icdfNotificationManagerOuterClass$HeaderView) {
        icdfNotificationManagerOuterClass$HeaderView.getClass();
        this.headerView_ = icdfNotificationManagerOuterClass$HeaderView;
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsCustomContent(BoolValue boolValue) {
        boolValue.getClass();
        this.isCustomContent_ = boolValue;
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsVerification(BoolValue boolValue) {
        boolValue.getClass();
        this.isVerification_ = boolValue;
        this.bitField0_ |= OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setKey(StringValue stringValue) {
        stringValue.getClass();
        this.key_ = stringValue;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotificationLevel(Int32Value int32Value) {
        int32Value.getClass();
        this.notificationLevel_ = int32Value;
        this.bitField0_ |= OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnGoing(BoolValue boolValue) {
        boolValue.getClass();
        this.onGoing_ = boolValue;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPinned(BoolValue boolValue) {
        boolValue.getClass();
        this.pinned_ = boolValue;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPkgName(StringValue stringValue) {
        stringValue.getClass();
        this.pkgName_ = stringValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserIdentifier(Int32Value int32Value) {
        int32Value.getClass();
        this.userIdentifier_ = int32Value;
        this.bitField0_ |= OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$IcdfNotification();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\r\u0000\u0001\u0001\r\r\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f", new Object[]{"bitField0_", "pkgName_", "pinned_", "onGoing_", "key_", "headerView_", "contractedChild_", "expandedChild_", "groupState_", "isCustomContent_", "isVerification_", "notificationLevel_", "bannerShow_", "userIdentifier_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$IcdfNotification> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$IcdfNotification.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public BoolValue getBannerShow() {
        BoolValue boolValue = this.bannerShow_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public IcdfNotificationManagerOuterClass.ContractedChild getContractedChild() {
        IcdfNotificationManagerOuterClass.ContractedChild contractedChild = this.contractedChild_;
        return contractedChild == null ? IcdfNotificationManagerOuterClass.ContractedChild.getDefaultInstance() : contractedChild;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public IcdfNotificationManagerOuterClass.ExpandedChild getExpandedChild() {
        IcdfNotificationManagerOuterClass.ExpandedChild expandedChild = this.expandedChild_;
        return expandedChild == null ? IcdfNotificationManagerOuterClass.ExpandedChild.getDefaultInstance() : expandedChild;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public IcdfNotificationManagerOuterClass$GroupState getGroupState() {
        IcdfNotificationManagerOuterClass$GroupState icdfNotificationManagerOuterClass$GroupState = this.groupState_;
        return icdfNotificationManagerOuterClass$GroupState == null ? IcdfNotificationManagerOuterClass$GroupState.getDefaultInstance() : icdfNotificationManagerOuterClass$GroupState;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public IcdfNotificationManagerOuterClass$HeaderView getHeaderView() {
        IcdfNotificationManagerOuterClass$HeaderView icdfNotificationManagerOuterClass$HeaderView = this.headerView_;
        return icdfNotificationManagerOuterClass$HeaderView == null ? IcdfNotificationManagerOuterClass$HeaderView.getDefaultInstance() : icdfNotificationManagerOuterClass$HeaderView;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public BoolValue getIsCustomContent() {
        BoolValue boolValue = this.isCustomContent_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public BoolValue getIsVerification() {
        BoolValue boolValue = this.isVerification_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public StringValue getKey() {
        StringValue stringValue = this.key_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public Int32Value getNotificationLevel() {
        Int32Value int32Value = this.notificationLevel_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public BoolValue getOnGoing() {
        BoolValue boolValue = this.onGoing_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public BoolValue getPinned() {
        BoolValue boolValue = this.pinned_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public StringValue getPkgName() {
        StringValue stringValue = this.pkgName_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
    public Int32Value getUserIdentifier() {
        Int32Value int32Value = this.userIdentifier_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder {
        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public BoolValue getBannerShow() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getBannerShow();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public IcdfNotificationManagerOuterClass.ContractedChild getContractedChild() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getContractedChild();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public IcdfNotificationManagerOuterClass.ExpandedChild getExpandedChild() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getExpandedChild();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public IcdfNotificationManagerOuterClass$GroupState getGroupState() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getGroupState();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public IcdfNotificationManagerOuterClass$HeaderView getHeaderView() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getHeaderView();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public BoolValue getIsCustomContent() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getIsCustomContent();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public BoolValue getIsVerification() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getIsVerification();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public StringValue getKey() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getKey();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public Int32Value getNotificationLevel() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getNotificationLevel();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public BoolValue getOnGoing() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getOnGoing();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public BoolValue getPinned() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getPinned();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public StringValue getPkgName() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getPkgName();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotificationOrBuilder
        public Int32Value getUserIdentifier() {
            return ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).getUserIdentifier();
        }

        public Builder setBannerShow(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setBannerShow(builder.build());
            return this;
        }

        public Builder setContractedChild(IcdfNotificationManagerOuterClass$ContractedChild$Builder icdfNotificationManagerOuterClass$ContractedChild$Builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setContractedChild(icdfNotificationManagerOuterClass$ContractedChild$Builder.build());
            return this;
        }

        public Builder setExpandedChild(IcdfNotificationManagerOuterClass$ExpandedChild$Builder icdfNotificationManagerOuterClass$ExpandedChild$Builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setExpandedChild(icdfNotificationManagerOuterClass$ExpandedChild$Builder.build());
            return this;
        }

        public Builder setGroupState(IcdfNotificationManagerOuterClass$GroupState.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setGroupState((IcdfNotificationManagerOuterClass$GroupState) builder.build());
            return this;
        }

        public Builder setHeaderView(IcdfNotificationManagerOuterClass$HeaderView.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setHeaderView((IcdfNotificationManagerOuterClass$HeaderView) builder.build());
            return this;
        }

        public Builder setIsCustomContent(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setIsCustomContent(builder.build());
            return this;
        }

        public Builder setIsVerification(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setIsVerification(builder.build());
            return this;
        }

        public Builder setKey(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setKey(builder.build());
            return this;
        }

        public Builder setNotificationLevel(Int32Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setNotificationLevel(builder.build());
            return this;
        }

        public Builder setOnGoing(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setOnGoing(builder.build());
            return this;
        }

        public Builder setPinned(BoolValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setPinned(builder.build());
            return this;
        }

        public Builder setPkgName(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setPkgName(builder.build());
            return this;
        }

        public Builder setUserIdentifier(Int32Value.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setUserIdentifier(builder.build());
            return this;
        }

        public Builder setBannerShow(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setBannerShow(boolValue);
            return this;
        }

        public Builder setContractedChild(IcdfNotificationManagerOuterClass.ContractedChild contractedChild) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setContractedChild(contractedChild);
            return this;
        }

        public Builder setExpandedChild(IcdfNotificationManagerOuterClass.ExpandedChild expandedChild) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setExpandedChild(expandedChild);
            return this;
        }

        public Builder setGroupState(IcdfNotificationManagerOuterClass$GroupState icdfNotificationManagerOuterClass$GroupState) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setGroupState(icdfNotificationManagerOuterClass$GroupState);
            return this;
        }

        public Builder setHeaderView(IcdfNotificationManagerOuterClass$HeaderView icdfNotificationManagerOuterClass$HeaderView) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setHeaderView(icdfNotificationManagerOuterClass$HeaderView);
            return this;
        }

        public Builder setIsCustomContent(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setIsCustomContent(boolValue);
            return this;
        }

        public Builder setIsVerification(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setIsVerification(boolValue);
            return this;
        }

        public Builder setKey(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setKey(stringValue);
            return this;
        }

        public Builder setNotificationLevel(Int32Value int32Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setNotificationLevel(int32Value);
            return this;
        }

        public Builder setOnGoing(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setOnGoing(boolValue);
            return this;
        }

        public Builder setPinned(BoolValue boolValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setPinned(boolValue);
            return this;
        }

        public Builder setPkgName(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setPkgName(stringValue);
            return this;
        }

        public Builder setUserIdentifier(Int32Value int32Value) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$IcdfNotification) ((GeneratedMessageLite.Builder) this).instance).setUserIdentifier(int32Value);
            return this;
        }
    }
}
