package com.oplus.systemui.statusbar.notification.collection;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.shared.ActiveNotificationModel;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.notification.OplusRoundImageDrawable;
import com.oplus.systemui.statusbar.notification.collection.inflation.OplusHeadsUpAvalancheControl;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplus.systemui.statusbar.notification.util.OplusNotificationSmallIconUtil;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil$$ExternalSyntheticLambda3;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.locks.Lock;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationEntryExImpl extends OplusListEntryExImpl implements OplusNotificationEntryEx {
    private static final String CLAZZ_NAME = "android.app.Notification";
    public static final Companion Companion = new Companion();
    private static final String FIELD = "creationTime";
    private static final String MCS_HEADS_UP_STAY_TIME = "mcs_headsup_stay_time";
    private static final String MCS_HIDDEN_IN_GROUP_RES_LIST = "hidden_in_group_res_list";
    private static final String MCS_IS_SINGLE_JOIN_GROUP = "is_single_join_group";
    private static final String MCS_NOTIFICATION_TYPE = "mcs_category";
    private static final String MCS_NOTIFICATION_VOIP = "voip";
    private static final String TAG = "OplusNotificationEntryE";
    private final Context context;
    private String encryptionKey;
    public int exposureFlagMask;
    public int exposureFlagValue;
    public int forceExpandedStyle;
    public int forceTopStyle;
    public boolean isClickFromIcdf;
    public boolean isClose;
    public int isMcsTop;
    public boolean isMcsVoipNotification;
    public boolean isOldWrapper;
    public boolean isShown;
    public boolean isSysUIHighPriority;
    private final NotificationKeyguardHelper keyguardHelper;
    private final KeyguardStateController keyguardStateController;
    public int mcsHeadsUpStayTime;
    private OplusNotificationEntryEx.McsInfo mcsInfo;
    public int notificationIndex;
    public long notificationShowTime;
    public int notifyState;
    private final OplusHeadsUpAvalancheControl oplusAvalanche;
    private Drawable oplusLabel;
    private OplusRoundImageDrawable oplusMcsBg;
    private Drawable oplusMcsIconRes;
    public long postTime;
    public boolean showHeadsUp;
    private final StatusBarStateController statusBarStateController;
    public boolean suppressPromotionAnimations;
    public long updateTime;
    public int useAppIconForSmallIcon;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationEntryExImpl(Context context, NotificationKeyguardHelper notificationKeyguardHelper, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, OplusHeadsUpAvalancheControl oplusHeadsUpAvalancheControl) {
        super(context);
        this.context = context;
        this.keyguardHelper = notificationKeyguardHelper;
        this.keyguardStateController = keyguardStateController;
        this.statusBarStateController = statusBarStateController;
        this.oplusAvalanche = oplusHeadsUpAvalancheControl;
        this.notifyState = -1;
        this.notificationIndex = -1;
        this.forceExpandedStyle = -1;
        this.forceTopStyle = -1;
        this.encryptionKey = "";
        this.postTime = -1L;
        this.useAppIconForSmallIcon = -1;
        this.mcsInfo = new OplusNotificationEntryEx.McsInfo();
        this.mcsHeadsUpStayTime = -1;
        this.isMcsTop = -1;
    }

    public void collectNotificationInfo(boolean z, NotificationListContainer notificationListContainer, NotificationEntry notificationEntry) {
        if (notificationListContainer == null || notificationEntry == null) {
            return;
        }
        setVisibleState(z);
        ViewGroup viewParentForNotification = notificationListContainer.getViewParentForNotification();
        setNotificationIndex(viewParentForNotification != null ? viewParentForNotification.indexOfChild(notificationEntry.getRow()) : -1);
    }

    @Override // com.oplus.systemui.statusbar.notification.collection.OplusListEntryExImpl
    public String getEncryptionKey() {
        return this.encryptionKey;
    }

    public final int getForceExpandedStyle() {
        return this.forceExpandedStyle;
    }

    public final int getForceTopStyle() {
        return this.forceTopStyle;
    }

    public OplusNotificationEntryEx.McsInfo getMcsInfo() {
        return this.mcsInfo;
    }

    public int getNotificationIndex() {
        return this.notificationIndex;
    }

    public long getNotificationShowTime() {
        return this.notificationShowTime;
    }

    public int getNotifyState() {
        return this.notifyState;
    }

    public final Drawable getOplusLabel() {
        return this.oplusLabel;
    }

    public final OplusRoundImageDrawable getOplusMcsBg() {
        return this.oplusMcsBg;
    }

    public final Drawable getOplusMcsIconRes() {
        return this.oplusMcsIconRes;
    }

    public long getPostTime() {
        long postTime;
        StatusBarNotification sbn;
        if (this.postTime != Long.MAX_VALUE) {
            if (shouldResetPostTime()) {
                postTime = 0;
            } else {
                NotificationEntry notificationEntryM384getBase = m384getBase();
                postTime = (notificationEntryM384getBase == null || (sbn = notificationEntryM384getBase.getSbn()) == null) ? -1L : sbn.getPostTime();
            }
            this.postTime = postTime;
        }
        return this.postTime;
    }

    public boolean getShowHeadsUp() {
        return this.showHeadsUp;
    }

    public boolean getSuppressPromotionAnimations() {
        return this.suppressPromotionAnimations;
    }

    @Override // com.oplus.systemui.statusbar.notification.collection.OplusListEntryExImpl
    public long getUpdateTime() {
        OplusNotificationEntryEx ex;
        NotificationEntry notificationEntryM384getBase = m384getBase();
        if (notificationEntryM384getBase == null) {
            return 0L;
        }
        NotificationEntry representativeEntry = notificationEntryM384getBase.getRepresentativeEntry();
        if (representativeEntry == null || (ex = representativeEntry.getEx()) == null) {
            return this.updateTime;
        }
        return ex == this ? this.updateTime : ex.getUpdateTime();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0085  */
    public final void init() {
        long jLongValue;
        StatusBarNotification sbn;
        Notification notification;
        Bundle bundle;
        int[] intArray;
        Bundle bundle2;
        Bundle bundle3;
        Bundle bundle4;
        Bundle bundle5;
        Bundle bundle6;
        StatusBarNotification sbn2;
        String string = null;
        long postTime = 0;
        try {
            NotificationEntry notificationEntryM384getBase = m384getBase();
            Notification notification2 = (notificationEntryM384getBase == null || (sbn2 = notificationEntryM384getBase.getSbn()) == null) ? null : sbn2.getNotification();
            Field declaredField = Class.forName(CLAZZ_NAME).getDeclaredField(FIELD);
            declaredField.setAccessible(true);
            jLongValue = ((Long) declaredField.get(notification2)).longValue();
        } catch (Exception unused) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationEntryE-->getCreationTime() notification error");
            }
            jLongValue = 0;
        }
        this.updateTime = jLongValue;
        NotificationEntry notificationEntryM384getBase2 = m384getBase();
        if (notificationEntryM384getBase2 == null) {
            postTime = -1;
        } else if (this.keyguardHelper.isWhiteListNotificationOnKeyguard(notificationEntryM384getBase2)) {
            postTime = Long.MAX_VALUE;
        } else {
            NotificationKeyguardHelper notificationKeyguardHelper = this.keyguardHelper;
            StatusBarNotification sbn3 = notificationEntryM384getBase2.getSbn();
            notificationKeyguardHelper.getClass();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion() && !sbn3.isClearable()) {
                postTime = Long.MAX_VALUE;
            } else if (!shouldResetPostTime()) {
                postTime = notificationEntryM384getBase2.getSbn().getPostTime();
            }
        }
        this.postTime = postTime;
        NotificationEntry notificationEntryM384getBase3 = m384getBase();
        this.encryptionKey = LogUtil.encryptionNotificationKey(notificationEntryM384getBase3 != null ? notificationEntryM384getBase3.getKey() : null);
        NotificationEntry notificationEntryM384getBase4 = m384getBase();
        boolean z = false;
        if (notificationEntryM384getBase4 != null && MCSUtil.isMCSPkg(notificationEntryM384getBase4.getSbn())) {
            Notification notification3 = notificationEntryM384getBase4.getSbn().getNotification();
            int i = -1;
            this.mcsHeadsUpStayTime = (notification3 == null || (bundle6 = notification3.extras) == null) ? -1 : bundle6.getInt(MCS_HEADS_UP_STAY_TIME, -1);
            Notification notification4 = notificationEntryM384getBase4.getSbn().getNotification();
            if (notification4 != null && (bundle5 = notification4.extras) != null) {
                string = bundle5.getString(MCS_NOTIFICATION_TYPE);
            }
            this.isMcsVoipNotification = MCS_NOTIFICATION_VOIP.equals(string);
            Notification notification5 = notificationEntryM384getBase4.getSbn().getNotification();
            if (notification5 != null && (bundle4 = notification5.extras) != null) {
                i = bundle4.getInt(OplusNotificationCommercializeControl.EXTRA_OPLUS_FORCE_TOP, -1);
            }
            this.isMcsTop = i;
            Notification notification6 = notificationEntryM384getBase4.getSbn().getNotification();
            boolean z2 = (notification6 == null || (bundle3 = notification6.extras) == null) ? false : bundle3.getBoolean(MCS_IS_SINGLE_JOIN_GROUP, false);
            Notification notification7 = notificationEntryM384getBase4.getSbn().getNotification();
            if (notification7 == null || (bundle2 = notification7.extras) == null || (intArray = bundle2.getIntArray(MCS_HIDDEN_IN_GROUP_RES_LIST)) == null) {
                intArray = new int[0];
            }
            this.mcsInfo = OplusNotificationEntryEx.McsInfo.copy$default(this.mcsInfo, z2, ArraysKt___ArraysKt.toList(intArray), false, false, 12);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationEntryE-->".concat("initMcsNotificationParams=" + intArray + OplusPreventModeData.SEPARATOR + this.mcsInfo));
            }
        }
        NotificationEntry notificationEntryM384getBase5 = m384getBase();
        if (notificationEntryM384getBase5 != null && (sbn = notificationEntryM384getBase5.getSbn()) != null && (notification = sbn.getNotification()) != null && (bundle = notification.extras) != null) {
            z = bundle.getBoolean("isShown");
        }
        setShown(z);
    }

    public final void onExposure(int i) {
        int i2;
        StatusBarNotification sbn;
        Notification notification;
        StatusBarNotification sbn2;
        StatusBarNotification sbn3;
        Notification notification2;
        StatusBarNotification sbn4;
        ExpandableNotificationRow row;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        StatusBarNotification sbn5;
        Notification notification3;
        StatusBarNotification sbn6;
        int i3 = i | this.exposureFlagMask;
        this.exposureFlagMask = i3;
        int i4 = this.exposureFlagValue;
        if ((i4 & i3) != i3) {
            int i5 = i3 & (~i4);
            boolean shouldEncrypted = false;
            if ((i5 & 4) == 4) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    StringBuilder sb = new StringBuilder("OplusNotificationEntryE-->");
                    NotificationEntry notificationEntryM384getBase = m384getBase();
                    String key = (notificationEntryM384getBase == null || (sbn6 = notificationEntryM384getBase.getSbn()) == null) ? null : sbn6.getKey();
                    NotificationEntry notificationEntryM384getBase2 = m384getBase();
                    ExifInterface$.ExternalSyntheticOutline1.m(sb, "expose: " + key + ":" + ((notificationEntryM384getBase2 == null || (sbn5 = notificationEntryM384getBase2.getSbn()) == null || (notification3 = sbn5.getNotification()) == null) ? null : Integer.valueOf(notification3.describeContents())) + ", 4 ", "SystemUi--Notification");
                }
                i2 = 1;
            } else if ((i5 & 2) == 2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    StringBuilder sb2 = new StringBuilder("OplusNotificationEntryE-->");
                    NotificationEntry notificationEntryM384getBase3 = m384getBase();
                    String key2 = (notificationEntryM384getBase3 == null || (sbn4 = notificationEntryM384getBase3.getSbn()) == null) ? null : sbn4.getKey();
                    NotificationEntry notificationEntryM384getBase4 = m384getBase();
                    ExifInterface$.ExternalSyntheticOutline1.m(sb2, "expose: " + key2 + ":" + ((notificationEntryM384getBase4 == null || (sbn3 = notificationEntryM384getBase4.getSbn()) == null || (notification2 = sbn3.getNotification()) == null) ? null : Integer.valueOf(notification2.describeContents())) + ", 2 ", "SystemUi--Notification");
                }
                i2 = 1000;
            } else if ((i5 & 8) == 8) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    StringBuilder sb3 = new StringBuilder("OplusNotificationEntryE-->");
                    NotificationEntry notificationEntryM384getBase5 = m384getBase();
                    String key3 = (notificationEntryM384getBase5 == null || (sbn2 = notificationEntryM384getBase5.getSbn()) == null) ? null : sbn2.getKey();
                    NotificationEntry notificationEntryM384getBase6 = m384getBase();
                    ExifInterface$.ExternalSyntheticOutline1.m(sb3, "expose: " + key3 + ":" + ((notificationEntryM384getBase6 == null || (sbn = notificationEntryM384getBase6.getSbn()) == null || (notification = sbn.getNotification()) == null) ? null : Integer.valueOf(notification.describeContents())) + ", 8 ", "SystemUi--Notification");
                }
                i2 = 0;
            } else {
                i2 = -1;
            }
            if (i2 != -1) {
                long jCurrentTimeMillis = getNotificationShowTime() <= 0 ? System.currentTimeMillis() : getNotificationShowTime();
                NotificationEntry notificationEntryM384getBase7 = m384getBase();
                if (notificationEntryM384getBase7 != null && (row = notificationEntryM384getBase7.getRow()) != null && (oplusExpandableNotificationRowEx = row.mRowEx) != null) {
                    shouldEncrypted = oplusExpandableNotificationRowEx.getShouldEncrypted();
                }
                Context context = this.context;
                NotificationEntry notificationEntryM384getBase8 = m384getBase();
                StatusBarNotification sbn7 = notificationEntryM384getBase8 != null ? notificationEntryM384getBase8.getSbn() : null;
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
                ThreadManager threadManager = ThreadManager.getInstance();
                NotificationStatisticUtil$$ExternalSyntheticLambda3 notificationStatisticUtil$$ExternalSyntheticLambda3 = new NotificationStatisticUtil$$ExternalSyntheticLambda3();
                notificationStatisticUtil$$ExternalSyntheticLambda3.f$0 = context;
                notificationStatisticUtil$$ExternalSyntheticLambda3.f$1 = sbn7;
                notificationStatisticUtil$$ExternalSyntheticLambda3.f$2 = i2;
                notificationStatisticUtil$$ExternalSyntheticLambda3.f$3 = shouldEncrypted;
                notificationStatisticUtil$$ExternalSyntheticLambda3.f$4 = jCurrentTimeMillis;
                VarHandle.storeStoreFence();
                threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda3);
            }
            this.exposureFlagValue |= this.exposureFlagMask;
        }
    }

    public void onNotificationExposure(ActiveNotificationModel activeNotificationModel, NotificationVisibility[] notificationVisibilityArr) {
        ExpandableNotificationRow row;
        ExpandableNotificationRow row2;
        if (activeNotificationModel.getRow() != null && (((row = activeNotificationModel.getRow()) == null || row.getClipBottomAmount() != 0) && (row2 = activeNotificationModel.getRow()) != null && !row2.isGroupExpanded())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationEntryE-->In the folded state, if the sub-notification has a clip bottom amount, it will not be drawn on the UI");
                return;
            }
            return;
        }
        String key = activeNotificationModel.getKey();
        for (NotificationVisibility notificationVisibility : notificationVisibilityArr) {
            if (notificationVisibility.visible && Intrinsics.areEqual(notificationVisibility.key, key)) {
                if (notificationVisibility.location != NotificationVisibility.NotificationLocation.LOCATION_FIRST_HEADS_UP) {
                    onExposure(this.statusBarStateController.getState() == 1 ? 4 : 8);
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusNotificationEntryE-->kecf onNotificationExposure banner");
                }
            }
        }
    }

    public void setClickFromIcdf(boolean z) {
        this.isClickFromIcdf = z;
    }

    public void setClose(boolean z) {
        this.isClose = z;
    }

    public final void setForceExpandedStyle(int i) {
        this.forceExpandedStyle = i;
    }

    public final void setForceTopStyle(int i) {
        this.forceTopStyle = i;
    }

    public final void setHeadsUpEnd() {
        NotificationEntry notificationEntryM384getBase = m384getBase();
        if (notificationEntryM384getBase != null) {
            this.oplusAvalanche.endEntry(notificationEntryM384getBase);
        }
    }

    public void setNotificationIndex(int i) {
        this.notificationIndex = i;
    }

    public void setNotificationShowTime(long j) {
        this.notificationShowTime = j;
    }

    public void setNotifyState(int i) {
        this.notifyState = i;
    }

    public void setOldWrapper(boolean z) {
        this.isOldWrapper = z;
    }

    public final void setOplusLabel(Drawable drawable) {
        this.oplusLabel = drawable;
    }

    public final void setOplusMcsBg(OplusRoundImageDrawable oplusRoundImageDrawable) {
        this.oplusMcsBg = oplusRoundImageDrawable;
        this.mcsInfo = OplusNotificationEntryEx.McsInfo.copy$default(this.mcsInfo, false, (List) null, oplusRoundImageDrawable != null, oplusRoundImageDrawable != null, 3);
    }

    public final void setOplusMcsIconRes(Drawable drawable) {
        this.oplusMcsIconRes = drawable;
    }

    public void setRemoveByNewComing(boolean z) {
    }

    public void setShowHeadsUp(boolean z) {
        this.showHeadsUp = z;
    }

    public void setShown(boolean z) {
        this.isShown = z;
    }

    public void setSuppressPromotionAnimations(boolean z) {
        this.suppressPromotionAnimations = z;
    }

    public void setSysUIHighPriority(boolean z) {
        this.isSysUIHighPriority = z;
    }

    public void setVisibleState(boolean z) {
    }

    public final boolean shouldResetPostTime() {
        StatusBarNotification sbn;
        boolean z = this.keyguardStateController.mShowing;
        boolean z2 = this.statusBarStateController.getState() == 1;
        NotificationEntry notificationEntryM384getBase = m384getBase();
        return (((((notificationEntryM384getBase == null || (sbn = notificationEntryM384getBase.getSbn()) == null) ? 0L : sbn.getPostTime()) > (NotificationCommHelper.Companion.getInstance().getLastGoKeyguardTime() - 10000) ? 1 : (((notificationEntryM384getBase == null || (sbn = notificationEntryM384getBase.getSbn()) == null) ? 0L : sbn.getPostTime()) == (NotificationCommHelper.Companion.getInstance().getLastGoKeyguardTime() - 10000) ? 0 : -1)) < 0) && z) || (z2 && !z);
    }

    public final boolean useAppIconForSmallIcon() {
        StatusBarNotification sbn;
        if (this.useAppIconForSmallIcon == -1) {
            NotificationEntry notificationEntryM384getBase = m384getBase();
            this.useAppIconForSmallIcon = OplusNotificationSmallIconUtil.useAppIconForSmallIcon((notificationEntryM384getBase == null || (sbn = notificationEntryM384getBase.getSbn()) == null) ? null : sbn.getNotification()) ? 1 : 0;
        }
        return this.useAppIconForSmallIcon > 0;
    }
}
