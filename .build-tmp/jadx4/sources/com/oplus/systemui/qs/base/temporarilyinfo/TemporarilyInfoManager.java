package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.ValueAnimator;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.oplus.systemui.qs.base.util.SpecialLanguageTextScaleHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager {
    public static final String GROUP_NON = "group_non";
    private static final String TAG = "TemporarilyInfoManager";
    private final TemporarilyInfoManager$attachStateChangeListener$1 attachStateChangeListener;
    public int currentViewIndex;
    private final IRemoveDuplicateStrategy duplicateStrategy;
    private final Lazy elements$delegate;
    public final ValueAnimator fadeInAnimator;
    public final ValueAnimator fadeOutAnimator;
    private TemporarilyInfoView fullShownTextView;
    private Function0 getLandSplitMode;
    private Runnable hideAction;
    public float initialDensity;
    public boolean isLandSplitLayout;
    public boolean isLandSplitMode;
    public boolean isShowing;
    private ViewGroup privacyContainerView;
    private ViewGroup qsQuickEntranceContainerView;
    private ViewGroup qsRootView;
    private Function0 quickEntranceTranslationYReset;
    public float scaleResult;
    public int separateExtraSpace;
    private Element showingElement;
    private SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper;
    public boolean started;
    private ViewGroup statusBarContainerView;
    private TemporarilyInfoView temporarilyInfoView;
    private List<TemporarilyInfoView> viewPool;
    public static final Companion Companion = new Companion();
    private static final InfoShowStyle defaultShowStyle = new InfoShowStyle(0, 31);
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new TemporarilyInfoManager$$ExternalSyntheticLambda0(0));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final TemporarilyInfoManager getInstance() {
            return (TemporarilyInfoManager) TemporarilyInfoManager.instance$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Element {
        private final String groupId;
        private final InfoShowStyle style;
        private final String subText;
        private final String text;

        public Element(String str, String str2, String str3, InfoShowStyle infoShowStyle) {
            this.text = str;
            this.subText = str2;
            this.groupId = str3;
            this.style = infoShowStyle;
        }

        public final String component1() {
            return this.text;
        }

        public final String component2() {
            return this.subText;
        }

        public final String component3() {
            return this.groupId;
        }

        public final InfoShowStyle component4() {
            return this.style;
        }

        public final Element copy(String str, String str2, String str3, InfoShowStyle infoShowStyle) {
            return new Element(str, str2, str3, infoShowStyle);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Element)) {
                return false;
            }
            Element element = (Element) obj;
            return Intrinsics.areEqual(this.text, element.text) && Intrinsics.areEqual(this.subText, element.subText) && Intrinsics.areEqual(this.groupId, element.groupId) && Intrinsics.areEqual(this.style, element.style);
        }

        public final String getGroupId() {
            return this.groupId;
        }

        public final InfoShowStyle getStyle() {
            return this.style;
        }

        public final String getSubText() {
            return this.subText;
        }

        public final String getText() {
            return this.text;
        }

        public final int hashCode() {
            int iHashCode = this.text.hashCode() * 31;
            String str = this.subText;
            return this.style.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.groupId);
        }

        public String toString() {
            String str = this.text;
            String str2 = this.subText;
            String str3 = this.groupId;
            InfoShowStyle infoShowStyle = this.style;
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("Element(text=", str, ", subText=", str2, ", groupId=");
            sbM.append(str3);
            sbM.append(", style=");
            sbM.append(infoShowStyle);
            sbM.append(")");
            return sbM.toString();
        }
    }

    public static Unit $r8$lambda$sJJGbnr1BJ5cBF28KfS6uyCsfek(TemporarilyInfoManager temporarilyInfoManager, ViewGroup viewGroup, Element element, TemporarilyInfoView temporarilyInfoView) {
        TemporarilyInfoManager$showElement$1$1 temporarilyInfoManager$showElement$1$1 = new TemporarilyInfoManager$showElement$1$1();
        temporarilyInfoManager$showElement$1$1.this$0 = temporarilyInfoManager;
        temporarilyInfoManager$showElement$1$1.$temporarilyView = temporarilyInfoView;
        VarHandle.storeStoreFence();
        temporarilyInfoManager.hideAction = temporarilyInfoManager$showElement$1$1;
        viewGroup.postDelayed(temporarilyInfoManager$showElement$1$1, element.getStyle().getShowDuration());
        temporarilyInfoManager.trigger("showElementAfterShowFully", temporarilyInfoManager.showingElement);
        return Unit.INSTANCE;
    }

    public TemporarilyInfoManager() {
        TemporarilyInfoManager$attachStateChangeListener$1 temporarilyInfoManager$attachStateChangeListener$1 = new TemporarilyInfoManager$attachStateChangeListener$1();
        temporarilyInfoManager$attachStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.attachStateChangeListener = temporarilyInfoManager$attachStateChangeListener$1;
        this.quickEntranceTranslationYReset = new TemporarilyInfoManager$$ExternalSyntheticLambda0(1);
        this.elements$delegate = LazyKt__LazyJVMKt.lazy(new TemporarilyInfoManager$$ExternalSyntheticLambda0(2));
        this.duplicateStrategy = DefaultDuplicateStrategy.INSTANCE;
        TemporarilyInfoManager$$ExternalSyntheticLambda3 temporarilyInfoManager$$ExternalSyntheticLambda3 = new TemporarilyInfoManager$$ExternalSyntheticLambda3();
        temporarilyInfoManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.getLandSplitMode = temporarilyInfoManager$$ExternalSyntheticLambda3;
        this.viewPool = new ArrayList();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(200L);
        valueAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        this.fadeOutAnimator = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setDuration(300L);
        valueAnimatorOfFloat2.setInterpolator(new COUIEaseInterpolator());
        valueAnimatorOfFloat2.setStartDelay(100L);
        this.fadeInAnimator = valueAnimatorOfFloat2;
    }

    public static final void access$animateHide(TemporarilyInfoManager temporarilyInfoManager, TemporarilyInfoView temporarilyInfoView, TemporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0 temporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0) {
        temporarilyInfoManager.fadeInAnimator.removeAllUpdateListeners();
        temporarilyInfoManager.fadeOutAnimator.removeAllUpdateListeners();
        temporarilyInfoManager.fadeInAnimator.removeAllListeners();
        temporarilyInfoManager.fadeOutAnimator.removeAllListeners();
        Function0 function0 = temporarilyInfoManager.quickEntranceTranslationYReset;
        if (function0 != null) {
            function0.invoke();
        }
        ValueAnimator valueAnimator = temporarilyInfoManager.fadeOutAnimator;
        int i = 0;
        TemporarilyInfoManager$animateHide$1 temporarilyInfoManager$animateHide$1 = new TemporarilyInfoManager$animateHide$1(i);
        temporarilyInfoManager$animateHide$1.$targetHide = temporarilyInfoView;
        VarHandle.storeStoreFence();
        valueAnimator.addUpdateListener(temporarilyInfoManager$animateHide$1);
        ValueAnimator valueAnimator2 = temporarilyInfoManager.fadeInAnimator;
        TemporarilyInfoManager$animateHide$1 temporarilyInfoManager$animateHide$2 = new TemporarilyInfoManager$animateHide$1(2);
        temporarilyInfoManager$animateHide$2.$targetHide = temporarilyInfoManager;
        VarHandle.storeStoreFence();
        valueAnimator2.addUpdateListener(temporarilyInfoManager$animateHide$2);
        ValueAnimator valueAnimator3 = temporarilyInfoManager.fadeInAnimator;
        TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1 temporarilyInfoManager$animateHide$$inlined$doOnEnd$1 = new TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(i);
        temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.$targetHide$inlined = temporarilyInfoView;
        temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.this$0 = temporarilyInfoManager;
        temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.$onEnd$inlined = temporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0;
        VarHandle.storeStoreFence();
        valueAnimator3.addListener(temporarilyInfoManager$animateHide$$inlined$doOnEnd$1);
        resetTemporarilyInfoView(temporarilyInfoManager.fullShownTextView);
        temporarilyInfoManager.fullShownTextView = null;
        temporarilyInfoManager.fadeOutAnimator.start();
        temporarilyInfoManager.fadeInAnimator.start();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[animateHide] " + temporarilyInfoView + " start", null, 8);
    }

    public static final TemporarilyInfoView access$getNextTemporarilyInfoView(TemporarilyInfoManager temporarilyInfoManager, TemporarilyInfoView temporarilyInfoView) {
        if (temporarilyInfoManager.viewPool.isEmpty()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "[getNextTemporarilyInfoView] viewPool is empty");
            return null;
        }
        TemporarilyInfoView temporarilyInfoView2 = temporarilyInfoManager.viewPool.get((temporarilyInfoManager.currentViewIndex + 1) % 2);
        temporarilyInfoView2.setVisibility(0);
        if (temporarilyInfoView != null) {
            temporarilyInfoView2.setAlpha(temporarilyInfoView.getAlpha());
            temporarilyInfoView2.setTemporarilyInfoViewStyle(temporarilyInfoView.getStyle());
            temporarilyInfoView2.setTemporarilyInfoViewContainer(temporarilyInfoManager.qsRootView, temporarilyInfoManager.statusBarContainerView, temporarilyInfoManager.qsQuickEntranceContainerView);
            temporarilyInfoView2.setText(temporarilyInfoView.getMainViewText(), temporarilyInfoView.getSubViewText());
        }
        return temporarilyInfoView2;
    }

    private final ArrayDeque<Element> getElements() {
        return (ArrayDeque) this.elements$delegate.getValue();
    }

    public static void resetTemporarilyInfoView(TemporarilyInfoView temporarilyInfoView) {
        View alongWithView;
        if (temporarilyInfoView != null && (alongWithView = temporarilyInfoView.getAlongWithView()) != null) {
            ViewExtKt.stopAlongWithPosition(temporarilyInfoView, alongWithView);
        }
        if (temporarilyInfoView != null) {
            temporarilyInfoView.setClickable(false);
        }
        if (temporarilyInfoView != null) {
            temporarilyInfoView.setVisibility(8);
        }
        if (temporarilyInfoView != null) {
            temporarilyInfoView.setText("", "");
        }
    }

    public static /* synthetic */ void show$default(TemporarilyInfoManager temporarilyInfoManager, String str, String str2, String str3, int i) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = GROUP_NON;
        }
        temporarilyInfoManager.show(str, str2, str3, defaultShowStyle);
    }

    public final void clearViewPool() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TemporarilyInfoManager-->[stopAndClearAll] cancel and clear");
        }
        getElements().clear();
        this.fadeOutAnimator.cancel();
        this.fadeInAnimator.cancel();
        ViewGroup viewGroup = this.qsRootView;
        if (viewGroup != null) {
            viewGroup.removeCallbacks(this.hideAction);
        }
        this.hideAction = null;
        this.isShowing = false;
        this.currentViewIndex = 0;
        resetTemporarilyInfoView(this.fullShownTextView);
        resetTemporarilyInfoView(this.temporarilyInfoView);
        if (this.fullShownTextView != null) {
            ViewGroup viewGroup2 = this.statusBarContainerView;
            if (viewGroup2 != null) {
                viewGroup2.setTransitionAlpha(1.0f);
            }
            ViewGroup viewGroup3 = this.qsQuickEntranceContainerView;
            if (viewGroup3 != null) {
                viewGroup3.setTransitionAlpha(1.0f);
            }
            ViewGroup viewGroup4 = this.privacyContainerView;
            if (viewGroup4 != null) {
                viewGroup4.setTransitionAlpha(1.0f);
            }
            this.fullShownTextView = null;
        }
        this.showingElement = null;
        this.temporarilyInfoView = null;
        this.fadeOutAnimator.removeAllUpdateListeners();
        this.fadeOutAnimator.removeAllListeners();
        this.fadeInAnimator.removeAllUpdateListeners();
        this.fadeInAnimator.removeAllListeners();
        for (TemporarilyInfoView temporarilyInfoView : this.viewPool) {
            ViewParent parent = temporarilyInfoView.getParent();
            ViewGroup viewGroup5 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup5 != null) {
                viewGroup5.removeView(temporarilyInfoView);
            }
        }
        this.viewPool.clear();
    }

    public final Function0 getGetLandSplitMode() {
        return this.getLandSplitMode;
    }

    public final ViewGroup getPrivacyContainerView() {
        return this.privacyContainerView;
    }

    public final ViewGroup getQsQuickEntranceContainerView() {
        return this.qsQuickEntranceContainerView;
    }

    public final ViewGroup getQsRootView() {
        return this.qsRootView;
    }

    public final Function0 getQuickEntranceTranslationYReset() {
        return this.quickEntranceTranslationYReset;
    }

    public final int getSeparateExtraSpace() {
        return this.separateExtraSpace;
    }

    public final boolean getStarted() {
        return this.started;
    }

    public final ViewGroup getStatusBarContainerView() {
        return this.statusBarContainerView;
    }

    public final void setGetLandSplitMode(Function0 function0) {
        this.getLandSplitMode = function0;
    }

    public final void setLandSplitLayout(boolean z) {
        this.isLandSplitLayout = z;
        if (this.temporarilyInfoView != null) {
            Iterator<T> it = this.viewPool.iterator();
            while (it.hasNext()) {
                ((TemporarilyInfoView) it.next()).setLandSplitLayout(z);
            }
        }
    }

    public final void setLandSplitMode(boolean z) {
        this.isLandSplitMode = z;
    }

    public final void setPrivacyContainerView(ViewGroup viewGroup) {
        this.privacyContainerView = viewGroup;
    }

    public final void setQsQuickEntranceContainerView(ViewGroup viewGroup) {
        this.qsQuickEntranceContainerView = viewGroup;
    }

    public final void setQsRootView(ViewGroup viewGroup) {
        if (Intrinsics.areEqual(this.qsRootView, viewGroup)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setQsRootView] " + viewGroup + ", old=" + this.qsRootView, null, 8);
        ViewGroup viewGroup2 = this.qsRootView;
        if (viewGroup2 != null) {
            viewGroup2.removeOnAttachStateChangeListener(this.attachStateChangeListener);
        }
        this.qsRootView = viewGroup;
        if (viewGroup != null) {
            viewGroup.addOnAttachStateChangeListener(this.attachStateChangeListener);
        }
    }

    public final void setQuickEntranceTranslationYReset(Function0 function0) {
        this.quickEntranceTranslationYReset = function0;
    }

    public final void setSeparateExtraSpace(int i) {
        this.separateExtraSpace = i;
        if (this.temporarilyInfoView != null) {
            Iterator<T> it = this.viewPool.iterator();
            while (it.hasNext()) {
                ((TemporarilyInfoView) it.next()).setSeparateExtraSpace(i);
            }
        }
    }

    public final void setStarted(boolean z) {
        if (this.started != z) {
            this.started = z;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setStarted] ", z), null, 8);
            if (this.started) {
                return;
            }
            clearViewPool();
        }
    }

    public final void setStatusBarContainerView(ViewGroup viewGroup) {
        this.statusBarContainerView = viewGroup;
    }

    public final void show(String str, String str2, String str3, InfoShowStyle infoShowStyle) {
        ViewGroup viewGroup = this.qsRootView;
        if (viewGroup == null || this.statusBarContainerView == null || this.qsQuickEntranceContainerView == null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "[show]haven't inited, return");
            return;
        }
        if (Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper())) {
            if (viewGroup.getVisibility() == 0 && this.started) {
                getElements().offer(new Element(str, str2, str3, infoShowStyle));
                if (getElements().size() == 1) {
                    trigger("show", this.showingElement);
                    return;
                }
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[show]qsRootView.isVisible=", ", started=", "TemporarilyInfoManager-->", viewGroup.getVisibility() == 0, this.started));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TemporarilyInfoManager-->[show] not main thread, post");
        }
        show.2 r1 = new show.2();
        r1.this$0 = this;
        r1.$text = str;
        r1.$subText = str2;
        r1.$groupId = str3;
        r1.$style = infoShowStyle;
        VarHandle.storeStoreFence();
        viewGroup.post(r1);
    }

    public final void trigger(String str, Element element) {
        if (this.isShowing && this.fullShownTextView == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                boolean z = this.isShowing;
                TemporarilyInfoView temporarilyInfoView = this.fullShownTextView;
                StringBuilder sb = new StringBuilder("[trigger] isShowing=");
                sb.append(z);
                sb.append(", fullShownTextView=");
                sb.append(temporarilyInfoView);
                sb.append(", from=");
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "TemporarilyInfoManager-->", "SystemUi--QuickSettings", sb);
                return;
            }
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[trigger] start from ".concat(str), null, 8);
        ((DefaultDuplicateStrategy) this.duplicateStrategy).handleDuplicate(element, getElements());
        Element elementPoll = getElements().poll();
        if (elementPoll == null) {
            return;
        }
        ViewGroup viewGroup = this.qsRootView;
        ViewGroup viewGroup2 = this.statusBarContainerView;
        ViewGroup viewGroup3 = this.qsQuickEntranceContainerView;
        if (viewGroup == null || viewGroup2 == null || viewGroup3 == null) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "[showElement]haven't inited, return;" + viewGroup + ", " + viewGroup2 + ", " + viewGroup3);
        } else {
            viewGroup.removeCallbacks(this.hideAction);
            String text = elementPoll.getText();
            String subText = elementPoll.getSubText();
            InfoShowStyle style = elementPoll.getStyle();
            int i = 2;
            if (this.viewPool.isEmpty()) {
                ViewGroup viewGroup4 = this.qsRootView;
                if (viewGroup4 == null) {
                    LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "[buildTemporarilyInfoViewPool] qsRootView is null");
                } else {
                    ViewGroup viewGroup5 = this.statusBarContainerView;
                    if (viewGroup5 == null) {
                        LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "[buildTemporarilyInfoViewPool] statusBarContainerView is null");
                    } else {
                        ViewGroup viewGroup6 = this.qsQuickEntranceContainerView;
                        if (viewGroup6 == null) {
                            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "[buildTemporarilyInfoViewPool] qsQuickEntranceContainerView is null");
                        } else {
                            int i2 = 0;
                            while (i2 < i) {
                                ViewGroup viewGroup7 = viewGroup6;
                                int i3 = i;
                                ViewGroup viewGroup8 = viewGroup5;
                                int i4 = i2;
                                TemporarilyInfoView temporarilyInfoView2 = new TemporarilyInfoView(viewGroup4.getContext().getApplicationContext(), viewGroup4, viewGroup8, viewGroup7, defaultShowStyle, this.getLandSplitMode);
                                temporarilyInfoView2.setVisibility(8);
                                temporarilyInfoView2.setLandSplitLayout(this.isLandSplitLayout);
                                temporarilyInfoView2.setSeparateExtraSpace(this.separateExtraSpace);
                                SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = this.specialLanguageTextScaleHelper;
                                if (specialLanguageTextScaleHelper != null) {
                                    temporarilyInfoView2.updateTemporarilyViewTextSize(this.scaleResult, specialLanguageTextScaleHelper, this.initialDensity);
                                }
                                this.viewPool.add(temporarilyInfoView2);
                                i2 = i4 + 1;
                                i = i3;
                                viewGroup5 = viewGroup8;
                                viewGroup6 = viewGroup7;
                            }
                        }
                    }
                }
            }
            TemporarilyInfoView temporarilyInfoView3 = (TemporarilyInfoView) CollectionsKt___CollectionsKt.getOrNull(this.viewPool, this.currentViewIndex);
            if (temporarilyInfoView3 != null) {
                this.temporarilyInfoView = temporarilyInfoView3;
                temporarilyInfoView3.setAlpha(0.0f);
                if (!Intrinsics.areEqual(temporarilyInfoView3.getParent(), this.qsRootView)) {
                    for (TemporarilyInfoView temporarilyInfoView4 : this.viewPool) {
                        ViewParent parent = temporarilyInfoView4.getParent();
                        ViewGroup viewGroup9 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                        if (viewGroup9 != null) {
                            viewGroup9.removeView(temporarilyInfoView4);
                        }
                        ViewGroup viewGroup10 = this.qsRootView;
                        if (viewGroup10 != null) {
                            viewGroup10.addView(temporarilyInfoView4);
                        }
                    }
                }
                temporarilyInfoView3.setTemporarilyInfoViewStyle(style);
                temporarilyInfoView3.setTemporarilyInfoViewContainer(this.qsRootView, this.statusBarContainerView, this.qsQuickEntranceContainerView);
                temporarilyInfoView3.setText(text, subText);
                temporarilyInfoView3.setVisibility(0);
            } else {
                temporarilyInfoView3 = null;
            }
            int i5 = 1;
            if (temporarilyInfoView3 != null) {
                this.currentViewIndex = (this.currentViewIndex + 1) % 2;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[showElement] " + temporarilyInfoView3 + " " + elementPoll, null, 8);
            TemporarilyInfoManager$$ExternalSyntheticLambda4 temporarilyInfoManager$$ExternalSyntheticLambda4 = new TemporarilyInfoManager$$ExternalSyntheticLambda4();
            temporarilyInfoManager$$ExternalSyntheticLambda4.f$0 = this;
            temporarilyInfoManager$$ExternalSyntheticLambda4.f$1 = viewGroup;
            temporarilyInfoManager$$ExternalSyntheticLambda4.f$2 = elementPoll;
            temporarilyInfoManager$$ExternalSyntheticLambda4.f$3 = temporarilyInfoView3;
            VarHandle.storeStoreFence();
            this.fadeInAnimator.removeAllUpdateListeners();
            this.fadeOutAnimator.removeAllUpdateListeners();
            this.fadeInAnimator.removeAllListeners();
            this.fadeOutAnimator.removeAllListeners();
            TemporarilyInfoView temporarilyInfoView5 = this.fullShownTextView;
            this.fullShownTextView = null;
            if (temporarilyInfoView3 != null) {
                temporarilyInfoView3.setClickable(true);
            }
            ValueAnimator valueAnimator = this.fadeOutAnimator;
            TemporarilyInfoManager$animateShow$1 temporarilyInfoManager$animateShow$1 = new TemporarilyInfoManager$animateShow$1();
            temporarilyInfoManager$animateShow$1.$localShownTextView = temporarilyInfoView5;
            temporarilyInfoManager$animateShow$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimator.addUpdateListener(temporarilyInfoManager$animateShow$1);
            ValueAnimator valueAnimator2 = this.fadeOutAnimator;
            TemporarilyInfoManager$animateShow$$inlined$doOnCancel$1 temporarilyInfoManager$animateShow$$inlined$doOnCancel$1 = new TemporarilyInfoManager$animateShow$$inlined$doOnCancel$1();
            temporarilyInfoManager$animateShow$$inlined$doOnCancel$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimator2.addListener(temporarilyInfoManager$animateShow$$inlined$doOnCancel$1);
            ValueAnimator valueAnimator3 = this.fadeOutAnimator;
            TemporarilyInfoManager$animateShow$$inlined$doOnEnd$1 temporarilyInfoManager$animateShow$$inlined$doOnEnd$1 = new TemporarilyInfoManager$animateShow$$inlined$doOnEnd$1();
            temporarilyInfoManager$animateShow$$inlined$doOnEnd$1.$localShownTextView$inlined = temporarilyInfoView5;
            temporarilyInfoManager$animateShow$$inlined$doOnEnd$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimator3.addListener(temporarilyInfoManager$animateShow$$inlined$doOnEnd$1);
            ValueAnimator valueAnimator4 = this.fadeInAnimator;
            TemporarilyInfoManager$animateHide$1 temporarilyInfoManager$animateHide$1 = new TemporarilyInfoManager$animateHide$1(i5);
            temporarilyInfoManager$animateHide$1.$targetHide = temporarilyInfoView3;
            VarHandle.storeStoreFence();
            valueAnimator4.addUpdateListener(temporarilyInfoManager$animateHide$1);
            ValueAnimator valueAnimator5 = this.fadeInAnimator;
            TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1 temporarilyInfoManager$animateHide$$inlined$doOnEnd$1 = new TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(i5);
            temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.$targetHide$inlined = temporarilyInfoView3;
            temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.this$0 = this;
            temporarilyInfoManager$animateHide$$inlined$doOnEnd$1.$onEnd$inlined = temporarilyInfoManager$$ExternalSyntheticLambda4;
            VarHandle.storeStoreFence();
            valueAnimator5.addListener(temporarilyInfoManager$animateHide$$inlined$doOnEnd$1);
            this.fadeOutAnimator.start();
            this.fadeInAnimator.start();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[animateShow] " + temporarilyInfoView3 + " start", null, 8);
            this.isShowing = true;
        }
        this.showingElement = elementPoll;
    }

    public final void updateTemporarilyViewTextSize(float f, SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper, float f2) {
        if (this.temporarilyInfoView == null) {
            this.scaleResult = f;
            this.specialLanguageTextScaleHelper = specialLanguageTextScaleHelper;
            this.initialDensity = f2;
        } else {
            Iterator<T> it = this.viewPool.iterator();
            while (it.hasNext()) {
                ((TemporarilyInfoView) it.next()).updateTemporarilyViewTextSize(f, specialLanguageTextScaleHelper, f2);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InfoShowStyle {
        public final boolean alongWithReplacedView;
        public final long showDuration;
        public final int sizeUnit;
        public final int textColor;
        public final float textSize;

        public /* synthetic */ InfoShowStyle(int i, int i2) {
            this(14.0f, 2, (i2 & 4) != 0 ? -1 : i, 1500L, (i2 & 16) == 0);
        }

        public final InfoShowStyle copy(float f, int i, int i2, long j, boolean z) {
            return new InfoShowStyle(f, i, i2, j, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InfoShowStyle)) {
                return false;
            }
            InfoShowStyle infoShowStyle = (InfoShowStyle) obj;
            return Float.compare(this.textSize, infoShowStyle.textSize) == 0 && this.sizeUnit == infoShowStyle.sizeUnit && this.textColor == infoShowStyle.textColor && this.showDuration == infoShowStyle.showDuration && this.alongWithReplacedView == infoShowStyle.alongWithReplacedView;
        }

        public final boolean getAlongWithReplacedView() {
            return this.alongWithReplacedView;
        }

        public final long getShowDuration() {
            return this.showDuration;
        }

        public final int getSizeUnit() {
            return this.sizeUnit;
        }

        public final int getTextColor() {
            return this.textColor;
        }

        public final float getTextSize() {
            return this.textSize;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.alongWithReplacedView) + ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.textColor, MonoSpline$.ExternalSyntheticOutline0.m(this.sizeUnit, Float.hashCode(this.textSize) * 31, 31), 31), 31, this.showDuration);
        }

        public String toString() {
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(this.textSize, this.sizeUnit, "InfoShowStyle(textSize=", ", sizeUnit=", ", textColor=");
            sbM.append(this.textColor);
            sbM.append(", showDuration=");
            sbM.append(this.showDuration);
            sbM.append(", alongWithReplacedView=");
            sbM.append(this.alongWithReplacedView);
            sbM.append(")");
            return sbM.toString();
        }

        public InfoShowStyle(float f, int i, int i2, long j, boolean z) {
            this.textSize = f;
            this.sizeUnit = i;
            this.textColor = i2;
            this.showDuration = j;
            this.alongWithReplacedView = z;
        }
    }

    public final void show(String str, String str2) {
        show$default(this, str, str2, null, 12);
    }

    public final void show(String str, String str2, String str3) {
        show$default(this, str, str2, str3, 8);
    }

    public final void show(String str) {
        show$default(this, str, null, null, 14);
    }
}
