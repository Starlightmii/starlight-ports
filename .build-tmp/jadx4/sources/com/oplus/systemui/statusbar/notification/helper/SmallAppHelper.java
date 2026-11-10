package com.oplus.systemui.statusbar.notification.helper;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplus.systemui.statusbar.notification.smallapp.SmallApp;
import com.oplus.systemui.statusbar.notification.smallapp.SmallAppUtil;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallAppHelper {
    public static final Companion Companion = new Companion();
    private static final String SMALL_APP_PACKAGE_NAME = "com.nearme.instant.platform";
    private static final String TAG = "SmallAppHelper";
    public static boolean isRegister;
    private final Context mContext;
    private final Handler mMainHandler;
    private final IMultiUserListener mMultiUserListener;
    private ContentObserver mObserver;
    private Context mUserContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getSmallAppName(StatusBarNotification statusBarNotification) {
            Notification notification;
            Bundle bundle;
            String string;
            return (statusBarNotification == null || (notification = statusBarNotification.getNotification()) == null || (bundle = notification.extras) == null || (string = bundle.getString("small_app_name", "")) == null) ? "" : string;
        }

        public final String getSmallAppPkg(StatusBarNotification statusBarNotification) {
            if ((statusBarNotification != null ? statusBarNotification.getNotification() : null) == null) {
                return "";
            }
            String string = statusBarNotification.getNotification().extras.getString("small_app_package", "");
            string.getClass();
            return string;
        }

        public final boolean isSmallApp(StatusBarNotification statusBarNotification) {
            if (TextUtils.equals(statusBarNotification != null ? statusBarNotification.getPackageName() : null, SmallAppHelper.SMALL_APP_PACKAGE_NAME)) {
                if ((statusBarNotification != null ? statusBarNotification.getNotification() : null) != null) {
                    return statusBarNotification.getNotification().extras.getBoolean("small_app", false);
                }
            }
            return false;
        }

        public final void setSmallAppNotify(Context context, boolean z, StatusBarNotification statusBarNotification) {
            SmallApp smallAppForCache = SmallAppUtil.getSmallAppForCache(context, getSmallAppPkg(statusBarNotification));
            if (smallAppForCache != null) {
                smallAppForCache.setNotify(z);
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(SeparateQSStatisicUtil.VALUE_PANEL_NOTIFY, Boolean.valueOf(smallAppForCache.mNotify));
                    contentValues.put("badge", Boolean.valueOf(smallAppForCache.mBadge));
                    contentValues.put("banner", Boolean.valueOf(smallAppForCache.mBanner));
                    contentValues.put("hidedetail", Boolean.valueOf(smallAppForCache.mHideDetail));
                    contentValues.put("light", Boolean.valueOf(smallAppForCache.mLight));
                    contentValues.put(LockTile.TILE_SPEC, Boolean.valueOf(smallAppForCache.mLockScreen));
                    contentValues.put("vibrate", Boolean.valueOf(smallAppForCache.mVibrate));
                    contentValues.put("priority", Boolean.valueOf(smallAppForCache.mPriority));
                    contentValues.put("dot", Boolean.valueOf(smallAppForCache.mCircleBadge));
                    contentValues.put("ring", Boolean.valueOf(smallAppForCache.mSound));
                    if (!smallAppForCache.mPropertiesOnOs6NotAvailable) {
                        contentValues.put("statusbar", Boolean.valueOf(smallAppForCache.mShowIcon));
                        contentValues.put("statusbar_user", Boolean.valueOf(smallAppForCache.mChangeableShowIcon));
                        contentValues.put("fold", Boolean.valueOf(smallAppForCache.mFold));
                        contentValues.put("fold_user", Boolean.valueOf(smallAppForCache.mChangeableFold));
                    }
                    if (!smallAppForCache.mPropertiesOnOs7NotAvailable) {
                        contentValues.put("smart_fold", Integer.valueOf(smallAppForCache.getStowOption()));
                    }
                    Context currentUserContext = MultiUserContentHelper.getCurrentUserContext(context);
                    if (currentUserContext != null) {
                        context = currentUserContext;
                    }
                    context.getContentResolver().update(Uri.parse("content://com.nearme.instant.setting/notification/" + smallAppForCache.getPackageName()), contentValues, null, null);
                } catch (Exception e) {
                    LogUtil.e(LogUtil.TAG_COMMON, "SystemUi_SmallAppUtil", "updateSmallApp-e:" + e.getMessage(), e);
                }
            }
        }
    }

    public SmallAppHelper(Context context, Handler handler) {
        SmallAppHelper$mMultiUserListener$1 smallAppHelper$mMultiUserListener$1 = new SmallAppHelper$mMultiUserListener$1();
        smallAppHelper$mMultiUserListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMultiUserListener = smallAppHelper$mMultiUserListener$1;
        this.mContext = context.getApplicationContext();
        MultiUserManager.Companion.getInstance().addListener(smallAppHelper$mMultiUserListener$1);
        this.mMainHandler = handler;
        initHelper();
    }

    public static final boolean isSmallApp(StatusBarNotification statusBarNotification) {
        return Companion.isSmallApp(statusBarNotification);
    }

    public final int getImportanceOfSmallApp(StatusBarNotification statusBarNotification, int i) {
        SmallApp smallAppForCache = SmallAppUtil.getSmallAppForCache(this.mContext, Companion.getSmallAppPkg(statusBarNotification));
        if (smallAppForCache == null) {
            return i;
        }
        if (smallAppForCache.mBanner) {
            return 4;
        }
        return smallAppForCache.getStowOption() == -1 ? 2 : 3;
    }

    public final void initHelper() {
        if (this.mObserver == null) {
            this.mObserver = new ContentObserver(this.mMainHandler) { // from class: com.oplus.systemui.statusbar.notification.helper.SmallAppHelper.initHelper.1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m("onChange() selfChange = ", "SmallAppHelper-->", "SystemUi--Notification", z);
                    }
                    SmallAppUtil.initSmallAppInfo(SmallAppHelper.this.mContext);
                }
            };
        }
        try {
            this.mUserContext = MultiUserContentHelper.Companion.getCurrentUserContext(this.mContext);
            int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
            Context context = this.mUserContext;
            if (context != null) {
                context.getContentResolver().registerContentObserver(Uri.parse("content://com.nearme.instant.setting/notification"), true, this.mObserver, currentUserId);
            } else {
                this.mContext.getContentResolver().registerContentObserver(Uri.parse("content://com.nearme.instant.setting/notification"), true, this.mObserver, currentUserId);
            }
            isRegister = true;
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, TAG, "Notification--listenNotificationCenterChange--smallApp--e:" + e.getMessage(), e);
        }
    }

    public final boolean suppressForSmallApp(String str) {
        SmallApp smallAppForCache;
        return TextUtils.isEmpty(str) || (smallAppForCache = SmallAppUtil.getSmallAppForCache(this.mContext, str)) == null || !smallAppForCache.mNotify || !smallAppForCache.mBanner;
    }
}
