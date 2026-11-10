package com.oplus.zoom.ui.tips;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.wm.shell.R;
import com.coui.appcompat.tooltips.COUIToolTips;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.common.ZoomUtil;
import com.oplus.zoom.ui.baseview.BaseViewManager;
import com.oplus.zoom.ui.common.UiLogUtils;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TipsManager extends BaseViewManager<TipsContainerView> {
    public static final String AUTO_SHOW_HANDLE_TIP = "handle_tip";
    public static final String AUTO_SHOW_LONG_CLICK_FLEXIBLE = "flexible_long_tip";
    public static final String AUTO_SHOW_MINI_GESTURE_CLOSE_TIP = "mini_gesture_close_tip";
    public static final String AUTO_SHOW_MINI_TIP = "mini_tip";
    public static final String AUTO_SHOW_SCALE_TIP = "scale_tip";
    public static final String AUTO_SHOW_SIMPLE_MODE_TIP = "simple_mode_tip";
    public static final int DELAY_AUTO_HIDE_TIME = 5000;
    public static final int DELAY_SING_TAP_HIDE_TIME = 2000;
    public static final int DONT_HIDE = -1;
    public static final int FLAG_ANIMATE_DEFAULT = -1;
    public static final int FLAG_ANIMATE_DRAG = 10;
    public static final int FLAG_ANIMATE_FLEXIBLE_LONG_CLICK = 12;
    public static final int FLAG_ANIMATE_POINT = 11;
    private static final int HIDE_TIPS = 1;
    public static final int NO_DELAY_TIME = 0;
    public static final int POSITION_CORRECTION_VALUE_100 = 100;
    public static final int POSITION_CORRECTION_VALUE_30 = 30;
    public static final int POSITION_CORRECTION_VALUE_40 = 40;
    public static final int POSITION_CORRECTION_VALUE_50 = 50;
    public static final int POSITION_CORRECTION_VALUE_97 = 97;
    private static final int ROTATION_OF_CLOSE = 180;
    private static final String TAG = "Tips";
    private static final int TEXT_MAX_LINES = 2;
    public static final int TIPS_SHOW_TIMES_1 = 1;
    public static final int TIPS_SHOW_TIMES_2 = 2;
    public static final int TIPS_SHOW_TIMES_3 = 3;
    public static final int TIP_CHANGE_SCALE = 102;
    public static final int TIP_HANDLE_LONG_PRESS = 100;
    public static final int TIP_LONG_CLICK_FLEXIBLE = 104;
    public static final int TIP_MINI_ZOOM = 103;
    public static final int TIP_MINI_ZOOM_GESTURE_CLOSE = 105;
    public static final int TIP_SIMPLE_MODE = 101;
    private static final float WIDTH_HEIGHT_HALF = 2.0f;
    private int mAnimateFlag;
    private Context mContext;
    private float mDensity;
    private PopupWindow.OnDismissListener mDismissListener;
    private boolean mIsFlip;
    private boolean mIsFoldScreen;
    private boolean mIsShowDirection;
    private boolean mIsTabletScreen;
    private TipsHideListener mListener;
    private Executor mMainExecutor;
    private float mPositionX;
    private float mPositionY;
    private String mText;
    private COUIToolTips mTips;
    private TipsContainerView mTipsContainerView;
    private int mTipsDerection;
    private ZoomPositionInfo mZoomWindowInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TipsHideListener {
        void onTipsHide(ZoomPositionInfo zoomPositionInfo);
    }

    public TipsManager(Context context) {
        super(context);
        this.mIsShowDirection = true;
        this.mAnimateFlag = -1;
        this.mMainExecutor = null;
        TipsManager$$ExternalSyntheticLambda2 tipsManager$$ExternalSyntheticLambda2 = new TipsManager$$ExternalSyntheticLambda2();
        tipsManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.mDismissListener = tipsManager$$ExternalSyntheticLambda2;
        this.mContext = context;
        this.mMainExecutor = context.getMainExecutor();
        this.mIsTabletScreen = ZoomUtil.isTabletDevice();
        this.mIsFoldScreen = ZoomUtil.isFoldDevice();
        this.mIsFlip = ZoomUtil.isFlipDevice();
        this.mDensity = this.mContext.getResources().getDisplayMetrics().density;
    }

    private void addTipsContainerView() {
        if (!initTargetView()) {
            UiLogUtils.d("Tips", "Failed to showTipsContainerView(): initTargetView failed");
        } else {
            UiLogUtils.d("Tips", "TipsContainerView addView");
            addView();
        }
    }

    private void computeMiniGestureCloseTipPosition(EffectiveAnimationView effectiveAnimationView, Rect rect, boolean z) {
        this.mText = this.mContext.getString(R.string.zoom_guide_mini_gesture_close_tip);
        effectiveAnimationView.setAnimation(R.raw.animation_slide);
        effectiveAnimationView.setRotation(180.0f);
        this.mTipsDerection = 128;
        int i = z ? 100 : 0;
        effectiveAnimationView.setX((rect.right - (rect.width() / WIDTH_HEIGHT_HALF)) - 50.0f);
        effectiveAnimationView.setY((rect.bottom - effectiveAnimationView.getLayoutParams().height) - i);
        setPosition(rect.right - (rect.width() / WIDTH_HEIGHT_HALF), rect.bottom);
        this.mAnimateFlag = 10;
        UiLogUtils.d("Tips", "computeMiniGestureCloseTipPosition rect:" + rect);
    }

    private void computeMiniTipPosition(Rect rect) {
        this.mText = this.mContext.getString(R.string.zoom_guide_mini_mode);
        if (ZoomUtil.isRTL()) {
            if ((rect.width() / WIDTH_HEIGHT_HALF) + rect.left > ZoomDisplayController.getInstance().getScreenWidth() / WIDTH_HEIGHT_HALF) {
                this.mTipsDerection = 64;
                setPosition(rect.left, (rect.height() / WIDTH_HEIGHT_HALF) + rect.top);
                return;
            } else {
                this.mTipsDerection = 32;
                setPosition(rect.right, (rect.height() / WIDTH_HEIGHT_HALF) + rect.top);
                return;
            }
        }
        if ((rect.width() / WIDTH_HEIGHT_HALF) + rect.left > ZoomDisplayController.getInstance().getScreenWidth() / WIDTH_HEIGHT_HALF) {
            this.mTipsDerection = 32;
            setPosition(rect.left, (rect.height() / WIDTH_HEIGHT_HALF) + rect.top);
        } else {
            this.mTipsDerection = 64;
            setPosition(rect.right, (rect.height() / WIDTH_HEIGHT_HALF) + rect.top);
        }
    }

    private void computePositionForFlexible(EffectiveAnimationView effectiveAnimationView, Rect rect) {
        this.mIsShowDirection = false;
        this.mText = this.mContext.getString(R.string.flexible_longclick);
        effectiveAnimationView.setRotation(0.0f);
        int i = (int) (this.mDensity * 40.0f);
        effectiveAnimationView.getLayoutParams().width = i;
        effectiveAnimationView.getLayoutParams().height = i;
        effectiveAnimationView.setLayoutParams(effectiveAnimationView.getLayoutParams());
        this.mAnimateFlag = 12;
        effectiveAnimationView.setAnimation(R.raw.flexible_long_click);
        int i2 = i / 2;
        int iWidth = ((rect.width() / 2) + rect.left) - i2;
        int i3 = rect.top - 30;
        effectiveAnimationView.setX(iWidth);
        effectiveAnimationView.setY(i3);
        int i4 = iWidth + i2;
        int i5 = i3 - ((int) (this.mDensity * 97.0f));
        TipsContainerView tipsContainerView = this.mTarget;
        if (tipsContainerView != null && tipsContainerView.getTipsView() != null) {
            FrameLayout tipsView = this.mTarget.getTipsView();
            ViewGroup.LayoutParams layoutParams = tipsView.getLayoutParams();
            layoutParams.height = 60;
            tipsView.setLayoutParams(layoutParams);
        }
        setPosition(i4, i5);
        this.mTipsDerection = 128;
        UiLogUtils.d("Tips", "computePositionForFlexible rect:" + rect + ",drWidth:" + i + ",mTarget:" + this.mTarget);
    }

    private void computePositionForLand(Rect rect, int i) {
        EffectiveAnimationView dragAnimationViewRight = this.mTarget.getDragAnimationViewRight();
        switch (i) {
            case 100:
                this.mText = this.mContext.getString(R.string.zoom_hold_handle_tip);
                setPosition((rect.width() / 2) + rect.left, rect.top + 50);
                this.mTipsDerection = 4;
                this.mAnimateFlag = 11;
                break;
            case 101:
                this.mText = this.mContext.getString(R.string.zoom_guide_panel_guide);
                setPosition((rect.width() / 2) + rect.left, rect.top + 50);
                this.mTipsDerection = 4;
                this.mAnimateFlag = 11;
                break;
            case 102:
                this.mText = this.mContext.getString(R.string.zoom_change_scale);
                dragAnimationViewRight.setAnimation(R.raw.animation_slide);
                if ((this.mIsTabletScreen || this.mIsFoldScreen) && !this.mIsFlip) {
                    this.mTipsDerection = 32;
                    dragAnimationViewRight.setX((rect.left + dragAnimationViewRight.getLayoutParams().width) - 50);
                    dragAnimationViewRight.setY((rect.bottom - dragAnimationViewRight.getLayoutParams().height) + 50);
                    setPosition(rect.left, rect.bottom - 50);
                } else {
                    this.mTipsDerection = 64;
                    dragAnimationViewRight.setX((rect.right - dragAnimationViewRight.getLayoutParams().width) - 50);
                    dragAnimationViewRight.setY((rect.bottom - dragAnimationViewRight.getLayoutParams().height) + 50);
                    setPosition(rect.right, rect.bottom - 50);
                }
                this.mAnimateFlag = 10;
                break;
            case 103:
                computeMiniTipPosition(rect);
                break;
            case 104:
                computePositionForFlexible(dragAnimationViewRight, rect);
                break;
            case 105:
                computeMiniGestureCloseTipPosition(dragAnimationViewRight, rect, true);
                break;
        }
    }

    private void computePositionForPort(Rect rect, int i) {
        EffectiveAnimationView dragAnimationViewRight = this.mTarget.getDragAnimationViewRight();
        this.mIsShowDirection = true;
        switch (i) {
            case 100:
                this.mText = this.mContext.getString(R.string.zoom_hold_handle_tip);
                if ((this.mIsTabletScreen || this.mIsFoldScreen) && !this.mIsFlip) {
                    setPosition((rect.width() / 2) + rect.left, rect.top + 50);
                } else {
                    setPosition((rect.width() / 2) + rect.left, rect.top);
                }
                this.mTipsDerection = 4;
                this.mAnimateFlag = 11;
                break;
            case 101:
                this.mText = this.mContext.getString(R.string.zoom_guide_panel_guide);
                if ((this.mIsTabletScreen || this.mIsFoldScreen) && !this.mIsFlip) {
                    setPosition((rect.width() / 2) + rect.left, rect.top + 50);
                } else {
                    setPosition((rect.width() / 2) + rect.left, rect.top);
                }
                this.mTipsDerection = 4;
                this.mAnimateFlag = 11;
                break;
            case 102:
                this.mText = this.mContext.getString(R.string.zoom_change_scale);
                dragAnimationViewRight.setAnimation(R.raw.animation_slide);
                if ((this.mIsTabletScreen || this.mIsFoldScreen) && !this.mIsFlip) {
                    this.mTipsDerection = 32;
                    dragAnimationViewRight.setX((rect.left + dragAnimationViewRight.getLayoutParams().width) - 50);
                    dragAnimationViewRight.setY((rect.bottom - dragAnimationViewRight.getLayoutParams().height) + 50);
                    setPosition(rect.left, rect.bottom);
                } else {
                    this.mTipsDerection = 128;
                    dragAnimationViewRight.setX((rect.right - dragAnimationViewRight.getLayoutParams().width) - 50);
                    dragAnimationViewRight.setY((rect.bottom - dragAnimationViewRight.getLayoutParams().height) + 100);
                    setPosition(rect.right, rect.bottom);
                }
                this.mAnimateFlag = 10;
                break;
            case 103:
                computeMiniTipPosition(rect);
                break;
            case 104:
                computePositionForFlexible(dragAnimationViewRight, rect);
                break;
            case 105:
                computeMiniGestureCloseTipPosition(dragAnimationViewRight, rect, false);
                break;
        }
    }

    private void deleteTipsContainerView() {
        UiLogUtils.d("Tips", "TipsContainerView removeView");
        TipsContainerView tipsContainerView = this.mTarget;
        if (tipsContainerView != null && tipsContainerView.getTipsView() != null && this.mTarget.getTipsView().isAttachedToWindow()) {
            this.mTarget.getTipsView().removeAllViews();
        }
        if (this.mState == 2) {
            removeView(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$hideTips$2() {
        try {
            if (this.mTarget != 0) {
                UiLogUtils.d("Tips", "hideTips final dismiss");
                this.mTips.dismiss();
                deleteTipsContainerView();
            }
        } catch (Exception e) {
            UiLogUtils.e("Tips", "hideTips error", e);
            deleteTipsContainerView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showTips$1(ZoomPositionInfo zoomPositionInfo, int i) {
        try {
            this.mZoomWindowInfo = zoomPositionInfo;
            if (this.mTarget != 0) {
                UiLogUtils.d("Tips", "showTips, mTarget != null, removeView");
                if (this.mTips.isShowing()) {
                    return;
                }
                removeView(false);
                return;
            }
            int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
            UiLogUtils.d("Tips", "showTips,  rotation:" + rotation + " flag: " + i);
            addTipsContainerView();
            computePosition(zoomPositionInfo.getScaleRect(), rotation, i);
        } catch (Exception e) {
            UiLogUtils.e("Tips", "showTips error", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipsImpl() {
        TextView textView;
        this.mTips.setContent(this.mText);
        if (this.mTarget.getTipsView() != null) {
            this.mTips.showWithDirection(this.mTarget.getTipsView(), this.mTipsDerection, this.mIsShowDirection);
            View contentView = this.mTips.getContentView();
            if (this.mAnimateFlag == 12 && contentView != null && (textView = (TextView) contentView.findViewById(R.id.contentTv)) != null) {
                textView.setMaxLines(2);
            }
            UiLogUtils.d("Tips", "showTipsImpl");
        }
        int i = this.mAnimateFlag;
        if (i == 0) {
            this.mTarget.getDragAnimationViewRight().setVisibility(4);
            return;
        }
        if (i == 10 || i == 12) {
            this.mTarget.getDragAnimationViewRight().setVisibility(0);
            this.mTarget.getDragAnimationViewRight().playAnimation();
            UiLogUtils.d("Tips", "playAnimation, flag: " + this.mAnimateFlag);
        }
    }

    public void computePosition(Rect rect, int i, int i2) {
        if (this.mTarget == 0) {
            UiLogUtils.d("Tips", "mTarget null, computePosition error");
        } else if (i == 0 || i == 2) {
            computePositionForPort(rect, i2);
        } else {
            computePositionForLand(rect, i2);
        }
    }

    @Override // com.oplus.zoom.ui.baseview.BaseViewManager
    public TipsContainerView createView() {
        UiLogUtils.d("Tips", "TipsContainerView createView start");
        COUIToolTips cOUIToolTips = new COUIToolTips(this.mContext);
        this.mTips = cOUIToolTips;
        cOUIToolTips.setOnDismissListener(this.mDismissListener);
        TipsContainerView tipsContainerView = new TipsContainerView(this.mContext);
        this.mTipsContainerView = tipsContainerView;
        tipsContainerView.setHook(this);
        return this.mTipsContainerView;
    }

    /* JADX INFO: renamed from: hideTips, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0() {
        Executor executor = this.mMainExecutor;
        TipsManager$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new TipsManager$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda1);
    }

    public boolean isTipsShowing() {
        COUIToolTips cOUIToolTips = this.mTips;
        if (cOUIToolTips != null) {
            return cOUIToolTips.isShowing();
        }
        return false;
    }

    @Override // com.oplus.zoom.ui.baseview.BaseViewManager, com.oplus.zoom.ui.baseview.ViewHook
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TipsContainerView tipsContainerView = this.mTarget;
        if (tipsContainerView == null || tipsContainerView.getView() == null) {
            return;
        }
        this.mTarget.getView().getViewTreeObserver().addOnGlobalLayoutListener(new 1(this));
    }

    @Override // com.oplus.zoom.ui.baseview.BaseViewManager, com.oplus.zoom.ui.baseview.ViewHook
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        UiLogUtils.d("Tips", "TipsManager: onDetachedFromWindow");
        this.mAnimateFlag = -1;
        TipsHideListener tipsHideListener = this.mListener;
        if (tipsHideListener != null) {
            tipsHideListener.onTipsHide(this.mZoomWindowInfo);
        }
    }

    public void setPosition(float f, float f2) {
        this.mPositionX = f;
        this.mPositionY = f2;
        TipsContainerView tipsContainerView = this.mTarget;
        if (tipsContainerView != null) {
            tipsContainerView.getTipsView().setX(this.mPositionX);
            this.mTarget.getTipsView().setY(this.mPositionY);
        }
    }

    public void setTipsChangeListener(TipsHideListener tipsHideListener) {
        this.mListener = tipsHideListener;
    }

    public void showTips(ZoomPositionInfo zoomPositionInfo, int i) {
        Executor executor = this.mMainExecutor;
        TipsManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new TipsManager$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = zoomPositionInfo;
        externalSyntheticLambda0.f$2 = i;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda0);
    }

    public void updateZoom(ZoomPositionInfo zoomPositionInfo) {
        this.mZoomWindowInfo = zoomPositionInfo;
    }
}
