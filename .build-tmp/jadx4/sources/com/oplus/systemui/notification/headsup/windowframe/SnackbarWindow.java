package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SnackbarWindow {
    private static final Companion Companion = new Companion();
    private static final String TAG = "SnackbarWindow";
    private final Context context;
    private final Lazy windowManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static WindowManager $r8$lambda$iTp9m25fuoTazmNJIFt6Q2o3f_c(SnackbarWindow snackbarWindow) {
        return (WindowManager) snackbarWindow.context.getSystemService("window");
    }

    public SnackbarWindow(Context context) {
        this.context = context;
        SnackbarWindow$$ExternalSyntheticLambda0 snackbarWindow$$ExternalSyntheticLambda0 = new SnackbarWindow$$ExternalSyntheticLambda0();
        snackbarWindow$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowManager$delegate = LazyKt__LazyJVMKt.lazy(snackbarWindow$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    public final void show(String str, String str2, int i, View.OnClickListener onClickListener) {
        FrameLayout frameLayout = new FrameLayout(OplusThemeApplyUtil.toAppContextTheme(this.context));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2017, 40, -3);
        layoutParams.setTitle(TAG);
        layoutParams.setFitInsetsIgnoringVisibility(true);
        COUISnackBar cOUISnackBarMake = COUISnackBar.make(frameLayout, str);
        cOUISnackBarMake.setFocusableInTouchMode(false);
        cOUISnackBarMake.setOnAction(str2, onClickListener);
        cOUISnackBarMake.setDuration(i);
        SnackbarWindow$show$1$1 snackbarWindow$show$1$1 = new SnackbarWindow$show$1$1();
        snackbarWindow$show$1$1.this$0 = this;
        snackbarWindow$show$1$1.$windowView = frameLayout;
        VarHandle.storeStoreFence();
        cOUISnackBarMake.setOnStatusChangeListener(snackbarWindow$show$1$1);
        SnackbarWindow$show$1$2 snackbarWindow$show$1$2 = new SnackbarWindow$show$1$2();
        snackbarWindow$show$1$2.this$0 = this;
        snackbarWindow$show$1$2.$windowView = frameLayout;
        snackbarWindow$show$1$2.$this_apply = cOUISnackBarMake;
        VarHandle.storeStoreFence();
        cOUISnackBarMake.addOnLayoutChangeListener(snackbarWindow$show$1$2);
        cOUISnackBarMake.show();
        if (frameLayout.getParent() == null) {
            getWindowManager().addView(frameLayout, layoutParams);
        }
    }
}
