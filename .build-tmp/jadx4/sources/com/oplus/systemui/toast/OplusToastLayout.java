package com.oplus.systemui.toast;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusToastLayout extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ImageView mIconView;
    public boolean mIsSubDisPlayShow;
    public boolean mIsTheFirstFrame;
    public final Float mPortion;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mShadowSize;
    public int mSubMarginTopVertical;
    public int mSubPddingWidth;
    public int mSubPddingWidthNoIcon;
    public int mSubSingleMarginTopVertical;
    public TextView mTextView;
    public int mToastContentPaddingLeft;
    public int mToastContentPaddingRight;
    public int mToastDoubleContentPaddingTop;
    public int mToastMutlContentPaddingTop;
    public Consumer mVisibilityChangedListener;
    public final WindowManager mWindowManager;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class LineStyle {
        public static final /* synthetic */ LineStyle[] $VALUES;
        public static final LineStyle MULTI;
        public static final LineStyle ONE;
        public static final LineStyle TWO;

        static {
            LineStyle lineStyle = new LineStyle("ONE", 0);
            ONE = lineStyle;
            LineStyle lineStyle2 = new LineStyle("TWO", 1);
            TWO = lineStyle2;
            LineStyle lineStyle3 = new LineStyle("MULTI", 2);
            MULTI = lineStyle3;
            $VALUES = new LineStyle[]{lineStyle, lineStyle2, lineStyle3};
        }

        public static LineStyle valueOf(String str) {
            return (LineStyle) Enum.valueOf(LineStyle.class, str);
        }

        public static LineStyle[] values() {
            return (LineStyle[]) $VALUES.clone();
        }
    }

    public OplusToastLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPortion = Float.valueOf(1.0f);
        this.mIsTheFirstFrame = true;
        this.mIsSubDisPlayShow = false;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        initResourse();
    }

    public final void initResourse() {
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        boolean baseSubDisplayShow = companion.getInstance(((LinearLayout) this).mContext).getBaseSubDisplayShow();
        this.mIsSubDisPlayShow = baseSubDisplayShow;
        Context context = ((LinearLayout) this).mContext;
        if (baseSubDisplayShow) {
            this.mScreenWidth = companion.getInstance(context).getSecondaryDisplayWidth(this.mPortion.floatValue());
        } else {
            Size screenSize = ScreenSizeHelper.getInstance(context).getScreenSize();
            if (screenSize != null) {
                this.mScreenWidth = screenSize.getWidth();
            } else {
                ScreenSizeHelper.getInstance(((LinearLayout) this).mContext).updateScreenSize(((LinearLayout) this).mContext);
                Size screenSize2 = ScreenSizeHelper.getInstance(((LinearLayout) this).mContext).getScreenSize();
                if (screenSize2 != null) {
                    this.mScreenWidth = screenSize2.getWidth();
                } else {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusToastLayout", "[initResourse] mScreenWidth is null");
                }
            }
        }
        this.mScreenHeight = this.mWindowManager.getCurrentWindowMetrics().getBounds().width();
        this.mSubMarginTopVertical = getResources().getDimensionPixelSize(2131171192);
        this.mSubSingleMarginTopVertical = getResources().getDimensionPixelSize(2131171190);
        this.mToastContentPaddingLeft = getResources().getDimensionPixelSize(2131171220);
        this.mToastContentPaddingRight = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171219);
        this.mSubPddingWidth = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171201);
        this.mSubPddingWidthNoIcon = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171198);
        this.mToastMutlContentPaddingTop = getResources().getDimensionPixelSize(2131171213);
        this.mToastDoubleContentPaddingTop = getResources().getDimensionPixelSize(2131171208);
        this.mShadowSize = getResources().getDimensionPixelSize(2131173620);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mTextView = (TextView) findViewById(2131365614);
        this.mIconView = (ImageView) findViewById(2131363381);
        if (this.mTextView == null) {
            setVisibility(0);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TextView textView;
        int width;
        TextPaint paint;
        super.onLayout(z, i, i2, i3, i4);
        if (!this.mIsTheFirstFrame || (textView = this.mTextView) == null || (width = textView.getWidth()) <= 0) {
            return;
        }
        this.mIsTheFirstFrame = false;
        CharSequence text = this.mTextView.getText();
        if (TextUtils.isEmpty(text) || (paint = this.mTextView.getPaint()) == null) {
            return;
        }
        String string = text.toString();
        float fMeasureText = paint.measureText(string);
        boolean z2 = this.mTextView.getLineCount() > 1 && this.mTextView.getLineCount() <= 2;
        boolean z3 = this.mTextView.getLineCount() > 2;
        View viewFindViewById = findViewById(2131365689);
        if (z2 || z3) {
            TextView textView2 = this.mTextView;
            float lineCount = textView2.getLineCount() - 0.5f;
            float f = width;
            if (fMeasureText > f && fMeasureText < f * lineCount && !TextUtils.isEmpty(string)) {
                int length = string.length();
                for (int i5 = 0; i5 < length; i5++) {
                    Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(string.charAt(i5));
                    if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlockOf) || Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(unicodeBlockOf) || Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(unicodeBlockOf) || Character.UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlockOf)) {
                        textView2.setMinWidth(0);
                        textView2.setMaxWidth((int) (fMeasureText / lineCount));
                        textView2.requestLayout();
                        break;
                    }
                }
            }
        }
        LineStyle lineStyle = z3 ? LineStyle.MULTI : z2 ? LineStyle.TWO : LineStyle.ONE;
        OplusToastLayout$$ExternalSyntheticLambda0 oplusToastLayout$$ExternalSyntheticLambda0 = new OplusToastLayout$$ExternalSyntheticLambda0();
        oplusToastLayout$$ExternalSyntheticLambda0.f$0 = this;
        oplusToastLayout$$ExternalSyntheticLambda0.f$1 = lineStyle;
        oplusToastLayout$$ExternalSyntheticLambda0.f$2 = viewFindViewById;
        VarHandle.storeStoreFence();
        viewFindViewById.post(oplusToastLayout$$ExternalSyntheticLambda0);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int size = View.MeasureSpec.getSize(i);
            int i3 = this.mScreenWidth;
            if (size < i3) {
                i = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
            }
        }
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            int size2 = View.MeasureSpec.getSize(i2);
            int i4 = this.mScreenHeight;
            if (size2 < i4) {
                i2 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        Consumer consumer;
        super.onVisibilityChanged(view, i);
        if (view != this || (consumer = this.mVisibilityChangedListener) == null) {
            return;
        }
        consumer.accept(Integer.valueOf(i));
    }

    public void setIsTheFirstFrame(boolean z) {
        this.mIsTheFirstFrame = z;
    }

    public void setVisibilityChangedListener(Consumer<Integer> consumer) {
        this.mVisibilityChangedListener = consumer;
        if (consumer != null) {
            consumer.accept(Integer.valueOf(getVisibility()));
        }
    }

    public OplusToastLayout(Context context) {
        this(context, null);
    }
}
