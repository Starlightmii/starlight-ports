package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.util.QSColorUtil;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import com.oplus.systemui.plugins.qs.widget.OneHandedEnableProvider;
import com.oplus.systemui.plugins.qs.widget.OplusQsToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableVolumeViewHolder extends BaseEditableViewHolder {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(1, 2);
    private static final String TAG = "EditableVolumeViewHolder";
    public boolean isBeingDragged;
    public boolean isEditMode;
    public boolean isListening;
    private final View.OnAttachStateChangeListener onAttachStateListener;
    private final Lazy qsVolume$delegate;
    private OplusQsToggleSliderLayout toggleSliderLayout;
    private final Lazy volumeSliderController$delegate;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableVolumeViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ Context $context;
        public /* synthetic */ EditableVolumeViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.$context);
            EditableVolumeViewHolder editableVolumeViewHolder = this.this$0;
            View viewInflate = layoutInflaterFrom.inflate(2131559383, (ViewGroup) editableVolumeViewHolder.getDeformationContainer(), false);
            editableVolumeViewHolder.initSliderLayout(viewInflate instanceof OplusQsToggleSliderLayout ? (OplusQsToggleSliderLayout) viewInflate : null);
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableVolumeViewHolder$initSliderLayout$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02331 implements OneHandedEnableProvider {
        @Override // com.oplus.systemui.plugins.qs.widget.OneHandedEnableProvider
        public boolean getOneHandedEnable() {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            if (proxy != null) {
                return proxy.getOneHandedEnable();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableVolumeViewHolder$onViewCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnClickListenerC02341 implements View.OnClickListener {
        public /* synthetic */ EditableVolumeViewHolder this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            EditableVolumeViewHolder editableVolumeViewHolder = this.this$0;
            Companion companion = EditableVolumeViewHolder.Companion;
            editableVolumeViewHolder.showVolumeDialog(false);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableVolumeViewHolder$onViewCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnLongClickListener {
        public /* synthetic */ EditableVolumeViewHolder this$0;

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            EditableVolumeViewHolder editableVolumeViewHolder = this.this$0;
            Companion companion = EditableVolumeViewHolder.Companion;
            editableVolumeViewHolder.showVolumeDialog(true);
            return true;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$BM2j69Ta-D9gVpcmIoosqpHyWd0, reason: not valid java name */
    public static OplusQsVolumeSliderController m174$r8$lambda$BM2j69TaD9gVpcmIoosqpHyWd0(EditableVolumeViewHolder editableVolumeViewHolder) {
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = editableVolumeViewHolder.toggleSliderLayout;
        QSVolume qsVolume = editableVolumeViewHolder.getQsVolume();
        Handler mainThreadHandler = editableVolumeViewHolder.getContext().getMainThreadHandler();
        Handler workHandler = QSThreadUtil.getWorkHandler();
        EditableVolumeViewHolder$create$1 editableVolumeViewHolder$create$1 = new EditableVolumeViewHolder$create$1();
        editableVolumeViewHolder$create$1.this$0 = editableVolumeViewHolder;
        VarHandle.storeStoreFence();
        return new OplusQsVolumeSliderController(oplusQsToggleSliderLayout, qsVolume, mainThreadHandler, workHandler, editableVolumeViewHolder$create$1);
    }

    public EditableVolumeViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel);
        this.qsVolume$delegate = LazyKt__LazyJVMKt.lazy(new EditableVolumeViewHolder$$ExternalSyntheticLambda0());
        EditableVolumeViewHolder$$ExternalSyntheticLambda1 editableVolumeViewHolder$$ExternalSyntheticLambda1 = new EditableVolumeViewHolder$$ExternalSyntheticLambda1();
        editableVolumeViewHolder$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeSliderController$delegate = LazyKt__LazyJVMKt.lazy(editableVolumeViewHolder$$ExternalSyntheticLambda1);
        EditableVolumeViewHolder$onAttachStateListener$1 editableVolumeViewHolder$onAttachStateListener$1 = new EditableVolumeViewHolder$onAttachStateListener$1();
        editableVolumeViewHolder$onAttachStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachStateListener = editableVolumeViewHolder$onAttachStateListener$1;
        this.isEditMode = super.isEditMode;
        this.isBeingDragged = super.isBeingDragged;
        this.isListening = super.isListening;
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = DEFAULT_SIZE;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$context = context;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, anonymousClass1, null, false, 12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QSVolume getQsVolume() {
        return (QSVolume) this.qsVolume$delegate.getValue();
    }

    private final OplusQsVolumeSliderController getVolumeSliderController() {
        return (OplusQsVolumeSliderController) this.volumeSliderController$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        super.bindData(gridData);
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955301));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
    }

    public final void initSliderLayout(OplusQsToggleSliderLayout oplusQsToggleSliderLayout) {
        this.toggleSliderLayout = oplusQsToggleSliderLayout;
        if (oplusQsToggleSliderLayout != null) {
            oplusQsToggleSliderLayout.setOneHandedEnableProvider(new C02331());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        super.onViewCreated();
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955301));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = this.toggleSliderLayout;
        if (oplusQsToggleSliderLayout != null && oplusQsToggleSliderLayout.isAttachedToWindow()) {
            this.onAttachStateListener.onViewAttachedToWindow(((RecyclerView.ViewHolder) this).itemView);
        }
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout2 = this.toggleSliderLayout;
        if (oplusQsToggleSliderLayout2 != null) {
            oplusQsToggleSliderLayout2.addOnAttachStateChangeListener(this.onAttachStateListener);
        }
        getVolumeSliderController().init();
        OplusQsVolumeSliderController.updateSeekBarColor$default(getVolumeSliderController(), QsViewBackgroundKt.isMixColorSupport(getContext(), QSColorUtil.isGaussBlurDisabled()), false, null, 6);
        OplusQsVolumeSliderController volumeSliderController = getVolumeSliderController();
        ViewOnClickListenerC02341 viewOnClickListenerC02341 = new ViewOnClickListenerC02341();
        viewOnClickListenerC02341.this$0 = this;
        VarHandle.storeStoreFence();
        volumeSliderController.setOnLayoutClickListener(viewOnClickListenerC02341);
        OplusQsVolumeSliderController volumeSliderController2 = getVolumeSliderController();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        volumeSliderController2.setLongClickListener(anonymousClass2);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            super.setBeingDragged(z);
            OplusQsVolumeSliderController.updateSeekBarColor$default(getVolumeSliderController(), QsViewBackgroundKt.isMixColorSupport(getContext(), QSColorUtil.isGaussBlurDisabled()), z, null, 4);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            super.setEditMode(z);
            getVolumeSliderController().setIsEditable(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        if (this.isListening != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[isListening] ", z), null, 8);
            this.isListening = z;
            getVolumeSliderController().setListening(z);
        }
    }

    public final void showVolumeDialog(boolean z) {
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null) {
            proxy.showVolumeDialog(getQsVolume(), getVolumeSliderController(), z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditableVolumeViewHolder-->".concat("[updateColor] " + colorType));
        }
        getVolumeSliderController().updateColor(QsViewBackgroundKt.isMixColorSupport(getContext(), QSColorUtil.isGaussBlurDisabled()), colorType);
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = this.toggleSliderLayout;
        if (oplusQsToggleSliderLayout != null) {
            oplusQsToggleSliderLayout.updateColor(QsViewBackgroundKt.isMixColorSupport(getContext(), QSColorUtil.isGaussBlurDisabled()), this.isBeingDragged, colorType);
        }
        QsLabelView labelView = getDeformationContainer().getLabelView();
        int qsColorState = QsColorUtil.getQsColorState();
        QsLabelView.Companion companion = QsLabelView.Companion;
        labelView.updateColor(qsColorState, false, false);
    }
}
