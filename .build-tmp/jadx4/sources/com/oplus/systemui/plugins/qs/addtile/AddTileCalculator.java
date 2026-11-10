package com.oplus.systemui.plugins.qs.addtile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.carousel.KeylinesKt$;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import kotlin.ranges.RangesKt___RangesKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AddTileCalculator {
    public static final AddTileCalculator INSTANCE = new AddTileCalculator();
    public static int addSplitPanelMargin = 0;
    public static float addSplitPanelScale = 1.0f;
    public static int addSplitPanelWidth = 0;
    public static int marginEnd = 0;
    public static int marginStart = 0;
    public static float scale = 1.0f;
    public static int width = -1;

    public static Drawable getPreviewDrawable(int i, int i2, Context context) {
        if (QsColorUtil.isAddDialogLowGaussianColor(context) && !OplusDarkModeUtil.isNightMode(context)) {
            i = i2;
        }
        Drawable drawable = context.getDrawable(i);
        if (drawable != null) {
            return drawable;
        }
        Path$.ExternalSyntheticBUOutline0.m$1(ListPopupWindow$.ExternalSyntheticOutline0.m(i, "preview drawable not found: "));
        return null;
    }

    public final void calculate(Context context, CellAreaProvider cellAreaProvider) {
        if (!OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            addSplitPanelWidth = cellAreaProvider.getCellAreaConfig().getWidth();
            addSplitPanelMargin = cellAreaProvider.getCellAreaConfig().getMinMargin();
            addSplitPanelScale = 1.0f;
            width = -1;
            marginStart = 0;
            marginEnd = 0;
            scale = 1.0f;
            return;
        }
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        int cellWidth = companion.getInstance(context).getCellSize(true).getCellWidth() * 2;
        addSplitPanelMargin = companion.lockDpi(context, 2131171767);
        float f = cellWidth;
        if ((cellAreaProvider.getCellAreaConfig().getWidth() / 2.0f) - f < addSplitPanelMargin * 2.0f) {
            addSplitPanelScale = KeylinesKt$.ExternalSyntheticOutline0.m(addSplitPanelMargin, 2.0f, cellAreaProvider.getCellAreaConfig().getWidth() / 2.0f, f);
        } else {
            addSplitPanelMargin = RangesKt___RangesKt.coerceIn((int) (((cellAreaProvider.getCellAreaConfig().getWidth() / 2.0f) - f) / 2.0f), companion.lockDpi(context, 2131171767), companion.lockDpi(context, 2131171766));
            addSplitPanelScale = 1.0f;
        }
        addSplitPanelWidth = (int) ((f * addSplitPanelScale) + (addSplitPanelMargin * 2.0f));
        marginStart = companion.lockDpi(context, 2131171524);
        marginEnd = addSplitPanelWidth;
        int width2 = (cellAreaProvider.getCellAreaConfig().getWidth() - marginStart) - marginEnd;
        int iLockDpi = companion.lockDpi(context, 2131171523);
        if (width2 > iLockDpi) {
            width2 = iLockDpi;
        }
        width = width2;
        float fLockDpi = (width2 / companion.lockDpi(context, 2131171513)) / 4.0f;
        scale = fLockDpi <= 1.0f ? fLockDpi : 1.0f;
    }

    public final int getAddSplitPanelMargin() {
        return addSplitPanelMargin;
    }

    public final float getAddSplitPanelScale() {
        return addSplitPanelScale;
    }

    public final int getAddSplitPanelWidth() {
        return addSplitPanelWidth;
    }

    public final Drawable getBrightnessVolumePreviewDrawable(Context context) {
        return getPreviewDrawable(2131236006, 2131236007, context);
    }

    public final Drawable getDevicePreviewDrawable(Context context) {
        Drawable drawable = context.getDrawable(2131236011);
        if (drawable != null) {
            return drawable;
        }
        Path$.ExternalSyntheticBUOutline0.m("preview drawable not found: oplus_qs_device_preview");
        return null;
    }

    public final int getMarginEnd() {
        return marginEnd;
    }

    public final int getMarginStart() {
        return marginStart;
    }

    public final Drawable getMediaPreviewDrawable(Context context) {
        return getPreviewDrawable(2131236021, 2131236022, context);
    }

    public final float getScale() {
        return scale;
    }

    public final int getWidth() {
        return width;
    }
}
