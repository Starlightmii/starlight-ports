package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureKeysModeObserver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class GestureUpGuideBarView extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Context mContext;
    public int mGapEdge;
    public final boolean mIconDark;
    public boolean mIsLayoutRtl;
    public Drawable mLeftDrawable;
    public int mMiddleButtonHeight;
    public float mMiddleButtonWidth;
    public Drawable mMiddleDrawable;
    public boolean mOnlyHomeBar;
    public Drawable mRightDrawable;
    public int mRotation;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mSideButtonHeight;
    public float mSideButtonWidth;
    public final Runnable mUpdateRunnable;

    public GestureUpGuideBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSideButtonHeight = 0;
        this.mSideButtonWidth = 0.0f;
        this.mMiddleButtonHeight = 0;
        this.mMiddleButtonWidth = 0.0f;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mRotation = 0;
        this.mIconDark = true;
        this.mOnlyHomeBar = false;
        GestureUpGuideBarView$$ExternalSyntheticLambda0 gestureUpGuideBarView$$ExternalSyntheticLambda0 = new GestureUpGuideBarView$$ExternalSyntheticLambda0();
        gestureUpGuideBarView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateRunnable = gestureUpGuideBarView$$ExternalSyntheticLambda0;
        if (NavBarUtils.isSubDisplayId(context, context.getDisplayId())) {
            return;
        }
        init(context);
    }

    public final Drawable getEdgeDrawable(boolean z) {
        if (this.mOnlyHomeBar) {
            return null;
        }
        boolean z2 = this.mIsLayoutRtl;
        boolean z3 = this.mIconDark;
        if (z) {
            if (z2) {
                return z3 ? this.mContext.getDrawable(2131235682) : this.mRightDrawable;
            }
            return z3 ? this.mContext.getDrawable(2131235680) : this.mLeftDrawable;
        }
        if (z2) {
            return z3 ? this.mContext.getDrawable(2131235680) : this.mLeftDrawable;
        }
        return z3 ? this.mContext.getDrawable(2131235682) : this.mRightDrawable;
    }

    public final void init(Context context) {
        this.mContext = context;
        this.mGapEdge = context.getResources().getDimensionPixelOffset(2131171023);
        this.mContext.getResources().getDimensionPixelOffset(2131171024);
        this.mContext.getResources().getDimensionPixelOffset(2131171025);
        this.mRotation = this.mContext.getDisplay().getRotation();
        Rect screenRect = ScreenSizeHelper.getInstance(this.mContext).getScreenRect(this.mContext);
        this.mScreenWidth = screenRect.width();
        this.mScreenHeight = screenRect.height();
        boolean z = this.mOnlyHomeBar;
        Context context2 = this.mContext;
        if (z) {
            Drawable drawable = context2.getDrawable(2131235681);
            this.mMiddleDrawable = drawable;
            this.mMiddleButtonHeight = drawable.getIntrinsicHeight();
            if (this.mRotation == 0) {
                this.mMiddleButtonWidth = this.mScreenWidth * 0.335f;
            } else {
                this.mMiddleButtonWidth = this.mScreenHeight * 0.257f;
            }
        } else {
            this.mLeftDrawable = context2.getDrawable(2131235680);
            this.mMiddleDrawable = this.mContext.getDrawable(2131235681);
            this.mRightDrawable = this.mContext.getDrawable(2131235682);
            this.mMiddleButtonHeight = this.mMiddleDrawable.getIntrinsicHeight();
            this.mMiddleButtonWidth = this.mContext.getResources().getDimensionPixelOffset(2131171028);
        }
        this.mSideButtonHeight = this.mLeftDrawable.getIntrinsicHeight();
        this.mSideButtonWidth = this.mScreenWidth * 0.224f;
        this.mIsLayoutRtl = getLayoutDirection() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = SwipeUpGestureKeysModeObserver.INSTANCE.getSwipeUpGestureKeysMode() == 1;
        if (this.mOnlyHomeBar != z) {
            this.mOnlyHomeBar = z;
            init(this.mContext);
            updateButtonViews();
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        int rotation = configuration.windowConfiguration.getRotation();
        if (NavBarUtils.isGestureUpMode()) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(rotation, "onRotationChanged rotation:", " oldRotation:");
            sbM.append(this.mRotation);
            sbM.append(" forceUpdate:true");
            LogUtil.d(LogUtil.TAG_NAVBAR, "HoverNavigationBarView", sbM.toString());
            this.mRotation = rotation;
            post(this.mUpdateRunnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public final void setIconDark(ImageView imageView, boolean z) {
        Drawable drawable;
        LogUtil.d(LogUtil.TAG_NAVBAR, "HoverNavigationBarView", "setIconDark dark:" + z + ", icon:" + imageView);
        if (imageView != null) {
            int id = imageView.getId();
            if (id == 2131364381) {
                drawable = getEdgeDrawable(true);
            } else if (id == 2131364377) {
                drawable = getEdgeDrawable(false);
            } else if (id == 2131364380) {
                drawable = this.mIconDark ? this.mContext.getDrawable(2131235681) : this.mMiddleDrawable;
            } else {
                drawable = null;
            }
            imageView.setBackground(drawable);
        }
    }

    public void setIconsDark(boolean z) {
        setIconDark((ImageView) findViewById(2131364381), this.mIconDark);
        setIconDark((ImageView) findViewById(2131364380), this.mIconDark);
        setIconDark((ImageView) findViewById(2131364377), this.mIconDark);
    }

    public final void updateButtonViews() {
        Drawable drawable = this.mLeftDrawable;
        if (drawable == null || this.mMiddleDrawable == null) {
            return;
        }
        int i = this.mRotation;
        if (i == 1 || i == 3) {
            this.mSideButtonHeight = drawable.getIntrinsicHeight();
            this.mSideButtonWidth = this.mScreenHeight * 0.257f;
            this.mMiddleButtonHeight = this.mMiddleDrawable.getIntrinsicHeight();
            this.mMiddleButtonWidth = this.mScreenHeight * 0.257f;
        } else {
            this.mSideButtonHeight = drawable.getIntrinsicHeight();
            this.mSideButtonWidth = this.mScreenWidth * 0.224f;
            this.mMiddleButtonHeight = this.mMiddleDrawable.getIntrinsicHeight();
            boolean z = this.mOnlyHomeBar;
            int i2 = this.mScreenWidth;
            if (z) {
                this.mMiddleButtonWidth = i2 * 0.335f;
            } else {
                this.mMiddleButtonWidth = i2 * 0.224f;
            }
        }
        updateViewLayoutParams(2131364378, -1, this.mGapEdge);
        updateViewLayoutParams(2131364379, -1, this.mGapEdge);
        updateViewLayoutParams(2131364381, this.mSideButtonHeight, (int) this.mSideButtonWidth);
        updateViewLayoutParams(2131364380, this.mMiddleButtonHeight, (int) this.mMiddleButtonWidth);
        updateViewLayoutParams(2131364377, this.mSideButtonHeight, (int) this.mSideButtonWidth);
        ImageView imageView = (ImageView) findViewById(2131364381);
        ImageView imageView2 = (ImageView) findViewById(2131364380);
        ImageView imageView3 = (ImageView) findViewById(2131364377);
        this.mIsLayoutRtl = getLayoutDirection() == 1;
        if (imageView != null && imageView2 != null && imageView3 != null) {
            imageView2.setBackground(this.mIconDark ? this.mContext.getDrawable(2131235681) : this.mMiddleDrawable);
            if (this.mOnlyHomeBar) {
                imageView.setVisibility(8);
                imageView3.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                imageView3.setVisibility(0);
            }
        }
        setIconsDark(this.mIconDark);
    }

    public final void updateViewLayoutParams(int i, int i2, int i3) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById != null) {
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            layoutParams.height = i2;
            layoutParams.width = i3;
            viewFindViewById.setLayoutParams(layoutParams);
        }
    }

    public GestureUpGuideBarView(Context context) {
        super(context);
        this.mSideButtonHeight = 0;
        this.mSideButtonWidth = 0.0f;
        this.mMiddleButtonHeight = 0;
        this.mMiddleButtonWidth = 0.0f;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mRotation = 0;
        this.mIconDark = true;
        this.mOnlyHomeBar = false;
        GestureUpGuideBarView$$ExternalSyntheticLambda0 gestureUpGuideBarView$$ExternalSyntheticLambda0 = new GestureUpGuideBarView$$ExternalSyntheticLambda0();
        gestureUpGuideBarView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateRunnable = gestureUpGuideBarView$$ExternalSyntheticLambda0;
        init(context);
    }

    public GestureUpGuideBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSideButtonHeight = 0;
        this.mSideButtonWidth = 0.0f;
        this.mMiddleButtonHeight = 0;
        this.mMiddleButtonWidth = 0.0f;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mRotation = 0;
        this.mIconDark = true;
        this.mOnlyHomeBar = false;
        GestureUpGuideBarView$$ExternalSyntheticLambda0 gestureUpGuideBarView$$ExternalSyntheticLambda0 = new GestureUpGuideBarView$$ExternalSyntheticLambda0();
        gestureUpGuideBarView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateRunnable = gestureUpGuideBarView$$ExternalSyntheticLambda0;
        init(context);
    }
}
