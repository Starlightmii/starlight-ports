package com.oplus.systemui.notification.base;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import com.android.systemui.statusbar.notification.OplusPropertyData;
import com.android.systemui.statusbar.notification.PropertyData;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RowAnimatorExt {
    public ArrayList<String> formatAnimatorDumpString(Collection<?> collection, ViewGroup viewGroup) {
        return new ArrayList<>();
    }

    public long getSingleHeightAnimDuration(ExpandableView expandableView, long j) {
        return 0L;
    }

    public void onAnimatorStart(View view, Animator animator, int i) {
    }

    public void onCouiSpringAnimatorStart(View view, COUISpringAnimation cOUISpringAnimation, OplusPhysicsProperty oplusPhysicsProperty, AnimationProperties animationProperties, OplusPropertyData oplusPropertyData, long j, boolean z) {
    }

    public void onSpringAnimatorStart(View view, SpringAnimation springAnimation, int i, PropertyData propertyData) {
    }
}
