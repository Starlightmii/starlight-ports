package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HiddenIconDispatcherImpl implements HiddenIconDispatcher, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HiddenIconDispatcherImpl";
    private final Context context;
    private final DumpManager dumpManager;
    public boolean isDualSim;
    public boolean isStatusBarIconScaleDisallowed;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final CoroutineScope scope;
    private final ConcurrentHashMap<StatusBarLocation, ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver>> receiversMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<StatusBarLocation, CopyOnWriteArrayList<Rect>> hiddenAreasMap = new ConcurrentHashMap<>();
    private ArrayList<String> hiddenIcons = new ArrayList<>();
    private final ConcurrentHashMap<StatusBarLocation, Rect> sbPaddingMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<StatusBarLocation, Integer> lastRemainingWidthMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<StatusBarLocation, Boolean> shouldScaleMap = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.HiddenIconDispatcherImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.HiddenIconDispatcherImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01661 implements FlowCollector {
            public /* synthetic */ HiddenIconDispatcherImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                HiddenIconDispatcherImpl hiddenIconDispatcherImpl = this.this$0;
                hiddenIconDispatcherImpl.isDualSim = zBooleanValue;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("isDualSim: ", "HiddenIconDispatcherImpl-->", "SystemUi--Statusbar", hiddenIconDispatcherImpl.isDualSim);
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return HiddenIconDispatcherImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsDualSim = HiddenIconDispatcherImpl.this.oplusMobileIconsInteractor.isDualSim();
                HiddenIconDispatcherImpl hiddenIconDispatcherImpl = HiddenIconDispatcherImpl.this;
                C01661 c01661 = new C01661();
                c01661.this$0 = hiddenIconDispatcherImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsDualSim.collect(c01661, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public HiddenIconDispatcherImpl(OplusMobileIconsInteractor oplusMobileIconsInteractor, CoroutineScope coroutineScope, DumpManager dumpManager, Context context) {
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
        this.scope = coroutineScope;
        this.dumpManager = dumpManager;
        this.context = context;
        dumpManager.registerDumpable(this);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public void addHiddenReceiver(StatusBarLocation statusBarLocation, HiddenIconDispatcher.HiddenReceiver hiddenReceiver) {
        if (this.receiversMap.get(statusBarLocation) == null) {
            this.receiversMap.put(statusBarLocation, new ConcurrentHashMap<>());
        }
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        if (concurrentHashMap != null) {
            concurrentHashMap.put(hiddenReceiver, hiddenReceiver);
        }
        hiddenReceiver.onHiddenChanged(getHiddenAreas(statusBarLocation), getLastShouldScale(statusBarLocation));
        ArrayList<String> arrayList = this.hiddenIcons;
        hiddenReceiver.setHiddenIconListChanged(arrayList != null, arrayList);
    }

    public final void disallowStatusBarIconScale(boolean z) {
        if (this.isStatusBarIconScaleDisallowed != z) {
            this.isStatusBarIconScaleDisallowed = z;
            int i = this.context.getResources().getConfiguration().windowConfiguration.getBounds().right;
            ConcurrentHashMap<StatusBarLocation, Integer> concurrentHashMap = this.lastRemainingWidthMap;
            StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
            int i2 = i / 2;
            concurrentHashMap.put(statusBarLocation, Integer.valueOf(i2));
            ConcurrentHashMap<StatusBarLocation, Integer> concurrentHashMap2 = this.lastRemainingWidthMap;
            StatusBarLocation statusBarLocation2 = StatusBarLocation.KEYGUARD;
            concurrentHashMap2.put(statusBarLocation2, Integer.valueOf(i2));
            handHiddenChange(statusBarLocation);
            handHiddenChange(statusBarLocation2);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("HiddenIconDispatcherImpl state: ");
        printWriter.println("hiddenIcons: " + this.hiddenIcons);
        ConcurrentHashMap<StatusBarLocation, CopyOnWriteArrayList<Rect>> concurrentHashMap = this.hiddenAreasMap;
        StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m(" HOME=", concurrentHashMap.get(statusBarLocation), printWriter);
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap2 = this.receiversMap.get(statusBarLocation);
        if (concurrentHashMap2 != null) {
            Iterator<Map.Entry<Object, HiddenIconDispatcher.HiddenReceiver>> it = concurrentHashMap2.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().dump(printWriter, strArr);
            }
        }
        ConcurrentHashMap<StatusBarLocation, CopyOnWriteArrayList<Rect>> concurrentHashMap3 = this.hiddenAreasMap;
        StatusBarLocation statusBarLocation2 = StatusBarLocation.KEYGUARD;
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("\n Keyguard=", concurrentHashMap3.get(statusBarLocation2), printWriter);
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap4 = this.receiversMap.get(statusBarLocation2);
        if (concurrentHashMap4 != null) {
            Iterator<Map.Entry<Object, HiddenIconDispatcher.HiddenReceiver>> it2 = concurrentHashMap4.entrySet().iterator();
            while (it2.hasNext()) {
                it2.next().getValue().dump(printWriter, strArr);
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public ArrayList<Rect> getHiddenAreas(StatusBarLocation statusBarLocation) {
        Collection arrayList = (CopyOnWriteArrayList) this.hiddenAreasMap.get(statusBarLocation);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        return new ArrayList<>(arrayList);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public boolean getLastShouldScale(StatusBarLocation statusBarLocation) {
        if (!this.shouldScaleMap.getOrDefault(statusBarLocation, Boolean.FALSE).booleanValue()) {
            return false;
        }
        Context context = this.context;
        return DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context)) && !this.isStatusBarIconScaleDisallowed;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public List<HiddenIconDispatcher.HiddenReceiver> getReceivers(StatusBarLocation statusBarLocation) {
        Collection<HiddenIconDispatcher.HiddenReceiver> collectionValues;
        List<HiddenIconDispatcher.HiddenReceiver> list;
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        return (concurrentHashMap == null || (collectionValues = concurrentHashMap.values()) == null || (list = CollectionsKt___CollectionsKt.toList(collectionValues)) == null) ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    public final void handHiddenChange(StatusBarLocation statusBarLocation) {
        Collection arrayList = (CopyOnWriteArrayList) this.hiddenAreasMap.get(statusBarLocation);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        ArrayList<Rect> arrayList2 = new ArrayList<>((Collection<? extends Rect>) arrayList);
        if (HiddenIconDispatcher.Companion.isValid(arrayList2)) {
            Context context = this.context;
            DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "HiddenIconDispatcherImpl-->".concat("handHiddenChange: false"));
        }
        this.shouldScaleMap.put(statusBarLocation, Boolean.FALSE);
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        if (concurrentHashMap != null) {
            Iterator<Map.Entry<Object, HiddenIconDispatcher.HiddenReceiver>> it = concurrentHashMap.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onHiddenChanged(arrayList2, false);
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public boolean isRegistered(StatusBarLocation statusBarLocation, HiddenIconDispatcher.HiddenReceiver hiddenReceiver) {
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        return (concurrentHashMap != null ? concurrentHashMap.get(hiddenReceiver) : null) != null;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public /* bridge */ /* synthetic */ HiddenIconDispatcher.HiddenReceiver observe(Lifecycle lifecycle, StatusBarLocation statusBarLocation, HiddenIconDispatcher.HiddenReceiver hiddenReceiver) {
        return super.observe(lifecycle, statusBarLocation, hiddenReceiver);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public void removeHiddenReceiver(StatusBarLocation statusBarLocation, HiddenIconDispatcher.HiddenReceiver hiddenReceiver) {
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(hiddenReceiver);
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public void setHiddenIconAreas(StatusBarLocation statusBarLocation, List<Rect> list) {
        CopyOnWriteArrayList<Rect> copyOnWriteArrayList;
        CopyOnWriteArrayList<Rect> copyOnWriteArrayList2 = this.hiddenAreasMap.get(statusBarLocation);
        if (copyOnWriteArrayList2 == null) {
            this.hiddenAreasMap.put(statusBarLocation, new CopyOnWriteArrayList<>());
            if (list != null && (copyOnWriteArrayList = this.hiddenAreasMap.get(statusBarLocation)) != null) {
                copyOnWriteArrayList.addAll(list);
            }
        } else {
            copyOnWriteArrayList2.clear();
            if (list != null) {
                copyOnWriteArrayList2.addAll(list);
            }
        }
        handHiddenChange(statusBarLocation);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public void setHiddenStatusBarIcon(StatusBarLocation statusBarLocation, ArrayList<String> arrayList) {
        ArrayList<String> arrayList2;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "setHiddenStatusBarIcon, location: " + statusBarLocation + ", hiddenIconlist: " + arrayList, null, 8);
        ArrayList<String> arrayList3 = this.hiddenIcons;
        if (arrayList3 != null) {
            arrayList3.clear();
        }
        if (arrayList != null && (arrayList2 = this.hiddenIcons) != null) {
            arrayList2.addAll(arrayList);
        }
        ConcurrentHashMap<Object, HiddenIconDispatcher.HiddenReceiver> concurrentHashMap = this.receiversMap.get(statusBarLocation);
        if (concurrentHashMap != null) {
            Iterator<Map.Entry<Object, HiddenIconDispatcher.HiddenReceiver>> it = concurrentHashMap.entrySet().iterator();
            while (it.hasNext()) {
                HiddenIconDispatcher.HiddenReceiver value = it.next().getValue();
                ArrayList<String> arrayList4 = this.hiddenIcons;
                if (arrayList != null) {
                    value.setHiddenIconListChanged(true, arrayList4);
                } else {
                    value.setHiddenIconListChanged(false, arrayList4);
                }
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public void setStatusBarPaddingRect(StatusBarLocation statusBarLocation, Rect rect) {
        this.sbPaddingMap.put(statusBarLocation, rect);
        handHiddenChange(statusBarLocation);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher
    public /* bridge */ /* synthetic */ HiddenIconDispatcher.HiddenReceiver observe(LifecycleOwner lifecycleOwner, StatusBarLocation statusBarLocation, HiddenIconDispatcher.HiddenReceiver hiddenReceiver) {
        return super.observe(lifecycleOwner, statusBarLocation, hiddenReceiver);
    }
}
