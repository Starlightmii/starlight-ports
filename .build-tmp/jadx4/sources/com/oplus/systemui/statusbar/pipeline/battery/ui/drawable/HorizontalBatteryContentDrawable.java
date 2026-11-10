package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.compose.ui.text.font.FontWeight;
import com.android.keyguard.OplusLockIconView$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.pipeline.battery.utils.BatteryBarUtils;
import com.oplus.systemui.statusbar.util.OpImageUtils;
import com.oplus.systemui.statusbar.util.StatusBarHelper;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.text.NumberFormat;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt___RangesKt;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HorizontalBatteryContentDrawable extends BatteryBarDrawableBase implements SystemTypefaceHelper.SystemUIFontCallback {
    public float advanceWidth;
    public int batteryOutChargeBgMode;
    private Drawable bgDrawable;
    private final RectF bodyRectF;
    private Bitmap chargingBgBitmap;
    private Drawable chargingBgDrawable;
    public int chargingBgDrawableId;
    private final Rect chargingBgDstRect;
    public int chargingBgIconId;
    private final Paint chargingBgPaint;
    private Drawable chargingDrawable;
    private final Paint clearPaint;
    private final Rect contentPixelRect;
    private final Context context;
    private final Rect debugBodyInclusiveRect;
    private final Rect debugContentInclusiveRect;
    private final Rect debugScratchRect;
    private final RectF debugSnapRectF;
    private final Rect inkInclusiveRect;
    public boolean isDefaultTypeface;
    public boolean isPercentOutBgMode;
    public boolean isPercentOutPbMode;
    public boolean isShowPercentIn;
    public int lastLevel;
    public float layoutBaselineY;
    public float layoutTextX;
    private String levelString;
    private final Path measurePath;
    private RectF noPercentRectF;
    private final Lazy offsetX$delegate;
    public float outPaddingBottom;
    public float outPaddingLeft;
    public float outPaddingRight;
    public float outPaddingTop;
    private final Paint percentInPaint;
    private LayerDrawable progressDrawable;
    public float textBiasX;
    private final RectF textBoundsF;
    public int textPixelH;
    public int textPixelW;
    private final Rect textRect;
    public static final Companion Companion = new Companion();
    private static final String TAG = "HorizontalBatteryContentDrawable";
    public static final boolean DEBUG = Log.isLoggable(TAG, 3);

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.HorizontalBatteryContentDrawable$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            int iIntValue = ((Number) obj).intValue();
            SystemTypefaceHelper systemTypefaceHelper = (SystemTypefaceHelper) ((CallableReference) this).receiver;
            systemTypefaceHelper.getClass();
            return Integer.valueOf((OplusFontUtils.isFlipFontUsed || systemTypefaceHelper.isRobotoStatusTypeface()) ? FontWeight.Companion.getBold().getWeight() : SystemTypefaceHelper.mapPrecise(iIntValue, 550, 682, 799));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public HorizontalBatteryContentDrawable(Context context, int i) {
        super(context, i);
        this.context = context;
        Paint paint = new Paint(1);
        this.percentInPaint = paint;
        this.textRect = new Rect();
        this.textBoundsF = new RectF();
        this.measurePath = new Path();
        this.levelString = "100";
        HorizontalBatteryContentDrawable$$ExternalSyntheticLambda0 horizontalBatteryContentDrawable$$ExternalSyntheticLambda0 = new HorizontalBatteryContentDrawable$$ExternalSyntheticLambda0();
        horizontalBatteryContentDrawable$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.offsetX$delegate = LazyKt__LazyJVMKt.lazy(horizontalBatteryContentDrawable$$ExternalSyntheticLambda0);
        this.bodyRectF = new RectF();
        this.contentPixelRect = new Rect();
        this.inkInclusiveRect = new Rect();
        this.debugSnapRectF = new RectF();
        this.debugContentInclusiveRect = new Rect();
        this.debugBodyInclusiveRect = new Rect();
        this.debugScratchRect = new Rect();
        Paint paint2 = new Paint(1);
        this.chargingBgPaint = paint2;
        this.noPercentRectF = new RectF();
        this.chargingBgDstRect = new Rect();
        Paint paint3 = new Paint();
        this.clearPaint = paint3;
        Paint.Cap cap = Paint.Cap.BUTT;
        paint.setStrokeCap(cap);
        paint.setDither(true);
        paint.setStrokeWidth(0.0f);
        Paint.Style style = Paint.Style.FILL_AND_STROKE;
        paint.setStyle(style);
        PorterDuff.Mode mode = PorterDuff.Mode.DST_OUT;
        paint.setXfermode(new PorterDuffXfermode(mode));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(getContext().getResources().getDimension(2131165617));
        paint2.setStrokeCap(cap);
        paint2.setDither(true);
        paint2.setStrokeWidth(0.0f);
        paint2.setStyle(style);
        paint2.setXfermode(new PorterDuffXfermode(mode));
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(getContext()).addCallback(this, new AnonymousClass1(1, companion.getInstance(getContext()), SystemTypefaceHelper.class, "mapBatteryInStatusBarIconWeight", "mapBatteryInStatusBarIconWeight(I)I", 0));
    }

    public static Paint debugFillPaint(int i) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i);
        paint.setAntiAlias(false);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        return paint;
    }

    public static Paint debugStrokePaint(int i) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        paint.setColor(i);
        paint.setAntiAlias(false);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        return paint;
    }

    private final float getOffsetX() {
        return ((Number) this.offsetX$delegate.getValue()).floatValue();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase, com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.isShowPercentIn) {
            super.draw(canvas);
            return;
        }
        int iSaveLayer = canvas.saveLayer(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, null);
        RectF rectF = this.noPercentRectF;
        if (getBatteryLevel() == -1) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "draw batteryLevel -1");
        } else if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "width and height must be > 0");
        } else {
            canvas.drawPaint(this.clearPaint);
            canvas.save();
            if (isRTLLayout()) {
                canvas.scale(-1.0f, 1.0f, (rectF.width() / 2.0f) + rectF.left, (rectF.height() / 2.0f) + rectF.top);
            }
            Drawable drawable = this.bgDrawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            LayerDrawable layerDrawable = this.progressDrawable;
            if (layerDrawable != null) {
                layerDrawable.draw(canvas);
            }
            if (getChargeIconId() > 0) {
                Bitmap bitmap = this.chargingBgBitmap;
                if (bitmap != null && !bitmap.isRecycled() && this.batteryOutChargeBgMode <= 0) {
                    this.chargingBgDstRect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                    canvas.drawBitmap(bitmap, (Rect) null, this.chargingBgDstRect, this.chargingBgPaint);
                }
                Drawable drawable2 = this.chargingDrawable;
                if (drawable2 != null) {
                    drawable2.draw(canvas);
                }
            }
            canvas.restore();
        }
        canvas.restoreToCount(iSaveLayer);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public void drawContent(Canvas canvas, RectF rectF) {
        PorterDuffXfermode porterDuffXfermode;
        float f;
        float f2;
        int i;
        if (this.isShowPercentIn) {
            Paint paint = this.percentInPaint;
            if (getSpecialColor()) {
                this.percentInPaint.setColor(AodData.getInstance(getContext()).isPanoramicAodInShow() ? getContext().getColor(2131103154) : getContext().getColor(2131099902));
                porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
            } else if (canvas.isHighContrastTextEnabled()) {
                this.percentInPaint.setColor(getOutlineColor());
                porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
            } else {
                porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
            }
            paint.setXfermode(porterDuffXfermode);
            if (this.textPixelW <= 0 || this.textPixelH <= 0) {
                return;
            }
            RectF bodyRectForLayout = getBodyRectForLayout(rectF);
            int i2 = (int) bodyRectForLayout.left;
            int i3 = (int) bodyRectForLayout.top;
            int i4 = ((int) bodyRectForLayout.right) - i2;
            int i5 = ((int) bodyRectForLayout.bottom) - i3;
            int iFloor = (int) Math.floor(this.textBoundsF.left);
            int iFloor2 = (int) Math.floor(this.textBoundsF.top);
            int i6 = this.textPixelW;
            if (i4 <= i6 || i5 <= (i = this.textPixelH)) {
                this.layoutTextX = MathKt.roundToInt(rectF.centerX() - (isRTLLayout() ? -getOffsetX() : getOffsetX())) - this.textBiasX;
                this.layoutBaselineY = MathKt.roundToInt(rectF.centerY() - this.textBoundsF.centerY());
                Paint.Align textAlign = this.percentInPaint.getTextAlign();
                int i7 = textAlign == null ? -1 : WhenMappings.$EnumSwitchMapping$0[textAlign.ordinal()];
                float f3 = this.layoutTextX;
                if (i7 != 1) {
                    if (i7 == 2) {
                        f = this.advanceWidth;
                    }
                    int iFloor3 = (int) Math.floor(f3 + this.textBoundsF.left);
                    int iFloor4 = (int) Math.floor(this.layoutBaselineY + this.textBoundsF.top);
                    this.inkInclusiveRect.set(iFloor3, iFloor4, (this.textPixelW + iFloor3) - 1, (this.textPixelH + iFloor4) - 1);
                } else {
                    f = this.advanceWidth / 2.0f;
                }
                f3 -= f;
                int iFloor5 = (int) Math.floor(f3 + this.textBoundsF.left);
                int iFloor6 = (int) Math.floor(this.layoutBaselineY + this.textBoundsF.top);
                this.inkInclusiveRect.set(iFloor5, iFloor6, (this.textPixelW + iFloor5) - 1, (this.textPixelH + iFloor6) - 1);
            } else {
                int i8 = i2 + ((i4 - i6) / 2);
                int i9 = i3 + ((i5 - i) / 2);
                this.layoutBaselineY = i9 - iFloor2;
                this.layoutTextX = (this.advanceWidth / 2.0f) + (i8 - iFloor);
                this.inkInclusiveRect.set(i8, i9, (i6 + i8) - 1, (i + i9) - 1);
            }
            boolean z = DEBUG;
            if (z) {
                Paint.FontMetrics fontMetrics = this.percentInPaint.getFontMetrics();
                float fCenterX = this.textBoundsF.centerX() + (this.layoutTextX - (this.advanceWidth / 2.0f));
                float fCenterY = this.textBoundsF.centerY() + this.layoutBaselineY;
                RectF bodyRectForLayout2 = getBodyRectForLayout(rectF);
                int i10 = (int) bodyRectForLayout2.left;
                int i11 = (int) bodyRectForLayout2.top;
                int i12 = (int) bodyRectForLayout2.right;
                int i13 = (int) bodyRectForLayout2.bottom;
                Rect rect = this.inkInclusiveRect;
                int i14 = rect.top - i11;
                int i15 = (i13 - 1) - rect.bottom;
                f2 = 2.0f;
                int i16 = rect.left - i10;
                int i17 = (i12 - 1) - rect.right;
                float f4 = ((i10 + i12) - 1) / 2.0f;
                float f5 = ((i11 + i13) - 1) / 2.0f;
                String str = this.levelString;
                int i18 = this.textPixelW;
                int i19 = this.textPixelH;
                RectF rectF2 = this.textBoundsF;
                Rect rect2 = this.textRect;
                float f6 = this.advanceWidth;
                float f7 = this.layoutTextX;
                float f8 = this.layoutBaselineY;
                float f9 = fontMetrics.ascent;
                float f10 = fontMetrics.descent;
                float f11 = fontMetrics.top;
                float f12 = fontMetrics.bottom;
                StringBuilder sb = new StringBuilder("drawText levelString:'");
                sb.append(str);
                sb.append("' rect:");
                sb.append(rectF);
                sb.append(" bodyRect:");
                sb.append(bodyRectForLayout2);
                sb.append(" gap(L,T,R,B):(");
                sb.append(i16);
                sb.append(OplusPreventModeData.SEPARATOR);
                RectList$.ExternalSyntheticOutline0.m(i14, i17, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, sb);
                sb.append(i15);
                sb.append(") inkInclusive:");
                sb.append(rect);
                sb.append(" inkSize:(");
                RectList$.ExternalSyntheticOutline0.m(i18, i19, OplusPreventModeData.SEPARATOR, ") textBoundsF:", sb);
                sb.append(rectF2);
                sb.append(" textRect:");
                sb.append(rect2);
                sb.append(" advanceWidth:");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f6, " textX:", f7, " baselineY:");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f8, " visualCenter:(", fCenterX, ", ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, fCenterY, ") idealCenter:(", f4, ", ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f5, ") delta:(", fCenterX - f4, ", ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, fCenterY - f5, ") fontMetrics: ascent:", f9, " descent:");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f10, " top:", f11, " bottom:");
                sb.append(f12);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sb.toString(), null, 8);
            } else {
                f2 = 2.0f;
            }
            canvas.drawText(this.levelString, this.layoutTextX, this.layoutBaselineY, this.percentInPaint);
            if (z == 0 || !z) {
                return;
            }
            this.debugSnapRectF.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            RectF rectF3 = this.debugSnapRectF;
            Rect rect3 = this.debugContentInclusiveRect;
            int i20 = (int) rectF3.left;
            int i21 = (int) rectF3.top;
            rect3.set(i20, i21, Math.max(i20, ((int) rectF3.right) - 1), Math.max(i21, ((int) rectF3.bottom) - 1));
            RectF bodyRectForLayout3 = getBodyRectForLayout(rectF);
            Rect rect4 = this.debugBodyInclusiveRect;
            int i22 = (int) bodyRectForLayout3.left;
            int i23 = (int) bodyRectForLayout3.top;
            rect4.set(i22, i23, Math.max(i22, ((int) bodyRectForLayout3.right) - 1), Math.max(i23, ((int) bodyRectForLayout3.bottom) - 1));
            int i24 = rect3.top;
            rect4.top = i24;
            int i25 = rect3.bottom;
            rect4.bottom = i25;
            Rect rect5 = this.inkInclusiveRect;
            float f13 = (i24 + i25) / f2;
            float f14 = (rect5.top + rect5.bottom) / f2;
            float f15 = (rect4.left + rect4.right) / f2;
            float f16 = (rect5.left + rect5.right) / f2;
            drawDebugCornerMarks(canvas, rect3, debugFillPaint(-65536), 6.0f);
            drawDebugCornerMarks(canvas, rect4, debugFillPaint(-16711936), 4.0f);
            drawDebugCornerMarks(canvas, rect5, debugFillPaint(-16711681), f2);
            Paint paintDebugStrokePaint = debugStrokePaint(-256);
            Paint paintDebugStrokePaint2 = debugStrokePaint(-65281);
            Paint paintDebugStrokePaint3 = debugStrokePaint(-16776961);
            Paint paintDebugStrokePaint4 = debugStrokePaint(Color.parseColor("#FFAA00FF"));
            canvas.drawLine(f15, rect4.top, f15, rect4.bottom, paintDebugStrokePaint);
            canvas.drawLine(f16, rect5.top, f16, rect5.bottom, paintDebugStrokePaint2);
            canvas.drawLine(rect4.left, f13, rect4.right, f13, paintDebugStrokePaint3);
            canvas.drawLine(rect5.left, f14, rect5.right, f14, paintDebugStrokePaint4);
        }
    }

    public final void drawDebugCornerMarks(Canvas canvas, Rect rect, Paint paint, float f) {
        int iMin = Math.min((int) f, Math.min((rect.width() + 1) / 2, (rect.height() + 1) / 2));
        if (iMin <= 0) {
            return;
        }
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        int i5 = i + iMin;
        int i6 = i2 + 1;
        this.debugScratchRect.set(i, i2, i5, i6);
        canvas.drawRect(this.debugScratchRect, paint);
        int i7 = i + 1;
        int i8 = i2 + iMin;
        this.debugScratchRect.set(i, i2, i7, i8);
        canvas.drawRect(this.debugScratchRect, paint);
        int i9 = (i3 - iMin) + 1;
        int i10 = i3 + 1;
        this.debugScratchRect.set(i9, i2, i10, i6);
        canvas.drawRect(this.debugScratchRect, paint);
        this.debugScratchRect.set(i3, i2, i10, i8);
        canvas.drawRect(this.debugScratchRect, paint);
        int i11 = i4 + 1;
        this.debugScratchRect.set(i, i4, i5, i11);
        canvas.drawRect(this.debugScratchRect, paint);
        int i12 = (i4 - iMin) + 1;
        this.debugScratchRect.set(i, i12, i7, i11);
        canvas.drawRect(this.debugScratchRect, paint);
        this.debugScratchRect.set(i9, i4, i10, i11);
        canvas.drawRect(this.debugScratchRect, paint);
        this.debugScratchRect.set(i3, i12, i10, i11);
        canvas.drawRect(this.debugScratchRect, paint);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase, com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void dump(PrintWriter printWriter) {
        super.dump(printWriter);
        if (printWriter != null) {
            printWriter.println("HorizontalBatteryContentDrawable：----------------------------------------------------------------------------");
        }
        if (printWriter != null) {
            printWriter.println("rect:" + this.noPercentRectF);
        }
        if (printWriter != null) {
            printWriter.println("outPaddingLeft: " + this.outPaddingLeft + ", outPaddingRight: " + this.outPaddingRight);
        }
        if (printWriter != null) {
            printWriter.println("outPaddingTop: " + this.outPaddingTop + ", outPaddingBottom: " + this.outPaddingBottom);
        }
        if (printWriter != null) {
            printWriter.println("typeface:" + this.percentInPaint.getTypeface() + " isDefaultTypeface:" + this.isDefaultTypeface + " isShowPercentIn:" + this.isShowPercentIn + " ");
        }
        Paint.FontMetricsInt fontMetricsInt = this.percentInPaint.getFontMetricsInt();
        if (printWriter != null) {
            printWriter.println(MutableVectorKt$.ExternalSyntheticOutline0.m("ascent:", fontMetricsInt.ascent, fontMetricsInt.descent, "  descent:", " "));
        }
        if (printWriter != null) {
            int i = fontMetricsInt.top;
            int i2 = fontMetricsInt.bottom;
            Rect rect = this.textRect;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("top:", i, i2, " bottom:", "  textRect: ");
            sbM.append(rect);
            printWriter.println(sbM.toString());
        }
        if (printWriter != null) {
            printWriter.println("textRect: " + this.textRect + "  isDefaultTypeface:" + this.isDefaultTypeface);
        }
        if (printWriter != null) {
            Drawable drawable = this.bgDrawable;
            Rect bounds = drawable != null ? drawable.getBounds() : null;
            LayerDrawable layerDrawable = this.progressDrawable;
            Rect bounds2 = layerDrawable != null ? layerDrawable.getBounds() : null;
            LayerDrawable layerDrawable2 = this.progressDrawable;
            printWriter.println("bgDrawable:" + drawable + " bounds:" + bounds + " progress:" + layerDrawable + " bounds:" + bounds2 + " progress level:" + (layerDrawable2 != null ? Integer.valueOf(layerDrawable2.getLevel()) : null));
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isPercentOutThemeMode: ", this.isPercentOutBgMode);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isPercentOutPbMode: ", this.isPercentOutBgMode);
        }
        if (printWriter != null) {
            Drawable drawable2 = this.chargingDrawable;
            Rect bounds3 = drawable2 != null ? drawable2.getBounds() : null;
            printWriter.println("chargingDrawable: " + drawable2 + " bounds:" + bounds3 + " chargingBgBitmap: " + this.chargingBgBitmap + " chargeIconId: " + Integer.toHexString(getChargeIconId()) + " chargingBgIconLeft:0.0 chargingBgIconTop:0.0 batteryOutChargeBgMode: " + this.batteryOutChargeBgMode + " ");
        }
    }

    public final int getBatteryOutChargeBgMode() {
        return this.batteryOutChargeBgMode;
    }

    public final RectF getBodyRectForLayout(RectF rectF) {
        this.contentPixelRect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        int iWidth = this.contentPixelRect.width();
        int iCoerceIn = 0;
        if (iWidth > 0) {
            int i = iWidth * 2;
            int i2 = (i + 12) / 25;
            if (i2 == 1 && i < 25) {
                i2 = 0;
            }
            iCoerceIn = RangesKt___RangesKt.coerceIn(i2, 0, iWidth);
        }
        int i3 = iWidth - iCoerceIn;
        boolean zIsRTLLayout = isRTLLayout();
        RectF rectF2 = this.bodyRectF;
        if (zIsRTLLayout) {
            Rect rect = this.contentPixelRect;
            int i4 = rect.right;
            rectF2.set(i4 - i3, rect.top, i4, rect.bottom);
        } else {
            Rect rect2 = this.contentPixelRect;
            int i5 = rect2.left;
            rectF2.set(i5, rect2.top, i5 + i3, rect2.bottom);
        }
        return this.bodyRectF;
    }

    public final int getChargingBgIconId() {
        return this.chargingBgIconId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public Context getContext() {
        return this.context;
    }

    public final boolean isPercentageOutUseThirdThemePicture() {
        return getThemeId() > 0 && BatteryBarUtils.percentageOutHasThirdPicture(getContext());
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase, com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public final void onDestroy() {
        SystemTypefaceHelper.Companion.getInstance(getContext()).removeCallback(this);
        super.onDestroy();
    }

    public final void setBatteryOutChargeBgMode(int i) {
        this.batteryOutChargeBgMode = i;
    }

    public final void setChargingBgIconId(int i) {
        Bitmap bitmap;
        this.chargingBgIconId = i;
        if (i <= 0) {
            return;
        }
        if (this.chargingBgDrawableId != i || (bitmap = this.chargingBgBitmap) == null || bitmap.isRecycled()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("updateChargeBgIcon iconId:", i, this.chargingBgDrawableId, "  bitmapIconId:", "HorizontalBatteryContentDrawable-->"));
            }
            this.chargingBgDrawableId = i;
            Drawable drawable = getContext().getDrawable(i);
            this.chargingBgDrawable = drawable;
            if (drawable != null) {
                recycleBitmap(this.chargingBgBitmap);
                if (this.batteryOutChargeBgMode <= 0) {
                    this.chargingBgBitmap = OpImageUtils.drawableToBitmap(this.chargingBgDrawable);
                }
            }
        }
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public void setFontTypeface(Typeface typeface, int i) {
        this.percentInPaint.setTypeface(typeface);
        this.isDefaultTypeface = StatusBarHelper.Companion.getInstance(getContext()).isDefaultTypeface();
        if (this.isShowPercentIn) {
            updatePercentInTextBounds();
        }
        invalidateSelf();
    }

    public final void setPercentOutBgMode(boolean z) {
        if (this.isPercentOutBgMode != z) {
            updateDrawable();
        }
        this.isPercentOutBgMode = z;
    }

    public final void setPercentOutPbMode(boolean z) {
        if (this.isPercentOutPbMode != z) {
            updateDrawable();
        }
        this.isPercentOutPbMode = z;
    }

    public final void setShowPercentIn(boolean z) {
        if (this.isShowPercentIn != z) {
            this.isShowPercentIn = z;
            if (z) {
                updatePercentInTextBounds();
            }
            updateDrawable();
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updateBatteryLevel() {
        this.levelString = NumberFormat.getIntegerInstance().format(getBatteryLevel());
        if (this.isShowPercentIn) {
            updatePercentInTextBounds();
            super.updateBatteryLevel();
        } else if (getBatteryLevel() != this.lastLevel) {
            this.lastLevel = getBatteryLevel();
            updateProgressDrawable();
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase, com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public final void updateChargeIcon(int i) {
        if (i == 0 || i <= 0) {
            return;
        }
        Drawable drawable = getContext().getDrawable(i);
        this.chargingDrawable = drawable;
        setDrawableBounds(drawable, this.noPercentRectF);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updateDrawable() {
        if (this.isShowPercentIn) {
            super.updateDrawable();
            return;
        }
        Drawable drawableMutate = null;
        if (isPercentageOutUseThirdThemePicture()) {
            Drawable drawable = getContext().getDrawable(2131236703);
            if (drawable != null) {
                drawableMutate = drawable.mutate();
            }
        } else {
            Drawable drawable2 = getContext().getDrawable(2131236702);
            if (drawable2 != null) {
                drawableMutate = drawable2.mutate();
            }
        }
        this.bgDrawable = drawableMutate;
        setDrawableBounds(drawableMutate, this.noPercentRectF);
        updateProgressDrawable();
        setDrawableBounds(this.progressDrawable, this.noPercentRectF);
        setDrawableBounds(this.chargingDrawable, this.noPercentRectF);
        updateDrawableTint();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updateDrawableTint() {
        LayerDrawable layerDrawable;
        Drawable drawable;
        if (this.isShowPercentIn) {
            super.updateDrawableTint();
            return;
        }
        if (!this.isPercentOutBgMode && (drawable = this.bgDrawable) != null) {
            drawable.setTint(getOutlineColor());
        }
        if (!this.isPercentOutPbMode && (layerDrawable = this.progressDrawable) != null) {
            layerDrawable.setTint(getProgressColor());
        }
        Drawable drawable2 = this.chargingDrawable;
        if (drawable2 != null) {
            drawable2.setTint(getOutlineColor());
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updatePadding() {
        super.updatePadding();
        if (!isPercentageOutUseThirdThemePicture()) {
            this.outPaddingLeft = 0.0f;
            this.outPaddingTop = 0.0f;
            this.outPaddingRight = 0.0f;
            this.outPaddingBottom = 0.0f;
            return;
        }
        float fFloatValue = ((Number) BatteryBarUtils.getThirdThemePicPadding(getContext(), 2131236703).getFirst()).floatValue();
        this.outPaddingLeft = fFloatValue;
        this.outPaddingRight = fFloatValue;
        float fFloatValue2 = ((Number) BatteryBarUtils.getThirdThemePicPadding(getContext(), 2131236703).getSecond()).floatValue();
        this.outPaddingTop = fFloatValue2;
        this.outPaddingBottom = fFloatValue2;
    }

    public final void updatePercentInTextBounds() {
        Paint paint = this.percentInPaint;
        String str = this.levelString;
        paint.getTextBounds(str, 0, str.length(), this.textRect);
        Paint.Align textAlign = this.percentInPaint.getTextAlign();
        try {
            try {
                this.percentInPaint.setTextAlign(Paint.Align.LEFT);
                this.measurePath.reset();
                Paint paint2 = this.percentInPaint;
                String str2 = this.levelString;
                paint2.getTextPath(str2, 0, str2.length(), 0.0f, 0.0f, this.measurePath);
                this.measurePath.computeBounds(this.textBoundsF, true);
            } catch (IllegalArgumentException e) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "updatePercentInTextBounds path measure failed, IllegalArgumentException fallback to textRect: " + e.getMessage());
                RectF rectF = this.textBoundsF;
                Rect rect = this.textRect;
                rectF.set(rect.left, rect.top, rect.right, rect.bottom);
            }
            this.percentInPaint.setTextAlign(textAlign);
            if (this.textBoundsF.isEmpty()) {
                RectF rectF2 = this.textBoundsF;
                Rect rect2 = this.textRect;
                rectF2.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
            }
            this.advanceWidth = this.percentInPaint.measureText(this.levelString);
            this.textBiasX = this.textBoundsF.centerX() - (this.advanceWidth / 2.0f);
            int iFloor = (int) Math.floor(this.textBoundsF.left);
            int iFloor2 = (int) Math.floor(this.textBoundsF.top);
            int iCeil = ((int) Math.ceil(this.textBoundsF.right)) - 1;
            int iCeil2 = ((int) Math.ceil(this.textBoundsF.bottom)) - 1;
            int i = (iCeil - iFloor) + 1;
            if (i < 0) {
                i = 0;
            }
            this.textPixelW = i;
            int i2 = (iCeil2 - iFloor2) + 1;
            this.textPixelH = i2 >= 0 ? i2 : 0;
        } catch (Throwable th) {
            this.percentInPaint.setTextAlign(textAlign);
            throw th;
        }
    }

    public final void updateProgressDrawable() {
        if (isPercentageOutUseThirdThemePicture()) {
            Drawable drawable = getContext().getDrawable(2131236704);
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.progressDrawable = drawableMutate instanceof LayerDrawable ? (LayerDrawable) drawableMutate : null;
        } else {
            Drawable drawable2 = getContext().getDrawable(2131236706);
            Drawable drawableMutate2 = drawable2 != null ? drawable2.mutate() : null;
            this.progressDrawable = drawableMutate2 instanceof LayerDrawable ? (LayerDrawable) drawableMutate2 : null;
        }
        LayerDrawable layerDrawable = this.progressDrawable;
        if (layerDrawable != null) {
            layerDrawable.setLevel(getBatteryLevel() * 100);
        }
        setDrawableBounds(this.progressDrawable, this.noPercentRectF);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updateRect() {
        super.updateRect();
        RectF rectF = new RectF(getBounds());
        this.noPercentRectF = rectF;
        rectF.top += this.outPaddingTop;
        rectF.bottom -= this.outPaddingBottom;
        rectF.left += this.outPaddingLeft;
        rectF.right -= this.outPaddingRight;
    }
}
