package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import com.android.systemui.statusbar.phone.domain.interactor.DarkIconInteractor;
import com.oplus.systemui.statusbar.icon.domain.OplusNotificationIconAreaInteractor;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationIconAreaViewModel";
    private final DarkIconInteractor darkIconInteractor;
    private final Flow icons;
    private final Flow notificationBadgeViewVisibility;
    private final Flow notificationIconsViewVisibility;
    private final Flow notificationNum;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationIconAreaViewModel(DarkIconInteractor darkIconInteractor, OplusNotificationIconAreaInteractor oplusNotificationIconAreaInteractor) {
        this.darkIconInteractor = darkIconInteractor;
        this.icons = FlowKt.transformLatest(oplusNotificationIconAreaInteractor.getNotificationIconAreaType(), new OplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1(null, oplusNotificationIconAreaInteractor, this));
        Flow notificationNum = oplusNotificationIconAreaInteractor.getNotificationNum();
        this.notificationNum = notificationNum;
        Flow notificationIconAreaType = oplusNotificationIconAreaInteractor.getNotificationIconAreaType();
        OplusNotificationIconAreaViewModel$special$$inlined$map$1 oplusNotificationIconAreaViewModel$special$$inlined$map$1 = new OplusNotificationIconAreaViewModel$special$$inlined$map$1(0);
        oplusNotificationIconAreaViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = notificationIconAreaType;
        VarHandle.storeStoreFence();
        this.notificationIconsViewVisibility = oplusNotificationIconAreaViewModel$special$$inlined$map$1;
        this.notificationBadgeViewVisibility = FlowKt.combine(notificationNum, oplusNotificationIconAreaInteractor.getNotificationIconAreaType(), new OplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1(3, null));
    }

    public final Flow badgeViewColors(int i) {
        Flow flowDarkState = this.darkIconInteractor.darkState(i);
        OplusNotificationIconAreaViewModel$special$$inlined$map$1 oplusNotificationIconAreaViewModel$special$$inlined$map$1 = new OplusNotificationIconAreaViewModel$special$$inlined$map$1(1);
        oplusNotificationIconAreaViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = flowDarkState;
        VarHandle.storeStoreFence();
        return FlowKt.distinctUntilChanged(FlowKt.conflate(oplusNotificationIconAreaViewModel$special$$inlined$map$1));
    }

    public final Flow getIcons() {
        return this.icons;
    }

    public final Flow getNotificationBadgeViewVisibility() {
        return this.notificationBadgeViewVisibility;
    }

    public final Flow getNotificationIconsViewVisibility() {
        return this.notificationIconsViewVisibility;
    }

    public final Flow getNotificationNum() {
        return this.notificationNum;
    }
}
