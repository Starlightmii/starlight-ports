package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.oplus.systemui.notification.headsup.windowframe.CouiMotionForwarder;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FullScreenBannerTouchHandler implements Gefingerpoken {
    public FullScreenBanner mAnimView;
    public boolean mBannerPullDownRebound;
    public CouiMotionForwarder mCouiMotionForwarder;
    public float mDensityScale;
    public NotificationEntry mEntry;
    public FlingAnimationUtils mFlingAnimationUtils;
    public boolean mFloatWindowTriggered;
    public FullScreenBannerHelper mFullscreenBannerHelper;
    public float mInitialTranslationY;
    public boolean mScrollWantsIt;
    public float mStartPosX;
    public float mStartPosY;
    public boolean mSwipeWantsIt;
    public Callback mTouchCallback;
    public VelocityTracker mVelocityTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    private int getDirection() {
        if (this.mSwipeWantsIt) {
            return 1;
        }
        return this.mScrollWantsIt ? 2 : 0;
    }

    private float getMaxVelocity() {
        return this.mDensityScale * 4000.0f;
    }

    public float getEscapeVelocity() {
        return this.mDensityScale * 500.0f;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        int action = motionEvent.getAction();
        boolean z = false;
        if (action == 0) {
            CouiMotionForwarder couiMotionForwarder = this.mCouiMotionForwarder;
            if (couiMotionForwarder != null) {
                ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).forwardCancel();
            }
            this.mSwipeWantsIt = false;
            this.mScrollWantsIt = false;
            this.mStartPosY = y;
            this.mStartPosX = x;
            this.mVelocityTracker.clear();
            this.mVelocityTracker.addMovement(motionEvent);
            return false;
        }
        if (action != 2) {
            return false;
        }
        float f = x - this.mStartPosX;
        float f2 = y - this.mStartPosY;
        if (!this.mSwipeWantsIt && !this.mScrollWantsIt && (Math.abs(f) > 25.0f || Math.abs(f2) > 25.0f)) {
            z = true;
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:107:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:109:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:112:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:113:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:115:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:116:0x0207  */
    /* JADX WARN: Code duplicated, block: B:118:0x0234  */
    /* JADX WARN: Code duplicated, block: B:120:0x023e  */
    /* JADX WARN: Code duplicated, block: B:60:0x010a  */
    /* JADX WARN: Code duplicated, block: B:80:0x0168  */
    /* JADX WARN: Code duplicated, block: B:88:0x017f  */
    /* JADX WARN: Code duplicated, block: B:90:0x0182  */
    /* JADX WARN: Code duplicated, block: B:92:0x0190  */
    /* JADX WARN: Code duplicated, block: B:94:0x0194  */
    /* JADX WARN: Code duplicated, block: B:95:0x019b  */
    /* JADX WARN: Code duplicated, block: B:97:0x019f  */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zCanExpandableViewBeDismissed;
        float xVelocity;
        boolean zSwipedFastEnough$1;
        boolean z;
        FullScreenBanner fullScreenBanner;
        int width;
        float f;
        ObjectAnimator objectAnimatorOfFloat;
        long jMin;
        Callback callback;
        NotificationEntry notificationEntry;
        float xVelocity2;
        CouiMotionForwarder couiMotionForwarder;
        FullScreenBannerHelper fullScreenBannerHelper;
        NotificationEntry notificationEntry2;
        FullScreenBannerHelper fullScreenBannerHelper2;
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        int action = motionEvent.getAction();
        boolean zCanExpandableViewBeDismissed2 = false;
        if (action == 0) {
            CouiMotionForwarder couiMotionForwarder2 = this.mCouiMotionForwarder;
            if (couiMotionForwarder2 != null) {
                ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder2).forwardCancel();
            }
            this.mSwipeWantsIt = false;
            this.mScrollWantsIt = false;
            this.mStartPosY = y;
            this.mStartPosX = x;
            this.mFloatWindowTriggered = false;
            this.mVelocityTracker.addMovement(motionEvent);
            return true;
        }
        if (action == 1) {
            if (this.mScrollWantsIt && (couiMotionForwarder = this.mCouiMotionForwarder) != null && (fullScreenBannerHelper = this.mFullscreenBannerHelper) != null && fullScreenBannerHelper.mBannerZoom) {
                ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).forwardMotionEvent(motionEvent);
            }
            this.mVelocityTracker.addMovement(motionEvent);
            this.mAnimView.getBannerBoostFrameRateRateHandler().setBoostFrameRate(false);
            this.mVelocityTracker.computeCurrentVelocity(1000, getMaxVelocity());
            if (!swipedFastEnough$1() || (!this.mSwipeWantsIt ? !this.mScrollWantsIt || this.mAnimView.getTranslationY() >= this.mInitialTranslationY : this.mAnimView.getTranslationX() <= 0.65f * this.mAnimView.getWidth())) {
                if (this.mSwipeWantsIt || (notificationEntry = this.mEntry) == null) {
                    zCanExpandableViewBeDismissed = true;
                } else {
                    ExpandableNotificationRow row = notificationEntry.getRow();
                    int i = NotificationStackScrollLayout.$r8$clinit;
                    zCanExpandableViewBeDismissed = row != null ? row.canExpandableViewBeDismissed() : false;
                }
                if (zCanExpandableViewBeDismissed) {
                    xVelocity = this.mVelocityTracker.getXVelocity();
                    zSwipedFastEnough$1 = swipedFastEnough$1();
                    if (this.mScrollWantsIt) {
                        callback = this.mTouchCallback;
                        if (callback != null) {
                            ((FullScreenBannerHelper.AnonymousClass9) callback).hide();
                        }
                    } else if (this.mSwipeWantsIt) {
                        z = (Math.abs(xVelocity) <= getEscapeVelocity() && xVelocity < 0.0f) || this.mAnimView.getTranslationY() < 0.0f;
                        fullScreenBanner = this.mAnimView;
                        if (z) {
                            width = -fullScreenBanner.getWidth();
                        } else {
                            width = fullScreenBanner.getWidth();
                        }
                        f = width;
                        objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, f);
                        if (xVelocity != 0.0f) {
                            jMin = Math.min(400L, (int) ((Math.abs(f - this.mAnimView.getTranslationX()) * 1000.0f) / Math.abs(xVelocity)));
                        } else {
                            jMin = 200;
                        }
                        if (zSwipedFastEnough$1) {
                            this.mFlingAnimationUtils.applyDismissing(objectAnimatorOfFloat, this.mAnimView.getTranslationX(), f, xVelocity, this.mAnimView.getWidth());
                        } else {
                            objectAnimatorOfFloat.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
                            objectAnimatorOfFloat.setDuration(jMin);
                        }
                        this.mAnimView.setLayerType(2, null);
                        objectAnimatorOfFloat.addListener(new 1(this));
                        this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat);
                        objectAnimatorOfFloat.start();
                    }
                } else {
                    xVelocity2 = this.mVelocityTracker.getXVelocity();
                    if (this.mSwipeWantsIt) {
                        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, 0.0f);
                        this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat2);
                        this.mFlingAnimationUtils.apply(objectAnimatorOfFloat2, this.mAnimView.getTranslationX(), 0.0f, xVelocity2, Math.abs(0.0f - this.mAnimView.getTranslationX()));
                        objectAnimatorOfFloat2.start();
                    }
                }
            } else {
                xVelocity2 = this.mVelocityTracker.getXVelocity();
                if (this.mSwipeWantsIt) {
                    ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, 0.0f);
                    this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat3);
                    this.mFlingAnimationUtils.apply(objectAnimatorOfFloat3, this.mAnimView.getTranslationX(), 0.0f, xVelocity2, Math.abs(0.0f - this.mAnimView.getTranslationX()));
                    objectAnimatorOfFloat3.start();
                }
            }
            if (this.mScrollWantsIt && this.mBannerPullDownRebound) {
                this.mFullscreenBannerHelper.setBannerTranslationY(this.mInitialTranslationY);
            }
        } else {
            if (action == 2) {
                float fSin = x - this.mStartPosX;
                float f2 = y - this.mStartPosY;
                if (!this.mSwipeWantsIt && !this.mScrollWantsIt && (Math.abs(fSin) > 25.0f || Math.abs(f2) > 25.0f)) {
                    if (Math.abs(f2) >= Math.abs(fSin)) {
                        this.mScrollWantsIt = true;
                        this.mInitialTranslationY = this.mFullscreenBannerHelper.getEarScreenTranslationY();
                    } else {
                        this.mSwipeWantsIt = true;
                    }
                }
                this.mAnimView.getBannerBoostFrameRateRateHandler().setBoostFrameRate(true);
                if (this.mScrollWantsIt) {
                    FullScreenBannerHelper fullScreenBannerHelper3 = this.mFullscreenBannerHelper;
                    if (!fullScreenBannerHelper3.mBannerCollpseAniming) {
                        if (f2 < 0.0f) {
                            fullScreenBannerHelper3.setBannerDragTranslationY(Math.min(f2, 0.0f) + this.mInitialTranslationY);
                        } else if (f2 < 100.0f || this.mFloatWindowTriggered) {
                            fullScreenBannerHelper3.setBannerTranslationY(this.mInitialTranslationY + f2);
                            this.mBannerPullDownRebound = true;
                        } else {
                            this.mFloatWindowTriggered = true;
                            this.mBannerPullDownRebound = false;
                            fullScreenBannerHelper3.onStartFloatWindow(this.mEntry);
                        }
                        CouiMotionForwarder couiMotionForwarder3 = this.mCouiMotionForwarder;
                        if (couiMotionForwarder3 != null && (fullScreenBannerHelper2 = this.mFullscreenBannerHelper) != null && fullScreenBannerHelper2.mBannerZoom) {
                            ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder3).forwardMotionEvent(motionEvent);
                        }
                    }
                }
                if (this.mSwipeWantsIt) {
                    float width2 = this.mAnimView.getWidth() * 0.3f;
                    if (Math.abs(fSin) > width2) {
                        if (!this.mSwipeWantsIt || (notificationEntry2 = this.mEntry) == null) {
                            zCanExpandableViewBeDismissed2 = true;
                        } else {
                            ExpandableNotificationRow row2 = notificationEntry2.getRow();
                            int i2 = NotificationStackScrollLayout.$r8$clinit;
                            if (row2 != null) {
                                zCanExpandableViewBeDismissed2 = row2.canExpandableViewBeDismissed();
                            }
                        }
                        if (!zCanExpandableViewBeDismissed2) {
                            float width3 = this.mAnimView.getWidth() * 0.4f;
                            float fSignum = (int) (Math.signum(fSin) * width2);
                            fSin = (width3 * ((float) Math.sin(((double) ((fSin - fSignum) / this.mAnimView.getWidth())) * 1.5707963267948966d))) + fSignum;
                        }
                    }
                    this.mAnimView.setTranslationX(fSin);
                }
                this.mVelocityTracker.addMovement(motionEvent);
                return true;
            }
            if (action == 3) {
                if (this.mScrollWantsIt) {
                    ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).forwardMotionEvent(motionEvent);
                }
                this.mVelocityTracker.addMovement(motionEvent);
                this.mAnimView.getBannerBoostFrameRateRateHandler().setBoostFrameRate(false);
                this.mVelocityTracker.computeCurrentVelocity(1000, getMaxVelocity());
                if (swipedFastEnough$1()) {
                    xVelocity2 = this.mVelocityTracker.getXVelocity();
                    if (this.mSwipeWantsIt) {
                        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, 0.0f);
                        this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat4);
                        this.mFlingAnimationUtils.apply(objectAnimatorOfFloat4, this.mAnimView.getTranslationX(), 0.0f, xVelocity2, Math.abs(0.0f - this.mAnimView.getTranslationX()));
                        objectAnimatorOfFloat4.start();
                    }
                } else {
                    if (this.mSwipeWantsIt) {
                        zCanExpandableViewBeDismissed = true;
                    } else {
                        zCanExpandableViewBeDismissed = true;
                    }
                    if (zCanExpandableViewBeDismissed) {
                        xVelocity = this.mVelocityTracker.getXVelocity();
                        zSwipedFastEnough$1 = swipedFastEnough$1();
                        if (this.mScrollWantsIt) {
                            callback = this.mTouchCallback;
                            if (callback != null) {
                                ((FullScreenBannerHelper.AnonymousClass9) callback).hide();
                            }
                        } else if (this.mSwipeWantsIt) {
                            if (Math.abs(xVelocity) <= getEscapeVelocity()) {
                            }
                            fullScreenBanner = this.mAnimView;
                            if (z) {
                                width = -fullScreenBanner.getWidth();
                            } else {
                                width = fullScreenBanner.getWidth();
                            }
                            f = width;
                            objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, f);
                            if (xVelocity != 0.0f) {
                                jMin = Math.min(400L, (int) ((Math.abs(f - this.mAnimView.getTranslationX()) * 1000.0f) / Math.abs(xVelocity)));
                            } else {
                                jMin = 200;
                            }
                            if (zSwipedFastEnough$1) {
                                objectAnimatorOfFloat.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
                                objectAnimatorOfFloat.setDuration(jMin);
                            } else {
                                this.mFlingAnimationUtils.applyDismissing(objectAnimatorOfFloat, this.mAnimView.getTranslationX(), f, xVelocity, this.mAnimView.getWidth());
                            }
                            this.mAnimView.setLayerType(2, null);
                            objectAnimatorOfFloat.addListener(new 1(this));
                            this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat);
                            objectAnimatorOfFloat.start();
                        }
                    } else {
                        xVelocity2 = this.mVelocityTracker.getXVelocity();
                        if (this.mSwipeWantsIt) {
                            ObjectAnimator objectAnimatorOfFloat5 = ObjectAnimator.ofFloat(this.mAnimView, (Property<FullScreenBanner, Float>) View.TRANSLATION_X, 0.0f);
                            this.mAnimView.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat5);
                            this.mFlingAnimationUtils.apply(objectAnimatorOfFloat5, this.mAnimView.getTranslationX(), 0.0f, xVelocity2, Math.abs(0.0f - this.mAnimView.getTranslationX()));
                            objectAnimatorOfFloat5.start();
                        }
                    }
                }
                if (this.mScrollWantsIt) {
                    this.mFullscreenBannerHelper.setBannerTranslationY(this.mInitialTranslationY);
                }
            }
        }
        return true;
    }

    public void setAnimView(FullScreenBanner fullScreenBanner) {
        this.mAnimView = fullScreenBanner;
        this.mDensityScale = fullScreenBanner.getResources().getDisplayMetrics().density;
        this.mFlingAnimationUtils = new FlingAnimationUtils(this.mAnimView.getResources().getDisplayMetrics(), 0.4f);
    }

    public void setCallback(Callback callback) {
        this.mTouchCallback = callback;
    }

    public void setCouiMotionForwarder(CouiMotionForwarder couiMotionForwarder) {
        this.mCouiMotionForwarder = couiMotionForwarder;
    }

    public void setEntry(NotificationEntry notificationEntry) {
        this.mEntry = notificationEntry;
    }

    public void setFullscreenBannerHelper(FullScreenBannerHelper fullScreenBannerHelper) {
        this.mFullscreenBannerHelper = fullScreenBannerHelper;
    }

    public final boolean swipedFastEnough$1() {
        if (this.mSwipeWantsIt) {
            float xVelocity = this.mVelocityTracker.getXVelocity();
            float translationX = this.mAnimView.getTranslationX();
            if (Math.abs(xVelocity) > getEscapeVelocity()) {
                if ((xVelocity > 0.0f) == (translationX > 0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }
}
