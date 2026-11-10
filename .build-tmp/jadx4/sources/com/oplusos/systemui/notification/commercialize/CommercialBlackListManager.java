package com.oplusos.systemui.notification.commercialize;

import android.content.Context;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.CommonUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Regex;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommercialBlackListManager implements UserTracker.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CommercialBlackListManager";
    private static final String UPDATE_REASON_BLACK_LIST_CHANGED = "mSuperPowerSaveSettingsObserver Changed";
    private static final String USER_BLACK_LIST = "notification_commercial_black_list";
    private static CommercialBlackListManager instance;
    private Function1 isAllowCommercialBlackListManager;
    public volatile boolean isRegister;
    private WeakReference<Context> weakCtx;
    private final CopyOnWriteArraySet<String> blackListSet = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<String> exemptionSet = new CopyOnWriteArraySet<>();
    public final UserTracker userTracker = (UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class);
    public int currentUserId = UserHandle.myUserId();
    public final Executor mainExecutor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.MAIN_EXECUTOR);
    private final AbstractObserver notificationBlackListAppObserver = new CommercializeSettingsValueProxy.NotificationBlackListAppObserver() { // from class: com.oplusos.systemui.notification.commercialize.CommercialBlackListManager$notificationBlackListAppObserver$1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            Context context = getContext();
            if (context != null) {
                CommercialBlackListManager commercialBlackListManager = this.this$0;
                if (commercialBlackListManager.updateBlackList(context)) {
                    commercialBlackListManager.mainExecutor.execute(CommercialBlackListManager$updateNotificationUI$1.INSTANCE);
                }
            } else {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "CommercialBlackListManager", "blacklist init fail");
            }
            CommercialBlackListManager commercialBlackListManager2 = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CommercialBlackListManager-->".concat("change: " + z + ", update blacklist:" + commercialBlackListManager2.blackListSet));
            }
        }
    };
    private final AbstractObserver exemptionListAppObserver = new CommercializeSettingsValueProxy.UnimportantExemptionObserver() { // from class: com.oplusos.systemui.notification.commercialize.CommercialBlackListManager$exemptionListAppObserver$1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            Context context = getContext();
            if (context != null) {
                CommercialBlackListManager commercialBlackListManager = this.this$0;
                if (CommercialBlackListManager.access$updateUnimportantExemptionList(commercialBlackListManager, context)) {
                    commercialBlackListManager.mainExecutor.execute(CommercialBlackListManager$updateNotificationUI$1.INSTANCE);
                }
            } else {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "CommercialBlackListManager", "unimportant exemption list change fail");
            }
            CommercialBlackListManager commercialBlackListManager2 = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CommercialBlackListManager-->".concat("change: " + z + ", update exemption list:" + commercialBlackListManager2.exemptionSet));
            }
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static Set getUnimportantExemptionListFromSettings(Context context) {
            List listEmptyList;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String unimportantExemptionString = CommercializeSettingsValueProxy.Companion.getUnimportantExemptionString(context);
            if (unimportantExemptionString != null) {
                List listSplit = new Regex(OplusPreventModeData.SEPARATOR).split(unimportantExemptionString, 0);
                if (listSplit.isEmpty()) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                ListIterator listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator.previous()).length() != 0) {
                        listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                for (String str : (String[]) listEmptyList.toArray(new String[0])) {
                    if (str.length() > 0) {
                        linkedHashSet.add(str);
                    }
                }
            }
            return linkedHashSet;
        }

        public final void addExemption(String str, Context context) {
            Set mutableSet = CollectionsKt___CollectionsKt.toMutableSet(getUnimportantExemptionListFromSettings(context));
            if (!mutableSet.contains(str)) {
                mutableSet.add(str);
            }
            CommercializeSettingsValueProxy.Companion.putUnimportantExemptionString(context, CollectionsKt___CollectionsKt.joinToString$default(mutableSet, OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, (Function1) null, 62));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("add exemption ", str, "CommercialBlackListManager-->", "SystemUi--Notification");
            }
        }

        public final CommercialBlackListManager getInstance(Context context) {
            if (CommercialBlackListManager.instance == null) {
                synchronized (CommercialBlackListManager.class) {
                    if (CommercialBlackListManager.instance == null) {
                        CommercialBlackListManager.instance = new CommercialBlackListManager();
                        CommercialBlackListManager commercialBlackListManager = CommercialBlackListManager.instance;
                        if (commercialBlackListManager != null) {
                            commercialBlackListManager.registerObserver(context);
                        }
                    }
                }
            }
            CommercialBlackListManager commercialBlackListManager2 = CommercialBlackListManager.instance;
            commercialBlackListManager2.getClass();
            return commercialBlackListManager2;
        }
    }

    public static final boolean access$updateUnimportantExemptionList(CommercialBlackListManager commercialBlackListManager, Context context) {
        commercialBlackListManager.getClass();
        Companion.getClass();
        Set unimportantExemptionListFromSettings = Companion.getUnimportantExemptionListFromSettings(context);
        boolean zIsDiffContent = CommonUtils.isDiffContent(commercialBlackListManager.exemptionSet, unimportantExemptionListFromSettings);
        commercialBlackListManager.exemptionSet.clear();
        commercialBlackListManager.exemptionSet.addAll(unimportantExemptionListFromSettings);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CommercialBlackListManager-->".concat("update UnimportantExemptionList:" + commercialBlackListManager.exemptionSet));
        }
        return zIsDiffContent;
    }

    public static final void addExemption(String str, Context context) {
        Companion.addExemption(str, context);
    }

    public static final CommercialBlackListManager getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public final Function1 isAllowCommercialBlackListManager() {
        return this.isAllowCommercialBlackListManager;
    }

    public final boolean isInBlackList(StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null && this.currentUserId == 0 && this.blackListSet.contains(statusBarNotification.getPackageName()) && !this.exemptionSet.contains(statusBarNotification.getPackageName())) {
            Function1 function1 = this.isAllowCommercialBlackListManager;
            if (function1 != null ? ((Boolean) function1.invoke(statusBarNotification)).booleanValue() : true) {
                return true;
            }
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public void onUserChanged(int i, Context context) {
        super.onUserChanged(i, context);
        this.currentUserId = i;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "user change ", "CommercialBlackListManager-->", "SystemUi--Notification");
        }
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public final void registerObserver(Context context) {
        if (this.isRegister) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CommercialBlackListManager-->black list register");
        }
        this.isRegister = true;
        this.weakCtx = new WeakReference<>(context.getApplicationContext());
        this.exemptionSet.clear();
        CopyOnWriteArraySet<String> copyOnWriteArraySet = this.exemptionSet;
        Companion.getClass();
        copyOnWriteArraySet.addAll(Companion.getUnimportantExemptionListFromSettings(context));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CommercialBlackListManager-->".concat("init UnimportantExemptionList:" + this.exemptionSet));
        }
        this.blackListSet.clear();
        updateBlackList(context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CommercialBlackListManager-->".concat("init blacklist:" + this.blackListSet));
        }
        this.notificationBlackListAppObserver.registerForUserSwitch(context.getApplicationContext(), true);
        this.exemptionListAppObserver.registerForUserSwitch(context.getApplicationContext(), true);
        this.userTracker.addCallback(this, this.mainExecutor);
    }

    public final void setAllowCommercialBlackListManager(Function1 function1) {
        this.isAllowCommercialBlackListManager = function1;
    }

    public final boolean updateBlackList(Context context) {
        List listEmptyList;
        String blackListString = CommercializeSettingsValueProxy.Companion.getBlackListString(context);
        HashSet hashSet = new HashSet();
        if (blackListString != null) {
            List listSplit = new Regex(OplusPreventModeData.SEPARATOR).split(blackListString, 0);
            if (listSplit.isEmpty()) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            ListIterator listIterator = listSplit.listIterator(listSplit.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                if (((String) listIterator.previous()).length() != 0) {
                    listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    break;
                }
            }
            for (String str : (String[]) listEmptyList.toArray(new String[0])) {
                if (str.length() > 0) {
                    hashSet.add(str);
                }
            }
        }
        boolean zIsDiffContent = CommonUtils.isDiffContent(this.blackListSet, hashSet);
        this.blackListSet.clear();
        this.blackListSet.addAll(hashSet);
        return zIsDiffContent;
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
