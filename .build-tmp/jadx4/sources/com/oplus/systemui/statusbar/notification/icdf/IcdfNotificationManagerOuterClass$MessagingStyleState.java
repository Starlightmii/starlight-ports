package com.oplus.systemui.statusbar.notification.icdf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.StringValue;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationManagerOuterClass$MessagingStyleState extends GeneratedMessageLite implements IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder {
    public static final int CONVERSATIONTITLE_FIELD_NUMBER = 1;
    private static final IcdfNotificationManagerOuterClass$MessagingStyleState DEFAULT_INSTANCE;
    public static final int HISTORICMESSAGES_FIELD_NUMBER = 3;
    public static final int MESSAGES_FIELD_NUMBER = 2;
    private static volatile Parser<IcdfNotificationManagerOuterClass$MessagingStyleState> PARSER = null;
    public static final int PERSON_FIELD_NUMBER = 4;
    private int bitField0_;
    private StringValue conversationTitle_;
    private IcdfNotificationManagerOuterClass.IcdfPerson person_;
    private Internal.ProtobufList<IcdfNotificationManagerOuterClass.IcdfMessage> messages_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<IcdfNotificationManagerOuterClass.IcdfMessage> historicMessages_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: -$$Nest$maddMessages, reason: not valid java name */
    public static void m434$$Nest$maddMessages(IcdfNotificationManagerOuterClass$MessagingStyleState icdfNotificationManagerOuterClass$MessagingStyleState, IcdfNotificationManagerOuterClass.IcdfMessage icdfMessage) {
        icdfNotificationManagerOuterClass$MessagingStyleState.getClass();
        icdfMessage.getClass();
        Internal.ProtobufList<IcdfNotificationManagerOuterClass.IcdfMessage> protobufList = icdfNotificationManagerOuterClass$MessagingStyleState.messages_;
        if (!protobufList.isModifiable()) {
            icdfNotificationManagerOuterClass$MessagingStyleState.messages_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        icdfNotificationManagerOuterClass$MessagingStyleState.messages_.add(icdfMessage);
    }

    static {
        IcdfNotificationManagerOuterClass$MessagingStyleState icdfNotificationManagerOuterClass$MessagingStyleState = new IcdfNotificationManagerOuterClass$MessagingStyleState();
        DEFAULT_INSTANCE = icdfNotificationManagerOuterClass$MessagingStyleState;
        GeneratedMessageLite.registerDefaultInstance(IcdfNotificationManagerOuterClass$MessagingStyleState.class, icdfNotificationManagerOuterClass$MessagingStyleState);
    }

    public static IcdfNotificationManagerOuterClass$MessagingStyleState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConversationTitle(StringValue stringValue) {
        stringValue.getClass();
        this.conversationTitle_ = stringValue;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPerson(IcdfNotificationManagerOuterClass.IcdfPerson icdfPerson) {
        icdfPerson.getClass();
        this.person_ = icdfPerson;
        this.bitField0_ |= 2;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        GeneratedMessageLite.DefaultInstanceBasedParser defaultInstanceBasedParser;
        switch (IcdfNotificationManagerOuterClass.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new IcdfNotificationManagerOuterClass$MessagingStyleState();
            case 2:
                return new Builder(DEFAULT_INSTANCE);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဉ\u0000\u0002\u001b\u0003\u001b\u0004ဉ\u0001", new Object[]{"bitField0_", "conversationTitle_", "messages_", IcdfNotificationManagerOuterClass.IcdfMessage.class, "historicMessages_", IcdfNotificationManagerOuterClass.IcdfMessage.class, "person_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IcdfNotificationManagerOuterClass$MessagingStyleState> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (IcdfNotificationManagerOuterClass$MessagingStyleState.class) {
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

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public StringValue getConversationTitle() {
        StringValue stringValue = this.conversationTitle_;
        return stringValue == null ? StringValue.getDefaultInstance() : stringValue;
    }

    public final IcdfNotificationManagerOuterClass.IcdfMessage getHistoricMessages(int i) {
        return (IcdfNotificationManagerOuterClass.IcdfMessage) this.historicMessages_.get(i);
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public int getHistoricMessagesCount() {
        return this.historicMessages_.size();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public List<IcdfNotificationManagerOuterClass.IcdfMessage> getHistoricMessagesList() {
        return this.historicMessages_;
    }

    public List<? extends IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder> getHistoricMessagesOrBuilderList() {
        return this.historicMessages_;
    }

    public final IcdfNotificationManagerOuterClass.IcdfMessage getMessages(int i) {
        return (IcdfNotificationManagerOuterClass.IcdfMessage) this.messages_.get(i);
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public int getMessagesCount() {
        return this.messages_.size();
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public List<IcdfNotificationManagerOuterClass.IcdfMessage> getMessagesList() {
        return this.messages_;
    }

    public List<? extends IcdfNotificationManagerOuterClass$IcdfMessageOrBuilder> getMessagesOrBuilderList() {
        return this.messages_;
    }

    @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
    public IcdfNotificationManagerOuterClass.IcdfPerson getPerson() {
        IcdfNotificationManagerOuterClass.IcdfPerson icdfPerson = this.person_;
        return icdfPerson == null ? IcdfNotificationManagerOuterClass.IcdfPerson.getDefaultInstance() : icdfPerson;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder extends GeneratedMessageLite.Builder implements IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder {
        public final void addMessages(IcdfNotificationManagerOuterClass.IcdfMessage icdfMessage) {
            copyOnWrite();
            IcdfNotificationManagerOuterClass$MessagingStyleState.m434$$Nest$maddMessages((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance, icdfMessage);
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public StringValue getConversationTitle() {
            return ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getConversationTitle();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public int getHistoricMessagesCount() {
            return ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getHistoricMessagesCount();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public List<IcdfNotificationManagerOuterClass.IcdfMessage> getHistoricMessagesList() {
            return Collections.unmodifiableList(((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getHistoricMessagesList());
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public int getMessagesCount() {
            return ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getMessagesCount();
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public List<IcdfNotificationManagerOuterClass.IcdfMessage> getMessagesList() {
            return Collections.unmodifiableList(((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getMessagesList());
        }

        @Override // com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleStateOrBuilder
        public IcdfNotificationManagerOuterClass.IcdfPerson getPerson() {
            return ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).getPerson();
        }

        public Builder setConversationTitle(StringValue.Builder builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).setConversationTitle(builder.build());
            return this;
        }

        public Builder setPerson(IcdfNotificationManagerOuterClass$IcdfPerson$Builder icdfNotificationManagerOuterClass$IcdfPerson$Builder) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).setPerson(icdfNotificationManagerOuterClass$IcdfPerson$Builder.build());
            return this;
        }

        public Builder setConversationTitle(StringValue stringValue) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).setConversationTitle(stringValue);
            return this;
        }

        public Builder setPerson(IcdfNotificationManagerOuterClass.IcdfPerson icdfPerson) {
            copyOnWrite();
            ((IcdfNotificationManagerOuterClass$MessagingStyleState) ((GeneratedMessageLite.Builder) this).instance).setPerson(icdfPerson);
            return this;
        }
    }
}
