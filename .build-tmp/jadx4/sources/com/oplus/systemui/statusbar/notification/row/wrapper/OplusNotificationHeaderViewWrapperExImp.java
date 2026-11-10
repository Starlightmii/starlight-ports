package com.oplus.systemui.statusbar.notification.row.wrapper;

import _COROUTINE.CoroutineDebuggingKt$;
import android.R;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pools;
import android.view.NotificationHeaderView;
import android.view.NotificationTopLineView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OplusNotificationDateTimeView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.compose.foundation.text.selection.MultiSelectionLayout$;
import com.android.internal.widget.CachingIconView;
import com.android.internal.widget.MessagingLinearLayout;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationConversationTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationMessagingTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.oplus.systemui.notification.NotificationIconBadgePackages;
import com.oplus.systemui.notification.row.oplusgroup.GroupIconTransformState;
import com.oplus.systemui.notification.row.oplusgroup.OplusGroupNotificationRowIconView;
import com.oplus.systemui.notification.row.wrapper.LayerDrawableWithAppIconKt;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.notification.utlils.OplusNotificationUtil;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationHeaderViewWrapperExImp extends OplusNotificationViewWrapperExImpl implements OplusNotificationHeaderViewWrapperEx {
    public static final Companion Companion = new Companion();
    private static final String MINIMIZED_SECTION_NAME = "Minimized";
    private static final String TAG = "OplusNotificationHeaderViewWrapperExImp";
    private View boundAltExpandTarget;
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null));
    private OplusNotificationDateTimeView dateView;
    public int expandButtonContainerMarginTop;
    public int expandButtonMarginLeft;
    public int expandButtonPaddingTop;
    private TextView headerTextDivider;
    private TextView headerTextSecondary;
    private TextView headerTextSecondaryDivider;
    private MessagingLinearLayout imageMessageContainer;
    private Icon lastIcon;
    private ImageView mLabelIcon;
    private ImageView mPhisihingAlertImage;
    private ImageView mRightIcon;
    private Drawable notificationTopIcon;
    public int rightIconStartMargin;
    private Integer rightIconTopMargin;
    public int rightIconWidth;
    private final View.OnClickListener rowClickListener;
    private TextView timeDivider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp$bindExpandButton$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ View $parentView;
        public /* synthetic */ View $view;
        public /* synthetic */ OplusNotificationHeaderViewWrapperExImp this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Rect rect = new Rect();
            this.$view.getHitRect(rect);
            int i = rect.top;
            Resources resources = this.this$0.getContext().getResources();
            rect.top = i - (resources != null ? resources.getDimensionPixelOffset(2131170658) : 0);
            int i2 = rect.right;
            Resources resources2 = this.this$0.getContext().getResources();
            rect.right = i2 + (resources2 != null ? resources2.getDimensionPixelOffset(2131170647) : 0);
            this.$parentView.setTouchDelegate(new TouchDelegate(rect, this.$view));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp$getBadge$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02931 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C02931(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OplusNotificationHeaderViewWrapperExImp.this.getBadge(this);
        }
    }

    public OplusNotificationHeaderViewWrapperExImp() {
        OplusNotificationHeaderViewWrapperExImp$rowClickListener$1 oplusNotificationHeaderViewWrapperExImp$rowClickListener$1 = new OplusNotificationHeaderViewWrapperExImp$rowClickListener$1();
        oplusNotificationHeaderViewWrapperExImp$rowClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.rowClickListener = oplusNotificationHeaderViewWrapperExImp$rowClickListener$1;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$getIconWithBadge(OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp, Drawable drawable, ContinuationImpl continuationImpl) {
        OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1 oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1;
        Ref.ObjectRef objectRef;
        Drawable drawable2;
        Drawable drawable3;
        Context context;
        long j;
        oplusNotificationHeaderViewWrapperExImp.getClass();
        if (continuationImpl instanceof OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1) {
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1 = (OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1) continuationImpl;
            int i = oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.label = i - Integer.MIN_VALUE;
            } else {
                oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1 = new OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1(oplusNotificationHeaderViewWrapperExImp, continuationImpl);
            }
        } else {
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1 = new OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1(oplusNotificationHeaderViewWrapperExImp, continuationImpl);
        }
        Object badge = oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(badge);
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = (NotificationHeaderViewWrapper) oplusNotificationHeaderViewWrapperExImp.m459getBase();
            if (notificationHeaderViewWrapper == null) {
                return drawable;
            }
            objectRef = new Ref.ObjectRef();
            long jCurrentTimeMillis = System.currentTimeMillis();
            Context context2 = oplusNotificationHeaderViewWrapperExImp.getContext();
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$0 = drawable;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$1 = SpillingKt.nullOutSpilledVariable(notificationHeaderViewWrapper);
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$2 = objectRef;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$3 = drawable;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$4 = context2;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.I$0 = 0;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.I$1 = 0;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.J$0 = jCurrentTimeMillis;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.I$2 = 0;
            oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.label = 1;
            badge = oplusNotificationHeaderViewWrapperExImp.getBadge(oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1);
            if (badge == coroutine_suspended) {
                return coroutine_suspended;
            }
            drawable2 = drawable;
            drawable3 = drawable2;
            context = context2;
            j = jCurrentTimeMillis;
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j = oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.J$0;
            context = (Context) oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$4;
            drawable3 = (Drawable) oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$3;
            objectRef = (Ref.ObjectRef) oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$2;
            drawable2 = (Drawable) oplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1.L$0;
            ResultKt.throwOnFailure(badge);
        }
        Drawable drawableCreateBackgroundWithAppIcon = LayerDrawableWithAppIconKt.createBackgroundWithAppIcon(context, drawable3, (Drawable) badge, TAG, Boxing.boxInt(oplusNotificationHeaderViewWrapperExImp.resolveHeaderIconContentSizePx()));
        if (drawableCreateBackgroundWithAppIcon != null) {
            objectRef.element = drawableCreateBackgroundWithAppIcon;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, MultiSelectionLayout$.ExternalSyntheticOutline0.m(System.currentTimeMillis() - j, "getIconWithLabel cost "), null, 8);
        Drawable drawable4 = (Drawable) objectRef.element;
        return drawable4 == null ? drawable2 : drawable4;
    }

    public static final void access$updateRightIconWithAppIconIfNeeded(OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp, Drawable drawable) {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        StatusBarNotification sbn;
        String packageName;
        ImageView imageView;
        Drawable drawableMutate;
        Drawable drawableNewDrawable;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = oplusNotificationHeaderViewWrapperExImp.m459getBase();
        if (notificationHeaderViewWrapperM459getBase != null) {
            ImageView imageView2 = oplusNotificationHeaderViewWrapperExImp.mRightIcon;
            if ((imageView2 != null ? imageView2.getDrawable() : null) == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (packageName = sbn.getPackageName()) == null || !FlavorTwoFeatureOption.INSTANCE.shouldUseOpaquePlateIcon(packageName) || (imageView = oplusNotificationHeaderViewWrapperExImp.mRightIcon) == null) {
                return;
            }
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState == null || (drawableNewDrawable = constantState.newDrawable()) == null || (drawableMutate = drawableNewDrawable.mutate()) == null) {
                drawableMutate = drawable.mutate();
            }
            imageView.setImageDrawable(drawableMutate);
        }
    }

    public static View findLastMessageTextViewById(ViewGroup viewGroup) {
        Object obj;
        View view = null;
        if (viewGroup == null) {
            return null;
        }
        try {
            for (int childCount = viewGroup.getChildCount() - 1; -1 < childCount; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt.getId() != 16909341 && (!(childAt instanceof ViewGroup) || (childAt = findLastMessageTextViewById((ViewGroup) childAt)) == null)) {
                }
                view = childAt;
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "find view error");
        }
        return view;
    }

    public static void setViewMarginEnd(int i, View view) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null || marginLayoutParams.getMarginEnd() == i) {
            return;
        }
        marginLayoutParams.setMarginEnd(i);
        view.setLayoutParams(marginLayoutParams);
    }

    public final void applyExpandedCanvasIconWithBadge(Drawable drawable, int i, int i2, String str) {
        CachingIconView cachingIconView;
        if (str != null) {
            ((NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class)).putHeaderIconBadgeDrawable(str, drawable);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase == null || (cachingIconView = notificationHeaderViewWrapperM459getBase.mIcon) == null) {
            return;
        }
        int i3 = i + i2;
        cachingIconView.setClipToOutline(false);
        cachingIconView.setCropToPadding(false);
        cachingIconView.setBackground((Drawable) null);
        cachingIconView.setImageDrawable(drawable);
        drawable.setBounds(0, 0, i3, i3);
    }

    public void bindAltExpandTarget(View view) {
        View view2 = this.boundAltExpandTarget;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        this.boundAltExpandTarget = view;
        view.setOnClickListener(this.rowClickListener);
    }

    public void bindExpandButton(View view, boolean z, View.OnClickListener onClickListener) {
        if (!z) {
            onClickListener = null;
        }
        view.setOnClickListener(onClickListener);
        view.setClickable(z);
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if ((notificationHeaderViewWrapperM459getBase != null ? notificationHeaderViewWrapperM459getBase.mNotificationHeader : null) == null || !z) {
            return;
        }
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$view = view;
            anonymousClass1.this$0 = this;
            anonymousClass1.$parentView = view2;
            VarHandle.storeStoreFence();
            view2.post(anonymousClass1);
        }
    }

    public final void cleanOldResource() {
        View view = this.boundAltExpandTarget;
        if (view != null) {
            view.setOnClickListener(null);
        }
        this.boundAltExpandTarget = null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getBadge(ContinuationImpl continuationImpl) {
        C02931 c02931;
        Ref.ObjectRef objectRefM;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        if (continuationImpl instanceof C02931) {
            c02931 = (C02931) continuationImpl;
            int i = c02931.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02931.label = i - Integer.MIN_VALUE;
            } else {
                c02931 = new C02931(continuationImpl);
            }
        } else {
            c02931 = new C02931(continuationImpl);
        }
        Object obj = c02931.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02931.label;
        if (i2 == 0) {
            objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
            if (notificationHeaderViewWrapperM459getBase != null) {
                ExpandableNotificationRow expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow;
                Icon smallIcon = expandableNotificationRow.getEntry().getSbn().getNotification().getSmallIcon();
                String packageName = expandableNotificationRow.getEntry().getSbn().getPackageName();
                if (smallIcon != null) {
                    objectRefM.element = smallIcon.loadDrawable(getContext());
                } else {
                    NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
                    packageName.getClass();
                    c02931.L$0 = objectRefM;
                    c02931.L$1 = notificationHeaderViewWrapperM459getBase;
                    c02931.L$2 = SpillingKt.nullOutSpilledVariable(notificationHeaderViewWrapperM459getBase);
                    c02931.L$3 = SpillingKt.nullOutSpilledVariable(smallIcon);
                    c02931.L$4 = SpillingKt.nullOutSpilledVariable(packageName);
                    c02931.L$5 = SpillingKt.nullOutSpilledVariable(notificationHeaderViewWrapperM459getBase);
                    c02931.L$6 = objectRefM;
                    c02931.I$0 = 0;
                    c02931.I$1 = 0;
                    c02931.label = 1;
                    Object packageIcon = notificationResolverEx.getPackageIcon(packageName, c02931);
                    if (packageIcon == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRefM;
                    obj = packageIcon;
                    objectRef2 = objectRef;
                }
            }
            return objectRefM.element;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        objectRef2 = (Ref.ObjectRef) c02931.L$6;
        objectRef = (Ref.ObjectRef) c02931.L$0;
        ResultKt.throwOnFailure(obj);
        objectRef2.element = obj;
        objectRefM = objectRef;
        return objectRefM.element;
    }

    public TransformState getCurrentState(int i, ViewTransformationHelper viewTransformationHelper) {
        View view = (View) viewTransformationHelper.mTransformedViews.get(Integer.valueOf(i));
        if (view == null || view.getVisibility() == 8 || !(view instanceof OplusGroupNotificationRowIconView)) {
            return null;
        }
        GroupIconTransformState groupIconTransformStateObtain = GroupIconTransformState.Companion.obtain();
        groupIconTransformStateObtain.initFrom(view, viewTransformationHelper);
        if (viewTransformationHelper.mKeysTransformingToSimilar.contains(Integer.valueOf(i))) {
            groupIconTransformStateObtain.setIsSameAsAnyView(true);
        }
        return groupIconTransformStateObtain;
    }

    public final OplusNotificationDateTimeView getDateView() {
        return this.dateView;
    }

    public final int getExpandButtonMarginLeft() {
        return this.expandButtonMarginLeft;
    }

    public final TextView getHeaderTextDivider() {
        return this.headerTextDivider;
    }

    public final TextView getHeaderTextSecondary() {
        return this.headerTextSecondary;
    }

    public final TextView getHeaderTextSecondaryDivider() {
        return this.headerTextSecondaryDivider;
    }

    public final MessagingLinearLayout getImageMessageContainer() {
        return this.imageMessageContainer;
    }

    public final Icon getLastIcon() {
        return this.lastIcon;
    }

    public final ImageView getMLabelIcon() {
        return this.mLabelIcon;
    }

    public final ImageView getMRightIcon() {
        return this.mRightIcon;
    }

    public final int getMeasuredWidthOrMeasure(View view, Boolean bool) {
        if (view.getMeasuredWidth() == 0 || Intrinsics.areEqual(bool, Boolean.TRUE)) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        return view.getMeasuredWidth();
    }

    public final int getRightIconStartMargin() {
        return this.rightIconStartMargin;
    }

    public final int getRightIconWidth() {
        return this.rightIconWidth;
    }

    public final TextView getTimeDivider() {
        return this.timeDivider;
    }

    public final void hideRightIconForBadge() {
        View view;
        View viewFindViewById;
        ImageView imageView = this.mRightIcon;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase == null || (view = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mView) == null || (viewFindViewById = view.findViewById(R.id.persistAcrossReboots)) == null) {
            return;
        }
        setViewMarginEnd(0, viewFindViewById.findViewById(R.id.title));
        setViewMarginEnd(0, viewFindViewById.findViewById(R.id.transition_overlay_view_tag));
        setViewMarginEnd(0, viewFindViewById.findViewById(R.id.chooser_edit_button));
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2 = m459getBase();
        if ((notificationHeaderViewWrapperM459getBase2 != null ? notificationHeaderViewWrapperM459getBase2.mNotificationHeader : null) != null) {
            setViewMarginEnd(getContext().getResources().getDimensionPixelSize(2131171061), viewFindViewById);
        } else {
            setViewMarginEnd(0, viewFindViewById);
        }
    }

    public void initBind(ExpandableNotificationRow expandableNotificationRow) {
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase != null) {
            notificationHeaderViewWrapperM459getBase.resolveHeaderViews();
            updateRecentlyAudiblyAlerted(expandableNotificationRow);
        }
    }

    public boolean proxyOnContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        NotificationChildrenContainerExt ext;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase != null) {
            NotificationHeaderViewWrapper.1 r2 = notificationHeaderViewWrapperM459getBase.mTransformationHelper;
            notificationHeaderViewWrapperM459getBase.mIsLowPriority = expandableNotificationRow.getEntry().mRanking.isAmbient();
            notificationHeaderViewWrapperM459getBase.mTransformLowPriorityTitle = (expandableNotificationRow.isChildInGroup() || expandableNotificationRow.mIsSummaryWithChildren) ? false : true;
            ArraySet allTransformingViews = r2.getAllTransformingViews();
            notificationHeaderViewWrapperM459getBase.updateTransformedTypes();
            notificationHeaderViewWrapperM459getBase.addRemainingTransformTypes$1();
            notificationHeaderViewWrapperM459getBase.updateCropToPaddingForImageViews();
            Notification notification = expandableNotificationRow.getEntry().getSbn().getNotification();
            CachingIconView cachingIconView = notificationHeaderViewWrapperM459getBase.mIcon;
            if (cachingIconView != null) {
                Pools.SimplePool simplePool = ImageTransformState.sInstancePool;
                cachingIconView.setTag(2131363420, notification.getSmallIcon());
            }
            updateIconContentDescription();
            ArraySet allTransformingViews2 = r2.getAllTransformingViews();
            int size = allTransformingViews.size();
            for (int i = 0; i < size; i++) {
                View view = (View) allTransformingViews.valueAt(i);
                if (!allTransformingViews2.contains(view)) {
                    r2.resetTransformedView(view);
                }
            }
            NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
            if (childrenContainer != null && (ext = childrenContainer.getExt()) != null) {
                ext.updateChildrenContainerHeaderText();
            }
            updateRecentlyAudiblyAlerted(expandableNotificationRow);
        }
        return true;
    }

    public final void proxyUpdateTransformedTypes() {
        TextView textView;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase != null) {
            NotificationHeaderViewWrapper.1 r1 = notificationHeaderViewWrapperM459getBase.mTransformationHelper;
            ((ViewTransformationHelper) r1).mTransformedViews.clear();
            ((ViewTransformationHelper) r1).mKeysTransformingToSimilar.clear();
            CachingIconView cachingIconView = notificationHeaderViewWrapperM459getBase.mIcon;
            if (cachingIconView != null) {
                r1.addTransformedView(0, cachingIconView);
            }
            NotificationExpandButton notificationExpandButton = notificationHeaderViewWrapperM459getBase.mExpandButton;
            if (notificationExpandButton != null) {
                r1.addTransformedView(6, notificationExpandButton);
            }
            if (notificationHeaderViewWrapperM459getBase.mIsLowPriority && (textView = notificationHeaderViewWrapperM459getBase.mHeaderText) != null) {
                r1.addTransformedView(1, textView);
            }
            ImageView imageView = this.mLabelIcon;
            if (imageView != null) {
                r1.addTransformedView(7, imageView);
            }
            notificationHeaderViewWrapperM459getBase.addViewsTransformingToSimilar(new View[]{notificationHeaderViewWrapperM459getBase.mWorkProfileImage});
        }
    }

    public boolean resetTransformedView(View view, TransformState.TransformInfo transformInfo) {
        if (!(view instanceof OplusGroupNotificationRowIconView)) {
            return false;
        }
        GroupIconTransformState groupIconTransformStateObtain = GroupIconTransformState.Companion.obtain();
        groupIconTransformStateObtain.initFrom(view, transformInfo);
        groupIconTransformStateObtain.setVisible(true, true);
        groupIconTransformStateObtain.recycle();
        return true;
    }

    public final void resolveChildInGroup(boolean z) {
        OplusNotificationTemplateViewWrapperEx oplusNotificationTemplateViewWrapperEx;
        View view;
        View view2;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase;
        CachingIconView icon;
        View view3;
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        NotificationTemplateViewWrapper notificationTemplateViewWrapper = (NotificationHeaderViewWrapper) m459getBase();
        if (notificationTemplateViewWrapper instanceof NotificationMessagingTemplateViewWrapper) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationHeaderViewWrapperExImp-->resolve margin block");
                return;
            }
            return;
        }
        if (!(notificationTemplateViewWrapper instanceof NotificationTemplateViewWrapper) || (oplusNotificationTemplateViewWrapperEx = notificationTemplateViewWrapper.mTemplateViewWrapperEx) == null) {
            return;
        }
        OplusNotificationTemplateViewWrapperExImpl oplusNotificationTemplateViewWrapperExImpl = (OplusNotificationTemplateViewWrapperExImpl) oplusNotificationTemplateViewWrapperEx;
        oplusNotificationTemplateViewWrapperExImpl.isChildInGroup = z;
        if (!z) {
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = oplusNotificationTemplateViewWrapperExImpl.m459getBase();
            if (((notificationTemplateViewWrapperM459getBase2 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null) ? null : entry.getDismissState()) == NotificationEntry.DismissState.DISMISSED) {
                return;
            }
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = oplusNotificationTemplateViewWrapperExImpl.m459getBase();
        ViewParent parent = (notificationTemplateViewWrapperM459getBase3 == null || (view3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mView) == null) ? null : view3.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null || viewGroup.getId() != 2131363090) {
            if (!(oplusNotificationTemplateViewWrapperExImpl.m459getBase() instanceof NotificationConversationTemplateViewWrapper) && (notificationTemplateViewWrapperM459getBase = oplusNotificationTemplateViewWrapperExImpl.m459getBase()) != null && (icon = notificationTemplateViewWrapperM459getBase.getIcon()) != null) {
                icon.setVisibility(!z ? 0 : 8);
            }
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = oplusNotificationTemplateViewWrapperExImpl.m459getBase();
            ViewGroup.LayoutParams layoutParams = (notificationTemplateViewWrapperM459getBase4 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase4).mView) == null) ? null : view2.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            oplusNotificationTemplateViewWrapperExImpl.resolveActionsMargin(z);
            if (z) {
                if (marginLayoutParams != null) {
                    marginLayoutParams.setMarginStart(oplusNotificationTemplateViewWrapperExImpl.getIconMargin());
                }
            } else if (marginLayoutParams != null) {
                marginLayoutParams.setMarginStart(0);
            }
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase5 = oplusNotificationTemplateViewWrapperExImpl.m459getBase();
            if (notificationTemplateViewWrapperM459getBase5 == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase5).mView) == null) {
                return;
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public final int resolveHeaderIconContentSizePx() {
        return getContext().getResources().getDimensionPixelSize(2131170676);
    }

    public void resolveHeaderViewsPlus() {
        View view;
        ExpandableNotificationRow expandableNotificationRow;
        ImageView imageView;
        Drawable drawable;
        ExpandableNotificationRow expandableNotificationRow2;
        NotificationEntry entry;
        StatusBarNotification sbn;
        ExpandableNotificationRow expandableNotificationRow3;
        NotificationEntry entry2;
        StatusBarNotification sbn2;
        ExpandableNotificationRow expandableNotificationRow4;
        NotificationHeaderView notificationHeader;
        TextView textView;
        TextView textView2;
        View view2;
        ExpandableNotificationRow expandableNotificationRow5;
        TextView textView3;
        ExpandableNotificationRow expandableNotificationRow6;
        ExpandableNotificationRow expandableNotificationRow7;
        NotificationEntry entry3;
        StatusBarNotification sbn3;
        Notification notification;
        ExpandableNotificationRow expandableNotificationRow8;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase;
        ExpandableNotificationRow expandableNotificationRow9;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2;
        ExpandableNotificationRow expandableNotificationRow10;
        NotificationEntry entry4;
        OplusNotificationEntryEx ex;
        OplusNotificationEntryEx.McsInfo mcsInfo;
        ExpandableNotificationRow expandableNotificationRow11;
        NotificationEntry entry5;
        OplusNotificationDateTimeView oplusNotificationDateTimeView;
        View view3;
        Drawable oplusMcsIconRes;
        NotificationEntry entry6;
        OplusNotificationEntryEx ex2;
        NotificationEntry entry7;
        StatusBarNotification sbn4;
        Notification notification2;
        OplusNotificationDateTimeView oplusNotificationDateTimeView2;
        NotificationEntry entry8;
        StatusBarNotification sbn5;
        Notification notification3;
        NotificationEntry entry9;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase3 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase3 != null) {
            this.mPhisihingAlertImage = (ImageView) ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase3).mView.findViewById(R.id.reply_action_container);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase4 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase4 != null) {
            this.timeDivider = (TextView) ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.unchecked);
            this.dateView = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.typeWindowContentChanged);
            this.headerTextDivider = (TextView) ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.input_block);
            this.headerTextSecondary = (TextView) ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.input_header);
            this.headerTextSecondaryDivider = (TextView) ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.input_hour);
            this.rightIconWidth = getContext().getResources().getDimensionPixelSize(R.dimen.preference_fragment_padding_vertical_material);
            this.rightIconStartMargin = getContext().getResources().getDimensionPixelSize(2131171068);
            this.imageMessageContainer = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mView.findViewById(R.id.email);
            this.expandButtonMarginLeft = getContext().getResources().getDimensionPixelOffset(2131170635);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase5 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase5 != null && notificationHeaderViewWrapperM459getBase5.mAppNameText != null) {
            String multiAppName = OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mView.getContext().getUserId())) ? OplusMultiAppUtils.getMultiAppName(Integer.valueOf(((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mView.getContext().getUserId()), ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mView.getContext().getPackageName()) : null;
            Context context = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mView.getContext();
            PackageManager packageManager = context != null ? context.getPackageManager() : null;
            ExpandableNotificationRow expandableNotificationRow12 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mRow;
            String mcsCustomAppName = MCSUtil.getMcsCustomAppName(packageManager, (expandableNotificationRow12 == null || (entry9 = expandableNotificationRow12.getEntry()) == null) ? null : entry9.getSbn());
            if (!TextUtils.isEmpty(mcsCustomAppName)) {
                multiAppName = mcsCustomAppName;
            }
            if (!TextUtils.isEmpty(multiAppName)) {
                notificationHeaderViewWrapperM459getBase5.mAppNameText.setText(multiAppName);
            }
        }
        updateIconContentDescription();
        updateHeaderTopLineMargin();
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase6 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase6 != null) {
            ExpandableNotificationRow expandableNotificationRow13 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase6).mRow;
            int couiColorPrimaryNeutral = NotificationColorUtil.getCouiColorPrimaryNeutral(getContext());
            int couiColorSecondaryNeutral = NotificationColorUtil.getCouiColorSecondaryNeutral(getContext());
            boolean zIsColorized = (expandableNotificationRow13 == null || (entry8 = expandableNotificationRow13.getEntry()) == null || (sbn5 = entry8.getSbn()) == null || (notification3 = sbn5.getNotification()) == null) ? false : notification3.isColorized();
            TextView textView4 = notificationHeaderViewWrapperM459getBase6.mAppNameText;
            if (textView4 != null) {
                if (zIsColorized) {
                    couiColorPrimaryNeutral = textView4.getTextColors().getDefaultColor();
                }
                notificationHeaderViewWrapperM459getBase6.mAppNameText.setTextColor(couiColorPrimaryNeutral);
            }
            TextView textView5 = notificationHeaderViewWrapperM459getBase6.mHeaderText;
            if (textView5 != null) {
                textView5.setTextColor(couiColorPrimaryNeutral);
            }
            TextView textView6 = this.headerTextDivider;
            if (textView6 != null) {
                textView6.setTextColor(couiColorPrimaryNeutral);
            }
            TextView textView7 = this.headerTextSecondary;
            if (textView7 != null) {
                textView7.setTextColor(couiColorPrimaryNeutral);
            }
            TextView textView8 = this.headerTextSecondaryDivider;
            if (textView8 != null) {
                textView8.setTextColor(couiColorPrimaryNeutral);
            }
            TextView textView9 = this.timeDivider;
            if (textView9 != null) {
                textView9.setTextColor(couiColorPrimaryNeutral);
            }
            if (expandableNotificationRow13 != null && (entry7 = expandableNotificationRow13.getEntry()) != null && (sbn4 = entry7.getSbn()) != null && (notification2 = sbn4.getNotification()) != null && !notification2.isColorized() && (oplusNotificationDateTimeView2 = this.dateView) != null) {
                oplusNotificationDateTimeView2.setTextColor(couiColorSecondaryNeutral);
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase7 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase7 != null && (ex2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase7).mRow.getEntry().getEx()) != null && ((OplusNotificationEntryExImpl) ex2).useAppIconForSmallIcon()) {
            BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new OplusNotificationHeaderViewWrapperExImp$updateIconWithAppIconIfNeeded$1$1(notificationHeaderViewWrapperM459getBase7, this, null), 2);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase8 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase8 != null) {
            ExpandableNotificationRow expandableNotificationRow14 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase8).mRow;
            OplusNotificationEntryEx ex3 = (expandableNotificationRow14 == null || (entry6 = expandableNotificationRow14.getEntry()) == null) ? null : entry6.getEx();
            if ((ex3 instanceof OplusNotificationEntryExImpl) && (oplusMcsIconRes = ((OplusNotificationEntryExImpl) ex3).getOplusMcsIconRes()) != null) {
                BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new OplusNotificationHeaderViewWrapperExImp$updateIconForMcs$1$1$1(this, oplusMcsIconRes, null), 2);
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase9 = m459getBase();
        ImageView imageView2 = (notificationHeaderViewWrapperM459getBase9 == null || (view3 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase9).mView) == null) ? null : (ImageView) view3.findViewById(R.id.shortcut);
        this.mRightIcon = imageView2;
        ViewGroup.LayoutParams layoutParams = imageView2 != null ? imageView2.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        float f = Resources.getSystem().getConfiguration().fontScale;
        if (marginLayoutParams != null) {
            if (this.rightIconTopMargin == null) {
                this.rightIconTopMargin = Integer.valueOf(marginLayoutParams.topMargin);
            }
            Integer num = this.rightIconTopMargin;
            if (num != null) {
                marginLayoutParams.topMargin = (int) (num.intValue() * f);
                ImageView imageView3 = this.mRightIcon;
                if (imageView3 != null) {
                    imageView3.setLayoutParams(marginLayoutParams);
                }
            }
        }
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDeviceExpLessThanC()) {
            updateIconRoundness(updateIconColor());
        } else {
            updateIconRoundness(false);
            updateIconColor();
        }
        if (OplusNotificationUtil.isSpecialHeightLanguage() && (oplusNotificationDateTimeView = this.dateView) != null) {
            oplusNotificationDateTimeView.setIncludeFontPadding(true);
            oplusNotificationDateTimeView.setElegantTextHeight(false);
        }
        CommercialBlackListManager companion = CommercialBlackListManager.Companion.getInstance(getContext());
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase10 = m459getBase();
        int i = (companion.isInBlackList((notificationHeaderViewWrapperM459getBase10 == null || (expandableNotificationRow11 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase10).mRow) == null || (entry5 = expandableNotificationRow11.getEntry()) == null) ? null : entry5.getSbn()) || !((notificationHeaderViewWrapperM459getBase2 = m459getBase()) == null || (expandableNotificationRow10 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase2).mRow) == null || (entry4 = expandableNotificationRow10.getEntry()) == null || (ex = entry4.getEx()) == null || (mcsInfo = ex.getMcsInfo()) == null || !mcsInfo.getHideTime())) ? 8 : 0;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase11 = m459getBase();
        boolean z = (notificationHeaderViewWrapperM459getBase11 == null || (expandableNotificationRow8 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase11).mRow) == null || !expandableNotificationRow8.mIsSummaryWithChildren || (notificationHeaderViewWrapperM459getBase = m459getBase()) == null || (expandableNotificationRow9 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow) == null || !expandableNotificationRow9.isGroupExpanded()) ? false : true;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase12 = m459getBase();
        if ((notificationHeaderViewWrapperM459getBase12 == null || (expandableNotificationRow7 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase12).mRow) == null || (entry3 = expandableNotificationRow7.getEntry()) == null || (sbn3 = entry3.getSbn()) == null || (notification = sbn3.getNotification()) == null || !notification.isStyle(Notification.CallStyle.class)) && !z) {
            OplusNotificationDateTimeView oplusNotificationDateTimeView3 = this.dateView;
            if (oplusNotificationDateTimeView3 != null) {
                oplusNotificationDateTimeView3.setVisibility(i);
            }
            TextView textView10 = this.timeDivider;
            if (textView10 != null) {
                textView10.setVisibility(i);
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase13 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase13 != null && (expandableNotificationRow4 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase13).mRow) != null && expandableNotificationRow4.isChildInGroup()) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase14 = m459getBase();
            ExpandableNotificationRow notificationParent = (notificationHeaderViewWrapperM459getBase14 == null || (expandableNotificationRow6 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase14).mRow) == null) ? null : expandableNotificationRow6.getNotificationParent();
            if ((notificationParent != null ? notificationParent.getNotificationViewWrapper() : null) != null && (notificationHeader = notificationParent.getNotificationViewWrapper().getNotificationHeader()) != null && (textView = (TextView) notificationHeader.findViewById(R.id.blocksDescendants)) != null) {
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase15 = m459getBase();
                if ((notificationHeaderViewWrapperM459getBase15 != null ? notificationHeaderViewWrapperM459getBase15.mAppNameText : null) != null) {
                    CharSequence text = textView.getText();
                    NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase16 = m459getBase();
                    if (Intrinsics.areEqual(text, (notificationHeaderViewWrapperM459getBase16 == null || (textView3 = notificationHeaderViewWrapperM459getBase16.mAppNameText) == null) ? null : textView3.getText())) {
                        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase17 = m459getBase();
                        NotificationContentView publicLayout = (notificationHeaderViewWrapperM459getBase17 == null || (expandableNotificationRow5 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase17).mRow) == null) ? null : expandableNotificationRow5.getPublicLayout();
                        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase18 = m459getBase();
                        if (!Intrinsics.areEqual(publicLayout, (notificationHeaderViewWrapperM459getBase18 == null || (view2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase18).mView) == null) ? null : view2.getParent()) && (textView2 = this.timeDivider) != null) {
                            textView2.setVisibility(8);
                        }
                    }
                }
            }
        }
        Context context2 = getContext();
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase19 = m459getBase();
        String packageName = (notificationHeaderViewWrapperM459getBase19 == null || (expandableNotificationRow3 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase19).mRow) == null || (entry2 = expandableNotificationRow3.getEntry()) == null || (sbn2 = entry2.getSbn()) == null) ? null : sbn2.getPackageName();
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase20 = m459getBase();
        Integer numValueOf = (notificationHeaderViewWrapperM459getBase20 == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase20).mRow) == null || (entry = expandableNotificationRow2.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : Integer.valueOf(sbn.getUid());
        if (packageName != null && numValueOf != null) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isExpRegion()) {
                this.notificationTopIcon = AppCompatResources.getDrawable(context2, 2131235744);
                NotificationTopListManage notificationTopListManageNewInstance = NotificationTopListManage.Companion.newInstance(context2);
                if (notificationTopListManageNewInstance != null) {
                    if (((NotificationTopListManageImpl) notificationTopListManageNewInstance).isNotificationTop(packageName, numValueOf)) {
                        Resources resources = getContext().getResources();
                        Integer numValueOf2 = resources != null ? Integer.valueOf(resources.getDimensionPixelOffset(2131170802)) : null;
                        Resources resources2 = getContext().getResources();
                        Integer numValueOf3 = resources2 != null ? Integer.valueOf(resources2.getDimensionPixelOffset(2131170800)) : null;
                        if (numValueOf3 != null && numValueOf2 != null && (drawable = this.notificationTopIcon) != null) {
                            drawable.setBounds(0, 0, numValueOf2.intValue(), numValueOf3.intValue());
                        }
                        boolean zIsRtl = DeviceUtil.isRtl();
                        OplusNotificationDateTimeView oplusNotificationDateTimeView4 = this.dateView;
                        if (zIsRtl) {
                            if (oplusNotificationDateTimeView4 != null) {
                                oplusNotificationDateTimeView4.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.notificationTopIcon, (Drawable) null);
                            }
                        } else if (oplusNotificationDateTimeView4 != null) {
                            oplusNotificationDateTimeView4.setCompoundDrawablesWithIntrinsicBounds(this.notificationTopIcon, (Drawable) null, (Drawable) null, (Drawable) null);
                        }
                        Resources resources3 = getContext().getResources();
                        if (resources3 != null) {
                            int dimensionPixelOffset = resources3.getDimensionPixelOffset(2131170801);
                            OplusNotificationDateTimeView oplusNotificationDateTimeView5 = this.dateView;
                            if (oplusNotificationDateTimeView5 != null) {
                                oplusNotificationDateTimeView5.setCompoundDrawablePadding(dimensionPixelOffset);
                            }
                        }
                    } else {
                        OplusNotificationDateTimeView oplusNotificationDateTimeView6 = this.dateView;
                        if (oplusNotificationDateTimeView6 != null) {
                            oplusNotificationDateTimeView6.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                        }
                    }
                }
            }
        }
        if (m459getBase() != null && FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
            int i2 = FlavorTwoFeatureOption.isFlavorTwoDeviceExpLessThanC() ? 2131236002 : 2131236000;
            ImageView imageView4 = this.mRightIcon;
            if (imageView4 != null) {
                imageView4.setBackground(getContext().getDrawable(i2));
            }
            ImageView imageView5 = this.mRightIcon;
            if (imageView5 != null) {
                imageView5.setClipToOutline(true);
            }
            ImageView imageView6 = this.mRightIcon;
            if (imageView6 != null) {
                imageView6.invalidateOutline();
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase21 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase21 != null) {
            ExpandableNotificationRow expandableNotificationRow15 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase21).mRow;
            if (this.mLabelIcon == null) {
                Context context3 = getContext();
                this.mLabelIcon = new ImageView(context3);
                int dimensionPixelSize = context3.getResources().getDimensionPixelSize(2131170681);
                int dimensionPixelSize2 = context3.getResources().getDimensionPixelSize(2131170679);
                int dimensionPixelSize3 = context3.getResources().getDimensionPixelSize(2131170680);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
                if (expandableNotificationRow15 == null || expandableNotificationRow15.getAttachedChildren() != null) {
                    layoutParams2.setMarginEnd(dimensionPixelSize3);
                } else {
                    layoutParams2.setMarginEnd(dimensionPixelSize2);
                    OplusNotificationEntryEx ex4 = expandableNotificationRow15.getEntry().getEx();
                    if ((ex4 instanceof OplusNotificationEntryExImpl) && (imageView = this.mLabelIcon) != null) {
                        imageView.setImageDrawable(((OplusNotificationEntryExImpl) ex4).getOplusLabel());
                    }
                }
                ImageView imageView7 = this.mLabelIcon;
                if (imageView7 != null) {
                    imageView7.setLayoutParams(layoutParams2);
                }
                ImageView imageView8 = this.mLabelIcon;
                if (imageView8 != null) {
                    imageView8.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ImageView imageView9 = this.mLabelIcon;
                if (imageView9 != null) {
                    imageView9.setVisibility(8);
                }
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase22 = m459getBase();
                NotificationContentView publicLayout2 = (notificationHeaderViewWrapperM459getBase22 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase22).mRow) == null) ? null : expandableNotificationRow.getPublicLayout();
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase23 = m459getBase();
                if (!Intrinsics.areEqual(publicLayout2, (notificationHeaderViewWrapperM459getBase23 == null || (view = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase23).mView) == null) ? null : view.getParent())) {
                    OplusNotificationDateTimeView oplusNotificationDateTimeView7 = this.dateView;
                    ViewParent parent = oplusNotificationDateTimeView7 != null ? oplusNotificationDateTimeView7.getParent() : null;
                    LinearLayout linearLayout = parent instanceof LinearLayout ? (LinearLayout) parent : null;
                    if (linearLayout != null) {
                        linearLayout.addView(this.mLabelIcon, 0);
                    }
                }
            } else {
                OplusNotificationEntryEx ex5 = expandableNotificationRow15.getEntry().getEx();
                boolean z2 = ex5 instanceof OplusNotificationEntryExImpl;
                ImageView imageView10 = this.mLabelIcon;
                if (z2) {
                    if (imageView10 != null) {
                        imageView10.setImageDrawable(((OplusNotificationEntryExImpl) ex5).getOplusLabel());
                    }
                    Drawable oplusLabel = ((OplusNotificationEntryExImpl) ex5).getOplusLabel();
                    ImageView imageView11 = this.mLabelIcon;
                    if (oplusLabel == null) {
                        if (imageView11 != null) {
                            imageView11.setVisibility(8);
                        }
                    } else if (imageView11 != null) {
                        imageView11.setVisibility(0);
                    }
                } else if (imageView10 != null) {
                    imageView10.setVisibility(8);
                }
            }
        }
        OplusNotificationDateTimeView oplusNotificationDateTimeView8 = this.dateView;
        if (oplusNotificationDateTimeView8 instanceof com.oplus.systemui.notification.row.widget.OplusNotificationDateTimeView) {
            OplusNotificationHeaderViewWrapperExImp$$ExternalSyntheticLambda0 oplusNotificationHeaderViewWrapperExImp$$ExternalSyntheticLambda0 = new OplusNotificationHeaderViewWrapperExImp$$ExternalSyntheticLambda0();
            oplusNotificationHeaderViewWrapperExImp$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            ((com.oplus.systemui.notification.row.widget.OplusNotificationDateTimeView) oplusNotificationDateTimeView8).setOnUpdateListener(oplusNotificationHeaderViewWrapperExImp$$ExternalSyntheticLambda0);
        }
    }

    public final void setDateView(OplusNotificationDateTimeView oplusNotificationDateTimeView) {
        this.dateView = oplusNotificationDateTimeView;
    }

    public final void setExpandButtonMarginLeft(int i) {
        this.expandButtonMarginLeft = i;
    }

    public final void setHeaderTextDivider(TextView textView) {
        this.headerTextDivider = textView;
    }

    public final void setHeaderTextSecondary(TextView textView) {
        this.headerTextSecondary = textView;
    }

    public final void setHeaderTextSecondaryDivider(TextView textView) {
        this.headerTextSecondaryDivider = textView;
    }

    public final void setImageMessageContainer(MessagingLinearLayout messagingLinearLayout) {
        this.imageMessageContainer = messagingLinearLayout;
    }

    public void setIsChildInGroup(boolean z) {
        resolveChildInGroup(z);
    }

    public final void setLastIcon(Icon icon) {
        this.lastIcon = icon;
    }

    public final void setMLabelIcon(ImageView imageView) {
        this.mLabelIcon = imageView;
    }

    public final void setMRightIcon(ImageView imageView) {
        this.mRightIcon = imageView;
    }

    public final void setRightIconStartMargin(int i) {
        this.rightIconStartMargin = i;
    }

    public final void setRightIconWidth(int i) {
        this.rightIconWidth = i;
    }

    public final void setTimeDivider(TextView textView) {
        this.timeDivider = textView;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0021  */
    public void setUpdateExpandability(boolean z) {
        boolean z2;
        NotificationExpandButton notificationExpandButton;
        NotificationEntry entry;
        OplusNotificationEntryEx ex;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase != null) {
            ExpandableNotificationRow expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow;
            if (expandableNotificationRow != null && (entry = expandableNotificationRow.getEntry()) != null && (ex = entry.getEx()) != null) {
                z2 = ((OplusNotificationEntryExImpl) ex).isOldWrapper;
            }
            if (z && (notificationExpandButton = notificationHeaderViewWrapperM459getBase.mExpandButton) != null) {
                notificationExpandButton.setAlpha(1.0f);
            }
        } else {
            z2 = false;
        }
        ImageView imageView = this.mLabelIcon;
        if (imageView != null) {
            imageView.setVisibility(((z && !z2) || imageView.getDrawable() == null) ? 8 : 0);
        }
    }

    public final void updateExpandButtonContainerParams(int i, int i2, int i3, View view, boolean z) {
        View expandButton;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase;
        View expandButton2;
        NotificationTopLineView notificationTopLineView;
        if (z) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2 = m459getBase();
            int top = ((notificationHeaderViewWrapperM459getBase2 == null || (notificationTopLineView = notificationHeaderViewWrapperM459getBase2.mNotificationTopLine) == null) ? 0 : notificationTopLineView.getTop()) - view.getTop();
            if (top <= 0 || (notificationHeaderViewWrapperM459getBase = m459getBase()) == null || (expandButton2 = notificationHeaderViewWrapperM459getBase.getExpandButton()) == null) {
                return;
            }
            expandButton2.setPaddingRelative(expandButton2.getPaddingStart(), top, expandButton2.getPaddingEnd(), expandButton2.getPaddingBottom());
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            if (layoutParams2.gravity != i) {
                layoutParams2.gravity = i;
                layoutParams2.topMargin = i2;
                view.setLayoutParams(layoutParams);
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase3 = m459getBase();
                if (notificationHeaderViewWrapperM459getBase3 == null || (expandButton = notificationHeaderViewWrapperM459getBase3.getExpandButton()) == null) {
                    return;
                }
                expandButton.setPaddingRelative(expandButton.getPaddingStart(), i3, expandButton.getPaddingEnd(), expandButton.getPaddingBottom());
            }
        }
    }

    public void updateHeaderTopLineMargin() {
        NotificationTopLineView notificationTopLineView;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase;
        View expandButton;
        OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2;
        View expandButton2;
        NotifSection section;
        View expandButton3;
        NotificationContentView privateLayout;
        View view;
        FrameLayout frameLayout;
        View view2;
        View view3;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase3;
        NotificationExpandButton notificationExpandButton;
        int i;
        View view4;
        NotificationTopLineView notificationTopLineView2;
        View view5;
        View view6;
        CharSequence text;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase4;
        ExpandableNotificationRow expandableNotificationRow;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        View view7;
        ExpandableNotificationRow expandableNotificationRow2;
        View view8;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase5 = m459getBase();
        ViewGroup.LayoutParams layoutParams = null;
        TextView textView = (notificationHeaderViewWrapperM459getBase5 == null || (view8 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase5).mView) == null) ? null : (TextView) view8.findViewById(R.id.title);
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase6 = m459getBase();
        NotificationContentView publicLayout = (notificationHeaderViewWrapperM459getBase6 == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase6).mRow) == null) ? null : expandableNotificationRow2.getPublicLayout();
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase7 = m459getBase();
        boolean zAreEqual = Intrinsics.areEqual(publicLayout, (notificationHeaderViewWrapperM459getBase7 == null || (view7 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase7).mView) == null) ? null : view7.getParent());
        if (zAreEqual && textView != null && (text = textView.getText()) != null && text.length() == 0 && (notificationHeaderViewWrapperM459getBase4 = m459getBase()) != null && (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mRow) != null && (oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx) != null) {
            ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).updateEncryptPublicContentView();
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase8 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase8 == null || (notificationTopLineView = notificationHeaderViewWrapperM459getBase8.mNotificationTopLine) == null || (notificationHeaderViewWrapperM459getBase = m459getBase()) == null || (expandButton = notificationHeaderViewWrapperM459getBase.getExpandButton()) == null) {
            return;
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase9 = m459getBase();
        int measuredHeight = 0;
        if ((notificationHeaderViewWrapperM459getBase9 != null ? notificationHeaderViewWrapperM459getBase9.mNotificationHeader : null) == null && (notificationHeaderViewWrapperM459getBase3 = m459getBase()) != null && (notificationExpandButton = notificationHeaderViewWrapperM459getBase3.mExpandButton) != null) {
            OplusNotificationDateTimeView oplusNotificationDateTimeView = this.dateView;
            int measuredWidthOrMeasure = getMeasuredWidthOrMeasure(notificationExpandButton, oplusNotificationDateTimeView != null ? Boolean.valueOf(oplusNotificationDateTimeView.isLayoutRequested()) : null);
            ViewGroup.LayoutParams layoutParams2 = notificationTopLineView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
            int marginEnd = marginLayoutParams != null ? marginLayoutParams.getMarginEnd() : 0;
            NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase10 = m459getBase();
            boolean zEquals = OplusNotificationTemplateViewWrapperExImpl.MEDIA_TAG.equals((notificationHeaderViewWrapperM459getBase10 == null || (view6 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase10).mView) == null) ? null : view6.getTag());
            NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase11 = m459getBase();
            if ("call".equals((notificationHeaderViewWrapperM459getBase11 == null || (view5 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase11).mView) == null) ? null : view5.getTag()) || zEquals || (i = measuredWidthOrMeasure - marginEnd) < 0) {
                i = 0;
            }
            boolean zIsRtl = DeviceUtil.isRtl();
            if (notificationTopLineView.getPaddingRight() != i || (zIsRtl && notificationTopLineView.getPaddingLeft() != i)) {
                if (zIsRtl) {
                    notificationTopLineView.setPadding(i, notificationTopLineView.getPaddingTop(), notificationTopLineView.getPaddingRight(), notificationTopLineView.getPaddingBottom());
                } else {
                    notificationTopLineView.setPadding(notificationTopLineView.getPaddingLeft(), notificationTopLineView.getPaddingTop(), i, notificationTopLineView.getPaddingBottom());
                }
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase12 = m459getBase();
                if (((notificationHeaderViewWrapperM459getBase12 == null || (notificationTopLineView2 = notificationHeaderViewWrapperM459getBase12.mNotificationTopLine) == null) ? null : (TextView) notificationTopLineView2.findViewById(R.id.title)) != null) {
                    return;
                }
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase13 = m459getBase();
                TextView textView2 = (notificationHeaderViewWrapperM459getBase13 == null || (view4 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase13).mView) == null) ? null : (TextView) view4.findViewById(R.id.title);
                if (textView2 != null) {
                    textView2.setPaddingRelative(textView2.getPaddingStart(), textView2.getPaddingTop(), i, textView2.getPaddingBottom());
                }
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase14 = m459getBase();
        View view9 = notificationHeaderViewWrapperM459getBase14 != null ? ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase14).mView : null;
        View viewFindLastMessageTextViewById = findLastMessageTextViewById(view9 instanceof ViewGroup ? (ViewGroup) view9 : null);
        boolean z = (viewFindLastMessageTextViewById instanceof ImageView) && ((ImageView) viewFindLastMessageTextViewById).getDrawable() != null;
        ImageView imageView = this.mRightIcon;
        boolean z2 = !(imageView == null || imageView.getDrawable() == null) || z;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase15 = m459getBase();
        View viewFindViewById = (notificationHeaderViewWrapperM459getBase15 == null || (view3 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase15).mView) == null) ? null : view3.findViewById(R.id.persistAcrossReboots);
        if (zAreEqual) {
            return;
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase16 = m459getBase();
        View viewFindViewById2 = (notificationHeaderViewWrapperM459getBase16 == null || (view2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase16).mView) == null) ? null : view2.findViewById(R.id.permission_list);
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase17 = m459getBase();
        View view10 = (notificationHeaderViewWrapperM459getBase17 == null || (view = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase17).mView) == null || (frameLayout = (FrameLayout) view.findViewById(R.id.flagRequestEnhancedWebAccessibility)) == null) ? expandButton : frameLayout;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase18 = m459getBase();
        boolean z3 = (notificationHeaderViewWrapperM459getBase18 != null ? notificationHeaderViewWrapperM459getBase18.mNotificationHeader : null) != null;
        if (viewFindViewById2 != null) {
            if (this.expandButtonContainerMarginTop == 0) {
                ViewGroup.LayoutParams layoutParams3 = view10.getLayoutParams();
                ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
                this.expandButtonContainerMarginTop = marginLayoutParams2 != null ? marginLayoutParams2.topMargin : 0;
            }
            if (this.expandButtonPaddingTop == 0) {
                this.expandButtonPaddingTop = expandButton.getPaddingTop();
            }
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase19 = m459getBase();
        ExpandableNotificationRow expandableNotificationRow3 = notificationHeaderViewWrapperM459getBase19 != null ? ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase19).mRow : null;
        View contractedChild = (expandableNotificationRow3 == null || (privateLayout = expandableNotificationRow3.getPrivateLayout()) == null) ? null : privateLayout.getContractedChild();
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase20 = m459getBase();
        boolean z4 = contractedChild == (notificationHeaderViewWrapperM459getBase20 != null ? ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase20).mView : null);
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase21 = m459getBase();
        boolean z5 = ((notificationHeaderViewWrapperM459getBase21 != null ? ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase21).mView : null) instanceof NotificationHeaderView) && (viewFindViewById == null || viewFindViewById.getVisibility() != 0);
        if (expandableNotificationRow3 != null && z4 && z5) {
            NotificationEntry entry = expandableNotificationRow3.getEntry();
            if ((entry != null && (section = entry.getSection()) != null && Intrinsics.areEqual(section.getSectioner().getName(), MINIMIZED_SECTION_NAME) && entry.mRanking.isAmbient()) ? Intrinsics.areEqual(entry.getParent(), GroupEntry.ROOT_ENTRY) : false) {
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase22 = m459getBase();
                if (notificationHeaderViewWrapperM459getBase22 == null || (expandButton3 = notificationHeaderViewWrapperM459getBase22.getExpandButton()) == null) {
                    return;
                }
                if (this.expandButtonPaddingTop == 0 && expandButton3.getPaddingTop() != 0) {
                    this.expandButtonPaddingTop = expandButton3.getPaddingTop();
                }
                if (expandButton3.getPaddingTop() != 0) {
                    expandButton3.setPaddingRelative(expandButton3.getPaddingStart(), 0, expandButton3.getPaddingEnd(), expandButton3.getPaddingBottom());
                    return;
                }
                return;
            }
        }
        if (viewFindViewById != null) {
            if (viewFindViewById.getMeasuredHeight() == 0) {
                int layoutDirection = viewFindViewById.getContext().getResources().getConfiguration().getLayoutDirection();
                if (layoutDirection == 1 && viewFindViewById.getLayoutDirection() != layoutDirection) {
                    viewFindViewById.setLayoutDirection(layoutDirection);
                }
                viewFindViewById.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            }
            measuredHeight = viewFindViewById.getMeasuredHeight();
        }
        if (z2 || measuredHeight != 0) {
            oplusNotificationHeaderViewWrapperExImp = this;
            oplusNotificationHeaderViewWrapperExImp.updateExpandButtonContainerParams(8388661, oplusNotificationHeaderViewWrapperExImp.expandButtonContainerMarginTop, oplusNotificationHeaderViewWrapperExImp.expandButtonPaddingTop, view10, z3);
        } else {
            oplusNotificationHeaderViewWrapperExImp = this;
            oplusNotificationHeaderViewWrapperExImp.updateExpandButtonContainerParams(8388629, 0, 0, view10, z3);
        }
        if (z3 || !z || (notificationHeaderViewWrapperM459getBase2 = oplusNotificationHeaderViewWrapperExImp.m459getBase()) == null || (expandButton2 = notificationHeaderViewWrapperM459getBase2.getExpandButton()) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams4 = expandButton2.getLayoutParams();
        if (layoutParams4 != null) {
            layoutParams4.height = -2;
            layoutParams = layoutParams4;
        }
        expandButton2.setLayoutParams(layoutParams);
    }

    public final boolean updateIconColor() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        OplusNotificationEntryEx ex;
        ExpandableNotificationRow expandableNotificationRow2;
        NotificationEntry entry2;
        StatusBarNotification sbn;
        ExpandableNotificationRow expandableNotificationRow3;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        int i = 0;
        if (notificationHeaderViewWrapperM459getBase != null) {
            CachingIconView cachingIconView = notificationHeaderViewWrapperM459getBase.mIcon;
            Boolean boolValueOf = null;
            if (cachingIconView != null && cachingIconView.getDrawable() != null) {
                OplusContrastColorUtil oplusContrastColorUtil = OplusContrastColorUtil.getInstance(notificationHeaderViewWrapperM459getBase.mIcon.getContext());
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2 = m459getBase();
                if (notificationHeaderViewWrapperM459getBase2 != null && (expandableNotificationRow3 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase2).mRow) != null && notificationHeaderViewWrapperM459getBase2.mIcon != null) {
                    Icon smallIcon = expandableNotificationRow3.getEntry().getSbn().getNotification().getSmallIcon();
                    Icon icon = this.lastIcon;
                    if (icon == null) {
                        this.lastIcon = smallIcon;
                    } else if (!smallIcon.sameAs(icon)) {
                        if (notificationHeaderViewWrapperM459getBase2.mIcon.getTag(2131363396) != null) {
                            notificationHeaderViewWrapperM459getBase2.mIcon.setTag(2131363396, (Object) null);
                        }
                        this.lastIcon = smallIcon;
                    }
                }
                CachingIconView cachingIconView2 = notificationHeaderViewWrapperM459getBase.mIcon;
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase3 = m459getBase();
                Notification notification = (notificationHeaderViewWrapperM459getBase3 == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase3).mRow) == null || (entry2 = expandableNotificationRow2.getEntry()) == null || (sbn = entry2.getSbn()) == null) ? null : sbn.getNotification();
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase4 = m459getBase();
                if (notificationHeaderViewWrapperM459getBase4 != null && (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase4).mRow) != null && (entry = expandableNotificationRow.getEntry()) != null && (ex = entry.getEx()) != null) {
                    boolValueOf = Boolean.valueOf(((OplusNotificationEntryExImpl) ex).useAppIconForSmallIcon());
                }
                boolean zIsGrayscaleOplus = OpNotificationUtils.isGrayscaleOplus(cachingIconView2, oplusContrastColorUtil, notification, boolValueOf);
                if (zIsGrayscaleOplus) {
                    ExpandableNotificationRow expandableNotificationRow4 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow;
                    int color = ((expandableNotificationRow4 == null || !expandableNotificationRow4.getEntry().getSbn().getNotification().isColorized()) && !oplusContrastColorUtil.isVectorDrawable(notificationHeaderViewWrapperM459getBase.mIcon.getDrawable())) ? getContext().getColor(2131102881) : notificationHeaderViewWrapperM459getBase.getOriginalIconColor();
                    i = color;
                    notificationHeaderViewWrapperM459getBase.mIcon.setOriginalIconColor(i);
                }
                if (notificationHeaderViewWrapperM459getBase.mIcon.getDrawable() != null) {
                    notificationHeaderViewWrapperM459getBase.mIcon.getDrawable().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
                }
                return zIsGrayscaleOplus;
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "Trying to updateIconColor on null icon", null, 8);
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0039  */
    public final void updateIconContentDescription() {
        View viewFindViewById;
        ExpandableNotificationRow expandableNotificationRow;
        CharSequence text;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
        if (notificationHeaderViewWrapperM459getBase == null || notificationHeaderViewWrapperM459getBase.mIcon == null || (viewFindViewById = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mView.findViewById(R.id.assetsPaths)) == null || !TextUtils.isEmpty(viewFindViewById.getContentDescription())) {
            return;
        }
        TextView textView = notificationHeaderViewWrapperM459getBase.mAppNameText;
        String strLoadHeaderAppName = null;
        if (textView == null || (text = textView.getText()) == null) {
            expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow;
            if (expandableNotificationRow != null && (entry = expandableNotificationRow.getEntry()) != null && (sbn = entry.getSbn()) != null && (notification = sbn.getNotification()) != null) {
                strLoadHeaderAppName = notification.loadHeaderAppName(((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mView.getContext());
            }
            text = strLoadHeaderAppName;
        } else {
            if (TextUtils.isEmpty(text)) {
                text = null;
            }
            if (text == null) {
                expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow;
                if (expandableNotificationRow != null) {
                    strLoadHeaderAppName = notification.loadHeaderAppName(((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mView.getContext());
                }
                text = strLoadHeaderAppName;
            }
        }
        if (TextUtils.isEmpty(text)) {
            return;
        }
        viewFindViewById.setContentDescription(text);
        viewFindViewById.setImportantForAccessibility(1);
    }

    public final void updateIconRoundness(boolean z) {
        int i;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase;
        ExpandableNotificationRow expandableNotificationRow;
        String packageName;
        Drawable drawable;
        Drawable.ConstantState constantState;
        Drawable drawableNewDrawable;
        Drawable drawableMutate;
        NotificationEntry entry;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase2 = m459getBase();
        if (notificationHeaderViewWrapperM459getBase2 != null) {
            ImageView imageView = this.mRightIcon;
            if (imageView != null && imageView.getDrawable() != null && (notificationHeaderViewWrapperM459getBase = m459getBase()) != null && (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow) != null && (packageName = expandableNotificationRow.getEntry().getSbn().getPackageName()) != null && NotificationIconBadgePackages.Companion.getInstance().contains(packageName)) {
                String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(expandableNotificationRow.getEntry().getKey(), "|", packageName);
                int iResolveHeaderIconContentSizePx = resolveHeaderIconContentSizePx();
                int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131170675);
                Drawable headerIconBadgeDrawable = ((NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class)).getHeaderIconBadgeDrawable(strM);
                String logKeyNonNull = null;
                if (headerIconBadgeDrawable != null) {
                    applyExpandedCanvasIconWithBadge(headerIconBadgeDrawable, iResolveHeaderIconContentSizePx, dimensionPixelSize, null);
                    hideRightIconForBadge();
                } else {
                    ImageView imageView2 = this.mRightIcon;
                    if (imageView2 != null && (drawable = imageView2.getDrawable()) != null && (constantState = drawable.getConstantState()) != null && (drawableNewDrawable = constantState.newDrawable()) != null && (drawableMutate = drawableNewDrawable.mutate()) != null) {
                        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new scheduleApplyIconWithBadge.1(this, strM, expandableNotificationRow, drawableMutate, (Continuation) null), 2);
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    ExpandableNotificationRow expandableNotificationRow2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase2).mRow;
                    if (expandableNotificationRow2 != null && (entry = expandableNotificationRow2.getEntry()) != null) {
                        logKeyNonNull = NotificationUtilsKt.getLogKeyNonNull(entry);
                    }
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("Update IconWithBadge! Key =", logKeyNonNull, "OplusNotificationHeaderViewWrapperExImp-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
            FlavorTwoFeatureOption.INSTANCE.getClass();
            boolean zIsFlavorTwoDeviceExpLessThanC = FlavorTwoFeatureOption.isFlavorTwoDeviceExpLessThanC();
            if (zIsFlavorTwoDeviceExpLessThanC) {
                i = 2131236002;
            } else {
                i = FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 2131236003 : 2131236001;
            }
            CachingIconView cachingIconView = notificationHeaderViewWrapperM459getBase2.mIcon;
            if (cachingIconView != null) {
                cachingIconView.setBackground(getContext().getDrawable(i));
            }
            CachingIconView cachingIconView2 = notificationHeaderViewWrapperM459getBase2.mIcon;
            if (cachingIconView2 != null) {
                cachingIconView2.setClipToOutline((zIsFlavorTwoDeviceExpLessThanC && z) ? false : true);
            }
            CachingIconView cachingIconView3 = notificationHeaderViewWrapperM459getBase2.mIcon;
            if (cachingIconView3 != null) {
                cachingIconView3.invalidateOutline();
            }
        }
    }

    public final void updateRecentlyAudiblyAlerted(ExpandableNotificationRow expandableNotificationRow) {
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase;
        long jCurrentTimeMillis = System.currentTimeMillis() - expandableNotificationRow.getEntry().getLastAudiblyAlertedMs();
        SourceType sourceType = ExpandableNotificationRow.BASE_VALUE;
        if (jCurrentTimeMillis >= 30000 || (notificationHeaderViewWrapperM459getBase = m459getBase()) == null) {
            return;
        }
        notificationHeaderViewWrapperM459getBase.setRecentlyAudiblyAlerted(true);
    }

    public void updateTextStyleForAlpha(TextView textView, float f, int i) {
        if (m459getBase() != null) {
            if (textView != null && textView.getId() == 16909341) {
                textView.setTextColor(getContext().getResources().getColor(2131102919));
            }
            updateTextStyleForAlpha(textView, i);
            if (textView != null) {
                textView.setTextSize(f);
            }
        }
    }

    public void updateTextStyleForAlpha(TextView textView, int i) {
        if (m459getBase() == null || textView == null) {
            return;
        }
        textView.setTextColor(textView.getTextColors().withAlpha(i));
    }
}
