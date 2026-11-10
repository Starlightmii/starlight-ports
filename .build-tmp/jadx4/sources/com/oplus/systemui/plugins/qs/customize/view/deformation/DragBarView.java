package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragBarView extends View implements IStrokeWidthModifier {
    public static final Companion Companion = new Companion();
    public final float dragBarDefaultValue;
    public final float dragBarHighLightValue;
    public final int dragBarSize;
    private final Lazy paint$delegate;
    private final Lazy path$delegate;
    public float radius;
    private final Lazy rectF$delegate;
    public float strokeWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DragBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        final int i2 = 0;
        this.path$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.deformation.DragBarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        DragBarView.Companion companion = DragBarView.Companion;
                        return new Path();
                    case 1:
                        DragBarView.Companion companion2 = DragBarView.Companion;
                        return new RectF();
                    default:
                        DragBarView.Companion companion3 = DragBarView.Companion;
                        Paint paint = new Paint(1);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setColor(-1);
                        return paint;
                }
            }
        });
        final int i3 = 1;
        this.rectF$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.deformation.DragBarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        DragBarView.Companion companion = DragBarView.Companion;
                        return new Path();
                    case 1:
                        DragBarView.Companion companion2 = DragBarView.Companion;
                        return new RectF();
                    default:
                        DragBarView.Companion companion3 = DragBarView.Companion;
                        Paint paint = new Paint(1);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setColor(-1);
                        return paint;
                }
            }
        });
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        this.dragBarSize = companion.lockDpi(context.getResources().getDimensionPixelSize(2131171783), context);
        this.dragBarHighLightValue = companion.lockDpi(context.getResources().getDimensionPixelSize(2131171785), context);
        this.dragBarDefaultValue = companion.lockDpi(context.getResources().getDimensionPixelSize(2131171784), context);
        final int i4 = 2;
        this.paint$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.deformation.DragBarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        DragBarView.Companion companion2 = DragBarView.Companion;
                        return new Path();
                    case 1:
                        DragBarView.Companion companion3 = DragBarView.Companion;
                        return new RectF();
                    default:
                        DragBarView.Companion companion4 = DragBarView.Companion;
                        Paint paint = new Paint(1);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setColor(-1);
                        return paint;
                }
            }
        });
    }

    private final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    private final Path getPath() {
        return (Path) this.path$delegate.getValue();
    }

    private final RectF getRectF() {
        return (RectF) this.rectF$delegate.getValue();
    }

    public final float getDragBarDefaultValue() {
        return this.dragBarDefaultValue;
    }

    public final float getDragBarHighLightValue() {
        return this.dragBarHighLightValue;
    }

    public final int getDragBarSize() {
        return this.dragBarSize;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getStrokeExtraWidth() {
        return this.dragBarHighLightValue / 2.0f;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier
    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public final void onDragBarShowStatusChange(boolean z) {
        setVisibility(!z ? 4 : 0);
        setScaleX(z ? 1.0f : 0.9f);
        setScaleY(z ? 1.0f : 0.9f);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(getPath(), getPaint());
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updatePath();
        setPivotX((getWidth() - getStrokeExtraWidth()) - (this.dragBarSize / 2));
        setPivotY((getHeight() - getStrokeExtraWidth()) - (this.dragBarSize / 2));
    }

    public final void setRadius(float f) {
        if (this.radius == f) {
            return;
        }
        this.radius = f;
        updatePath();
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier
    public void setStrokeWidth(float f) {
        if (this.strokeWidth == f) {
            return;
        }
        this.strokeWidth = f;
        getPaint().setStrokeWidth(f);
        invalidate();
    }

    public final void updatePath() {
        float width = (getWidth() - getStrokeExtraWidth()) - this.radius;
        float height = getHeight() - getStrokeExtraWidth();
        float f = this.radius;
        float f2 = height - f;
        float f3 = f * 2.0f < ((float) getHeight()) - getStrokeExtraWidth() ? 0.0f : 20.0f;
        float f4 = this.radius * 2.0f < ((float) getHeight()) - getStrokeExtraWidth() ? 90.0f : 50.0f;
        Path path = getPath();
        path.reset();
        path.moveTo(this.radius + width, f2);
        RectF rectF = getRectF();
        float f5 = this.radius;
        rectF.set(width - f5, f2 - f5, width + f5, f2 + f5);
        path.arcTo(rectF, RangesKt___RangesKt.coerceIn(f3, -180.0f, 180.0f), f4, true);
        invalidate();
    }

    public DragBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ DragBarView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public DragBarView(Context context) {
        this(context, null, 0, 6, null);
    }
}
