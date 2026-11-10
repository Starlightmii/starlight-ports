package com.oplus.systemui.qs.media;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.oplus.anim.EffectiveAnimationProperty;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.anim.SimpleColorFilter;
import com.oplus.anim.model.KeyPath;
import com.oplus.anim.value.EffectiveValueCallback;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusLottieButton extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusLottieButton";
    private final Lazy bgDrawable$delegate;
    private final ImageView imageView;
    private final Animator.AnimatorListener lottieAnimationListener;
    private final EffectiveAnimationView lottieAnimationView;
    public float qsState;
    public boolean spotLightEnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusLottieButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        EffectiveAnimationView effectiveAnimationView = new EffectiveAnimationView(context);
        this.lottieAnimationView = effectiveAnimationView;
        ImageView imageView = new ImageView(context);
        this.imageView = imageView;
        OplusLottieButton$lottieAnimationListener$1 oplusLottieButton$lottieAnimationListener$1 = new OplusLottieButton$lottieAnimationListener$1();
        oplusLottieButton$lottieAnimationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.lottieAnimationListener = oplusLottieButton$lottieAnimationListener$1;
        OplusLottieButton$$ExternalSyntheticLambda0 oplusLottieButton$$ExternalSyntheticLambda0 = new OplusLottieButton$$ExternalSyntheticLambda0();
        oplusLottieButton$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.bgDrawable$delegate = LazyKt__LazyJVMKt.lazy(oplusLottieButton$$ExternalSyntheticLambda0);
        addView((View) effectiveAnimationView, new FrameLayout.LayoutParams(-1, -1));
        effectiveAnimationView.setVisibility(4);
        addView(imageView, new FrameLayout.LayoutParams(-1, -1));
        effectiveAnimationView.effectiveDrawable.animator.addListener(oplusLottieButton$lottieAnimationListener$1);
        setClickable(true);
    }

    private final Drawable getBgDrawable() {
        return (Drawable) this.bgDrawable$delegate.getValue();
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final EffectiveAnimationView getLottieAnimationView() {
        return this.lottieAnimationView;
    }

    public void handleAnimationEnd() {
        hideLottieView();
    }

    public final void hideLottieView() {
        this.imageView.setVisibility(0);
        this.lottieAnimationView.setVisibility(4);
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusLottieButton-->performClick");
        }
        if (!this.spotLightEnable && this.qsState == 1.0f && getBackground() == null) {
            setBackground(getBgDrawable());
        }
        boolean zPerformClick = super.performClick();
        playLottieAnimation();
        return zPerformClick;
    }

    public void playLottieAnimation() {
        if (QsColorUtil.isGlobalThemeApplied(getContext()) || this.lottieAnimationView.getDrawable() == null) {
            return;
        }
        this.lottieAnimationView.setVisibility(0);
        this.lottieAnimationView.playAnimation();
    }

    public final void setColorFilter(int i) {
        this.imageView.setColorFilter(i);
    }

    public final void setImageDrawable(Drawable drawable) {
        this.imageView.setImageDrawable(drawable);
    }

    public void setImageResource(int i) {
        this.imageView.setImageResource(i);
    }

    public void setLottieColorFilter(int i) {
        int iRgb = Color.rgb(Color.red(i), Color.green(i), Color.blue(i));
        this.lottieAnimationView.effectiveDrawable.addValueCallback(new KeyPath(new String[]{"**"}), EffectiveAnimationProperty.COLOR_FILTER, new EffectiveValueCallback(new SimpleColorFilter(iRgb)));
        int iAlpha = (int) ((Color.alpha(i) / 255.0f) * 100.0f);
        this.lottieAnimationView.effectiveDrawable.addValueCallback(new KeyPath(new String[]{"**"}), 4, new EffectiveValueCallback(Integer.valueOf(iAlpha)));
    }

    public void setLottieRes(Integer num) {
        EffectiveAnimationView effectiveAnimationView = this.lottieAnimationView;
        if (num != null) {
            effectiveAnimationView.setAnimation(num.intValue());
        } else {
            effectiveAnimationView.clearAnimation();
        }
    }

    public final void updateBackground() {
        Drawable bgDrawable = null;
        if (!this.spotLightEnable) {
            float f = this.qsState;
            if (f != 0.0f && f != -1.0f) {
                if (f != 1.0f) {
                    return;
                } else {
                    bgDrawable = getBgDrawable();
                }
            }
        }
        if (getBackground() != bgDrawable) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusLottieButton-->".concat("updateBackground qsState=" + this.qsState + " spotLightEnable=" + this.spotLightEnable));
            }
            setBackground(bgDrawable);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OplusLottieButton(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public /* synthetic */ OplusLottieButton(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
