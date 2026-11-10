package com.oplus.systemui.plugins.qs.customize.view.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.util.QSColorUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderProxy {
    public static final Companion Companion = new Companion();
    private final Lazy bottomBg$delegate;
    private final Context context;
    private final Lazy drawable$delegate;
    private final View targetView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PlaceHolderLayerDrawable extends Drawable implements Drawable.Callback {
        public static final Companion Companion = new Companion();
        private final Context context;
        private final Drawable[] layers;
        public int wholeAlpha = 255;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public PlaceHolderLayerDrawable(Context context, Drawable[] drawableArr) {
            this.context = context;
            this.layers = drawableArr;
            for (Drawable drawable : drawableArr) {
                drawable.setCallback(this);
            }
        }

        public final PlaceHolderLayerDrawable copy() {
            Drawable drawableNewDrawable;
            List<Drawable> list = ArraysKt___ArraysKt.toList(this.layers);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (Drawable drawableCopy : list) {
                Drawable.ConstantState constantState = drawableCopy.getConstantState();
                if (constantState != null && (drawableNewDrawable = constantState.newDrawable()) != null) {
                    drawableCopy = drawableNewDrawable;
                } else if (drawableCopy instanceof BlurDrawable) {
                    drawableCopy = ((BlurDrawable) drawableCopy).copy(this.context);
                    drawableCopy.setShaderBlendParams(QSBlurConfigProvider.getSepEmptyHolderBlurConfig(this.context.getResources().getConfiguration().isNightModeActive(), 1.0f).getMixMultiShaderParams());
                    Companion companion = EmptyPlaceHolderProxy.Companion;
                    Context context = this.context;
                    companion.getClass();
                    float fDpToPxWithInitialDensity = CellCalculatorManager.Companion.getInstance(context).dpToPxWithInitialDensity(62.0f) / 2.0f;
                    drawableCopy.setSmoothCorner(fDpToPxWithInitialDensity, fDpToPxWithInitialDensity, 1.0f, true);
                }
                arrayList.add(drawableCopy);
            }
            PlaceHolderLayerDrawable placeHolderLayerDrawable = new PlaceHolderLayerDrawable(this.context, (Drawable[]) arrayList.toArray(new Drawable[0]));
            placeHolderLayerDrawable.wholeAlpha = this.wholeAlpha;
            return placeHolderLayerDrawable;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            int i = this.wholeAlpha;
            int i2 = 0;
            if (i == 255) {
                Drawable[] drawableArr = this.layers;
                int length = drawableArr.length;
                while (i2 < length) {
                    drawableArr[i2].draw(canvas);
                    i2++;
                }
                return;
            }
            canvas.saveLayerAlpha(null, i);
            Drawable[] drawableArr2 = this.layers;
            int length2 = drawableArr2.length;
            while (i2 < length2) {
                drawableArr2[i2].draw(canvas);
                i2++;
            }
            canvas.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.wholeAlpha;
        }

        public final int getFgAlpha() {
            Drawable drawable = (Drawable) CollectionsKt___CollectionsKt.firstOrNull(ArraysKt___ArraysKt.toList(this.layers).subList(1, this.layers.length));
            if (drawable != null) {
                return drawable.getAlpha();
            }
            return 255;
        }

        public final Drawable[] getLayers() {
            return this.layers;
        }

        @Override // android.graphics.drawable.Drawable
        @Deprecated
        public int getOpacity() {
            Drawable drawable = (Drawable) ArraysKt___ArraysKt.firstOrNull(this.layers);
            if (drawable != null) {
                return drawable.getOpacity();
            }
            return -2;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            if (ArraysKt___ArraysKt.contains(this.layers, drawable)) {
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect rect) {
            for (Drawable drawable : this.layers) {
                drawable.setBounds(rect);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            if (this.wholeAlpha != i) {
                this.wholeAlpha = i;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            for (Drawable drawable : this.layers) {
                drawable.setColorFilter(colorFilter);
            }
        }

        public final void setFgAlpha(int i) {
            Drawable[] drawableArr = this.layers;
            int length = drawableArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                Drawable drawable = drawableArr[i2];
                int i4 = i3 + 1;
                if (i3 == 0) {
                    drawable.setAlpha(255 - i);
                } else {
                    drawable.setAlpha(i);
                }
                i2++;
                i3 = i4;
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Aeki1dzKZbJT-PZGD_4ZT6YKjVY, reason: not valid java name */
    public static Drawable m169$r8$lambda$Aeki1dzKZbJTPZGD_4ZT6YKjVY(EmptyPlaceHolderProxy emptyPlaceHolderProxy) {
        boolean zIsMixColorSupport = QsViewBackgroundKt.isMixColorSupport(emptyPlaceHolderProxy.context, QSColorUtil.isGaussBlurDisabled());
        Context context = emptyPlaceHolderProxy.context;
        return zIsMixColorSupport ? QSBlurConfigProvider.getSepEmptyHolderBlurDrawable$default(context, emptyPlaceHolderProxy.targetView.getViewRootImpl().getSurfaceControl(), CellCalculatorManager.Companion.getInstance(emptyPlaceHolderProxy.context).dpToPxWithInitialDensity(62.0f) / 2.0f, false, Float.valueOf(1.0f), 40) : context.getResources().getDrawable(2131233382, null);
    }

    public static PlaceHolderLayerDrawable $r8$lambda$YEZa34Ns4DG03q3zNJ8lzSRWNqg(EmptyPlaceHolderProxy emptyPlaceHolderProxy) {
        boolean zIsLowGaussianLevelColor = QsColorUtil.isLowGaussianLevelColor(emptyPlaceHolderProxy.context, true);
        Context context = emptyPlaceHolderProxy.context;
        Drawable drawable = zIsLowGaussianLevelColor ? context.getResources().getDrawable(2131236391, null) : context.getResources().getDrawable(2131236390, null);
        Context context2 = emptyPlaceHolderProxy.context;
        drawable.getClass();
        PlaceHolderLayerDrawable placeHolderLayerDrawable = new PlaceHolderLayerDrawable(context2, new Drawable[]{emptyPlaceHolderProxy.getBottomBg(), drawable});
        placeHolderLayerDrawable.setFgAlpha(255);
        return placeHolderLayerDrawable;
    }

    public EmptyPlaceHolderProxy(View view) {
        this.targetView = view;
        this.context = view.getContext();
        EmptyPlaceHolderProxy$$ExternalSyntheticLambda0 emptyPlaceHolderProxy$$ExternalSyntheticLambda0 = new EmptyPlaceHolderProxy$$ExternalSyntheticLambda0(0);
        emptyPlaceHolderProxy$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.bottomBg$delegate = LazyKt__LazyJVMKt.lazy(emptyPlaceHolderProxy$$ExternalSyntheticLambda0);
        EmptyPlaceHolderProxy$$ExternalSyntheticLambda0 emptyPlaceHolderProxy$$ExternalSyntheticLambda1 = new EmptyPlaceHolderProxy$$ExternalSyntheticLambda0(1);
        emptyPlaceHolderProxy$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.drawable$delegate = LazyKt__LazyJVMKt.lazy(emptyPlaceHolderProxy$$ExternalSyntheticLambda1);
    }

    private final Drawable getBottomBg() {
        return (Drawable) this.bottomBg$delegate.getValue();
    }

    public final Drawable copy() {
        return ((PlaceHolderLayerDrawable) getDrawable()).copy();
    }

    public final Drawable getDrawable() {
        return (Drawable) this.drawable$delegate.getValue();
    }
}
