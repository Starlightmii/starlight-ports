package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;
import com.google.protobuf.StringValueOrBuilder;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$InboxStyleState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder {
    private static final IcdfNotificationManagerOuterClass$InboxStyleState DEFAULT_INSTANCE;
    public static final int EXPANDEDSUMMARYTEXT_FIELD_NUMBER = 2;
    public static final int EXPANDEDTITLE_FIELD_NUMBER = 1;
    public static final int INBOXLINES_FIELD_NUMBER = 3;
    private static volatile Parser<IcdfNotificationManagerOuterClass$InboxStyleState> PARSER;
    private int bitField0_;
    private StringValue expandedSummaryText_;
    private StringValue expandedTitle_;
    private Internal.ProtobufList<StringValue> inboxLines_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: -$$Nest$maddInboxLines, reason: not valid java name */
    public static void m430$$Nest$maddInboxLines(IcdfNotificationManagerOuterClass$InboxStyleState icdfNotificationManagerOuterClass$InboxStyleState, StringValue stringValue) {
        icdfNotificationManagerOuterClass$InboxStyleState.getClass();
        stringValue.getClass();
        Internal.ProtobufList<StringValue> protobufList = icdfNotificationManagerOuterClass$InboxStyleState.inboxLines_;
        if (!protobufList.isModifiable()) {
            icdfNotificationManagerOuterClass$InboxStyleState.inboxLines_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        icdfNotificationManagerOuterClass$InboxStyleState.inboxLines_.add(stringValue);
    }

    static {
        IcdfNotificationManagerOuterClass$InboxStyleState icdfNotificationManagerOuterClass$InboxStyleState = new IcdfNotificationManagerOuterClass$InboxStyleState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$InboxStyleState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$InboxStyleState.class, icdfNotificationManagerOuterClass$InboxStyleState);
    }

    public static IcdfNotificationManagerOuterClass$InboxStyleState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
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
                return new IcdfNotificationManagerOuterClass$InboxStyleState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b", new Object[]{"bitField0_", "expandedTitle_", "expandedSummaryText_", "inboxLines_", StringValue.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$InboxStyleState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$InboxStyleState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
    public StringValue getExpandedSummaryText() {
        StringValue stringValue = this.expandedSummaryText_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
    public StringValue getExpandedTitle() {
        StringValue stringValue = this.expandedTitle_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    public final StringValue getInboxLines(int i) {
        return (StringValue) this.inboxLines_.get(i);
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
    public int getInboxLinesCount() {
        return this.inboxLines_.size();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
    public List<StringValue> getInboxLinesList() {
        return this.inboxLines_;
    }

    public List<? extends StringValueOrBuilder> getInboxLinesOrBuilderList() {
        return this.inboxLines_;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder {
        public final void addInboxLines(StringValue stringValue) {
            copyOnWrite();
            IcdfNotificationManagerOuterClass$InboxStyleState.m430$$Nest$maddInboxLines((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance, stringValue);
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
        public StringValue getExpandedSummaryText() {
            return ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedSummaryText();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
        public StringValue getExpandedTitle() {
            return ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).getExpandedTitle();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
        public int getInboxLinesCount() {
            return ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).getInboxLinesCount();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleStateOrBuilder
        public List<StringValue> getInboxLinesList() {
            return Collections.unmodifiableList(((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).getInboxLinesList());
        }

        public Builder setExpandedSummaryText(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(builder.build());
            return this;
        }

        public Builder setExpandedTitle(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(builder.build());
            return this;
        }

        public Builder setExpandedSummaryText(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedSummaryText(stringValue);
            return this;
        }

        public Builder setExpandedTitle(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$InboxStyleState) ((GeneratedMessageLite.Builder) this).instance).setExpandedTitle(stringValue);
            return this;
        }
    }
}
