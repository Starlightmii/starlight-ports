package com.oplus.systemui.statusbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.util.MathUtils;
import android.widget.RelativeLayout;
import androidx.core.animation.ArgbEvaluator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.CarrierText;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.helper.ColorChangeCallback;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.keyguard.color.OplusKeyguardColorUtil;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.keyguard.anim.OplusPanelKeyguardAnimControllerImpl;
import com.oplus.systemui.keyguard.helper.OplusKeyguardTopAreaColorHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.widget.OplusKeyguardStatusBarClock;
import com.oplus.systemui.statusbar.widget.OplusStatCarrierText;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarColorController implements ColorChangeCallback {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusKeyguardStatusBarColorController";
    private final Runnable applyColorRunnable;
    private final Handler bgHandler;
    private final Context context;
    public int contrastColor;
    public int currentExtState;
    private final ArrayList<Rect> emptyTintRect;
    private TintedIconManager iconManager;
    public boolean isWallpaperLight;
    public OplusStatCarrierText keyguardCarrierTextView;
    public int keyguardColor;
    public float keyguardIntensity;
    private OplusKeyguardStatusBarClock keyguardStatusBarClock;
    public KeyguardStatusBarView keyguardStatusBarView;
    private final Lazy keyguardTopAreaColorHelper;
    private final LockScreenNotificationExtStateManager lockScreenExtStateController;
    private final Handler mainHandler;
    private final Lazy oplusPanelKeyguardAnimControllerImpl;
    public int statusBarState;
    private final OplusKeyguardStatusBarColorController$statusBarStateListener$1 statusBarStateListener;
    public boolean usingLightBgInShadeLock;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$bindView$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$bindView$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusKeyguardStatusBarColorController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$bindView$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01131 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusKeyguardStatusBarColorController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$bindView$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01141 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ OplusKeyguardStatusBarColorController this$0;

                    public /* synthetic */ C01141(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                OplusKeyguardStatusBarColorController.access$updatePanoramicAnimColor(this.this$0, ((Number) obj).floatValue());
                                break;
                            default:
                                ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) obj;
                                OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = this.this$0;
                                if (extStateEvent.getNewState() == 2 && oplusKeyguardStatusBarColorController.currentExtState != 2) {
                                    oplusKeyguardStatusBarColorController.currentExtState = extStateEvent.getNewState();
                                    oplusKeyguardStatusBarColorController.updateColor(0L, "enterImmersive");
                                } else if (extStateEvent.getNewState() != 2 && oplusKeyguardStatusBarColorController.currentExtState == 2) {
                                    oplusKeyguardStatusBarColorController.currentExtState = extStateEvent.getNewState();
                                    oplusKeyguardStatusBarColorController.updateColor(0L, "exitImmersive");
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01131(OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusKeyguardStatusBarColorController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01131(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow statusbarColorIntensityAnimAlpha = ((OplusPanelKeyguardAnimControllerImpl) this.this$0.oplusPanelKeyguardAnimControllerImpl.get()).getStatusbarColorIntensityAnimAlpha();
                        OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = this.this$0;
                        C01141 c01141 = new C01141(0);
                        c01141.this$0 = oplusKeyguardStatusBarColorController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (statusbarColorIntensityAnimAlpha.collect(c01141, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$bindView$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01152 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusKeyguardStatusBarColorController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01152(OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusKeyguardStatusBarColorController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01152(this.this$0, continuation);
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
                        StateFlow extStateEvent = this.this$0.lockScreenExtStateController.getExtStateEvent();
                        OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = this.this$0;
                        C01131.C01141 c01141 = new C01131.C01141(i2);
                        c01141.this$0 = oplusKeyguardStatusBarColorController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (extStateEvent.collect(c01141, this) == coroutine_suspended) {
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
            public AnonymousClass1(OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusKeyguardStatusBarColorController;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01131(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C01152(this.this$0, null), 2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = OplusKeyguardStatusBarColorController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusKeyguardStatusBarColorController.this, null);
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController$onColorBitmapChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $bitmap;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusKeyguardStatusBarColorController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = this.this$0;
                    OplusKeyguardStatusBarColorController.access$updateStatusBarShadow(oplusKeyguardStatusBarColorController, oplusKeyguardStatusBarColorController.isWallpaperLight, (Bitmap) this.$bitmap);
                    break;
                default:
                    float f = ((Ref.FloatRef) this.$bitmap).element;
                    RelativeLayout relativeLayout = this.this$0.keyguardStatusBarView;
                    if (f < 5.0f) {
                        if (relativeLayout == null) {
                            relativeLayout = null;
                        }
                        relativeLayout.setBackgroundResource(0);
                    } else {
                        if (relativeLayout == null) {
                            relativeLayout = null;
                        }
                        relativeLayout.setBackgroundResource(2131235821);
                    }
                    break;
            }
        }
    }

    public OplusKeyguardStatusBarColorController(Context context, Lazy lazy, Handler handler, Handler handler2, Lazy lazy2, LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager) {
        this.context = context;
        this.keyguardTopAreaColorHelper = lazy;
        this.bgHandler = handler;
        this.mainHandler = handler2;
        this.oplusPanelKeyguardAnimControllerImpl = lazy2;
        this.lockScreenExtStateController = lockScreenNotificationExtStateManager;
        OplusKeyguardStatusBarColorController$applyColorRunnable$1 oplusKeyguardStatusBarColorController$applyColorRunnable$1 = new OplusKeyguardStatusBarColorController$applyColorRunnable$1();
        oplusKeyguardStatusBarColorController$applyColorRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.applyColorRunnable = oplusKeyguardStatusBarColorController$applyColorRunnable$1;
        this.emptyTintRect = new ArrayList<>();
        this.keyguardColor = -855638017;
        this.contrastColor = -855638017;
        this.statusBarState = -1;
        OplusKeyguardStatusBarColorController$statusBarStateListener$1 oplusKeyguardStatusBarColorController$statusBarStateListener$1 = new OplusKeyguardStatusBarColorController$statusBarStateListener$1();
        oplusKeyguardStatusBarColorController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusKeyguardStatusBarColorController$statusBarStateListener$1;
    }

    public static final void access$updatePanoramicAnimColor(OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController, float f) {
        int iIntValue = ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(oplusKeyguardStatusBarColorController.context.getColor(2131103154)), Integer.valueOf(oplusKeyguardStatusBarColorController.keyguardColor)).intValue();
        CarrierText carrierText = oplusKeyguardStatusBarColorController.keyguardCarrierTextView;
        if (carrierText == null) {
            carrierText = null;
        }
        carrierText.setTextColor(iIntValue);
        TintedIconManager tintedIconManager = oplusKeyguardStatusBarColorController.iconManager;
        if (tintedIconManager != null) {
            tintedIconManager.setTint(iIntValue, oplusKeyguardStatusBarColorController.contrastColor);
        }
        float fLerp = MathUtils.lerp(0.1f, oplusKeyguardStatusBarColorController.keyguardIntensity, f);
        ArrayList<Rect> arrayList = oplusKeyguardStatusBarColorController.emptyTintRect;
        RelativeLayout relativeLayout = oplusKeyguardStatusBarColorController.keyguardStatusBarView;
        StatBatteryMeterView statBatteryMeterView = (StatBatteryMeterView) (relativeLayout != null ? relativeLayout : null).findViewById(2131362233);
        if (statBatteryMeterView != null) {
            statBatteryMeterView.onDarkChanged(arrayList, fLerp, iIntValue);
        }
    }

    public static final void access$updateStatusBarShadow(OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController, boolean z, Bitmap bitmap) {
        Ref.FloatRef floatRef = new Ref.FloatRef();
        int i = 1;
        if (!z) {
            Rect colorRect = ((OplusKeyguardTopAreaColorHelper) oplusKeyguardStatusBarColorController.keyguardTopAreaColorHelper.get()).getColorRect();
            Rect rect = bitmap != null ? new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()) : null;
            Rect[] rectArr = {colorRect};
            OplusKeyguardStatusBarColorController$$ExternalSyntheticLambda0 oplusKeyguardStatusBarColorController$$ExternalSyntheticLambda0 = new OplusKeyguardStatusBarColorController$$ExternalSyntheticLambda0();
            OplusKeyguardStatusBarColorController$sam$java_util_function_BiFunction$0 oplusKeyguardStatusBarColorController$sam$java_util_function_BiFunction$0 = new OplusKeyguardStatusBarColorController$sam$java_util_function_BiFunction$0();
            oplusKeyguardStatusBarColorController$sam$java_util_function_BiFunction$0.function = oplusKeyguardStatusBarColorController$$ExternalSyntheticLambda0;
            VarHandle.storeStoreFence();
            ArrayList arrayListCalculateAreaPercentageBySBCondition = OplusKeyguardColorUtil.calculateAreaPercentageBySBCondition(bitmap, rect, rectArr, oplusKeyguardStatusBarColorController$sam$java_util_function_BiFunction$0);
            if (arrayListCalculateAreaPercentageBySBCondition != null) {
                floatRef.element = ((Number) arrayListCalculateAreaPercentageBySBCondition.get(0)).floatValue();
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusKeyguardStatusBarColorController-->".concat("percentage=" + floatRef.element + ", wallpaper=" + bitmap + ", wallpaperRect=" + rect));
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusKeyguardStatusBarColorController-->".concat("isLight=" + z + ", percentage=" + floatRef.element));
        }
        Handler handler = oplusKeyguardStatusBarColorController.mainHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.$bitmap = floatRef;
        anonymousClass1.this$0 = oplusKeyguardStatusBarColorController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void bindIconManager(TintedIconManager tintedIconManager) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatusBarColorController-->".concat("bindIconManager " + tintedIconManager));
        }
        this.iconManager = tintedIconManager;
    }

    public final void bindView(KeyguardStatusBarView keyguardStatusBarView) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatusBarColorController-->".concat("bindView " + keyguardStatusBarView));
        }
        this.keyguardStatusBarView = keyguardStatusBarView;
        this.keyguardStatusBarClock = (OplusKeyguardStatusBarClock) keyguardStatusBarView.findViewById(2131363605);
        this.keyguardCarrierTextView = (OplusStatCarrierText) keyguardStatusBarView.requireViewById(2131363560);
        RepeatWhenAttachedKt.repeatWhenAttached$default(keyguardStatusBarView, Dispatchers.getMain().getImmediate(), new AnonymousClass2(null), 2);
    }

    public void onColorBitmapChanged(boolean z, Bitmap bitmap) {
        Handler handler = this.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        anonymousClass1.$bitmap = bitmap;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void onColorChanged(int i, boolean z) {
        if (z != this.isWallpaperLight) {
            this.isWallpaperLight = z;
            updateColor(0L, "onColorChanged");
        }
    }

    public final void onViewAttached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatusBarColorController-->onViewAttached");
        }
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this.statusBarStateListener);
        ((OplusKeyguardTopAreaColorHelper) this.keyguardTopAreaColorHelper.get()).addCallback(this);
    }

    public final void onViewDetached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatusBarColorController-->onViewDetached");
        }
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this.statusBarStateListener);
        ((OplusKeyguardTopAreaColorHelper) this.keyguardTopAreaColorHelper.get()).removeCallback(this);
    }

    public final void updateColor(long j, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("updateColor reason=", str, "OplusKeyguardStatusBarColorController-->", "SystemUi--Keyguard");
        }
        SystemUIThreadUtil.removeCallbacksInUiThread(this.applyColorRunnable);
        if (j > 0) {
            SystemUIThreadUtil.runOnUiThreadDelay(this.applyColorRunnable, j);
        } else {
            updateColorInternal();
        }
    }

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
    public final void updateColorInternal() {
        boolean z = (this.isWallpaperLight || this.usingLightBgInShadeLock) && this.currentExtState != 2;
        Context context = this.context;
        int color = z ? context.getColor(2131101481) : context.getColor(2131101840);
        this.keyguardColor = color;
        if (this.statusBarState == 2) {
            Clock clock = this.keyguardStatusBarClock;
            if (clock != null) {
                clock.setTextColor(color);
            }
        } else {
            CarrierText carrierText = this.keyguardCarrierTextView;
            if (carrierText == null) {
                carrierText = null;
            }
            carrierText.setTextColor(color);
        }
        int i = z ? -16777216 : -855638017;
        this.contrastColor = i;
        TintedIconManager tintedIconManager = this.iconManager;
        if (tintedIconManager != null) {
            tintedIconManager.setTint(this.keyguardColor, i);
        }
        float f = z ? 1.0f : 0.0f;
        this.keyguardIntensity = f;
        ArrayList<Rect> arrayList = this.emptyTintRect;
        int i2 = this.keyguardColor;
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        StatBatteryMeterView statBatteryMeterView = (StatBatteryMeterView) (relativeLayout != null ? relativeLayout : null).findViewById(2131362233);
        if (statBatteryMeterView != null) {
            statBatteryMeterView.onDarkChanged(arrayList, f, i2);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateColorInternal: isBackgroundLightFixed: ", "OplusKeyguardStatusBarColorController-->", "SystemUi--Keyguard", z);
        }
    }
}
