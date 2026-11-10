package com.oplus.systemui.notification.lockscreen.seedling;

import android.content.Context;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.lockscreen.base.CapsuleBaseViewModel;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$initCapsule$1;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingViewModel extends CapsuleBaseViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeedlingViewModel";
    private final Context context;
    private final OplusCustomRowControllerManager oplusCustomRowControllerManager;
    private final NotifPipeline pipeline;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface SeedingStateCallback {
        void onRequestImmersiveState(boolean z, String str, int i, OplusCustomRow oplusCustomRow);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel$setSeedingStateCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusCustomRowControllerManager.RequestImmersiveStateListener {
        public /* synthetic */ SeedingStateCallback $listener;
        public /* synthetic */ SeedlingViewModel this$0;

        @Override // com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager.RequestImmersiveStateListener
        public final void onRequestImmersiveState(int i, int i2, String str) {
            OplusCustomRow oplusCustomRowFindStackView = this.this$0.findStackView(str);
            if (oplusCustomRowFindStackView == null) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "onRequestImmersiveState: can not find item: ", str, LogUtil.TAG_NOTIFICATION, SeedlingViewModel.TAG);
            } else {
                ((LockScreenNotificationViewBinder$initCapsule$1) this.$listener).onRequestImmersiveState(i == 1, KeyguardLogger$.ExternalSyntheticOutline0.m("seedingRequest(", i == 1, "):", i2), i2, oplusCustomRowFindStackView);
            }
        }
    }

    public SeedlingViewModel(Context context, NotifPipeline notifPipeline, OplusCustomRowControllerManager oplusCustomRowControllerManager) {
        super(notifPipeline, CollectionsKt__CollectionsKt.emptyList());
        this.context = context;
        this.pipeline = notifPipeline;
        this.oplusCustomRowControllerManager = oplusCustomRowControllerManager;
    }

    public final OplusCustomRow findStackView(String str) {
        Object next;
        CustomRowEntry entry;
        if (str == null) {
            return null;
        }
        Iterator<T> it = this.oplusCustomRowControllerManager.getOplusCustomRowControllerList().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            entry = ((OplusCustomRowController) next).getEntry();
        } while (!Intrinsics.areEqual(entry != null ? entry.getKey() : null, str));
        OplusCustomRowController oplusCustomRowController = (OplusCustomRowController) next;
        if (oplusCustomRowController != null) {
            return oplusCustomRowController.getView();
        }
        return null;
    }

    public final boolean isRemoved(String str) {
        return findStackView(str) == null;
    }

    @Override // com.oplus.systemui.notification.lockscreen.base.CapsuleBaseViewModel
    public void refreshCapsuleData(List<? extends PipelineEntry> list, Function1 function1) {
        List<OplusCustomRowController> oplusCustomRowControllerList = this.oplusCustomRowControllerManager.getOplusCustomRowControllerList();
        ArrayList arrayList = new ArrayList();
        for (OplusCustomRowController oplusCustomRowController : oplusCustomRowControllerList) {
            CustomRowEntry entry = oplusCustomRowController.getEntry();
            Pair pair = entry != null ? TuplesKt.to(entry, oplusCustomRowController.getOplusCustomRow()) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List<Pair> listSortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList, new SeedlingViewModel$refreshCapsuleData$$inlined$sortedByDescending$1());
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSortedWith, 10));
        for (Pair pair2 : listSortedWith) {
            arrayList2.add(CapsuleSeedingData.Companion.from((CustomRowEntry) pair2.getFirst(), this.context, (OplusCustomRow) pair2.getSecond()));
        }
        function1.invoke(arrayList2);
    }

    public final void setSeedingStateCallback(SeedingStateCallback seedingStateCallback) {
        OplusCustomRowControllerManager oplusCustomRowControllerManager = this.oplusCustomRowControllerManager;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$listener = seedingStateCallback;
        VarHandle.storeStoreFence();
        oplusCustomRowControllerManager.setRequestImmersiveStateListener(anonymousClass1);
    }
}
