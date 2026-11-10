package com.oplus.systemui.media;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MarqueeTextView extends TextView {
    public boolean isTextChange;
    public boolean mAggregatedVisible;
    private final TextWatcher mTextWatcher;
    private String oldText;

    public MarqueeTextView(Context context) {
        super(context);
        this.oldText = "";
        this.mTextWatcher = new TextWatcher() { // from class: com.oplus.systemui.media.MarqueeTextView$mTextWatcher$1
            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (Intrinsics.areEqual(this.this$0.oldText, editable.toString())) {
                    return;
                }
                this.this$0.oldText = editable.toString();
                MarqueeTextView marqueeTextView = this.this$0;
                marqueeTextView.isTextChange = true;
                marqueeTextView.stopMarquee();
                this.this$0.isTextChange = false;
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSelected(true);
        addTextChangedListener(this.mTextWatcher);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setSelected(false);
        removeTextChangedListener(this.mTextWatcher);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        onVisibilityAggregated(isVisibleToUser());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (z == this.mAggregatedVisible) {
            return;
        }
        this.mAggregatedVisible = z;
        if (z) {
            return;
        }
        stopMarquee();
    }

    public final void stopMarquee() {
        if (!this.mAggregatedVisible || this.isTextChange) {
            super.stopMarquee();
        }
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.oldText = "";
        this.mTextWatcher = new TextWatcher() { // from class: com.oplus.systemui.media.MarqueeTextView$mTextWatcher$1
            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (Intrinsics.areEqual(this.this$0.oldText, editable.toString())) {
                    return;
                }
                this.this$0.oldText = editable.toString();
                MarqueeTextView marqueeTextView = this.this$0;
                marqueeTextView.isTextChange = true;
                marqueeTextView.stopMarquee();
                this.this$0.isTextChange = false;
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.oldText = "";
        this.mTextWatcher = new TextWatcher() { // from class: com.oplus.systemui.media.MarqueeTextView$mTextWatcher$1
            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (Intrinsics.areEqual(this.this$0.oldText, editable.toString())) {
                    return;
                }
                this.this$0.oldText = editable.toString();
                MarqueeTextView marqueeTextView = this.this$0;
                marqueeTextView.isTextChange = true;
                marqueeTextView.stopMarquee();
                this.this$0.isTextChange = false;
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.oldText = "";
        this.mTextWatcher = new TextWatcher() { // from class: com.oplus.systemui.media.MarqueeTextView$mTextWatcher$1
            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (Intrinsics.areEqual(this.this$0.oldText, editable.toString())) {
                    return;
                }
                this.this$0.oldText = editable.toString();
                MarqueeTextView marqueeTextView = this.this$0;
                marqueeTextView.isTextChange = true;
                marqueeTextView.stopMarquee();
                this.this$0.isTextChange = false;
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }
        };
    }
}
