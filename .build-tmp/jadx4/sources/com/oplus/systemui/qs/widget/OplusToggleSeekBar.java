package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.settingslib.RestrictedLockUtils;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.coui.appcompat.seekbar.COUISeekBar;
import java.util.Collections;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusToggleSeekBar extends COUISeekBar {
    private String mAccessibilityLabel;
    private RestrictedLockUtils.EnforcedAdmin mEnforcedAdmin;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusToggleSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, 12, (DefaultConstructorMarker) null);
        setThumb(null);
        setSystemGestureExclusionRects(Collections.EMPTY_LIST);
    }

    public final boolean extracted() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = this.mEnforcedAdmin;
        if (enforcedAdmin == null) {
            return false;
        }
        ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).postStartActivityDismissingKeyguard(RestrictedLockUtils.getShowAdminSupportDetailsIntent$1(enforcedAdmin), 0);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super/*android.widget.AbsSeekBar*/.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = this.mAccessibilityLabel;
        if (str != null) {
            accessibilityNodeInfo.setText(str);
        }
    }

    public final void setAccessibilityLabel(String str) {
        this.mAccessibilityLabel = str;
    }

    public final void setEnforcedAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mEnforcedAdmin = enforcedAdmin;
    }

    public OplusToggleSeekBar(Context context) {
        super(context, (AttributeSet) null, 0, 0, 14, (DefaultConstructorMarker) null);
    }

    public OplusToggleSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0, 8, (DefaultConstructorMarker) null);
    }
}
