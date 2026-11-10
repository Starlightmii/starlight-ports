package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BatteryContentDrawableBase extends Drawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BatteryContentDrawableBase";
    public int batteryLevel;
    public int chargeIconId;
    private final Context mContext;
    public float paddingBottom;
    public float paddingLeft;
    public float paddingRight;
    public float paddingTop;
    public boolean specialColor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getTAG() {
            return BatteryContentDrawableBase.TAG;
        }
    }

    public BatteryContentDrawableBase(Context context, int i) {
        this.mContext = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
    }

    public void dump(PrintWriter printWriter) {
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public final int getChargeIconId() {
        return this.chargeIconId;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public final float getPaddingBottom() {
        return this.paddingBottom;
    }

    public final float getPaddingLeft() {
        return this.paddingLeft;
    }

    public final float getPaddingRight() {
        return this.paddingRight;
    }

    public final float getPaddingTop() {
        return this.paddingTop;
    }

    public boolean getSpecialColor() {
        return this.specialColor;
    }

    public final boolean isRTLLayout() {
        return getLayoutDirection() == 1;
    }

    public void onDestroy() {
    }

    public final void recycleBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        invalidateSelf();
    }

    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
    }

    public final void setChargeIconId(int i) {
        this.chargeIconId = i;
        updateChargeIcon(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setColors(int i, int i2, int i3) {
    }

    public final void setPaddingBottom(float f) {
        this.paddingBottom = f;
    }

    public final void setPaddingLeft(float f) {
        this.paddingLeft = f;
    }

    public final void setPaddingRight(float f) {
        this.paddingRight = f;
    }

    public final void setPaddingTop(float f) {
        this.paddingTop = f;
    }

    public void setSpecialColor(boolean z) {
        this.specialColor = z;
    }

    public void updateChargeIcon(int i) {
    }
}
