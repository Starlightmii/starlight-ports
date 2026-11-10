package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.keyguard.OplusLockIconView$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.systemui.statusbar.pipeline.battery.utils.BatteryBarUtils;
import com.oplus.systemui.statusbar.util.OpImageUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BatteryBarDrawableBase extends BatteryContentDrawableBase {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BatteryBarDrawableBase";
    public int backgroundColor;
    public int batteryLevel;
    public int bitmapIconId;
    private Canvas canvas;
    private final Paint chargePaint;
    private Bitmap chargingBitmap;
    private final Paint clearPaint;
    private final Context context;
    public int frameColorMode;
    private Drawable frameDrawable;
    private LayerDrawable insideDrawable;
    public boolean isThemeModeColor;
    public int lastHeight;
    public int lastLevel;
    public int lastWidth;
    public float maskHeight;
    public float maskWidth;
    public int outlineColor;
    private Drawable outsideDrawable;
    private final Paint paint;
    public int progressColor;
    private RectF rect;
    public int themeId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase$setBounds$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ BatteryBarDrawableBase this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.invalidateSelf();
        }
    }

    public BatteryBarDrawableBase(Context context, int i) {
        super(context, i);
        this.context = context;
        this.lastLevel = -1;
        Paint paint = new Paint(3);
        this.paint = paint;
        Paint paint2 = new Paint();
        this.clearPaint = paint2;
        this.rect = new RectF();
        Paint paint3 = new Paint(1);
        this.chargePaint = paint3;
        paint.setAntiAlias(true);
        paint3.setStrokeCap(Paint.Cap.BUTT);
        paint3.setDither(true);
        paint3.setStrokeWidth(0.0f);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable;
        if (getBatteryLevel() == -1) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "draw batteryLevel -1");
            return;
        }
        if (this.rect.width() <= 0.0f || this.rect.height() <= 0.0f) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "width and height must be > 0");
            return;
        }
        int iSaveLayer = canvas.saveLayer(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, null);
        canvas.drawPaint(this.clearPaint);
        canvas.save();
        if (isRTLLayout()) {
            RectF rectF = this.rect;
            float fWidth = (rectF.width() / 2.0f) + rectF.left;
            RectF rectF2 = this.rect;
            canvas.scale(-1.0f, 1.0f, fWidth, (rectF2.height() / 2.0f) + rectF2.top);
        }
        Drawable drawable2 = this.outsideDrawable;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        LayerDrawable layerDrawable = this.insideDrawable;
        if (layerDrawable != null) {
            layerDrawable.draw(canvas);
        }
        if (getSpecialColor() && (drawable = this.frameDrawable) != null) {
            drawable.draw(canvas);
        }
        canvas.restore();
        drawContent(canvas, this.rect);
        canvas.restoreToCount(iSaveLayer);
    }

    public void drawChargingIcon(Canvas canvas, RectF rectF) {
        Bitmap bitmap;
        if (getChargeIconId() <= 0 || (bitmap = this.chargingBitmap) == null || bitmap.isRecycled()) {
            return;
        }
        float fWidth = ((rectF.width() - this.maskWidth) / 2.0f) + rectF.left;
        if (fWidth < 0.0f) {
            fWidth = 0.0f;
        }
        float fHeight = ((rectF.height() - this.maskHeight) / 2.0f) + rectF.top;
        float f = fHeight >= 0.0f ? fHeight : 0.0f;
        canvas.save();
        if (isRTLLayout()) {
            canvas.scale(-1.0f, 1.0f, (rectF.width() / 2.0f) + rectF.left, (rectF.height() / 2.0f) + rectF.top);
        }
        canvas.drawBitmap(bitmap, fWidth, f, this.chargePaint);
        canvas.restore();
    }

    public void drawContent(Canvas canvas, RectF rectF) {
        updatePaintXfermode(this.chargePaint);
        drawChargingIcon(canvas, rectF);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void dump(PrintWriter printWriter) {
        PrintWriter printWriter2 = printWriter;
        if (printWriter2 != null) {
            printWriter2.println("BatteryBarDrawableBase：----------------------------------------------------------------------------");
        }
        if (printWriter2 != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter2, "isThemeModeColor:", this.isThemeModeColor);
        }
        if (printWriter2 != null) {
            BiometricMessageDeferral$.ExternalSyntheticOutline0.m("themeId:", this.themeId, printWriter2);
        }
        if (printWriter2 != null) {
            boolean specialColor = getSpecialColor();
            String hexString = Integer.toHexString(this.progressColor);
            String hexString2 = Integer.toHexString(this.backgroundColor);
            String hexString3 = Integer.toHexString(this.outlineColor);
            int alpha = getAlpha();
            int batteryLevel = getBatteryLevel();
            LayerDrawable layerDrawable = this.insideDrawable;
            Integer numValueOf = layerDrawable != null ? Integer.valueOf(layerDrawable.getLevel()) : null;
            StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("specialColor:", " progressColor:", hexString, specialColor, " backgroundColor:");
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, hexString2, " outlineColor:", hexString3, " alpha:");
            RectList$.ExternalSyntheticOutline0.m(alpha, batteryLevel, " batteryLevel:", " insideDrawable level:", sbM);
            sbM.append(numValueOf);
            printWriter2.println(sbM.toString());
        }
        if (printWriter2 != null) {
            RectF rectF = this.rect;
            Rect bounds = getBounds();
            float paddingTop = getPaddingTop();
            float paddingLeft = getPaddingLeft();
            float paddingRight = getPaddingRight();
            float paddingBottom = getPaddingBottom();
            Canvas canvas = this.canvas;
            Integer numValueOf2 = canvas != null ? Integer.valueOf(canvas.getWidth()) : null;
            Canvas canvas2 = this.canvas;
            Integer numValueOf3 = canvas2 != null ? Integer.valueOf(canvas2.getHeight()) : null;
            int intrinsicHeight = getIntrinsicHeight();
            int intrinsicWidth = getIntrinsicWidth();
            Drawable drawable = this.frameDrawable;
            Rect bounds2 = drawable != null ? drawable.getBounds() : null;
            LayerDrawable layerDrawable2 = this.insideDrawable;
            Rect bounds3 = layerDrawable2 != null ? layerDrawable2.getBounds() : null;
            Drawable drawable2 = this.outsideDrawable;
            Rect bounds4 = drawable2 != null ? drawable2.getBounds() : null;
            float f = this.maskWidth;
            float f2 = this.maskHeight;
            StringBuilder sb = new StringBuilder(" rect:");
            sb.append(rectF);
            sb.append(" bounds:");
            sb.append(bounds);
            sb.append("paddingTop: ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, paddingTop, ", paddingLeft: ", paddingLeft, ", paddingRight: ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, paddingRight, ", paddingBottom: ", paddingBottom, " canvas:");
            sb.append(numValueOf2);
            sb.append(" ");
            sb.append(numValueOf3);
            sb.append(" intrinsicHeight:");
            RectList$.ExternalSyntheticOutline0.m(intrinsicHeight, intrinsicWidth, " intrinsicWidth:", " frameDrawable:", sb);
            sb.append(bounds2);
            sb.append(" inside:");
            sb.append(bounds3);
            sb.append(" outside:");
            sb.append(bounds4);
            sb.append(" maskWidth:");
            sb.append(f);
            sb.append(" maskHeight:");
            sb.append(f2);
            printWriter2 = printWriter;
            printWriter2.println(sb.toString());
        }
        if (printWriter2 != null) {
            int alpha2 = getAlpha();
            Drawable drawable3 = this.frameDrawable;
            Integer numValueOf4 = drawable3 != null ? Integer.valueOf(drawable3.getAlpha()) : null;
            Drawable drawable4 = this.outsideDrawable;
            Integer numValueOf5 = drawable4 != null ? Integer.valueOf(drawable4.getAlpha()) : null;
            LayerDrawable layerDrawable3 = this.insideDrawable;
            Integer numValueOf6 = layerDrawable3 != null ? Integer.valueOf(layerDrawable3.getAlpha()) : null;
            int alpha3 = this.paint.getAlpha();
            StringBuilder sb2 = new StringBuilder(" alpha:");
            sb2.append(alpha2);
            sb2.append(" frameDrawable alpha:");
            sb2.append(numValueOf4);
            sb2.append(" outsideDrawable alpha:");
            sb2.append(numValueOf5);
            sb2.append(" insideDrawable alpha:");
            sb2.append(numValueOf6);
            sb2.append(" paint alpha:");
            OplusLockIconView$.ExternalSyntheticOutline0.m(sb2, alpha3, printWriter2);
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public Context getContext() {
        return this.context;
    }

    public final int getFrameColorMode() {
        return this.frameColorMode;
    }

    public Drawable getFrameDrawable() {
        Drawable drawable = getContext().getDrawable(2131236693);
        if (drawable != null) {
            return drawable.mutate();
        }
        return null;
    }

    public Drawable getOutSideDrawable() {
        Drawable drawable = getContext().getDrawable(2131236692);
        if (drawable != null) {
            return drawable.mutate();
        }
        return null;
    }

    public final int getOutlineColor() {
        return this.outlineColor;
    }

    public final int getProgressColor() {
        return this.progressColor;
    }

    public Drawable getProgressDrawable() {
        Drawable drawable = getContext().getDrawable(2131236708);
        if (drawable != null) {
            return drawable.mutate();
        }
        return null;
    }

    public int getProgressLevel(int i) {
        return i * 100;
    }

    public final int getThemeId() {
        return this.themeId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void onDestroy() {
        recycleBitmap(this.chargingBitmap);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
        updateBatteryLevel();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        int i5 = i4 - i2;
        int i6 = i3 - i;
        if (this.lastHeight != i5 || this.lastWidth != i6) {
            this.lastHeight = i5;
            this.lastWidth = i6;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            scheduleSelf(anonymousClass1, 20L);
        }
        updatePadding();
        updateRect();
        updateDrawable();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public final void setColors(int i, int i2, int i3) {
        this.progressColor = i;
        this.backgroundColor = i2;
        this.outlineColor = i3;
        updateDrawableTint();
    }

    public final void setDrawableBounds(Drawable drawable, RectF rectF) {
        if (rectF.width() <= 0.0f || this.rect.height() <= 0.0f || drawable == null) {
            return;
        }
        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public final void setFrameColorMode(int i) {
        this.frameColorMode = i;
    }

    public final void setThemeId(int i) {
        this.themeId = i;
    }

    public final void setThemeModeColor(boolean z) {
        if (this.isThemeModeColor != z) {
            updateDrawable();
        }
        this.isThemeModeColor = z;
    }

    public void updateBatteryLevel() {
        if (this.lastLevel != getBatteryLevel()) {
            Drawable progressDrawable = getProgressDrawable();
            LayerDrawable layerDrawable = progressDrawable instanceof LayerDrawable ? (LayerDrawable) progressDrawable : null;
            this.insideDrawable = layerDrawable;
            if (layerDrawable != null) {
                layerDrawable.setLevel(getProgressLevel(getBatteryLevel()));
            }
            setDrawableBounds(this.insideDrawable, this.rect);
            this.lastLevel = getBatteryLevel();
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void updateChargeIcon(int i) {
        Bitmap bitmap;
        if (i <= 0) {
            return;
        }
        if (this.bitmapIconId != i || (bitmap = this.chargingBitmap) == null || bitmap == null || bitmap.isRecycled()) {
            String tag = BatteryContentDrawableBase.Companion.getTAG();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
                sbM.append("updateChargeBitmap iconId:" + i + "  bitmapIconId:" + this.bitmapIconId);
                Log.d("SystemUi--Statusbar", sbM.toString());
            }
            this.bitmapIconId = i;
            Drawable drawable = getContext().getDrawable(i);
            if (drawable != null) {
                recycleBitmap(this.chargingBitmap);
                this.chargingBitmap = OpImageUtils.drawableToBitmap(drawable);
                this.maskWidth = drawable.getIntrinsicWidth();
                this.maskHeight = drawable.getIntrinsicHeight();
            }
        }
    }

    public void updateDrawable() {
        Drawable outSideDrawable = getOutSideDrawable();
        this.outsideDrawable = outSideDrawable;
        setDrawableBounds(outSideDrawable, this.rect);
        Drawable frameDrawable = getFrameDrawable();
        this.frameDrawable = frameDrawable;
        setDrawableBounds(frameDrawable, this.rect);
        setDrawableBounds(this.insideDrawable, this.rect);
        updateDrawableTint();
    }

    public void updateDrawableTint() {
        if (!this.isThemeModeColor) {
            Drawable drawable = this.outsideDrawable;
            if (drawable != null) {
                drawable.setTint(this.backgroundColor);
            }
            LayerDrawable layerDrawable = this.insideDrawable;
            if (layerDrawable != null) {
                layerDrawable.setTint(this.progressColor);
            }
        }
        int i = this.frameColorMode;
        if (i < 0) {
            return;
        }
        int iArgb = this.outlineColor;
        if (i <= 0) {
            iArgb = Color.argb(77, Color.red(iArgb), Color.green(this.outlineColor), Color.blue(this.outlineColor));
        }
        Drawable drawable2 = this.frameDrawable;
        if (drawable2 != null) {
            drawable2.setTint(iArgb);
        }
    }

    public void updatePadding() {
        if (this.themeId <= 0 || !BatteryBarUtils.percentageInHasThirdPicture(getContext())) {
            setPaddingLeft(getContext().getResources().getDimension(2131172991));
            setPaddingTop(getContext().getResources().getDimension(2131172993));
            setPaddingRight(getContext().getResources().getDimension(2131172992));
            setPaddingBottom(getContext().getResources().getDimension(2131172990));
            return;
        }
        setPaddingLeft(((Number) BatteryBarUtils.getThirdThemePicPadding(getContext(), 2131236692).getFirst()).floatValue());
        setPaddingRight(getPaddingLeft());
        setPaddingTop(((Number) BatteryBarUtils.getThirdThemePicPadding(getContext(), 2131236692).getSecond()).floatValue());
        setPaddingBottom(getPaddingTop());
    }

    public final void updatePaintXfermode(Paint paint) {
        if (!getSpecialColor()) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            paint.setColor(getContext().getColor(2131099902));
        }
    }

    public void updateRect() {
        RectF rectF = new RectF(getBounds());
        this.rect = rectF;
        rectF.top = getPaddingTop() + rectF.top;
        this.rect.bottom -= getPaddingBottom();
        RectF rectF2 = this.rect;
        rectF2.left = getPaddingLeft() + rectF2.left;
        this.rect.right -= getPaddingRight();
    }
}
