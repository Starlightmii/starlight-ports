package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.ActionReceiver$;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.capsule.background.CapsuleShardBackgroundDrawableManager;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleBackgroundView extends View implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleBackgroundView";
    private final CapsuleBackgroundHelper capsuleHelper;
    private MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback;
    private final Lazy materialColorManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleBackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.materialColorManager$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleBackgroundView$$ExternalSyntheticLambda0());
        this.capsuleHelper = new CapsuleBackgroundHelper((Context) DependencyEx.sDependency.getDependency(Context.class));
    }

    private final MaterialColorManager getMaterialColorManager() {
        return (MaterialColorManager) this.materialColorManager$delegate.getValue();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        ActionReceiver$.ExternalSyntheticOutline1.m("Background color: ", Integer.toHexString(this.capsuleHelper.getBackgroundColor()), printWriter);
    }

    public final void enablePathProvider(boolean z) {
        this.capsuleHelper.enablePathProvider(z, true);
    }

    public final MetaBallBlurDrawable getBlurDrawable() {
        return this.capsuleHelper.getBlurDrawable();
    }

    public final CapsuleBackgroundHelper getCapsuleHelper() {
        return this.capsuleHelper;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void initHelper(AutoRoundRectPathProvider autoRoundRectPathProvider) {
        this.capsuleHelper.initBackground(this, autoRoundRectPathProvider);
    }

    public final void initSharedBgManager(CapsuleShardBackgroundDrawableManager capsuleShardBackgroundDrawableManager) {
        this.capsuleHelper.initSharedBgManager(capsuleShardBackgroundDrawableManager);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (ScrimUtil.isMiddleOrHighAnimLevel(getContext())) {
            return;
        }
        if (this.materialColorChangeCallback == null) {
            CapsuleBackgroundView$registerMaterialColorChangeCallback$1 capsuleBackgroundView$registerMaterialColorChangeCallback$1 = new CapsuleBackgroundView$registerMaterialColorChangeCallback$1();
            capsuleBackgroundView$registerMaterialColorChangeCallback$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.materialColorChangeCallback = capsuleBackgroundView$registerMaterialColorChangeCallback$1;
        }
        MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback = this.materialColorChangeCallback;
        if (materialColorChangeCallback != null) {
            getMaterialColorManager().addMaterialColorCallback(materialColorChangeCallback);
        }
    }

    public final void onClearTransient() {
        this.capsuleHelper.onClearTransient(this);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.capsuleHelper.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback = this.materialColorChangeCallback;
        if (materialColorChangeCallback != null) {
            getMaterialColorManager().removeMaterialColorCallback(materialColorChangeCallback);
        }
        this.materialColorChangeCallback = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.capsuleHelper.drawCapsuleBg(this, canvas);
    }

    public final void resetMetaBallParams() {
        CapsuleBackgroundHelper capsuleBackgroundHelper = this.capsuleHelper;
        capsuleBackgroundHelper.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleBackgroundHelper-->resetMetaBallParams: ");
        }
        MetaBallBlurDrawable blurDrawable = capsuleBackgroundHelper.getBlurDrawable();
        if (blurDrawable != null) {
            NotificationMenuRowProviderKt.setBlendRange(blurDrawable, 0, "CapsuleBackgroundHelper resetMetaBallParams");
        }
    }

    public final void setScrimAlpha(float f) {
        this.capsuleHelper.setScrimAlpha(f);
        float f2 = 1.0f - f;
        this.capsuleHelper.setStrokeAndShadowAlpha(f2, f2, null);
    }

    public final void setStrokeAndShadowAlpha(float f, float f2, Float f3) {
        this.capsuleHelper.setStrokeAndShadowAlpha(f, f2, f3);
    }

    public final void setStrokeRadius(float f, boolean z) {
        this.capsuleHelper.setStrokeRadius(f, z);
    }

    public final void useSmoothRadius(String str, boolean z) {
        this.capsuleHelper.useSmoothRadius(str, z);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || this.capsuleHelper.verifyDrawable(drawable);
    }

    public CapsuleBackgroundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ CapsuleBackgroundView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public CapsuleBackgroundView(Context context) {
        this(context, null, 0, 6, null);
    }
}
