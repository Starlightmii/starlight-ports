package com.oplus.systemui.notification.lockscreen.capsule.modle;

import androidx.lifecycle.ViewModel;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationViewModel;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleViewModel extends ViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleViewModel";
    private final StateFlow capsuleData;
    private List<? extends CapsuleItemData> lastCapsuleData = CollectionsKt__CollectionsKt.emptyList();
    private final CoroutineScope mainScope;
    private final CapsuleNotificationViewModel notifViewModel;
    private final SeedlingViewModel seedlingViewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleViewModel(CoroutineScope coroutineScope, CapsuleNotificationViewModel capsuleNotificationViewModel, SeedlingViewModel seedlingViewModel) {
        this.mainScope = coroutineScope;
        this.notifViewModel = capsuleNotificationViewModel;
        this.seedlingViewModel = seedlingViewModel;
        this.capsuleData = FlowKt.stateIn(FlowKt.combine(capsuleNotificationViewModel.getDataFlow(), seedlingViewModel.getDataFlow(), CapsuleDataChangeInterceptor.Companion.getInterceptorFlow(), new CapsuleViewModel$capsuleData$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), CollectionsKt__CollectionsKt.emptyList());
    }

    public final StateFlow getCapsuleData() {
        return this.capsuleData;
    }

    public final boolean hasSeedingData() {
        List<? extends CapsuleItemData> list = this.lastCapsuleData;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((CapsuleItemData) it.next()) instanceof CapsuleSeedingData) {
                return true;
            }
        }
        return false;
    }
}
