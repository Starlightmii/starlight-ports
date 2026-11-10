package com.oplusos.systemui.common.outline;

import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.car.app.SurfaceContainer$;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplus.graphics.OplusPath;
import com.oplus.posteffect.util.OplusPathAdapterCompatUtils;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RoundRectOutlineProvider extends CornerOutlineProvider {
    private final float[] cornerRadii;
    public float cornerRadius;
    private Float cornerWeight;
    private OplusOutlineAdapter oplusOutlineAdapter;
    private Outline outlineLast;
    private final RectF tmpRoundRectF;

    public RoundRectOutlineProvider(float f, Float f2) {
        this.tmpRoundRectF = new RectF();
        this.cornerRadii = new float[8];
        update(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundRectOutlineProvider)) {
            return false;
        }
        RoundRectOutlineProvider roundRectOutlineProvider = (RoundRectOutlineProvider) obj;
        return Intrinsics.areEqual(this.cornerWeight, roundRectOutlineProvider.cornerWeight) && Arrays.equals(this.cornerRadii, roundRectOutlineProvider.cornerRadii);
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final Float getCornerWeight() {
        return this.cornerWeight;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        int width = view.getWidth();
        int height = view.getHeight();
        Float f = this.cornerWeight;
        if (f == null) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.cornerRadius);
            return;
        }
        if (this.oplusOutlineAdapter == null || !Intrinsics.areEqual(this.outlineLast, outline)) {
            this.outlineLast = outline;
            this.oplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
        }
        OplusOutlineAdapter oplusOutlineAdapter = this.oplusOutlineAdapter;
        if (oplusOutlineAdapter != null) {
            oplusOutlineAdapter.setSmoothRoundRect(0, 0, width, height, this.cornerRadius, f.floatValue());
        }
    }

    public final Outline getOutlineLast() {
        return this.outlineLast;
    }

    public final int hashCode() {
        Float f = this.cornerWeight;
        return Arrays.hashCode(this.cornerRadii) + ((f != null ? f.hashCode() : 0) * 31);
    }

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public final boolean isSmoothCorner() {
        return true;
    }

    public final void setCornerRadius(float f) {
        this.cornerRadius = f;
    }

    public final void setCornerWeight(Float f) {
        this.cornerWeight = f;
    }

    public final void setOutlineLast(Outline outline) {
        this.outlineLast = outline;
    }

    public String toString() {
        return "RoundRectOutlineProvider(cornerRadius=" + this.cornerRadius + ", cornerWeight=" + this.cornerWeight + ", cornerRadii=" + Arrays.toString(this.cornerRadii) + ')';
    }

    public final void update(float[] fArr, Float f) {
        int length = fArr.length;
        float[] fArr2 = this.cornerRadii;
        if (length < fArr2.length) {
            throw new ArrayIndexOutOfBoundsException(SurfaceContainer$.ExternalSyntheticOutline0.m(this.cornerRadii.length, " values", new StringBuilder("radii[] needs ")));
        }
        this.cornerWeight = f;
        this.cornerRadius = fArr2[0];
        ArraysKt___ArraysJvmKt.copyInto(fArr, fArr2, 0, 0, fArr2.length);
    }

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public void updatePath(Drawable drawable, Path path, OplusPath oplusPath, Object obj) {
        this.tmpRoundRectF.set(drawable.getBounds());
        Float f = this.cornerWeight;
        path.reset();
        if (f == null || obj == null) {
            path.addRoundRect(this.tmpRoundRectF, this.cornerRadii, Path.Direction.CW);
        } else {
            OplusPathAdapterCompatUtils.INSTANCE.addSmoothRoundRect(obj, this.tmpRoundRectF, this.cornerRadii, Path.Direction.CW, f.floatValue());
        }
    }

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public float getCornerRadius(Drawable drawable) {
        return this.cornerRadius;
    }

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public Float getCornerWeight(Drawable drawable) {
        return this.cornerWeight;
    }

    public RoundRectOutlineProvider(float[] fArr, Float f) {
        this.tmpRoundRectF = new RectF();
        this.cornerRadii = new float[8];
        update(fArr, f);
    }

    public final void update(float f, Float f2) {
        this.cornerWeight = f2;
        this.cornerRadius = f;
        ArraysKt___ArraysJvmKt.fill$default(f, this.cornerRadii);
    }
}
