package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import android.content.Context;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractor;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
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
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconsViewModel {
    private final ConnectivityConstants constants;
    private final Context context;
    private final MobileIconsInteractor interactor;
    private final Map<Integer, OplusMobileIconViewModel> mobileIconSubIdCache = new LinkedHashMap();
    private final MobileIconsViewModel mobileIconsViewModel;
    private final StateFlow mobileSubViewModels;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final CoroutineScope scope;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconsViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconsViewModel$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01591 implements FlowCollector {
            public /* synthetic */ OplusMobileIconsViewModel this$0;

            public final Object emit(Object obj, Continuation continuation) {
                OplusMobileIconsViewModel.access$invalidateCaches(this.this$0, (List) obj);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusMobileIconsViewModel.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow subscriptionIdsFlow = OplusMobileIconsViewModel.this.mobileIconsViewModel.getSubscriptionIdsFlow();
                OplusMobileIconsViewModel oplusMobileIconsViewModel = OplusMobileIconsViewModel.this;
                C01591 c01591 = new C01591();
                c01591.this$0 = oplusMobileIconsViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (subscriptionIdsFlow.collect(c01591, this) == coroutine_suspended) {
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

    public OplusMobileIconsViewModel(Context context, MobileIconsInteractor mobileIconsInteractor, MobileIconsViewModel mobileIconsViewModel, CoroutineScope coroutineScope, ConnectivityConstants connectivityConstants, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        this.context = context;
        this.interactor = mobileIconsInteractor;
        this.mobileIconsViewModel = mobileIconsViewModel;
        this.scope = coroutineScope;
        this.constants = connectivityConstants;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        StateFlow subscriptionIdsFlow = mobileIconsViewModel.getSubscriptionIdsFlow();
        OplusMobileIconsViewModel$special$.inlined.map.1 r4 = new OplusMobileIconsViewModel$special$.inlined.map.1();
        r4.$this_unsafeTransform$inlined = subscriptionIdsFlow;
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.mobileSubViewModels = FlowKt.stateIn(r4, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), CollectionsKt__CollectionsKt.emptyList());
    }

    public static final void access$invalidateCaches(OplusMobileIconsViewModel oplusMobileIconsViewModel, List list) {
        Set<Integer> setKeySet = oplusMobileIconsViewModel.mobileIconSubIdCache.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (!list.contains(Integer.valueOf(((Number) obj).intValue()))) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            oplusMobileIconsViewModel.mobileIconSubIdCache.remove(Integer.valueOf(((Number) obj2).intValue()));
        }
    }

    public final StateFlow getMobileSubViewModels() {
        return this.mobileSubViewModels;
    }

    public final OplusMobileIconViewModel viewModelForSub(int i) {
        OplusMobileIconViewModel oplusMobileIconViewModel = this.mobileIconSubIdCache.get(Integer.valueOf(i));
        if (oplusMobileIconViewModel != null) {
            return oplusMobileIconViewModel;
        }
        OplusMobileIconViewModel oplusMobileIconViewModel2 = new OplusMobileIconViewModel(this.context, i, (OplusMobileIconInteractorImpl) this.interactor.getMobileConnectionInteractorForSubId(i), this.constants, this.scope, this.oplusMobileIconsInteractor);
        this.mobileIconSubIdCache.put(Integer.valueOf(i), oplusMobileIconViewModel2);
        return oplusMobileIconViewModel2;
    }
}
