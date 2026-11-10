package w2;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import io.github.andrea_lyz.assistrestore.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends View.AccessibilityDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f6367a;

    public a(b bVar) {
        this.f6367a = bVar;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.f6367a.f6373a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        a2.h hVarA = this.f6367a.a(view);
        if (hVarA != null) {
            return (AccessibilityNodeProvider) hVarA.f49e;
        }
        return null;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f6367a.f6373a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        Object tag;
        Object objValueOf;
        Object tag2;
        int i5 = u.f6438a;
        int i6 = Build.VERSION.SDK_INT;
        Object objA = null;
        if (i6 >= 28) {
            tag = Boolean.valueOf(r.c(view));
        } else {
            tag = view.getTag(R.id.tag_screen_reader_focusable);
            if (!Boolean.class.isInstance(tag)) {
                tag = null;
            }
        }
        Boolean bool = (Boolean) tag;
        boolean z2 = (bool == null || !bool.booleanValue()) ? 0 : 1;
        if (i6 >= 28) {
            accessibilityNodeInfo.setScreenReaderFocusable(z2);
        } else {
            Bundle extras = accessibilityNodeInfo.getExtras();
            if (extras != null) {
                extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", z2 | (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-2)));
            }
        }
        if (i6 >= 28) {
            objValueOf = Boolean.valueOf(r.b(view));
        } else {
            Object tag3 = view.getTag(R.id.tag_accessibility_heading);
            objValueOf = Boolean.class.isInstance(tag3) ? tag3 : null;
        }
        Boolean bool2 = (Boolean) objValueOf;
        boolean z3 = bool2 != null && bool2.booleanValue();
        if (i6 >= 28) {
            accessibilityNodeInfo.setHeading(z3);
        } else {
            Bundle extras2 = accessibilityNodeInfo.getExtras();
            if (extras2 != null) {
                extras2.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (extras2.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-3)) | (z3 ? 2 : 0));
            }
        }
        if (i6 >= 28) {
            tag2 = r.a(view);
        } else {
            tag2 = view.getTag(R.id.tag_accessibility_pane_title);
            if (!CharSequence.class.isInstance(tag2)) {
                tag2 = null;
            }
        }
        CharSequence charSequence = (CharSequence) tag2;
        if (i6 >= 28) {
            accessibilityNodeInfo.setPaneTitle(charSequence);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
        if (i6 >= 30) {
            objA = s.a(view);
        } else {
            Object tag4 = view.getTag(R.id.tag_state_description);
            if (CharSequence.class.isInstance(tag4)) {
                objA = tag4;
            }
        }
        CharSequence charSequence2 = (CharSequence) objA;
        if (i6 >= 30) {
            t2.b.g(accessibilityNodeInfo, charSequence2);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence2);
        }
        this.f6367a.f6373a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.getText();
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            accessibilityNodeInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) ((x2.c) list.get(i7)).f6561a);
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f6367a.f6373a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f6367a.f6373a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i5, Bundle bundle) {
        WeakReference weakReference;
        ClickableSpan clickableSpan;
        b bVar = this.f6367a;
        bVar.getClass();
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        for (int i6 = 0; i6 < list.size() && ((AccessibilityNodeInfo.AccessibilityAction) ((x2.c) list.get(i6)).f6561a).getId() != i5; i6++) {
        }
        boolean zPerformAccessibilityAction = bVar.f6373a.performAccessibilityAction(view, i5, bundle);
        if (zPerformAccessibilityAction || i5 != R.id.accessibility_action_clickable_span || bundle == null) {
            return zPerformAccessibilityAction;
        }
        int i7 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray != null && (weakReference = (WeakReference) sparseArray.get(i7)) != null && (clickableSpan = (ClickableSpan) weakReference.get()) != null) {
            CharSequence text = view.createAccessibilityNodeInfo().getText();
            ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
            for (int i8 = 0; clickableSpanArr != null && i8 < clickableSpanArr.length; i8++) {
                if (clickableSpan.equals(clickableSpanArr[i8])) {
                    clickableSpan.onClick(view);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEvent(View view, int i5) {
        this.f6367a.f6373a.sendAccessibilityEvent(view, i5);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.f6367a.f6373a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
