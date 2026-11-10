package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.serialization.Bundler$;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.IconProvider$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.view.IHostContainerAbility;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.IPressFeedBack;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.util.DragUtil;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged;
import com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformationCallback;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolder;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseEditableViewHolder extends GridViewHolder {
    public static final /* synthetic */ int $r8$clinit = 0;
    private IDeformStateChanged deformStateChangeListener;
    private DeformationFrameLayout deformationContainer;
    public boolean dragResult;
    public boolean enableResize;
    public boolean enableTileName;
    private IHostContainerAbility hostContainerAbility;
    public boolean isBeingDragged;
    public boolean isDragDisable;
    public boolean isDragMode;
    public boolean isEditMode;
    public boolean isImmersiveOperation;
    public boolean isListening;
    public boolean isPersonalArea;
    private SpanSize lastSpanSize;
    private IPressFeedBack pressFeedBack;
    public boolean realResizable;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    public final String tag;
    public float touchX;
    public float touchY;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TouchActionInDefaultMode extends PressFeedbackHelper.QsTilePressFeedback {
        public TouchActionInDefaultMode() {
            super(BaseEditableViewHolder.this.getDeformationContainer().getCurrInnerView());
        }

        @Override // com.oplus.systemui.qs.base.tile.PressFeedbackHelper.QsTilePressFeedback, android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                View currInnerView = BaseEditableViewHolder.this.getDeformationContainer().getCurrInnerView();
                if (currInnerView != null) {
                    currInnerView.getHitRect(rect);
                }
                BaseEditableViewHolder.this.getDeformationContainer().getLabelView().getHitRect(rect2);
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (!rect.contains(x, y) && !rect2.contains(x, y)) {
                    return true;
                }
            }
            return super.onTouch(view, motionEvent);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TouchActionInEditMode implements View.OnTouchListener {
        public /* synthetic */ BaseEditableViewHolder this$0;

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            GridData gridData;
            IPressFeedBack pressFeedBack;
            IPressFeedBack pressFeedBack2;
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            if (!baseEditableViewHolder.isDragMode) {
                baseEditableViewHolder.touchX = motionEvent != null ? motionEvent.getRawX() : 0.0f;
                this.this$0.touchY = motionEvent != null ? motionEvent.getRawY() : 0.0f;
                Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
                if (numValueOf != null && numValueOf.intValue() == 0) {
                    BaseEditableViewHolder baseEditableViewHolder2 = this.this$0;
                    baseEditableViewHolder2.dragResult = false;
                    GridData gridData2 = (GridData) baseEditableViewHolder2.getData();
                    if (gridData2 != null && (pressFeedBack2 = this.this$0.getPressFeedBack()) != null) {
                        ((RecyclerViewDragHelper) pressFeedBack2).findAnimation(gridData2).startPressAnim();
                        return false;
                    }
                } else if ((numValueOf != null && numValueOf.intValue() == 3) || (numValueOf != null && numValueOf.intValue() == 1)) {
                    BaseEditableViewHolder baseEditableViewHolder3 = this.this$0;
                    if (!baseEditableViewHolder3.dragResult && (gridData = (GridData) baseEditableViewHolder3.getData()) != null && (pressFeedBack = this.this$0.getPressFeedBack()) != null) {
                        ((RecyclerViewDragHelper) pressFeedBack).findAnimation(gridData).resetPressAnim();
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder$onViewCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnLongClickListener {
        public /* synthetic */ BaseEditableViewHolder this$0;

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            int i = BaseEditableViewHolder.$r8$clinit;
            GridData gridData = (GridData) baseEditableViewHolder.getData();
            BaseEditableViewHolder baseEditableViewHolder2 = this.this$0;
            if (gridData == null) {
                LogUtil.i$default(LogUtil.TAG_QS, baseEditableViewHolder2.tag, "[onLongClick] tileInfo is null", null, 8);
                return true;
            }
            boolean zIsEditMode = baseEditableViewHolder2.isEditMode();
            BaseEditableViewHolder baseEditableViewHolder3 = this.this$0;
            String str = baseEditableViewHolder3.tag;
            if (!zIsEditMode) {
                LogUtil.i$default(LogUtil.TAG_QS, str, "[onLongClick] current is not EditMode", null, 8);
                return false;
            }
            if (baseEditableViewHolder3.isDragMode) {
                LogUtil.i$default(LogUtil.TAG_QS, str, "[onLongClick] current is dragMode", null, 8);
                return false;
            }
            if (baseEditableViewHolder3.isImmersiveOperation()) {
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.tag, "[onLongClick] current is isImmersiveOperation", null, 8);
                return false;
            }
            int[] iArr = {MathKt.roundToInt((view.getWidth() - view.getMeasuredWidth()) / 2.0f) + i, MathKt.roundToInt((view.getHeight() - view.getMeasuredHeight()) / 2.0f) + i};
            view.getLocationInWindow(iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            BaseEditableViewHolder baseEditableViewHolder4 = this.this$0;
            baseEditableViewHolder4.dragResult = DragUtil.startDrag(view, null, (GridData) baseEditableViewHolder4.getData(), (int) ((this.this$0.touchX - iArr[0]) - (((view.getScaleX() - 1.0f) * view.getWidth()) / 2.0f)), (int) ((this.this$0.touchY - iArr[1]) - (((view.getScaleY() - 1.0f) * view.getHeight()) / 2.0f)));
            BaseEditableViewHolder baseEditableViewHolder5 = this.this$0;
            String str2 = baseEditableViewHolder5.tag;
            boolean z = baseEditableViewHolder5.dragResult;
            float f = baseEditableViewHolder5.touchX;
            float f2 = baseEditableViewHolder5.touchY;
            int width = view.getWidth();
            int height = view.getHeight();
            float scaleX = view.getScaleX();
            float scaleY = view.getScaleY();
            int i4 = iArr[0];
            int i5 = iArr[1];
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[onLongClick] dragResult is ", z, " touchX=", f, " touchY=");
            ChildHelper$.ExternalSyntheticOutline0.m(f2, width, " vwh=", " ", sbM);
            IconProvider$.ExternalSyntheticOutline0.m(scaleX, height, " vScaleXY=", " ", sbM);
            ChildHelper$.ExternalSyntheticOutline0.m(scaleY, i4, " location=", " ", sbM);
            sbM.append(i5);
            LogUtil.i$default(LogUtil.TAG_QS, str2, sbM.toString(), null, 8);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder$onViewCreated$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements IDeformationCallback {
        public /* synthetic */ BaseEditableViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformationCallback
        public final List getActualSupportSize(List list) {
            SpanSize spanSize;
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            int i = BaseEditableViewHolder.$r8$clinit;
            GridData gridData = (GridData) baseEditableViewHolder.getData();
            if (gridData == null || (spanSize = gridData.getSize()) == null) {
                spanSize = SpanCache.INSTANCE.spanSize(1, 1);
            }
            baseEditableViewHolder.lastSpanSize = spanSize;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                SpanSize spanSize2 = (SpanSize) obj;
                if (spanSize2.getColSpan() <= baseEditableViewHolder.getCurrRealSize().getColSpan() && spanSize2.getRowSpan() <= baseEditableViewHolder.getCurrRealSize().getRowSpan()) {
                    arrayList.add(obj);
                }
            }
            if (baseEditableViewHolder.getPosition() == -1) {
                return arrayList;
            }
            List<SpanSize> actualSupportSize = ((TileEditViewModel) baseEditableViewHolder.getViewModel()).getActualSupportSize(baseEditableViewHolder.getPosition(), arrayList);
            LogUtil.i$default(baseEditableViewHolder.tag, "getActualSupportSize rel=" + actualSupportSize, null, null, 12);
            return actualSupportSize;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged
        public final void onDeformEnd() {
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            LogUtil.i$default(LogUtil.TAG_QS, baseEditableViewHolder.tag, "onDeformEnd", null, 8);
            IDeformStateChanged deformStateChangeListener = baseEditableViewHolder.getDeformStateChangeListener();
            if (deformStateChangeListener != null) {
                deformStateChangeListener.onDeformEnd();
            }
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged
        public final void onDeformStarted() {
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            LogUtil.i$default(LogUtil.TAG_QS, baseEditableViewHolder.tag, "onDeformStarted", null, 8);
            baseEditableViewHolder.sendIntent(new TileEditIntent.DragStartIntent(baseEditableViewHolder.getPosition()));
            IDeformStateChanged deformStateChangeListener = baseEditableViewHolder.getDeformStateChangeListener();
            if (deformStateChangeListener != null) {
                deformStateChangeListener.onDeformStarted();
            }
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformationCallback
        public final void onSizeUpdated(SpanSize spanSize) {
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            LogUtil.i$default(LogUtil.TAG_QS, baseEditableViewHolder.tag, "[onSizeUpdated] position=" + baseEditableViewHolder.getPosition() + " targetSize=" + spanSize, null, 8);
            if (baseEditableViewHolder.getPosition() != -1) {
                baseEditableViewHolder.sendIntent(new TileEditIntent.DeformTileIntent(baseEditableViewHolder.getPosition(), spanSize));
            }
            baseEditableViewHolder.sendIntent(TileEditIntent.DragEndIntent.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder$onViewCreated$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements View.OnClickListener {
        public /* synthetic */ BaseEditableViewHolder this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_ADD_TILE_CLICK)) {
                return;
            }
            BaseEditableViewHolder baseEditableViewHolder = this.this$0;
            int i = BaseEditableViewHolder.$r8$clinit;
            GridData gridData = (GridData) baseEditableViewHolder.getData();
            BaseEditableViewHolder baseEditableViewHolder2 = this.this$0;
            if (gridData == null) {
                LogUtil.i$default(LogUtil.TAG_QS, baseEditableViewHolder2.tag, "[onRemoveClick] tileInfo is null", null, 8);
                return;
            }
            if (baseEditableViewHolder2.getViewModel() instanceof TileEditViewModel) {
                GridData gridData2 = (GridData) this.this$0.getData();
                GridData gridData3 = gridData2 != null ? gridData2 : null;
                if (gridData3 == null) {
                    return;
                }
                IHostContainerAbility hostContainerAbility = this.this$0.getHostContainerAbility();
                if (hostContainerAbility != null) {
                    hostContainerAbility.allowLayoutForOnce();
                }
                SeparateQsTileListLogBuffer separateQsTileListLogBuffer = this.this$0.separateQsTileLogger;
                if (separateQsTileListLogBuffer != null) {
                    separateQsTileListLogBuffer.logTile(this.this$0.tag, "user click button remove tile:" + gridData3);
                }
                this.this$0.sendIntent(new TileEditIntent.RemoveOneTileIntent(gridData3));
            }
        }
    }

    public BaseEditableViewHolder(Context context, ViewGroup viewGroup, ViewModel viewModel) {
        SeparateQsTileListLogBuffer separateQsTileLogger;
        LogBuffer logger;
        super(context, 2131559335, viewGroup, viewModel);
        this.tag = getClass().getSimpleName();
        SeparateQsTileListLogBuffer separateQsTileListLogBuffer = null;
        TileEditViewModel tileEditViewModel = viewModel instanceof TileEditViewModel ? (TileEditViewModel) viewModel : null;
        if (tileEditViewModel != null && (separateQsTileLogger = tileEditViewModel.getSeparateQsTileLogger()) != null && (logger = separateQsTileLogger.getLogger()) != null) {
            separateQsTileListLogBuffer = new SeparateQsTileListLogBuffer(logger);
        }
        this.separateQsTileLogger = separateQsTileListLogBuffer;
        this.deformationContainer = (DeformationFrameLayout) ((RecyclerView.ViewHolder) this).itemView;
        this.isPersonalArea = true;
        this.enableTileName = true;
        this.lastSpanSize = SpanCache.INSTANCE.spanSize(1, 1);
    }

    private final void setRealResizable(boolean z) {
        if (this.realResizable != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("[realResizable] changed, newValue: ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR), z);
            }
            this.realResizable = z;
            this.deformationContainer.setWithResizeBar(z);
        }
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
            sbM.append("[bindData] " + gridData);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        super.bindData((Object) gridData);
        refreshRealResizable();
        if (isEditMode()) {
            updateAllValidSize();
        }
    }

    public final SpanSize getCurrRealSize() {
        SpanSize size;
        boolean z = this.isPersonalArea;
        SpanSize size2 = null;
        GridData gridData = getGridData();
        if (z) {
            if (gridData != null && (size = gridData.getSize()) != null) {
                size2 = DataConvertorKt.revertToTwoCols(size);
            }
        } else if (gridData != null) {
            size2 = gridData.getSize();
        }
        return size2 == null ? SpanCache.INSTANCE.spanSize(1, 1) : size2;
    }

    public final IDeformStateChanged getDeformStateChangeListener() {
        return this.deformStateChangeListener;
    }

    public final DeformationFrameLayout getDeformationContainer() {
        return this.deformationContainer;
    }

    public final boolean getEnableResize() {
        return this.enableResize;
    }

    public boolean getEnableTileName() {
        return this.enableTileName;
    }

    public final IHostContainerAbility getHostContainerAbility() {
        return this.hostContainerAbility;
    }

    public final IPressFeedBack getPressFeedBack() {
        return this.pressFeedBack;
    }

    public boolean isEditMode() {
        return this.isEditMode;
    }

    public boolean isImmersiveOperation() {
        return this.isImmersiveOperation;
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void onRecycled() {
        super.onRecycled();
        setBeingDragged(false);
        setDragDisable(false);
        this.deformationContainer.onRecycled();
    }

    public void onThemeApplied(boolean z) {
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void onViewCreated() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(this.tag, "-->[onViewCreated]", "SystemUi--QuickSettings");
        }
        updateColor(QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
        DeformationFrameLayout deformationFrameLayout = this.deformationContainer;
        TouchActionInEditMode touchActionInEditMode = new TouchActionInEditMode();
        touchActionInEditMode.this$0 = this;
        VarHandle.storeStoreFence();
        deformationFrameLayout.setEditingPressFeedBackHandler(touchActionInEditMode);
        this.deformationContainer.setDefaultPressFeedbackHandler(new TouchActionInDefaultMode());
        DeformationFrameLayout deformationFrameLayout2 = this.deformationContainer;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        deformationFrameLayout2.setOnLongClickListener(anonymousClass2);
        DeformationFrameLayout deformationFrameLayout3 = this.deformationContainer;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        deformationFrameLayout3.setDeformCallback(anonymousClass3);
        DeformationFrameLayout deformationFrameLayout4 = this.deformationContainer;
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        deformationFrameLayout4.setRemoveIconClickListener(anonymousClass4);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0025  */
    public final void refreshRealResizable() {
        boolean z;
        GridData gridData = getGridData();
        List<SpanSize> allValidSize = gridData != null ? gridData.getAllValidSize() : null;
        if (this.enableResize && allValidSize != null && allValidSize.contains(getCurrRealSize())) {
            z = allValidSize.size() > 1;
        }
        setRealResizable(z);
    }

    public final void sendIntent(TileEditIntent tileEditIntent) {
        ViewModel viewModel = getViewModel();
        TileEditViewModel tileEditViewModel = viewModel instanceof TileEditViewModel ? (TileEditViewModel) viewModel : null;
        if (tileEditViewModel != null) {
            tileEditViewModel.sendIntent(tileEditIntent, this.isPersonalArea ? IntentTarget.PERSONAL_LIST : IntentTarget.OTHER_LIST);
        }
    }

    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
                sbM.append("[setIsBeingDragged] " + z + " for " + this);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            this.isBeingDragged = z;
            this.deformationContainer.setBeingDragged(z);
        }
    }

    public final void setDeformStateChangeListener(IDeformStateChanged iDeformStateChanged) {
        this.deformStateChangeListener = iDeformStateChanged;
    }

    public final void setDeformationContainer(DeformationFrameLayout deformationFrameLayout) {
        this.deformationContainer = deformationFrameLayout;
    }

    public final void setDragDisable(boolean z) {
        if (this.isDragDisable != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
                sbM.append("[setIsDragDisable] " + z + " for " + this);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            this.isDragDisable = z;
            this.deformationContainer.setDragDisable(z);
        }
    }

    public final void setDragMode(boolean z) {
        if (this.isDragMode != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
                sbM.append("[setIsDragMode] " + z + " for " + this);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            this.isDragMode = z;
            this.deformationContainer.setDragMode(z);
        }
    }

    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("[isEditMode] ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR), z);
            }
            this.isEditMode = z;
            this.deformationContainer.setEditMode(z);
            if (z) {
                updateAllValidSize();
            }
        }
    }

    public final void setEnableResize(boolean z) {
        if (this.enableResize != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("[setEnableResize] ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR), z);
            }
            this.enableResize = z;
            this.deformationContainer.setWithStrokeDrawable(z);
            refreshRealResizable();
        }
    }

    public void setEnableTileName(boolean z) {
        if (this.enableTileName != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("[enableTileName] ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR), z);
            }
            this.enableTileName = z;
            this.deformationContainer.setEnableTileName(getEnableTileName());
        }
    }

    public final void setHostContainerAbility(IHostContainerAbility iHostContainerAbility) {
        this.hostContainerAbility = iHostContainerAbility;
    }

    public void setImmersiveOperation(boolean z) {
        if (this.isImmersiveOperation != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
                sbM.append("[isImmersiveOperation] " + z + " for " + this);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            this.isImmersiveOperation = z;
            this.deformationContainer.setImmersiveOperation(z);
        }
    }

    public void setListening(boolean z) {
        this.isListening = z;
    }

    public final void setPersonalArea(boolean z) {
        if (this.isPersonalArea != z) {
            this.isPersonalArea = z;
            this.deformationContainer.setPersonalArea(z);
        }
    }

    public final void setPressFeedBack(IPressFeedBack iPressFeedBack) {
        this.pressFeedBack = iPressFeedBack;
    }

    public final void updateAllValidSize() {
        GridData gridData = getGridData();
        if (gridData == null) {
            return;
        }
        DeformationFrameLayout deformationFrameLayout = this.deformationContainer;
        List<SpanSize> allValidSize = gridData.getAllValidSize();
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(allValidSize, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj : allValidSize) {
            CellCalculatorManager.LayoutConstraint layoutConstraint = CellCalculatorManager.Companion.getInstance(getContext()).getLayoutConstraint((SpanSize) obj, getEnableTileName(), isEditMode(), false);
            linkedHashMap.put(obj, new Size(layoutConstraint.getCapsuleWidth(), layoutConstraint.getCapsuleHeight()));
        }
        deformationFrameLayout.setValidSize(linkedHashMap);
    }

    public abstract void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType);

    public void updateDrawable(List<SpanSize> list, boolean z) {
    }

    public void updateIconDrawable(boolean z) {
    }

    public void updateIconTint() {
    }

    public void updateOutline(List<SpanSize> list) {
    }
}
