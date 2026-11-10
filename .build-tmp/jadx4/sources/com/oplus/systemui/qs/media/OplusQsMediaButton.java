package com.oplus.systemui.qs.media;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageButton;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaButton extends ImageButton {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Runnable onSpotLightAvailabilityChanged;
    private final Lazy rippleBackground$delegate;
    private final QsMediaSpotLightHelper spotLight;

    public OplusQsMediaButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.spotLight = new QsMediaSpotLightHelper(this, QsMediaSpotLightHelper.ClipShape.CIRCLE);
        OplusQsMediaButton$$ExternalSyntheticLambda0 oplusQsMediaButton$$ExternalSyntheticLambda0 = new OplusQsMediaButton$$ExternalSyntheticLambda0();
        oplusQsMediaButton$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.rippleBackground$delegate = LazyKt__LazyJVMKt.lazy(oplusQsMediaButton$$ExternalSyntheticLambda0);
        OplusQsMediaButton$onSpotLightAvailabilityChanged$1 oplusQsMediaButton$onSpotLightAvailabilityChanged$1 = new OplusQsMediaButton$onSpotLightAvailabilityChanged$1();
        oplusQsMediaButton$onSpotLightAvailabilityChanged$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onSpotLightAvailabilityChanged = oplusQsMediaButton$onSpotLightAvailabilityChanged$1;
    }

    private final Drawable getRippleBackground() {
        return (Drawable) this.rippleBackground$delegate.getValue();
    }

    public final void applySystemSpotLightPolicy() {
        boolean z = NotificationSpotLightEffectManager.spotLightEnabled;
        setBackground(z ? null : getRippleBackground());
        if (!isAttachedToWindow() || getVisibility() != 0) {
            this.spotLight.releaseDrawable();
            return;
        }
        QsMediaSpotLightHelper qsMediaSpotLightHelper = this.spotLight;
        if (!z) {
            qsMediaSpotLightHelper.releaseDrawable();
            return;
        }
        qsMediaSpotLightHelper.ensureDrawable();
        this.spotLight.updateDrawableBounds(getWidth(), getHeight());
        this.spotLight.syncDrawableEnabledFromHost();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(this.onSpotLightAvailabilityChanged);
        applySystemSpotLightPolicy();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        NotificationSpotLightEffectManager.removeOnSpotLightAvailabilityChangedListener(this.onSpotLightAvailabilityChanged);
        this.spotLight.releaseDrawable();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.spotLight.drawSpotLightEffect(canvas);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.spotLight.updateDrawableBounds(i, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            ViewParent parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(true);
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (parent = getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        this.spotLight.handleMotionEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view != this) {
            return;
        }
        applySystemSpotLightPolicy();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.spotLight.syncDrawableEnabledFromHost();
    }

    public OplusQsMediaButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ OplusQsMediaButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? R.attr.imageButtonStyle : i);
    }

    public OplusQsMediaButton(Context context) {
        this(context, null, 0, 6, null);
    }
}
