package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.FileProvider$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.Dumpable;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.doze.DozeLogger$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationOptions;
import com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction;
import com.oplus.systemui.statusbar.notification.livealert.data.model.SbnModel;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginCallUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsRepository implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String KEY_OPLUS_LIVE_ALERT_APP_CONFIG = "oplusLiveAlertAppConfig";
    private static final String METHOD_INIT_NOTIFICATIONS = "initNotifications";
    private static final String METHOD_INTERCEPTOR_HUN = "interceptorHun";
    private static final String METHOD_NOTIFICATION_ADD = "notificationAdd";
    private static final String METHOD_NOTIFICATION_REMOVE = "notificationRemove";
    private static final String METHOD_NOTIFICATION_UPDATE = "notificationUpdate";
    private static final String PARAMS_RESULT = "result";
    private static final String PARAMS_SBN = "sbn";
    private static final String PARAMS_SBN_LIST = "sbnList";
    private static final String TAG = "OplusLiveAlertNotificationsRepository";
    private final MutableStateFlow _liveAlerts;
    private final MutableSharedFlow _loadPlugin;
    private final Context context;
    private List<? extends PipelineEntry> entries;
    private OplusLiveAlertFilters filters;
    private final List<SbnModel> liveAlertList;
    private final StateFlow liveAlerts;
    private final SharedFlow loadPlugin;
    private final NotifCollection notifCollection;
    private final OplusLiveAlertNotificationsRepository$notifCollectionListener$1 notifCollectionListener;
    private final OnBeforeRenderListListener notifTracker;
    private SeedlingPlugin plugin;
    public int previousEntriesSize;
    private final CoroutineScope scope;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertNotificationsRepository$setFilter$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IConditionListener {
        public /* synthetic */ OplusLiveAlertNotificationsRepository this$0;

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.IConditionListener
        public final void onConditionChanged(String str) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusLiveAlertNotificationsRepository.TAG, FileProvider$.ExternalSyntheticOutline0.m("onConditionChanged: ", str), null, 8);
            this.this$0.reInitLiveAlert();
        }
    }

    public OplusLiveAlertNotificationsRepository(Context context, CoroutineScope coroutineScope, NotifCollection notifCollection, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, DumpManager dumpManager) {
        this.context = context;
        this.scope = coroutineScope;
        this.notifCollection = notifCollection;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(LiveAlertAction.Default.INSTANCE);
        this._liveAlerts = MutableStateFlow;
        this.liveAlertList = new ArrayList();
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this._loadPlugin = mutableSharedFlowMutableSharedFlow$default;
        this.liveAlerts = FlowKt.asStateFlow(MutableStateFlow);
        this.loadPlugin = mutableSharedFlowMutableSharedFlow$default;
        this.entries = CollectionsKt__CollectionsKt.emptyList();
        this.previousEntriesSize = -1;
        OplusLiveAlertNotificationsRepository$notifTracker$1 oplusLiveAlertNotificationsRepository$notifTracker$1 = new OplusLiveAlertNotificationsRepository$notifTracker$1();
        oplusLiveAlertNotificationsRepository$notifTracker$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifTracker = oplusLiveAlertNotificationsRepository$notifTracker$1;
        OplusLiveAlertNotificationsRepository$notifCollectionListener$1 oplusLiveAlertNotificationsRepository$notifCollectionListener$1 = new OplusLiveAlertNotificationsRepository$notifCollectionListener$1();
        oplusLiveAlertNotificationsRepository$notifCollectionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifCollectionListener = oplusLiveAlertNotificationsRepository$notifCollectionListener$1;
        dumpManager.registerCriticalDumpable(this);
    }

    public static final int access$entryToReminderType(OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, NotificationEntry notificationEntry, boolean z) {
        if (z && notificationEntry.getSbn().getNotification().fullScreenIntent != null) {
            return 13;
        }
        if (z && notificationEntry.isStickyAndNotDemoted()) {
            return 12;
        }
        return z ? 11 : 0;
    }

    public static final void access$notifyNotificationRemove(OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, StatusBarNotification statusBarNotification) {
        Bundle bundle = new Bundle();
        bundle.putString("method", METHOD_NOTIFICATION_REMOVE);
        bundle.putParcelable(PARAMS_SBN, statusBarNotification);
        SeedlingPlugin seedlingPlugin = oplusLiveAlertNotificationsRepository.plugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.call(bundle);
        }
    }

    public static final void access$processNotificationEntry(OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, NotificationEntry notificationEntry) {
        int notifyState = notificationEntry.getEx().getNotifyState();
        if (notifyState == 0) {
            StatusBarNotification sbn = notificationEntry.getSbn();
            int importance = notificationEntry.getImportance();
            boolean showHeadsUp = notificationEntry.getEx().getShowHeadsUp();
            Bundle bundle = new Bundle();
            bundle.putString("method", METHOD_NOTIFICATION_ADD);
            bundle.putInt(SeedlingPluginCallUtils.PARAM_NOTIFICATION_IMPORTANCE, importance);
            bundle.putBoolean(SeedlingPluginCallUtils.PARAM_SHOW_HUN, showHeadsUp);
            bundle.putParcelable(PARAMS_SBN, sbn);
            SeedlingPlugin seedlingPlugin = oplusLiveAlertNotificationsRepository.plugin;
            if (seedlingPlugin != null) {
                seedlingPlugin.call(bundle);
            }
        } else if (notifyState == 1) {
            StatusBarNotification sbn2 = notificationEntry.getSbn();
            int importance2 = notificationEntry.getImportance();
            boolean showHeadsUp2 = notificationEntry.getEx().getShowHeadsUp();
            Bundle bundle2 = new Bundle();
            bundle2.putString("method", METHOD_NOTIFICATION_UPDATE);
            bundle2.putInt(SeedlingPluginCallUtils.PARAM_NOTIFICATION_IMPORTANCE, importance2);
            bundle2.putBoolean(SeedlingPluginCallUtils.PARAM_SHOW_HUN, showHeadsUp2);
            bundle2.putParcelable(PARAMS_SBN, sbn2);
            SeedlingPlugin seedlingPlugin2 = oplusLiveAlertNotificationsRepository.plugin;
            if (seedlingPlugin2 != null) {
                seedlingPlugin2.call(bundle2);
            }
        }
        notificationEntry.getEx().setNotifyState(-1);
        notificationEntry.getEx().setShowHeadsUp(false);
    }

    public static final void access$removeLiveAlert(OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, NotificationEntry notificationEntry) {
        Object next;
        Object value;
        Iterator<T> it = oplusLiveAlertNotificationsRepository.liveAlertList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SbnModel) next).getSbn().getKey(), notificationEntry.getKey()));
        SbnModel sbnModel = (SbnModel) next;
        if (sbnModel != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "removeLiveAlert: " + notificationEntry.getKey() + " " + notificationEntry.getSbn().getPostTime(), null, 8);
            List<SbnModel> list = oplusLiveAlertNotificationsRepository.liveAlertList;
            OplusLiveAlertNotificationsRepository$removeLiveAlert$2$1 oplusLiveAlertNotificationsRepository$removeLiveAlert$2$1 = new OplusLiveAlertNotificationsRepository$removeLiveAlert$2$1();
            oplusLiveAlertNotificationsRepository$removeLiveAlert$2$1.$entry = notificationEntry;
            VarHandle.storeStoreFence();
            list.removeIf(oplusLiveAlertNotificationsRepository$removeLiveAlert$2$1);
            StateFlowImpl stateFlowImpl = oplusLiveAlertNotificationsRepository._liveAlerts;
            do {
                value = stateFlowImpl.getValue();
            } while (!stateFlowImpl.compareAndSet(value, new LiveAlertAction.RemoveAction(CollectionsKt__CollectionsKt.arrayListOf(new StatusBarNotification[]{sbnModel.getSbn()}))));
        }
    }

    public static final void access$updateLiveAlert(OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, NotificationEntry notificationEntry, int i, int i2, boolean z) {
        Object value;
        StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(notificationEntry.getSbn().getPostTime(), "updateLiveAlert: ", notificationEntry.getKey(), " ");
        sbM.append(" remindType=");
        sbM.append(i2);
        sbM.append(" shouldReminderAgain=");
        sbM.append(z);
        Object obj = null;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        List<SbnModel> list = oplusLiveAlertNotificationsRepository.liveAlertList;
        OplusLiveAlertNotificationsRepository$updateLiveAlert$1 oplusLiveAlertNotificationsRepository$updateLiveAlert$1 = new OplusLiveAlertNotificationsRepository$updateLiveAlert$1();
        oplusLiveAlertNotificationsRepository$updateLiveAlert$1.$entry = notificationEntry;
        oplusLiveAlertNotificationsRepository$updateLiveAlert$1.this$0 = oplusLiveAlertNotificationsRepository;
        oplusLiveAlertNotificationsRepository$updateLiveAlert$1.$entryFlag = i;
        oplusLiveAlertNotificationsRepository$updateLiveAlert$1.$remindType = i2;
        oplusLiveAlertNotificationsRepository$updateLiveAlert$1.$shouldReminderAgain = z;
        VarHandle.storeStoreFence();
        list.replaceAll(oplusLiveAlertNotificationsRepository$updateLiveAlert$1);
        for (Object obj2 : oplusLiveAlertNotificationsRepository.liveAlertList) {
            if (Intrinsics.areEqual(((SbnModel) obj2).getSbn().getKey(), notificationEntry.getKey())) {
                obj = obj2;
                break;
            }
        }
        SbnModel sbnModel = (SbnModel) obj;
        if (sbnModel != null) {
            StateFlowImpl stateFlowImpl = oplusLiveAlertNotificationsRepository._liveAlerts;
            do {
                value = stateFlowImpl.getValue();
            } while (!stateFlowImpl.compareAndSet(value, new LiveAlertAction.PostAction(CollectionsKt__CollectionsKt.arrayListOf(new StatusBarNotification[]{sbnModel.getSbn()}))));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLiveAlerts(List<? extends NotificationEntry> list) {
        int i;
        int size = list.size();
        if (size != this.previousEntriesSize) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(size, "setLiveAlerts: "), null, 8);
            this.previousEntriesSize = size;
        }
        this.liveAlertList.clear();
        List<? extends NotificationEntry> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            NotificationEntry notificationEntry = (NotificationEntry) it.next();
            OplusLiveAlertFilters oplusLiveAlertFilters = this.filters;
            arrayList.add(entryToLiveAlert(notificationEntry, oplusLiveAlertFilters != null ? oplusLiveAlertFilters.filterEntry(notificationEntry) : 0, 0, true));
        }
        this.liveAlertList.addAll(arrayList);
        MutableStateFlow mutableStateFlow = this._liveAlerts;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size2 = arrayList.size();
        while (i < size2) {
            Object obj = arrayList.get(i);
            i++;
            arrayList2.add(((SbnModel) obj).getSbn());
        }
        mutableStateFlow.setValue(new LiveAlertAction.InitAction(new ArrayList(arrayList2)));
    }

    public final void addLiveAlert(NotificationEntry notificationEntry, int i, int i2, boolean z) {
        Object value;
        StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(notificationEntry.getSbn().getPostTime(), "addLiveAlert: ", notificationEntry.getKey(), " ");
        sbM.append(" remindType=");
        sbM.append(i2);
        sbM.append(" shouldReminderAgain=");
        sbM.append(z);
        sbM.append(", entryFlag=");
        sbM.append(i);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        SbnModel sbnModelEntryToLiveAlert = entryToLiveAlert(notificationEntry, i, i2, !(i2 == 11 || i2 == 12 || i2 == 13) || z);
        this.liveAlertList.add(sbnModelEntryToLiveAlert);
        StateFlowImpl stateFlowImpl = this._liveAlerts;
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, new LiveAlertAction.PostAction(CollectionsKt__CollectionsKt.arrayListOf(new StatusBarNotification[]{sbnModelEntryToLiveAlert.getSbn()}))));
    }

    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addCollectionListener(this.notifCollectionListener);
        notifPipeline.addOnBeforeRenderListListener(this.notifTracker);
    }

    public final void dismissLiveAlert(String str) {
        dismissLiveAlert.1 r0 = new dismissLiveAlert.1();
        r0.this$0 = this;
        r0.$key = str;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        CharSequence charSequence;
        CharSequence charSequence2;
        printWriter.println("OplusLiveAlertNotificationsRepository:");
        printWriter.println("\tfilters: " + this.filters);
        printWriter.println("\tplugin: " + this.plugin);
        printWriter.println("\tliveAlertList:");
        for (SbnModel sbnModel : this.liveAlertList) {
            Bundle bundle = sbnModel.getSbn().getNotification().extras;
            CharSequence charSequence3 = "";
            if (bundle == null || (charSequence = bundle.getCharSequence("android.title")) == null) {
                charSequence = "";
            }
            Bundle bundle2 = sbnModel.getSbn().getNotification().extras;
            if (bundle2 != null && (charSequence2 = bundle2.getCharSequence("android.text")) != null) {
                charSequence3 = charSequence2;
            }
            String key = sbnModel.getSbn().getKey();
            int entryFlag = sbnModel.getEntryFlag();
            Class notificationStyle = sbnModel.getSbn().getNotification().getNotificationStyle();
            String channelId = sbnModel.getSbn().getNotification().getChannelId();
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(entryFlag, "\t\t", key, " ", " ");
            sbM.append(notificationStyle);
            sbM.append(" ");
            sbM.append(channelId);
            sbM.append(" ");
            sbM.append((Object) charSequence);
            sbM.append(" ");
            sbM.append((Object) charSequence3);
            printWriter.println(sbM.toString());
        }
        OplusLiveAlertFilters oplusLiveAlertFilters = this.filters;
        if (oplusLiveAlertFilters != null) {
            oplusLiveAlertFilters.dump(printWriter, strArr);
        }
    }

    public final SbnModel entryToLiveAlert(NotificationEntry notificationEntry, int i, int i2, boolean z) {
        Bundle bundle = notificationEntry.getSbn().getNotification().extras;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isDismissible", Boolean.valueOf(notificationEntry.getSbn().isNonDismissable() ? false : OplusNotificationCommercializeControl.isCommercializeDismissAble(this.context, notificationEntry)));
        linkedHashMap.put("isAutoCancel", Boolean.valueOf(((notificationEntry.getSbn().getNotification().flags & 16) == 0 || notificationEntry.getSbn().getNotification().contentIntent == null) ? false : true));
        linkedHashMap.put("liveAlertService", bundle.getString("liveAlertService", ""));
        linkedHashMap.put("immersiveCardType", Integer.valueOf(bundle.getInt("immersiveCardType", -1)));
        linkedHashMap.put("requestLockScreenImmersive", Integer.valueOf(bundle.getInt("requestLockScreenImmersive", -1)));
        linkedHashMap.put("ignoreEntry", Integer.valueOf(bundle.getInt("ignoreEntry", 0)));
        String packageName = notificationEntry.getSbn().getPackageName();
        ArrayList arrayListArrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{8});
        int i3 = i & 2;
        if (i3 > 0) {
            arrayListArrayListOf.add(16);
        }
        if ((i & 4) > 0) {
            arrayListArrayListOf.add(4);
        }
        ArrayList arrayList = new ArrayList();
        if (i3 > 0) {
            arrayList.add(16);
        }
        try {
            bundle.putString("oplusLiveAlertOptions", new Gson().toJson(new OplusLiveAlertNotificationOptions(z, packageName, i2, arrayListArrayListOf, arrayList, linkedHashMap)));
        } catch (JsonIOException e) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "entryToLiveAlert failed: " + e);
        }
        return new SbnModel(notificationEntry.getSbn(), i);
    }

    public final int filterNotificationEntry(NotificationEntry notificationEntry, String str) {
        if (this.plugin == null && FeatureOption.isLiteLiveAlertDevice()) {
            StatusBarNotification sbn = notificationEntry.getSbn();
            Notification notification = sbn.getNotification();
            Bundle bundle = notification.extras;
            String string = bundle != null ? bundle.getString(KEY_OPLUS_LIVE_ALERT_APP_CONFIG) : null;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, DozeLogger$.ExternalSyntheticOutline0.m("loadPluginIfNeeded isPromotedOngoing: ", " liveAlertConfig=", string != null ? StringsKt___StringsKt.take(string, 10) : null, notification.isPromotedOngoing()), null, 8);
            if (notification.isPromotedOngoing() || string != null) {
                BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new loadPluginIfNeeded.1(this, sbn, (Continuation) null), 3);
            }
        }
        OplusLiveAlertFilters oplusLiveAlertFilters = this.filters;
        int iFilterEntry = oplusLiveAlertFilters != null ? oplusLiveAlertFilters.filterEntry(notificationEntry) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(iFilterEntry, "OplusLiveAlertNotificationsRepository-->", "SystemUi--Notification", ExifInterface$.ExternalSyntheticOutline1.m("filterNotificationEntry ", str, ", ", notificationEntry.getSbn().getKey(), " "));
        }
        return iFilterEntry;
    }

    public final StatusBarNotification getLiveAlertNotification(String str) {
        Object next;
        Iterator<T> it = this.liveAlertList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SbnModel) next).getSbn().getKey(), str));
        SbnModel sbnModel = (SbnModel) next;
        if (sbnModel != null) {
            return sbnModel.getSbn();
        }
        return null;
    }

    public final StateFlow getLiveAlerts() {
        return this.liveAlerts;
    }

    public final SharedFlow getLoadPlugin() {
        return this.loadPlugin;
    }

    public final boolean hasStatusBarLiveAlert(NotificationEntry notificationEntry) {
        Object next;
        Iterator<T> it = this.liveAlertList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SbnModel) next).getSbn().getKey(), notificationEntry.getKey()));
        SbnModel sbnModel = (SbnModel) next;
        return sbnModel != null && (sbnModel.getEntryFlag() & 1) > 0;
    }

    public final boolean interceptorHun(NotificationEntry notificationEntry) {
        Bundle bundleCall;
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", METHOD_INTERCEPTOR_HUN);
        bundleM.putParcelable(PARAMS_SBN, notificationEntry.getSbn());
        SeedlingPlugin seedlingPlugin = this.plugin;
        if (seedlingPlugin == null || (bundleCall = seedlingPlugin.call(bundleM)) == null) {
            return false;
        }
        return bundleCall.getBoolean(PARAMS_RESULT, false);
    }

    public final boolean isLiveAlert(NotificationEntry notificationEntry, boolean z) {
        Object next;
        int iFilterNotificationEntry;
        Iterator<T> it = this.liveAlertList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SbnModel) next).getSbn().getKey(), notificationEntry.getKey()));
        SbnModel sbnModel = (SbnModel) next;
        if (sbnModel != null) {
            iFilterNotificationEntry = sbnModel.getEntryFlag();
        } else {
            iFilterNotificationEntry = z ? filterNotificationEntry(notificationEntry, "checkNotification") : 0;
        }
        return (iFilterNotificationEntry & 2) > 0;
    }

    public final void reInitLiveAlert() {
        int iFilterEntry;
        OplusLiveAlertFilters oplusLiveAlertFilters = this.filters;
        if (oplusLiveAlertFilters != null) {
            oplusLiveAlertFilters.clearCache$1();
        }
        Collection allNotifs = this.notifCollection.getAllNotifs();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNotifs) {
            NotificationEntry notificationEntry = (NotificationEntry) obj;
            OplusLiveAlertFilters oplusLiveAlertFilters2 = this.filters;
            if (oplusLiveAlertFilters2 != null) {
                notificationEntry.getClass();
                iFilterEntry = oplusLiveAlertFilters2.filterEntry(notificationEntry);
            } else {
                iFilterEntry = 0;
            }
            if ((iFilterEntry & 1) > 0) {
                arrayList.add(obj);
            }
        }
        setLiveAlerts(arrayList);
    }

    public final void setFilter(OplusLiveAlertFilters oplusLiveAlertFilters) {
        this.filters = oplusLiveAlertFilters;
        if (oplusLiveAlertFilters != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusLiveAlertFilters.setConditionListener(anonymousClass1);
        }
        reInitLiveAlert();
    }

    public final void setPlugin(SeedlingPlugin seedlingPlugin) {
        this.plugin = seedlingPlugin;
        OplusLiveAlertFilters oplusLiveAlertFilters = this.filters;
        if (oplusLiveAlertFilters != null) {
            oplusLiveAlertFilters.clearCache$1();
        }
        List<? extends PipelineEntry> list = this.entries;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((PipelineEntry) obj) instanceof NotificationEntry) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            NotificationEntry notificationEntry = (PipelineEntry) obj2;
            NotificationEntry notificationEntry2 = notificationEntry instanceof NotificationEntry ? notificationEntry : null;
            StatusBarNotification sbn = notificationEntry2 != null ? notificationEntry2.getSbn() : null;
            if (sbn != null) {
                arrayList2.add(sbn);
            }
        }
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", METHOD_INIT_NOTIFICATIONS);
        bundleM.putParcelableArrayList(PARAMS_SBN_LIST, new ArrayList<>(arrayList2));
        SeedlingPlugin seedlingPlugin2 = this.plugin;
        if (seedlingPlugin2 != null) {
            seedlingPlugin2.call(bundleM);
        }
    }
}
