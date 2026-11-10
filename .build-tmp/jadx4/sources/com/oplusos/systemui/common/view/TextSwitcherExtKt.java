package com.oplusos.systemui.common.view;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import androidx.core.view.ViewGroupKt;
import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TextSwitcherExtKt {
    public static final boolean compareAndSetTextSizePx(TextSwitcher textSwitcher, int i) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                return i2 > 0;
            }
            TextView textView = (TextView) ((View) it.next());
            float f = i;
            boolean z = textView.getTextSize() == f;
            textView.setTextSize(0, f);
            if (!z && (i2 = i2 + 1) < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
    }

    public static final CharSequence getCurrentText(TextSwitcher textSwitcher) {
        return ((TextView) textSwitcher.getCurrentView()).getText();
    }

    public static final TextView getCurrentTextView(TextSwitcher textSwitcher) {
        return (TextView) textSwitcher.getCurrentView();
    }

    public static final int getTextColor(TextSwitcher textSwitcher, int i) {
        if (textSwitcher.getChildCount() > 0) {
            View childAt = textSwitcher.getChildAt(0);
            TextView textView = childAt instanceof TextView ? (TextView) childAt : null;
            if (textView != null) {
                return textView.getCurrentTextColor();
            }
        }
        return i;
    }

    public static final void setContent(TextSwitcher textSwitcher, CharSequence charSequence) {
        if (TextUtils.equals(getCurrentText(textSwitcher), charSequence) && textSwitcher.getCurrentView().getVisibility() == 0) {
            return;
        }
        textSwitcher.setText(charSequence);
    }

    public static final void setLineHeight(TextSwitcher textSwitcher, int i) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setLineHeight(i);
        }
    }

    public static final void setLineSpacing(TextSwitcher textSwitcher) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setLineSpacing(0.0f, 1.0f);
        }
    }

    public static final void setTextColor(TextSwitcher textSwitcher, ColorStateList colorStateList) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setTextColor(colorStateList);
        }
    }

    public static final void setTextSize(TextSwitcher textSwitcher, float f) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setTextSize(f);
        }
    }

    public static final void setTextSizePx(TextSwitcher textSwitcher, float f) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setTextSize(0, f);
        }
    }

    public static final void setTextViewSelected(TextSwitcher textSwitcher, boolean z) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setSelected(z);
        }
    }

    public static final void setTextViewTypeface(TextSwitcher textSwitcher, Typeface typeface) {
        Iterator it = ViewGroupKt.getChildren(textSwitcher).iterator();
        while (it.hasNext()) {
            ((TextView) ((View) it.next())).setTypeface(typeface);
        }
    }
}
