package com.oplus.systemui.plugins.qs.addtile;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.customize.view.dialog.QsAddTileViewManager;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler;
import com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler$unRegister$1;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.edittop.EditTopComponent;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ScaleXYTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.TranslationYTransition;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.MaxHeightAndWidthLinearLayout;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AddTileComponent implements IQsComponent {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AddTileComponent";
    private ViewGroup addPanelContainer;
    private MaxHeightAndWidthLinearLayout addPanelView;
    private final CellAreaProvider cellAreaProvider;
    public final Context context;
    private final ContextInstance contextInstance;
    private final TileEditViewModel editViewModel;
    private final AddTileComponent$endListener$1 endListener;
    private IComponentHostAbility hostAbility;
    public boolean isAddingMode;
    private QsAddTileViewManager qsAddTileViewMgr;
    private final StatisticHandler statisticHandler;
    private TextView titleTv;
    public boolean isPersonalArea = true;
    private final Lazy transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new AddTileComponent$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$1F4VcjDYepI3ieJo2Hjb2IxWhcs(AddTileComponent addTileComponent, Bundle bundle, boolean z, GridData gridData) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "click on AddDialog", null, 8);
        QsAddTileViewManager qsAddTileViewManager = addTileComponent.qsAddTileViewMgr;
        if (qsAddTileViewManager != null) {
            qsAddTileViewManager.setNeedItemAnimator(true);
        }
        addTileComponent.editViewModel.sendIntent(new TileEditIntent.AddOneTileIntent(gridData, bundle != null ? EditTopComponent.Companion.getEnterLoc(bundle) : null, -1), z ? IntentTarget.PERSONAL_LIST : IntentTarget.OTHER_LIST);
        return Unit.INSTANCE;
    }

    public AddTileComponent(OplusQSRootView oplusQSRootView, TileEditViewModel tileEditViewModel, CellAreaProvider cellAreaProvider, StatisticHandler statisticHandler, ContextInstance contextInstance) {
        this.editViewModel = tileEditViewModel;
        this.cellAreaProvider = cellAreaProvider;
        this.statisticHandler = statisticHandler;
        this.contextInstance = contextInstance;
        this.context = oplusQSRootView.getContext();
        this.addPanelContainer = (ViewGroup) oplusQSRootView.requireViewById(2131364392);
        AddTileComponent$endListener$1 addTileComponent$endListener$1 = new AddTileComponent$endListener$1();
        addTileComponent$endListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.endListener = addTileComponent$endListener$1;
        new ViewController(this.addPanelContainer) { // from class: com.oplus.systemui.plugins.qs.addtile.AddTileComponent.1
            @Override // com.oplus.systemui.qs.base.util.ViewController
            public final void onViewAttached() {
                LogUtil.i$default(LogUtil.TAG_QS, AddTileComponent.TAG, "[onViewAttached] ", null, 8);
                AddTileComponent addTileComponent = AddTileComponent.this;
                RepeatWhenAttachedKt.repeatWhenAttached(addTileComponent.addPanelContainer, EmptyCoroutineContext.INSTANCE, new AddTileComponent$1$onViewAttached$1(addTileComponent, null));
                addTileComponent.statisticHandler.registerReceiver(addTileComponent.contextInstance.getSysUiContext(), addTileComponent.editViewModel);
            }

            @Override // com.oplus.systemui.qs.base.util.ViewController
            public final void onViewDetached() {
                LogUtil.i$default(LogUtil.TAG_QS, AddTileComponent.TAG, "[onViewDetached] ", null, 8);
                StatisticHandler statisticHandler2 = AddTileComponent.this.statisticHandler;
                statisticHandler2.getClass();
                Handler workHandler = SystemUIThreadUtil.getWorkHandler();
                StatisticHandler$unRegister$1 statisticHandler$unRegister$1 = new StatisticHandler$unRegister$1();
                statisticHandler$unRegister$1.this$0 = statisticHandler2;
                VarHandle.storeStoreFence();
                workHandler.post(statisticHandler$unRegister$1);
            }
        }.init();
    }

    private final int getAHeight() {
        return this.cellAreaProvider.getCellAreaConfig().getAPersonalHeight() + getBottomHeight();
    }

    private final int getBHeight() {
        return this.cellAreaProvider.getCellAreaConfig().getIndicatorHeight() + this.cellAreaProvider.getCellAreaConfig().getBOtherHeight() + getBottomHeight();
    }

    private final int getBottomHeight() {
        return this.cellAreaProvider.getCellAreaConfig().getBottomHeight();
    }

    private final int getPanelViewPaddingBottom() {
        OplusQSSpecialModeProvider companion = OplusQSSpecialModeProvider.Companion.getInstance();
        if (companion.isSplitAdd()) {
            return CellCalculatorManager.Companion.lockDpi(this.context, 2131171506);
        }
        return companion.isGestureBarHide() ? CellCalculatorManager.Companion.lockDpi(this.context, 2131171509) : this.cellAreaProvider.getCellAreaConfig().getGHeight();
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object next;
        boolean zIsSplitAdd = OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd();
        Iterator<T> it = set.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "adding_transition_position"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout = this.addPanelView;
            if (zIsSplitAdd) {
                if (maxHeightAndWidthLinearLayout != null) {
                    springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(maxHeightAndWidthLinearLayout, ScaleXYTransition.class));
                }
                springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.addPanelContainer, FadeTransition.class));
            } else {
                if (maxHeightAndWidthLinearLayout != null) {
                    springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(maxHeightAndWidthLinearLayout, TranslationYTransition.class));
                }
                springTransition.addOnEndListener(this.endListener);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return super.createSpringTransition(str);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
        getTransitionProperties().destroy();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return super.getAnimatorView(i);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return 0;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        this.hostAbility = iComponentHostAbility;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        ViewGroup.LayoutParams layoutParams;
        this.isAddingMode = true;
        this.isPersonalArea = z;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("[onEnterAddingMode] isPersonalArea=", ", withAnimation=", ", extra=", z, z2);
        sbM.append(bundle);
        QsAddTileViewManager qsAddTileViewManager = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        QsAddTileViewManager qsAddTileViewManager2 = this.qsAddTileViewMgr;
        if (qsAddTileViewManager2 != null) {
            qsAddTileViewManager2.setNeedItemAnimator(false);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "showAddPanelView addPanelContainer=" + this.addPanelContainer + " addPanelView=" + this.addPanelView, null, 8);
        boolean zIsAddDialogLowGaussianColor = QsColorUtil.isAddDialogLowGaussianColor(this.context);
        TextView textView = this.titleTv;
        if (zIsAddDialogLowGaussianColor) {
            if (textView != null) {
                textView.setTextColor(this.context.getColorStateList(2131103190));
            }
        } else if (textView != null) {
            textView.setTextColor(this.context.getColorStateList(2131103191));
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "showAddPanelViewInner qsAddTileViewMgr=" + this.qsAddTileViewMgr, null, 8);
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout = this.addPanelView;
            if (maxHeightAndWidthLinearLayout != null) {
                maxHeightAndWidthLinearLayout.setScaleX(1.0f);
                maxHeightAndWidthLinearLayout.setScaleY(1.0f);
            }
        } else {
            int bHeight = this.isPersonalArea ? getBHeight() : getAHeight();
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout2 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout2 != null && (layoutParams = maxHeightAndWidthLinearLayout2.getLayoutParams()) != null) {
                layoutParams.height = bHeight;
            }
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout3 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout3 != null) {
                maxHeightAndWidthLinearLayout3.setTranslationY(0.0f);
            }
            this.addPanelContainer.setTranslationY(0.0f);
        }
        ViewGroup.LayoutParams layoutParams2 = this.addPanelContainer.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.height = -1;
        }
        ViewGroup viewGroup = this.addPanelContainer;
        viewGroup.setLayoutParams(viewGroup.getLayoutParams());
        this.addPanelContainer.setVisibility(0);
        QsAddTileViewManager qsAddTileViewManager3 = this.qsAddTileViewMgr;
        if (qsAddTileViewManager3 == null) {
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout4 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout4 != null) {
                qsAddTileViewManager = new QsAddTileViewManager(this.context, this.editViewModel, maxHeightAndWidthLinearLayout4);
                qsAddTileViewManager.setFilterOutBigTile(!z);
                qsAddTileViewManager.onCreate();
            }
            this.qsAddTileViewMgr = qsAddTileViewManager;
        } else {
            boolean z3 = !z;
            boolean z4 = !(z3 == qsAddTileViewManager3.getFilterOutBigTile());
            QsAddTileViewManager qsAddTileViewManager4 = this.qsAddTileViewMgr;
            if (qsAddTileViewManager4 != null) {
                qsAddTileViewManager4.setFilterOutBigTile(z3);
            }
            QsAddTileViewManager qsAddTileViewManager5 = this.qsAddTileViewMgr;
            if (qsAddTileViewManager5 != null) {
                qsAddTileViewManager5.updateTileAdapterData(z4);
            }
        }
        QsAddTileViewManager qsAddTileViewManager6 = this.qsAddTileViewMgr;
        if (qsAddTileViewManager6 != null) {
            AddTileComponent$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new AddTileComponent$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = this;
            externalSyntheticLambda1.f$1 = bundle;
            externalSyntheticLambda1.f$2 = z;
            VarHandle.storeStoreFence();
            qsAddTileViewManager6.setOnTileClickListener(externalSyntheticLambda1);
        }
        QsAddTileViewManager qsAddTileViewManager7 = this.qsAddTileViewMgr;
        if (qsAddTileViewManager7 != null) {
            qsAddTileViewManager7.updateBackGround();
        }
        this.addPanelContainer.setClickable(true);
        ViewGroup viewGroup2 = this.addPanelContainer;
        showAddPanelView.1 r11 = new showAddPanelView.1();
        r11.this$0 = this;
        VarHandle.storeStoreFence();
        viewGroup2.setOnClickListener(r11);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        super.onEnterDragMode(z, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        FrameLayout.LayoutParams layoutParams;
        Context contextCreateDarkColorContext;
        QsAddTileViewManager qsAddTileViewManager = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[onEnterEditMode] withAnimation=", z), null, 8);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[createTilePanelToRootView] addPanelContainer=" + this.addPanelContainer, null, 8);
        if (this.addPanelView == null) {
            boolean zIsWallpaperBlurDisable = ScrimUtil.isWallpaperBlurDisable();
            Context context = this.context;
            if (zIsWallpaperBlurDisable) {
                contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, (QsColorUtil.isAddDialogLowGaussianColor(context) || OplusDarkModeUtil.isNightMode(this.context)) ? false : true);
            } else {
                contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, !OplusDarkModeUtil.isNightMode(context));
            }
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout = (MaxHeightAndWidthLinearLayout) LayoutInflater.from(contextCreateDarkColorContext).inflate(2131559323, this.addPanelContainer, false);
            this.addPanelView = maxHeightAndWidthLinearLayout;
            this.titleTv = maxHeightAndWidthLinearLayout != null ? (TextView) maxHeightAndWidthLinearLayout.requireViewById(2131361988) : null;
        }
        this.addPanelContainer.removeAllViews();
        AddTileCalculator addTileCalculator = AddTileCalculator.INSTANCE;
        addTileCalculator.calculate(this.context, this.cellAreaProvider);
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            layoutParams = new FrameLayout.LayoutParams(addTileCalculator.getWidth(), -1, 8388661);
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = companion.lockDpi(this.context, 2131171525);
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = companion.lockDpi(this.context, 2131171505);
            layoutParams.setMarginStart(addTileCalculator.getMarginStart());
            layoutParams.setMarginEnd(addTileCalculator.getMarginEnd());
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout2 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout2 != null) {
                maxHeightAndWidthLinearLayout2.setMaxHeight(companion.lockDpi(maxHeightAndWidthLinearLayout2.getContext(), 2131171522));
                maxHeightAndWidthLinearLayout2.setScaleX(0.8f);
                maxHeightAndWidthLinearLayout2.setScaleY(0.8f);
            }
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout3 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout3 != null) {
                maxHeightAndWidthLinearLayout3.setTranslationY(0.0f);
            }
        } else {
            int bHeight = this.isPersonalArea ? getBHeight() : getAHeight();
            layoutParams = new FrameLayout.LayoutParams(-1, bHeight, 81);
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout4 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout4 != null) {
                maxHeightAndWidthLinearLayout4.setTranslationY(bHeight);
            }
        }
        this.addPanelContainer.addView(this.addPanelView, layoutParams);
        this.addPanelContainer.setTranslationY(0.0f);
        this.addPanelContainer.setVisibility(4);
        MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout5 = this.addPanelView;
        if (maxHeightAndWidthLinearLayout5 != null) {
            maxHeightAndWidthLinearLayout5.setPaddingRelative(maxHeightAndWidthLinearLayout5.getPaddingLeft(), 0, maxHeightAndWidthLinearLayout5.getPaddingRight(), getPanelViewPaddingBottom());
        }
        if (this.qsAddTileViewMgr == null) {
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout6 = this.addPanelView;
            if (maxHeightAndWidthLinearLayout6 != null) {
                qsAddTileViewManager = new QsAddTileViewManager(this.context, this.editViewModel, maxHeightAndWidthLinearLayout6);
                qsAddTileViewManager.onCreate();
            }
            this.qsAddTileViewMgr = qsAddTileViewManager;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, OplusLockIconView$.ExternalSyntheticOutline0.m("[onExitAddingMode] withAnimation=", OplusPreventModeData.SEPARATOR, z), null, 8);
        this.isAddingMode = false;
        this.addPanelContainer.setClickable(false);
        this.addPanelContainer.setOnClickListener(null);
        QsAddTileViewManager qsAddTileViewManager = this.qsAddTileViewMgr;
        if (qsAddTileViewManager != null) {
            qsAddTileViewManager.setOnTileClickListener(null);
        }
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout = this.addPanelView;
            if (maxHeightAndWidthLinearLayout != null) {
                maxHeightAndWidthLinearLayout.setScaleX(0.8f);
                maxHeightAndWidthLinearLayout.setScaleY(0.8f);
            }
            this.addPanelContainer.setVisibility(4);
            return;
        }
        int bHeight = this.isPersonalArea ? getBHeight() : getAHeight();
        ViewGroup.LayoutParams layoutParams = this.addPanelContainer.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = bHeight;
        }
        this.addPanelContainer.setLayoutParams(layoutParams);
        MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout2 = this.addPanelView;
        if (maxHeightAndWidthLinearLayout2 != null) {
            maxHeightAndWidthLinearLayout2.setTranslationY(bHeight);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[onExitEditMode] withAnimation=", z), null, 8);
        this.addPanelContainer.removeAllViews();
        this.addPanelContainer.setVisibility(4);
        this.qsAddTileViewMgr = null;
        this.addPanelView = null;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        super.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        super.setEmptyTouchListener(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        super.setLayerType(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        super.setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(colorType);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        super.updateDrawable(list, z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        super.updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        super.updateResource(z, context);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
    }
}
