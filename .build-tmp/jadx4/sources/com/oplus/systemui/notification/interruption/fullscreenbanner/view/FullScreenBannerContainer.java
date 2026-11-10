package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.android.systemui.shade.OplusNotificationShadeWindowControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.base.radius.NotificationPath;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.material.HeadsUpShadowEdgeController;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FullScreenBannerContainer extends LinearLayout implements View.OnAttachStateChangeListener {
    public int mClipTop;
    public FullScreenBanner mFullScreenBanner;
    public Drawable mFullScreenBannerBg;
    public FullScreenBannerHelper mHelper;
    public NotificationBlurMode mNotificationBlurMode;
    public OplusNotificationShadeWindowControllerEx mNotificationShadeWindowControllerEx;
    public NotificationPath mOplusPath;
    public Path mPath;
    public HeadsUpShadowEdgeController mShadowEdgeController;
    public int mSpotLightRadius;
    public FullScreenBannerTouchHandler mTouchHandler;
    public boolean mUseBlurBannerBg;
    public ViewBlurManager mViewBlurManager;
    public ViewBlurProxy mViewBlurProxy;

    public FullScreenBannerContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Path path = new Path();
        this.mPath = path;
        this.mOplusPath = new NotificationPath(path);
        this.mClipTop = 0;
        this.mSpotLightRadius = 0;
        this.mNotificationBlurMode = NotificationBlurMode.UNINITIATED;
        this.mSpotLightRadius = getContext().getResources().getDimensionPixelSize(2131168360);
        NotificationSpotLightDelegate.markLazyParamsKind(this, NotificationSpotLightParamsKind.FULLSCREEN_BANNER);
    }

    private Path getClipPath() {
        int translationY = this.mClipTop;
        int width = this.mFullScreenBanner.getWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.mFullScreenBanner.getTranslationX() != 0.0f || this.mHelper.mIsMoving) {
            translationY = (int) this.mFullScreenBanner.getTranslationY();
            measuredHeight = this.mFullScreenBanner.getMeasuredHeight() + translationY;
        }
        this.mPath.reset();
        this.mOplusPath.addSmoothRoundRect(new RectF(0.0f, translationY, width, measuredHeight), 0.0f, 0.0f);
        return this.mPath;
    }

    private Drawable getFullScreenBannerDrawable() {
        if (GaussBlurUtils.isGaussBlurDisabled()) {
            return null;
        }
        ViewBlurProxy viewBlurProxyRequireBlurProxyForView = this.mViewBlurManager.requireBlurProxyForView(this.mFullScreenBanner, ViewBlurManager.CardType.FULLSCREENBANNER, SmoothRoundEx.INSTANCE.getDefaultWeight(), this.mNotificationBlurMode);
        this.mViewBlurManager.blurForHeadsUp(this.mFullScreenBanner, false);
        this.mViewBlurProxy = viewBlurProxyRequireBlurProxyForView;
        return viewBlurProxyRequireBlurProxyForView.getBlurDrawable(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NotificationSpotLightDelegate.ClipSpec getSpotLightClipSpec() {
        int translationX = (int) this.mFullScreenBanner.getTranslationX();
        int translationY = (int) this.mFullScreenBanner.getTranslationY();
        int width = this.mFullScreenBanner.getWidth();
        int measuredHeight = this.mFullScreenBanner.getMeasuredHeight();
        int i = this.mSpotLightRadius;
        return new NotificationSpotLightDelegate.ClipSpec(Math.max(0, translationX), translationY, Math.min(width, translationX + width), translationY + measuredHeight, i, i, true);
    }

    private void setUxProcess(boolean z) {
        OplusNotificationShadeWindowControllerEx oplusNotificationShadeWindowControllerEx = this.mNotificationShadeWindowControllerEx;
        if (oplusNotificationShadeWindowControllerEx != null) {
            oplusNotificationShadeWindowControllerEx.adjustShadeUxFlag(getContext(), z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable drawable;
        super.dispatchDraw(canvas);
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        if (fullScreenBanner != null) {
            HeadsUpShadowEdgeController headsUpShadowEdgeController = this.mShadowEdgeController;
            if (headsUpShadowEdgeController != null && (drawable = this.mFullScreenBannerBg) != null) {
                headsUpShadowEdgeController.draw(canvas, fullScreenBanner, drawable.getBounds());
            }
            FullScreenBannerContainer$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FullScreenBannerContainer$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            NotificationSpotLightDelegate.draw(this, canvas, externalSyntheticLambda0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        NotificationSpotLightDelegate.onTouchEvent(this, motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        int iMax;
        int i;
        canvas.save();
        if (view == this.mFullScreenBanner) {
            canvas.clipPath(getClipPath());
            if (this.mFullScreenBannerBg != null && this.mUseBlurBannerBg) {
                int translationX = (int) this.mFullScreenBanner.getTranslationX();
                int translationY = (int) this.mFullScreenBanner.getTranslationY();
                int width = this.mFullScreenBanner.getWidth();
                int height = this.mFullScreenBanner.getHeight();
                int i2 = 0;
                if (this.mHelper.mIsMoving) {
                    iMax = 0;
                } else {
                    if (translationX < 0) {
                        i = 0 - translationX;
                    } else {
                        if (translationX > 0) {
                            width -= translationX;
                        }
                        i = 0;
                    }
                    int i3 = translationY + height;
                    int i4 = this.mClipTop;
                    if (i3 > i4) {
                        i2 = i;
                        iMax = Math.max(0, i4 - translationY);
                    } else {
                        iMax = 0;
                        width = 0;
                        height = 0;
                    }
                }
                this.mFullScreenBannerBg.setBounds(i2, iMax, width, height);
            }
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return zDrawChild;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        FullScreenBanner fullScreenBanner;
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = this.mTouchHandler;
        if (fullScreenBannerTouchHandler != null && (fullScreenBanner = fullScreenBannerTouchHandler.mAnimView) != null) {
            fullScreenBannerTouchHandler.mDensityScale = fullScreenBanner.getResources().getDisplayMetrics().density;
        }
        super.onConfigurationChanged(configuration);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131168360);
        if (dimensionPixelSize != this.mSpotLightRadius) {
            this.mSpotLightRadius = dimensionPixelSize;
            HeadsUpShadowEdgeController headsUpShadowEdgeController = this.mShadowEdgeController;
            if (headsUpShadowEdgeController != null) {
                headsUpShadowEdgeController.refresh(dimensionPixelSize);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = this.mTouchHandler;
        if (fullScreenBannerTouchHandler == null || !fullScreenBannerTouchHandler.onInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = this.mTouchHandler;
        if (fullScreenBannerTouchHandler == null) {
            return super.onTouchEvent(motionEvent);
        }
        fullScreenBannerTouchHandler.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        if (view instanceof FullScreenBanner) {
            FullScreenBanner fullScreenBanner = (FullScreenBanner) view;
            this.mFullScreenBanner = fullScreenBanner;
            fullScreenBanner.addOnAttachStateChangeListener(this);
        }
        super.onViewAdded(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        if (this.mFullScreenBanner == null || this.mViewBlurManager == null) {
            return;
        }
        Drawable fullScreenBannerDrawable = getFullScreenBannerDrawable();
        this.mFullScreenBannerBg = fullScreenBannerDrawable;
        if (fullScreenBannerDrawable != null) {
            this.mUseBlurBannerBg = true;
        } else {
            this.mFullScreenBannerBg = ((LinearLayout) this).mContext.getDrawable(2131236573);
        }
        Drawable drawable = this.mFullScreenBannerBg;
        if (drawable != null) {
            this.mFullScreenBanner.setBackground(drawable);
        }
        if (this.mFullScreenBannerBg == null || !this.mUseBlurBannerBg) {
            return;
        }
        if (this.mShadowEdgeController == null) {
            this.mShadowEdgeController = HeadsUpShadowEdgeController.create(((LinearLayout) this).mContext);
        }
        HeadsUpShadowEdgeController headsUpShadowEdgeController = this.mShadowEdgeController;
        if (headsUpShadowEdgeController == null || headsUpShadowEdgeController.isAttached()) {
            return;
        }
        this.mShadowEdgeController.show(this.mFullScreenBanner, ((LinearLayout) this).mContext, this.mSpotLightRadius, this, this.mNotificationBlurMode.isHeadsUpBlurDark());
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewBlurManager viewBlurManager;
        HeadsUpShadowEdgeController headsUpShadowEdgeController = this.mShadowEdgeController;
        if (headsUpShadowEdgeController != null) {
            headsUpShadowEdgeController.release();
            this.mShadowEdgeController = null;
        }
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        if (fullScreenBanner != null && (viewBlurManager = this.mViewBlurManager) != null) {
            viewBlurManager.releaseBlurProxyForView(fullScreenBanner, true, "");
            this.mViewBlurProxy = null;
        }
        FullScreenBanner fullScreenBanner2 = this.mFullScreenBanner;
        if (fullScreenBanner2 != null) {
            fullScreenBanner2.removeOnAttachStateChangeListener(this);
            setUxProcess(false);
        }
        this.mFullScreenBanner = null;
    }

    public void setClipTop(int i) {
        this.mClipTop = i;
    }

    public void setNotificationBlurMode(NotificationBlurMode notificationBlurMode) {
        this.mNotificationBlurMode = notificationBlurMode;
    }

    public void setTouchHandler(FullScreenBannerTouchHandler fullScreenBannerTouchHandler) {
        this.mTouchHandler = fullScreenBannerTouchHandler;
    }
}
