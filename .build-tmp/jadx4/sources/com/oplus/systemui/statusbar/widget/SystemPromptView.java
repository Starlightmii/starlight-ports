package com.oplus.systemui.statusbar.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import androidx.car.app.model.TimerSpan$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.statusbar.controller.SystemPromptController;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemPromptView extends ImageView implements SystemPromptController.IconVisibleChange, ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SystemPromptView";
    public static final String TAG_SIGNAL_VIEW_KEYGUARD = "signal_view_ks";
    private AnimatorSet allAnim;
    private final PathInterpolator hidePathInterpolator;
    public int lastMode;
    public int lastVisible;
    private int[] location;
    public int resId;
    private final PathInterpolator showPathInterpolator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SystemPromptView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastVisible = 8;
        this.showPathInterpolator = new PathInterpolator(0.0f, 0.0f, 0.33f, 1.0f);
        this.hidePathInterpolator = new PathInterpolator(0.0f, 0.0f, 0.33f, 1.0f);
        this.allAnim = new AnimatorSet();
        this.location = new int[2];
    }

    public final AnimatorSet createShowHideAnim(SystemPromptView systemPromptView, final boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(z ? this.showPathInterpolator : this.hidePathInterpolator);
        Animator[] animatorArr = new Animator[3];
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(systemPromptView, (Property<SystemPromptView, Float>) ImageView.SCALE_X, z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(333L);
        animatorArr[0] = objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(systemPromptView, (Property<SystemPromptView, Float>) ImageView.SCALE_Y, z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat2.setDuration(333L);
        animatorArr[1] = objectAnimatorOfFloat2;
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(systemPromptView, (Property<SystemPromptView, Float>) ImageView.ALPHA, z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat3.setDuration(270L);
        animatorArr[2] = objectAnimatorOfFloat3;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.widget.SystemPromptView.createShowHideAnim.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SystemPromptView.this.setVisibility(z ? 0 : 8);
                SystemPromptView.this.setAlpha(z ? 1.0f : 0.0f);
                SystemPromptView.this.setScaleX(z ? 1.0f : 0.0f);
                SystemPromptView.this.setScaleY(z ? 1.0f : 0.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                SystemPromptView.this.setVisibility(0);
                SystemPromptView.this.setAlpha(z ? 0.0f : 1.0f);
                SystemPromptView.this.setScaleX(z ? 0.0f : 1.0f);
                SystemPromptView.this.setScaleY(z ? 0.0f : 1.0f);
            }
        });
        return animatorSet;
    }

    @Override // com.oplus.systemui.statusbar.controller.SystemPromptController.IconVisibleChange
    public Rect getIconRect() {
        this.location = getLocationOnScreen();
        Rect rect = new Rect();
        if (Intrinsics.areEqual(getTag(), TAG_SIGNAL_VIEW_KEYGUARD)) {
            return null;
        }
        int[] iArr = this.location;
        int i = iArr[0];
        rect.left = i;
        rect.top = iArr[1];
        rect.right = getMeasuredWidth() + i;
        rect.bottom = getMeasuredHeight() + this.location[1];
        return rect;
    }

    public final void onDensityOrFontScaleChanged() {
        int i;
        if (getVisibility() != 0 || (i = this.resId) == 0) {
            return;
        }
        setImageResource(i);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.location = getLocationOnScreen();
    }

    public void onMovedToDisplay(int i, Configuration configuration) {
        super.onMovedToDisplay(i, configuration);
    }

    public final void setImage(int i, boolean z) {
        this.resId = i;
        setImageResource(i);
        int i2 = z ? 0 : 8;
        this.lastVisible = i2;
        setVisibility(i2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SystemPromptView-->".concat("resetStateIfNeeded " + this));
        }
        if (getVisibility() == 0) {
            if (Float.compare(1.0f, getAlpha()) != 0) {
                setAlpha(1.0f);
            }
            if (Float.compare(1.0f, getScaleX()) != 0) {
                setScaleX(1.0f);
            }
            if (Float.compare(1.0f, getScaleY()) != 0) {
                setScaleY(1.0f);
            }
        }
    }

    @Override // android.view.View
    public String toString() {
        int iHashCode = hashCode();
        boolean z = this.lastVisible == 0;
        float alpha = getAlpha();
        String resourceName = OplusStatusBarUtils.getResourceName(this.resId, getContext());
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        int width = getWidth();
        int height = getHeight();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("SystemPromptView:{hash: ", iHashCode, ",lastVisible = ", ",alpha = ", z);
        sbM.append(alpha);
        sbM.append(",resId = ");
        sbM.append(resourceName);
        sbM.append(",scaleX = ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, scaleX, ",scaleY = ", scaleY, ",size = [");
        return TimerSpan$.ExternalSyntheticOutline0.m(width, height, ", ", "]}", sbM);
    }

    @Override // com.oplus.systemui.statusbar.controller.SystemPromptController.IconVisibleChange
    public void updateIcon(final int i, final boolean z, int i2, String str) {
        setContentDescription(str);
        boolean z2 = getVisibility() == 0;
        if (!z) {
            if (this.allAnim.isRunning()) {
                this.allAnim.cancel();
            }
            if (z2 != z) {
                AnimatorSet animatorSetCreateShowHideAnim = createShowHideAnim(this, z);
                animatorSetCreateShowHideAnim.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.widget.SystemPromptView.updateIcon.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        SystemPromptView systemPromptView = this;
                        int i3 = i;
                        boolean z3 = z;
                        Companion companion = SystemPromptView.Companion;
                        systemPromptView.setImage(i3, z3);
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        boolean z3 = z;
                        if (z3) {
                            SystemPromptView systemPromptView = this;
                            int i3 = i;
                            Companion companion = SystemPromptView.Companion;
                            systemPromptView.setImage(i3, z3);
                        }
                    }
                });
                animatorSetCreateShowHideAnim.start();
            } else {
                setImage(i, z);
            }
        } else if (this.lastMode != i2) {
            AnimatorSet animatorSetCreateShowHideAnim2 = createShowHideAnim(this, false);
            AnimatorSet animatorSetCreateShowHideAnim3 = createShowHideAnim(this, true);
            animatorSetCreateShowHideAnim3.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.widget.SystemPromptView.updateIcon.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    SystemPromptView systemPromptView = this;
                    int i3 = i;
                    boolean z3 = z;
                    Companion companion = SystemPromptView.Companion;
                    systemPromptView.setImage(i3, z3);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    SystemPromptView systemPromptView = this;
                    int i3 = i;
                    boolean z3 = z;
                    Companion companion = SystemPromptView.Companion;
                    systemPromptView.setImage(i3, z3);
                }
            });
            if (this.allAnim.isRunning()) {
                this.allAnim.cancel();
            }
            AnimatorSet animatorSet = new AnimatorSet();
            this.allAnim = animatorSet;
            animatorSet.playSequentially(animatorSetCreateShowHideAnim2, animatorSetCreateShowHideAnim3);
            this.allAnim.start();
        } else if (!z2) {
            setImage(i, z);
        }
        this.lastMode = i2;
    }

    public SystemPromptView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ SystemPromptView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public SystemPromptView(Context context) {
        this(context, null, 0, 6, null);
    }
}
