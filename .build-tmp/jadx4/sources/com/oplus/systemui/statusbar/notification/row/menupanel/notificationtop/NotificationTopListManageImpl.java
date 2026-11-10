package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTopListManageImpl implements NotificationTopListManage {
    public static final Companion Companion = new Companion();
    private static final String PACKAGE = "package";
    private static final String SPLIT_SYMBOL = ",";
    private static final String TAG = "NotificationTopListManage";
    private static volatile NotificationTopListManageImpl instance;
    public volatile boolean isRegister;
    private NotificationTopListListener notificationTopListListener;
    public final Executor mainExecutor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.MAIN_EXECUTOR);
    public final UserTracker userTracker = (UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class);
    private final CopyOnWriteArraySet<String> currentNotificationTopSet = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<String> preNotificationTopSet = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<String> diffNotificationTopSet = new CopyOnWriteArraySet<>();
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null));
    private final Lazy backgroundDispatcher$delegate = LazyKt__LazyJVMKt.lazy(new NotificationTopListManageImpl$$ExternalSyntheticLambda1());
    private final AbstractObserver notificationTopListObserver = new NotificationTopSettingsValueProxy.NotificationTopListObserver() { // from class: com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl$notificationTopListObserver$1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            Object obj;
            super.onChange(z);
            Context context = getContext();
            if (context != null) {
                NotificationTopListManageImpl notificationTopListManageImpl = this.this$0;
                try {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationTopListManage-->updateToppedNotificationSet");
                    }
                    obj = Result.constructor-impl(BuildersKt.launch$default(notificationTopListManageImpl.coroutineScope, notificationTopListManageImpl.getBackgroundDispatcher(), (CoroutineStart) null, new NotificationTopListManageImpl$notificationTopListObserver$1$onChange$1$2(context, notificationTopListManageImpl, null), 2));
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.exceptionOrNull-impl(obj) != null) {
                    LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, "NotificationTopListManage", "updateToppedNotificationSet failed");
                }
            }
        }
    };
    private final NotificationTopListManageImpl$appChangeReceiver$1 appChangeReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl$appChangeReceiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String schemeSpecificPart;
            if (context == null || intent == null) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("appChangeReceiver: ", intent.getAction(), "NotificationTopListManage-->", "SystemUi--Notification");
            }
            Uri data = intent.getData();
            if (data == null || (schemeSpecificPart = data.getSchemeSpecificPart()) == null) {
                return;
            }
            NotificationTopListManageImpl.access$appChangeOperation(this.this$0, context, intent, schemeSpecificPart);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NotificationTopListManage getInstance(Context context) {
            NotificationTopListManageImpl notificationTopListManageImpl;
            if (NotificationTopListManageImpl.instance == null) {
                synchronized (NotificationTopListManage.class) {
                    if (NotificationTopListManageImpl.instance == null) {
                        NotificationTopListManageImpl.instance = new NotificationTopListManageImpl();
                        if (context != null && (notificationTopListManageImpl = NotificationTopListManageImpl.instance) != null) {
                            NotificationTopListManageImpl.access$registerObserver(notificationTopListManageImpl, context);
                        }
                    }
                }
            }
            return NotificationTopListManageImpl.instance;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface NotificationTopListListener {
    }

    public static final void access$appChangeOperation(NotificationTopListManageImpl notificationTopListManageImpl, Context context, Intent intent, String str) {
        int intExtra;
        String notificationTopKey = NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(notificationTopListManageImpl.getCurrentUserId()));
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode == 525384130) {
                if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                    notificationTopListManageImpl.cancelNotificationTop(context, notificationTopKey);
                    return;
                }
                return;
            }
            if (iHashCode != 1544582882) {
                if (iHashCode == 1732243384 && action.equals("oplus.intent.action.MULTI_APP_PACKAGE_REMOVED") && -10000 != (intExtra = intent.getIntExtra("android.intent.extra.user_handle", -10000))) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intExtra, "multi app uninstall for userId: ", "NotificationTopListManage-->", "SystemUi--Notification");
                    }
                    notificationTopListManageImpl.cancelNotificationTop(context, NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(intExtra)));
                    return;
                }
                return;
            }
            if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                    return;
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Resources resources = context.getResources();
                String[] stringArray = resources != null ? resources.getStringArray(2130903250) : null;
                if (stringArray != null) {
                    for (String str2 : stringArray) {
                        if (str2 != null) {
                            linkedHashSet.add(str2);
                        }
                    }
                }
                if (linkedHashSet.contains(str)) {
                    if (notificationTopListManageImpl.currentNotificationTopSet.size() >= context.getResources().getInteger(2131427707)) {
                        notificationTopListManageImpl.cancelNotificationTop(context, (String) CollectionsKt___CollectionsKt.first(notificationTopListManageImpl.currentNotificationTopSet));
                    }
                    notificationTopListManageImpl.setNotificationTop(context, NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(notificationTopListManageImpl.getCurrentUserId())));
                }
            }
        }
    }

    public static final void access$registerObserver(NotificationTopListManageImpl notificationTopListManageImpl, Context context) {
        if (notificationTopListManageImpl.isRegister) {
            return;
        }
        notificationTopListManageImpl.isRegister = true;
        notificationTopListManageImpl.notificationTopListObserver.registerForUserSwitch(context.getApplicationContext(), true);
        BuildersKt.launch$default(notificationTopListManageImpl.coroutineScope, notificationTopListManageImpl.getBackgroundDispatcher(), (CoroutineStart) null, new NotificationTopListManageImpl$initData$1(context, notificationTopListManageImpl, null), 2);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("oplus.intent.action.MULTI_APP_PACKAGE_REMOVED");
        intentFilter.addDataScheme(PACKAGE);
        BroadcastDispatcher.registerReceiver$default((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class), notificationTopListManageImpl.appChangeReceiver, intentFilter, notificationTopListManageImpl.mainExecutor, (UserHandle) null, 0, 56);
        CommercialBlackListManager companion = CommercialBlackListManager.Companion.getInstance(context);
        NotificationTopListManageImpl$$ExternalSyntheticLambda0 notificationTopListManageImpl$$ExternalSyntheticLambda0 = new NotificationTopListManageImpl$$ExternalSyntheticLambda0();
        notificationTopListManageImpl$$ExternalSyntheticLambda0.f$0 = notificationTopListManageImpl;
        VarHandle.storeStoreFence();
        companion.setAllowCommercialBlackListManager(notificationTopListManageImpl$$ExternalSyntheticLambda0);
    }

    public static final void access$updateNotifTopDiffSet(NotificationTopListManageImpl notificationTopListManageImpl) {
        notificationTopListManageImpl.diffNotificationTopSet.clear();
        notificationTopListManageImpl.diffNotificationTopSet.addAll(CollectionsKt___CollectionsKt.union(CollectionsKt___CollectionsKt.subtract(notificationTopListManageImpl.currentNotificationTopSet, notificationTopListManageImpl.preNotificationTopSet), CollectionsKt___CollectionsKt.subtract(notificationTopListManageImpl.preNotificationTopSet, notificationTopListManageImpl.currentNotificationTopSet)));
        notificationTopListManageImpl.preNotificationTopSet.clear();
        notificationTopListManageImpl.preNotificationTopSet.addAll(notificationTopListManageImpl.currentNotificationTopSet);
    }

    public static final void access$updateNotificationTopSet(NotificationTopListManageImpl notificationTopListManageImpl, Context context) {
        Set notificationTopListFromSettings = notificationTopListManageImpl.getNotificationTopListFromSettings(context);
        notificationTopListManageImpl.currentNotificationTopSet.clear();
        notificationTopListManageImpl.currentNotificationTopSet.addAll(notificationTopListFromSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineDispatcher getBackgroundDispatcher() {
        return (CoroutineDispatcher) this.backgroundDispatcher$delegate.getValue();
    }

    public static final NotificationTopListManage getInstance(Context context) {
        return Companion.getInstance(context);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public void addNotificationTopListListener(NotificationTopListListener notificationTopListListener) {
        this.notificationTopListListener = notificationTopListListener;
    }

    public final void cancelNotificationTop(Context context, String str) {
        if (this.currentNotificationTopSet.contains(str)) {
            this.currentNotificationTopSet.remove(str);
            BuildersKt.launch$default(this.coroutineScope, getBackgroundDispatcher(), (CoroutineStart) null, new cancelNotificationTop.1(context, this, (Continuation) null), 2);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationTopListManage-->cancelTopNotification");
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public int getCurrentUserId() {
        return this.userTracker.getUserId();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public Set<String> getNotificationTopDiffSet() {
        return this.diffNotificationTopSet;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public int getNotificationTopIndex(String str) {
        return CollectionsKt___CollectionsKt.indexOf(this.currentNotificationTopSet, str);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public Set<String> getNotificationTopList() {
        return this.currentNotificationTopSet;
    }

    public final Set getNotificationTopListFromSettings(Context context) {
        List listEmptyList;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String notificationTopString = NotificationTopSettingsValueProxy.Companion.getNotificationTopString(context, this.userTracker.getUserId());
        if (notificationTopString != null) {
            List listSplit = new Regex(",").split(notificationTopString, 0);
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

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public int getNotificationTopNum() {
        return this.currentNotificationTopSet.size();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public boolean isNotificationTop(String str, Integer num) {
        if (str == null || num == null) {
            return false;
        }
        return this.currentNotificationTopSet.contains(NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(UserHandle.getUserId(num.intValue()))));
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public void notificationTopBatchOperation(Context context, String str, Integer num, Set<String> set) {
        if (context == null || str == null || num == null || set == null) {
            return;
        }
        String notificationTopKey = NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(UserHandle.getUserId(num.intValue())));
        for (String str2 : set) {
            if (this.currentNotificationTopSet.contains(str2)) {
                this.currentNotificationTopSet.remove(str2);
            }
        }
        if (!this.currentNotificationTopSet.contains(notificationTopKey)) {
            this.currentNotificationTopSet.add(notificationTopKey);
        }
        BuildersKt.launch$default(this.coroutineScope, getBackgroundDispatcher(), (CoroutineStart) null, new notificationTopBatchOperation.2(context, this, (Continuation) null), 2);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage
    public void notificationTopOperation(Context context, String str, Integer num, boolean z) {
        if (context == null || str == null || num == null) {
            return;
        }
        String notificationTopKey = NotificationTopUtils.getNotificationTopKey(str, Integer.valueOf(UserHandle.getUserId(num.intValue())));
        if (z) {
            setNotificationTop(context, notificationTopKey);
        } else {
            cancelNotificationTop(context, notificationTopKey);
        }
    }

    public final void setNotificationTop(Context context, String str) {
        if (this.currentNotificationTopSet.contains(str)) {
            return;
        }
        this.currentNotificationTopSet.add(str);
        BuildersKt.launch$default(this.coroutineScope, getBackgroundDispatcher(), (CoroutineStart) null, new setNotificationTop.1(context, this, (Continuation) null), 2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationTopListManage-->setTopNotification");
        }
    }
}
