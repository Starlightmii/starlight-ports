package com.oplus.systemui.statusbar.notification.stack;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RuntimeShader;
import android.os.Handler;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.statusbar.NotificationShadeDepthControllerEx;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.VisualStabilityCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.stack.NotificationSeedlingInteractor;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterEx;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.keyguard.anim.OplusPanelKeyguardAnimControllerImpl;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.lockscreen.LockScreenExpandOriginController;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomMenuRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutControllerExtImpl extends NotificationStackScrollLayoutControllerExt {
    public static final Companion Companion = new Companion();
    private static final String MASK_AGSL = "uniform float fade;\nhalf4 main(float2 coord) {\n    float a = min(coord.y / fade, 1.0);\n    return half4(1.0, 1.0, 1.0, a);\n}\n";
    private static final String TAG = "NotificationStackScrollLayoutControllerExtImpl";
    private final MutableStateFlow _viewScaleFromPanelFlow;
    private final NotificationDependOnCommon common;
    public boolean hasRenderEffect;
    private final Lazy headsUpController;
    public boolean hiddenByKeyguardQsCover;
    public boolean isDownInNsslAlphaArea;
    public boolean isScrolling;
    private final Lazy keyguardUpdateMonitor;
    private final Lazy lockScreenExpandOriginController;
    private final Handler mainHandler;
    private final Lazy materialBlurStateManager;
    private final NotificationStackScrollLayoutControllerExtImpl$notifListener$1 notifListener;
    private final NotifPipeline notifPipeline;
    private NotificationStackScrollLayout notifScroller;
    private final Lazy notificationActivityStarterLazy;
    private final Lazy notificationCapsuleInteractor;
    private final Lazy notificationPanelViewControllerEx;
    private final Lazy notificationSectionsManagerEx;
    private final NotificationSeedlingInteractor notificationSeedlingInteractor;
    private final Lazy notificationShadeDepthControllerEx;
    private final Lazy oplusPanelKeyguardAnimControllerImpl;
    private final Lazy oplusPanoramicAodRepository;
    public int ownScrollY;
    private final Runnable resetScrollState;
    private final Runnable scrollEndDebounceAction;
    private final SeedlingPluginManager seedlingPluginManager;
    public int statusBarState;
    private final StatusBarStateController statusBarStateController;
    private NotificationSwipeHelper swipeHelper;
    public int tiltShiftBlurContainerHeight;
    private final int[] tmpLocations;
    public float viewScaleFromPanel;
    public float viewScaleFromPanelAnim;
    public VisualStabilityCoordinatorEx visualStabilityCoordinatorEx;
    private final IOplusGcSupressionExt oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
    private final kotlin.Lazy strategy$delegate = LazyKt__LazyJVMKt.lazy(new NotificationStackScrollLayoutControllerExtImpl$$ExternalSyntheticLambda0());
    private final TopFadeDrawer topFadeDrawer = new TopFadeDrawer();
    private final CopyOnWriteArrayList<NotificationStackScrollLayoutControllerExt.OnSizeChangedListener> sizeChangedListeners = new CopyOnWriteArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TopFadeDrawer implements NotificationStackScrollLayout.OplusDispatchDrawHook {
        public int fadeHeight;
        private final Paint maskPaint;
        private RuntimeShader maskShader;
        public int maskShaderHeight;

        public TopFadeDrawer() {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.maskPaint = paint;
            this.maskShaderHeight = -1;
        }

        public final int getFadeHeight() {
            return this.fadeHeight;
        }

        public boolean onDispatchDraw(Canvas canvas) {
            int iMin;
            float f;
            float f2;
            ViewGroup view;
            NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = NotificationStackScrollLayoutControllerExtImpl.this;
            NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutControllerExtImpl.notifScroller;
            if (notificationStackScrollLayout != null && this.fadeHeight > 0 && canvas.isHardwareAccelerated()) {
                int width = notificationStackScrollLayout.getWidth();
                NotificationPanelViewController base = ((NotificationPanelViewControllerEx) notificationStackScrollLayoutControllerExtImpl.notificationPanelViewControllerEx.get()).getBase();
                int iMax = Math.max(width, (base == null || (view = base.getView()) == null) ? 0 : view.getWidth());
                int height = notificationStackScrollLayout.getHeight();
                if (iMax > 0 && height > 0 && (iMin = Math.min(this.fadeHeight, height)) > 0) {
                    canvas.save();
                    canvas.clipRect(Integer.MIN_VALUE, iMin, Integer.MAX_VALUE, Integer.MAX_VALUE);
                    notificationStackScrollLayout.drawDispatchedChildren(canvas);
                    canvas.restore();
                    if (((NotificationStackScrollLayoutExtImpl) notificationStackScrollLayout.mExt).isHorizontalSwitch) {
                        f = iMax;
                        f2 = 0.20000005f * f;
                    } else {
                        f = 0.0f;
                        f2 = 0.0f;
                    }
                    float f3 = -f2;
                    float f4 = iMax + f;
                    float f5 = iMin;
                    int iSaveLayer = canvas.saveLayer(f3, 0.0f, f4, f5, null);
                    notificationStackScrollLayout.drawDispatchedChildren(canvas);
                    RuntimeShader runtimeShader = this.maskShader;
                    if (runtimeShader == null) {
                        runtimeShader = new RuntimeShader(NotificationStackScrollLayoutControllerExtImpl.MASK_AGSL);
                        this.maskShader = runtimeShader;
                        this.maskPaint.setShader(runtimeShader);
                    }
                    if (this.maskShaderHeight != iMin) {
                        runtimeShader.setFloatUniform("fade", f5);
                        this.maskShaderHeight = iMin;
                    }
                    canvas.drawRect(f3, 0.0f, f4, f5, this.maskPaint);
                    canvas.restoreToCount(iSaveLayer);
                    return true;
                }
            }
            return false;
        }

        public final void releaseMaskResources() {
            this.maskPaint.setShader(null);
            this.maskShader = null;
            this.maskShaderHeight = -1;
        }

        public final void setFadeHeight(int i) {
            this.fadeHeight = i;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl$attach$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl$attach$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl$attach$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01251 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl$attach$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01261 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

                    public /* synthetic */ C01261(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                float fFloatValue = ((Number) obj).floatValue();
                                NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = this.this$0;
                                notificationStackScrollLayoutControllerExtImpl.viewScaleFromPanelAnim = fFloatValue;
                                notificationStackScrollLayoutControllerExtImpl.updateNotificationViewScale(false);
                                break;
                            default:
                                float fFloatValue2 = ((Number) obj).floatValue();
                                NotificationStackScrollLayout notificationStackScrollLayout = this.this$0.notifScroller;
                                if (notificationStackScrollLayout != null) {
                                    notificationStackScrollLayout.setTranslationYForPanelAnim(fFloatValue2);
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01251(NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = notificationStackScrollLayoutControllerExtImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01251(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow notificationAnimScale = ((OplusPanelKeyguardAnimControllerImpl) this.this$0.oplusPanelKeyguardAnimControllerImpl.get()).getNotificationAnimScale();
                        NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = this.this$0;
                        C01261 c01261 = new C01261(0);
                        c01261.this$0 = notificationStackScrollLayoutControllerExtImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (notificationAnimScale.collect(c01261, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl$attach$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01272 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01272(NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = notificationStackScrollLayoutControllerExtImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01272(this.this$0, continuation);
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
                        Flow notificationAnimTranslateY = ((OplusPanelKeyguardAnimControllerImpl) this.this$0.oplusPanelKeyguardAnimControllerImpl.get()).getNotificationAnimTranslateY();
                        NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = this.this$0;
                        C01251.C01261 c01261 = new C01251.C01261(i2);
                        c01261.this$0 = notificationStackScrollLayoutControllerExtImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (notificationAnimTranslateY.collect(c01261, this) == coroutine_suspended) {
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
            public AnonymousClass1(NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationStackScrollLayoutControllerExtImpl;
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
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C01251(this.this$0, null), 2);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C01272(this.this$0, null), 2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = NotificationStackScrollLayoutControllerExtImpl.this.new AnonymousClass2((Continuation) obj3);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(NotificationStackScrollLayoutControllerExtImpl.this, null);
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

    public NotificationStackScrollLayoutControllerExtImpl(Lazy lazy, SeedlingPluginManager seedlingPluginManager, NotificationDependOnCommon notificationDependOnCommon, Lazy lazy2, StatusBarStateController statusBarStateController, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, NotificationSeedlingInteractor notificationSeedlingInteractor, NotifPipeline notifPipeline, Lazy lazy7, Handler handler, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11) {
        this.notificationSectionsManagerEx = lazy;
        this.seedlingPluginManager = seedlingPluginManager;
        this.common = notificationDependOnCommon;
        this.keyguardUpdateMonitor = lazy2;
        this.statusBarStateController = statusBarStateController;
        this.notificationActivityStarterLazy = lazy3;
        this.notificationShadeDepthControllerEx = lazy4;
        this.oplusPanelKeyguardAnimControllerImpl = lazy5;
        this.oplusPanoramicAodRepository = lazy6;
        this.notificationSeedlingInteractor = notificationSeedlingInteractor;
        this.notifPipeline = notifPipeline;
        this.headsUpController = lazy7;
        this.mainHandler = handler;
        this.notificationCapsuleInteractor = lazy8;
        this.notificationPanelViewControllerEx = lazy9;
        this.materialBlurStateManager = lazy10;
        this.lockScreenExpandOriginController = lazy11;
        NotificationStackScrollLayoutControllerExtImpl$notifListener$1 notificationStackScrollLayoutControllerExtImpl$notifListener$1 = new NotificationStackScrollLayoutControllerExtImpl$notifListener$1();
        notificationStackScrollLayoutControllerExtImpl$notifListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifListener = notificationStackScrollLayoutControllerExtImpl$notifListener$1;
        NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1 notificationStackScrollLayoutControllerExtImpl$resetScrollState$1 = new NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1(1);
        notificationStackScrollLayoutControllerExtImpl$resetScrollState$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.scrollEndDebounceAction = notificationStackScrollLayoutControllerExtImpl$resetScrollState$1;
        NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1 notificationStackScrollLayoutControllerExtImpl$resetScrollState$2 = new NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1(0);
        notificationStackScrollLayoutControllerExtImpl$resetScrollState$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.resetScrollState = notificationStackScrollLayoutControllerExtImpl$resetScrollState$2;
        this._viewScaleFromPanelFlow = StateFlowKt.MutableStateFlow(Float.valueOf(1.0f));
        this.viewScaleFromPanel = 1.0f;
        this.viewScaleFromPanelAnim = 1.0f;
        this.tmpLocations = new int[2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotifInflateDelayStrategy getStrategy() {
        return (NotifInflateDelayStrategy) this.strategy$delegate.getValue();
    }

    private final boolean getTiltShiftBlurEnabled() {
        return ((MaterialBlurStateManager) this.materialBlurStateManager.get()).getTiltShiftBlurEnabled();
    }

    public void addOnSizeChangedListener(NotificationStackScrollLayoutControllerExt.OnSizeChangedListener onSizeChangedListener) {
        this.sizeChangedListeners.addIfAbsent(onSizeChangedListener);
    }

    public final void applyKeyguardQsCoverVisibility(NotificationStackScrollLayout notificationStackScrollLayout, float f, float f2, int i) {
        if (notificationStackScrollLayout.getVisibility() == i) {
            this.hiddenByKeyguardQsCover = i == 4;
            return;
        }
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("syncVisibilityForKeyguardQsCoverAlpha: ", notificationStackScrollLayout.getVisibility(), i, " -> ", " alpha=");
        sbM.append(f);
        sbM.append(" qsExpansion=");
        sbM.append(f2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        notificationStackScrollLayout.setVisibility(i);
        this.hiddenByKeyguardQsCover = i == 4;
    }

    public final void applyTopFade(View view, String str, int i) {
        if (view == null || this.ownScrollY == 0 || !getTiltShiftBlurEnabled() || i == 0) {
            return;
        }
        try {
            this.topFadeDrawer.setFadeHeight(i);
            this.hasRenderEffect = true;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "applyTopFade width: " + view.getWidth() + "; height: " + i + "; reason: " + str, null, 8);
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public void attach(NotificationStackScrollLayout notificationStackScrollLayout, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationSwipeHelper notificationSwipeHelper) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationStackScrollLayoutControllerExtImpl-->attach");
        }
        this.notifScroller = notificationStackScrollLayout;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.setOplusDispatchDrawHook(this.topFadeDrawer);
        }
        this.swipeHelper = notificationSwipeHelper;
        this.seedlingPluginManager.setNssLController(notificationStackScrollLayoutController);
        this.visualStabilityCoordinatorEx = (VisualStabilityCoordinatorEx) DependencyEx.sDependency.getDependency(VisualStabilityCoordinatorEx.class);
        this.notifPipeline.addCollectionListener(this.notifListener);
        if (notificationStackScrollLayout != null) {
            RepeatWhenAttachedKt.repeatWhenAttached$default(notificationStackScrollLayout, (CoroutineContext) null, new AnonymousClass2(null), 3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0097  */
    /* JADX WARN: Code duplicated, block: B:45:0x009b  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:61:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:66:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:68:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean canChildBeDismissedInDirectionOverride(View view, boolean z) {
        boolean z2;
        OplusCustomMenuRow oplusCustomMenuRow;
        CustomRowEntry entry;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("canChildBeDismissedInDirectionOverride isRightOrDown=", "NotificationStackScrollLayoutControllerExtImpl-->", "SystemUi--Notification", z);
        }
        if (view != 0 && (view instanceof ExpandableNotificationRow)) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            int state = expandableNotificationRow.mIsHeadsUp ? 1000 : this.statusBarStateController.getState();
            if (z) {
                NotificationStatisticUtil.collectNotificationMenuEvent(expandableNotificationRow.getContext(), expandableNotificationRow.getEntry(), "", "swipe_right", state);
            } else {
                NotificationStatisticUtil.collectNotificationMenuEvent(expandableNotificationRow.getContext(), expandableNotificationRow.getEntry(), "", "swipe_left", state);
            }
        }
        if (view == 0 || !view.isLayoutRtl()) {
            z2 = !z;
        } else {
            z2 = z;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() || !z2 || !(view instanceof ExpandableNotificationRow)) {
            if (view instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
                NotificationMenuRowPlugin provider = oplusCustomRow.getProvider();
                oplusCustomMenuRow = provider instanceof OplusCustomMenuRow ? (OplusCustomMenuRow) provider : null;
                if ((oplusCustomMenuRow != null || oplusCustomMenuRow.mMenuSnapped || oplusCustomMenuRow.mSnappingToDismiss || z) && (oplusCustomMenuRow == null || !oplusCustomMenuRow.isImmersiveState())) {
                    entry = oplusCustomRow.getEntry();
                    if (entry != null && entry.isShowDeleteMenu()) {
                        return true;
                    }
                }
            }
            int i = NotificationStackScrollLayout.$r8$clinit;
            if (view instanceof ExpandableNotificationRow) {
                return ((ExpandableNotificationRow) view).canExpandableViewBeDismissed();
            }
            return false;
        }
        ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) view;
        if (expandableNotificationRow2.mPinnedStatus.isPinned()) {
            if (view instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow2 = (OplusCustomRow) view;
                NotificationMenuRowPlugin provider2 = oplusCustomRow2.getProvider();
                if (provider2 instanceof OplusCustomMenuRow) {
                }
                if (oplusCustomMenuRow != null) {
                }
                entry = oplusCustomRow2.getEntry();
                if (entry != null) {
                    return true;
                }
            }
            int i2 = NotificationStackScrollLayout.$r8$clinit;
            if (view instanceof ExpandableNotificationRow) {
                return ((ExpandableNotificationRow) view).canExpandableViewBeDismissed();
            }
            return false;
        }
        NotificationMenuRow provider3 = expandableNotificationRow2.getProvider();
        NotificationMenuRow notificationMenuRow = provider3 instanceof NotificationMenuRow ? provider3 : null;
        if (notificationMenuRow != null && (notificationMenuRow.isMenuSnapped() || notificationMenuRow.isSnappingToDismiss())) {
            if (view instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow3 = (OplusCustomRow) view;
                NotificationMenuRowPlugin provider4 = oplusCustomRow3.getProvider();
                if (provider4 instanceof OplusCustomMenuRow) {
                }
                if (oplusCustomMenuRow != null) {
                }
                entry = oplusCustomRow3.getEntry();
                if (entry != null) {
                    return true;
                }
            }
            int i3 = NotificationStackScrollLayout.$r8$clinit;
            if (view instanceof ExpandableNotificationRow) {
                return ((ExpandableNotificationRow) view).canExpandableViewBeDismissed();
            }
            return false;
        }
        return false;
    }

    public boolean canMenuReset(View view, NotificationSwipeHelper notificationSwipeHelper) {
        return notificationSwipeHelper.getExposedMenuView() != null && Intrinsics.areEqual(view, notificationSwipeHelper.getExposedMenuView());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean canOplusCustomRowBeDismiss(View view) {
        CustomRowEntry entry;
        if ((view instanceof OplusCustomRow) && (entry = ((OplusCustomRow) view).getEntry()) != null) {
            return entry.isShowDeleteMenu();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dismissOplusCustomRow(View view) {
        if (view instanceof OplusCustomRow) {
            ((OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class)).dissmissCustomRow((OplusCustomRow) view, OplusCustomRowControllerManager.ACTION_TYPE_RIGHT_DELETE);
        }
    }

    public final void endScrollNotificationList(boolean z) {
        this.isScrolling = false;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "isScrolling: false", null, 8);
        NotificationShadeDepthControllerEx notificationShadeDepthControllerEx = (NotificationShadeDepthControllerEx) this.notificationShadeDepthControllerEx.get();
        if (notificationShadeDepthControllerEx != null) {
            notificationShadeDepthControllerEx.restrainNavigationBarInvertColor(2, false);
        }
        Handler handler = this.mainHandler;
        Runnable runnable = this.scrollEndDebounceAction;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 100L);
        if (z) {
            JankUtils.gfxSceneEnd(this.common.getApplicationContext(), new int[]{4});
        }
    }

    public int getConstrainSwipeStartPosition(NotificationMenuRowPlugin notificationMenuRowPlugin) {
        View menuView = notificationMenuRowPlugin.getMenuView();
        return (menuView == null || !(menuView instanceof ViewGroup) || ((ViewGroup) menuView).getChildCount() > 0 || !(notificationMenuRowPlugin instanceof NotificationMenuRow)) ? super.getConstrainSwipeStartPosition(notificationMenuRowPlugin) : Math.abs((int) ((NotificationMenuRow) notificationMenuRowPlugin).getDismissThreshold());
    }

    public boolean getHasOplusLogic() {
        return true;
    }

    public int getNsslWidth() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.notifScroller;
        if (notificationStackScrollLayout != null) {
            return notificationStackScrollLayout.getWidth();
        }
        return 0;
    }

    public Pair<Float, Float> getSettingDismissAnimator(float f, float f2, View view) {
        float f3;
        int[] locationOnScreen;
        NotificationStackScrollLayout notificationStackScrollLayout = this.notifScroller;
        float topPadding = 0.0f;
        if (notificationStackScrollLayout != null) {
            Context applicationContext = this.common.getApplicationContext();
            float translationY = view != null ? view.getTranslationY() : 0.0f;
            float height = view != null ? view.getHeight() : 0.0f;
            int dimensionPixelOffset = applicationContext.getResources().getDimensionPixelOffset(2131170782);
            int currentStackHeight = ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class)).getCurrentStackHeight(notificationStackScrollLayout);
            float topPadding2 = notificationStackScrollLayout.getTopPadding();
            if (topPadding2 < f) {
                topPadding2 = f;
            }
            float f4 = currentStackHeight - dimensionPixelOffset;
            if (topPadding2 > f4) {
                topPadding2 = f4;
            }
            f3 = (topPadding2 - ((view == null || (locationOnScreen = view.getLocationOnScreen()) == null) ? 0 : locationOnScreen[1])) + translationY;
            float f5 = f + f2;
            if (f5 > f4) {
                float f6 = currentStackHeight + dimensionPixelOffset;
                if (f5 > f6) {
                    f5 = f6;
                }
                float f7 = f5 - f;
                if (0.0f < f7) {
                    topPadding = f7;
                }
            } else if (f < notificationStackScrollLayout.getTopPadding()) {
                topPadding = (f2 - notificationStackScrollLayout.getTopPadding()) + f;
            }
            if (topPadding > height) {
                topPadding = height / 2.0f;
            }
        } else {
            f3 = 0.0f;
        }
        return Pair.create(Float.valueOf(topPadding), Float.valueOf(f3));
    }

    public Pair<Float, Integer> getSettingInitialAnimator(float f, float f2, float f3) {
        float f4;
        Context applicationContext = this.common.getApplicationContext();
        int topPadding = 0;
        if (LargeScreenUtils.shouldUseSplitNotificationShade(applicationContext.getResources())) {
            float height = ((CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class)).getShadeViewController().getView().getHeight();
            float f5 = f + f3;
            if (f5 > height) {
                topPadding = (int) (height - f5);
                f = height - f3;
            }
        } else {
            int dimensionPixelOffset = applicationContext.getResources().getDimensionPixelOffset(2131170782);
            NotificationStackScrollLayout notificationStackScrollLayout = this.notifScroller;
            if (notificationStackScrollLayout != null) {
                int currentStackHeight = ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class)).getCurrentStackHeight(notificationStackScrollLayout);
                float f6 = f + f3;
                int i = notificationStackScrollLayout.mMaxLayoutHeight;
                if (f6 > i - dimensionPixelOffset) {
                    int i2 = currentStackHeight + dimensionPixelOffset;
                    if (i > i2) {
                        i = i2;
                    }
                    topPadding = ((i - dimensionPixelOffset) - ((int) f3)) - ((int) f);
                    float f7 = f + f2;
                    float f8 = currentStackHeight;
                    if (f7 > f8) {
                        f7 = f8;
                    }
                    f4 = f7 - f;
                } else {
                    f4 = f2;
                }
                if (f < notificationStackScrollLayout.getTopPadding()) {
                    float f9 = (f + f2) - currentStackHeight;
                    if (0.0f >= f9) {
                        f9 = 0.0f;
                    }
                    f2 = (f2 - (notificationStackScrollLayout.getTopPadding() - f)) - f9;
                    float topPadding2 = notificationStackScrollLayout.getTopPadding();
                    if (f < topPadding2) {
                        f = topPadding2;
                    }
                    if (f + f3 > currentStackHeight - dimensionPixelOffset) {
                        int i3 = notificationStackScrollLayout.mMaxLayoutHeight;
                        int i4 = currentStackHeight + dimensionPixelOffset;
                        if (i3 > i4) {
                            i3 = i4;
                        }
                        topPadding = ((i3 - dimensionPixelOffset) - ((int) f3)) - notificationStackScrollLayout.getTopPadding();
                    }
                    f = f2;
                } else {
                    f = f4;
                }
            } else {
                f = f2;
            }
        }
        if (f > f3) {
            f = f3 / 2.0f;
        }
        return Pair.create(Float.valueOf(f), Integer.valueOf(topPadding));
    }

    public final float getViewScaleFromPanel() {
        return this.viewScaleFromPanel;
    }

    public final float getViewScaleFromPanelAnim() {
        return this.viewScaleFromPanelAnim;
    }

    public final Flow getViewScaleFromPanelFlow() {
        return this._viewScaleFromPanelFlow;
    }

    public boolean handleNsslAlphaAreaDownEvent(MotionEvent motionEvent) {
        NotificationStackScrollLayout notificationStackScrollLayout;
        Resources resources;
        NotificationPanelViewController notificationPanelController;
        boolean z = false;
        if (motionEvent.getActionMasked() == 0) {
            if (getTiltShiftBlurEnabled() && (notificationStackScrollLayout = this.notifScroller) != null && notificationStackScrollLayout.getIsExpanded()) {
                NotificationStackScrollLayout notificationStackScrollLayout2 = this.notifScroller;
                int minExpansionHeight = (notificationStackScrollLayout2 == null || (notificationPanelController = notificationStackScrollLayout2.getNotificationPanelController()) == null) ? 0 : notificationPanelController.getMinExpansionHeight();
                NotificationStackScrollLayout notificationStackScrollLayout3 = this.notifScroller;
                if (((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getOplusQSContainerEx().isInsideQsContainer(motionEvent, minExpansionHeight, (notificationStackScrollLayout3 == null || (resources = notificationStackScrollLayout3.getResources()) == null) ? false : LargeScreenUtils.shouldUseSplitNotificationShade(resources)) && this.ownScrollY != 0) {
                    z = true;
                }
            }
            this.isDownInNsslAlphaArea = z;
        }
        return z;
    }

    public boolean handleNsslAlphaAreaTouchEvent(MotionEvent motionEvent) {
        NotificationPanelViewController notificationPanelController;
        if (this.isDownInNsslAlphaArea) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onTouchEvent onEmptySpaceClick", null, 8);
                NotificationStackScrollLayout notificationStackScrollLayout = this.notifScroller;
                if (notificationStackScrollLayout != null && (notificationPanelController = notificationStackScrollLayout.getNotificationPanelController()) != null) {
                    notificationPanelController.onEmptySpaceClick(motionEvent.getRawX(), motionEvent.getRawY());
                }
                this.isDownInNsslAlphaArea = false;
                return true;
            }
            if (actionMasked == 2) {
                NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
                if (notificationSwipeHelper != null) {
                    notificationSwipeHelper.cancelLongPress();
                }
            } else if (actionMasked == 3) {
                this.isDownInNsslAlphaArea = false;
                return true;
            }
        }
        return false;
    }

    public final boolean interceptOriginBlur() {
        return getTiltShiftBlurEnabled();
    }

    public boolean isOplusCustomRow(View view) {
        return view instanceof OplusCustomRow;
    }

    public boolean isTouchInSideEmptyArea(NotificationStackScrollLayout notificationStackScrollLayout, MotionEvent motionEvent) {
        if (notificationStackScrollLayout.getFirstChildNotGone() instanceof EmptyShadeView) {
            return false;
        }
        ExpandableView firstChildNotGone = notificationStackScrollLayout.getFirstChildNotGone();
        ExpandableView lastChildNotGone = notificationStackScrollLayout.getLastChildNotGone();
        if (firstChildNotGone != null && lastChildNotGone != null) {
            float translationY = firstChildNotGone.getTranslationY();
            float scaleY = (lastChildNotGone.getScaleY() * lastChildNotGone.getActualHeight()) + lastChildNotGone.getTranslationY();
            float width = (notificationStackScrollLayout.getWidth() - firstChildNotGone.getWidth()) / 2.0f;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("isTouchInSideEmptyArea, startY: ", translationY, ", endY: ", scaleY, ", padding: ");
                sbM.append(width);
                sbM.append(", event: ");
                sbM.append(motionEvent);
                Log.d("SystemUi--Notification", "NotificationStackScrollLayoutControllerExtImpl-->".concat(sbM.toString()));
            }
            if ((motionEvent.getX() < width || motionEvent.getX() > notificationStackScrollLayout.getWidth() - width) && motionEvent.getY() >= translationY && motionEvent.getY() <= scaleY) {
                return true;
            }
        }
        return false;
    }

    public final boolean needForceEnableAnimations(boolean z) {
        if (z || !((OplusPanoramicAodRepository) this.oplusPanoramicAodRepository.get()).getShouldUpdateUi() || ((OplusPanoramicAodCoreStartable) ((OplusPanoramicAodRepository) this.oplusPanoramicAodRepository.get())).isInScreenOffProcess) {
            return false;
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            return true;
        }
        Log.d("SystemUi--Notification", "NotificationStackScrollLayoutControllerExtImpl-->needForceEnableAnimations::change to enable because shouldUpdateUi");
        return true;
    }

    public final void notifySizeChanged(int i, int i2, int i3, int i4) {
        Object obj;
        if (this.sizeChangedListeners.isEmpty()) {
            return;
        }
        Iterator<NotificationStackScrollLayoutControllerExt.OnSizeChangedListener> it = this.sizeChangedListeners.iterator();
        while (it.hasNext()) {
            try {
                ((OplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1) it.next()).onSizeChanged(i, i3);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                th2.printStackTrace();
            }
        }
    }

    public void notifyViewGroupChangeAllowed(View view) {
        VisualStabilityCoordinatorEx visualStabilityCoordinatorEx = this.visualStabilityCoordinatorEx;
        if (visualStabilityCoordinatorEx == null) {
            visualStabilityCoordinatorEx = null;
        }
        visualStabilityCoordinatorEx.notifyViewGroupChangeAllowed(view);
    }

    public final void nsslAlphaConfigChanged() {
        if (!getTiltShiftBlurEnabled()) {
            resetTopFade("nsslAlphaConfigChanged");
            this.topFadeDrawer.releaseMaskResources();
        } else if (this.statusBarState != 1) {
            applyTopFade(this.notifScroller, "nsslAlphaConfigChanged", this.tiltShiftBlurContainerHeight);
        }
    }

    public void onLockScreenExpandClicked(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        ((LockScreenExpandOriginController) this.lockScreenExpandOriginController.get()).onLockScreenExpandClicked(expandableNotificationRow, z);
    }

    public boolean onMenuClicked(View view, int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem, NotificationSwipeHelper notificationSwipeHelper) {
        View contentView;
        if (view instanceof ExpandableNotificationRow) {
            Object gutsContent = menuItem.getGutsContent();
            if (gutsContent == null ? true : gutsContent instanceof NotificationGuts.GutsContent) {
                Object gutsContent2 = menuItem.getGutsContent();
                NotificationGuts.GutsContent gutsContent3 = gutsContent2 instanceof NotificationGuts.GutsContent ? (NotificationGuts.GutsContent) gutsContent2 : null;
                Object tag = (gutsContent3 == null || (contentView = gutsContent3.getContentView()) == null) ? null : contentView.getTag(2131364198);
                if (Intrinsics.areEqual(tag, 3)) {
                    NotificationStatisticUtil.setReasonWhenRemoving(11);
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                    NotificationMenuRowPlugin provider = expandableNotificationRow.getProvider();
                    if ((provider != null ? provider.getMenuView() : null) != null) {
                        NotificationMenuRowPlugin provider2 = expandableNotificationRow.getProvider();
                        expandableNotificationRow.removeView(provider2 != null ? provider2.getMenuView() : null);
                        notificationSwipeHelper.clearExposedMenuView("onMenuClicked");
                        notificationSwipeHelper.setTranslatingParentView((View) null);
                    }
                    notificationSwipeHelper.dismiss(view, 0.0f);
                    return true;
                }
                if (Intrinsics.areEqual(tag, 0)) {
                    NotificationStatisticUtil.setSwipeLeftToShowMenu(true);
                    ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) view;
                    expandableNotificationRow2.doLongClickCallback(expandableNotificationRow2.getWidth() / 2, expandableNotificationRow2.getHeight() / 2);
                    return true;
                }
            }
        }
        return false;
    }

    public final void onRequestDisallowLongPress() {
        ((HeadsUpContainerWindow) this.headsUpController.get()).requestDisallowLongPress();
    }

    public boolean onStartFloatWindow(ExpandableNotificationRow expandableNotificationRow, float f) {
        OplusStatusBarNotificationActivityStarterEx activityStarterEx;
        Object obj = this.notificationActivityStarterLazy.get();
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = obj instanceof StatusBarNotificationActivityStarter ? (StatusBarNotificationActivityStarter) obj : null;
        return (statusBarNotificationActivityStarter == null || (activityStarterEx = statusBarNotificationActivityStarter.getActivityStarterEx()) == null || !activityStarterEx.onStartFloatWindow(statusBarNotificationActivityStarter, expandableNotificationRow, f)) ? false : true;
    }

    public final void onStatusBarStateChanged(int i) {
        this.statusBarState = i;
        if (i != 1) {
            this.hiddenByKeyguardQsCover = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void oplusCustomMenuOnClick(View view, NotificationSwipeHelper notificationSwipeHelper, NotificationMenuRowPlugin.MenuItem menuItem) {
        if (view instanceof OplusCustomRow) {
            Object gutsContent = menuItem.getGutsContent();
            NotificationGuts.GutsContent gutsContent2 = gutsContent instanceof NotificationGuts.GutsContent ? (NotificationGuts.GutsContent) gutsContent : null;
            View contentView = gutsContent2 != null ? gutsContent2.getContentView() : null;
            Object tag = contentView != null ? contentView.getTag(2131364198) : null;
            Integer num = tag instanceof Integer ? (Integer) tag : null;
            if (num != null && num.intValue() == 0) {
                ((OplusCustomRow) view).doLongClick(OplusCustomRowControllerManager.ACTION_TYPE_LEFT_SWIPE);
                return;
            }
            if (num != null && num.intValue() == 3) {
                OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
                oplusCustomRow.closeMenuView();
                notificationSwipeHelper.clearExposedMenuView("oplusCustomMenuOnClick");
                notificationSwipeHelper.setTranslatingParentView((View) null);
                notificationSwipeHelper.dismiss(view, 0.0f);
                ((OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class)).dissmissCustomRow(oplusCustomRow, OplusCustomRowControllerManager.ACTION_TYPE_LEFT_SWIPE_CLICK_DELETE);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeFromTransientContainer(View view) {
        OplusCustomRow oplusCustomRow = view instanceof OplusCustomRow ? (OplusCustomRow) view : null;
        if (oplusCustomRow != null) {
            oplusCustomRow.removeFromTransientContainer();
        }
    }

    public void removeOnSizeChangedListener(NotificationStackScrollLayoutControllerExt.OnSizeChangedListener onSizeChangedListener) {
        this.sizeChangedListeners.remove(onSizeChangedListener);
    }

    public void removeViewGroupChangeAllowed(View view) {
        if (view instanceof ExpandableNotificationRow) {
            VisualStabilityCoordinatorEx visualStabilityCoordinatorEx = this.visualStabilityCoordinatorEx;
            if (visualStabilityCoordinatorEx == null) {
                visualStabilityCoordinatorEx = null;
            }
            visualStabilityCoordinatorEx.removeViewGroupChangeAllowed(view);
        }
    }

    public final void resetTopFade(String str) {
        if (this.hasRenderEffect) {
            this.topFadeDrawer.setFadeHeight(0);
            this.hasRenderEffect = false;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "reset renderEffect: ".concat(str), null, 8);
        }
    }

    public void setOwnScrollY(int i) {
        this.ownScrollY = i;
        if (i == 0) {
            resetTopFade("setOwnScrollY");
        } else {
            if (this.hasRenderEffect) {
                return;
            }
            applyTopFade(this.notifScroller, "setOwnScrollY", this.tiltShiftBlurContainerHeight);
        }
    }

    public void showEmptyShadeViewWhenExpansionStopped(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
    }

    public void snapChildIfNeeded(View view, boolean z, float f) {
        NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
        if (notificationSwipeHelper != null) {
            notificationSwipeHelper.snapChildIfNeeded(view, z, f);
        }
    }

    public final void startScrollNotificationList() {
        this.isScrolling = true;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "isScrolling: true", null, 8);
        NotificationShadeDepthControllerEx notificationShadeDepthControllerEx = (NotificationShadeDepthControllerEx) this.notificationShadeDepthControllerEx.get();
        if (notificationShadeDepthControllerEx != null) {
            notificationShadeDepthControllerEx.restrainNavigationBarInvertColor(2, true);
        }
        JankUtils.gfxSceneBegin$default(this.common.getApplicationContext(), 4, "notification scroll", 260L);
        Handler handler = this.mainHandler;
        Runnable runnable = this.resetScrollState;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000L);
        this.oplusGcSupressionExt.callGcDesupression(3);
        this.oplusGcSupressionExt.callGcSupression(3, 800);
    }

    public void startSeedingViewIntent(Intent intent, int i, LaunchableView launchableView) {
        OplusStatusBarNotificationActivityStarterEx activityStarterEx;
        Object obj = this.notificationActivityStarterLazy.get();
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = obj instanceof StatusBarNotificationActivityStarter ? (StatusBarNotificationActivityStarter) obj : null;
        if (statusBarNotificationActivityStarter == null || (activityStarterEx = statusBarNotificationActivityStarter.getActivityStarterEx()) == null) {
            return;
        }
        activityStarterEx.startLaunchableViewIntent(intent, i, launchableView);
    }

    public void syncVisibilityForKeyguardQsCoverAlpha(NotificationStackScrollLayout notificationStackScrollLayout, float f, float f2, int i) {
        if (f == f2 && !SeparateModeHelper.INSTANCE.getSeparateMode() && i == 1) {
            float qsExpansionFraction = notificationStackScrollLayout.getQsExpansionFraction();
            if (qsExpansionFraction > 0.0f) {
                applyKeyguardQsCoverVisibility(notificationStackScrollLayout, f, qsExpansionFraction, f <= 0.0f ? 4 : 0);
            } else {
                if (!this.hiddenByKeyguardQsCover || f <= 0.0f) {
                    return;
                }
                applyKeyguardQsCoverVisibility(notificationStackScrollLayout, f, qsExpansionFraction, 0);
            }
        }
    }

    public final void updateHasSeedingOrMediaContainerValue() {
        this.notificationSeedlingInteractor.updateHasSeedingOrMediaContainerValue(((OplusNotificationSectionsManagerExImpl) this.notificationSectionsManagerEx.get()).hasNotificationSeedingContainer() || ((OplusNotificationSectionsManagerExImpl) this.notificationSectionsManagerEx.get()).hasMediaNotification());
    }

    public final void updateNotificationViewScale(boolean z) {
        NotificationPanelViewControllerEx notificationPanelViewControllerEx = (NotificationPanelViewControllerEx) this.notificationPanelViewControllerEx.get();
        kotlin.Pair targetScaleFadePivot = notificationPanelViewControllerEx.getTargetScaleFadePivot();
        float fFloatValue = ((Number) targetScaleFadePivot.component1()).floatValue();
        float fFloatValue2 = ((Number) targetScaleFadePivot.component2()).floatValue();
        float fMin = this.viewScaleFromPanelAnim;
        if (fMin <= 1.0f) {
            fMin = MathUtils.min(this.viewScaleFromPanel, fMin);
        }
        NotificationStackScrollLayout notificationStackScrollLayout = this.notifScroller;
        if (notificationStackScrollLayout != null) {
            if (!((NotificationStackScrollLayoutExtImpl) notificationStackScrollLayout.mExt).isViewScaleAnimationRunning()) {
                if (z) {
                    NotificationPanelViewController base = notificationPanelViewControllerEx.getBase();
                    if (notificationPanelViewControllerEx.canScaleFadePanelAtExpandFraction(base != null ? base.getExpandedFraction() : 1.0f)) {
                        notificationStackScrollLayout.getLocationInWindow(this.tmpLocations);
                        notificationStackScrollLayout.setPivotX(fFloatValue - this.tmpLocations[0]);
                        notificationStackScrollLayout.setPivotY(fFloatValue2 - this.tmpLocations[1]);
                    } else {
                        notificationStackScrollLayout.resetPivot();
                    }
                }
                notificationStackScrollLayout.setScaleX(fMin);
                notificationStackScrollLayout.setScaleY(fMin);
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationStackScrollLayoutControllerExtImpl-->Scale animation blocked - view scale animation is running");
            }
        }
        ((OplusNotificationCapsuleInteractorImpl) ((OplusNotificationCapsuleInteractor) this.notificationCapsuleInteractor.get())).updateScaleWithPivot(fMin, fFloatValue, fFloatValue2);
    }

    public final void updateNsslAlphaAreaHeight(int i) {
        if (this.tiltShiftBlurContainerHeight == i) {
            return;
        }
        this.tiltShiftBlurContainerHeight = i;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updateNsslAlphaAreaHeight: "), null, 8);
        if (this.statusBarState != 1) {
            applyTopFade(this.notifScroller, "nsslAlphaHeightChanged", this.tiltShiftBlurContainerHeight);
        }
    }

    public final void updateSplitShadeNotificationActions() {
        OplusNotificationTemplateViewWrapperEx oplusNotificationTemplateViewWrapperEx;
        NotificationContentView privateLayout;
        Iterator it = this.notifPipeline.getAllNotifs().iterator();
        while (it.hasNext()) {
            ExpandableNotificationRow row = ((NotificationEntry) it.next()).getRow();
            NotificationViewWrapper visibleWrapper = (row == null || (privateLayout = row.getPrivateLayout()) == null) ? null : privateLayout.getVisibleWrapper(1);
            if (visibleWrapper != null && (visibleWrapper instanceof NotificationTemplateViewWrapper) && (oplusNotificationTemplateViewWrapperEx = ((NotificationTemplateViewWrapper) visibleWrapper).mTemplateViewWrapperEx) != null) {
                ((OplusNotificationTemplateViewWrapperExImpl) oplusNotificationTemplateViewWrapperEx).resolveNotificationActionSize();
            }
        }
    }

    public final void updateViewScaleFromPanel(float f) {
        this.viewScaleFromPanel = f;
        this._viewScaleFromPanelFlow.setValue(Float.valueOf(f));
        updateNotificationViewScale(true);
    }
}
