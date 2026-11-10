package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$;
import com.oplus.systemui.notification.utlils.DrawableWithBadge;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.separate.ColumnWidthUtil;
import com.oplus.systemui.statusbar.notification.fullscreenbanner.FullScreenBannerBoostFrameRateRateHandler;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FullScreenBanner extends LinearLayout {
    public static ReplySendListener sSendBtnListener;
    public FullScreenBannerHelper mBannerHelper;
    public final LinearLayout mBannerLayout;
    public final FullScreenBannerBoostFrameRateRateHandler mBoostFrameRateHandler;
    public final LinearLayout mBottomBtnLayout;
    public Callback mCallback;
    public final ImageView mIvAppIcon;
    public int mOrientation;
    public int mOriginTopWidth;
    public final String mPackageName;
    public RemoteEditText mReplyEditText;
    public final LinearLayout mReplyLayout;
    public Resources mResources;
    public final LinearLayout mTopBtnLayout;
    public final TextView mTvAppName;
    public final TextView mTvContent;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class Builder {
        public Drawable mAvatarIcon;
        public final LinkedHashMap mBtnMap = new LinkedHashMap();
        public Callback mCallback;
        public boolean mClonedApp;
        public String mContent;
        public final Context mContext;
        public FullScreenBannerHelper mFullScreenBannerHelper;
        public Drawable mIcon;
        public boolean mIsMessageStyle;
        public String mLabel;
        public Drawable mLargeIconWithBadge;
        public FullScreenBannerHelper$.ExternalSyntheticLambda3 mSendListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setAvatarIcon(Drawable drawable) {
            this.mAvatarIcon = drawable;
            return this;
        }

        public Builder setCallback(Callback callback) {
            this.mCallback = callback;
            return this;
        }

        public Builder setCloned(boolean z) {
            this.mClonedApp = z;
            return this;
        }

        public Builder setContent(String str) {
            this.mContent = str;
            return this;
        }

        public Builder setFullScreenBannerHelper(FullScreenBannerHelper fullScreenBannerHelper) {
            this.mFullScreenBannerHelper = fullScreenBannerHelper;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.mIcon = drawable;
            return this;
        }

        public Builder setLabel(String str) {
            this.mLabel = str;
            return this;
        }

        public Builder setLargeIconWithBadge(Drawable drawable) {
            this.mLargeIconWithBadge = drawable;
            return this;
        }

        public Builder setMessageStyle(boolean z) {
            this.mIsMessageStyle = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    public FullScreenBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBoostFrameRateHandler = new FullScreenBannerBoostFrameRateRateHandler(this);
        String packageName = context.getPackageName();
        this.mPackageName = packageName;
        LocalBroadcastManager.getInstance(context);
        refreshResource(context);
        LayoutInflater.from(context).inflate(this.mResources.getIdentifier("notification_full_screen_banner", "layout", packageName), (ViewGroup) this, true);
        this.mBannerLayout = (LinearLayout) findViewById(this.mResources.getIdentifier("banner_layout", PictorialAppInfo.ID, packageName));
        this.mIvAppIcon = (ImageView) findViewById(this.mResources.getIdentifier("app_icon", PictorialAppInfo.ID, packageName));
        this.mTvAppName = (TextView) findViewById(this.mResources.getIdentifier(KeyBaseController.MAGIC_KEY_APP_NAME, PictorialAppInfo.ID, packageName));
        this.mTvContent = (TextView) findViewById(this.mResources.getIdentifier("app_content", PictorialAppInfo.ID, packageName));
        this.mTopBtnLayout = (LinearLayout) findViewById(this.mResources.getIdentifier("button_layout_top", PictorialAppInfo.ID, packageName));
        this.mBottomBtnLayout = (LinearLayout) findViewById(this.mResources.getIdentifier("button_layout_bottom", PictorialAppInfo.ID, packageName));
        this.mReplyLayout = (LinearLayout) findViewById(this.mResources.getIdentifier("reply_layout", PictorialAppInfo.ID, packageName));
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
    }

    private float getBannerBadgeCornerRadiusFraction() {
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(2131170678);
        if (dimensionPixelSize > 0) {
            return this.mResources.getDimension(2131168361) / dimensionPixelSize;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerHelper(FullScreenBannerHelper fullScreenBannerHelper) {
        this.mBannerHelper = fullScreenBannerHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setButtonPosition(LinkedHashMap<String, View.OnClickListener> linkedHashMap) {
        int[] iArr;
        int size = linkedHashMap.size();
        if (size <= 0 || size > 3) {
            return;
        }
        int i = 0;
        if (this.mOrientation != 1 || size <= 1) {
            this.mTopBtnLayout.setVisibility(0);
            this.mBottomBtnLayout.setVisibility(8);
            iArr = new int[]{this.mResources.getIdentifier("button_1", PictorialAppInfo.ID, this.mPackageName), this.mResources.getIdentifier("button_2", PictorialAppInfo.ID, this.mPackageName), this.mResources.getIdentifier("button_7", PictorialAppInfo.ID, this.mPackageName)};
        } else {
            this.mBottomBtnLayout.setVisibility(0);
            this.mTopBtnLayout.setVisibility(8);
            iArr = new int[]{this.mResources.getIdentifier("button_3", PictorialAppInfo.ID, this.mPackageName), this.mResources.getIdentifier("button_4", PictorialAppInfo.ID, this.mPackageName), this.mResources.getIdentifier("button_5", PictorialAppInfo.ID, this.mPackageName)};
        }
        for (int i2 = 0; i2 < 3; i2++) {
            Button button = (Button) findViewById(iArr[i2]);
            if (i2 < size) {
                button.setVisibility(0);
            } else {
                button.setVisibility(8);
                button.setText((CharSequence) null);
                button.setOnClickListener(null);
            }
        }
        for (Map.Entry<String, View.OnClickListener> entry : linkedHashMap.entrySet()) {
            Button button2 = (Button) findViewById(iArr[i]);
            button2.setTextColor(NotificationColorUtil.getCouiColorLabelPrimary(((LinearLayout) this).mContext));
            button2.setText(entry.getKey());
            button2.setOnClickListener(entry.getValue());
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallback(Callback callback) {
        this.mCallback = callback;
        FullScreenBanner$$ExternalSyntheticLambda0 fullScreenBanner$$ExternalSyntheticLambda0 = new FullScreenBanner$$ExternalSyntheticLambda0();
        fullScreenBanner$$ExternalSyntheticLambda0.f$0 = callback;
        VarHandle.storeStoreFence();
        setOnClickListener(fullScreenBanner$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContent(String str) {
        this.mTvContent.setText(str);
        this.mTvContent.setTextColor(NotificationColorUtil.getCouiColorLabelSecondary(((LinearLayout) this).mContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabel(String str) {
        this.mTvAppName.setText(str);
        this.mTvAppName.setTextColor(NotificationColorUtil.getCouiColorLabelPrimary(((LinearLayout) this).mContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSendListener(ReplySendListener replySendListener) {
        sSendBtnListener = replySendListener;
    }

    public FullScreenBannerBoostFrameRateRateHandler getBannerBoostFrameRateRateHandler() {
        return this.mBoostFrameRateHandler;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        this.mBannerLayout.getLayoutParams().width = this.mOriginTopWidth;
        measureChild(this.mTopBtnLayout, i, i2);
        measureChild(this.mIvAppIcon, i, i2);
        measureChild(this.mTvAppName, i, i2);
        measureChild(this.mTvContent, i, i2);
        this.mIvAppIcon.getMeasuredWidth();
        this.mTvAppName.getMeasuredWidth();
        this.mTvContent.getMeasuredWidth();
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.mBannerHelper.setBannerRealHeight(getMeasuredHeight());
        super.onSizeChanged(i, i2, i3, i4);
    }

    public final void refreshResource(Context context) {
        Resources resources = context.getResources();
        this.mResources = resources;
        resources.getDimensionPixelSize(2131165236);
        this.mResources.getDimensionPixelSize(2131165230);
        this.mResources.getDimensionPixelSize(2131166122);
        this.mResources.getDimensionPixelSize(2131166123);
        this.mResources.getDimensionPixelSize(2131168263);
        this.mResources.getDimensionPixelSize(2131168358);
        this.mOrientation = this.mResources.getConfiguration().orientation;
        this.mOriginTopWidth = ColumnWidthUtil.getPanelWidth(context.getApplicationContext());
    }

    public final void setIcon(Drawable drawable, boolean z, Drawable drawable2, boolean z2, Drawable drawable3) {
        if (z2 && drawable2 != null) {
            Drawable drawableWithBadge = new DrawableWithBadge(drawable2);
            ((DrawableWithBadge) drawableWithBadge).mainPaddingFactor = 0.045f;
            ((DrawableWithBadge) drawableWithBadge).badgeSizeScale = 0.33f;
            ((DrawableWithBadge) drawableWithBadge).intrinsicContentSizePx = 0;
            drawableWithBadge.relayoutIfHasBounds();
            drawableWithBadge.setBadgeCornerRadiusFraction(getBannerBadgeCornerRadiusFraction());
            if (drawable == null || !z) {
                drawableWithBadge.setBadge(drawable);
                this.mIvAppIcon.setImageDrawable(drawableWithBadge);
                return;
            } else {
                drawableWithBadge.setBadge(drawable, ((LinearLayout) this).mContext.getDrawable(2131233559));
                this.mIvAppIcon.setImageDrawable(drawableWithBadge);
                return;
            }
        }
        if (drawable3 != null) {
            this.mIvAppIcon.setImageDrawable(drawable3);
            return;
        }
        if (drawable != null && z) {
            Drawable drawable4 = ((LinearLayout) this).mContext.getDrawable(2131233559);
            Drawable drawableWithBadge2 = new DrawableWithBadge(drawable);
            ((DrawableWithBadge) drawableWithBadge2).mainPaddingFactor = 0.045f;
            ((DrawableWithBadge) drawableWithBadge2).badgeSizeScale = 0.33f;
            ((DrawableWithBadge) drawableWithBadge2).intrinsicContentSizePx = 0;
            drawableWithBadge2.relayoutIfHasBounds();
            drawableWithBadge2.setBadgeCornerRadiusFraction(getBannerBadgeCornerRadiusFraction());
            drawableWithBadge2.setBadge(drawable4);
            this.mIvAppIcon.setImageDrawable(drawableWithBadge2);
            return;
        }
        this.mIvAppIcon.setImageDrawable(drawable);
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDeviceExpLessThanC()) {
            ImageView imageView = this.mIvAppIcon;
            5 r5 = new 5();
            r5.mPath = new Path();
            r5.mRect = new RectF();
            VarHandle.storeStoreFence();
            imageView.setOutlineProvider(r5);
            imageView.setClipToOutline(true);
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        FullScreenBannerContainer fullScreenBannerContainer;
        super.setTranslationX(f);
        Callback callback = this.mCallback;
        if (callback == null || (fullScreenBannerContainer = ((FullScreenBannerHelper.AnonymousClass8) callback).this$0.mRootView) == null) {
            return;
        }
        fullScreenBannerContainer.invalidate();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        FullScreenBannerContainer fullScreenBannerContainer;
        super.setTranslationY(f);
        Callback callback = this.mCallback;
        if (callback == null || (fullScreenBannerContainer = ((FullScreenBannerHelper.AnonymousClass8) callback).this$0.mRootView) == null) {
            return;
        }
        fullScreenBannerContainer.invalidate();
    }

    public final void updateBanner(Builder builder) {
        setIcon(builder.mIcon, builder.mClonedApp, builder.mAvatarIcon, builder.mIsMessageStyle, builder.mLargeIconWithBadge);
        setLabel(builder.mLabel);
        setContent(builder.mContent);
        setButtonPosition(builder.mBtnMap);
        setCallback(builder.mCallback);
        setSendListener(builder.mSendListener);
        if (this.mReplyLayout.getVisibility() == 0) {
            this.mBottomBtnLayout.setVisibility(8);
            this.mTopBtnLayout.setVisibility(8);
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            ((FullScreenBannerHelper.AnonymousClass8) callback).onBannerUpdate();
        }
    }

    public FullScreenBanner(Context context) {
        this(context, null);
    }
}
