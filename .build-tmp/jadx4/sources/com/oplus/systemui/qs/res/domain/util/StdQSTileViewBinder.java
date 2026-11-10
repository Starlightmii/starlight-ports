package com.oplus.systemui.qs.res.domain.util;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.QuickQSPanelController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.OplusQSFooterViewController;
import com.oplus.systemui.qs.OplusQSTileMediaContainerController;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StdQSTileViewBinder {
    public static final StdQSTileViewBinder INSTANCE = new StdQSTileViewBinder();

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectFooterButton$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ OplusQSFooterViewController $footer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectFooterButton$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00881 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSFooterViewController $footer;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectFooterButton$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00891 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSFooterViewController $footer;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00891(OplusQSFooterViewController oplusQSFooterViewController, Continuation continuation) {
                    super(2, continuation);
                    this.$footer = oplusQSFooterViewController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00891(this.$footer, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((QSDrawableBuilder) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    ((OplusQSFooterImpl) ((ViewController) this.$footer).mView).refreshFooterButtonBackground();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00881(OplusQSFooterViewController oplusQSFooterViewController, Continuation continuation) {
                super(2, continuation);
                this.$footer = oplusQSFooterViewController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00881(this.$footer, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.quickEntranceDrawable;
                    C00891 c00891 = new C00891(this.$footer, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00891, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectFooterButton$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSFooterViewController $footer;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectFooterButton$1$2$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00901 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSFooterViewController $footer;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00901(OplusQSFooterViewController oplusQSFooterViewController, Continuation continuation) {
                    super(2, continuation);
                    this.$footer = oplusQSFooterViewController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00901(this.$footer, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((TileIconColorState) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    ((OplusQSFooterImpl) ((ViewController) this.$footer).mView).refreshFooterButtonIconColor();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OplusQSFooterViewController oplusQSFooterViewController, Continuation continuation) {
                super(2, continuation);
                this.$footer = oplusQSFooterViewController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$footer, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.tileIconColorState;
                    C00901 c00901 = new C00901(this.$footer, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00901, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OplusQSFooterViewController oplusQSFooterViewController, Continuation continuation) {
            super(2, continuation);
            this.$footer = oplusQSFooterViewController;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$footer, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00881(this.$footer, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$footer, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02711 extends SuspendLambda implements Function2 {
        final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00911 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00921 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00921(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.$mediaPanelController = oplusQSTileMediaContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00921(this.$mediaPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CornerOutlineProvider) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$mediaPanelController.updateMediaPanelOutline();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00911(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                super(2, continuation);
                this.$mediaPanelController = oplusQSTileMediaContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00911(this.$mediaPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow mediaPanelOutline = StdQSResPool.INSTANCE.getMediaPanelOutline();
                    C00921 c00921 = new C00921(this.$mediaPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(mediaPanelOutline, c00921, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$2$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00931 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00931(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.$mediaPanelController = oplusQSTileMediaContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00931(this.$mediaPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((QSDrawableBuilder) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$mediaPanelController.updateMediaPanelDrawable();
                    this.$mediaPanelController.updateSliderCornerRadius();
                    this.$mediaPanelController.updateColor();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                super(2, continuation);
                this.$mediaPanelController = oplusQSTileMediaContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$mediaPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.mediaPanelDrawable;
                    C00931 c00931 = new C00931(this.$mediaPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00931, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$3$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00941 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00941(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.$mediaPanelController = oplusQSTileMediaContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00941(this.$mediaPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((ShapeConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$mediaPanelController.updateSliderCornerRadius();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                super(2, continuation);
                this.$mediaPanelController = oplusQSTileMediaContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$mediaPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.tileShapeConfig;
                    C00941 c00941 = new C00941(this.$mediaPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00941, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$4, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$4$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00951 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00951(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.$mediaPanelController = oplusQSTileMediaContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00951(this.$mediaPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((C00951) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$mediaPanelController.updateSliderCornerRadius();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                super(2, continuation);
                this.$mediaPanelController = oplusQSTileMediaContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$mediaPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow highlightDefaultShape = StdQSResPool.INSTANCE.getHighlightDefaultShape();
                    C00951 c00951 = new C00951(this.$mediaPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(highlightDefaultShape, c00951, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$5, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectMediaPanelRes$1$5$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00961 extends SuspendLambda implements Function2 {
                final /* synthetic */ OplusQSTileMediaContainerController $mediaPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00961(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.$mediaPanelController = oplusQSTileMediaContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00961(this.$mediaPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((C00961) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$mediaPanelController.updateSliderCornerRadius();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
                super(2, continuation);
                this.$mediaPanelController = oplusQSTileMediaContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$mediaPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow supportStrokeCorner = StdQSResPool.INSTANCE.getSupportStrokeCorner();
                    C00961 c00961 = new C00961(this.$mediaPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(supportStrokeCorner, c00961, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02711(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, Continuation continuation) {
            super(2, continuation);
            this.$mediaPanelController = oplusQSTileMediaContainerController;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C02711 c02711 = new C02711(this.$mediaPanelController, continuation);
            c02711.L$0 = obj;
            return c02711;
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00911(this.$mediaPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$mediaPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$mediaPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$mediaPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.$mediaPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 6(this.$mediaPanelController, (Continuation) null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02721 extends SuspendLambda implements Function2 {
        final /* synthetic */ QuickQSPanelController $qqsPanelController;
        final /* synthetic */ QSPanelController $qsPanelController;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00971 extends SuspendLambda implements Function2 {
            final /* synthetic */ QuickQSPanelController $qqsPanelController;
            final /* synthetic */ QSPanelController $qsPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00981 extends SuspendLambda implements Function2 {
                final /* synthetic */ QuickQSPanelController $qqsPanelController;
                final /* synthetic */ QSPanelController $qsPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00981(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                    super(2, continuation);
                    this.$qsPanelController = qSPanelController;
                    this.$qqsPanelController = quickQSPanelController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00981(this.$qsPanelController, this.$qqsPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((QSDrawableBuilder) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    QSPanelController qSPanelController = this.$qsPanelController;
                    List<SpanSize> list = QSConstant.tileType;
                    qSPanelController.updateDrawable(list);
                    this.$qqsPanelController.updateDrawable(list);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00971(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                super(2, continuation);
                this.$qsPanelController = qSPanelController;
                this.$qqsPanelController = quickQSPanelController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00971(this.$qsPanelController, this.$qqsPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.tileViewDrawable;
                    C00981 c00981 = new C00981(this.$qsPanelController, this.$qqsPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00981, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ QuickQSPanelController $qqsPanelController;
            final /* synthetic */ QSPanelController $qsPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$2$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00991 extends SuspendLambda implements Function2 {
                final /* synthetic */ QuickQSPanelController $qqsPanelController;
                final /* synthetic */ QSPanelController $qsPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00991(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                    super(2, continuation);
                    this.$qsPanelController = qSPanelController;
                    this.$qqsPanelController = quickQSPanelController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00991(this.$qsPanelController, this.$qqsPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CornerOutlineProvider) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    QSPanelController qSPanelController = this.$qsPanelController;
                    List<SpanSize> list = QSConstant.tileType;
                    qSPanelController.updateOutline(list);
                    this.$qqsPanelController.updateOutline(list);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                super(2, continuation);
                this.$qsPanelController = qSPanelController;
                this.$qqsPanelController = quickQSPanelController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$qsPanelController, this.$qqsPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.tileOutline;
                    C00991 c00991 = new C00991(this.$qsPanelController, this.$qqsPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c00991, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ QuickQSPanelController $qqsPanelController;
            final /* synthetic */ QSPanelController $qsPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$3$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01001 extends SuspendLambda implements Function2 {
                final /* synthetic */ QuickQSPanelController $qqsPanelController;
                final /* synthetic */ QSPanelController $qsPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01001(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                    super(2, continuation);
                    this.$qsPanelController = qSPanelController;
                    this.$qqsPanelController = quickQSPanelController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01001(this.$qsPanelController, this.$qqsPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((TileIconColorState) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$qsPanelController.updateIconTint();
                    this.$qqsPanelController.updateIconTint();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                super(2, continuation);
                this.$qsPanelController = qSPanelController;
                this.$qqsPanelController = quickQSPanelController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$qsPanelController, this.$qqsPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.tileIconColorState;
                    C01001 c01001 = new C01001(this.$qsPanelController, this.$qqsPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c01001, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$4, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ QuickQSPanelController $qqsPanelController;
            final /* synthetic */ QSPanelController $qsPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$4$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01011 extends SuspendLambda implements Function2 {
                final /* synthetic */ QuickQSPanelController $qqsPanelController;
                final /* synthetic */ QSPanelController $qsPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01011(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                    super(2, continuation);
                    this.$qsPanelController = qSPanelController;
                    this.$qqsPanelController = quickQSPanelController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01011(this.$qsPanelController, this.$qqsPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((QSDrawableBuilder) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    QSPanelController qSPanelController = this.$qsPanelController;
                    List<SpanSize> list = QSConstant.highlightTileType;
                    qSPanelController.updateDrawable(list);
                    this.$qqsPanelController.updateDrawable(list);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                super(2, continuation);
                this.$qsPanelController = qSPanelController;
                this.$qqsPanelController = quickQSPanelController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$qsPanelController, this.$qqsPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.highlightTileViewDrawable;
                    C01011 c01011 = new C01011(this.$qsPanelController, this.$qqsPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c01011, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$5, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ QuickQSPanelController $qqsPanelController;
            final /* synthetic */ QSPanelController $qsPanelController;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder$collectTileViewRes$1$5$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01021 extends SuspendLambda implements Function2 {
                final /* synthetic */ QuickQSPanelController $qqsPanelController;
                final /* synthetic */ QSPanelController $qsPanelController;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01021(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                    super(2, continuation);
                    this.$qsPanelController = qSPanelController;
                    this.$qqsPanelController = quickQSPanelController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01021(this.$qsPanelController, this.$qqsPanelController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CornerOutlineProvider) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    QSPanelController qSPanelController = this.$qsPanelController;
                    List<SpanSize> list = QSConstant.highlightTileType;
                    qSPanelController.updateOutline(list);
                    this.$qqsPanelController.updateOutline(list);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
                super(2, continuation);
                this.$qsPanelController = qSPanelController;
                this.$qqsPanelController = quickQSPanelController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$qsPanelController, this.$qqsPanelController, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = StdQSResPool.highLightTileOutline;
                    C01021 c01021 = new C01021(this.$qsPanelController, this.$qqsPanelController, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(stateFlow, c01021, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02721(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, Continuation continuation) {
            super(2, continuation);
            this.$qsPanelController = qSPanelController;
            this.$qqsPanelController = quickQSPanelController;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C02721 c02721 = new C02721(this.$qsPanelController, this.$qqsPanelController, continuation);
            c02721.L$0 = obj;
            return c02721;
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00971(this.$qsPanelController, this.$qqsPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$qsPanelController, this.$qqsPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$qsPanelController, this.$qqsPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$qsPanelController, this.$qqsPanelController, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.$qsPanelController, this.$qqsPanelController, null), 3);
            return Unit.INSTANCE;
        }
    }

    public static final void collectFooterButton(OplusQSFooterViewController oplusQSFooterViewController, LifecycleOwner lifecycleOwner) {
        if (oplusQSFooterViewController == null) {
            return;
        }
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(oplusQSFooterViewController, null), 3);
    }

    public static final void collectMediaPanelRes(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, LifecycleOwner lifecycleOwner) {
        if (oplusQSTileMediaContainerController == null) {
            return;
        }
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new C02711(oplusQSTileMediaContainerController, null), 3);
    }

    public static final void collectTileViewRes(QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, LifecycleOwner lifecycleOwner) {
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new C02721(qSPanelController, quickQSPanelController, null), 3);
    }
}
