package com.oplus.systemui.plugins.qs.edittop;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.OplusQSRootViewComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.util.QSColorUtil;
import com.oplus.systemui.qs.base.background.BaseBackgroundProxy;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.AlphaTransition;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.TranslationXTransition;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditTopComponent implements IQsComponent {
    public static final Companion Companion = new Companion();
    private static final String LOC_ENTER_FROM = "loc_enter_from";
    private static final String TAG = "EditTopController";
    private final ImageView addBtn;
    private final Lazy addBtnBackgroundProxy$delegate;
    private final CellAreaProvider cellAreaProvider;
    public final Context context;
    private IComponentHostAbility hostAbility;
    public boolean isEditMode;
    private final QSQuickEntrance qsQSQuickEntrance;
    private final ImageView qsSettingBtn;
    private final Lazy qsSettingBtnBackgroundProxy$delegate;
    private final TextView saveBtn;
    private final Lazy saveBtnBackgroundProxy$delegate;
    private final View saveBtnContainer;
    public List topButtonPressFeedbacks;
    private final Lazy transitionProperties$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void addEnterLoc(Bundle bundle, Loc loc) {
            bundle.putSerializable(EditTopComponent.LOC_ENTER_FROM, loc);
        }

        public final Loc getEnterLoc(Bundle bundle) {
            return (Loc) bundle.getSerializable(EditTopComponent.LOC_ENTER_FROM, Loc.class);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.edittop.EditTopComponent$startComponent$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ EditTopComponent this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            switch (this.$r8$classId) {
                case 0:
                    LogUtil.i$default(LogUtil.TAG_QS, EditTopComponent.TAG, "addBtn clicked", null, 8);
                    IComponentHostAbility iComponentHostAbility = this.this$0.hostAbility;
                    if (iComponentHostAbility != null) {
                        iComponentHostAbility.enterAddingMode(false, true, null);
                    }
                    break;
                case 1:
                    LogUtil.i$default(LogUtil.TAG_QS, EditTopComponent.TAG, "saveBtn clicked", null, 8);
                    IComponentHostAbility iComponentHostAbility2 = this.this$0.hostAbility;
                    if (iComponentHostAbility2 != null) {
                        ((OplusQSRootViewComponent) iComponentHostAbility2).exitEditMode(true);
                    }
                    break;
                default:
                    LogUtil.i$default(LogUtil.TAG_QS, EditTopComponent.TAG, "qsSettingBtn clicked", null, 8);
                    this.this$0.qsQSQuickEntrance.handleQSSettingsOnClick(this.this$0.qsSettingBtn);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$EbbvZd08MR1ToK-j9tfEgfYjJhk, reason: not valid java name */
    public static BaseBackgroundProxy m223$r8$lambda$EbbvZd08MR1ToKj9tfEgfYjJhk(EditTopComponent editTopComponent, OplusQSRootView oplusQSRootView) {
        ImageView imageView = editTopComponent.addBtn;
        Float fValueOf = Float.valueOf(1.0f);
        EditTopComponent$$ExternalSyntheticLambda4 editTopComponent$$ExternalSyntheticLambda4 = new EditTopComponent$$ExternalSyntheticLambda4(0);
        editTopComponent$$ExternalSyntheticLambda4.f$0 = oplusQSRootView;
        VarHandle.storeStoreFence();
        return new BaseBackgroundProxy(imageView, fValueOf, editTopComponent$$ExternalSyntheticLambda4);
    }

    /* JADX INFO: renamed from: $r8$lambda$QMAfIT4sV5tBpQBtX2rHMTRi-DE, reason: not valid java name */
    public static BaseBackgroundProxy m224$r8$lambda$QMAfIT4sV5tBpQBtX2rHMTRiDE(EditTopComponent editTopComponent, OplusQSRootView oplusQSRootView) {
        ImageView imageView = editTopComponent.qsSettingBtn;
        EditTopComponent$$ExternalSyntheticLambda4 editTopComponent$$ExternalSyntheticLambda4 = new EditTopComponent$$ExternalSyntheticLambda4(2);
        editTopComponent$$ExternalSyntheticLambda4.f$0 = oplusQSRootView;
        VarHandle.storeStoreFence();
        return new BaseBackgroundProxy(imageView, null, editTopComponent$$ExternalSyntheticLambda4);
    }

    public static BaseBackgroundProxy $r8$lambda$Sq7s9wZABbAM9cFH1mEA4MNc2XI(EditTopComponent editTopComponent, OplusQSRootView oplusQSRootView) {
        View view = editTopComponent.saveBtnContainer;
        Float fValueOf = Float.valueOf(1.0f);
        EditTopComponent$$ExternalSyntheticLambda4 editTopComponent$$ExternalSyntheticLambda4 = new EditTopComponent$$ExternalSyntheticLambda4(1);
        editTopComponent$$ExternalSyntheticLambda4.f$0 = oplusQSRootView;
        VarHandle.storeStoreFence();
        return new BaseBackgroundProxy(view, fValueOf, editTopComponent$$ExternalSyntheticLambda4);
    }

    public EditTopComponent(OplusQSRootView oplusQSRootView, CellAreaProvider cellAreaProvider, QSQuickEntrance qSQuickEntrance) {
        this.cellAreaProvider = cellAreaProvider;
        this.qsQSQuickEntrance = qSQuickEntrance;
        this.addBtn = (ImageView) oplusQSRootView.requireViewById(2131361990);
        this.saveBtnContainer = oplusQSRootView.requireViewById(2131364981);
        TextView textView = (TextView) oplusQSRootView.requireViewById(2131364980);
        this.saveBtn = textView;
        this.qsSettingBtn = (ImageView) oplusQSRootView.requireViewById(2131364800);
        this.context = textView.getContext();
        this.transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new EditTopComponent$$ExternalSyntheticLambda0());
        EditTopComponent$$ExternalSyntheticLambda1 editTopComponent$$ExternalSyntheticLambda1 = new EditTopComponent$$ExternalSyntheticLambda1(0);
        editTopComponent$$ExternalSyntheticLambda1.f$0 = this;
        editTopComponent$$ExternalSyntheticLambda1.f$1 = oplusQSRootView;
        VarHandle.storeStoreFence();
        this.saveBtnBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(editTopComponent$$ExternalSyntheticLambda1);
        EditTopComponent$$ExternalSyntheticLambda1 editTopComponent$$ExternalSyntheticLambda2 = new EditTopComponent$$ExternalSyntheticLambda1(1);
        editTopComponent$$ExternalSyntheticLambda2.f$0 = this;
        editTopComponent$$ExternalSyntheticLambda2.f$1 = oplusQSRootView;
        VarHandle.storeStoreFence();
        this.addBtnBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(editTopComponent$$ExternalSyntheticLambda2);
        EditTopComponent$$ExternalSyntheticLambda1 editTopComponent$$ExternalSyntheticLambda3 = new EditTopComponent$$ExternalSyntheticLambda1(2);
        editTopComponent$$ExternalSyntheticLambda3.f$0 = this;
        editTopComponent$$ExternalSyntheticLambda3.f$1 = oplusQSRootView;
        VarHandle.storeStoreFence();
        this.qsSettingBtnBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(editTopComponent$$ExternalSyntheticLambda3);
    }

    private final BaseBackgroundProxy getAddBtnBackgroundProxy() {
        return (BaseBackgroundProxy) this.addBtnBackgroundProxy$delegate.getValue();
    }

    private final BaseBackgroundProxy getQsSettingBtnBackgroundProxy() {
        return (BaseBackgroundProxy) this.qsSettingBtnBackgroundProxy$delegate.getValue();
    }

    private final BaseBackgroundProxy getSaveBtnBackgroundProxy() {
        return (BaseBackgroundProxy) this.saveBtnBackgroundProxy$delegate.getValue();
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        Set<? extends SpringTransition> set2 = set;
        Iterator<T> it = set2.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "add_save_icon_alpha_transition"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.saveBtnContainer, FadeTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.addBtn, FadeTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.qsSettingBtn, FadeTransition.class));
        }
        Iterator<T> it2 = set2.iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next2).getTransitionName(), "parent_translation_transition"));
        SpringTransition springTransition2 = (SpringTransition) next2;
        if (springTransition2 != null) {
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.saveBtnContainer, BoundsTransition.class));
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.addBtn, BoundsTransition.class));
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.qsSettingBtn, BoundsTransition.class));
        }
        Iterator<T> it3 = set2.iterator();
        do {
            if (!it3.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it3.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next3).getTransitionName(), "drag_transition"));
        SpringTransition springTransition3 = (SpringTransition) next3;
        if (springTransition3 != null) {
            springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.saveBtnContainer, AlphaTransition.class));
            springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.addBtn, AlphaTransition.class));
            springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.qsSettingBtn, AlphaTransition.class));
        }
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            for (Object obj2 : set2) {
                if (Intrinsics.areEqual(((SpringTransition) obj2).getTransitionName(), "adding_transition_position")) {
                    obj = obj2;
                    break;
                }
            }
            SpringTransition springTransition4 = (SpringTransition) obj;
            if (springTransition4 != null) {
                springTransition4.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.saveBtnContainer, BoundsTransition.class));
                springTransition4.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.saveBtnContainer, TranslationXTransition.class));
                springTransition4.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.qsSettingBtn, BoundsTransition.class));
                springTransition4.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.qsSettingBtn, TranslationXTransition.class));
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return this.saveBtnContainer.getVisibility() == 0 || this.addBtn.getVisibility() == 0 || this.qsSettingBtn.getVisibility() == 0;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return Intrinsics.areEqual(str, EditModeSwitchAnimationManager.SCENE_EDIT) ? (SpringTransition[]) CollectionsKt__CollectionsKt.listOfNotNull(IEditModeSwitcher.Companion.createTransition("add_save_icon_alpha_transition")).toArray(new SpringTransition[0]) : new SpringTransition[0];
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
        if (this.isEditMode) {
            return this.addBtn;
        }
        return null;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return super.getSubSize();
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
        updateSaveAndAddBtnState(false);
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            float addSplitPanelMargin = AddTileCalculator.INSTANCE.getAddSplitPanelMargin() - this.cellAreaProvider.getCellAreaConfig().getMinMargin();
            this.saveBtnContainer.setTranslationX(addSplitPanelMargin);
            this.qsSettingBtn.setTranslationX(addSplitPanelMargin);
            if (ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1) {
                View view = this.saveBtnContainer;
                view.setTranslationX(-view.getTranslationX());
                ImageView imageView = this.qsSettingBtn;
                imageView.setTranslationX(-imageView.getTranslationX());
            }
        }
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("[onEnterAddingMode] isPersonalArea=", ", withAnimation=", ", extra=", z, z2);
        sbM.append(bundle);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[onEnterDragMode] withAnimation=" + z + ", extra=" + bundle, null, 8);
        updateSaveAndAddBtnState(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        List list = this.topButtonPressFeedbacks;
        if (list == null) {
            list = null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((PressFeedbackHelper.QsTilePressFeedback) it.next()).reset();
        }
        Iterator it2 = CollectionsKt__CollectionsKt.listOf(new View[]{this.saveBtnContainer, this.addBtn, this.qsSettingBtn}).iterator();
        while (it2.hasNext()) {
            SharedSpotLightEffect.detach((View) it2.next(), "detach");
        }
        this.isEditMode = true;
        Drawable drawable = this.context.getDrawable(2131237497);
        Drawable drawable2 = this.context.getDrawable(2131236024);
        QSColorUtil.adaptiveColor(this.saveBtn, this.context.getColor(R.color.white));
        QSColorUtil.adaptiveColor(this.addBtn, drawable);
        QSColorUtil.adaptiveColor(this.qsSettingBtn, drawable2);
        this.saveBtnContainer.setVisibility(0);
        this.addBtn.setVisibility(0);
        this.qsSettingBtn.setVisibility(0);
        this.saveBtnContainer.setClickable(true);
        this.addBtn.setClickable(true);
        this.qsSettingBtn.setClickable(true);
        updateButtonTopMargin(this.saveBtnContainer);
        updateButtonTopMargin(this.addBtn);
        updateButtonTopMargin(this.qsSettingBtn);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[onExitAddingMode] withAnimation=", z), null, 8);
        updateSaveAndAddBtnState(true);
        this.saveBtnContainer.setTranslationX(0.0f);
        this.qsSettingBtn.setTranslationX(0.0f);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[onExitDragMode] withAnimation=true", null, 8);
        updateSaveAndAddBtnState(true);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        this.isEditMode = false;
        this.saveBtnContainer.setVisibility(4);
        this.addBtn.setVisibility(4);
        this.qsSettingBtn.setVisibility(4);
        this.saveBtnContainer.setClickable(false);
        this.addBtn.setClickable(false);
        this.qsSettingBtn.setClickable(false);
        updateButtonTopMargin(this.saveBtnContainer);
        updateButtonTopMargin(this.addBtn);
        updateButtonTopMargin(this.qsSettingBtn);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    public final void refreshItemViewBackground() {
        QsColorUtil.INSTANCE.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            return;
        }
        getSaveBtnBackgroundProxy().refreshViewBackground();
        getAddBtnBackgroundProxy().refreshViewBackground();
        getQsSettingBtnBackgroundProxy().refreshViewBackground();
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
        int i = z ? 2 : 0;
        this.saveBtnContainer.setLayerType(i, null);
        this.addBtn.setLayerType(i, null);
        this.qsSettingBtn.setLayerType(i, null);
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
        new ViewController(this.saveBtnContainer) { // from class: com.oplus.systemui.plugins.qs.edittop.EditTopComponent.startComponent.1
            @Override // com.oplus.systemui.qs.base.util.ViewController
            public final void onViewAttached() {
                Companion companion = EditTopComponent.Companion;
                EditTopComponent.this.refreshItemViewBackground();
            }

            @Override // com.oplus.systemui.qs.base.util.ViewController
            public final void onViewDetached() {
            }
        }.init();
        ImageView imageView = this.addBtn;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        imageView.setOnClickListener(anonymousClass2);
        View view = this.saveBtnContainer;
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(1);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        view.setOnClickListener(anonymousClass3);
        ImageView imageView2 = this.qsSettingBtn;
        AnonymousClass2 anonymousClass4 = new AnonymousClass2(2);
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        imageView2.setOnClickListener(anonymousClass4);
        List<View> listListOf = CollectionsKt__CollectionsKt.listOf(new View[]{this.saveBtnContainer, this.addBtn, this.qsSettingBtn});
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listListOf, 10));
        for (View view2 : listListOf) {
            arrayList.add(PressFeedbackHelper.attachPressFeedback(view2, view2));
        }
        this.topButtonPressFeedbacks = arrayList;
    }

    public final void updateButtonTopMargin(View view) {
        int c5Height;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        boolean z = this.isEditMode;
        CellAreaProvider cellAreaProvider = this.cellAreaProvider;
        if (z) {
            c5Height = cellAreaProvider.getCellAreaConfig().getAHeight();
        } else {
            c5Height = (this.cellAreaProvider.getCellAreaConfig().getC5Height() + (this.cellAreaProvider.getCellAreaConfig().getStatusBarHeight() + cellAreaProvider.getCellAreaConfig().getAHeight())) - this.cellAreaProvider.getCellAreaConfig().getAEditHeight();
        }
        marginLayoutParams.topMargin = c5Height;
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(colorType);
        refreshItemViewBackground();
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
        refreshItemViewBackground();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        super.updateResource(z, context);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131171499) + this.cellAreaProvider.getCellAreaConfig().getMinMargin();
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(2131171500);
        if (dimensionPixelSize < dimensionPixelSize2) {
            dimensionPixelSize = dimensionPixelSize2;
        }
        ((ViewGroup.MarginLayoutParams) this.saveBtnContainer.getLayoutParams()).setMarginEnd(dimensionPixelSize);
        ((ViewGroup.MarginLayoutParams) this.addBtn.getLayoutParams()).setMarginStart(dimensionPixelSize);
        View view = this.saveBtnContainer;
        view.setLayoutParams(view.getLayoutParams());
        ImageView imageView = this.addBtn;
        imageView.setLayoutParams(imageView.getLayoutParams());
    }

    public final void updateSaveAndAddBtnState(boolean z) {
        View view = this.saveBtnContainer;
        if (z) {
            view.setAlpha(1.0f);
            this.addBtn.setAlpha(1.0f);
            this.qsSettingBtn.setAlpha(1.0f);
        } else {
            view.setAlpha(0.4f);
            this.addBtn.setAlpha(0.4f);
            this.qsSettingBtn.setAlpha(0.4f);
        }
        boolean z2 = false;
        this.saveBtnContainer.setVisibility(!this.isEditMode ? 4 : 0);
        this.addBtn.setVisibility(!this.isEditMode ? 4 : 0);
        this.qsSettingBtn.setVisibility(this.isEditMode ? 0 : 4);
        this.saveBtnContainer.setClickable(this.isEditMode && z);
        this.addBtn.setClickable(this.isEditMode && z);
        ImageView imageView = this.qsSettingBtn;
        if (this.isEditMode && z) {
            z2 = true;
        }
        imageView.setClickable(z2);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.addBtn.setTransitionAlpha(f);
        this.saveBtnContainer.setTransitionAlpha(f);
        this.qsSettingBtn.setTransitionAlpha(f);
    }

    public final void updateTopViewAlpha(float f) {
        this.addBtn.setTransitionAlpha(f);
        this.saveBtnContainer.setTransitionAlpha(f);
        this.qsSettingBtn.setTransitionAlpha(f);
    }
}
