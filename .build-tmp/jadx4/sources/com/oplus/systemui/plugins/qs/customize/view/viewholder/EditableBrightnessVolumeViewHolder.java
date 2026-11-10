package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.common.viewholder.CellInfo;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessVolumeViewHolder extends BaseEditableViewHolder {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(1, 2);
    private static final String TAG = "EditableBrightnessVolumeViewHolder";
    private final EditableBrightnessViewHolder brightnessViewHolder;
    private final Lazy bvLayoutContainer$delegate;
    private CharSequence contentDescription;
    public boolean isBeingDragged;
    public boolean isEditMode;
    public boolean isImmersiveOperation;
    public boolean isListening;
    public View strokeBgView;
    public final int strokeWidth;
    private final EditableVolumeViewHolder volumeViewHolder;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessVolumeViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ EditableBrightnessVolumeViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            return this.this$0.getBvLayoutContainer();
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public CellInfo getCellInfo() {
            return this.this$0.getCellInfo();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EditableBrightnessVolumeViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel);
        this.brightnessViewHolder = new EditableBrightnessViewHolder(context, viewGroup, tileEditViewModel);
        this.volumeViewHolder = new EditableVolumeViewHolder(context, viewGroup, tileEditViewModel);
        this.strokeWidth = context.getResources().getDimensionPixelSize(2131171768);
        EditableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0 editableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0 = new EditableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0();
        editableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0.f$0 = context;
        editableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0.f$1 = this;
        VarHandle.storeStoreFence();
        this.bvLayoutContainer$delegate = LazyKt__LazyJVMKt.lazy(editableBrightnessVolumeViewHolder$$ExternalSyntheticLambda0);
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = DEFAULT_SIZE;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, anonymousClass1, null, false, 12);
        DeformationFrameLayout deformationContainer2 = getDeformationContainer();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        deformationContainer2.setElevation(20.0f);
        this.isListening = super.isListening;
        this.isEditMode = super.isEditMode;
        this.isBeingDragged = super.isBeingDragged;
        this.isImmersiveOperation = super.isImmersiveOperation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1 getBvLayoutContainer() {
        return (EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1) this.bvLayoutContainer$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isImmersiveOperation() {
        return this.isImmersiveOperation;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        super.onViewCreated();
        getDeformationContainer().setDefaultPressFeedbackHandler(null);
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        deformationContainer.setImportantForAccessibility(2);
        deformationContainer.setContentDescription(null);
        deformationContainer.setRemoveIconContentDescription(deformationContainer.getContext().getResources().getString(2131955226));
        this.brightnessViewHolder.onViewCreated();
        this.volumeViewHolder.onViewCreated();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            super.setBeingDragged(z);
            this.brightnessViewHolder.setBeingDragged(z);
            this.volumeViewHolder.setBeingDragged(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            super.setEditMode(z);
            View view = this.strokeBgView;
            if (view == null) {
                view = null;
            }
            view.setVisibility(z ? 0 : 8);
            this.brightnessViewHolder.setEditMode(z);
            this.volumeViewHolder.setEditMode(z);
            View view2 = this.strokeBgView;
            if (view2 == null) {
                view2 = null;
            }
            CharSequence contentDescription = view2.getContentDescription();
            if (contentDescription != null) {
                this.contentDescription = contentDescription;
            }
            View view3 = this.strokeBgView;
            ViewExtKt.setTalkBackEnabled(view3 != null ? view3 : null, z, this.contentDescription);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setImmersiveOperation(boolean z) {
        if (this.isImmersiveOperation != z) {
            this.isImmersiveOperation = z;
            super.setImmersiveOperation(z);
            View view = this.strokeBgView;
            if (view == null) {
                view = null;
            }
            view.setVisibility(!z && this.isEditMode ? 0 : 8);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        if (this.isListening != z) {
            this.isListening = z;
            this.brightnessViewHolder.setListening(z);
            this.volumeViewHolder.setListening(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        GradientDrawable gradientDrawable;
        this.brightnessViewHolder.updateColor(colorType);
        this.volumeViewHolder.updateColor(colorType);
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(getContext(), false);
            View view = this.strokeBgView;
            int i = this.strokeWidth;
            if (zIsNeedDarkThemeColor) {
                if (view == null) {
                    view = null;
                }
                Drawable background = view.getBackground();
                gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
                if (gradientDrawable != null) {
                    gradientDrawable.setStroke(i, getContext().getResources().getColor(2131103214));
                    return;
                }
                return;
            }
            if (view == null) {
                view = null;
            }
            Drawable background2 = view.getBackground();
            gradientDrawable = background2 instanceof GradientDrawable ? (GradientDrawable) background2 : null;
            if (gradientDrawable != null) {
                gradientDrawable.setStroke(i, getContext().getResources().getColor(2131103213));
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateDrawable(List<SpanSize> list, boolean z) {
        if (list.contains(SpanCache.TWO_X_TWO)) {
            EditableBrightnessViewHolder editableBrightnessViewHolder = this.brightnessViewHolder;
            QSSpecialModeUpdateMonitor.ColorType colorType = QSSpecialModeUpdateMonitor.ColorType.UI_MODE;
            editableBrightnessViewHolder.updateColor(colorType);
            this.volumeViewHolder.updateColor(colorType);
        }
    }
}
