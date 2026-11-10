package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.statusbar.phone.OplusStatusBarIcon;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatIconView extends StatusBarIconView implements ConfigurationController.ConfigurationListener, Dumpable, CoordAnimIcon {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatIconView";
    public float darkIntensity;
    private Drawable foreDrawable;
    public int iconTint;
    public int layoutLeft;
    private final ArrayList<Rect> tintAreas;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatIconView(Context context, String str, boolean z) {
        super(context, str, (StatusBarNotification) null, z);
        this.tintAreas = new ArrayList<>();
        this.iconTint = -1;
        this.layoutLeft = -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("StatIconView state: ");
        printWriter.println("  view: " + this);
        printWriter.println("  slot: " + getSlot());
        printWriter.println("  vis: " + getVisibility());
        printWriter.println("  location: " + getLocationOnScreen()[0]);
        printWriter.println("  width: " + getWidth());
        printWriter.println("  alpha: " + getAlpha());
        printWriter.println("  scale: " + getScaleX());
        printWriter.println("  transitionX: " + getTranslationX());
        printWriter.println("  transXForCoord: " + getTransXForCoord());
        Object tag = getTag(2131365374);
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        if (statusIconState != null) {
            statusIconState.dump(printWriter, strArr);
        }
    }

    public /* bridge */ /* synthetic */ int getSlotTag() {
        return super/*com.android.systemui.statusbar.StatusIconDisplayable*/.getSlotTag();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        this.iconTint = i;
        this.darkIntensity = f;
        this.tintAreas.clear();
        this.tintAreas.addAll(arrayList);
        super.onDarkChanged(arrayList, f, i);
    }

    public final void onDensityOrFontScaleChanged() {
        reloadDimens();
        updateDrawable(true);
        maybeUpdateIconScaleDimens();
    }

    public void onDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            onDrawOriginal(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        onDrawOriginal(canvas);
        canvas.restore();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDrawOriginal(Canvas canvas) {
        if (getIconAppearAmount() == 0.0f) {
            super.onDraw(canvas);
            return;
        }
        Drawable drawable = this.foreDrawable;
        if (drawable != null) {
            int saveCount = canvas.getSaveCount();
            canvas.save();
            if (getImageMatrix() != null) {
                canvas.concat(getImageMatrix());
            }
            drawable.setBounds(getDrawable().copyBounds());
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
        super.onDraw(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int[] locationOnScreen = getLocationOnScreen();
        if (locationOnScreen != null) {
            if (!(locationOnScreen.length == 0)) {
                int i5 = this.layoutLeft;
                int i6 = locationOnScreen[0];
                if (i5 != i6) {
                    this.layoutLeft = i6;
                    super.onDarkChanged((ArrayList) CollectionsKt___CollectionsKt.toMutableList(this.tintAreas), this.darkIntensity, this.iconTint);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onMovedToDisplay(int i, Configuration configuration) {
        super/*android.widget.ImageView*/.onMovedToDisplay(i, configuration);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean set(StatusBarIcon statusBarIcon) {
        int foreDrawableResId = statusBarIcon instanceof OplusStatusBarIcon ? ((OplusStatusBarIcon) statusBarIcon).getForeDrawableResId() : -1;
        if (foreDrawableResId != (getStatusBarIcon() instanceof OplusStatusBarIcon ? ((OplusStatusBarIcon) getStatusBarIcon()).getForeDrawableResId() : -1)) {
            this.foreDrawable = foreDrawableResId != -1 ? getContext().getDrawable(foreDrawableResId) : null;
            invalidate();
        }
        return super.set(statusBarIcon);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }
}
