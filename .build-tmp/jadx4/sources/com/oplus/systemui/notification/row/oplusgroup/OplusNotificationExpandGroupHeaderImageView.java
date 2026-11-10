package com.oplus.systemui.notification.row.oplusgroup;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.android.systemui.statusbar.AlphaOptimizedImageView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.transition.OplusAnimationController;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationExpandGroupHeaderImageView extends AlphaOptimizedImageView {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy blurHelper$delegate;
    private final Configuration lastConfig;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusNotificationExpandGroupHeaderImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.blurHelper$delegate = LazyKt__LazyJVMKt.lazy(new OplusNotificationExpandGroupHeaderImageView$.ExternalSyntheticLambda0());
        this.lastConfig = new Configuration(context.getResources().getConfiguration());
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.EXPAND_GROUP_HEADER_BUTTON);
    }

    private final ExpandGroupHeaderButtonBlurHelper getBlurHelper() {
        return (ExpandGroupHeaderButtonBlurHelper) this.blurHelper$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.ImageView*/.onAttachedToWindow();
        ExpandGroupHeaderButtonBlurHelper blurHelper = getBlurHelper();
        if (blurHelper != null) {
            blurHelper.apply(getContext(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.ImageView*/.onConfigurationChanged(configuration);
        if ((this.lastConfig.updateFrom(configuration) & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0) {
            refreshMaterialBackground();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        ExpandGroupHeaderButtonBlurHelper blurHelper = getBlurHelper();
        if (blurHelper != null) {
            blurHelper.release(this, "expand group header detach");
        }
        super/*android.widget.ImageView*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        super/*android.widget.ImageView*/.onDraw(canvas);
        NotificationSpotLightDelegate.Companion.draw(this, canvas, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshMaterialBackground() {
        ExpandGroupHeaderButtonBlurHelper blurHelper;
        if (isAttachedToWindow() && (blurHelper = getBlurHelper()) != null) {
            blurHelper.apply(getContext(), this);
        }
    }

    public OplusNotificationExpandGroupHeaderImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusNotificationExpandGroupHeaderImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ OplusNotificationExpandGroupHeaderImageView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public OplusNotificationExpandGroupHeaderImageView(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
