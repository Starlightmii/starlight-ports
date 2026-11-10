package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSBrightness;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.util.AnimatableProperty;
import com.oplus.systemui.plugins.qs.util.AnimationProperties;
import com.oplus.systemui.plugins.qs.widget.OneHandedEnableProvider;
import com.oplus.systemui.plugins.qs.widget.OplusQsToggleSliderLayout;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder extends BaseEditableViewHolder {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(1, 2);
    private static final PathInterpolator PANEL_TRANSITION_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    private static final String TAG = "EditableBrightnessViewHolder";
    private Function0 endAction;
    public boolean isBeingDragged;
    public boolean isEditMode;
    public boolean isListening;
    private final View.OnAttachStateChangeListener onAttachStateListener;
    private final AnimatableProperty panelAlphaAnimator;
    public final AnimationProperties panelAlphaInPropertiesAnimation;
    public final AnimationProperties panelAlphaOutPropertiesAnimator;
    private final Lazy qsBrightness$delegate;
    private final EditableBrightnessViewHolder$showMirrorListener$1 showMirrorListener;
    private final View.OnClickListener sliderClickListener;
    private final View.OnLongClickListener sliderLongClickListener;
    private OplusQsToggleSliderLayout toggleSliderLayout;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ Context $context;
        public /* synthetic */ EditableBrightnessViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.$context);
            EditableBrightnessViewHolder editableBrightnessViewHolder = this.this$0;
            View viewInflate = layoutInflaterFrom.inflate(2131559328, (ViewGroup) editableBrightnessViewHolder.getDeformationContainer(), false);
            editableBrightnessViewHolder.initSliderLayout(viewInflate instanceof OplusQsToggleSliderLayout ? (OplusQsToggleSliderLayout) viewInflate : null);
            return viewInflate;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public CellInfo getCellInfo() {
            return this.this$0.getCellInfo();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessViewHolder$initSliderLayout$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02321 implements OneHandedEnableProvider {
        @Override // com.oplus.systemui.plugins.qs.widget.OneHandedEnableProvider
        public boolean getOneHandedEnable() {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            if (proxy != null) {
                return proxy.getOneHandedEnable();
            }
            return false;
        }
    }

    public static QSBrightness $r8$lambda$keyddgpJehqVRjJ9pr16dD5XFIE(EditableBrightnessViewHolder editableBrightnessViewHolder) {
        QSBrightness qSBrightnessBindQSBrightness;
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = editableBrightnessViewHolder.toggleSliderLayout;
        if (oplusQsToggleSliderLayout == null) {
            Path$.ExternalSyntheticBUOutline0.m$1("cannot get toggleSliderLayout");
            return null;
        }
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null && (qSBrightnessBindQSBrightness = proxy.bindQSBrightness(oplusQsToggleSliderLayout, editableBrightnessViewHolder.sliderClickListener, editableBrightnessViewHolder.sliderLongClickListener)) != null) {
            return qSBrightnessBindQSBrightness;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("cannot get QSVolume from host");
        return null;
    }

    public EditableBrightnessViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel);
        EditableBrightnessViewHolder$sliderClickListener$1 editableBrightnessViewHolder$sliderClickListener$1 = new EditableBrightnessViewHolder$sliderClickListener$1();
        editableBrightnessViewHolder$sliderClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sliderClickListener = editableBrightnessViewHolder$sliderClickListener$1;
        EditableBrightnessViewHolder$sliderLongClickListener$1 editableBrightnessViewHolder$sliderLongClickListener$1 = new EditableBrightnessViewHolder$sliderLongClickListener$1();
        editableBrightnessViewHolder$sliderLongClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sliderLongClickListener = editableBrightnessViewHolder$sliderLongClickListener$1;
        EditableBrightnessViewHolder$$ExternalSyntheticLambda0 editableBrightnessViewHolder$$ExternalSyntheticLambda0 = new EditableBrightnessViewHolder$$ExternalSyntheticLambda0();
        editableBrightnessViewHolder$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsBrightness$delegate = LazyKt__LazyJVMKt.lazy(editableBrightnessViewHolder$$ExternalSyntheticLambda0);
        AnimatableProperty animatablePropertyFrom = AnimatableProperty.Companion.from("qsPanelAlpha", EditableBrightnessViewHolder$panelAlphaAnimator$1.INSTANCE, EditableBrightnessViewHolder$panelAlphaAnimator$2.INSTANCE, 2131364710, 2131364709, 2131364708);
        this.panelAlphaAnimator = animatablePropertyFrom;
        AnimationProperties duration = new AnimationProperties().setDuration(300L);
        Property<View, Float> property = animatablePropertyFrom.getProperty();
        PathInterpolator pathInterpolator = PANEL_TRANSITION_INTERPOLATOR;
        if (duration.mInterpolatorMap == null) {
            duration.mInterpolatorMap = new ArrayMap();
        }
        duration.mInterpolatorMap.put(property, pathInterpolator);
        this.panelAlphaOutPropertiesAnimator = duration;
        AnimationProperties duration2 = new AnimationProperties().setDuration(300L);
        Property<View, Float> property2 = animatablePropertyFrom.getProperty();
        if (duration2.mInterpolatorMap == null) {
            duration2.mInterpolatorMap = new ArrayMap();
        }
        duration2.mInterpolatorMap.put(property2, pathInterpolator);
        EditableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1 editableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1 = new EditableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1();
        editableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelAlphaInPropertiesAnimation = duration2.setAnimationEndAction(editableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1);
        EditableBrightnessViewHolder$showMirrorListener$1 editableBrightnessViewHolder$showMirrorListener$1 = new EditableBrightnessViewHolder$showMirrorListener$1();
        editableBrightnessViewHolder$showMirrorListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.showMirrorListener = editableBrightnessViewHolder$showMirrorListener$1;
        EditableBrightnessViewHolder$onAttachStateListener$1 editableBrightnessViewHolder$onAttachStateListener$1 = new EditableBrightnessViewHolder$onAttachStateListener$1();
        editableBrightnessViewHolder$onAttachStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachStateListener = editableBrightnessViewHolder$onAttachStateListener$1;
        this.isListening = super.isListening;
        this.isEditMode = super.isEditMode;
        this.isBeingDragged = super.isBeingDragged;
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = DEFAULT_SIZE;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$context = context;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, anonymousClass1, null, false, 12);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x000b, code lost:
    
        r2 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.oplus.systemui.plugins.qs.OplusQSRootView access$findQSRootView(com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessViewHolder r2) {
        /*
            com.oplus.systemui.plugins.qs.widget.OplusQsToggleSliderLayout r2 = r2.toggleSliderLayout
            r0 = 0
            if (r2 == 0) goto La
            android.view.ViewParent r2 = r2.getParent()
            goto Lb
        La:
            r2 = r0
        Lb:
            if (r2 == 0) goto L20
            boolean r1 = r2 instanceof com.oplus.systemui.plugins.qs.OplusQSRootView
            if (r1 != 0) goto L20
            boolean r1 = r2 instanceof android.view.View
            if (r1 == 0) goto L18
            android.view.View r2 = (android.view.View) r2
            goto L19
        L18:
            r2 = r0
        L19:
            if (r2 == 0) goto La
            android.view.ViewParent r2 = r2.getParent()
            goto Lb
        L20:
            boolean r1 = r2 instanceof com.oplus.systemui.plugins.qs.OplusQSRootView
            if (r1 == 0) goto L27
            com.oplus.systemui.plugins.qs.OplusQSRootView r2 = (com.oplus.systemui.plugins.qs.OplusQSRootView) r2
            return r2
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessViewHolder.access$findQSRootView(com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessViewHolder):com.oplus.systemui.plugins.qs.OplusQSRootView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QSBrightness getQsBrightness() {
        return (QSBrightness) this.qsBrightness$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        super.bindData(gridData);
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955074));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
    }

    public final void initSliderLayout(OplusQsToggleSliderLayout oplusQsToggleSliderLayout) {
        this.toggleSliderLayout = oplusQsToggleSliderLayout;
        if (oplusQsToggleSliderLayout != null) {
            oplusQsToggleSliderLayout.setOneHandedEnableProvider(new C02321());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        super.onViewCreated();
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955074));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = this.toggleSliderLayout;
        if (oplusQsToggleSliderLayout != null && oplusQsToggleSliderLayout.isAttachedToWindow()) {
            this.onAttachStateListener.onViewAttachedToWindow(((RecyclerView.ViewHolder) this).itemView);
        }
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout2 = this.toggleSliderLayout;
        if (oplusQsToggleSliderLayout2 != null) {
            oplusQsToggleSliderLayout2.addOnAttachStateChangeListener(this.onAttachStateListener);
        }
        getQsBrightness().doInit();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            super.setBeingDragged(z);
            getQsBrightness().updateSliderColor(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            super.setEditMode(z);
            getQsBrightness().setIsEditable(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        if (this.isListening != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[isListening] ", z), null, 8);
            this.isListening = z;
            getQsBrightness().setListening(z);
            if (!z) {
                getQsBrightness().unregisterCallbacks();
                getQsBrightness().setShowMirrorListener((QSBrightness.ShowMirrorListener) null);
            } else {
                getQsBrightness().checkRestrictionAndSetEnabled();
                getQsBrightness().registerCallbacks();
                getQsBrightness().setShowMirrorListener(this.showMirrorListener);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        getQsBrightness().updateColor(colorType);
        QsLabelView labelView = getDeformationContainer().getLabelView();
        int qsColorState = QsColorUtil.getQsColorState();
        QsLabelView.Companion companion = QsLabelView.Companion;
        labelView.updateColor(qsColorState, false, false);
    }
}
