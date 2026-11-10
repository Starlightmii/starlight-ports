package com.oplus.systemui.notification.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.AlphaOptimizedView;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.OplusPropertyData;
import com.android.systemui.statusbar.notification.PropertyData;
import com.android.systemui.statusbar.notification.mergeanimator.OplusMergedAnimator;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.kotlin.UtilsKt;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RowAnimatorExtImpl extends RowAnimatorExt {
    private static final String TAG = "RowAnimatorExtImpl";
    private final ConfigurationController configurationController;
    private final Context context;
    private final Lazy oplusLockscreenShadeTransitionController;
    public int screenHeight;
    public static final Companion Companion = new Companion();
    public static final boolean DEBUG = Build.IS_DEBUGGABLE;
    private static final int[] ANIMATOR_TYPE_TAGS = {2131365784, 2131365788, 2131365780, 2131364988, 2131364992, 2131362019, 2131363352, 2131365722, 2131362310, 2131362558, 2131362056, 2131365340, 2131366070, 2131364189, 2131364186, 2131364183, 2131362291, 2131362059, 2131362063, 2131364619, 2131362650, 2131362645};

    /* JADX INFO: renamed from: com.oplus.systemui.notification.base.RowAnimatorExtImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ RowAnimatorExtImpl this$0;

        public final void onConfigChanged(Configuration configuration) {
            RowAnimatorExtImpl rowAnimatorExtImpl = this.this$0;
            rowAnimatorExtImpl.screenHeight = UtilsKt.toPx(rowAnimatorExtImpl.context.getResources().getConfiguration().screenHeightDp, rowAnimatorExtImpl.context);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.base.RowAnimatorExtImpl$onAnimatorStart$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements Animator.AnimatorListener {
        public /* synthetic */ Animator $animator;
        public /* synthetic */ String $tagId;
        public /* synthetic */ String $typeS;

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            String str = this.$tagId;
            String str2 = this.$typeS;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("cancel:", str, OplusPreventModeData.SEPARATOR, str2, "RowAnimatorExtImpl-->"));
            }
            this.$animator.removeListener(this);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            String str = this.$tagId;
            String str2 = this.$typeS;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("end:", str, OplusPreventModeData.SEPARATOR, str2, "RowAnimatorExtImpl-->"));
            }
            this.$animator.removeListener(this);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            String str = this.$tagId;
            String str2 = this.$typeS;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("start:", str, OplusPreventModeData.SEPARATOR, str2, "RowAnimatorExtImpl-->"));
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.base.RowAnimatorExtImpl$onAnimatorStart$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements Animator.AnimatorListener {
        public /* synthetic */ Animator $animator;

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.$animator.removeListener(this);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.$animator.removeListener(this);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.base.RowAnimatorExtImpl$onCouiSpringAnimatorStart$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements COUIDynamicAnimation.OnAnimationEndListener {
        public /* synthetic */ COUISpringAnimation $animator;
        public /* synthetic */ String $tagId;
        public /* synthetic */ String $typeS;

        public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
            String str = this.$typeS;
            String str2 = this.$tagId;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("end:couispring ", str, " ", str2, "RowAnimatorExtImpl-->"));
            }
            this.$animator.removeEndListener(this);
        }
    }

    public RowAnimatorExtImpl(Context context, ConfigurationController configurationController, Lazy lazy) {
        this.context = context;
        this.configurationController = configurationController;
        this.oplusLockscreenShadeTransitionController = lazy;
        this.screenHeight = UtilsKt.toPx(context.getResources().getConfiguration().screenHeightDp, context);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public static void collectRunningAnimatorLines(View view, Set set, List list) {
        List listEmptyList;
        Iterator it;
        String string;
        int[] iArr = ANIMATOR_TYPE_TAGS;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            view = view;
            Object tag = view.getTag(i2);
            if (tag != null) {
                if (tag instanceof Animator) {
                    listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(tag);
                } else if (tag instanceof PropertyData) {
                    listEmptyList = CollectionsKt__CollectionsKt.listOfNotNull(((PropertyData) tag).getAnimator());
                } else if (tag instanceof OplusPropertyData) {
                    OplusPropertyData oplusPropertyData = (OplusPropertyData) tag;
                    COUISpringAnimation animator = oplusPropertyData.getAnimator();
                    OplusMergedAnimator mergedAnimator = oplusPropertyData.getMergedAnimator();
                    listEmptyList = CollectionsKt__CollectionsKt.listOfNotNull(new COUISpringAnimation[]{animator, mergedAnimator != null ? mergedAnimator.getAnimation() : null});
                } else {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                Iterator it2 = listEmptyList.iterator();
                while (it2.hasNext()) {
                    if (set.remove(it2.next())) {
                        String strTypeToString = typeToString(i2);
                        String strTagId = RowAnimatorExtImplKt.tagId(view);
                        if (tag instanceof ObjectAnimator) {
                            ObjectAnimator objectAnimator = (ObjectAnimator) tag;
                            Object objFirstOrNull = ArraysKt___ArraysKt.firstOrNull(objectAnimator.getValues());
                            long currentPlayTime = objectAnimator.getCurrentPlayTime();
                            long duration = objectAnimator.getDuration();
                            boolean zIsStarted = objectAnimator.isStarted();
                            StringBuilder sb = new StringBuilder();
                            sb.append(strTypeToString);
                            sb.append(OplusPreventModeData.SEPARATOR);
                            sb.append(strTagId);
                            sb.append(OplusPreventModeData.SEPARATOR);
                            sb.append(objFirstOrNull);
                            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sb, ",play=", currentPlayTime, "/");
                            sb.append(duration);
                            sb.append(",started=");
                            sb.append(zIsStarted);
                            string = sb.toString();
                            it = it2;
                        } else if (tag instanceof ValueAnimator) {
                            ValueAnimator valueAnimator = (ValueAnimator) tag;
                            Object objFirstOrNull2 = ArraysKt___ArraysKt.firstOrNull(valueAnimator.getValues());
                            long currentPlayTime2 = valueAnimator.getCurrentPlayTime();
                            it = it2;
                            long duration2 = valueAnimator.getDuration();
                            boolean zIsStarted2 = valueAnimator.isStarted();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(strTypeToString);
                            sb2.append(OplusPreventModeData.SEPARATOR);
                            sb2.append(strTagId);
                            sb2.append(OplusPreventModeData.SEPARATOR);
                            sb2.append(objFirstOrNull2);
                            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sb2, ",play=", currentPlayTime2, "/");
                            sb2.append(duration2);
                            sb2.append(",started=");
                            sb2.append(zIsStarted2);
                            string = sb2.toString();
                        } else {
                            it = it2;
                            if (tag instanceof OplusPropertyData) {
                                OplusPropertyData oplusPropertyData2 = (OplusPropertyData) tag;
                                float offset = oplusPropertyData2.getOffset() + oplusPropertyData2.getFinalValue();
                                float finalValue = oplusPropertyData2.getFinalValue();
                                boolean zIsRunning = oplusPropertyData2.isRunning();
                                boolean z = oplusPropertyData2.getMergedAnimator() != null;
                                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("couispring:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, offset, " -> ", finalValue, ",running=");
                                string = KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, zIsRunning, ",merged=", z);
                            } else if (tag instanceof PropertyData) {
                                PropertyData propertyData = (PropertyData) tag;
                                float startOffset = propertyData.getStartOffset() + propertyData.getFinalValue();
                                float finalValue2 = propertyData.getFinalValue();
                                SpringAnimation animator2 = propertyData.getAnimator();
                                Boolean boolValueOf = animator2 != null ? Boolean.valueOf(animator2.isRunning()) : null;
                                StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("spring:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, startOffset, " -> ", finalValue2, ",running=");
                                sbM2.append(boolValueOf);
                                string = sbM2.toString();
                            } else {
                                string = strTypeToString + OplusPreventModeData.SEPARATOR + strTagId + OplusPreventModeData.SEPARATOR + tag;
                            }
                        }
                        list.add(string);
                        it2 = it;
                        iArr = iArr;
                        length = length;
                        i = i;
                        i2 = i2;
                    } else {
                        length = length;
                    }
                }
            }
            i++;
            iArr = iArr;
            length = length;
        }
    }

    public static String typeToString(int i) {
        if (i == 2131365784) {
            return "tY";
        }
        if (i == 2131365788) {
            return "tZ";
        }
        if (i == 2131365780) {
            return "tx";
        }
        if (i == 2131364988) {
            return "sX";
        }
        if (i == 2131364992) {
            return "sY";
        }
        if (i == 2131362019) {
            return OplusVolumeTipsControler.PROPERTY_NAME_ALPHA;
        }
        if (i == 2131363352) {
            return "height";
        }
        if (i == 2131365722) {
            return "clipT";
        }
        if (i == 2131362310) {
            return "clipB";
        }
        if (i == 2131362558) {
            return "clipW";
        }
        if (i == 2131362056) {
            return "capsuleP";
        }
        if (i == 2131365340) {
            return "stackedCover";
        }
        if (i == 2131366070) {
            return "width";
        }
        if (i == 2131364189) {
            return "groupExpansionProgress";
        }
        if (i == 2131364186) {
            return "groupHeaderAlpha";
        }
        if (i == 2131364183) {
            return "groupContentAlpha";
        }
        if (i == 2131362291) {
            return "blurRadio";
        }
        if (i == 2131362059) {
            return "animationCornerRadius";
        }
        if (i == 2131362063) {
            return "immersiveP";
        }
        if (i == 2131364619) {
            return "capsulePressP";
        }
        if (i == 2131362650) {
            return "capsuleContentW";
        }
        return i == 2131362645 ? "capsuleContentS" : String.valueOf(i);
    }

    @Override // com.oplus.systemui.notification.base.RowAnimatorExt
    public ArrayList<String> formatAnimatorDumpString(Collection<?> collection, ViewGroup viewGroup) {
        List<ExpandableNotificationRow> attachedChildren;
        Set mutableSet = CollectionsKt___CollectionsKt.toMutableSet(CollectionsKt___CollectionsKt.filterNotNull(collection));
        ArrayList<String> arrayList = new ArrayList<>();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableNotificationRow childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                collectRunningAnimatorLines(childAt, mutableSet, arrayList);
                if (childAt instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow = childAt;
                    if (expandableNotificationRow.mIsSummaryWithChildren && (attachedChildren = expandableNotificationRow.getAttachedChildren()) != null) {
                        for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                            expandableNotificationRow2.getClass();
                            collectRunningAnimatorLines(expandableNotificationRow2, mutableSet, arrayList);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.oplus.systemui.notification.base.RowAnimatorExt
    public long getSingleHeightAnimDuration(ExpandableView expandableView, long j) {
        if (((OplusLockscreenShadeTransitionControllerExImpl) this.oplusLockscreenShadeTransitionController.get()).isAnimatingToCollapse() && (expandableView instanceof ExpandableNotificationRow)) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (expandableNotificationRow.mIsSummaryWithChildren) {
                OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableNotificationRow.getViewState().mExpandableViewStateEx;
                if ((oplusExpandableViewStateEx != null ? oplusExpandableViewStateEx.getNotificationStackedProgress() : 0.0f) > 0.0f && expandableNotificationRow.getTranslationY() + expandableNotificationRow.getActualHeight() > this.screenHeight) {
                    if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        return 250L;
                    }
                    Log.d("SystemUi--Notification", "RowAnimatorExtImpl-->customize height anim duration");
                    return 250L;
                }
            }
        }
        return j;
    }

    @Override // com.oplus.systemui.notification.base.RowAnimatorExt
    public void onAnimatorStart(View view, Animator animator, int i) {
        if (view instanceof AlphaOptimizedView) {
            return;
        }
        if (DEBUG) {
            String strTagId = RowAnimatorExtImplKt.tagId(view);
            String strTypeToString = typeToString(i);
            if (animator instanceof ObjectAnimator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    PropertyValuesHolder propertyValuesHolder = ((ObjectAnimator) animator).getValues()[0];
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("init:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                    sbM.append(propertyValuesHolder);
                    Log.d("SystemUi--Notification", "RowAnimatorExtImpl-->".concat(sbM.toString()));
                }
            } else if (animator instanceof ValueAnimator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    PropertyValuesHolder propertyValuesHolder2 = ((ValueAnimator) animator).getValues()[0];
                    StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("init:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                    sbM2.append(propertyValuesHolder2);
                    Log.d("SystemUi--Notification", "RowAnimatorExtImpl-->".concat(sbM2.toString()));
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM3 = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "init:", OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                sbM3.append(animator);
                Log.d("SystemUi--Notification", "RowAnimatorExtImpl-->".concat(sbM3.toString()));
            }
            AnonymousClass4 anonymousClass4 = new AnonymousClass4();
            anonymousClass4.$tagId = strTagId;
            anonymousClass4.$typeS = strTypeToString;
            anonymousClass4.$animator = animator;
            VarHandle.storeStoreFence();
            animator.addListener(anonymousClass4);
        }
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.$animator = animator;
        VarHandle.storeStoreFence();
        animator.addListener(anonymousClass5);
    }

    @Override // com.oplus.systemui.notification.base.RowAnimatorExt
    public void onCouiSpringAnimatorStart(View view, COUISpringAnimation cOUISpringAnimation, OplusPhysicsProperty oplusPhysicsProperty, AnimationProperties animationProperties, OplusPropertyData oplusPropertyData, long j, boolean z) {
        if (DEBUG) {
            String strTagId = RowAnimatorExtImplKt.tagId(view);
            String strTypeToString = typeToString(oplusPhysicsProperty.getTag());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Pair bounceAndResponse = OplusPhysicsPropertyAnimator.INSTANCE.getBounceAndResponse(animationProperties, view, oplusPhysicsProperty);
                float fFloatValue = ((Number) bounceAndResponse.component1()).floatValue();
                float fFloatValue2 = ((Number) bounceAndResponse.component2()).floatValue();
                float offset = oplusPropertyData.getOffset() + oplusPropertyData.getFinalValue();
                float finalValue = oplusPropertyData.getFinalValue();
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("init:couispring:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR);
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, offset, " -> ", finalValue, ",delay:");
                sbM.append(j);
                sbM.append(",b=");
                sbM.append(fFloatValue);
                sbM.append(",r=");
                sbM.append(fFloatValue2);
                Log.d("SystemUi--Notification", "RowAnimatorExtImpl-->".concat(sbM.toString()));
            }
            if (z) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                anonymousClass2.$animator = cOUISpringAnimation;
                anonymousClass2.$typeS = strTypeToString;
                anonymousClass2.$tagId = strTagId;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(anonymousClass2);
            }
        }
    }

    @Override // com.oplus.systemui.notification.base.RowAnimatorExt
    public void onSpringAnimatorStart(View view, SpringAnimation springAnimation, int i, PropertyData propertyData) {
        if (DEBUG) {
            String strTagId = RowAnimatorExtImplKt.tagId(view);
            String strTypeToString = typeToString(i);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(ExifInterface$.ExternalSyntheticOutline1.m("init:spring:", strTypeToString, OplusPreventModeData.SEPARATOR, strTagId, OplusPreventModeData.SEPARATOR), propertyData.getStartOffset() + propertyData.getFinalValue(), " -> ", propertyData.getFinalValue(), "RowAnimatorExtImpl-->"));
            }
            onSpringAnimatorStart.2 r6 = new onSpringAnimatorStart.2();
            r6.$animator = springAnimation;
            r6.$typeS = strTypeToString;
            r6.$tagId = strTagId;
            VarHandle.storeStoreFence();
            springAnimation.addEndListener(r6);
        }
    }
}
