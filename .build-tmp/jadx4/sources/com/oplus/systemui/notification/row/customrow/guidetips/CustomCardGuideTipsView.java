package com.oplus.systemui.notification.row.customrow.guidetips;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomCardGuideTipsView extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String KEY_IMMERSIVE_TIP = "immersiveTip";
    private static final String KEY_IMMERSIVE_TIP_PKG = "immersiveTipPkg";
    private static final String TAG = "CustomCardGuideTipsView";
    public int blurEndHeight;
    public boolean blurExpanding;
    public int blurStartHeight;
    public int contentViewHeight;
    private final Paint dstOutPaint;
    private FrameLayout highlightArea;
    private final Paint maskPaint;
    private final Path punchPath;
    public boolean tipsCollapsing;
    public boolean tipsHeightAnimating;
    private TextView tipsTextView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CustomCardGuideTipsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.punchPath = new Path();
        Paint paint = new Paint(1);
        paint.setColor(context.getResources().getColor(2131101474));
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.maskPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(style);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.dstOutPaint = paint2;
        this.blurExpanding = true;
        setWillNotDraw(false);
    }

    private final int getContentViewHeight() {
        View contentLayout;
        OplusCustomRow oplusCustomRow = oplusCustomRow();
        if (oplusCustomRow == null || (contentLayout = oplusCustomRow.getContentLayout()) == null) {
            return 0;
        }
        return contentLayout.getHeight();
    }

    private final String getTipsText() {
        CustomRowEntry entry;
        Bundle extras;
        try {
            OplusCustomRow oplusCustomRow = oplusCustomRow();
            if (oplusCustomRow != null && (entry = oplusCustomRow.getEntry()) != null && (extras = entry.getExtras()) != null) {
                int i = extras.getInt(KEY_IMMERSIVE_TIP, 0);
                String string = extras.getString(KEY_IMMERSIVE_TIP_PKG);
                if (string == null || i == 0) {
                    return null;
                }
                return getContext().getPackageManager().getResourcesForApplication(string).getString(i);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "getTipsText failed: pkg not found");
            return null;
        } catch (Resources.NotFoundException unused2) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "getTipsText failed: res not found");
            return null;
        }
    }

    public final void applyBlurRadius(float f) {
        TextView textView = this.tipsTextView;
        if (textView == null) {
            return;
        }
        if (f < 1.0f) {
            textView.setRenderEffect(null);
        } else {
            textView.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        }
    }

    public final void clearBlur() {
        TextView textView = this.tipsTextView;
        if (textView != null) {
            textView.setRenderEffect(null);
        }
    }

    public final FrameLayout getHighlightArea() {
        return this.highlightArea;
    }

    public final boolean getTipsCollapsing() {
        return this.tipsCollapsing;
    }

    public final boolean getTipsHeightAnimating() {
        return this.tipsHeightAnimating;
    }

    public final TextView getTipsTextView() {
        return this.tipsTextView;
    }

    public final int getTipsTextViewHeight() {
        TextView textView = this.tipsTextView;
        if (textView == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        return marginLayoutParams == null ? textView.getMeasuredHeight() : textView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        TextView textView;
        super.onAttachedToWindow();
        String tipsText = getTipsText();
        if (tipsText == null || (textView = this.tipsTextView) == null) {
            return;
        }
        textView.setText(tipsText);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        OplusCustomRow oplusCustomRow;
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        if (this.punchPath.isEmpty() && (oplusCustomRow = oplusCustomRow()) != null) {
            oplusCustomRow.getContentViewClipPath(this.punchPath);
        }
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, width, height, null);
        canvas.drawRect(0.0f, getContentViewHeight() / 2.0f, width, height, this.maskPaint);
        if (!this.punchPath.isEmpty()) {
            canvas.drawPath(this.punchPath, this.dstOutPaint);
        }
        canvas.restoreToCount(iSaveLayer);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.tipsTextView = (TextView) findViewById(2131365119);
        this.highlightArea = (FrameLayout) findViewById(2131365118);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TextView textView;
        super.onLayout(z, i, i2, i3, i4);
        int contentViewHeight = getContentViewHeight();
        if (contentViewHeight != this.contentViewHeight) {
            this.contentViewHeight = contentViewHeight;
            OplusCustomRow oplusCustomRow = oplusCustomRow();
            if (oplusCustomRow != null) {
                oplusCustomRow.getContentViewClipPath(this.punchPath);
            }
        }
        int i5 = this.contentViewHeight;
        if (i5 <= 0 || (textView = this.tipsTextView) == null) {
            return;
        }
        textView.layout(textView.getLeft(), i5, textView.getRight(), textView.getMeasuredHeight() + i5);
    }

    public final OplusCustomRow oplusCustomRow() {
        Object parent = getParent();
        if (parent instanceof OplusCustomRow) {
            return (OplusCustomRow) parent;
        }
        return null;
    }

    public final void resetTipsHeightAnimationState(boolean z) {
        OplusCustomRowGuideTipsController guideTipsController;
        boolean z2 = this.tipsCollapsing;
        this.tipsHeightAnimating = false;
        this.tipsCollapsing = false;
        clearBlur();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("resetTipsHeightAnimationState: wasCollapsing=", ", forceHide=", "CustomCardGuideTipsView-->", z2, z));
        }
        if (z2 || z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CustomCardGuideTipsView-->resetTipsHeightAnimationState: tipsView GONE");
            }
            setVisibility(8);
            OplusCustomRow oplusCustomRow = oplusCustomRow();
            if (oplusCustomRow == null || (guideTipsController = oplusCustomRow.getGuideTipsController()) == null) {
                return;
            }
            guideTipsController.onTipsHidden();
        }
    }

    public final void showTips() {
        View contentLayout;
        if (getVisibility() == 0) {
            return;
        }
        TextView textView = this.tipsTextView;
        if (textView != null) {
            LaunchableView launchableViewOplusCustomRow = oplusCustomRow();
            textView.measure(View.MeasureSpec.makeMeasureSpec(launchableViewOplusCustomRow != null ? launchableViewOplusCustomRow.getWidth() : getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        OplusCustomRow oplusCustomRow = oplusCustomRow();
        this.contentViewHeight = (oplusCustomRow == null || (contentLayout = oplusCustomRow.getContentLayout()) == null) ? 0 : contentLayout.getHeight();
        OplusCustomRow oplusCustomRow2 = oplusCustomRow();
        if (oplusCustomRow2 != null) {
            int actualHeight = oplusCustomRow2.getActualHeight();
            setVisibility(0);
            OplusCustomRow oplusCustomRow3 = oplusCustomRow();
            if (oplusCustomRow3 != null) {
                int intrinsicHeight = oplusCustomRow3.getIntrinsicHeight();
                this.tipsHeightAnimating = true;
                this.tipsCollapsing = false;
                this.blurStartHeight = actualHeight;
                this.blurEndHeight = intrinsicHeight;
                this.blurExpanding = true;
                applyBlurRadius(150.0f);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("showTips: heightBefore=", actualHeight, intrinsicHeight, ", heightAfter=", "CustomCardGuideTipsView-->"));
                }
            }
        }
    }

    public /* synthetic */ CustomCardGuideTipsView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CustomCardGuideTipsView(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }
}
