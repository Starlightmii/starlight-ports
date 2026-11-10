package com.oplus.systemui.statusbar.notification;

import android.content.res.Configuration;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCloseAllController$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusCloseAllController this$0;

    public final void onConfigChanged(Configuration configuration) {
        OplusCloseAllController.updatePlatformBlurDrawable$default(this.this$0);
    }

    public final void onDensityOrFontScaleChanged() {
        OplusCloseAllController oplusCloseAllController = this.this$0;
        int dimensionPixelSize = oplusCloseAllController.context.getResources().getDimensionPixelSize(2131166033);
        int dimensionPixelSize2 = oplusCloseAllController.context.getResources().getDimensionPixelSize(2131166030);
        int dimensionPixelSize3 = oplusCloseAllController.context.getResources().getDimensionPixelSize(2131166032);
        int dimensionPixelSize4 = oplusCloseAllController.context.getResources().getDimensionPixelSize(2131166031);
        AppCompatButton appCompatButton = oplusCloseAllController.oplusCloseAllButton;
        if (appCompatButton != null) {
            appCompatButton.setTextSize(0, oplusCloseAllController.context.getResources().getDimensionPixelSize(2131172948));
            ViewGroup.LayoutParams layoutParams = appCompatButton.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = dimensionPixelSize;
                layoutParams.height = dimensionPixelSize2;
                ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
                if (marginLayoutParams != null) {
                    marginLayoutParams.topMargin = dimensionPixelSize3;
                    marginLayoutParams.bottomMargin = dimensionPixelSize4;
                }
            } else {
                layoutParams = null;
            }
            appCompatButton.setLayoutParams(layoutParams);
        }
    }

    public final void onFontChanged(Configuration configuration) {
        OplusCloseAllController.access$updateFont(this.this$0);
    }

    public final void onLocaleListChanged() {
        OplusCloseAllController oplusCloseAllController = this.this$0;
        AppCompatButton appCompatButton = oplusCloseAllController.oplusCloseAllButton;
        if (appCompatButton != null) {
            appCompatButton.setText(oplusCloseAllController.context.getString(2131954268));
        }
    }
}
