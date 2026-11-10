package com.oplus.systemui.notification.row.oplusgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainer extends NotificationChildrenContainer {
    public float blurRadio;
    private final RenderNode blurRenderNode;

    public /* synthetic */ OplusNotificationChildrenContainer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        if (this.blurRadio <= 0.0f) {
            super/*android.view.ViewGroup*/.dispatchDraw(canvas);
            return;
        }
        this.blurRenderNode.setPosition(0, 0, getWidth(), getContainingNotification().getActualHeight());
        super/*android.view.ViewGroup*/.dispatchDraw(this.blurRenderNode.beginRecording());
        this.blurRenderNode.endRecording();
        canvas.drawRenderNode(this.blurRenderNode);
    }

    public final float getBlurRadio() {
        return this.blurRadio;
    }

    public /* bridge */ /* synthetic */ float getBottomCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getBottomRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundness();
    }

    public /* bridge */ /* synthetic */ Set getBottomRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundnessSources();
    }

    public /* bridge */ /* synthetic */ float getMaxRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getMaxRadius();
    }

    public /* bridge */ /* synthetic */ float getTopCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getTopRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundness();
    }

    public /* bridge */ /* synthetic */ Set getTopRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundnessSources();
    }

    public /* bridge */ /* synthetic */ float[] getUpdatedRadii() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getUpdatedRadii();
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx = ((NotificationChildrenContainer) this).mOverlayEx;
        if (notificationChildrenContainerOverlayEx != null) {
            notificationChildrenContainerOverlayEx.layoutGroupOverlay();
        }
        NotificationChildrenContainerExt ext = getExt();
        if (ext != null) {
            ext.layoutHeader(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onViewAdded(View view) {
        super/*android.view.ViewGroup*/.onViewAdded(view);
        NotificationChildrenContainerExt ext = getExt();
        if (ext != null) {
            ext.onChildrenViewAdded(this, view);
        }
    }

    public void removeTransientView(View view) {
        super.removeTransientView(view);
        NotificationChildrenContainerExt ext = getExt();
        if (ext != null) {
            ext.onRemoveTransientView(view);
        }
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBlurRadio(float f) {
        if (this.blurRadio == f) {
            return;
        }
        this.blurRadio = f;
        RenderNode renderNode = this.blurRenderNode;
        if (f == 0.0f) {
            renderNode.setRenderEffect(null);
        } else {
            renderNode.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        }
        invalidate();
    }

    public /* bridge */ /* synthetic */ void updateRadii(float f, float f2, float[] fArr) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.updateRadii(f, f2, fArr);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType, boolean z) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType, z);
    }

    public OplusNotificationChildrenContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusNotificationChildrenContainer(Context context) {
        this(context, null, 0, 6, null);
    }

    public OplusNotificationChildrenContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.blurRenderNode = new RenderNode("OplusNotificationChildrenContainer-Blur");
    }
}
