package com.oplus.systemui.notification.row.overlay;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.util.MathUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.compose.animation.core.MonoSpline$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.util.ReferenceExtKt;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.media.controls.ui.OplusMediaCarouselView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimSinkingParams;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.Regex$;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationOverlayControllerImpl implements OplusNotificationOverlayController {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final String TAG = "OplusNotificationOverlayController";
    private final ReadWriteProperty authorView$delegate;
    private final CapsuleCardControl capsuleCardControl;
    public volatile boolean enableOverlay;
    private final COUISpringAnimation fadeSpringAnimation;
    private final Runnable hideOverlayAction;
    private final Runnable hideOverlayActionNoAnimation;
    private final NotificationInfoTextView infoView;
    private CharSequence lastInfo;
    private final Lazy mainHandler$delegate;
    private final ReadWriteProperty mediaCarouselView$delegate;
    private Function0 onFadeOut;
    public final View oplusNotificationOverlay;
    private final NotificationOverlayBackgroundView overlayBackgroundView;
    private final Function0 overlayBackgroundViewInvalidate;
    private final Runnable showOverlayAction;
    private final Runnable showOverlayActionNoAnimation;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayControllerImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayControllerImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00361 implements FlowCollector {
            public /* synthetic */ OplusNotificationOverlayControllerImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                ((Number) obj).intValue();
                OplusNotificationOverlayControllerImpl.access$updateInfoViewPosition(this.this$0);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return OplusNotificationOverlayControllerImpl.this.new AnonymousClass1((Continuation) obj3).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            OplusExpandableViewEx oplusExpandableViewEx;
            StateFlow actualHeight;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ExpandableView authorView = OplusNotificationOverlayControllerImpl.this.getAuthorView();
                if (authorView == null || (oplusExpandableViewEx = authorView.mExpandableViewEx) == null || (actualHeight = oplusExpandableViewEx.getActualHeight()) == null) {
                    return Unit.INSTANCE;
                }
                OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl = OplusNotificationOverlayControllerImpl.this;
                C00361 c00361 = new C00361();
                c00361.this$0 = oplusNotificationOverlayControllerImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (actualHeight.collect(c00361, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayControllerImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnLayoutChangeListener {
        public /* synthetic */ OplusNotificationOverlayControllerImpl this$0;

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            OplusNotificationOverlayControllerImpl.access$updateInfoViewPosition(this.this$0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$G_AXcBhGAgM6h6ADVy6-JCm1tto, reason: not valid java name */
    public static Unit m98$r8$lambda$G_AXcBhGAgM6h6ADVy6JCm1tto(OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl) {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = oplusNotificationOverlayControllerImpl.overlayBackgroundView;
        if (notificationOverlayBackgroundView != null) {
            notificationOverlayBackgroundView.invalidate();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$wtqZpzIzCU83G2g9REeXn-5EX5o, reason: not valid java name */
    public static Unit m99$r8$lambda$wtqZpzIzCU83G2g9REeXn5EX5o(OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl) {
        oplusNotificationOverlayControllerImpl.bindBgViewWithNotificationBg(false);
        OplusCustomRow authorView = oplusNotificationOverlayControllerImpl.getAuthorView();
        OplusCustomRow oplusCustomRow = authorView instanceof OplusCustomRow ? authorView : null;
        if (oplusCustomRow != null) {
            oplusCustomRow.setMOverlayBackgroundView(null);
        }
        oplusNotificationOverlayControllerImpl.setAuthorView(null);
        oplusNotificationOverlayControllerImpl.setMediaCarouselView(null);
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = oplusNotificationOverlayControllerImpl.overlayBackgroundView;
        if (notificationOverlayBackgroundView != null) {
            notificationOverlayBackgroundView.setAodDrawable(null);
        }
        ViewParent parent = oplusNotificationOverlayControllerImpl.getOplusNotificationOverlay().getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(oplusNotificationOverlayControllerImpl.getOplusNotificationOverlay());
        }
        oplusNotificationOverlayControllerImpl.onFadeOut = new OplusNotificationOverlayControllerImpl$.ExternalSyntheticLambda3(3);
        return Unit.INSTANCE;
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(OplusNotificationOverlayControllerImpl.class, "authorView", "getAuthorView()Lcom/android/systemui/statusbar/notification/row/ExpandableView;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(OplusNotificationOverlayControllerImpl.class, "mediaCarouselView", "getMediaCarouselView()Lcom/oplus/systemui/media/controls/ui/OplusMediaCarouselView;", 0)};
        Companion = new Companion();
    }

    /* JADX WARN: Type inference failed for: r8v5, types: [android.widget.TextView, com.oplus.systemui.notification.row.overlay.NotificationInfoTextView] */
    public OplusNotificationOverlayControllerImpl(Context context, CapsuleCardControl capsuleCardControl) {
        this.capsuleCardControl = capsuleCardControl;
        this.oplusNotificationOverlay = LayoutInflater.from(context).inflate(2131559315, (ViewGroup) null);
        ?? r8 = (NotificationInfoTextView) getOplusNotificationOverlay().findViewById(2131364390);
        this.infoView = r8;
        int i = 1;
        OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0(i);
        oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.overlayBackgroundViewInvalidate = oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0;
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) getOplusNotificationOverlay().findViewById(2131364389);
        int i2 = 3;
        int i3 = 2;
        if (notificationOverlayBackgroundView != null) {
            OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0(i3);
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            notificationOverlayBackgroundView.setAuthorViewBlurProxyGetter(oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1);
            OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda2 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0(i2);
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            notificationOverlayBackgroundView.setMaskColorGetter(oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda2);
            OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8();
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8.f$0 = this;
            VarHandle.storeStoreFence();
            notificationOverlayBackgroundView.setDrawBoundsGetter(oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8);
            View.OnClickListener onClickListener = new initClickListener.1();
            ((initClickListener.1) onClickListener).$view = notificationOverlayBackgroundView;
            VarHandle.storeStoreFence();
            notificationOverlayBackgroundView.setOnClickListener(onClickListener);
        } else {
            notificationOverlayBackgroundView = null;
        }
        this.overlayBackgroundView = notificationOverlayBackgroundView;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.3f));
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(0.001f);
        OplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$1 oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$1 = new OplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$1();
        oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$1);
        OplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$2 oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$2 = new OplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$2();
        oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(oplusNotificationOverlayControllerImpl$fadeSpringAnimation$1$2);
        this.fadeSpringAnimation = cOUISpringAnimation;
        OplusNotificationOverlayControllerImpl$hideOverlayAction$1 oplusNotificationOverlayControllerImpl$hideOverlayAction$1 = new OplusNotificationOverlayControllerImpl$hideOverlayAction$1(i3);
        oplusNotificationOverlayControllerImpl$hideOverlayAction$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.showOverlayAction = oplusNotificationOverlayControllerImpl$hideOverlayAction$1;
        OplusNotificationOverlayControllerImpl$hideOverlayAction$1 oplusNotificationOverlayControllerImpl$hideOverlayAction$2 = new OplusNotificationOverlayControllerImpl$hideOverlayAction$1(i2);
        oplusNotificationOverlayControllerImpl$hideOverlayAction$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.showOverlayActionNoAnimation = oplusNotificationOverlayControllerImpl$hideOverlayAction$2;
        OplusNotificationOverlayControllerImpl$hideOverlayAction$1 oplusNotificationOverlayControllerImpl$hideOverlayAction$3 = new OplusNotificationOverlayControllerImpl$hideOverlayAction$1(0);
        oplusNotificationOverlayControllerImpl$hideOverlayAction$3.this$0 = this;
        VarHandle.storeStoreFence();
        this.hideOverlayAction = oplusNotificationOverlayControllerImpl$hideOverlayAction$3;
        OplusNotificationOverlayControllerImpl$hideOverlayAction$1 oplusNotificationOverlayControllerImpl$hideOverlayAction$4 = new OplusNotificationOverlayControllerImpl$hideOverlayAction$1(i);
        oplusNotificationOverlayControllerImpl$hideOverlayAction$4.this$0 = this;
        VarHandle.storeStoreFence();
        this.hideOverlayActionNoAnimation = oplusNotificationOverlayControllerImpl$hideOverlayAction$4;
        this.authorView$delegate = ReferenceExtKt.weakReference((Object) null);
        this.mediaCarouselView$delegate = ReferenceExtKt.weakReference((Object) null);
        this.mainHandler$delegate = LazyKt__LazyJVMKt.lazy(new OplusNotificationOverlayControllerImpl$.ExternalSyntheticLambda3(0));
        this.onFadeOut = new OplusNotificationOverlayControllerImpl$.ExternalSyntheticLambda3(1);
        RepeatWhenAttachedKt.repeatWhenAttached$default(getOplusNotificationOverlay(), Dispatchers.getMain().getImmediate(), new AnonymousClass1(null), 2);
        if (r8 != 0) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            r8.addOnLayoutChangeListener(anonymousClass2);
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
    public static final void access$updateInfoViewPosition(OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl) {
        ExpandableView authorView;
        AppCompatTextView appCompatTextView = oplusNotificationOverlayControllerImpl.infoView;
        if (appCompatTextView == null || (authorView = oplusNotificationOverlayControllerImpl.getAuthorView()) == null) {
            return;
        }
        appCompatTextView.setTranslationY(authorView.getIntrinsicHeight() - appCompatTextView.getMeasuredHeight());
    }

    private final Handler getMainHandler() {
        return (Handler) this.mainHandler$delegate.getValue();
    }

    private final OplusMediaCarouselView getMediaCarouselView() {
        return (OplusMediaCarouselView) this.mediaCarouselView$delegate.getValue(this, $$delegatedProperties[1]);
    }

    private void setAuthorView(ExpandableView expandableView) {
        this.authorView$delegate.setValue(this, $$delegatedProperties[0], expandableView);
    }

    private final void setMediaCarouselView(OplusMediaCarouselView oplusMediaCarouselView) {
        this.mediaCarouselView$delegate.setValue(this, $$delegatedProperties[1], oplusMediaCarouselView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOverlayAlpha(float f) {
        View oplusNotificationOverlay = getOplusNotificationOverlay();
        if (oplusNotificationOverlay != null) {
            oplusNotificationOverlay.setAlpha(f);
        }
        View oplusNotificationOverlay2 = getOplusNotificationOverlay();
        if (oplusNotificationOverlay2 != null) {
            oplusNotificationOverlay2.setVisibility((f > 0.0f ? 1 : (f == 0.0f ? 0 : -1)) == 0 ? 4 : 0);
        }
        AppCompatTextView appCompatTextView = this.infoView;
        if (appCompatTextView != null) {
            ExtStateAnimSinkingParams.SinkingToStackParams sinkingToStackParams = ExtStateAnimSinkingParams.SinkingToStackParams.INSTANCE;
            float fFloatValue = sinkingToStackParams.getStaticStackedMinScale().get().floatValue();
            if (fFloatValue < 0.0f) {
                fFloatValue = 0.0f;
            }
            float fFloatValue2 = sinkingToStackParams.getStaticStackedMaxScale().get().floatValue();
            if (fFloatValue2 > 1.0f) {
                fFloatValue2 = 1.0f;
            }
            float fLerp = MathUtils.lerp(fFloatValue, fFloatValue2, RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f));
            if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("applyOverlayFadeProgressScale:minScale:", fFloatValue, ", maxScale:", fFloatValue2, ", original="), f, ", realScale=", fLerp, "OplusNotificationOverlayController-->"));
            }
            ViewExpandKtKt.setPivotXY(appCompatTextView, appCompatTextView.getWidth() / 2.0f, appCompatTextView.getHeight() / 2.0f);
            ViewExpandKtKt.setScaleXY(appCompatTextView, fLerp, fLerp);
        }
        if (f == 0.0f) {
            this.onFadeOut.invoke();
        }
    }

    @Override // com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayController
    public void bindAuthorView(ExpandableView expandableView) {
        this.enableOverlay = (expandableView instanceof ExpandableNotificationRow) || (expandableView instanceof OplusCustomRow) || (expandableView instanceof MediaContainerView);
        if (!this.enableOverlay) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "bindAuthorView(" + hashCode() + "): disable Overlay for " + expandableView, null, 8);
            return;
        }
        setAuthorView(expandableView);
        OplusCustomRow authorView = getAuthorView();
        OplusCustomRow oplusCustomRow = authorView instanceof OplusCustomRow ? authorView : null;
        if (oplusCustomRow != null) {
            oplusCustomRow.setMOverlayBackgroundView(this.overlayBackgroundView);
        }
        this.onFadeOut = new OplusNotificationOverlayControllerImpl$.ExternalSyntheticLambda3(2);
        ViewParent parent = getOplusNotificationOverlay().getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual(viewGroup, expandableView)) {
            if (viewGroup != null) {
                viewGroup.removeView(getOplusNotificationOverlay());
            }
            if (expandableView != null) {
                expandableView.addView(getOplusNotificationOverlay());
            }
        }
        setMediaCarouselView(tryGetMediaCarouselView());
        bindBgViewWithNotificationBg(true);
    }

    public final void bindBgViewWithNotificationBg(boolean z) {
        NotificationBackgroundView notificationBackgroundView;
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = this.overlayBackgroundView;
        if (notificationOverlayBackgroundView != null) {
            notificationOverlayBackgroundView.setEnableBlur(z);
        }
        NotificationOverlayBackgroundView notificationOverlayBackgroundView2 = this.overlayBackgroundView;
        ExpandableView authorView = getAuthorView();
        if (authorView != null) {
            OplusExpandableViewEx oplusExpandableViewEx = authorView.mExpandableViewEx;
            Function0 function0 = null;
            NotificationBackgroundViewExt ext = (oplusExpandableViewEx == null || (notificationBackgroundView = oplusExpandableViewEx.getNotificationBackgroundView()) == null) ? null : notificationBackgroundView.getExt();
            if (ext != null) {
                if (z && notificationOverlayBackgroundView2 != null) {
                    function0 = this.overlayBackgroundViewInvalidate;
                }
                ext.setOnDrawListener(function0);
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayController
    public ExpandableView getAuthorView() {
        return (ExpandableView) this.authorView$delegate.getValue(this, $$delegatedProperties[0]);
    }

    @Override // com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayController
    public View getOplusNotificationOverlay() {
        return this.oplusNotificationOverlay;
    }

    @Override // com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayController
    public void setNotificationOverlayInfo(CharSequence charSequence, long j, boolean z) {
        Runnable runnable;
        if (this.enableOverlay && !Intrinsics.areEqual(this.lastInfo, charSequence)) {
            this.lastInfo = charSequence;
            NotificationInfoTextView notificationInfoTextView = this.infoView;
            if (notificationInfoTextView != null) {
                notificationInfoTextView.setTextWithAnimation(charSequence);
            }
            boolean z2 = charSequence != null;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationOverlayController-->".concat("updateWithAnimation(" + hashCode() + "): info=" + ((Object) this.lastInfo) + ", shouldShow=" + z2 + ", delay=" + j + ", animate=" + z));
            }
            CapsuleConfig.INSTANCE.getClass();
            if (CapsuleConfig.isLightOs && this.capsuleCardControl.isLockScreenExtStateAnimRunning()) {
                runnable = z2 ? this.showOverlayActionNoAnimation : this.hideOverlayActionNoAnimation;
            } else if (z2 && z) {
                runnable = this.showOverlayAction;
            } else if (!z2 || z) {
                runnable = (z2 || !z) ? this.hideOverlayActionNoAnimation : this.hideOverlayAction;
            } else {
                runnable = this.showOverlayActionNoAnimation;
            }
            getMainHandler().removeCallbacks(this.showOverlayAction);
            getMainHandler().removeCallbacks(this.showOverlayActionNoAnimation);
            getMainHandler().removeCallbacks(this.hideOverlayAction);
            getMainHandler().removeCallbacks(this.hideOverlayActionNoAnimation);
            if (j == 0) {
                runnable.run();
            } else {
                getMainHandler().postDelayed(runnable, j);
            }
            NotificationOverlayBackgroundView notificationOverlayBackgroundView = this.overlayBackgroundView;
            if (notificationOverlayBackgroundView != null) {
                notificationOverlayBackgroundView.setContentDescription(charSequence);
            }
        }
    }

    public String toString() {
        return super.toString() + ", authorView=" + getAuthorView() + ", oplusNotificationOverlay=" + getOplusNotificationOverlay() + ", infoView=" + this.infoView + ", overlayBackgroundView=" + this.overlayBackgroundView + ", info=" + ((Object) this.lastInfo) + ". ";
    }

    public final OplusMediaCarouselView tryGetMediaCarouselView() {
        OplusMediaCarouselView oplusMediaCarouselView;
        OplusMediaCarouselView mediaCarouselView = getMediaCarouselView();
        if (mediaCarouselView != null) {
            return mediaCarouselView;
        }
        ExpandableView authorView = getAuthorView();
        if (authorView == null || (oplusMediaCarouselView = (OplusMediaCarouselView) authorView.findViewById(2131363895)) == null) {
            return null;
        }
        setMediaCarouselView(oplusMediaCarouselView);
        return oplusMediaCarouselView;
    }

    @Override // com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayController
    public void unbindAuthorView(long j, boolean z, Function0 function0) {
        if (!this.enableOverlay) {
            function0.invoke();
            return;
        }
        OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0(0);
        oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        if (getOplusNotificationOverlay().getVisibility() != 0) {
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0.invoke();
            function0.invoke();
            setNotificationOverlayInfo(null, 0L, false);
        } else {
            OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1 oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1 = new OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1();
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1.f$0 = oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0;
            oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1.f$1 = function0;
            VarHandle.storeStoreFence();
            this.onFadeOut = oplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda1;
            setNotificationOverlayInfo(null, j, z);
        }
    }

    public final void updateResource() {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = this.overlayBackgroundView;
        if (notificationOverlayBackgroundView != null) {
            notificationOverlayBackgroundView.updateResource();
        }
        NotificationInfoTextView notificationInfoTextView = this.infoView;
        if (notificationInfoTextView != null) {
            notificationInfoTextView.updateResource$1();
        }
    }
}
