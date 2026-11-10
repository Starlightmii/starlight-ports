package com.oplus.systemui.qs.user;

import android.app.Dialog;
import com.android.systemui.CoreStartable;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import javax.inject.Provider;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUserSwitcherDialogCoordinator implements CoreStartable {
    private final Lazy applicationScope;
    private final Lazy context;
    private Dialog currentDialog;
    private final kotlin.Lazy detailInteractor$delegate;
    private final OplusQSDetailInteractor.Factory detailInteractorFactory;
    private final Lazy interactor;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final Provider userDetailAdapterProvider;
    private final Lazy userSwitcherControllerHelper;

    public OplusUserSwitcherDialogCoordinator(Lazy lazy, Lazy lazy2, Lazy lazy3, Provider provider, Lazy lazy4, OplusQSDetailInteractor.Factory factory, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        this.context = lazy;
        this.applicationScope = lazy2;
        this.interactor = lazy3;
        this.userDetailAdapterProvider = provider;
        this.userSwitcherControllerHelper = lazy4;
        this.detailInteractorFactory = factory;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        OplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0 oplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0 = new OplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0();
        oplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.detailInteractor$delegate = LazyKt__LazyJVMKt.lazy(oplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusQSDetailInteractor getDetailInteractor() {
        return (OplusQSDetailInteractor) this.detailInteractor$delegate.getValue();
    }

    public final void start() {
        BuildersKt.launch$default((CoroutineScope) this.applicationScope.get(), (CoroutineContext) null, (CoroutineStart) null, new OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1(this, null), 3);
        BuildersKt.launch$default((CoroutineScope) this.applicationScope.get(), (CoroutineContext) null, (CoroutineStart) null, new OplusUserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(this, null), 3);
    }
}
