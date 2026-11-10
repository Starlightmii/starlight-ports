package com.oplus.zoom.ui.tips;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.wm.shell.R;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.transition.OplusAnimationController;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomUtil;
import com.oplus.zoom.ui.common.UiLogUtils;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SnackBarManager {
    private static final int SNACK_BAK_BOTTOM_DP = 40;
    private static final int STAY_TIME = 5000;
    private static final int STAY_TIME_BUBBLE = 2000;
    private ViewGroup mContainer;
    private Context mContext;
    private COUISnackBar.OnStatusChangeListener mListener = new COUISnackBar.OnStatusChangeListener() { // from class: com.oplus.zoom.ui.tips.SnackBarManager.1
        public void onDismissed(COUISnackBar cOUISnackBar) {
            try {
                if (SnackBarManager.this.mWm != null) {
                    SnackBarManager.this.mWm.removeView(SnackBarManager.this.mContainer);
                }
            } catch (Exception e) {
                UiLogUtils.e("onDismissed removeView error", e);
            }
        }

        public void onShown(COUISnackBar cOUISnackBar) {
        }
    };
    private COUISnackBar mSnackBar;
    private TipsController mTipController;
    private WindowManager.LayoutParams mWindowParams;
    private WindowManager mWm;

    public SnackBarManager(TipsController tipsController, Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 2132019067);
        this.mTipController = tipsController;
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        this.mContext = contextThemeWrapper;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(contextThemeWrapper).inflate(R.layout.zoom_snack_bar_layout, (ViewGroup) null, false);
        this.mContainer = viewGroup;
        this.mSnackBar = viewGroup.getChildAt(0);
        this.mWm = (WindowManager) this.mContext.getSystemService("window");
        this.mSnackBar.setOnStatusChangeListener(this.mListener);
    }

    private void initLayoutParam() {
        int iDip2px = ZoomUtil.dip2px(this.mContext, 40.0f);
        int screenHeight = ZoomDisplayController.getInstance().getScreenHeight();
        int screenWidth = ZoomDisplayController.getInstance().getScreenWidth();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        this.mWindowParams = layoutParams;
        layoutParams.flags |= 16777480;
        layoutParams.format = 1;
        layoutParams.height = -2;
        layoutParams.width = screenWidth;
        layoutParams.y = screenHeight - iDip2px;
        layoutParams.setTitle("ZoomSnackBar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSnackBar$0(View view) {
        UiLogUtils.d("onClick button");
        try {
            this.mTipController.getUiManager().requestChangeZoomState(1);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oplus.exserviceui", "com.oplusos.zoomwindow.setting.ZoomSettingActivity"));
            intent.setFlags(268435456);
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            UiLogUtils.d("showSnackBar error, e:" + e);
        }
    }

    public void dismissSnackBar() {
        COUISnackBar cOUISnackBar = this.mSnackBar;
        if (cOUISnackBar != null) {
            cOUISnackBar.dismiss();
        }
    }

    public void showSnackBar() {
        if (this.mContext.getUserId() != ActivityManager.getCurrentUser()) {
            UiLogUtils.w("not current user, don't show snack bar");
            return;
        }
        initLayoutParam();
        this.mSnackBar.setContentText(this.mContext.getResources().getString(R.string.zoom_learn_more_tip));
        this.mSnackBar.setDuration(5000);
        COUISnackBar cOUISnackBar = this.mSnackBar;
        String string = this.mContext.getResources().getString(R.string.zoom_go_learn_tip);
        SnackBarManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new SnackBarManager$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        cOUISnackBar.setOnAction(string, externalSyntheticLambda0);
        try {
            if (this.mWm != null) {
                UiLogUtils.d("createSnakeBar, addView");
                this.mWm.addView(this.mContainer, this.mWindowParams);
            }
        } catch (Exception e) {
            UiLogUtils.e("showSnackBar addView error", e);
        }
        this.mSnackBar.show();
    }

    public void showSnackBarForBubble() {
        if (this.mContext.getUserId() != ActivityManager.getCurrentUser()) {
            UiLogUtils.w("not current user, don't show snack bar");
            return;
        }
        initLayoutParam();
        this.mSnackBar.setContentText(this.mContext.getResources().getString(this.mContext.getResources().getIdentifier("mmiErrorNotSupported", "string", OplusAnimationController.PACKAGE_SYSTEM)));
        this.mSnackBar.setDuration(2000);
        try {
            if (this.mWm != null) {
                UiLogUtils.d("createSnakeBar, addView");
                this.mWm.addView(this.mContainer, this.mWindowParams);
            }
        } catch (Exception e) {
            UiLogUtils.e("showSnackBar addView error", e);
        }
        this.mSnackBar.show();
    }
}
