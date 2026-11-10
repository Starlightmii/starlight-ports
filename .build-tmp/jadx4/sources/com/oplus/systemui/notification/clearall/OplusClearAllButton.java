package com.oplus.systemui.notification.clearall;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.AppCompatImageView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusClearAllButton extends AppCompatImageView {
    public static final Companion Companion = new Companion();
    public float currentScale;
    public boolean isNeedToDelayCancelScaleAnim;
    private final OplusClearAllButton$op$1 op;
    private final Interpolator scaleAnimationInterpolator;
    private ValueAnimator scaleAnimator;
    private NotificationSpotLightDelegate.ClipSpec spotLightClipSpec;
    private Boolean targetVisible;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.OplusClearAllButton$executeScaleAnimator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ boolean $isPressed;
        public /* synthetic */ OplusClearAllButton this$0;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.this$0.currentScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (this.this$0.isNeedToDelayCancelScaleAnim && this.$isPressed && valueAnimator.getCurrentPlayTime() > valueAnimator.getDuration() * 0.4f) {
                valueAnimator.cancel();
                this.this$0.executeScaleAnimator(false);
            } else {
                OplusClearAllButton oplusClearAllButton = this.this$0;
                oplusClearAllButton.setScale(oplusClearAllButton.currentScale);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.OplusClearAllButton$onDraw$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C01821 implements NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider {
        public /* synthetic */ OplusClearAllButton this$0;

        @Override // com.oplus.systemui.notification.material.NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider
        public final NotificationSpotLightDelegate.ClipSpec getClipSpec() {
            return this.this$0.spotLightClipSpec;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusClearAllButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scaleAnimationInterpolator = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        this.currentScale = 1.0f;
        OplusClearAllButton$op$1 oplusClearAllButton$op$1 = new OplusClearAllButton$op$1();
        this.op = oplusClearAllButton$op$1;
        setClipToOutline(true);
        setOutlineProvider(oplusClearAllButton$op$1);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.CLEAR_ALL_BUTTON);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final NotificationSpotLightDelegate.ClipSpec getSpotLightClipSpec() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        float f = height / 2.0f;
        return new NotificationSpotLightDelegate.ClipSpec(0, 0, width, height, f, f, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setScale(float f) {
        if (1.0f <= f) {
            f = 1.0f;
        }
        if (0.92f >= f) {
            f = 0.92f;
        }
        setScaleX(f);
        setScaleY(f);
        invalidate();
    }

    public final void executeScaleAnimator(boolean z) {
        this.isNeedToDelayCancelScaleAnim = false;
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            boolean z2 = !z && ((float) valueAnimator.getCurrentPlayTime()) < ((float) valueAnimator.getDuration()) * 0.4f;
            this.isNeedToDelayCancelScaleAnim = z2;
            if (!z2) {
                valueAnimator.cancel();
            }
        }
        if (this.isNeedToDelayCancelScaleAnim) {
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(z ? 1.0f : this.currentScale, z ? 0.92f : 1.0f);
        this.scaleAnimator = valueAnimatorOfFloat;
        if (valueAnimatorOfFloat != null) {
            valueAnimatorOfFloat.setInterpolator(this.scaleAnimationInterpolator);
        }
        ValueAnimator valueAnimator2 = this.scaleAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.setDuration(z ? 200L : 340L);
        }
        ValueAnimator valueAnimator3 = this.scaleAnimator;
        if (valueAnimator3 != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$isPressed = z;
            VarHandle.storeStoreFence();
            valueAnimator3.addUpdateListener(anonymousClass1);
        }
        ValueAnimator valueAnimator4 = this.scaleAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.start();
        }
    }

    public final Boolean getTargetVisible() {
        return this.targetVisible;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public void onDraw(Canvas canvas) {
        super/*android.widget.ImageView*/.onDraw(canvas);
        NotificationSpotLightDelegate.Companion companion = NotificationSpotLightDelegate.Companion;
        C01821 c01821 = new C01821();
        c01821.this$0 = this;
        VarHandle.storeStoreFence();
        companion.draw(this, canvas, c01821);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.ImageView*/.onSizeChanged(i, i2, i3, i4);
        this.spotLightClipSpec = getSpotLightClipSpec();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            motionEvent.getClass();
            int action = motionEvent.getAction();
            if (action == 0) {
                executeScaleAnimator(true);
            } else if (action == 1 || action == 3) {
                executeScaleAnimator(false);
            }
        }
        return super/*android.widget.ImageView*/.onTouchEvent(motionEvent);
    }

    public final void setTargetVisible(Boolean bool) {
        this.targetVisible = bool;
    }

    public OplusClearAllButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OplusClearAllButton(Context context) {
        this(context, null);
    }
}
