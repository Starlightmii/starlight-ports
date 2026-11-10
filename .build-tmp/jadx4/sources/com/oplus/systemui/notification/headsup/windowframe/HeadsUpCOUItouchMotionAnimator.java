package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerToAodTransitionViewModel$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.coui.appcompat.animation.touchanimation.TouchMotionParamConfig;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.headsup.zoom.HeadsUpZoomParamsKt;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpCOUItouchMotionAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpCOUIMotion";
    public float baseTranslationY;
    private final View.OnLayoutChangeListener childSizeChangeListener;
    private final Context context;
    public boolean couiFeeding;
    private final CouiMotionForwarder couiMotionForwarder;
    public float currentY;
    private HeadsUpDragYCallback dragYCallback;
    public boolean ignoredTouchMotionCallbackEnabledInternal;
    public boolean isSupportZoom;
    public final int maxPullHeight;
    public float maxdistance;
    private final Function2 onComposedYApplied;
    private final Function0 onTransformApplied;
    public float startY;
    private final Lazy touchMotionAnimator$delegate;
    public float touchMotionTranslationY;
    private View trackedSizeView;
    private final Function0 viewProvider;
    public float touchMotionScaleX = 1.0f;
    public float touchMotionScaleY = 1.0f;
    private final COUITouchMotionAnimator.AnimationParamsType animationParamsType = COUITouchMotionAnimator.AnimationParamsType.MAX;

    public static COUITouchMotionAnimator $r8$lambda$Q_Et6CoNriXWW12MLnJbAVQrRTk(HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator) {
        COUITouchMotionAnimator cOUITouchMotionAnimator = new COUITouchMotionAnimator(headsUpCOUItouchMotionAnimator.context);
        try {
            cOUITouchMotionAnimator.config(new TouchMotionParamConfig().setAnimationParamsType(headsUpCOUItouchMotionAnimator.animationParamsType).setMovementStrength(2.5f).setMaxDragDistance(400.0f).setRubberBandMaxDistance(900.0f));
        } catch (IllegalArgumentException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "configTouchMotionParam: failed ", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        } catch (IllegalStateException e2) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "configTouchMotionParam: failed ", e2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
        cOUITouchMotionAnimator.setDeformationEnable(true).setRubberEnable(true).setPressScaleEnable(false);
        headsUpCOUItouchMotionAnimator.applyCurrentViewTransform();
        HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1 headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1 = new HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1();
        headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1.this$0 = headsUpCOUItouchMotionAnimator;
        VarHandle.storeStoreFence();
        cOUITouchMotionAnimator.addOnAnimationUpdateCallback(headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1);
        HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2 headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2 = new HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2();
        headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2.this$0 = headsUpCOUItouchMotionAnimator;
        VarHandle.storeStoreFence();
        ((COUIComposeMotionAnimator) cOUITouchMotionAnimator).mAnimationEndListener = headsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2;
        return cOUITouchMotionAnimator;
    }

    public HeadsUpCOUItouchMotionAnimator(Context context, Function0 function0, Function0 function1, Function2 function2) {
        this.context = context;
        this.viewProvider = function0;
        this.onTransformApplied = function1;
        this.onComposedYApplied = function2;
        this.maxPullHeight = context.getResources().getDimensionPixelSize(2131170671);
        this.maxdistance = DeviceUtil.dp2px(context, 45.0f);
        HeadsUpCOUItouchMotionAnimator$childSizeChangeListener$1 headsUpCOUItouchMotionAnimator$childSizeChangeListener$1 = new HeadsUpCOUItouchMotionAnimator$childSizeChangeListener$1();
        headsUpCOUItouchMotionAnimator$childSizeChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childSizeChangeListener = headsUpCOUItouchMotionAnimator$childSizeChangeListener$1;
        HeadsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda0 headsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda0 = new HeadsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda0();
        headsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.touchMotionAnimator$delegate = LazyKt__LazyJVMKt.lazy(headsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda0);
        HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1 headsUpCOUItouchMotionAnimator$couiMotionForwarder$1 = new HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1();
        headsUpCOUItouchMotionAnimator$couiMotionForwarder$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.couiMotionForwarder = headsUpCOUItouchMotionAnimator$couiMotionForwarder$1;
    }

    public static final void access$couiDispatchEndEvent(HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator, MotionEvent motionEvent) {
        headsUpCOUItouchMotionAnimator.getTouchMotionAnimator().handleTouchEvent(motionEvent);
        headsUpCOUItouchMotionAnimator.couiFeeding = false;
    }

    public static final void access$couiDispatchStreamEvent(HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator, MotionEvent motionEvent) {
        ExpandableNotificationRow expandableNotificationRow = (View) headsUpCOUItouchMotionAnimator.viewProvider.invoke();
        if (expandableNotificationRow == null) {
            return;
        }
        if (!headsUpCOUItouchMotionAnimator.couiFeeding) {
            headsUpCOUItouchMotionAnimator.updateTouchMotionAnimatorSize(expandableNotificationRow);
            if (motionEvent.getActionMasked() == 2) {
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                motionEventObtain.setAction(0);
                try {
                    headsUpCOUItouchMotionAnimator.getTouchMotionAnimator().handleTouchEvent(motionEventObtain);
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
                    headsUpCOUItouchMotionAnimator.isSupportZoom = expandableNotificationRow2 != null ? HeadsUpZoomParamsKt.isSupportZoom(expandableNotificationRow2) : false;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "HeadsUpCOUIMotion-->".concat("couiDispatchStream: synth DOWN x=" + motionEventObtain.getX() + " y=" + motionEventObtain.getY()));
                    }
                    headsUpCOUItouchMotionAnimator.startY = motionEventObtain.getY();
                    motionEventObtain.recycle();
                } catch (Throwable th) {
                    motionEventObtain.recycle();
                    throw th;
                }
            }
            headsUpCOUItouchMotionAnimator.couiFeeding = true;
        }
        headsUpCOUItouchMotionAnimator.currentY = motionEvent.getY();
        headsUpCOUItouchMotionAnimator.getTouchMotionAnimator().handleTouchEvent(motionEvent);
    }

    public static /* synthetic */ float composedTranslationY$default(HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator, Float f, int i) {
        if ((i & 1) != 0) {
            f = null;
        }
        return headsUpCOUItouchMotionAnimator.composedTranslationY(f, null);
    }

    public static final HeadsUpCOUItouchMotionAnimator create(Context context, Function0 function0, Function0 function1) {
        return Companion.create(context, function0, function1);
    }

    private final COUITouchMotionAnimator getTouchMotionAnimator() {
        return (COUITouchMotionAnimator) this.touchMotionAnimator$delegate.getValue();
    }

    public final void applyCurrentViewTransform() {
        View view = (View) this.viewProvider.invoke();
        if (view != null) {
            float fComposedTranslationY$default = composedTranslationY$default(this, null, 3);
            view.setTranslationY(fComposedTranslationY$default);
            view.setScaleX(this.touchMotionScaleX);
            view.setScaleY(this.touchMotionScaleY);
            boolean z = this.isSupportZoom;
            if (z && this.touchMotionTranslationY > this.maxPullHeight && !this.ignoredTouchMotionCallbackEnabledInternal) {
                dispatchDragY(0.0f, new AlternateBouncerToAodTransitionViewModel$.ExternalSyntheticLambda1());
            } else if (z && this.currentY - this.startY > this.maxdistance && !this.ignoredTouchMotionCallbackEnabledInternal) {
                dispatchDragY(0.0f, new HeadsUpCOUItouchMotionAnimator$$ExternalSyntheticLambda1());
            }
            this.onComposedYApplied.invoke(view, Float.valueOf(fComposedTranslationY$default));
        }
        this.onTransformApplied.invoke();
    }

    public final void attachView(View view) {
        View view2 = this.trackedSizeView;
        if (view2 != view) {
            if (view2 != null) {
                view2.removeOnLayoutChangeListener(this.childSizeChangeListener);
            }
            this.trackedSizeView = view;
            if (view != null) {
                view.addOnLayoutChangeListener(this.childSizeChangeListener);
            }
        }
        forceCancelAndReset("attachView");
    }

    public final float composedTranslationY(Float f, Float f2) {
        return (f != null ? f.floatValue() : this.baseTranslationY) + (f2 != null ? f2.floatValue() : this.touchMotionTranslationY);
    }

    public final void detachView(View view) {
        View view2 = this.trackedSizeView;
        if (view2 != null) {
            if (view2 != null) {
                view2.removeOnLayoutChangeListener(this.childSizeChangeListener);
            }
            this.trackedSizeView = null;
        }
        if (view != null) {
            view.setTranslationX(0.0f);
            view.setTranslationY(this.baseTranslationY);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
        forceCancelAndReset("detachView");
    }

    public final void dispatchDragY(float f, Function0 function0) {
        if (((View) this.viewProvider.invoke()) == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpCOUIMotion-->dispatchDragY: skip, view is null");
                return;
            }
            return;
        }
        HeadsUpDragYCallback headsUpDragYCallback = this.dragYCallback;
        if (headsUpDragYCallback != null) {
            ((OplusHeadsUpTouchHelper$syncDragYCallback$1$1) headsUpDragYCallback).onY(f, function0);
        }
    }

    public final void forceCancelAndReset(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean z = this.couiFeeding;
            float f = this.touchMotionTranslationY;
            float f2 = this.touchMotionScaleX;
            float f3 = this.touchMotionScaleY;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("forceCancelAndReset: reason=", str, " feeding=", z, " tx=0.0 ty=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, " sx=", f2, " sy=");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f3, "HeadsUpCOUIMotion-->", "SystemUi--Notification");
        }
        getTouchMotionAnimator().cancel();
        getTouchMotionAnimator().reset(false);
        this.couiFeeding = false;
        this.isSupportZoom = false;
        this.touchMotionTranslationY = 0.0f;
        this.touchMotionScaleX = 1.0f;
        this.touchMotionScaleY = 1.0f;
        applyCurrentViewTransform();
    }

    public final float getBaseTranslationY() {
        return this.baseTranslationY;
    }

    public final CouiMotionForwarder getCouiMotionForwarder() {
        return this.couiMotionForwarder;
    }

    public final float getRealTranslationY() {
        return composedTranslationY$default(this, null, 3);
    }

    public final void onConfigChanged() {
        View view = (View) this.viewProvider.invoke();
        if (view != null) {
            updateTouchMotionAnimatorSize(view);
        }
        this.maxdistance = DeviceUtil.dp2px(this.context, 45.0f);
    }

    public final void setBaseTranslationY(float f) {
        if (this.baseTranslationY == f) {
            return;
        }
        this.baseTranslationY = f;
        applyCurrentViewTransform();
    }

    public final void updateTouchMotionAnimatorSize(View view) {
        int width = view.getWidth();
        int actualHeight = view instanceof ExpandableNotificationRow ? ((ExpandableNotificationRow) view).getActualHeight() : view.getHeight();
        if (width > 0 && actualHeight > 0) {
            getTouchMotionAnimator().onSizeChange(width, actualHeight);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpCOUIMotion-->skip update");
        }
    }

    public static final HeadsUpCOUItouchMotionAnimator create(Context context, Function0 function0, Function0 function1, Function2 function2) {
        return Companion.create(context, function0, function1, function2);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final HeadsUpCOUItouchMotionAnimator create(Context context, Function0 function0, Function0 function1, Function2 function2) {
            if (FeatureOption.getEnableLightFeature()) {
                return new HeadsUpCOUItouchMotionAnimator(context, function0, function1, function2);
            }
            return null;
        }

        public final HeadsUpCOUItouchMotionAnimator create(Context context, Function0 function0, Function0 function1) {
            return create(context, function0, function1, new HeadsUpCOUItouchMotionAnimator$Companion$.ExternalSyntheticLambda0());
        }
    }
}
