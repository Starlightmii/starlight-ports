package com.oplus.systemui.statusbar.pipeline.ui.viewmodel;

import androidx.compose.runtime.SnapshotStateKt;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.lifecycle.HydratedActivatable;
import com.android.systemui.lifecycle.Hydrator;
import com.android.systemui.log.table.DiffableExtensionsKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.OplusNetworkTypeTextModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.StackedMobileIconViewModel;
import com.android.systemui.statusbar.pipeline.ui.viewmodel.OplusStackedMobileIconViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconsViewModel;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackedMobileIconViewModelImpl extends HydratedActivatable implements OplusStackedMobileIconViewModel {
    private static final String TAG = "OplusStackedMobileIconViewModelImpl";
    private final Flow _activityContainerVisible;
    private final Flow _isIconVisible;
    private final ReadOnlyProperty activityContainerVisible$delegate;
    private final ReadOnlyProperty activityIndicatorIconResId$delegate;
    private final ReadOnlyProperty isIconVisible$delegate;
    private final ReadOnlyProperty networkTypeTextInfo$delegate;
    private final Flow oplusIconViewModelFlow;
    private final ReadOnlyProperty roamingText$delegate;
    private final StackedMobileIconViewModel stackedMobileIconViewModel;
    private final TableLogBuffer tableLogger;

    @Deprecated
    public static final String COL_IS_ICON_VISIBLE = "isIconVisible";

    @Deprecated
    public static final String COL_ACTIVITY_CONTAINER_VISIBLE = "activityContainerVisible";
    public static final /* synthetic */ KProperty[] $$delegatedProperties = {new PropertyReference1Impl(OplusStackedMobileIconViewModelImpl.class, COL_IS_ICON_VISIBLE, "isIconVisible()Z", 0), ExpandHelper$.ExternalSyntheticOutline0.m(Reflection.factory, OplusStackedMobileIconViewModelImpl.class, COL_ACTIVITY_CONTAINER_VISIBLE, "getActivityContainerVisible()Z", 0), new PropertyReference1Impl(OplusStackedMobileIconViewModelImpl.class, "networkTypeTextInfo", "getNetworkTypeTextInfo()Lcom/android/systemui/statusbar/pipeline/mobile/domain/model/OplusNetworkTypeTextModel;", 0), new PropertyReference1Impl(OplusStackedMobileIconViewModelImpl.class, "activityIndicatorIconResId", "getActivityIndicatorIconResId()I", 0), new PropertyReference1Impl(OplusStackedMobileIconViewModelImpl.class, "roamingText", "getRoamingText()Lcom/android/systemui/common/shared/model/Text;", 0)};
    private static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory extends OplusStackedMobileIconViewModel.Factory {
        /* synthetic */ OplusStackedMobileIconViewModel create(StackedMobileIconViewModel stackedMobileIconViewModel);

        /* JADX INFO: renamed from: create, reason: collision with other method in class */
        OplusStackedMobileIconViewModelImpl m470create(StackedMobileIconViewModel stackedMobileIconViewModel);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusStackedMobileIconViewModelImpl(OplusMobileIconsViewModel oplusMobileIconsViewModel, OplusMobileIconsInteractor oplusMobileIconsInteractor, MobileIconsViewModel mobileIconsViewModel, TableLogBuffer tableLogBuffer, StackedMobileIconViewModel stackedMobileIconViewModel) {
        super(3, (String) null, false);
        int i = 0;
        this.tableLogger = tableLogBuffer;
        this.stackedMobileIconViewModel = stackedMobileIconViewModel;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconsViewModel.getMobileSubViewModels(), mobileIconsViewModel.getActiveMobileDataSubscriptionId(), new oplusIconViewModelFlow.1(3, (Continuation) null)));
        this.oplusIconViewModelFlow = flowDistinctUntilChanged;
        Flow flowDistinctUntilChanged2 = FlowKt.distinctUntilChanged(FlowKt.transformLatest(flowDistinctUntilChanged, new OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1(this, null)));
        this._isIconVisible = flowDistinctUntilChanged2;
        Flow flowDistinctUntilChanged3 = FlowKt.distinctUntilChanged(FlowKt.transformLatest(flowDistinctUntilChanged, new OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2(3, null)));
        this._activityContainerVisible = flowDistinctUntilChanged3;
        Flow flowLogDiffsForTable = DiffableExtensionsKt.logDiffsForTable(flowDistinctUntilChanged2, tableLogBuffer, "", COL_IS_ICON_VISIBLE, false);
        Boolean bool = Boolean.FALSE;
        Hydrator.StateDelegateProvider stateDelegateProviderHydratedStateOf = hydratedStateOf(flowLogDiffsForTable, bool);
        KProperty[] kPropertyArr = $$delegatedProperties;
        this.isIconVisible$delegate = stateDelegateProviderHydratedStateOf.provideDelegate(this, kPropertyArr[0]);
        Flow flowTransformLatest = FlowKt.transformLatest(flowDistinctUntilChanged2, new OplusStackedMobileIconViewModelImpl$flowIfIconIsVisible$$inlined$flatMapLatest$1(DiffableExtensionsKt.logDiffsForTable(flowDistinctUntilChanged3, tableLogBuffer, "", COL_ACTIVITY_CONTAINER_VISIBLE, false), null));
        OplusStackedMobileIconViewModelImpl$special$$inlined$map$1 oplusStackedMobileIconViewModelImpl$special$$inlined$map$1 = new OplusStackedMobileIconViewModelImpl$special$$inlined$map$1(i);
        oplusStackedMobileIconViewModelImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = flowTransformLatest;
        VarHandle.storeStoreFence();
        Hydrator.StateDelegateProvider stateDelegateProviderHydratedStateOf2 = hydratedStateOf(oplusStackedMobileIconViewModelImpl$special$$inlined$map$1, bool);
        int i2 = 1;
        this.activityContainerVisible$delegate = stateDelegateProviderHydratedStateOf2.provideDelegate(this, kPropertyArr[1]);
        this.networkTypeTextInfo$delegate = hydratedStateOf(FlowKt.transformLatest(flowDistinctUntilChanged2, new OplusStackedMobileIconViewModelImpl$flowIfIconIsVisible$$inlined$flatMapLatest$1(FlowKt.transformLatest(flowDistinctUntilChanged, new OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$3(3, null)), null)), (Object) null).provideDelegate(this, kPropertyArr[2]);
        OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0 oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0 = new OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0(i);
        oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0);
        OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0 oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda1 = new OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0(i2);
        oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        Flow flowTransformLatest2 = FlowKt.transformLatest(flowDistinctUntilChanged2, new OplusStackedMobileIconViewModelImpl$flowIfIconIsVisible$$inlined$flatMapLatest$1(FlowKt.combine(flowDistinctUntilChanged3, flowSnapshotFlow, SnapshotStateKt.snapshotFlow(oplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda1), oplusMobileIconsInteractor.getMobileIconSets(), new activityIndicatorIconResId.4(this, (Continuation) null)), null));
        OplusStackedMobileIconViewModelImpl$special$$inlined$map$1 oplusStackedMobileIconViewModelImpl$special$$inlined$map$2 = new OplusStackedMobileIconViewModelImpl$special$$inlined$map$1(i2);
        oplusStackedMobileIconViewModelImpl$special$$inlined$map$2.$this_unsafeTransform$inlined = flowTransformLatest2;
        VarHandle.storeStoreFence();
        this.activityIndicatorIconResId$delegate = hydratedStateOf(oplusStackedMobileIconViewModelImpl$special$$inlined$map$2, 0).provideDelegate(this, kPropertyArr[3]);
        this.roamingText$delegate = hydratedStateOf(FlowKt.flowOf(new Text.Resource(2131955281)), new Text.Resource(2131955281)).provideDelegate(this, kPropertyArr[4]);
    }

    public boolean getActivityContainerVisible() {
        return ((Boolean) this.activityContainerVisible$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public int getActivityIndicatorIconResId() {
        return ((Number) this.activityIndicatorIconResId$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    public OplusNetworkTypeTextModel getNetworkTypeTextInfo() {
        return (OplusNetworkTypeTextModel) this.networkTypeTextInfo$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public Text getRoamingText() {
        return (Text) this.roamingText$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final boolean isIconVisible() {
        return ((Boolean) this.isIconVisible$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }
}
