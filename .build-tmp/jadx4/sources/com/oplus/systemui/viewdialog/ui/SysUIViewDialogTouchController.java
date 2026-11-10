package com.oplus.systemui.viewdialog.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.shade.ShadeController;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUIViewDialogTouchController implements View.OnTouchListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SysUIViewDialogTouchController";
    private final SysUIViewDialogInteractor dialogInteractor;
    public float downY;
    public boolean isDownInBottomTouchRegion;
    private final ShadeController shadeController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SysUIViewDialogTouchController(SysUIViewDialogInteractor sysUIViewDialogInteractor, ShadeController shadeController) {
        this.dialogInteractor = sysUIViewDialogInteractor;
        this.shadeController = shadeController;
    }

    public static boolean isInBottomTouchRegion(float f, View view) {
        if (NavBarUtils.isGestureSideMode()) {
            return ((float) view.getContext().getResources().getDisplayMetrics().heightPixels) - f <= ((float) view.getResources().getDimensionPixelSize(2131165705));
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            this.downY = rawY;
            boolean zIsInBottomTouchRegion = isInBottomTouchRegion(rawY, view);
            this.isDownInBottomTouchRegion = zIsInBottomTouchRegion;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onTouch: isDownInBottomTouchRegion = ", zIsInBottomTouchRegion), null, 8);
        } else if (actionMasked == 1) {
            float rawY2 = this.downY - motionEvent.getRawY();
            boolean zIsInBottomTouchRegion2 = isInBottomTouchRegion(motionEvent.getRawY(), view);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onTouch: isUpInBottomTouchRegion = ", zIsInBottomTouchRegion2), null, 8);
            if (this.isDownInBottomTouchRegion && !zIsInBottomTouchRegion2 && rawY2 > ViewConfiguration.get(view.getContext()).getScaledTouchSlop()) {
                this.shadeController.animateCollapseShade(0);
                this.dialogInteractor.dismissDialog("animate");
            }
        }
        return this.isDownInBottomTouchRegion;
    }
}
