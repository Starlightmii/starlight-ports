package com.oplus.systemui.notification.stackednotification.olive;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.animation.Interpolator;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.olivelink.SpringType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupExpansionAnimParams {
    public static final boolean DEBUG_OLIVE;
    public static final GroupExpansionAnimParams INSTANCE = new GroupExpansionAnimParams();
    private static final String SERVER_NAME = "组通知展开/收起";
    private static final Lazy childrenCollapsedInterpolatorSet$delegate;
    private static final Lazy childrenExpandedInterpolatorSet$delegate;
    private static final Lazy childrenExpandedToNtfCenterInterpolatorSet$delegate;
    private static final ValueProviderStrategy valueProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GroupCollapseParams extends OLiveLinkGroup {
        public static final GroupCollapseParams INSTANCE;
        private static final Scalar collapseChildScaleDelay;
        private static final Scalar collapseChildScaleDelayExtra;
        private static final Spring collapseChildScaleSpring;
        private static final Spring collapseChildTranslationYSpring;
        private static final Scalar collapseDuration;
        private static final Spring collapseGroupContentAlphaSpring;
        private static final Spring collapseGroupHeaderAlphaSpring;
        private static final Spring collapseGroupHeightSpring;
        private static final Spring collapseProgressSpring;
        private static final Scalar collapseSplit;

        static {
            GroupCollapseParams groupCollapseParams = new GroupCollapseParams(GroupExpansionAnimParams.SERVER_NAME, "组通知收起场景");
            INSTANCE = groupCollapseParams;
            collapseChildScaleDelay = groupCollapseParams.safeScalar("子通知缩放基础延时", 0L);
            collapseChildScaleDelayExtra = groupCollapseParams.safeScalar("子通知缩放额外延时", 0L);
            collapseProgressSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "整体进度控制(alpha+高度剪裁)", 0.01f, 0.4f);
            collapseDuration = groupCollapseParams.safeScalar("收起时长", 900L);
            collapseGroupHeaderAlphaSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "标题Alpha", 0.01f, 0.36f);
            collapseGroupContentAlphaSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "子卡内容Alpha", 0.01f, 0.3f);
            collapseChildScaleSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "各子通知缩放", 0.01f, 0.99f);
            collapseChildTranslationYSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "各子通知位移", 0.01f, 0.35f);
            collapseGroupHeightSpring = OLiveLinkGroup.safeSpring$default(groupCollapseParams, "整组高度", 0.01f, 0.38f);
            collapseSplit = groupCollapseParams.safeScalar("遮罩淡出和剪裁分割点", Float.valueOf(0.4f));
        }

        public final Scalar getCollapseChildScaleDelay() {
            return collapseChildScaleDelay;
        }

        public final Scalar getCollapseChildScaleDelayExtra() {
            return collapseChildScaleDelayExtra;
        }

        public final Spring getCollapseChildScaleSpring() {
            return collapseChildScaleSpring;
        }

        public final Spring getCollapseChildTranslationYSpring() {
            return collapseChildTranslationYSpring;
        }

        public final Scalar getCollapseDuration() {
            return collapseDuration;
        }

        public final Spring getCollapseGroupContentAlphaSpring() {
            return collapseGroupContentAlphaSpring;
        }

        public final Spring getCollapseGroupHeaderAlphaSpring() {
            return collapseGroupHeaderAlphaSpring;
        }

        public final Spring getCollapseGroupHeightSpring() {
            return collapseGroupHeightSpring;
        }

        public final Spring getCollapseProgressSpring() {
            return collapseProgressSpring;
        }

        public final Scalar getCollapseSplit() {
            return collapseSplit;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GroupExpandParams extends OLiveLinkGroup {
        public static final GroupExpandParams INSTANCE;
        private static final Scalar expandChildScaleDelay;
        private static final Scalar expandChildScaleDelayExtra;
        private static final Spring expandChildScaleSpring;
        private static final Spring expandChildTranslationYSpring;
        private static final Scalar expandDuration;
        private static final Spring expandGroupContentAlphaSpring;
        private static final Spring expandGroupHeaderAlphaSpring;
        private static final Spring expandGroupHeightSpring;
        private static final Spring expandProgressSpring;
        private static final Scalar expandSplit;

        static {
            GroupExpandParams groupExpandParams = new GroupExpandParams(GroupExpansionAnimParams.SERVER_NAME, "组通知展开场景");
            INSTANCE = groupExpandParams;
            expandChildScaleDelay = groupExpandParams.safeScalar("子通知缩放基础延时", 0L);
            expandChildScaleDelayExtra = groupExpandParams.safeScalar("子通知缩放额外延时", 0L);
            expandProgressSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "整体进度控制(alpha+高度剪裁)", 0.01f, 0.5f);
            expandDuration = groupExpandParams.safeScalar("展开时长", 800L);
            expandGroupHeaderAlphaSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "标题Alpha", 0.01f, 0.3f);
            expandGroupContentAlphaSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "子卡内容Alpha", 0.01f, 0.3f);
            expandChildScaleSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "各子通知缩放", 0.2f, 0.99f);
            expandChildTranslationYSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "各子通知位移", 0.3f, 0.4f);
            expandGroupHeightSpring = OLiveLinkGroup.safeSpring$default(groupExpandParams, "整组高度", 0.2f, 0.45f);
            expandSplit = groupExpandParams.safeScalar("遮罩淡出和剪裁分割点", Float.valueOf(0.3f));
        }

        public final Scalar getExpandChildScaleDelay() {
            return expandChildScaleDelay;
        }

        public final Scalar getExpandChildScaleDelayExtra() {
            return expandChildScaleDelayExtra;
        }

        public final Spring getExpandChildScaleSpring() {
            return expandChildScaleSpring;
        }

        public final Spring getExpandChildTranslationYSpring() {
            return expandChildTranslationYSpring;
        }

        public final Scalar getExpandDuration() {
            return expandDuration;
        }

        public final Spring getExpandGroupContentAlphaSpring() {
            return expandGroupContentAlphaSpring;
        }

        public final Spring getExpandGroupHeaderAlphaSpring() {
            return expandGroupHeaderAlphaSpring;
        }

        public final Spring getExpandGroupHeightSpring() {
            return expandGroupHeightSpring;
        }

        public final Spring getExpandProgressSpring() {
            return expandProgressSpring;
        }

        public final Scalar getExpandSplit() {
            return expandSplit;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InterpolatorSet {
        private final Interpolator childScaleInterpolator;
        private final Interpolator childTranslationInterpolator;
        private final Interpolator groupContentAlphaInterpolator;
        private final Interpolator groupExpansionProgressInterpolator;
        private final Interpolator groupHeaderAlphaInterpolator;
        private final Interpolator groupHeightInterpolator;

        public InterpolatorSet(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, Interpolator interpolator4, Interpolator interpolator5, Interpolator interpolator6) {
            this.groupExpansionProgressInterpolator = interpolator;
            this.groupHeaderAlphaInterpolator = interpolator2;
            this.groupContentAlphaInterpolator = interpolator3;
            this.childScaleInterpolator = interpolator4;
            this.groupHeightInterpolator = interpolator5;
            this.childTranslationInterpolator = interpolator6;
        }

        public final Interpolator component1() {
            return this.groupExpansionProgressInterpolator;
        }

        public final Interpolator component2() {
            return this.groupHeaderAlphaInterpolator;
        }

        public final Interpolator component3() {
            return this.groupContentAlphaInterpolator;
        }

        public final Interpolator component4() {
            return this.childScaleInterpolator;
        }

        public final Interpolator component5() {
            return this.groupHeightInterpolator;
        }

        public final Interpolator component6() {
            return this.childTranslationInterpolator;
        }

        public final InterpolatorSet copy(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, Interpolator interpolator4, Interpolator interpolator5, Interpolator interpolator6) {
            return new InterpolatorSet(interpolator, interpolator2, interpolator3, interpolator4, interpolator5, interpolator6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InterpolatorSet)) {
                return false;
            }
            InterpolatorSet interpolatorSet = (InterpolatorSet) obj;
            return Intrinsics.areEqual(this.groupExpansionProgressInterpolator, interpolatorSet.groupExpansionProgressInterpolator) && Intrinsics.areEqual(this.groupHeaderAlphaInterpolator, interpolatorSet.groupHeaderAlphaInterpolator) && Intrinsics.areEqual(this.groupContentAlphaInterpolator, interpolatorSet.groupContentAlphaInterpolator) && Intrinsics.areEqual(this.childScaleInterpolator, interpolatorSet.childScaleInterpolator) && Intrinsics.areEqual(this.groupHeightInterpolator, interpolatorSet.groupHeightInterpolator) && Intrinsics.areEqual(this.childTranslationInterpolator, interpolatorSet.childTranslationInterpolator);
        }

        public final Interpolator getChildScaleInterpolator() {
            return this.childScaleInterpolator;
        }

        public final Interpolator getChildTranslationInterpolator() {
            return this.childTranslationInterpolator;
        }

        public final Interpolator getGroupContentAlphaInterpolator() {
            return this.groupContentAlphaInterpolator;
        }

        public final Interpolator getGroupExpansionProgressInterpolator() {
            return this.groupExpansionProgressInterpolator;
        }

        public final Interpolator getGroupHeaderAlphaInterpolator() {
            return this.groupHeaderAlphaInterpolator;
        }

        public final Interpolator getGroupHeightInterpolator() {
            return this.groupHeightInterpolator;
        }

        public final int hashCode() {
            return this.childTranslationInterpolator.hashCode() + ((this.groupHeightInterpolator.hashCode() + ((this.childScaleInterpolator.hashCode() + ((this.groupContentAlphaInterpolator.hashCode() + ((this.groupHeaderAlphaInterpolator.hashCode() + (this.groupExpansionProgressInterpolator.hashCode() * 31)) * 31)) * 31)) * 31)) * 31);
        }

        public String toString() {
            return "InterpolatorSet(groupExpansionProgressInterpolator=" + this.groupExpansionProgressInterpolator + ", groupHeaderAlphaInterpolator=" + this.groupHeaderAlphaInterpolator + ", groupContentAlphaInterpolator=" + this.groupContentAlphaInterpolator + ", childScaleInterpolator=" + this.childScaleInterpolator + ", groupHeightInterpolator=" + this.groupHeightInterpolator + ", childTranslationInterpolator=" + this.childTranslationInterpolator + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class OLiveLinkGroup {
        private final String groupName;
        private final String serverName;

        public OLiveLinkGroup(String str, String str2) {
            this.serverName = str;
            this.groupName = str2;
        }

        public static /* synthetic */ Spring safeSpring$default(OLiveLinkGroup oLiveLinkGroup, String str, float f, float f2) {
            return oLiveLinkGroup.safeSpring(str, f, f2, 0.0f, SpringType.SPRING_ANIMATION);
        }

        public final <T extends Number> Scalar safeScalar(String str, T t) {
            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, this.serverName, CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(this.groupName, ": ", str), this.groupName, t, 16);
        }

        public final Spring safeSpring(String str, float f, float f2, float f3, SpringType springType) {
            return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, this.serverName, CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(this.groupName, ": ", str), f, f2, f3, 0.0f, 0.0f, springType, null, this.groupName, 352);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ReleaseValueProvider implements ValueProviderStrategy {
        @Override // com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams.ValueProviderStrategy
        public Interpolator getInterpolator(Spring spring, double d, double d2) {
            return new OplusPhysicsInterpolator(d, d2, (Float) null);
        }

        @Override // com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams.ValueProviderStrategy
        public <T extends Number> T getScalar(Scalar scalar, T t) {
            return t;
        }

        @Override // com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams.ValueProviderStrategy
        public void initOliveServer(Context context) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ValueProviderStrategy {
        Interpolator getInterpolator(Spring spring, double d, double d2);

        <T extends Number> T getScalar(Scalar scalar, T t);

        void initOliveServer(Context context);
    }

    /* JADX INFO: renamed from: $r8$lambda$-QOQ7nhh-ffUzNWRDOXuc5vyt7Q, reason: not valid java name */
    public static InterpolatorSet m100$r8$lambda$QOQ7nhhffUzNWRDOXuc5vyt7Q() {
        ValueProviderStrategy valueProviderStrategy = valueProvider;
        boolean z = DEBUG_OLIVE;
        return new InterpolatorSet(z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandProgressSpring(), 0.01d, 0.5d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.5d), z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandGroupHeaderAlphaSpring(), 0.01d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.3d), z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandGroupContentAlphaSpring(), 0.01d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.3d), z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandChildScaleSpring(), 0.2d, 0.9d) : valueProviderStrategy.getInterpolator(null, 0.2d, 0.9d), z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandGroupHeightSpring(), 0.0d, 0.33d) : valueProviderStrategy.getInterpolator(null, 0.0d, 0.33d), z ? valueProviderStrategy.getInterpolator(GroupExpandParamsForLockScreenToNtfCenter.INSTANCE.getExpandChildTranslationYSpring(), 0.0d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.0d, 0.3d));
    }

    public static InterpolatorSet $r8$lambda$HMHer_d4KwCIwgq2vVEBJcvntRs() {
        ValueProviderStrategy valueProviderStrategy = valueProvider;
        boolean z = DEBUG_OLIVE;
        return new InterpolatorSet(z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseProgressSpring(), 0.01d, 0.4d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.4d), z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseGroupHeaderAlphaSpring(), 0.01d, 0.36d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.36d), z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseGroupContentAlphaSpring(), 0.01d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.3d), z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseChildScaleSpring(), 0.01d, 0.99d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.99d), z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseGroupHeightSpring(), 0.01d, 0.38d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.38d), z ? valueProviderStrategy.getInterpolator(GroupCollapseParams.INSTANCE.getCollapseChildTranslationYSpring(), 0.01d, 0.35d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.35d));
    }

    /* JADX INFO: renamed from: $r8$lambda$kJ9zpt4LN8Ou7oLhwlJ-Xsw9xLg, reason: not valid java name */
    public static InterpolatorSet m101$r8$lambda$kJ9zpt4LN8Ou7oLhwlJXsw9xLg() {
        ValueProviderStrategy valueProviderStrategy = valueProvider;
        boolean z = DEBUG_OLIVE;
        return new InterpolatorSet(z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandProgressSpring(), 0.01d, 0.5d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.5d), z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandGroupHeaderAlphaSpring(), 0.01d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.3d), z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandGroupContentAlphaSpring(), 0.01d, 0.3d) : valueProviderStrategy.getInterpolator(null, 0.01d, 0.3d), z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandChildScaleSpring(), 0.2d, 0.99d) : valueProviderStrategy.getInterpolator(null, 0.2d, 0.99d), z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandGroupHeightSpring(), 0.2d, 0.45d) : valueProviderStrategy.getInterpolator(null, 0.2d, 0.45d), z ? valueProviderStrategy.getInterpolator(GroupExpandParams.INSTANCE.getExpandChildTranslationYSpring(), 0.3d, 0.4d) : valueProviderStrategy.getInterpolator(null, 0.3d, 0.4d));
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0038  */
    static {
        boolean z;
        Boolean booleanStrictOrNull;
        final int i = 1;
        final int i2 = 0;
        if (Build.IS_DEBUGGABLE) {
            Context context = (Context) DependencyEx.sDependency.getDependency(Context.class);
            String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "oplus_debug_group_expansion_anim", context.getUserId());
            if ((stringForUser == null || (booleanStrictOrNull = StringsKt__StringsKt.toBooleanStrictOrNull(stringForUser)) == null) ? false : booleanStrictOrNull.booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        DEBUG_OLIVE = z;
        valueProvider = z ? new DebugValueProvider() : new ReleaseValueProvider();
        childrenCollapsedInterpolatorSet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return GroupExpansionAnimParams.$r8$lambda$HMHer_d4KwCIwgq2vVEBJcvntRs();
                    case 1:
                        return GroupExpansionAnimParams.m100$r8$lambda$QOQ7nhhffUzNWRDOXuc5vyt7Q();
                    default:
                        return GroupExpansionAnimParams.m101$r8$lambda$kJ9zpt4LN8Ou7oLhwlJXsw9xLg();
                }
            }
        });
        childrenExpandedToNtfCenterInterpolatorSet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return GroupExpansionAnimParams.$r8$lambda$HMHer_d4KwCIwgq2vVEBJcvntRs();
                    case 1:
                        return GroupExpansionAnimParams.m100$r8$lambda$QOQ7nhhffUzNWRDOXuc5vyt7Q();
                    default:
                        return GroupExpansionAnimParams.m101$r8$lambda$kJ9zpt4LN8Ou7oLhwlJXsw9xLg();
                }
            }
        });
        final int i3 = 2;
        childrenExpandedInterpolatorSet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return GroupExpansionAnimParams.$r8$lambda$HMHer_d4KwCIwgq2vVEBJcvntRs();
                    case 1:
                        return GroupExpansionAnimParams.m100$r8$lambda$QOQ7nhhffUzNWRDOXuc5vyt7Q();
                    default:
                        return GroupExpansionAnimParams.m101$r8$lambda$kJ9zpt4LN8Ou7oLhwlJXsw9xLg();
                }
            }
        });
    }

    public static long getChildScaleDelayBase(boolean z) {
        boolean z2 = DEBUG_OLIVE;
        if (z) {
            ValueProviderStrategy valueProviderStrategy = valueProvider;
            return (z2 ? valueProviderStrategy.getScalar(GroupExpandParams.INSTANCE.getExpandChildScaleDelay(), 0L) : valueProviderStrategy.getScalar(null, 0L)).longValue();
        }
        ValueProviderStrategy valueProviderStrategy2 = valueProvider;
        return (z2 ? valueProviderStrategy2.getScalar(GroupCollapseParams.INSTANCE.getCollapseChildScaleDelay(), 0L) : valueProviderStrategy2.getScalar(null, 0L)).longValue();
    }

    public static long getChildScaleDelayExtra(boolean z) {
        boolean z2 = DEBUG_OLIVE;
        if (z) {
            ValueProviderStrategy valueProviderStrategy = valueProvider;
            return (z2 ? valueProviderStrategy.getScalar(GroupExpandParams.INSTANCE.getExpandChildScaleDelayExtra(), 0L) : valueProviderStrategy.getScalar(null, 0L)).longValue();
        }
        ValueProviderStrategy valueProviderStrategy2 = valueProvider;
        return (z2 ? valueProviderStrategy2.getScalar(GroupCollapseParams.INSTANCE.getCollapseChildScaleDelayExtra(), 0L) : valueProviderStrategy2.getScalar(null, 0L)).longValue();
    }

    private final InterpolatorSet getChildrenCollapsedInterpolatorSet() {
        return (InterpolatorSet) childrenCollapsedInterpolatorSet$delegate.getValue();
    }

    private final InterpolatorSet getChildrenExpandedInterpolatorSet() {
        return (InterpolatorSet) childrenExpandedInterpolatorSet$delegate.getValue();
    }

    private final InterpolatorSet getChildrenExpandedToNtfCenterInterpolatorSet() {
        return (InterpolatorSet) childrenExpandedToNtfCenterInterpolatorSet$delegate.getValue();
    }

    public static long getDuration(boolean z) {
        boolean z2 = DEBUG_OLIVE;
        if (z) {
            ValueProviderStrategy valueProviderStrategy = valueProvider;
            return (z2 ? valueProviderStrategy.getScalar(GroupExpandParams.INSTANCE.getExpandDuration(), 800L) : valueProviderStrategy.getScalar(null, 800L)).longValue();
        }
        ValueProviderStrategy valueProviderStrategy2 = valueProvider;
        return (z2 ? valueProviderStrategy2.getScalar(GroupCollapseParams.INSTANCE.getCollapseDuration(), 900L) : valueProviderStrategy2.getScalar(null, 900L)).longValue();
    }

    public static float getSplitPoint(boolean z) {
        boolean z2 = DEBUG_OLIVE;
        if (z) {
            ValueProviderStrategy valueProviderStrategy = valueProvider;
            Float fValueOf = Float.valueOf(0.3f);
            return (z2 ? valueProviderStrategy.getScalar(GroupExpandParams.INSTANCE.getExpandSplit(), fValueOf) : valueProviderStrategy.getScalar(null, fValueOf)).floatValue();
        }
        ValueProviderStrategy valueProviderStrategy2 = valueProvider;
        Float fValueOf2 = Float.valueOf(0.4f);
        return (z2 ? valueProviderStrategy2.getScalar(GroupCollapseParams.INSTANCE.getCollapseSplit(), fValueOf2) : valueProviderStrategy2.getScalar(null, fValueOf2)).floatValue();
    }

    public final InterpolatorSet getInterpolatorSet(boolean z, boolean z2) {
        if (z) {
            return z2 ? getChildrenExpandedToNtfCenterInterpolatorSet() : getChildrenExpandedInterpolatorSet();
        }
        return getChildrenCollapsedInterpolatorSet();
    }

    public final void initOlive(Context context) {
        valueProvider.initOliveServer(context);
    }
}
