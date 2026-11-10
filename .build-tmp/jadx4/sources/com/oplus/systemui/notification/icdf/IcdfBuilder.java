package com.oplus.systemui.notification.icdf;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.internal.widget.MessagingGroup;
import com.android.internal.widget.MessagingImageMessage;
import com.android.internal.widget.MessagingLayout;
import com.android.internal.widget.MessagingLinearLayout;
import com.android.internal.widget.MessagingTextMessage;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.icdf.helper.ICDFNotificationViewUtils;
import com.oplus.systemui.notification.mcs.NotificationDismissibilityProviderExt;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigPictureStyleState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$BigTextStyleState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ContractedChild$Builder;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ExpandedChild$Builder;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$GroupState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$HeaderView;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfAction;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessage$Builder;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$InboxStyleState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$MessagingStyleState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$ProgressState;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfBuilder {
    public static final Companion Companion = new Companion();
    private final IcdfHeadsUpDecision icdfHeadsUpDecision;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public IcdfBuilder(IcdfHeadsUpDecision icdfHeadsUpDecision) {
        this.icdfHeadsUpDecision = icdfHeadsUpDecision;
    }

    public static BoolValue createBoolValue(boolean z) {
        return BoolValue.newBuilder().setValue(z).build();
    }

    public static BytesValue createBytesValue(Drawable drawable) {
        if (drawable == null) {
            return BytesValue.newBuilder().build();
        }
        if (drawable.getIntrinsicHeight() <= 0 || drawable.getIntrinsicWidth() <= 0) {
            return BytesValue.newBuilder().build();
        }
        try {
            byte[] bArrDrawableToByteArrayOptimized = drawableToByteArrayOptimized(drawable);
            return bArrDrawableToByteArrayOptimized == null ? BytesValue.newBuilder().build() : BytesValue.newBuilder().setValue(ByteString.copyFrom(bArrDrawableToByteArrayOptimized)).build();
        } catch (Exception unused) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ICDF Builder-->createBytesValue by drawable fails");
            }
            return BytesValue.newBuilder().build();
        }
    }

    public static Int32Value createInt32Value(int i) {
        return Int32Value.newBuilder().setValue(i).build();
    }

    public static byte[] drawableToByteArrayOptimized(Drawable drawable) {
        Bitmap bitmap;
        int i;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            bitmap = bitmapCreateBitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > 100 || height > 100) {
            int i2 = width / 2;
            int i3 = height / 2;
            i = 1;
            while (i2 / i >= 100 && i3 / i >= 100) {
                i *= 2;
            }
        } else {
            i = 1;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, width / i, height / i, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateScaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public final IcdfNotificationManagerOuterClass$IcdfNotification createIcdfNotification(NotificationEntry notificationEntry, int i, boolean z, boolean z2) {
        ExpandableNotificationRow row;
        NotificationContentView privateLayout;
        BytesValue bytesValueCreateBytesValue;
        ArrayList arrayList;
        ArrayList arrayList2;
        BytesValue bytesValueCreateBytesValue2;
        Context context;
        BytesValue bytesValue;
        Context context2;
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder builderNewBuilder = IcdfNotificationManagerOuterClass$IcdfNotification.newBuilder();
        builderNewBuilder.getClass();
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder pinned = builderNewBuilder.setPkgName(createStringValue(notificationEntry.getSbn().getPackageName())).setPinned(createBoolValue(z));
        CharSequence charSequence = notificationEntry.getSbn().getNotification().extras.getCharSequence("android.template", "default");
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder bannerShow = pinned.setIsCustomContent(createBoolValue(Notification.DecoratedCustomViewStyle.class.getName().equals(charSequence) || Notification.DecoratedMediaCustomViewStyle.class.getName().equals(charSequence) || !(!"default".equals(charSequence) || (row = notificationEntry.getRow()) == null || (privateLayout = row.getPrivateLayout()) == null || privateLayout.getContractedChild() == null || privateLayout.getContractedChild().getId() == 16909714))).setKey(createStringValue(notificationEntry.getKey())).setIsVerification(createBoolValue(notificationEntry.getSbn().getNotification().extras.getBoolean("isVerificationCode"))).setNotificationLevel(createInt32Value(notificationEntry.getImportance())).setBannerShow(createBoolValue(ICDFNotificationViewUtils.canShowBanner(notificationEntry)));
        UserHandle user = notificationEntry.getSbn().getUser();
        bannerShow.setUserIdentifier(createInt32Value(user != null ? user.getIdentifier() : 0)).setOnGoing(createBoolValue(z2));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("is verification code: ", "ICDF Builder-->", "SystemUi--Notification", notificationEntry.getSbn().getNotification().extras.getBoolean("isVerificationCode"));
        }
        IcdfNotificationManagerOuterClass$HeaderView.Builder builderNewBuilder2 = IcdfNotificationManagerOuterClass$HeaderView.newBuilder();
        builderNewBuilder2.setSmallIcon(createBytesValue(ICDFNotificationViewUtils.getAppIconByPkgName(notificationEntry))).setLabel(createStringValue(ICDFNotificationViewUtils.getAppNameByPkgName(notificationEntry))).setHeaderText(createStringValue(ICDFNotificationViewUtils.getHeaderText(notificationEntry))).setShowWhen(createBoolValue(notificationEntry.getSbn().getNotification().showsTime())).setWhen((Int64Value) Int64Value.newBuilder().setValue(notificationEntry.getSbn().getNotification().when).build());
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "ICDF Builder", "createHeaderView nameText = " + builderNewBuilder2.getLabel() + ", headerText = " + builderNewBuilder2.getHeaderText() + ", icon = " + builderNewBuilder2.getSmallIcon(), null, 8);
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder headerView = builderNewBuilder.setHeaderView((IcdfNotificationManagerOuterClass$HeaderView) builderNewBuilder2.build());
        IcdfNotificationManagerOuterClass$ContractedChild$Builder icdfNotificationManagerOuterClass$ContractedChild$BuilderNewBuilder = IcdfNotificationManagerOuterClass.ContractedChild.newBuilder();
        CharSequence charSequence2 = notificationEntry.getSbn().getNotification().extras.getCharSequence("android.title");
        if (charSequence2 != null) {
            icdfNotificationManagerOuterClass$ContractedChild$BuilderNewBuilder.setContractedTitle(createStringValue(charSequence2));
        }
        CharSequence charSequence3 = notificationEntry.getSbn().getNotification().extras.getCharSequence("android.text");
        if (charSequence3 != null) {
            icdfNotificationManagerOuterClass$ContractedChild$BuilderNewBuilder.setContractedContent(createStringValue(charSequence3));
        }
        IcdfNotificationManagerOuterClass$ProgressState.Builder builderNewBuilder3 = IcdfNotificationManagerOuterClass$ProgressState.newBuilder();
        Bundle bundle = notificationEntry.getSbn().getNotification().extras;
        IcdfNotificationManagerOuterClass$ContractedChild$Builder progress = icdfNotificationManagerOuterClass$ContractedChild$BuilderNewBuilder.setProgress((bundle.getInt("android.progressMax") != 0 || bundle.getBoolean("android.progressIndeterminate")) ? (IcdfNotificationManagerOuterClass$ProgressState) builderNewBuilder3.setValue(createInt32Value(bundle.getInt("android.progress"))).setMax(createInt32Value(bundle.getInt("android.progressMax"))).setIndeterminate(createBoolValue(bundle.getBoolean("android.progressIndeterminate"))).build() : (IcdfNotificationManagerOuterClass$ProgressState) builderNewBuilder3.build());
        ExpandableNotificationRow row2 = notificationEntry.getRow();
        if (row2 == null || (context2 = row2.getContext()) == null || (bytesValueCreateBytesValue = createBytesValue(notificationEntry.getSbn().getNotification().getLargeIcon(), context2)) == null) {
            bytesValueCreateBytesValue = (BytesValue) BytesValue.newBuilder().build();
        }
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder contractedChild = headerView.setContractedChild((IcdfNotificationManagerOuterClass.ContractedChild) progress.setLargeIcon(bytesValueCreateBytesValue).build());
        IcdfNotificationManagerOuterClass$ExpandedChild$Builder icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder = IcdfNotificationManagerOuterClass.ExpandedChild.newBuilder();
        CharSequence charSequence4 = notificationEntry.getSbn().getNotification().extras.getCharSequence("android.template", "default");
        if (Intrinsics.areEqual(charSequence4, Notification.BigPictureStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.BIG_PICTURE_STYLE);
            IcdfNotificationManagerOuterClass$BigPictureStyleState.Builder builderNewBuilder4 = IcdfNotificationManagerOuterClass$BigPictureStyleState.newBuilder();
            IcdfNotificationManagerOuterClass$BigPictureStyleState.Builder expandedSummaryText = builderNewBuilder4.setExpandedTitle(createStringValue(notificationEntry.getSbn().getNotification().extras.getCharSequence("android.title.big"))).setExpandedSummaryText(createStringValue(notificationEntry.getSbn().getNotification().extras.getCharSequence("android.summaryText")));
            Bitmap bitmap = notificationEntry.getSbn().getNotification().largeIcon;
            if (bitmap == null) {
                bytesValue = (BytesValue) BytesValue.newBuilder().build();
            } else {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    bytesValue = byteArray == null ? (BytesValue) BytesValue.newBuilder().build() : (BytesValue) BytesValue.newBuilder().setValue(ByteString.copyFrom(byteArray)).build();
                } catch (Exception unused) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "ICDF Builder-->createBytesValue by bitmap fails");
                    }
                    bytesValue = (BytesValue) BytesValue.newBuilder().build();
                }
            }
            expandedSummaryText.setBigLargeIcon(bytesValue);
            ExpandableNotificationRow row3 = notificationEntry.getRow();
            Context context3 = row3 != null ? row3.getContext() : null;
            if (context3 != null) {
                builderNewBuilder4.setBigPicture(createBytesValue(((Notification.BigPictureStyle) Notification.Builder.recoverBuilder(context3, notificationEntry.getSbn().getNotification()).getStyle()).getBigPicture(), context3));
            }
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setBigPictureStyleState((IcdfNotificationManagerOuterClass$BigPictureStyleState) builderNewBuilder4.build());
        } else if (Intrinsics.areEqual(charSequence4, Notification.BigTextStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.BIG_TEXT_STYLE);
            IcdfNotificationManagerOuterClass$BigTextStyleState.Builder builderNewBuilder5 = IcdfNotificationManagerOuterClass$BigTextStyleState.newBuilder();
            Bundle bundle2 = notificationEntry.getSbn().getNotification().extras;
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setBigTextStyleState((IcdfNotificationManagerOuterClass$BigTextStyleState) builderNewBuilder5.setExpandedTitle(createStringValue(bundle2.getCharSequence("android.title.big"))).setExpandedSummaryText(createStringValue(bundle2.getCharSequence("android.summaryText"))).setBitText(createStringValue(bundle2.getCharSequence("android.bigText"))).build());
        } else if (Intrinsics.areEqual(charSequence4, Notification.MessagingStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.MESSAGING_STYLE);
            IcdfNotificationManagerOuterClass$MessagingStyleState.Builder builderNewBuilder6 = IcdfNotificationManagerOuterClass$MessagingStyleState.newBuilder();
            builderNewBuilder6.setConversationTitle(createStringValue(notificationEntry.getSbn().getNotification().extras.getCharSequence("android.conversationTitle")));
            MessagingLayout expandedChild = notificationEntry.getRow().getPrivateLayout().getExpandedChild();
            MessagingLayout messagingLayout = expandedChild instanceof MessagingLayout ? expandedChild : null;
            ArrayList messagingGroups = messagingLayout != null ? messagingLayout.getMessagingGroups() : null;
            ArrayList arrayList3 = new ArrayList();
            if (messagingGroups != null) {
                int size = messagingGroups.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = messagingGroups.get(i2);
                    i2++;
                    MessagingGroup messagingGroup = (MessagingGroup) obj;
                    if (!MessagingLinearLayout.isGone(messagingGroup)) {
                        messagingGroup.getClass();
                        IcdfNotificationManagerOuterClass$IcdfMessage$Builder icdfNotificationManagerOuterClass$IcdfMessage$BuilderNewBuilder = IcdfNotificationManagerOuterClass.IcdfMessage.newBuilder();
                        for (MessagingTextMessage messagingTextMessage : messagingGroup.getMessages()) {
                            messagingTextMessage.getClass();
                            ArrayList arrayList4 = messagingGroups;
                            IcdfNotificationManagerOuterClass$IcdfMessagingMessage$Builder icdfNotificationManagerOuterClass$IcdfMessagingMessage$BuilderNewBuilder = IcdfNotificationManagerOuterClass.IcdfMessagingMessage.newBuilder();
                            int i3 = size;
                            if (messagingTextMessage instanceof MessagingTextMessage) {
                                icdfNotificationManagerOuterClass$IcdfMessagingMessage$BuilderNewBuilder.setText(createStringValue(messagingTextMessage.getText()));
                            } else if (messagingTextMessage instanceof MessagingImageMessage) {
                                icdfNotificationManagerOuterClass$IcdfMessagingMessage$BuilderNewBuilder.setDrawable(createBytesValue(((MessagingImageMessage) messagingTextMessage).getDrawable()));
                            }
                            icdfNotificationManagerOuterClass$IcdfMessage$BuilderNewBuilder.addMessagingMessage((IcdfNotificationManagerOuterClass.IcdfMessagingMessage) icdfNotificationManagerOuterClass$IcdfMessagingMessage$BuilderNewBuilder.build());
                            messagingGroups = arrayList4;
                            size = i3;
                        }
                        ArrayList arrayList5 = messagingGroups;
                        int i4 = size;
                        CharSequence senderName = messagingGroup.getSenderName();
                        View avatar = messagingGroup.getAvatar();
                        arrayList3.add(icdfNotificationManagerOuterClass$IcdfMessage$BuilderNewBuilder.setPerson((IcdfNotificationManagerOuterClass.IcdfPerson) IcdfNotificationManagerOuterClass.IcdfPerson.newBuilder().setName(createStringValue(senderName)).setIcon(createBytesValue(avatar != null ? ((ImageView) avatar).getDrawable() : null)).build()).build());
                        messagingGroups = arrayList5;
                        size = i4;
                    }
                }
            }
            int size2 = arrayList3.size();
            int i5 = 0;
            while (i5 < size2) {
                Object obj2 = arrayList3.get(i5);
                i5++;
                builderNewBuilder6.addMessages((IcdfNotificationManagerOuterClass.IcdfMessage) obj2);
            }
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setMessagingStyleState((IcdfNotificationManagerOuterClass$MessagingStyleState) builderNewBuilder6.build());
        } else if (Intrinsics.areEqual(charSequence4, Notification.InboxStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.INBOX_STYLE);
            IcdfNotificationManagerOuterClass$InboxStyleState.Builder builderNewBuilder7 = IcdfNotificationManagerOuterClass$InboxStyleState.newBuilder();
            Bundle bundle3 = notificationEntry.getSbn().getNotification().extras;
            builderNewBuilder7.setExpandedTitle(createStringValue(bundle3.getCharSequence("android.title.big"))).setExpandedSummaryText(createStringValue(bundle3.getCharSequence("android.summaryText")));
            CharSequence[] charSequenceArray = bundle3.getCharSequenceArray("android.textLines");
            if (charSequenceArray != null) {
                for (CharSequence charSequence5 : charSequenceArray) {
                    builderNewBuilder7.addInboxLines(createStringValue(charSequence5));
                }
            }
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setInboxStyleState((IcdfNotificationManagerOuterClass$InboxStyleState) builderNewBuilder7.build());
        } else if (Intrinsics.areEqual(charSequence4, Notification.MediaStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.MEDIA_STYLE);
        } else if (Intrinsics.areEqual(charSequence4, Notification.DecoratedCustomViewStyle.class.getName())) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.DECORATED_CUSTOM_VIEW_STYLE);
        } else if (notificationEntry.getSbn().getNotification().contentView == null && notificationEntry.getSbn().getNotification().bigContentView == null) {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.DEFAULT);
        } else {
            icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.setNotificationStyle(IcdfNotificationManagerOuterClass.NotificationStyle.CUSTOM_VIEW_STYLE);
        }
        Notification.Action[] actionArr = notificationEntry.getSbn().getNotification().actions;
        if (actionArr == null || actionArr.length == 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            int length = actionArr.length;
            for (int i6 = 0; i6 < length; i6++) {
                IcdfNotificationManagerOuterClass$IcdfAction.Builder smartReply = IcdfNotificationManagerOuterClass$IcdfAction.newBuilder().setLabel(createStringValue(actionArr[i6].title)).setActionId(createInt32Value(i6)).setNotificationKey(createStringValue(notificationEntry.getKey())).setSmartReply(createBoolValue(actionArr[i6].getRemoteInputs() != null));
                ExpandableNotificationRow row4 = notificationEntry.getRow();
                if (row4 == null || (context = row4.getContext()) == null || (bytesValueCreateBytesValue2 = createBytesValue(actionArr[i6].getIcon(), context)) == null) {
                    bytesValueCreateBytesValue2 = (BytesValue) BytesValue.newBuilder().build();
                }
                arrayList.add(smartReply.setIcon(bytesValueCreateBytesValue2).build());
            }
        }
        if (arrayList != null) {
            int size3 = arrayList.size();
            int i7 = 0;
            while (i7 < size3) {
                Object obj3 = arrayList.get(i7);
                i7++;
                icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.addActions((IcdfNotificationManagerOuterClass$IcdfAction) obj3);
            }
        }
        CharSequence[] charSequenceArray2 = notificationEntry.getSbn().getNotification().extras.getCharSequenceArray("android.remoteInputHistory");
        if (charSequenceArray2 == null || charSequenceArray2.length == 0) {
            arrayList2 = null;
        } else {
            arrayList2 = new ArrayList();
            for (CharSequence charSequence6 : charSequenceArray2) {
                arrayList2.add(createStringValue(charSequence6));
            }
        }
        if (arrayList2 != null) {
            int size4 = arrayList2.size();
            int i8 = 0;
            while (i8 < size4) {
                Object obj4 = arrayList2.get(i8);
                i8++;
                icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.addReplyContent((StringValue) obj4);
            }
        }
        IcdfNotificationManagerOuterClass$IcdfNotification.Builder expandedChild2 = contractedChild.setExpandedChild((IcdfNotificationManagerOuterClass.ExpandedChild) icdfNotificationManagerOuterClass$ExpandedChild$BuilderNewBuilder.build());
        IcdfNotificationManagerOuterClass$GroupState.Builder builderNewBuilder8 = IcdfNotificationManagerOuterClass$GroupState.newBuilder();
        if (notificationEntry.getSbn().isGroup()) {
            IcdfNotificationManagerOuterClass$GroupState.Builder groupSummary = builderNewBuilder8.setGroupName(createStringValue(notificationEntry.getSbn().getGroup())).setGroupSummary(createBoolValue(notificationEntry.getSbn().getNotification().isGroupSummary()));
            ExpandableNotificationRow row5 = notificationEntry.getRow();
            IcdfNotificationManagerOuterClass$GroupState.Builder childrenInGroup = groupSummary.setChildrenInGroup(createBoolValue(row5 != null ? row5.isChildInGroup() : false));
            Bundle bundle4 = notificationEntry.getSbn().getNotification().extras;
            CharSequence charSequence7 = bundle4.getCharSequence("android.title");
            CharSequence charSequence8 = bundle4.getCharSequence("android.text");
            if (charSequence7 == null) {
                charSequence7 = bundle4.getCharSequence("android.title.big");
            }
            if (charSequence8 == null) {
                charSequence8 = bundle4.getCharSequence("android.bigText");
            }
            childrenInGroup.setGroupLine((IcdfNotificationManagerOuterClass.IcdfHybridNotification) IcdfNotificationManagerOuterClass.IcdfHybridNotification.newBuilder().setTitle(createStringValue(charSequence7)).setText(createStringValue(charSequence8)).build());
        }
        return (IcdfNotificationManagerOuterClass$IcdfNotification) expandedChild2.setGroupState((IcdfNotificationManagerOuterClass$GroupState) builderNewBuilder8.build()).build();
    }

    public final StringValue createStringValue(CharSequence charSequence) {
        if (charSequence == null) {
            StringValue stringValueBuild = StringValue.newBuilder().build();
            stringValueBuild.getClass();
            return stringValueBuild;
        }
        StringValue stringValueBuild2 = StringValue.newBuilder().setValue(charSequence.toString()).build();
        stringValueBuild2.getClass();
        return stringValueBuild2;
    }

    public final boolean resolveOnGoingForIcdf(NotificationEntry notificationEntry) {
        ExpandableNotificationRow row = notificationEntry.getRow();
        if (row != null ? row.canViewBeDismissed() : true) {
            return !((NotificationDismissibilityProviderExt) DependencyEx.sDependency.getDependency(NotificationDismissibilityProviderExt.class)).isCommercializeDismissAble(notificationEntry);
        }
        return true;
    }

    public final boolean resolvePinnedForIcdf(NotificationEntry notificationEntry, int i) {
        if (i == 0) {
            return false;
        }
        boolean zShouldHeadsUp = this.icdfHeadsUpDecision.shouldHeadsUp(notificationEntry);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("shouldHeadsUp: ", i, ", state: ", "ICDF Builder-->", zShouldHeadsUp));
        }
        if (i != 1) {
            return i == 2 && zShouldHeadsUp && (notificationEntry.getSbn().getNotification().flags & 8) == 0;
        }
        return zShouldHeadsUp;
    }

    public static BytesValue createBytesValue(Icon icon, Context context) {
        if (icon == null) {
            return BytesValue.newBuilder().build();
        }
        try {
            return createBytesValue(icon.loadDrawable(context));
        } catch (Exception unused) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ICDF Builder-->createBytesValue by icon fails");
            }
            return BytesValue.newBuilder().build();
        }
    }
}
