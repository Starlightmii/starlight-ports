package com.oplus.systemui.statusbar.icon.domain;

import com.android.systemui.statusbar.notification.icon.domain.interactor.StatusBarNotificationIconsInteractor;
import com.oplus.systemui.statusbar.icon.data.OplusNotificationIconAreaRepository;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaInteractor {
    public static final Companion Companion = new Companion();
    private MutableStateFlow maxIconSize;
    private final Flow notificationIconAreaType;
    private final Flow notificationNum;
    private final Flow oplusStatusBarNotifs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationIconAreaInteractor(StatusBarNotificationIconsInteractor statusBarNotificationIconsInteractor, OplusNotificationIconAreaRepository oplusNotificationIconAreaRepository) {
        Flow statusBarNotifs = statusBarNotificationIconsInteractor.getStatusBarNotifs();
        OplusNotificationIconAreaInteractor$special$$inlined$map$1 oplusNotificationIconAreaInteractor$special$$inlined$map$1 = new OplusNotificationIconAreaInteractor$special$$inlined$map$1();
        oplusNotificationIconAreaInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = statusBarNotifs;
        VarHandle.storeStoreFence();
        this.oplusStatusBarNotifs = oplusNotificationIconAreaInteractor$special$$inlined$map$1;
        Flow statusBarNotifs2 = statusBarNotificationIconsInteractor.getStatusBarNotifs();
        OplusNotificationIconAreaInteractor$special$$inlined$map$2 oplusNotificationIconAreaInteractor$special$$inlined$map$2 = new OplusNotificationIconAreaInteractor$special$$inlined$map$2();
        oplusNotificationIconAreaInteractor$special$$inlined$map$2.$this_unsafeTransform$inlined = statusBarNotifs2;
        oplusNotificationIconAreaInteractor$special$$inlined$map$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.notificationNum = oplusNotificationIconAreaInteractor$special$$inlined$map$2;
        this.notificationIconAreaType = oplusNotificationIconAreaRepository.getNotificationIconAreaType();
        FlavorTwoFeatureOption.INSTANCE.getClass();
        this.maxIconSize = StateFlowKt.MutableStateFlow(Integer.valueOf(FlavorTwoFeatureOption.isFlavorTwoDevice() ? 999 : 5));
    }

    public final MutableStateFlow getMaxIconSize() {
        return this.maxIconSize;
    }

    public final Flow getNotificationIconAreaType() {
        return this.notificationIconAreaType;
    }

    public final Flow getNotificationNum() {
        return this.notificationNum;
    }

    public final Flow getOplusStatusBarNotifs() {
        return this.oplusStatusBarNotifs;
    }

    public final void setMaxIconSize(MutableStateFlow mutableStateFlow) {
        this.maxIconSize = mutableStateFlow;
    }
}
