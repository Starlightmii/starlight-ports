package x2;

import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f6553c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f6554d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f6555e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f6556f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f6557g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c f6558h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f6559i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final c f6560j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6561a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6562b;

    /* JADX WARN: Code duplicated, block: B:62:0x0206  */
    static {
        boolean z2;
        new c(null, 1, null, null);
        new c(null, 2, null, null);
        new c(null, 4, null, null);
        new c(null, 8, null, null);
        new c(null, 16, null, null);
        new c(null, 32, null, null);
        f6553c = new c(null, 64, null, null);
        f6554d = new c(null, 128, null, null);
        new c(null, 256, null, j.class);
        new c(null, 512, null, j.class);
        new c(null, 1024, null, k.class);
        new c(null, 2048, null, k.class);
        f6555e = new c(null, 4096, null, null);
        f6556f = new c(null, 8192, null, null);
        new c(null, 16384, null, null);
        new c(null, 32768, null, null);
        new c(null, 65536, null, null);
        new c(null, 131072, null, o.class);
        new c(null, 262144, null, null);
        new c(null, 524288, null, null);
        new c(null, 1048576, null, null);
        new c(null, 2097152, null, p.class);
        new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null);
        new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, m.class);
        f6557g = new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null);
        f6558h = new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null);
        f6559i = new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null);
        f6560j = new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null);
        int i5 = Build.VERSION.SDK_INT;
        new c(i5 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null);
        new c(i5 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null);
        new c(i5 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null);
        new c(i5 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null);
        new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null);
        new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, n.class);
        new c(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, l.class);
        new c(i5 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null);
        new c(i5 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null);
        new c(i5 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null);
        new c(i5 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null);
        new c(i5 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START : null, R.id.accessibilityActionDragStart, null, null);
        new c(i5 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP : null, R.id.accessibilityActionDragDrop, null, null);
        new c(i5 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL : null, R.id.accessibilityActionDragCancel, null, null);
        new c(i5 >= 33 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS : null, R.id.accessibilityActionShowTextSuggestions, null, null);
        new c(i5 >= 34 ? e.a() : null, R.id.accessibilityActionScrollInDirection, null, null);
        int i6 = t2.c.f5801a;
        if (i5 >= 36) {
            z2 = (i5 < 36 ? i5 * 100000 : Build.VERSION.SDK_INT_FULL) >= 3600001;
        }
        new c(z2 ? g.a() : null, R.id.ALT, null, null);
    }

    public c(int i5, String str) {
        this(null, i5, str, null);
    }

    public c(Object obj, int i5, CharSequence charSequence, Class cls) {
        this.f6562b = i5;
        if (obj == null) {
            this.f6561a = new AccessibilityNodeInfo.AccessibilityAction(i5, charSequence);
        } else {
            this.f6561a = obj;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        Object obj2 = ((c) obj).f6561a;
        Object obj3 = this.f6561a;
        if (obj3 == null) {
            return obj2 == null;
        }
        return obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f6561a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AccessibilityActionCompat: ");
        String strC = h.c(this.f6562b);
        if (strC.equals("ACTION_UNKNOWN")) {
            Object obj = this.f6561a;
            if (((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel() != null) {
                strC = ((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel().toString();
            }
        }
        sb.append(strC);
        return sb.toString();
    }
}
