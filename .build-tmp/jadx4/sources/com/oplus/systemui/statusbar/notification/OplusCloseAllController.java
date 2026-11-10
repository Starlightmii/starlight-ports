package com.oplus.systemui.statusbar.notification;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.view.OplusCloseAllButton;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.MaskBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCloseAllController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCloseAllController";
    private final ConfigurationController configurationController;
    private final OplusCloseAllController$configurationListener$1 configurationListener;
    private final Context context;
    private DisposableHandle disposableHandle;
    private final Lazy lockScreenExtStateController;
    private final kotlin.Lazy materialColorChangeCallback$delegate;
    private final MaterialColorManager materialColorManager;
    private final Lazy notificationEffectRepository;
    private final Lazy notificationKeyguardHelper;
    private final Lazy notificationMaterialColorEx;
    private OplusCloseAllButton oplusCloseAllButton;
    private final Lazy oplusLockscreenShadeTransitionControllerEx;
    private final SysuiStatusBarStateController statusBarStateController;
    private final OplusCloseAllController$statusBarStateListener$1 statusBarStateListener;
    private final SuperPowerSaveRepository superPowerSaveRepository;
    private final ViewBlurManager viewBlurManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ OplusCloseAllController this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ILockScreenExtStateController.gotoKeyguardState$default((ILockScreenExtStateController) this.this$0.lockScreenExtStateController.get(), "click-close-button", 0.0f, 12);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusCloseAllController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01191 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01201 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ OplusCloseAllController this$0;

                    public /* synthetic */ C01201(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                float fFloatValue = ((Number) obj).floatValue();
                                OplusCloseAllController oplusCloseAllController = this.this$0;
                                AppCompatButton appCompatButton = oplusCloseAllController.oplusCloseAllButton;
                                if (appCompatButton != null) {
                                    if (appCompatButton.getVisibility() != 0 && fFloatValue != 0.0f) {
                                        appCompatButton.setVisibility(0);
                                    }
                                    appCompatButton.setAlpha(fFloatValue);
                                    if (fFloatValue == 0.0f || oplusCloseAllController.statusBarStateController.getState() == 0) {
                                        appCompatButton.setVisibility(4);
                                    }
                                }
                                break;
                            case 1:
                                ((Boolean) obj).getClass();
                                OplusCloseAllController.updatePlatformBlurDrawable$default(this.this$0);
                                break;
                            case 2:
                                ((Boolean) obj).getClass();
                                OplusCloseAllController.updatePlatformBlurDrawable$default(this.this$0);
                                break;
                            case 3:
                                ((Boolean) obj).getClass();
                                OplusCloseAllController.updatePlatformBlurDrawable$default(this.this$0);
                                break;
                            case 4:
                                ((Boolean) obj).booleanValue();
                                this.this$0.updateTextColor();
                                break;
                            default:
                                OplusCloseAllController.updatePlatformBlurDrawable$default(this.this$0);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01191(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01191(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow notificationCenterAnimFraction = ((OplusLockscreenShadeTransitionControllerExImpl) this.this$0.oplusLockscreenShadeTransitionControllerEx.get()).getNotificationCenterAnimFraction();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01201 c01201 = new C01201(0);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (notificationCenterAnimFraction.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01212 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01212(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01212(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    int i2 = 1;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsStrokeLineEnable = ((NotificationEffectRepository) this.this$0.notificationEffectRepository.get()).isStrokeLineEnable();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01191.C01201 c01201 = new C01191.C01201(i2);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsStrokeLineEnable.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow superPowerSaveMode = this.this$0.superPowerSaveRepository.getSuperPowerSaveMode();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01191.C01201 c01201 = new C01191.C01201(2);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (superPowerSaveMode.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsThirdThemeMode = ScrimControllerEx.Companion.isThirdThemeMode();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01191.C01201 c01201 = new C01191.C01201(3);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsThirdThemeMode.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsMaterialColor = ((NotificationMaterialColorEx) this.this$0.notificationMaterialColorEx.get()).isMaterialColor();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01191.C01201 c01201 = new C01191.C01201(4);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsMaterialColor.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.OplusCloseAllController$bindView$2$1$6, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusCloseAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusCloseAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow blurMode = ((NotificationMaterialColorEx) this.this$0.notificationMaterialColorEx.get()).getBlurMode();
                        OplusCloseAllController oplusCloseAllController = this.this$0;
                        C01191.C01201 c01201 = new C01191.C01201(5);
                        c01201.this$0 = oplusCloseAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (blurMode.collect(c01201, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusCloseAllController oplusCloseAllController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusCloseAllController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01191(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new C01212(this.this$0, null), 2);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = OplusCloseAllController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusCloseAllController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

    public OplusCloseAllController(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy, Lazy lazy2, ConfigurationController configurationController, Lazy lazy3, SuperPowerSaveRepository superPowerSaveRepository, ViewBlurManager viewBlurManager, MaterialColorManager materialColorManager, Lazy lazy4, Lazy lazy5) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.lockScreenExtStateController = lazy;
        this.oplusLockscreenShadeTransitionControllerEx = lazy2;
        this.configurationController = configurationController;
        this.notificationEffectRepository = lazy3;
        this.superPowerSaveRepository = superPowerSaveRepository;
        this.viewBlurManager = viewBlurManager;
        this.materialColorManager = materialColorManager;
        this.notificationMaterialColorEx = lazy4;
        this.notificationKeyguardHelper = lazy5;
        OplusCloseAllController$statusBarStateListener$1 oplusCloseAllController$statusBarStateListener$1 = new OplusCloseAllController$statusBarStateListener$1();
        oplusCloseAllController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusCloseAllController$statusBarStateListener$1;
        OplusCloseAllController$$ExternalSyntheticLambda0 oplusCloseAllController$$ExternalSyntheticLambda0 = new OplusCloseAllController$$ExternalSyntheticLambda0();
        oplusCloseAllController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.materialColorChangeCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusCloseAllController$$ExternalSyntheticLambda0);
        OplusCloseAllController$configurationListener$1 oplusCloseAllController$configurationListener$1 = new OplusCloseAllController$configurationListener$1();
        oplusCloseAllController$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = oplusCloseAllController$configurationListener$1;
    }

    public static final void access$updateFont(OplusCloseAllController oplusCloseAllController) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCloseAllController-->OplusCloseAllController oplusCloseAllButton updateFont");
        }
        OplusCloseAllButton oplusCloseAllButton = oplusCloseAllController.oplusCloseAllButton;
        if (oplusCloseAllButton != null) {
            oplusCloseAllButton.setTypeface(Typeface.DEFAULT);
        }
    }

    private final int getCloseAllButtonMaterialColor() {
        return this.materialColorManager.getCurrentButtonMaterialColor();
    }

    private final int getMaskColor() {
        if (ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
            return 0;
        }
        return this.context.getColor(2131103146);
    }

    private final OplusCloseAllController$materialColorChangeCallback$2$1 getMaterialColorChangeCallback() {
        return (OplusCloseAllController$materialColorChangeCallback$2$1) this.materialColorChangeCallback$delegate.getValue();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void updatePlatformBlurDrawable$default(OplusCloseAllController oplusCloseAllController) {
        Drawable background;
        ViewBlurProxy.BlurType blurType;
        AppCompatButton appCompatButton;
        Drawable drawable = oplusCloseAllController.context.getDrawable(2131232810);
        oplusCloseAllController.updateTextColor();
        AppCompatButton appCompatButton2 = oplusCloseAllController.oplusCloseAllButton;
        if (appCompatButton2 != null) {
            appCompatButton2.setClipToOutline(true);
        }
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                if (ScrimUtil.isBAnimLevel() || ScrimUtil.isBPAnimLevel()) {
                    int closeAllButtonMaterialColor = oplusCloseAllController.getCloseAllButtonMaterialColor();
                    if (closeAllButtonMaterialColor != 0) {
                        if (drawable != null) {
                            drawable.setTint(closeAllButtonMaterialColor);
                        }
                    } else if (drawable != null) {
                        drawable.setTintList(null);
                    }
                    AppCompatButton appCompatButton3 = oplusCloseAllController.oplusCloseAllButton;
                    if (appCompatButton3 != null) {
                        appCompatButton3.setBackground(drawable);
                        return;
                    }
                    return;
                }
                AppCompatButton appCompatButton4 = oplusCloseAllController.oplusCloseAllButton;
                if (appCompatButton4 == null) {
                    return;
                }
                ViewBlurProxy viewBlurProxyRequireBlurProxyForView = oplusCloseAllController.viewBlurManager.requireBlurProxyForView(appCompatButton4, ViewBlurManager.CardType.CLOSE_ALL);
                if (ScrimUtil.isBPAnimLevel()) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
                } else {
                    blurType = GaussBlurUtils.isGaussBlurDisabled() ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
                }
                viewBlurProxyRequireBlurProxyForView.setBlurType(blurType);
                if (Intrinsics.areEqual(viewBlurProxyRequireBlurProxyForView.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && (appCompatButton = oplusCloseAllController.oplusCloseAllButton) != null) {
                    appCompatButton.setClipToOutline(false);
                }
                BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
                blurConfig.setCornerRadius(oplusCloseAllController.context.getResources().getDimensionPixelSize(2131166030) >> 1);
                blurConfig.setRadiusWeight(null);
                blurConfig.setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createDecoratorCornerParams(blurConfig.getCornerRadius()));
                viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
                int maskColor = oplusCloseAllController.getMaskColor();
                Drawable background2 = appCompatButton4.getBackground();
                MaskBlurDrawable maskBlurDrawable = background2 instanceof MaskBlurDrawable ? (MaskBlurDrawable) background2 : null;
                if (maskBlurDrawable == null) {
                    appCompatButton4.setBackground(new MaskBlurDrawable(viewBlurProxyRequireBlurProxyForView, drawable, maskColor));
                    return;
                }
                maskBlurDrawable.setDefaultDrawable(drawable);
                maskBlurDrawable.setViewBlurProxy(viewBlurProxyRequireBlurProxyForView);
                maskBlurDrawable.setMaskColor(maskColor);
                return;
            }
        }
        if (drawable != null) {
            drawable.setTintList(null);
        }
        AppCompatButton appCompatButton5 = oplusCloseAllController.oplusCloseAllButton;
        if (appCompatButton5 != null) {
            appCompatButton5.setBackground(drawable);
        }
        AppCompatButton appCompatButton6 = oplusCloseAllController.oplusCloseAllButton;
        if (appCompatButton6 == null || (background = appCompatButton6.getBackground()) == null) {
            return;
        }
        background.invalidateSelf();
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [android.widget.Button, com.oplus.systemui.statusbar.notification.view.OplusCloseAllButton] */
    public final DisposableHandle bindView(View view) {
        ?? r4 = (OplusCloseAllButton) view.findViewById(2131362577);
        this.oplusCloseAllButton = r4;
        if (r4 != 0) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            r4.setOnClickListener(anonymousClass1);
        }
        this.statusBarStateController.addCallback(this.statusBarStateListener);
        this.configurationController.addCallback(this.configurationListener);
        if (ScrimUtil.isBAnimLevel() || ScrimUtil.isBPAnimLevel()) {
            this.materialColorManager.removeMaterialColorCallback(getMaterialColorChangeCallback());
            this.materialColorManager.addMaterialColorCallback(getMaterialColorChangeCallback());
        }
        DisposableHandle disposableHandle = this.disposableHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        AppCompatButton appCompatButton = this.oplusCloseAllButton;
        DisposableHandle disposableHandleRepeatWhenAttached$default = appCompatButton != null ? RepeatWhenAttachedKt.repeatWhenAttached$default(appCompatButton, (CoroutineContext) null, new AnonymousClass2(null), 3) : null;
        this.disposableHandle = disposableHandleRepeatWhenAttached$default;
        return disposableHandleRepeatWhenAttached$default == null ? new DisposableHandles() : disposableHandleRepeatWhenAttached$default;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0043  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateTextColor() {
        int color;
        if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            color = this.context.getColor(2131100031);
        } else {
            ScrimControllerEx.Companion.getClass();
            if (ScrimControllerEx.isThirdTheme) {
                color = this.context.getColor(2131100031);
            } else if (ScrimUtil.isLowGaussianLevel(this.context)) {
                color = this.context.getColor(2131102937);
            } else {
                color = NotificationColorPalette.Companion.from((MaterialColorMode) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).getCurrentMaterialColor().getValue()).getPrimaryColor();
            }
        }
        AppCompatButton appCompatButton = this.oplusCloseAllButton;
        if (appCompatButton != null) {
            appCompatButton.setTextColor(color);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final int viewTop() {
        AppCompatButton appCompatButton = this.oplusCloseAllButton;
        if (appCompatButton == null) {
            return 0;
        }
        int[] iArr = new int[2];
        appCompatButton.getLocationInWindow(iArr);
        int i = iArr[1];
        ViewGroup.LayoutParams layoutParams = appCompatButton.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        return i - (marginLayoutParams != null ? marginLayoutParams.topMargin : 0);
    }
}
