package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder;

import android.util.Log;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.kotlin.WithPrev;
import com.oplus.systemui.statusbar.phone.OplusIconController;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIconAnimationController;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.SysIconModel;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.TipModel;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconSetViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconGroupViewBinder {
    public static final DynamicIconGroupViewBinder INSTANCE = new DynamicIconGroupViewBinder();
    private static final String TAG = "DynamicIconGroupViewBinder";

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ DynamicIconAnimationController $animatorController;
        final /* synthetic */ ViewGroup $dynamicIconGroup;
        final /* synthetic */ OplusIconController $oplusIconController;
        final /* synthetic */ DynamicIconSetViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ DynamicIconAnimationController $animatorController;
            final /* synthetic */ OplusIconController $oplusIconController;
            final /* synthetic */ DynamicIconSetViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01372 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusIconController $oplusIconController;
                final /* synthetic */ DynamicIconSetViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C01381 extends SuspendLambda implements Function3 {
                    final /* synthetic */ OplusIconController $oplusIconController;
                    /* synthetic */ Object L$0;
                    /* synthetic */ Object L$1;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C01381(OplusIconController oplusIconController, Continuation continuation) {
                        super(3, continuation);
                        this.$oplusIconController = oplusIconController;
                    }

                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        C01381 c01381 = new C01381(this.$oplusIconController, (Continuation) obj3);
                        c01381.L$0 = (Map) obj;
                        c01381.L$1 = (Map) obj2;
                        return c01381.invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        Map map = (Map) this.L$0;
                        Map map2 = (Map) this.L$1;
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        DynamicIconGroupViewBinder dynamicIconGroupViewBinder = DynamicIconGroupViewBinder.INSTANCE;
                        OplusIconController oplusIconController = this.$oplusIconController;
                        dynamicIconGroupViewBinder.getClass();
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (Map.Entry entry : map2.entrySet()) {
                            if (!map.keySet().contains((String) entry.getKey())) {
                                linkedHashMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                        Collection<SysIconModel> collectionValues = linkedHashMap.values();
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                        for (Map.Entry entry2 : map.entrySet()) {
                            if (!map2.keySet().contains((String) entry2.getKey())) {
                                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                            }
                        }
                        Collection collectionValues2 = linkedHashMap2.values();
                        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                        for (Map.Entry entry3 : map2.entrySet()) {
                            String str = (String) entry3.getKey();
                            SysIconModel sysIconModel = (SysIconModel) entry3.getValue();
                            SysIconModel sysIconModel2 = (SysIconModel) map.get(str);
                            if (sysIconModel2 != null && !sysIconModel2.equals(sysIconModel)) {
                                linkedHashMap3.put(entry3.getKey(), entry3.getValue());
                            }
                        }
                        Collection<SysIconModel> collectionValues3 = linkedHashMap3.values();
                        Iterator it = collectionValues2.iterator();
                        while (it.hasNext()) {
                            oplusIconController.setOplusIconVisible(((SysIconModel) it.next()).getSlot(), false);
                        }
                        for (SysIconModel sysIconModel3 : collectionValues) {
                            oplusIconController.setOplusIcon(sysIconModel3.getSlot(), DynamicIconGroupViewBinderKt.toDynamicIcon(sysIconModel3));
                        }
                        for (SysIconModel sysIconModel4 : collectionValues3) {
                            oplusIconController.setOplusIcon(sysIconModel4.getSlot(), DynamicIconGroupViewBinderKt.toDynamicIcon(sysIconModel4));
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01372(DynamicIconSetViewModel dynamicIconSetViewModel, OplusIconController oplusIconController, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = dynamicIconSetViewModel;
                    this.$oplusIconController = oplusIconController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01372(this.$viewModel, this.$oplusIconController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowPairwiseBy = FlowKt.pairwiseBy(this.$viewModel.getIconMapFlow(), MapsKt__MapsKt.emptyMap(), new C01381(this.$oplusIconController, null));
                        this.label = 1;
                        if (kotlinx.coroutines.flow.FlowKt.collect(flowPairwiseBy, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ DynamicIconAnimationController $animatorController;
                final /* synthetic */ DynamicIconSetViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2$3$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C01391 extends SuspendLambda implements Function3 {
                    /* synthetic */ Object L$0;
                    /* synthetic */ boolean Z$0;
                    int label;

                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                        C01391 c01391 = new C01391(3, (Continuation) obj3);
                        c01391.L$0 = (TipModel) obj;
                        c01391.Z$0 = zBooleanValue;
                        return c01391.invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        TipModel tipModel = (TipModel) this.L$0;
                        boolean z = this.Z$0;
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            return TuplesKt.to(tipModel, Boxing.boxBoolean(z));
                        }
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                }

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder$bind$1$2$3$2, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01402 implements FlowCollector {
                    public /* synthetic */ DynamicIconAnimationController $animatorController;

                    public final Object emit(Object obj, Continuation continuation) {
                        WithPrev withPrev = (WithPrev) obj;
                        Pair pair = (Pair) withPrev.previousValue;
                        Pair pair2 = (Pair) withPrev.newValue;
                        TipModel tipModel = (TipModel) pair.component1();
                        boolean zBooleanValue = ((Boolean) pair.component2()).booleanValue();
                        TipModel tipModel2 = (TipModel) pair2.component1();
                        boolean zBooleanValue2 = ((Boolean) pair2.component2()).booleanValue();
                        if (zBooleanValue2) {
                            tipModel2 = null;
                        }
                        if (zBooleanValue) {
                            tipModel = null;
                        }
                        if (!Intrinsics.areEqual(tipModel2, tipModel)) {
                            boolean z = !zBooleanValue2;
                            DynamicIconGroupViewBinder dynamicIconGroupViewBinder = DynamicIconGroupViewBinder.INSTANCE;
                            DynamicIconAnimationController dynamicIconAnimationController = this.$animatorController;
                            dynamicIconGroupViewBinder.getClass();
                            if (tipModel == null && tipModel2 != null) {
                                dynamicIconAnimationController.show(tipModel2, z);
                            } else if (tipModel != null && tipModel2 == null) {
                                dynamicIconAnimationController.hide(z);
                            } else if (tipModel != null && tipModel2 != null) {
                                if (tipModel.getType() != tipModel2.getType()) {
                                    dynamicIconAnimationController.hide(false);
                                    dynamicIconAnimationController.show(tipModel2, true);
                                } else {
                                    dynamicIconAnimationController.show(tipModel2, false);
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(DynamicIconSetViewModel dynamicIconSetViewModel, DynamicIconAnimationController dynamicIconAnimationController, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = dynamicIconSetViewModel;
                    this.$animatorController = dynamicIconAnimationController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.$animatorController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            Flow flowPairwise = FlowKt.pairwise(kotlinx.coroutines.flow.FlowKt.combine(this.$viewModel.getTipFlow(), this.$viewModel.isTipHidden(), new C01391(3, null)), TuplesKt.to((Object) null, Boxing.boxBoolean(false)));
                            DynamicIconAnimationController dynamicIconAnimationController = this.$animatorController;
                            C01402 c01402 = new C01402();
                            c01402.$animatorController = dynamicIconAnimationController;
                            VarHandle.storeStoreFence();
                            this.label = 1;
                            if (flowPairwise.collect(c01402, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                                return null;
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", "DynamicIconGroupViewBinder-->tipFlow collect cancelled/stopped");
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", "DynamicIconGroupViewBinder-->tipFlow collect cancelled/stopped");
                        }
                        throw th;
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DynamicIconSetViewModel dynamicIconSetViewModel, OplusIconController oplusIconController, DynamicIconAnimationController dynamicIconAnimationController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = dynamicIconSetViewModel;
                this.$oplusIconController = oplusIconController;
                this.$animatorController = dynamicIconAnimationController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$oplusIconController, this.$animatorController, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "DynamicIconGroupViewBinder-->repeatOnLifecycle enter CREATED");
                }
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01372(this.$viewModel, this.$oplusIconController, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$viewModel, this.$animatorController, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ViewGroup viewGroup, DynamicIconSetViewModel dynamicIconSetViewModel, OplusIconController oplusIconController, DynamicIconAnimationController dynamicIconAnimationController, Continuation continuation) {
            super(3, continuation);
            this.$dynamicIconGroup = viewGroup;
            this.$viewModel = dynamicIconSetViewModel;
            this.$oplusIconController = oplusIconController;
            this.$animatorController = dynamicIconAnimationController;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$dynamicIconGroup, this.$viewModel, this.$oplusIconController, this.$animatorController, (Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ViewGroup viewGroup = this.$dynamicIconGroup;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "DynamicIconGroupViewBinder-->".concat("repeatWhenAttached start: view=" + viewGroup));
                }
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$oplusIconController, this.$animatorController, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            ViewGroup viewGroup2 = this.$dynamicIconGroup;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "DynamicIconGroupViewBinder-->".concat("repeatWhenAttached end: view=" + viewGroup2));
            }
            return Unit.INSTANCE;
        }
    }

    public final void bind(ViewGroup viewGroup, DynamicIconSetViewModel dynamicIconSetViewModel, OplusIconController oplusIconController, DynamicIconAnimationController dynamicIconAnimationController) {
        RepeatWhenAttachedKt.repeatWhenAttached$default(viewGroup, (CoroutineContext) null, new AnonymousClass1(viewGroup, dynamicIconSetViewModel, oplusIconController, dynamicIconAnimationController, null), 3);
    }
}
