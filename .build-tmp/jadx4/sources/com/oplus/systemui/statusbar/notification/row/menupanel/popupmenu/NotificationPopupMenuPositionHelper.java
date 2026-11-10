package com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.android.systemui.util.LargeScreenUtils;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuPositionHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPopupMenuPositionHelper";
    private final Context context;
    public float menuBottomMargin;
    public int menuContentWidth;
    public int menuHorizontalMargin;
    public float menuTopMargin;
    public boolean shouldShowHorizontal;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationPopupMenuPositionHelper(Context context) {
        this.context = context;
        updateResources();
    }

    public final void onConfigChanged(Configuration configuration) {
        updateResources();
    }

    public final boolean showAtAbove(Rect rect, int i) {
        return !this.shouldShowHorizontal && rect.top >= i / 2;
    }

    public final void updateAnchorPosition(ViewGroup viewGroup, View view, Rect rect) {
        Configuration configuration;
        Configuration configuration2;
        boolean zShouldUseSplitNotificationShade = LargeScreenUtils.shouldUseSplitNotificationShade(view.getResources());
        this.shouldShowHorizontal = zShouldUseSplitNotificationShade;
        if (!zShouldUseSplitNotificationShade) {
            viewGroup.addView(view, new ViewGroup.LayoutParams(1, rect.height()));
            Resources resources = view.getContext().getResources();
            view.setTranslationX((resources == null || (configuration = resources.getConfiguration()) == null || configuration.getLayoutDirection() != 1) ? rect.right : -rect.right);
            view.setTranslationY(rect.top);
            return;
        }
        viewGroup.addView(view, new ViewGroup.LayoutParams(1, 1));
        WindowManager windowManager = (WindowManager) view.getContext().getSystemService("window");
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        Insets insetsIgnoringVisibility = windowManager.getCurrentWindowMetrics().getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
        boolean z = (((float) rect.top) + this.menuBottomMargin) + this.menuTopMargin > ((float) (bounds.height() - insetsIgnoringVisibility.bottom));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationPopupMenuPositionHelper-->".concat("prepare , shouldAbove=" + z + ", notificationLocation=" + rect + ", menuBottomMargin=" + this.menuBottomMargin + ", windowBounds=" + bounds + ", inset=" + insetsIgnoringVisibility));
        }
        view.setTranslationY(z ? rect.bottom + this.menuBottomMargin : rect.top - this.menuTopMargin);
        Resources resources2 = view.getContext().getResources();
        view.setTranslationX(((resources2 == null || (configuration2 = resources2.getConfiguration()) == null || configuration2.getLayoutDirection() != 1) ? (rect.left - (this.menuContentWidth / 2)) - this.menuHorizontalMargin : (((-bounds.width()) + rect.right) + (this.menuContentWidth / 2)) + this.menuHorizontalMargin) - insetsIgnoringVisibility.left);
    }

    public final void updateResources() {
        this.menuContentWidth = this.context.getResources().getDimensionPixelOffset(2131170760);
        this.menuTopMargin = this.context.getResources().getDimension(2131167145);
        this.menuBottomMargin = this.context.getResources().getDimension(2131167143);
        this.menuHorizontalMargin = this.context.getResources().getDimensionPixelOffset(2131167144);
    }
}
