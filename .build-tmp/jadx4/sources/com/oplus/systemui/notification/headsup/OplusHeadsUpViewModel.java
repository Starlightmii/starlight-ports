package com.oplus.systemui.notification.headsup;

import android.content.Context;
import com.android.systemui.shade.OplusNotificationShadeWindowControllerEx;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.AnimationStateHandler;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import com.android.systemui.util.JankUtils;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpViewModel implements OnHeadsUpChangedListener {
    private final MutableStateFlow _headsUpGoingAwayAnimationsAllowed;
    private final MutableStateFlow _headsUpViewState;
    private final Context context;
    private final Lazy headsUpAnimationsEnabled$delegate;
    private final StateFlow headsUpGoingAwayAnimationsAllowed;
    private final HeadsUpManager headsUpManager;
    private final Flow headsUpViewState;
    private final dagger.Lazy notificationShadeWindowControllerEx;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpViewModel$initHeadsUp$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements AnimationStateHandler {
        public /* synthetic */ OplusHeadsUpViewModel this$0;

        public void setHeadsUpGoingAwayAnimationsAllowed(boolean z) {
            this.this$0._headsUpGoingAwayAnimationsAllowed.setValue(Boolean.valueOf(z));
        }
    }

    public OplusHeadsUpViewModel(HeadsUpManager headsUpManager, NotificationStackInteractor notificationStackInteractor, ShadeInteractor shadeInteractor, Context context, dagger.Lazy lazy) {
        this.headsUpManager = headsUpManager;
        this.context = context;
        this.notificationShadeWindowControllerEx = lazy;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._headsUpGoingAwayAnimationsAllowed = MutableStateFlow;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(TuplesKt.to((Object) null, bool));
        this._headsUpViewState = MutableStateFlow2;
        this.headsUpGoingAwayAnimationsAllowed = MutableStateFlow;
        this.headsUpViewState = MutableStateFlow2;
        OplusHeadsUpViewModel$$ExternalSyntheticLambda0 oplusHeadsUpViewModel$$ExternalSyntheticLambda0 = new OplusHeadsUpViewModel$$ExternalSyntheticLambda0();
        oplusHeadsUpViewModel$$ExternalSyntheticLambda0.f$0 = notificationStackInteractor;
        oplusHeadsUpViewModel$$ExternalSyntheticLambda0.f$1 = shadeInteractor;
        VarHandle.storeStoreFence();
        this.headsUpAnimationsEnabled$delegate = LazyKt__LazyJVMKt.lazy(oplusHeadsUpViewModel$$ExternalSyntheticLambda0);
    }

    public final Flow getHeadsUpAnimationsEnabled() {
        return (Flow) this.headsUpAnimationsEnabled$delegate.getValue();
    }

    public final StateFlow getHeadsUpGoingAwayAnimationsAllowed() {
        return this.headsUpGoingAwayAnimationsAllowed;
    }

    public final Flow getHeadsUpViewState() {
        return this.headsUpViewState;
    }

    public final void initHeadsUp() {
        this.headsUpManager.addListener(this);
        HeadsUpManager headsUpManager = this.headsUpManager;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        headsUpManager.setAnimationStateHandler(anonymousClass1);
    }

    public void onHeadsUpPinned(NotificationEntry notificationEntry) {
        ((OplusNotificationShadeWindowControllerEx) this.notificationShadeWindowControllerEx.get()).adjustShadeUxFlag(this.context, true);
        JankUtils.latencySceneBegin(this.context, 411, "BANNER_NF_APPEAR_L1", 1500L);
        JankUtils.latencySceneBegin(this.context, 412, "BANNER_NF_APPEAR_L2", 2500L);
        this._headsUpViewState.setValue(TuplesKt.to(notificationEntry, Boolean.TRUE));
    }

    public void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
        this._headsUpViewState.setValue(TuplesKt.to(notificationEntry, Boolean.FALSE));
    }

    public final void releaseHeadsUpViewState(NotificationEntry notificationEntry) {
        Pair pair = (Pair) this._headsUpViewState.getValue();
        if (((Boolean) pair.getSecond()).booleanValue() || pair.getFirst() != notificationEntry) {
            return;
        }
        this._headsUpViewState.setValue(TuplesKt.to((Object) null, Boolean.FALSE));
    }
}
