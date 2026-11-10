package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.IHostAbility;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditablePluginViewHolder extends BaseEditableViewHolder implements IHostAbility {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EditablePluginViewHolder";
    private final Lazy animationController$delegate;
    private final View bgView;
    public boolean enableTileName;
    public boolean isEditMode;
    public boolean isImmersiveOperation;
    public boolean isListening;
    private QsPanelPlugin panelPlugin;
    private final Lazy realPluginContainer$delegate;
    private SpanSize realSpanSize;
    public final int strokeWidth;
    public boolean updateEditModeWithAnimation;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditablePluginViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ EditablePluginViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            return this.this$0.getRealPluginContainer();
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public CellInfo getCellInfo() {
            return this.this$0.getCellInfo();
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final CellCalculatorManager.LayoutConstraint getLayoutConstraint(Context context, SpanSize spanSize, boolean z, boolean z2, boolean z3) {
            CellCalculatorManager companion = CellCalculatorManager.Companion.getInstance(context);
            EditablePluginViewHolder editablePluginViewHolder = this.this$0;
            CellCalculatorManager.LayoutConstraint layoutConstraint = companion.getLayoutConstraint(spanSize, editablePluginViewHolder.getEnableTileName(), editablePluginViewHolder.isEditMode, z3);
            return (editablePluginViewHolder.isEditMode || !editablePluginViewHolder.getEnableTileName()) ? layoutConstraint : layoutConstraint.copy(layoutConstraint.capsuleWidth, editablePluginViewHolder.getDeformationContainer().getMeasuredHeight() - layoutConstraint.getLayoutMarginTop(), layoutConstraint.layoutMarginTop, layoutConstraint.labelMarginTop, layoutConstraint.labelSize, layoutConstraint.labelLines, layoutConstraint.labelLineHeight, layoutConstraint.innerContentRatio, layoutConstraint.innerMarginRatio, layoutConstraint.twoXOneTextStyleInfo);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EditablePluginViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel);
        this.bgView = new View(context);
        this.strokeWidth = context.getResources().getDimensionPixelSize(2131171768);
        this.realSpanSize = SpanCache.TWO_X_ONE;
        this.isImmersiveOperation = super.isImmersiveOperation;
        EditablePluginViewHolder$$ExternalSyntheticLambda0 editablePluginViewHolder$$ExternalSyntheticLambda0 = new EditablePluginViewHolder$$ExternalSyntheticLambda0();
        editablePluginViewHolder$$ExternalSyntheticLambda0.f$0 = context;
        editablePluginViewHolder$$ExternalSyntheticLambda0.f$1 = this;
        VarHandle.storeStoreFence();
        this.realPluginContainer$delegate = LazyKt__LazyJVMKt.lazy(editablePluginViewHolder$$ExternalSyntheticLambda0);
        this.isListening = super.isListening;
        this.enableTileName = super.getEnableTileName();
        this.isEditMode = super.isEditMode;
        EditablePluginViewHolder$$ExternalSyntheticLambda1 editablePluginViewHolder$$ExternalSyntheticLambda1 = new EditablePluginViewHolder$$ExternalSyntheticLambda1();
        editablePluginViewHolder$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.animationController$delegate = LazyKt__LazyJVMKt.lazy(editablePluginViewHolder$$ExternalSyntheticLambda1);
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = SpanCache.INSTANCE.spanSize(1, 1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, anonymousClass1, getAnimationController(), false, 8);
    }

    private final EditablePluginViewHolder$animationController$2$1 getAnimationController() {
        return (EditablePluginViewHolder$animationController$2$1) this.animationController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditablePluginViewHolder$realPluginContainer$2$1 getRealPluginContainer() {
        return (EditablePluginViewHolder$realPluginContainer$2$1) this.realPluginContainer$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        super.bindData(gridData);
        DeformationFrameLayout.Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(getRealPluginContainer(), getCurrRealSize());
        getDeformationContainer().setCurSize(gridData.getSize());
        this.realSpanSize = getCurrRealSize();
        QsPanelPlugin qsPanelPlugin = (QsPanelPlugin) gridData.getData();
        if (!Intrinsics.areEqual(this.panelPlugin, qsPanelPlugin)) {
            for (View view : ViewGroupKt.getChildren(getRealPluginContainer())) {
                if (!Intrinsics.areEqual(view, this.bgView)) {
                    getRealPluginContainer().removeView(view);
                }
            }
            QsPanelPlugin qsPanelPlugin2 = this.panelPlugin;
            if (qsPanelPlugin2 != null) {
                qsPanelPlugin2.injectHostAbility((IHostAbility) null);
            }
            this.panelPlugin = qsPanelPlugin;
            if (qsPanelPlugin != null) {
                qsPanelPlugin.injectHostAbility(this);
                EditablePluginViewHolder$realPluginContainer$2$1 realPluginContainer = getRealPluginContainer();
                SpanSizeInPlugin spanSizeInPlugin = new SpanSizeInPlugin(getCurrRealSize().getColSpan(), getCurrRealSize().getRowSpan());
                Bundle bundle = new Bundle();
                bundle.putInt("device_mode_key", 1);
                qsPanelPlugin.onCreateView(realPluginContainer, spanSizeInPlugin, bundle);
                QsPanelPlugin qsPanelPlugin3 = this.panelPlugin;
                if (qsPanelPlugin3 != null) {
                    boolean z = this.isEditMode;
                    boolean z2 = this.updateEditModeWithAnimation;
                    if (z) {
                        qsPanelPlugin3.onEnterEditMode(z2);
                    } else {
                        qsPanelPlugin3.onExitEditMode(z2);
                    }
                }
            }
            QsLabelView labelView = getDeformationContainer().getLabelView();
            QsPanelPlugin qsPanelPlugin4 = this.panelPlugin;
            labelView.updateText(qsPanelPlugin4 != null ? qsPanelPlugin4.getLabel() : null);
            getDeformationContainer().setVisibility(this.panelPlugin == null ? 4 : 0);
        }
        QsPanelPlugin qsPanelPlugin5 = this.panelPlugin;
        if (qsPanelPlugin5 != null) {
            qsPanelPlugin5.setNewSize(new SpanSizeInPlugin(getCurrRealSize().getColSpan(), getCurrRealSize().getRowSpan()));
        }
    }

    public final View getAnimatorView(int i) {
        QsPanelPlugin qsPanelPlugin = this.panelPlugin;
        if (qsPanelPlugin != null) {
            return qsPanelPlugin.getAnimatorView(i);
        }
        return null;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public boolean getEnableTileName() {
        return this.enableTileName;
    }

    public IHostAbility.LayoutConstraint getLayoutInfo(SpanSizeInPlugin spanSizeInPlugin) {
        CellInfo cellInfo = getCellInfo();
        if (cellInfo == null) {
            Path$.ExternalSyntheticBUOutline0.m$1("getLayoutInfo, cellInfo is null");
            return null;
        }
        if (!isCellInfoValid()) {
            Path$.ExternalSyntheticBUOutline0.m$1("getLayoutInfo, cellInfo is invalid");
            return null;
        }
        SpanSize spanSize = SpanCache.INSTANCE.spanSize(spanSizeInPlugin.getColSpan() / 2, spanSizeInPlugin.getRowSpan());
        CellCalculatorManager.LayoutConstraint layoutConstraint$default = CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), spanSize, getEnableTileName(), this.isEditMode, 8);
        int colSpan = ((spanSize.getColSpan() - 1) * cellInfo.getHorizontalSpace()) + (spanSize.getColSpan() * cellInfo.getCellWidth());
        int rowSpan = ((spanSize.getRowSpan() - 1) * cellInfo.getVerticalSpace()) + (spanSize.getRowSpan() * cellInfo.getCellHeight());
        int layoutMarginTop = (!getEnableTileName() || this.isEditMode) ? 0 : ((rowSpan - layoutConstraint$default.getLayoutMarginTop()) - layoutConstraint$default.getCapsuleHeight()) - layoutConstraint$default.getLabelMarginTop();
        int labelMarginTop = (!getEnableTileName() || this.isEditMode) ? 0 : layoutConstraint$default.getLabelMarginTop();
        return new IHostAbility.LayoutConstraint(layoutConstraint$default.getCapsuleWidth(), layoutConstraint$default.getCapsuleHeight(), labelMarginTop, layoutConstraint$default.getLabelSize(), layoutConstraint$default.getLabelLines(), layoutConstraint$default.getLabelLineHeight(), layoutMarginTop, colSpan, layoutConstraint$default.getInnerContentRatio(), layoutConstraint$default.getInnerMarginRatio(), getEnableTileName() && !this.isEditMode, colSpan - layoutConstraint$default.getCapsuleWidth(), ((rowSpan - layoutConstraint$default.getCapsuleHeight()) - labelMarginTop) - layoutMarginTop, new IHostAbility.TwoXOneTextStyleInfo(layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelTextSize(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelDescTextSize(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelLineHeight(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelDescLineHeight()));
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isImmersiveOperation() {
        return this.isImmersiveOperation;
    }

    @Override // com.oplusos.systemui.common.viewholder.GridViewHolder
    public void onCellInfoChanged(CellInfo cellInfo) {
        QsPanelPlugin qsPanelPlugin;
        if (!isCellInfoValid() || (qsPanelPlugin = this.panelPlugin) == null) {
            return;
        }
        qsPanelPlugin.onLayoutChanged();
    }

    public void onPanelSizeChanged(SpanSizeInPlugin spanSizeInPlugin, SpanSizeInPlugin spanSizeInPlugin2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditablePluginViewHolder-->".concat("[onPanelSizeChanged] oldSize=" + spanSizeInPlugin + ", newSize=" + spanSizeInPlugin2));
        }
        SpanCache spanCache = SpanCache.INSTANCE;
        SpanSize spanSize = spanCache.spanSize(spanSizeInPlugin2.getColSpan(), spanSizeInPlugin2.getRowSpan());
        if (getAnimationController().isResizing) {
            DeformationFrameLayout.Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(getRealPluginContainer(), spanSize);
            getDeformationContainer().setCurSize(DataConvertorKt.mergeTwoCols(spanSize));
        } else if (SpanSizeKt.isLessThan(spanSize, spanCache.spanSize(spanSizeInPlugin.getColSpan(), spanSizeInPlugin.getRowSpan()))) {
            DeformationFrameLayout.Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(getRealPluginContainer(), spanSize);
            getDeformationContainer().setCurSize(DataConvertorKt.mergeTwoCols(spanSize));
            QsPanelPlugin qsPanelPlugin = this.panelPlugin;
            if (qsPanelPlugin != null) {
                sendIntent(new TileEditIntent.ClipTileSizeIntent(qsPanelPlugin.getSpec(), getDeformationContainer().getCurSize()));
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onRecycled() {
        super.onRecycled();
        this.panelPlugin = null;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        super.onViewCreated();
        getDeformationContainer().setDefaultPressFeedbackHandler(null);
        QsLabelView labelView = getDeformationContainer().getLabelView();
        int qsColorState = QsColorUtil.getQsColorState();
        QsLabelView.Companion companion = QsLabelView.Companion;
        labelView.updateColor(qsColorState, false, false);
        getDeformationContainer().setRemoveIconContentDescription(getContext().getString(2131955192, getContext().getResources().getString(2131955239), ""));
    }

    public final void setCusTranslationY(int i, float f) {
        QsPanelPlugin qsPanelPlugin = this.panelPlugin;
        if (qsPanelPlugin != null) {
            qsPanelPlugin.setCusTranslationY(i, 0.0f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEditMode(boolean z) {
        QsPanelPlugin qsPanelPlugin;
        ArrayList arrayList;
        List allValidSize;
        List<SpanSize> allValidSize2;
        if (this.isEditMode != z) {
            this.isEditMode = z;
            super.setEditMode(z);
            boolean z2 = false;
            getDeformationContainer().setEnableTileName(this.isEditMode && getEnableTileName());
            QsPanelPlugin qsPanelPlugin2 = this.panelPlugin;
            if (z) {
                if (qsPanelPlugin2 != null) {
                    qsPanelPlugin2.onEnterEditMode(this.updateEditModeWithAnimation);
                }
                QsPanelPlugin qsPanelPlugin3 = this.panelPlugin;
                if (qsPanelPlugin3 == null || (allValidSize = qsPanelPlugin3.getAllValidSize()) == null) {
                    arrayList = null;
                } else {
                    List<SpanSizeInPlugin> list = allValidSize;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                    for (SpanSizeInPlugin spanSizeInPlugin : list) {
                        arrayList2.add(SpanCache.INSTANCE.spanSize(spanSizeInPlugin.getColSpan(), spanSizeInPlugin.getRowSpan()));
                    }
                    arrayList = new ArrayList();
                    int size = arrayList2.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList2.get(i);
                        i++;
                        SpanSize spanSize = (SpanSize) obj;
                        GridData gridData = getGridData();
                        if (gridData != null && (allValidSize2 = gridData.getAllValidSize()) != null && allValidSize2.contains(spanSize)) {
                            arrayList.add(obj);
                        }
                    }
                }
                if (arrayList != null && (!arrayList.isEmpty())) {
                    DeformationFrameLayout deformationContainer = getDeformationContainer();
                    int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                    if (iMapCapacity < 16) {
                        iMapCapacity = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                    int size2 = arrayList.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        Object obj2 = arrayList.get(i2);
                        i2++;
                        CellCalculatorManager.LayoutConstraint layoutConstraint = CellCalculatorManager.Companion.getInstance(deformationContainer.getContext()).getLayoutConstraint((SpanSize) obj2, deformationContainer.getEnableTileName(), deformationContainer.isEditMode, false);
                        linkedHashMap.put(obj2, new Size(layoutConstraint.getCapsuleWidth(), layoutConstraint.getCapsuleHeight()));
                    }
                    deformationContainer.setValidSize(linkedHashMap);
                    if (deformationContainer.getValidSizeMap().size() > 1 && deformationContainer.getValidSizeMap().containsKey(deformationContainer.getCurSize())) {
                        z2 = true;
                    }
                    deformationContainer.setWithResizeBar(z2);
                }
            } else if (qsPanelPlugin2 != null) {
                qsPanelPlugin2.onExitEditMode(this.updateEditModeWithAnimation);
            }
            if (!isCellInfoValid() || (qsPanelPlugin = this.panelPlugin) == null) {
                return;
            }
            qsPanelPlugin.onLayoutChanged();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEnableTileName(boolean z) {
        QsPanelPlugin qsPanelPlugin;
        if (this.enableTileName != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[enableTileName] ", "EditablePluginViewHolder-->", "SystemUi--QuickSettings", z);
            }
            this.enableTileName = z;
            getDeformationContainer().setEnableTileName(this.isEditMode && getEnableTileName());
            if (!isCellInfoValid() || (qsPanelPlugin = this.panelPlugin) == null) {
                return;
            }
            qsPanelPlugin.onLayoutChanged();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setImmersiveOperation(boolean z) {
        if (this.isImmersiveOperation != z) {
            this.isImmersiveOperation = z;
            super.setImmersiveOperation(z);
            if (this.isEditMode) {
                this.bgView.setVisibility(!z ? 0 : 8);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        if (this.isListening != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[isListening] ", z), null, 8);
            this.isListening = z;
            QsPanelPlugin qsPanelPlugin = this.panelPlugin;
            if (qsPanelPlugin != null) {
                qsPanelPlugin.setListening(z);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        GradientDrawable gradientDrawable;
        QsLabelView labelView = getDeformationContainer().getLabelView();
        int qsColorState = QsColorUtil.getQsColorState();
        QsLabelView.Companion companion = QsLabelView.Companion;
        labelView.updateColor(qsColorState, false, false);
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(getContext(), false);
            View view = this.bgView;
            int i = this.strokeWidth;
            if (zIsNeedDarkThemeColor) {
                Drawable background = view.getBackground();
                gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
                if (gradientDrawable != null) {
                    gradientDrawable.setStroke(i, getContext().getResources().getColor(2131103214));
                    return;
                }
                return;
            }
            Drawable background2 = view.getBackground();
            gradientDrawable = background2 instanceof GradientDrawable ? (GradientDrawable) background2 : null;
            if (gradientDrawable != null) {
                gradientDrawable.setStroke(i, getContext().getResources().getColor(2131103213));
            }
        }
    }

    public final void updateInnerViewState(int i, float f, float f2, float f3) {
        QsPanelPlugin qsPanelPlugin = this.panelPlugin;
        if (qsPanelPlugin != null) {
            qsPanelPlugin.updateInnerViewState(i, 1.0f, 1.0f, 1.0f);
        }
    }
}
