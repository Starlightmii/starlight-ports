package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RoundClipView extends FrameLayout {
    public float bottomCornerRadius;
    public int clipBottomAmount;
    public int clipLeftAmount;
    public int clipRightAmount;
    public int clipTopAmount;
    public boolean isHeadsUp;
    private final Path tmpPath;
    public float topCornerRadius;
    private final RoundClipView$viewOutlineProvider$1 viewOutlineProvider;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.view.ViewOutlineProvider, com.oplus.systemui.statusbar.notification.row.menupanel.RoundClipView$viewOutlineProvider$1] */
    public RoundClipView(Context context) {
        super(context);
        this.clipRightAmount = getMeasuredWidth();
        this.tmpPath = new Path();
        ?? r1 = new ViewOutlineProvider() { // from class: com.oplus.systemui.statusbar.notification.row.menupanel.RoundClipView$viewOutlineProvider$1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (outline != null) {
                    outline.setPath(this.this$0.getRoundClipPath());
                }
            }
        };
        this.viewOutlineProvider = r1;
        setOutlineProvider(r1);
        setClipToOutline(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Path getRoundClipPath() {
        this.tmpPath.reset();
        float f = this.clipLeftAmount;
        float f2 = this.clipTopAmount;
        int measuredWidth = this.clipRightAmount;
        if (measuredWidth <= 0) {
            measuredWidth = getMeasuredWidth();
        }
        float f3 = measuredWidth;
        float measuredHeight = getMeasuredHeight() - this.clipBottomAmount;
        if (measuredHeight < f2) {
            measuredHeight = f2;
        }
        SmoothRoundExKt.addSmoothRoundRect$default(this.tmpPath, new RectF(f, f2, f3, measuredHeight), this.topCornerRadius, this.bottomCornerRadius, false, 56);
        return this.tmpPath;
    }

    public final float getBottomCornerRadius() {
        return this.bottomCornerRadius;
    }

    public final int getClipBottomAmount() {
        return this.clipBottomAmount;
    }

    public final int getClipLeftAmount() {
        return this.clipLeftAmount;
    }

    public final int getClipRightAmount() {
        return this.clipRightAmount;
    }

    public final int getClipTopAmount() {
        return this.clipTopAmount;
    }

    public final float getTopCornerRadius() {
        return this.topCornerRadius;
    }

    public final void setBottomCornerRadius(float f) {
        if (this.bottomCornerRadius == f) {
            return;
        }
        this.bottomCornerRadius = f;
    }

    public final void setClipBottomAmount(int i) {
        if (this.clipBottomAmount == i) {
            return;
        }
        this.clipBottomAmount = i;
    }

    public final void setClipLeftAmount(int i) {
        if (this.clipLeftAmount == i) {
            return;
        }
        this.clipLeftAmount = i;
    }

    public final void setClipRightAmount(int i) {
        if (this.clipRightAmount == i) {
            return;
        }
        this.clipRightAmount = i;
    }

    public final void setClipTopAmount(int i) {
        if (this.clipTopAmount == i) {
            return;
        }
        this.clipTopAmount = i;
    }

    public final void setHeadsUp(boolean z) {
        if (this.isHeadsUp == z) {
            return;
        }
        this.isHeadsUp = z;
    }

    public final void setTopCornerRadius(float f) {
        if (this.topCornerRadius == f) {
            return;
        }
        this.topCornerRadius = f;
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        int i = this.clipTopAmount;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight() - this.clipBottomAmount;
        if (measuredHeight < i) {
            measuredHeight = i;
        }
        Drawable background = getBackground();
        if (background != null) {
            background.getBounds().set(0, i, measuredWidth, measuredHeight);
            background.invalidateSelf();
        }
    }
}
