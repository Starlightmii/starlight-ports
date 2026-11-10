package com.oplus.systemui.notification.collection.progress;

import android.R;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import androidx.room.util.CursorUtil$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationProgressOnlyUpdateHelper {
    private static final String TAG = "NotifProgressOnlyUpdate";
    public static final NotificationProgressOnlyUpdateHelper INSTANCE = new NotificationProgressOnlyUpdateHelper();
    private static final Set<String> PROGRESS_ONLY_UPDATE_BLACKLIST = SetsKt__SetsJVMKt.setOf("com.apkpure.aegon");
    private static final String[] UI_EXTRAS_KEYS = {"android.title", "android.title.big", "android.text", "android.bigText", "android.subText", "android.infoText", "android.summaryText", "android.showWhen", "android.showChronometer", "android.chronometerCountDown", "android.colorized", "android.template", "android.substName", "android.largeIcon.big"};

    public static boolean applyProgress(int i, int i2, View view, boolean z) {
        if (view == null) {
            return false;
        }
        View viewFindViewById = view.findViewById(R.id.progress);
        if (!(viewFindViewById instanceof ProgressBar)) {
            return false;
        }
        ProgressBar progressBar = (ProgressBar) viewFindViewById;
        if (progressBar.getMax() != i2) {
            progressBar.setMax(i2);
        }
        if (progressBar.isIndeterminate() != z) {
            progressBar.setIndeterminate(z);
        }
        if (z || progressBar.getProgress() == i) {
            return true;
        }
        progressBar.setProgress(i);
        return true;
    }

    public static boolean hasAppCustomRemoteViews(Notification notification, String str) {
        String str2;
        String str3;
        String str4;
        if (str == null || str.length() == 0) {
            return false;
        }
        RemoteViews remoteViews = notification.contentView;
        if (remoteViews != null && (str4 = remoteViews.getPackage()) != null && str4.equals(str)) {
            return true;
        }
        RemoteViews remoteViews2 = notification.bigContentView;
        if (remoteViews2 != null && (str3 = remoteViews2.getPackage()) != null && str3.equals(str)) {
            return true;
        }
        RemoteViews remoteViews3 = notification.headsUpContentView;
        return (remoteViews3 == null || (str2 = remoteViews3.getPackage()) == null || !str2.equals(str)) ? false : true;
    }

    public static boolean remoteViewsSameLayout(RemoteViews remoteViews, RemoteViews remoteViews2) {
        if (remoteViews == null && remoteViews2 == null) {
            return true;
        }
        return remoteViews != null && remoteViews2 != null && remoteViews.getLayoutId() == remoteViews2.getLayoutId() && TextUtils.equals(remoteViews.getPackage(), remoteViews2.getPackage());
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0157 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:103:0x0159  */
    /* JADX WARN: Code duplicated, block: B:104:0x015c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0161  */
    /* JADX WARN: Code duplicated, block: B:108:0x0164  */
    /* JADX WARN: Code duplicated, block: B:112:0x016d A[LOOP:1: B:86:0x0128->B:112:0x016d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:113:0x0170  */
    /* JADX WARN: Code duplicated, block: B:115:0x0176  */
    /* JADX WARN: Code duplicated, block: B:116:0x0178  */
    /* JADX WARN: Code duplicated, block: B:127:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:133:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:138:0x01d4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x011e  */
    /* JADX WARN: Code duplicated, block: B:87:0x012a  */
    /* JADX WARN: Code duplicated, block: B:90:0x0138  */
    /* JADX WARN: Code duplicated, block: B:91:0x013a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x013c  */
    /* JADX WARN: Code duplicated, block: B:93:0x013e  */
    /* JADX WARN: Code duplicated, block: B:96:0x0149  */
    /* JADX WARN: Code duplicated, block: B:98:0x014d  */
    public final boolean tryUpdateProgressOnly(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        Notification notification;
        Notification notification2;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        Bundle bundle4;
        ExpandableNotificationRow row;
        Notification notification3;
        Bundle bundle5;
        NotificationContentView privateLayout;
        int i;
        int i2;
        boolean z;
        boolean zApplyProgress;
        int i3;
        boolean zContainsKey;
        boolean zContainsKey2;
        Object obj;
        Object obj2;
        boolean z2;
        CharSequence charSequence;
        CharSequence charSequence2;
        boolean zEquals;
        String packageName = statusBarNotification.getPackageName();
        if (packageName != null && PROGRESS_ONLY_UPDATE_BLACKLIST.contains(packageName) && notificationEntry != null && !notificationEntry.isCanceled()) {
            StatusBarNotification sbn = notificationEntry.getSbn();
            if (Intrinsics.areEqual(sbn.getKey(), statusBarNotification.getKey()) && (notification = sbn.getNotification()) != null && (notification2 = statusBarNotification.getNotification()) != null && (bundle = notification2.extras) != null && ((bundle.getInt("android.progressMax", 0) != 0 || bundle.getBoolean("android.progressIndeterminate", false)) && (bundle2 = notification.extras) != null && ((bundle2.getInt("android.progressMax", 0) != 0 || bundle2.getBoolean("android.progressIndeterminate", false)) && !hasAppCustomRemoteViews(notification, sbn.getPackageName()) && !hasAppCustomRemoteViews(notification2, statusBarNotification.getPackageName()) && notification.flags == notification2.flags && notification.color == notification2.color && notification.visibility == notification2.visibility && notification.iconLevel == notification2.iconLevel && notification.number == notification2.number && TextUtils.equals(notification.category, notification2.category) && TextUtils.equals(notification.getGroup(), notification2.getGroup()) && TextUtils.equals(notification.getSortKey(), notification2.getSortKey()) && TextUtils.equals(notification.tickerText, notification2.tickerText)))) {
                Notification.Action[] actionArr = notification.actions;
                Notification.Action[] actionArr2 = notification2.actions;
                if (actionArr != actionArr2) {
                    if (actionArr != null && actionArr2 != null && actionArr.length == actionArr2.length) {
                        int length = actionArr.length;
                        for (int i4 = 0; i4 < length; i4++) {
                            if (TextUtils.equals(actionArr[i4].title, actionArr2[i4].title)) {
                            }
                        }
                        if (remoteViewsSameLayout(notification.contentView, notification2.contentView) && remoteViewsSameLayout(notification.bigContentView, notification2.bigContentView) && remoteViewsSameLayout(notification.headsUpContentView, notification2.headsUpContentView)) {
                            bundle3 = notification.extras;
                            bundle4 = notification2.extras;
                            if (bundle3 != bundle4) {
                                row = notificationEntry.getRow();
                                if (row != null) {
                                    zApplyProgress = false;
                                } else {
                                    i = bundle5.getInt("android.progress", 0);
                                    i2 = bundle5.getInt("android.progressMax", 0);
                                    z = bundle5.getBoolean("android.progressIndeterminate", false);
                                    zApplyProgress = applyProgress(i, i2, privateLayout.getContractedChild(), z);
                                    if (applyProgress(i, i2, privateLayout.getExpandedChild(), z)) {
                                        zApplyProgress = true;
                                    }
                                    if (applyProgress(i, i2, privateLayout.getHeadsUpChild(), z)) {
                                        zApplyProgress = true;
                                    }
                                }
                                if (zApplyProgress) {
                                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("NotifRebuildTrace progress-only will skip onBuildList pkg=", statusBarNotification.getPackageName(), " key=", statusBarNotification.getKey()), null, 8);
                                    return true;
                                }
                            } else if (bundle3 != null && bundle4 != null) {
                                for (String str : UI_EXTRAS_KEYS) {
                                    zContainsKey = bundle3.containsKey(str);
                                    zContainsKey2 = bundle4.containsKey(str);
                                    if (zContainsKey && !zContainsKey2) {
                                        zEquals = true;
                                    } else if (zContainsKey != zContainsKey2) {
                                        zEquals = false;
                                    } else {
                                        obj = bundle3.get(str);
                                        obj2 = bundle4.get(str);
                                        if (obj == obj2) {
                                            zEquals = true;
                                        } else {
                                            z2 = obj instanceof CharSequence;
                                            if (!z2 || (obj2 instanceof CharSequence)) {
                                                if (z2) {
                                                    charSequence = (CharSequence) obj;
                                                } else {
                                                    charSequence = null;
                                                }
                                                if (obj2 instanceof CharSequence) {
                                                    charSequence2 = (CharSequence) obj2;
                                                } else {
                                                    charSequence2 = null;
                                                }
                                                zEquals = TextUtils.equals(charSequence, charSequence2);
                                            } else {
                                                zEquals = Intrinsics.areEqual(obj, obj2);
                                            }
                                        }
                                    }
                                    if (!zEquals) {
                                    }
                                }
                                row = notificationEntry.getRow();
                                if (row != null || (notification3 = statusBarNotification.getNotification()) == null || (bundle5 = notification3.extras) == null || (privateLayout = row.getPrivateLayout()) == null) {
                                    zApplyProgress = false;
                                } else {
                                    i = bundle5.getInt("android.progress", 0);
                                    i2 = bundle5.getInt("android.progressMax", 0);
                                    z = bundle5.getBoolean("android.progressIndeterminate", false);
                                    zApplyProgress = applyProgress(i, i2, privateLayout.getContractedChild(), z);
                                    if (applyProgress(i, i2, privateLayout.getExpandedChild(), z)) {
                                        zApplyProgress = true;
                                    }
                                    if (applyProgress(i, i2, privateLayout.getHeadsUpChild(), z)) {
                                        zApplyProgress = true;
                                    }
                                }
                                if (zApplyProgress) {
                                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("NotifRebuildTrace progress-only will skip onBuildList pkg=", statusBarNotification.getPackageName(), " key=", statusBarNotification.getKey()), null, 8);
                                    return true;
                                }
                            }
                        }
                    }
                } else if (remoteViewsSameLayout(notification.contentView, notification2.contentView)) {
                    bundle3 = notification.extras;
                    bundle4 = notification2.extras;
                    if (bundle3 != bundle4) {
                        row = notificationEntry.getRow();
                        if (row != null) {
                            zApplyProgress = false;
                        } else {
                            i = bundle5.getInt("android.progress", 0);
                            i2 = bundle5.getInt("android.progressMax", 0);
                            z = bundle5.getBoolean("android.progressIndeterminate", false);
                            zApplyProgress = applyProgress(i, i2, privateLayout.getContractedChild(), z);
                            if (applyProgress(i, i2, privateLayout.getExpandedChild(), z)) {
                                zApplyProgress = true;
                            }
                            if (applyProgress(i, i2, privateLayout.getHeadsUpChild(), z)) {
                                zApplyProgress = true;
                            }
                        }
                        if (zApplyProgress) {
                            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("NotifRebuildTrace progress-only will skip onBuildList pkg=", statusBarNotification.getPackageName(), " key=", statusBarNotification.getKey()), null, 8);
                            return true;
                        }
                    } else if (bundle3 != null) {
                        while (i3 < r7) {
                            zContainsKey = bundle3.containsKey(str);
                            zContainsKey2 = bundle4.containsKey(str);
                            if (zContainsKey) {
                                if (zContainsKey != zContainsKey2) {
                                    zEquals = false;
                                } else {
                                    obj = bundle3.get(str);
                                    obj2 = bundle4.get(str);
                                    if (obj == obj2) {
                                        zEquals = true;
                                    } else {
                                        z2 = obj instanceof CharSequence;
                                        if (z2) {
                                            if (z2) {
                                                charSequence = (CharSequence) obj;
                                            } else {
                                                charSequence = null;
                                            }
                                            if (obj2 instanceof CharSequence) {
                                                charSequence2 = (CharSequence) obj2;
                                            } else {
                                                charSequence2 = null;
                                            }
                                            zEquals = TextUtils.equals(charSequence, charSequence2);
                                        } else {
                                            if (z2) {
                                                charSequence = (CharSequence) obj;
                                            } else {
                                                charSequence = null;
                                            }
                                            if (obj2 instanceof CharSequence) {
                                                charSequence2 = (CharSequence) obj2;
                                            } else {
                                                charSequence2 = null;
                                            }
                                            zEquals = TextUtils.equals(charSequence, charSequence2);
                                        }
                                    }
                                }
                            } else if (zContainsKey != zContainsKey2) {
                                zEquals = false;
                            } else {
                                obj = bundle3.get(str);
                                obj2 = bundle4.get(str);
                                if (obj == obj2) {
                                    zEquals = true;
                                } else {
                                    z2 = obj instanceof CharSequence;
                                    if (z2) {
                                        if (z2) {
                                            charSequence = (CharSequence) obj;
                                        } else {
                                            charSequence = null;
                                        }
                                        if (obj2 instanceof CharSequence) {
                                            charSequence2 = (CharSequence) obj2;
                                        } else {
                                            charSequence2 = null;
                                        }
                                        zEquals = TextUtils.equals(charSequence, charSequence2);
                                    } else {
                                        if (z2) {
                                            charSequence = (CharSequence) obj;
                                        } else {
                                            charSequence = null;
                                        }
                                        if (obj2 instanceof CharSequence) {
                                            charSequence2 = (CharSequence) obj2;
                                        } else {
                                            charSequence2 = null;
                                        }
                                        zEquals = TextUtils.equals(charSequence, charSequence2);
                                    }
                                }
                            }
                            if (!zEquals) {
                            }
                        }
                        row = notificationEntry.getRow();
                        if (row != null) {
                            zApplyProgress = false;
                        } else {
                            i = bundle5.getInt("android.progress", 0);
                            i2 = bundle5.getInt("android.progressMax", 0);
                            z = bundle5.getBoolean("android.progressIndeterminate", false);
                            zApplyProgress = applyProgress(i, i2, privateLayout.getContractedChild(), z);
                            if (applyProgress(i, i2, privateLayout.getExpandedChild(), z)) {
                                zApplyProgress = true;
                            }
                            if (applyProgress(i, i2, privateLayout.getHeadsUpChild(), z)) {
                                zApplyProgress = true;
                            }
                        }
                        if (zApplyProgress) {
                            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("NotifRebuildTrace progress-only will skip onBuildList pkg=", statusBarNotification.getPackageName(), " key=", statusBarNotification.getKey()), null, 8);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
