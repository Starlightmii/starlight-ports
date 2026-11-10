package com.oplus.systemui.notification.lockscreen.statemanager;

import android.util.Property;
import androidx.collection.ArraySet;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ICardControlKt {
    public static final AnimationFilter getFilter() {
        AnimationFilter animationFilter = new AnimationFilter();
        animationFilter.animateAlpha = true;
        animationFilter.animateHideSensitive = true;
        animationFilter.animateHeight = true;
        animationFilter.animateTopInset = true;
        animationFilter.animateY = true;
        animationFilter.animateZ = true;
        animationFilter.animateScale();
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        Property property = companion.getCLIP_WIDTH_PROPERTY().getProperty();
        ArraySet arraySet = animationFilter.mAnimatedProperties;
        arraySet.add(property);
        arraySet.add(companion.getBLUR_RADIO_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_CAPSULE_PROGRESS_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_IMMERSIVE_PROGRESS_PROPERTY().getProperty());
        animationFilter.hasDelays = true;
        return animationFilter;
    }
}
