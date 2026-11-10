package com.oplus.systemui.notification.lockscreen.capsule;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.proxy.AodSettingsValueProxy;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator;
import com.oplus.systemui.notification.lockscreen.capsule.stroke.StrokeLayer;
import com.oplus.systemui.notification.lockscreen.capsule.stroke.StrokeView;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StokeViewControl implements IStrokeAnimator, View.OnAttachStateChangeListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StokeViewControl";
    private ValueAnimator animator;
    public boolean animatorHasDone;
    private final Lazy bindDataInterceptor$delegate;
    private final Context context;
    public float progress;
    public float startAngle;
    private StrokeView strokeView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StokeViewControl(View view) {
        Context context = view.getContext();
        this.context = context;
        this.bindDataInterceptor$delegate = LazyKt__LazyJVMKt.lazy(new StokeViewControl$$ExternalSyntheticLambda0());
        this.startAngle = 0.25f;
        if (ScrimUtil.isMiddleOrHighAnimLevel(context)) {
            StrokeView strokeView = (StrokeView) view.findViewById(2131362468);
            this.strokeView = strokeView;
            if (strokeView != null) {
                strokeView.addOnAttachStateChangeListener(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CapsuleDataChangeInterceptor getBindDataInterceptor() {
        return (CapsuleDataChangeInterceptor) this.bindDataInterceptor$delegate.getValue();
    }

    public final void cancelStrokeAnimation() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("cancelStrokeAnimation animatorHasDone=", "StokeViewControl-->", "SystemUi--Notification", this.animatorHasDone);
        }
        this.animatorHasDone = true;
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        getBindDataInterceptor().unIntercept();
        StrokeView strokeView = this.strokeView;
        if (strokeView != null) {
            strokeView.clearAllLayer();
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public float getProgress() {
        return this.progress;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public float getStartAngle() {
        return this.startAngle;
    }

    public final void initPathProvider(AutoRoundRectPathProvider autoRoundRectPathProvider) {
        StrokeView strokeView = this.strokeView;
        if (strokeView != null) {
            strokeView.initPathProvider(autoRoundRectPathProvider);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        getBindDataInterceptor().register();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        getBindDataInterceptor().unregister();
        cancelStrokeAnimation();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setProgress(float f) {
        List<StrokeLayer> allLayers;
        this.progress = f;
        StrokeView strokeView = this.strokeView;
        if (strokeView == null || (allLayers = strokeView.getAllLayers()) == null) {
            return;
        }
        Iterator<T> it = allLayers.iterator();
        while (it.hasNext()) {
            ((StrokeLayer) it.next()).setAnimationProgress(f);
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setStartAngle(float f) {
        List<StrokeLayer> allLayers;
        this.startAngle = f;
        StrokeView strokeView = this.strokeView;
        if (strokeView == null || (allLayers = strokeView.getAllLayers()) == null) {
            return;
        }
        Iterator<T> it = allLayers.iterator();
        while (it.hasNext()) {
            ((StrokeLayer) it.next()).setStartAngle(f);
        }
    }

    public final void startStrokeAnimation(float f) {
        StrokeLayer strokeLayerAddLayer;
        StrokeLayer strokeLayerAddLayer2;
        boolean zIsMiddleOrHighAnimLevel = false;
        if (this.strokeView != null) {
            NotificationEffectRepository.Companion.getClass();
            if (NotificationEffectRepository.isSupportBlurStroke && (AodSettingsValueProxy.getAodSwitchEnable(this.context) != 1 || AodSettingsValueProxy.getAodType(this.context) != 2 || SmoothTransitionController.Companion.getInstance(this.context).getUiState() != SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD)) {
                ScrimControllerEx.Companion.getClass();
                if (!ScrimControllerEx.isThirdTheme && !GaussBlurUtils.isGaussBlurDisabled()) {
                    zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(this.context);
                }
            }
        }
        if (zIsMiddleOrHighAnimLevel) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("startStrokeAnimation animatorHasDone=", "StokeViewControl-->", "SystemUi--Notification", this.animatorHasDone);
            }
            if (this.animatorHasDone) {
                return;
            }
            this.animatorHasDone = true;
            StrokeView strokeView = this.strokeView;
            if (strokeView != null) {
                strokeView.clearAllLayer();
                StrokeView strokeView2 = this.strokeView;
                if (strokeView2 != null && (strokeLayerAddLayer2 = strokeView2.addLayer(StrokeLayer.Companion.getTHICK_ANIMATOR_FACTORY())) != null) {
                    strokeLayerAddLayer2.setWeight(1.0f);
                    strokeLayerAddLayer2.setAntiAliasing(2.0f);
                    strokeLayerAddLayer2.setNearStrokeStart(0.0f);
                    strokeLayerAddLayer2.setNearStrokeWidth(60.0f);
                    strokeLayerAddLayer2.setFarStrokeStart(0.0f);
                    strokeLayerAddLayer2.setFarStrokeWidth(60.0f);
                    strokeLayerAddLayer2.setNearAlpha(0.6f);
                    strokeLayerAddLayer2.setFarAlpha(0.7f);
                    strokeLayerAddLayer2.setNearFadeStart(0.0f);
                    strokeLayerAddLayer2.setNearFadeWidth(0.8f);
                    strokeLayerAddLayer2.setFarFadeStart(0.0f);
                    strokeLayerAddLayer2.setFarFadeWidth(0.6f);
                    strokeLayerAddLayer2.setAngle(0.25f);
                    strokeLayerAddLayer2.setStrokePower(4.33f);
                    strokeLayerAddLayer2.setStrokePowerMix(0.704f);
                    strokeLayerAddLayer2.setStrokeColor(-1);
                }
                StrokeView strokeView3 = this.strokeView;
                if (strokeView3 != null && (strokeLayerAddLayer = strokeView3.addLayer(StrokeLayer.Companion.getTHIN_ANIMATOR_FACTORY())) != null) {
                    strokeLayerAddLayer.setWeight(1.0f);
                    strokeLayerAddLayer.setAntiAliasing(2.0f);
                    strokeLayerAddLayer.setNearStrokeStart(1.0f);
                    strokeLayerAddLayer.setNearStrokeWidth(2.0f);
                    strokeLayerAddLayer.setFarStrokeStart(1.0f);
                    strokeLayerAddLayer.setFarStrokeWidth(2.0f);
                    strokeLayerAddLayer.setNearAlpha(1.0f);
                    strokeLayerAddLayer.setFarAlpha(1.0f);
                    strokeLayerAddLayer.setNearFadeStart(0.0f);
                    strokeLayerAddLayer.setNearFadeWidth(0.4f);
                    strokeLayerAddLayer.setFarFadeStart(0.0f);
                    strokeLayerAddLayer.setFarFadeWidth(0.2f);
                    strokeLayerAddLayer.setAngle(0.25f);
                    strokeLayerAddLayer.setStrokePower(9.23f);
                    strokeLayerAddLayer.setStrokePowerMix(0.678f);
                    strokeLayerAddLayer.setStrokeColor(-1);
                }
            }
            setStartAngle(f);
            getBindDataInterceptor().intercept();
            ValueAnimator valueAnimator = this.animator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            getBindDataInterceptor().unIntercept();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(1200L);
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            StokeViewControl$createAnimator$1$1 stokeViewControl$createAnimator$1$1 = new StokeViewControl$createAnimator$1$1();
            stokeViewControl$createAnimator$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(stokeViewControl$createAnimator$1$1);
            StokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1 stokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1 = new StokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1();
            stokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addListener(stokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1);
            this.animator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.start();
        }
    }

    public final void updateStrokeViewSize(int i, int i2) {
        StrokeView strokeView = this.strokeView;
        if (strokeView != null) {
            strokeView.updateViewSize(i, i2);
        }
    }
}
