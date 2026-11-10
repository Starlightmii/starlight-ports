package com.oplus.systemui.notification.mcs;

import android.app.Notification;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Pair;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.notification.OplusRoundImageDrawable;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationCommercializeControl {
    public static final String DISABLE_DELETE_ONGOING_NOTIFICATION = "disable_delete_ongoing_notification";
    public static final String EXTRA_OPLUS_BG = "oplus_background";
    public static final String EXTRA_OPLUS_FORCE_EXPAND = "oplus_force_expand";
    public static final String EXTRA_OPLUS_FORCE_TOP = "oplus_force_top";
    public static final String EXTRA_OPLUS_LABEL = "oplus_label";
    public static final String EXTRA_OPLUS_MULTI_ICON_MAIN = "oplus_multi_icon_main";
    public static final String EXTRA_OPLUS_SECOND_BG = "oplus_second_background";
    public static final String KEY_KEYGUARD_SHOWING = "keyguardShowing";
    public static final String KEY_PANEL_EXPANDED = "panelExpanded";
    public static final String KEY_QS_EXPANDED = "qsExpanded";
    public static final String KEY_QS_FULLY_EXPANDED = "qsFullyExpanded";
    public static final String KEY_STATUS_BAR_STATE = "statusBarState";
    public static final String PANEL_STATE_ACTION = "com.oplus.systemui.state.panel";
    public static final String PANEL_STATE_PERMISSION = "com.oplus.systemui.permission.PANEL_STATE";
    private static final String TAG = "NotificationForceStyleControl";
    private static SettingsObserver mObserver;
    private static String onlineOngoingDismissWhiteList;
    public static final OplusNotificationCommercializeControl INSTANCE = new OplusNotificationCommercializeControl();
    private static final List<Pair<String, String>> channelForceEnableList = new ArrayList();
    private static final List<String> localOngoingDismissWhiteList = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SettingsObserver extends ContentObserver {
        private final WeakReference<Context> weakContext;

        public SettingsObserver(Handler handler, Context context) {
            super(handler);
            this.weakContext = new WeakReference<>(context);
        }

        public final WeakReference<Context> getWeakContext() {
            return this.weakContext;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            Context context = this.weakContext.get();
            if (context != null) {
                OplusNotificationCommercializeControl oplusNotificationCommercializeControl = OplusNotificationCommercializeControl.INSTANCE;
                OplusNotificationCommercializeControl.onlineOngoingDismissWhiteList = Settings.Global.getString(context.getContentResolver(), OplusNotificationCommercializeControl.DISABLE_DELETE_ONGOING_NOTIFICATION);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00a6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:55:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:57:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:55:0x00d2, please report this as an issue */
    public static final void handleEntryDataForCommercialization(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        OplusNotificationEntryEx ex;
        Notification notification;
        ArrayList arrayList;
        int i;
        Bundle bundle;
        Notification notification2;
        Bundle bundle2;
        Bundle bundle3;
        if (MCSUtil.isMCSPkg(statusBarNotification)) {
            String[] stringArray = null;
            try {
                Notification notification3 = statusBarNotification.getNotification();
                bitmap3 = (notification3 == null || (bundle3 = notification3.extras) == null) ? null : (Bitmap) bundle3.getParcelable(EXTRA_OPLUS_LABEL, Bitmap.class);
                if (bitmap3 != null) {
                    try {
                        if (statusBarNotification.getKey().length() > 0) {
                            NotificationStatisticUtil.sLabelNotificationKeyCollection.add(statusBarNotification.getKey());
                        }
                    } catch (BadParcelableException e) {
                        bitmap = bitmap3;
                        e = e;
                        bitmap2 = null;
                        OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "drawSecondBg: exception: ", e.getMessage(), LogUtil.TAG_STATUSBAR, TAG);
                        bitmap3 = bitmap;
                        bitmap4 = bitmap2;
                        ex = notificationEntry.getEx();
                        if (ex instanceof OplusNotificationEntryExImpl) {
                            if (bitmap3 != null) {
                                ((OplusNotificationEntryExImpl) ex).setOplusLabel(new BitmapDrawable(bitmap3));
                            }
                            if (bitmap4 != null) {
                                OplusRoundImageDrawable oplusRoundImageDrawable = new OplusRoundImageDrawable(bitmap4);
                                notification = statusBarNotification.getNotification();
                                if (notification != null) {
                                    stringArray = bundle.getStringArray(EXTRA_OPLUS_SECOND_BG);
                                }
                                arrayList = new ArrayList();
                                if (stringArray != null) {
                                    for (String str : stringArray) {
                                        try {
                                            arrayList.add(Integer.valueOf(Color.parseColor(str)));
                                        } catch (IllegalArgumentException e2) {
                                            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "drawSecondBg: exception: ", e2.getMessage(), LogUtil.TAG_STATUSBAR, TAG);
                                        }
                                    }
                                }
                                oplusRoundImageDrawable.getOplusBgColors().clear();
                                oplusRoundImageDrawable.getOplusBgColors().addAll(arrayList);
                                ((OplusNotificationEntryExImpl) ex).setOplusMcsBg(oplusRoundImageDrawable);
                            }
                            String forceExpand = NotificationUtil.getForceExpand(statusBarNotification);
                            OplusNotificationEntryExImpl oplusNotificationEntryExImpl = (OplusNotificationEntryExImpl) ex;
                            forceExpand.getClass();
                            oplusNotificationEntryExImpl.setForceExpandedStyle(Integer.parseInt(forceExpand));
                            String forceTop = NotificationUtil.getForceTop(statusBarNotification);
                            forceTop.getClass();
                            oplusNotificationEntryExImpl.setForceTopStyle(Integer.parseInt(forceTop));
                        }
                    }
                }
                FeatureOption.INSTANCE.getClass();
                bitmap4 = (FeatureOption.isFoldDevice() || FeatureOption.isTablet() || (notification2 = statusBarNotification.getNotification()) == null || (bundle2 = notification2.extras) == null) ? null : (Bitmap) bundle2.getParcelable(EXTRA_OPLUS_BG, Bitmap.class);
                if (bitmap4 != null) {
                    try {
                        if (statusBarNotification.getKey().length() > 0) {
                            NotificationStatisticUtil.sBackgroundNotificationKeyCollection.add(statusBarNotification.getKey());
                        }
                    } catch (BadParcelableException e3) {
                        Bitmap bitmap5 = bitmap4;
                        bitmap = bitmap3;
                        e = e3;
                        bitmap2 = bitmap5;
                        OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "drawSecondBg: exception: ", e.getMessage(), LogUtil.TAG_STATUSBAR, TAG);
                        bitmap3 = bitmap;
                        bitmap4 = bitmap2;
                    }
                }
                INSTANCE.handleMcsIconResource(notificationEntry, statusBarNotification);
            } catch (BadParcelableException e4) {
                e = e4;
                bitmap = null;
                bitmap2 = null;
            }
            ex = notificationEntry.getEx();
            if (ex instanceof OplusNotificationEntryExImpl) {
                if (bitmap3 != null) {
                    ((OplusNotificationEntryExImpl) ex).setOplusLabel(new BitmapDrawable(bitmap3));
                }
                if (bitmap4 != null) {
                    OplusRoundImageDrawable oplusRoundImageDrawable2 = new OplusRoundImageDrawable(bitmap4);
                    notification = statusBarNotification.getNotification();
                    if (notification != null && (bundle = notification.extras) != null) {
                        stringArray = bundle.getStringArray(EXTRA_OPLUS_SECOND_BG);
                    }
                    arrayList = new ArrayList();
                    if (stringArray != null) {
                        while (i < r7) {
                            arrayList.add(Integer.valueOf(Color.parseColor(str)));
                        }
                    }
                    oplusRoundImageDrawable2.getOplusBgColors().clear();
                    oplusRoundImageDrawable2.getOplusBgColors().addAll(arrayList);
                    ((OplusNotificationEntryExImpl) ex).setOplusMcsBg(oplusRoundImageDrawable2);
                }
                String forceExpand2 = NotificationUtil.getForceExpand(statusBarNotification);
                OplusNotificationEntryExImpl oplusNotificationEntryExImpl2 = (OplusNotificationEntryExImpl) ex;
                forceExpand2.getClass();
                oplusNotificationEntryExImpl2.setForceExpandedStyle(Integer.parseInt(forceExpand2));
                String forceTop2 = NotificationUtil.getForceTop(statusBarNotification);
                forceTop2.getClass();
                oplusNotificationEntryExImpl2.setForceTopStyle(Integer.parseInt(forceTop2));
            }
        }
    }

    public static boolean isBitmapSuitableForSize(Bitmap bitmap) {
        boolean z = false;
        if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationForceStyleControl-->[isBitmapSuitableForSize]: bitmap is null");
            }
            return false;
        }
        if (bitmap.getWidth() <= 96 && bitmap.getHeight() <= 96) {
            z = true;
        }
        if (!z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationForceStyleControl-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("[isBitmapSuitableForSize]: bitmap size is not suitable, bitmap size: ", bitmap.getWidth(), bitmap.getHeight(), "x", ", size: 96")));
        }
        return z;
    }

    public static final boolean isChannelInWhiteList(Context context, NotificationEntry notificationEntry) {
        INSTANCE.getClass();
        if (channelForceEnableList.size() <= 0) {
            for (String str : context.getResources().getStringArray(2130903352)) {
                String[] strArr = (String[]) StringsKt__StringsKt.split$default(str, new String[]{":"}, 0, 6).toArray(new String[0]);
                if (strArr.length == 2) {
                    channelForceEnableList.add(new Pair<>(strArr[0], strArr[1]));
                }
            }
        }
        Iterator<T> it = channelForceEnableList.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (Intrinsics.areEqual(pair.first, notificationEntry.getSbn().getPackageName()) && Intrinsics.areEqual(notificationEntry.getChannel().getId(), pair.second)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isCommercializeDismissAble(Context context, NotificationEntry notificationEntry) {
        if (notificationEntry.isClearable()) {
            return true;
        }
        INSTANCE.getClass();
        if (localOngoingDismissWhiteList.size() <= 0) {
            for (String str : context.getResources().getStringArray(2130903251)) {
                localOngoingDismissWhiteList.add(str);
            }
        }
        List<String> list = localOngoingDismissWhiteList;
        boolean zContains = list.contains(notificationEntry.getSbn().getPackageName());
        String str2 = onlineOngoingDismissWhiteList;
        boolean zContains2 = str2 != null ? StringsKt__StringsKt.contains(str2, notificationEntry.getSbn().getPackageName(), false) : false;
        boolean zIsMediaNotification = notificationEntry.getSbn().getNotification().isMediaNotification();
        boolean z = (zContains || zContains2 || zIsMediaNotification) ? false : true;
        if (!z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String str3 = onlineOngoingDismissWhiteList;
            StringBuilder sb = new StringBuilder("localOngoingDismissWhiteList:");
            sb.append(list);
            sb.append(" isInLocalWhiteList:");
            sb.append(zContains);
            sb.append(" onlineOngoingDismissWhiteList:");
            DeviceSettingItem$.ExternalSyntheticOutline0.m(str3, " isInOnlineWhiteList:", " isMediaStyle:", sb, zContains2);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("NotificationForceStyleControl-->", "SystemUi--Notification", sb, zIsMediaNotification);
        }
        return z;
    }

    public static final void registerOnlineOngoingDismissWhiteList(Context context) {
        if (context != null) {
            onlineOngoingDismissWhiteList = Settings.Global.getString(context.getContentResolver(), DISABLE_DELETE_ONGOING_NOTIFICATION);
            if (mObserver == null) {
                mObserver = new SettingsObserver(new Handler(), context);
            }
            SettingsObserver settingsObserver = mObserver;
            if (settingsObserver != null) {
                context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(DISABLE_DELETE_ONGOING_NOTIFICATION), true, settingsObserver);
            }
        }
    }

    public final void handleMcsIconResource(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        Object obj;
        Bundle bundle;
        if (MCSUtil.isMCSPkg(statusBarNotification)) {
            OplusNotificationEntryEx ex = notificationEntry.getEx();
            if (ex instanceof OplusNotificationEntryExImpl) {
                try {
                    Notification notification = statusBarNotification.getNotification();
                    Bitmap bitmap = (notification == null || (bundle = notification.extras) == null) ? null : (Bitmap) bundle.getParcelable(EXTRA_OPLUS_MULTI_ICON_MAIN, Bitmap.class);
                    if (isBitmapSuitableForSize(bitmap)) {
                        ((OplusNotificationEntryExImpl) ex).setOplusMcsIconRes(new BitmapDrawable(bitmap));
                    }
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "[handleMcsIconResource]: exception: ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
                }
            }
        }
    }
}
