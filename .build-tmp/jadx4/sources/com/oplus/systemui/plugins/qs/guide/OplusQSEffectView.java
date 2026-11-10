package com.oplus.systemui.plugins.qs.guide;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.content.FileProvider$;
import android.util.AttributeSet;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.fragment.app.Fragment$5$;
import androidx.room.util.CursorUtil$;
import com.android.systemui.doze.DozeLogger$;
import com.effects.lingguang.LingguangAnimator;
import com.effects.lingguang.LingguangEffect;
import com.effects.lingguang.LingguangManager;
import com.effects.lingguang.sysfx.SysFXEffect;
import com.effects.lingguang.sysfx.SysFXView;
import com.oplus.systemui.plugins.qs.R;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.UUID;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSEffectView extends View implements SysFXView {
    public static final Companion Companion = new Companion();
    private static final String PROPERTIES_CONFIG_PATH = "effect_properties.json";
    private static final String TAG = "OplusQSEffectView";
    public static boolean isDebug;
    public float bendPositionY;
    private SysFXEffect effect;
    private LingguangManager effectManager;
    private String path;
    private PositionListener positionListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void setDebug(boolean z) {
            OplusQSEffectView.isDebug = z;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PositionListener {
    }

    public OplusQSEffectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.path = PROPERTIES_CONFIG_PATH;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OplusQSEffectView, i, 0);
        try {
            this.path = typedArrayObtainStyledAttributes.getString(0);
            AutoCloseableKt.closeFinally(typedArrayObtainStyledAttributes, (Throwable) null);
            String str = this.path;
            if (str == null || str.length() == 0) {
                this.path = PROPERTIES_CONFIG_PATH;
            }
            String string = UUID.randomUUID().toString();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, CursorUtil$.ExternalSyntheticOutline0.m("init, ", string, ",  ", this.path), null, 8);
            LingguangManager lingguangManager = new LingguangManager(this, context, string, TAG, this.path);
            this.effectManager = lingguangManager;
            2.1 r6 = new 2.1();
            r6.this$0 = this;
            VarHandle.storeStoreFence();
            lingguangManager.addPositionsListener(r6);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(typedArrayObtainStyledAttributes, th);
                throw th2;
            }
        }
    }

    private final float getActivationDuration() {
        LingguangAnimator.AnimationTargets animationControls = getAnimationControls();
        if (animationControls == null) {
            return 0.0f;
        }
        return animationControls.barDeactivationDuration.get().floatValue() + animationControls.barActivationDuration.get().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getAnimPhase() {
        long currentPlayTime = getCurrentPlayTime();
        float entranceDuration = getEntranceDuration();
        float activationDuration = getActivationDuration();
        if (currentPlayTime == 0 || entranceDuration == 0.0f || activationDuration == 0.0f) {
            return -1;
        }
        return ((float) currentPlayTime) < entranceDuration ? 0 : 1;
    }

    private final LingguangAnimator.AnimationTargets getAnimationControls() {
        LingguangManager lingguangManager = this.effectManager;
        if (lingguangManager != null) {
            return lingguangManager.getAnimationControls();
        }
        return null;
    }

    private final long getCurrentPlayTime() {
        AnimatorSet animators = getAnimators();
        if (animators != null) {
            return animators.getCurrentPlayTime();
        }
        return -1L;
    }

    private final LingguangEffect.EffectTargets getEffectControls() {
        LingguangManager lingguangManager = this.effectManager;
        if (lingguangManager != null) {
            return lingguangManager.getEffectControls();
        }
        return null;
    }

    private final float getEntranceDuration() {
        LingguangAnimator.AnimationTargets animationControls = getAnimationControls();
        if (animationControls == null) {
            return 0.0f;
        }
        return animationControls.fadeOutDuration.get().floatValue() + animationControls.fadeInDuration.get().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getFraction() {
        long currentPlayTime = getCurrentPlayTime();
        float entranceDuration = getEntranceDuration();
        float activationDuration = getActivationDuration();
        if (currentPlayTime == 0 || entranceDuration == 0.0f || activationDuration == 0.0f) {
            return 0.0f;
        }
        float f = currentPlayTime;
        return f < entranceDuration ? f / entranceDuration : (f - entranceDuration) / activationDuration;
    }

    public void SetEffect(SysFXEffect sysFXEffect) {
        this.effect = sysFXEffect;
    }

    public final void addAnimatorListener(AnimatorListenerAdapter animatorListenerAdapter) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "addAnimatorListener,  " + animatorListenerAdapter + " " + this.path, null, 8);
        AnimatorSet animators = getAnimators();
        if (animators != null) {
            animators.removeAllListeners();
            animators.addListener(animatorListenerAdapter);
        }
    }

    public final void addPositionListener(PositionListener positionListener) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "addPositionListener,  " + positionListener + " " + this.path, null, 8);
        this.positionListener = positionListener;
    }

    public final void cancelEffect() {
        AnimatorSet animators = getAnimators();
        if (animators != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, DozeLogger$.ExternalSyntheticOutline0.m("cancelEffect , ", " ", this.path, animators.isStarted()), null, 8);
            if (animators.isStarted()) {
                animators.cancel();
            }
        }
    }

    public final AnimatorSet getAnimators() {
        LingguangManager lingguangManager = this.effectManager;
        if (lingguangManager != null) {
            return lingguangManager.getAnimators();
        }
        return null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        SysFXEffect sysFXEffect = this.effect;
        if (sysFXEffect != null) {
            sysFXEffect.onDraw(canvas);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        SysFXEffect sysFXEffect = this.effect;
        if (sysFXEffect != null) {
            sysFXEffect.onSizeChanged(i, i2, i3, i4);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("onSizeChanged ", i, i2, ", ", ", "), i3, i4, ", "), null, 8);
    }

    public final void releaseEffect() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("releaseEffect ", this.path), null, 8);
        cancelEffect();
        LingguangManager lingguangManager = this.effectManager;
        if (lingguangManager != null) {
            OplusQSEffectView$releaseEffect$1$1 oplusQSEffectView$releaseEffect$1$1 = new OplusQSEffectView$releaseEffect$1$1();
            oplusQSEffectView$releaseEffect$1$1.$it = lingguangManager;
            VarHandle.storeStoreFence();
            QSThreadUtil.runOnWorkThread(oplusQSEffectView$releaseEffect$1$1);
            this.positionListener = null;
        }
    }

    public final void startEffect() {
        AnimatorSet animators = getAnimators();
        if (animators != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, DozeLogger$.ExternalSyntheticOutline0.m("startEffect , ", " ", this.path, animators.isStarted()), null, 8);
            if (animators.isStarted()) {
                return;
            }
            animators.start();
        }
    }

    public OplusQSEffectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ OplusQSEffectView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public OplusQSEffectView(Context context) {
        this(context, null, 0, 6, null);
    }
}
