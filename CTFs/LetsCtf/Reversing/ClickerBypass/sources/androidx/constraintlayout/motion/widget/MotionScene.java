package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MotionScene {
    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    private static final String CONSTRAINTSET_TAG = "ConstraintSet";
    private static final boolean DEBUG = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final String INCLUDE_TAG = "include";
    private static final String INCLUDE_TAG_UC = "Include";
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final String KEYFRAMESET_TAG = "KeyFrameSet";
    public static final int LAYOUT_CALL_MEASURE = 2;
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    static final int LINEAR = 3;
    private static final int MIN_DURATION = 8;
    private static final String MOTIONSCENE_TAG = "MotionScene";
    private static final String ONCLICK_TAG = "OnClick";
    private static final String ONSWIPE_TAG = "OnSwipe";
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    private static final String STATESET_TAG = "StateSet";
    private static final String TAG = "MotionScene";
    static final int TRANSITION_BACKWARD = 0;
    static final int TRANSITION_FORWARD = 1;
    private static final String TRANSITION_TAG = "Transition";
    public static final int UNSET = -1;
    private static final String VIEW_TRANSITION = "ViewTransition";
    private boolean DEBUG_DESKTOP = false;
    private ArrayList<Transition> mAbstractTransitionList = new ArrayList<>();
    private HashMap<String, Integer> mConstraintSetIdMap = new HashMap<>();
    /* access modifiers changed from: private */
    public SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    Transition mCurrentTransition = null;
    /* access modifiers changed from: private */
    public int mDefaultDuration = 400;
    private Transition mDefaultTransition = null;
    private SparseIntArray mDeriveMap = new SparseIntArray();
    private boolean mDisableAutoTransition = false;
    private boolean mIgnoreTouch = false;
    private MotionEvent mLastTouchDown;
    float mLastTouchX;
    float mLastTouchY;
    /* access modifiers changed from: private */
    public int mLayoutDuringTransition = 0;
    /* access modifiers changed from: private */
    public final MotionLayout mMotionLayout;
    private boolean mMotionOutsideRegion = false;
    private boolean mRtl;
    StateSet mStateSet = null;
    private ArrayList<Transition> mTransitionList = new ArrayList<>();
    private MotionLayout.MotionTracker mVelocityTracker;
    final ViewTransitionController mViewTransitionController;

    public float getPathPercent(View view, int i) {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        if (r2 != -1) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTransition(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.StateSet r0 = r6.mStateSet
            r1 = -1
            if (r0 == 0) goto L_0x0016
            int r0 = r0.stateGetConstraintID(r7, r1, r1)
            if (r0 == r1) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r0 = r7
        L_0x000d:
            androidx.constraintlayout.widget.StateSet r2 = r6.mStateSet
            int r2 = r2.stateGetConstraintID(r8, r1, r1)
            if (r2 == r1) goto L_0x0017
            goto L_0x0018
        L_0x0016:
            r0 = r7
        L_0x0017:
            r2 = r8
        L_0x0018:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.mCurrentTransition
            if (r3 == 0) goto L_0x002b
            int r3 = r3.mConstraintSetEnd
            if (r3 != r8) goto L_0x002b
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.mCurrentTransition
            int r3 = r3.mConstraintSetStart
            if (r3 != r7) goto L_0x002b
            return
        L_0x002b:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.mTransitionList
            java.util.Iterator r3 = r3.iterator()
        L_0x0031:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x006b
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = r4.mConstraintSetEnd
            if (r5 != r2) goto L_0x0049
            int r5 = r4.mConstraintSetStart
            if (r5 == r0) goto L_0x0055
        L_0x0049:
            int r5 = r4.mConstraintSetEnd
            if (r5 != r8) goto L_0x0031
            int r5 = r4.mConstraintSetStart
            if (r5 != r7) goto L_0x0031
        L_0x0055:
            r6.mCurrentTransition = r4
            if (r4 == 0) goto L_0x006a
            androidx.constraintlayout.motion.widget.TouchResponse r7 = r4.mTouchResponse
            if (r7 == 0) goto L_0x006a
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.mCurrentTransition
            androidx.constraintlayout.motion.widget.TouchResponse r7 = r7.mTouchResponse
            boolean r8 = r6.mRtl
            r7.setRTL(r8)
        L_0x006a:
            return
        L_0x006b:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.mDefaultTransition
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.mAbstractTransitionList
            java.util.Iterator r3 = r3.iterator()
        L_0x0073:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0087
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = r4.mConstraintSetEnd
            if (r5 != r8) goto L_0x0073
            r7 = r4
            goto L_0x0073
        L_0x0087:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r8.<init>(r6, r7)
            int unused = r8.mConstraintSetStart = r0
            int unused = r8.mConstraintSetEnd = r2
            if (r0 == r1) goto L_0x0099
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r7 = r6.mTransitionList
            r7.add(r8)
        L_0x0099:
            r6.mCurrentTransition = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.setTransition(int, int):void");
    }

    public void addTransition(Transition transition) {
        int index = getIndex(transition);
        if (index == -1) {
            this.mTransitionList.add(transition);
        } else {
            this.mTransitionList.set(index, transition);
        }
    }

    public void removeTransition(Transition transition) {
        int index = getIndex(transition);
        if (index != -1) {
            this.mTransitionList.remove(index);
        }
    }

    private int getIndex(Transition transition) {
        int access$300 = transition.mId;
        if (access$300 != -1) {
            for (int i = 0; i < this.mTransitionList.size(); i++) {
                if (this.mTransitionList.get(i).mId == access$300) {
                    return i;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("The transition must have an id");
    }

    public boolean validateLayout(MotionLayout motionLayout) {
        return motionLayout == this.mMotionLayout && motionLayout.mScene == this;
    }

    public void setTransition(Transition transition) {
        this.mCurrentTransition = transition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.stateGetConstraintID(r3, -1, -1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getRealID(int r3) {
        /*
            r2 = this;
            androidx.constraintlayout.widget.StateSet r0 = r2.mStateSet
            if (r0 == 0) goto L_0x000c
            r1 = -1
            int r0 = r0.stateGetConstraintID(r3, r1, r1)
            if (r0 == r1) goto L_0x000c
            return r0
        L_0x000c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.getRealID(int):int");
    }

    public List<Transition> getTransitionsWithState(int i) {
        int realID = getRealID(i);
        ArrayList arrayList = new ArrayList();
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mConstraintSetStart == realID || next.mConstraintSetEnd == realID) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int i) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mOnClicks.size() > 0) {
                Iterator it2 = next.mOnClicks.iterator();
                while (it2.hasNext()) {
                    ((Transition.TransitionOnClick) it2.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it3 = this.mAbstractTransitionList.iterator();
        while (it3.hasNext()) {
            Transition next2 = it3.next();
            if (next2.mOnClicks.size() > 0) {
                Iterator it4 = next2.mOnClicks.iterator();
                while (it4.hasNext()) {
                    ((Transition.TransitionOnClick) it4.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it5 = this.mTransitionList.iterator();
        while (it5.hasNext()) {
            Transition next3 = it5.next();
            if (next3.mOnClicks.size() > 0) {
                Iterator it6 = next3.mOnClicks.iterator();
                while (it6.hasNext()) {
                    ((Transition.TransitionOnClick) it6.next()).addOnClickListeners(motionLayout, i, next3);
                }
            }
        }
        Iterator<Transition> it7 = this.mAbstractTransitionList.iterator();
        while (it7.hasNext()) {
            Transition next4 = it7.next();
            if (next4.mOnClicks.size() > 0) {
                Iterator it8 = next4.mOnClicks.iterator();
                while (it8.hasNext()) {
                    ((Transition.TransitionOnClick) it8.next()).addOnClickListeners(motionLayout, i, next4);
                }
            }
        }
    }

    public Transition bestTransitionFor(int i, float f, float f2, MotionEvent motionEvent) {
        RectF limitBoundsTo;
        int i2 = i;
        float f3 = f;
        float f4 = f2;
        if (i2 == -1) {
            return this.mCurrentTransition;
        }
        List<Transition> transitionsWithState = getTransitionsWithState(i);
        float f5 = 0.0f;
        Transition transition = null;
        RectF rectF = new RectF();
        for (Transition next : transitionsWithState) {
            if (!next.mDisable && next.mTouchResponse != null) {
                next.mTouchResponse.setRTL(this.mRtl);
                RectF touchRegion = next.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                if ((touchRegion == null || motionEvent == null || touchRegion.contains(motionEvent.getX(), motionEvent.getY())) && ((limitBoundsTo = next.mTouchResponse.getLimitBoundsTo(this.mMotionLayout, rectF)) == null || motionEvent == null || limitBoundsTo.contains(motionEvent.getX(), motionEvent.getY()))) {
                    float dot = next.mTouchResponse.dot(f3, f4);
                    if (next.mTouchResponse.mIsRotateMode && motionEvent != null) {
                        float x = motionEvent.getX() - next.mTouchResponse.mRotateCenterX;
                        float y = motionEvent.getY() - next.mTouchResponse.mRotateCenterY;
                        dot = ((float) (Math.atan2((double) (f4 + y), (double) (f3 + x)) - Math.atan2((double) x, (double) y))) * 10.0f;
                    }
                    float f6 = dot * (next.mConstraintSetEnd == i2 ? -1.0f : 1.1f);
                    if (f6 > f5) {
                        transition = next;
                        f5 = f6;
                    }
                }
            }
        }
        return transition;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.mTransitionList;
    }

    public Transition getTransitionById(int i) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mId == i) {
                return next;
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int size = this.mConstraintSetMap.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.mConstraintSetMap.keyAt(i);
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public boolean autoTransition(MotionLayout motionLayout, int i) {
        Transition transition;
        if (isProcessingTouch() || this.mDisableAutoTransition) {
            return false;
        }
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mAutoTransition != 0 && ((transition = this.mCurrentTransition) != next || !transition.isTransitionFlag(2))) {
                if (i == next.mConstraintSetStart && (next.mAutoTransition == 4 || next.mAutoTransition == 2)) {
                    motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    motionLayout.setTransition(next);
                    if (next.mAutoTransition == 4) {
                        motionLayout.transitionToEnd();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                        motionLayout.onNewStateAttachHandlers();
                    }
                    return true;
                } else if (i == next.mConstraintSetEnd && (next.mAutoTransition == 3 || next.mAutoTransition == 1)) {
                    motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    motionLayout.setTransition(next);
                    if (next.mAutoTransition == 3) {
                        motionLayout.transitionToStart();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                        motionLayout.onNewStateAttachHandlers();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isProcessingTouch() {
        return this.mVelocityTracker != null;
    }

    public void setRtl(boolean z) {
        this.mRtl = z;
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    public void viewTransition(int i, View... viewArr) {
        this.mViewTransitionController.viewTransition(i, viewArr);
    }

    public void enableViewTransition(int i, boolean z) {
        this.mViewTransitionController.enableViewTransition(i, z);
    }

    public boolean isViewTransitionEnabled(int i) {
        return this.mViewTransitionController.isViewTransitionEnabled(i);
    }

    public boolean applyViewTransition(int i, MotionController motionController) {
        return this.mViewTransitionController.applyViewTransition(i, motionController);
    }

    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        static final int TRANSITION_FLAG_FIRST_DRAW = 1;
        static final int TRANSITION_FLAG_INTERCEPT_TOUCH = 4;
        static final int TRANSITION_FLAG_INTRA_AUTO = 2;
        /* access modifiers changed from: private */
        public int mAutoTransition = 0;
        /* access modifiers changed from: private */
        public int mConstraintSetEnd = -1;
        /* access modifiers changed from: private */
        public int mConstraintSetStart = -1;
        /* access modifiers changed from: private */
        public int mDefaultInterpolator = 0;
        /* access modifiers changed from: private */
        public int mDefaultInterpolatorID = -1;
        /* access modifiers changed from: private */
        public String mDefaultInterpolatorString = null;
        /* access modifiers changed from: private */
        public boolean mDisable = false;
        /* access modifiers changed from: private */
        public int mDuration = 400;
        /* access modifiers changed from: private */
        public int mId = -1;
        /* access modifiers changed from: private */
        public boolean mIsAbstract = false;
        /* access modifiers changed from: private */
        public ArrayList<KeyFrames> mKeyFramesList = new ArrayList<>();
        private int mLayoutDuringTransition = 0;
        /* access modifiers changed from: private */
        public final MotionScene mMotionScene;
        /* access modifiers changed from: private */
        public ArrayList<TransitionOnClick> mOnClicks = new ArrayList<>();
        /* access modifiers changed from: private */
        public int mPathMotionArc = -1;
        /* access modifiers changed from: private */
        public float mStagger = 0.0f;
        /* access modifiers changed from: private */
        public TouchResponse mTouchResponse = null;
        private int mTransitionFlags = 0;

        public void setOnSwipe(OnSwipe onSwipe) {
            this.mTouchResponse = onSwipe == null ? null : new TouchResponse(this.mMotionScene.mMotionLayout, onSwipe);
        }

        public void addOnClick(int i, int i2) {
            Iterator<TransitionOnClick> it = this.mOnClicks.iterator();
            while (it.hasNext()) {
                TransitionOnClick next = it.next();
                if (next.mTargetId == i) {
                    next.mMode = i2;
                    return;
                }
            }
            this.mOnClicks.add(new TransitionOnClick(this, i, i2));
        }

        public void removeOnClick(int i) {
            TransitionOnClick transitionOnClick;
            Iterator<TransitionOnClick> it = this.mOnClicks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    transitionOnClick = null;
                    break;
                }
                transitionOnClick = it.next();
                if (transitionOnClick.mTargetId == i) {
                    break;
                }
            }
            if (transitionOnClick != null) {
                this.mOnClicks.remove(transitionOnClick);
            }
        }

        public int getLayoutDuringTransition() {
            return this.mLayoutDuringTransition;
        }

        public void setLayoutDuringTransition(int i) {
            this.mLayoutDuringTransition = i;
        }

        public void addOnClick(Context context, XmlPullParser xmlPullParser) {
            this.mOnClicks.add(new TransitionOnClick(context, this, xmlPullParser));
        }

        public void setAutoTransition(int i) {
            this.mAutoTransition = i;
        }

        public int getAutoTransition() {
            return this.mAutoTransition;
        }

        public int getId() {
            return this.mId;
        }

        public int getEndConstraintSetId() {
            return this.mConstraintSetEnd;
        }

        public int getStartConstraintSetId() {
            return this.mConstraintSetStart;
        }

        public void setDuration(int i) {
            this.mDuration = Math.max(i, 8);
        }

        public int getDuration() {
            return this.mDuration;
        }

        public float getStagger() {
            return this.mStagger;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.mKeyFramesList;
        }

        public void addKeyFrame(KeyFrames keyFrames) {
            this.mKeyFramesList.add(keyFrames);
        }

        public List<TransitionOnClick> getOnClickList() {
            return this.mOnClicks;
        }

        public TouchResponse getTouchResponse() {
            return this.mTouchResponse;
        }

        public void setStagger(float f) {
            this.mStagger = f;
        }

        public void setPathMotionArc(int i) {
            this.mPathMotionArc = i;
        }

        public int getPathMotionArc() {
            return this.mPathMotionArc;
        }

        public boolean isEnabled() {
            return !this.mDisable;
        }

        public void setEnable(boolean z) {
            setEnabled(z);
        }

        public void setEnabled(boolean z) {
            this.mDisable = !z;
        }

        public String debugString(Context context) {
            String str;
            if (this.mConstraintSetStart == -1) {
                str = "null";
            } else {
                str = context.getResources().getResourceEntryName(this.mConstraintSetStart);
            }
            if (this.mConstraintSetEnd == -1) {
                return String.valueOf(str).concat(" -> null");
            }
            String valueOf = String.valueOf(str);
            String resourceEntryName = context.getResources().getResourceEntryName(this.mConstraintSetEnd);
            return new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(resourceEntryName).length()).append(valueOf).append(" -> ").append(resourceEntryName).toString();
        }

        public boolean isTransitionFlag(int i) {
            return (i & this.mTransitionFlags) != 0;
        }

        public void setTransitionFlag(int i) {
            this.mTransitionFlags = i;
        }

        public void setOnTouchUp(int i) {
            TouchResponse touchResponse = getTouchResponse();
            if (touchResponse != null) {
                touchResponse.setTouchUpMode(i);
            }
        }

        public static class TransitionOnClick implements View.OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 256;
            public static final int JUMP_TO_START = 4096;
            int mMode = 17;
            int mTargetId = -1;
            private final Transition mTransition;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.mTransition = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = obtainStyledAttributes.getIndex(i);
                    if (index == R.styleable.OnClick_targetId) {
                        this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.mMode = obtainStyledAttributes.getInt(index, this.mMode);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            public TransitionOnClick(Transition transition, int i, int i2) {
                this.mTransition = transition;
                this.mTargetId = i;
                this.mMode = i2;
            }

            public void addOnClickListeners(MotionLayout motionLayout, int i, Transition transition) {
                int i2 = this.mTargetId;
                View view = motionLayout;
                if (i2 != -1) {
                    view = motionLayout.findViewById(i2);
                }
                if (view == null) {
                    Log.e(TypedValues.MotionScene.NAME, new StringBuilder(37).append("OnClick could not find id ").append(this.mTargetId).toString());
                    return;
                }
                int access$100 = transition.mConstraintSetStart;
                int access$000 = transition.mConstraintSetEnd;
                if (access$100 == -1) {
                    view.setOnClickListener(this);
                    return;
                }
                int i3 = this.mMode;
                boolean z = false;
                boolean z2 = ((i3 & 1) != 0 && i == access$100) | ((i3 & 1) != 0 && i == access$100) | ((i3 & 256) != 0 && i == access$100) | ((i3 & 16) != 0 && i == access$000);
                if ((i3 & 4096) != 0 && i == access$000) {
                    z = true;
                }
                if (z2 || z) {
                    view.setOnClickListener(this);
                }
            }

            public void removeOnClickListeners(MotionLayout motionLayout) {
                int i = this.mTargetId;
                if (i != -1) {
                    View findViewById = motionLayout.findViewById(i);
                    if (findViewById == null) {
                        Log.e(TypedValues.MotionScene.NAME, new StringBuilder(35).append(" (*)  could not find id ").append(this.mTargetId).toString());
                        return;
                    }
                    findViewById.setOnClickListener((View.OnClickListener) null);
                }
            }

            /* access modifiers changed from: package-private */
            public boolean isTransitionViable(Transition transition, MotionLayout motionLayout) {
                Transition transition2 = this.mTransition;
                if (transition2 == transition) {
                    return true;
                }
                int access$000 = transition2.mConstraintSetEnd;
                int access$100 = this.mTransition.mConstraintSetStart;
                if (access$100 == -1) {
                    if (motionLayout.mCurrentState != access$000) {
                        return true;
                    }
                    return false;
                } else if (motionLayout.mCurrentState == access$100 || motionLayout.mCurrentState == access$000) {
                    return true;
                } else {
                    return false;
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3  */
            /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r8) {
                /*
                    r7 = this;
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = r7.mTransition
                    androidx.constraintlayout.motion.widget.MotionScene r8 = r8.mMotionScene
                    androidx.constraintlayout.motion.widget.MotionLayout r8 = r8.mMotionLayout
                    boolean r0 = r8.isInteractionEnabled()
                    if (r0 != 0) goto L_0x0011
                    return
                L_0x0011:
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    int r0 = r0.mConstraintSetStart
                    r1 = -1
                    if (r0 != r1) goto L_0x004a
                    int r0 = r8.getCurrentState()
                    if (r0 != r1) goto L_0x002a
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    int r0 = r0.mConstraintSetEnd
                    r8.transitionToState(r0)
                    return
                L_0x002a:
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r1 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r2 = r7.mTransition
                    androidx.constraintlayout.motion.widget.MotionScene r2 = r2.mMotionScene
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r7.mTransition
                    r1.<init>(r2, r3)
                    int unused = r1.mConstraintSetStart = r0
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    int r0 = r0.mConstraintSetEnd
                    int unused = r1.mConstraintSetEnd = r0
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r1)
                    r8.transitionToEnd()
                    return
                L_0x004a:
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    androidx.constraintlayout.motion.widget.MotionScene r0 = r0.mMotionScene
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r0.mCurrentTransition
                    int r1 = r7.mMode
                    r2 = r1 & 1
                    r3 = 0
                    r4 = 1
                    if (r2 != 0) goto L_0x0061
                    r2 = r1 & 256(0x100, float:3.59E-43)
                    if (r2 == 0) goto L_0x005f
                    goto L_0x0061
                L_0x005f:
                    r2 = r3
                    goto L_0x0062
                L_0x0061:
                    r2 = r4
                L_0x0062:
                    r5 = r1 & 16
                    if (r5 != 0) goto L_0x006d
                    r1 = r1 & 4096(0x1000, float:5.74E-42)
                    if (r1 == 0) goto L_0x006b
                    goto L_0x006d
                L_0x006b:
                    r1 = r3
                    goto L_0x006e
                L_0x006d:
                    r1 = r4
                L_0x006e:
                    if (r2 == 0) goto L_0x0074
                    if (r1 == 0) goto L_0x0074
                    r5 = r4
                    goto L_0x0075
                L_0x0074:
                    r5 = r3
                L_0x0075:
                    if (r5 == 0) goto L_0x009c
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r5 = r7.mTransition
                    androidx.constraintlayout.motion.widget.MotionScene r5 = r5.mMotionScene
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r5 = r5.mCurrentTransition
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r6 = r7.mTransition
                    if (r5 == r6) goto L_0x0086
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r6)
                L_0x0086:
                    int r5 = r8.getCurrentState()
                    int r6 = r8.getEndState()
                    if (r5 == r6) goto L_0x009d
                    float r5 = r8.getProgress()
                    r6 = 1056964608(0x3f000000, float:0.5)
                    int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                    if (r5 <= 0) goto L_0x009b
                    goto L_0x009d
                L_0x009b:
                    r1 = r3
                L_0x009c:
                    r3 = r2
                L_0x009d:
                    boolean r0 = r7.isTransitionViable(r0, r8)
                    if (r0 == 0) goto L_0x00e8
                    if (r3 == 0) goto L_0x00b3
                    int r0 = r7.mMode
                    r0 = r0 & r4
                    if (r0 == 0) goto L_0x00b3
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r0)
                    r8.transitionToEnd()
                    goto L_0x00e8
                L_0x00b3:
                    if (r1 == 0) goto L_0x00c4
                    int r0 = r7.mMode
                    r0 = r0 & 16
                    if (r0 == 0) goto L_0x00c4
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r0)
                    r8.transitionToStart()
                    goto L_0x00e8
                L_0x00c4:
                    if (r3 == 0) goto L_0x00d7
                    int r0 = r7.mMode
                    r0 = r0 & 256(0x100, float:3.59E-43)
                    if (r0 == 0) goto L_0x00d7
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r0)
                    r0 = 1065353216(0x3f800000, float:1.0)
                    r8.setProgress(r0)
                    goto L_0x00e8
                L_0x00d7:
                    if (r1 == 0) goto L_0x00e8
                    int r0 = r7.mMode
                    r0 = r0 & 4096(0x1000, float:5.74E-42)
                    if (r0 == 0) goto L_0x00e8
                    androidx.constraintlayout.motion.widget.MotionScene$Transition r0 = r7.mTransition
                    r8.setTransition((androidx.constraintlayout.motion.widget.MotionScene.Transition) r0)
                    r0 = 0
                    r8.setProgress(r0)
                L_0x00e8:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick.onClick(android.view.View):void");
            }
        }

        Transition(MotionScene motionScene, Transition transition) {
            this.mMotionScene = motionScene;
            this.mDuration = motionScene.mDefaultDuration;
            if (transition != null) {
                this.mPathMotionArc = transition.mPathMotionArc;
                this.mDefaultInterpolator = transition.mDefaultInterpolator;
                this.mDefaultInterpolatorString = transition.mDefaultInterpolatorString;
                this.mDefaultInterpolatorID = transition.mDefaultInterpolatorID;
                this.mDuration = transition.mDuration;
                this.mKeyFramesList = transition.mKeyFramesList;
                this.mStagger = transition.mStagger;
                this.mLayoutDuringTransition = transition.mLayoutDuringTransition;
            }
        }

        public Transition(int i, MotionScene motionScene, int i2, int i3) {
            this.mId = i;
            this.mMotionScene = motionScene;
            this.mConstraintSetStart = i2;
            this.mConstraintSetEnd = i3;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
            this.mMotionScene = motionScene;
            fillFromAttributeList(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }

        public void setInterpolatorInfo(int i, String str, int i2) {
            this.mDefaultInterpolator = i;
            this.mDefaultInterpolatorString = str;
            this.mDefaultInterpolatorID = i2;
        }

        private void fillFromAttributeList(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            fill(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        private void fill(MotionScene motionScene, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.mConstraintSetEnd = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintSetEnd);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.load(context, this.mConstraintSetEnd);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetEnd, constraintSet);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.mConstraintSetEnd = motionScene.parseInclude(context, this.mConstraintSetEnd);
                    }
                } else if (index == R.styleable.Transition_constraintSetStart) {
                    this.mConstraintSetStart = typedArray.getResourceId(index, this.mConstraintSetStart);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.mConstraintSetStart);
                    if ("layout".equals(resourceTypeName2)) {
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.load(context, this.mConstraintSetStart);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetStart, constraintSet2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.mConstraintSetStart = motionScene.parseInclude(context, this.mConstraintSetStart);
                    }
                } else if (index == R.styleable.Transition_motionInterpolator) {
                    TypedValue peekValue = typedArray.peekValue(index);
                    if (peekValue.type == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.mDefaultInterpolatorID = resourceId;
                        if (resourceId != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    } else if (peekValue.type == 3) {
                        String string = typedArray.getString(index);
                        this.mDefaultInterpolatorString = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.mDefaultInterpolatorID = typedArray.getResourceId(index, -1);
                                this.mDefaultInterpolator = -2;
                            } else {
                                this.mDefaultInterpolator = -1;
                            }
                        }
                    } else {
                        this.mDefaultInterpolator = typedArray.getInteger(index, this.mDefaultInterpolator);
                    }
                } else if (index == R.styleable.Transition_duration) {
                    int i2 = typedArray.getInt(index, this.mDuration);
                    this.mDuration = i2;
                    if (i2 < 8) {
                        this.mDuration = 8;
                    }
                } else if (index == R.styleable.Transition_staggered) {
                    this.mStagger = typedArray.getFloat(index, this.mStagger);
                } else if (index == R.styleable.Transition_autoTransition) {
                    this.mAutoTransition = typedArray.getInteger(index, this.mAutoTransition);
                } else if (index == R.styleable.Transition_android_id) {
                    this.mId = typedArray.getResourceId(index, this.mId);
                } else if (index == R.styleable.Transition_transitionDisable) {
                    this.mDisable = typedArray.getBoolean(index, this.mDisable);
                } else if (index == R.styleable.Transition_pathMotionArc) {
                    this.mPathMotionArc = typedArray.getInteger(index, -1);
                } else if (index == R.styleable.Transition_layoutDuringTransition) {
                    this.mLayoutDuringTransition = typedArray.getInteger(index, 0);
                } else if (index == R.styleable.Transition_transitionFlags) {
                    this.mTransitionFlags = typedArray.getInteger(index, 0);
                }
            }
            if (this.mConstraintSetStart == -1) {
                this.mIsAbstract = true;
            }
        }
    }

    public MotionScene(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
        this.mViewTransitionController = new ViewTransitionController(motionLayout);
    }

    MotionScene(Context context, MotionLayout motionLayout, int i) {
        this.mMotionLayout = motionLayout;
        this.mViewTransitionController = new ViewTransitionController(motionLayout);
        load(context, i);
        this.mConstraintSetMap.put(R.id.motion_base, new ConstraintSet());
        this.mConstraintSetIdMap.put("motion_base", Integer.valueOf(R.id.motion_base));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r10, int r11) {
        /*
            r9 = this;
            android.content.res.Resources r0 = r10.getResources()
            android.content.res.XmlResourceParser r0 = r0.getXml(r11)
            r1 = 0
            int r2 = r0.getEventType()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x000d:
            r3 = 1
            if (r2 == r3) goto L_0x0189
            if (r2 == 0) goto L_0x0177
            r4 = 2
            if (r2 == r4) goto L_0x0017
            goto L_0x017a
        L_0x0017:
            java.lang.String r2 = r0.getName()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            boolean r5 = r9.DEBUG_DESKTOP     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r5 == 0) goto L_0x003b
            java.io.PrintStream r5 = java.lang.System.out     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r6 = "parsing = "
            java.lang.String r7 = java.lang.String.valueOf(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            int r8 = r7.length()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r8 == 0) goto L_0x0032
            java.lang.String r6 = r6.concat(r7)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x0038
        L_0x0032:
            java.lang.String r7 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r7.<init>(r6)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r6 = r7
        L_0x0038:
            r5.println(r6)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x003b:
            int r5 = r2.hashCode()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r6 = "MotionScene"
            r7 = -1
            switch(r5) {
                case -1349929691: goto L_0x00a0;
                case -1239391468: goto L_0x0095;
                case -687739768: goto L_0x008b;
                case 61998586: goto L_0x0080;
                case 269306229: goto L_0x0077;
                case 312750793: goto L_0x006d;
                case 327855227: goto L_0x0063;
                case 793277014: goto L_0x005b;
                case 1382829617: goto L_0x0051;
                case 1942574248: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x00aa
        L_0x0047:
            java.lang.String r3 = "include"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 6
            goto L_0x00ab
        L_0x0051:
            java.lang.String r3 = "StateSet"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 4
            goto L_0x00ab
        L_0x005b:
            boolean r2 = r2.equals(r6)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 0
            goto L_0x00ab
        L_0x0063:
            java.lang.String r3 = "OnSwipe"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = r4
            goto L_0x00ab
        L_0x006d:
            java.lang.String r3 = "OnClick"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 3
            goto L_0x00ab
        L_0x0077:
            java.lang.String r4 = "Transition"
            boolean r2 = r2.equals(r4)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            goto L_0x00ab
        L_0x0080:
            java.lang.String r3 = "ViewTransition"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 9
            goto L_0x00ab
        L_0x008b:
            java.lang.String r3 = "Include"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 7
            goto L_0x00ab
        L_0x0095:
            java.lang.String r3 = "KeyFrameSet"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 8
            goto L_0x00ab
        L_0x00a0:
            java.lang.String r3 = "ConstraintSet"
            boolean r2 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r2 == 0) goto L_0x00aa
            r3 = 5
            goto L_0x00ab
        L_0x00aa:
            r3 = r7
        L_0x00ab:
            switch(r3) {
                case 0: goto L_0x0173;
                case 1: goto L_0x0131;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00df;
                case 4: goto L_0x00d6;
                case 5: goto L_0x00d1;
                case 6: goto L_0x00cc;
                case 7: goto L_0x00cc;
                case 8: goto L_0x00bc;
                case 9: goto L_0x00b0;
                default: goto L_0x00ae;
            }     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x00ae:
            goto L_0x017a
        L_0x00b0:
            androidx.constraintlayout.motion.widget.ViewTransition r2 = new androidx.constraintlayout.motion.widget.ViewTransition     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r2.<init>(r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.ViewTransitionController r3 = r9.mViewTransitionController     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r3.add(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00bc:
            androidx.constraintlayout.motion.widget.KeyFrames r2 = new androidx.constraintlayout.motion.widget.KeyFrames     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r2.<init>(r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 == 0) goto L_0x017a
            java.util.ArrayList r3 = r1.mKeyFramesList     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r3.add(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00cc:
            r9.parseInclude((android.content.Context) r10, (org.xmlpull.v1.XmlPullParser) r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00d1:
            r9.parseConstraintSet(r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00d6:
            androidx.constraintlayout.widget.StateSet r2 = new androidx.constraintlayout.widget.StateSet     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r2.<init>(r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r9.mStateSet = r2     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00df:
            if (r1 == 0) goto L_0x017a
            r1.addOnClick((android.content.Context) r10, (org.xmlpull.v1.XmlPullParser) r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x00e6:
            if (r1 != 0) goto L_0x0124
            android.content.res.Resources r2 = r10.getResources()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r2 = r2.getResourceEntryName(r11)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            int r3 = r0.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            int r4 = r4.length()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            int r4 = r4 + 27
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r5.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r4 = " OnSwipe ("
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r4 = ".xml:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            java.lang.String r2 = r2.toString()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            android.util.Log.v(r6, r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x0124:
            if (r1 == 0) goto L_0x017a
            androidx.constraintlayout.motion.widget.TouchResponse r2 = new androidx.constraintlayout.motion.widget.TouchResponse     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r9.mMotionLayout     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r2.<init>(r10, r3, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.TouchResponse unused = r1.mTouchResponse = r2     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x0131:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r1 = r9.mTransitionList     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.MotionScene$Transition r2 = new androidx.constraintlayout.motion.widget.MotionScene$Transition     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r2.<init>(r9, r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r1.add(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.MotionScene$Transition r1 = r9.mCurrentTransition     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 != 0) goto L_0x0158
            boolean r1 = r2.mIsAbstract     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 != 0) goto L_0x0158
            r9.mCurrentTransition = r2     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.TouchResponse r1 = r2.mTouchResponse     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 == 0) goto L_0x0158
            androidx.constraintlayout.motion.widget.MotionScene$Transition r1 = r9.mCurrentTransition     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            androidx.constraintlayout.motion.widget.TouchResponse r1 = r1.mTouchResponse     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            boolean r3 = r9.mRtl     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r1.setRTL(r3)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x0158:
            boolean r1 = r2.mIsAbstract     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 == 0) goto L_0x0171
            int r1 = r2.mConstraintSetEnd     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            if (r1 != r7) goto L_0x0167
            r9.mDefaultTransition = r2     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x016c
        L_0x0167:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r1 = r9.mAbstractTransitionList     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r1.add(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x016c:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r1 = r9.mTransitionList     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            r1.remove(r2)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x0171:
            r1 = r2
            goto L_0x017a
        L_0x0173:
            r9.parseMotionSceneTags(r10, r0)     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x017a
        L_0x0177:
            r0.getName()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
        L_0x017a:
            int r2 = r0.next()     // Catch:{ XmlPullParserException -> 0x0185, IOException -> 0x0180 }
            goto L_0x000d
        L_0x0180:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0189
        L_0x0185:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0189:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.load(android.content.Context, int):void");
    }

    private void parseMotionSceneTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.MotionScene_defaultDuration) {
                int i2 = obtainStyledAttributes.getInt(index, this.mDefaultDuration);
                this.mDefaultDuration = i2;
                if (i2 < 8) {
                    this.mDefaultDuration = 8;
                }
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.mLayoutDuringTransition = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private int getId(Context context, String str) {
        int i;
        if (str.contains("/")) {
            i = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.DEBUG_DESKTOP) {
                System.out.println(new StringBuilder(27).append("id getMap res = ").append(i).toString());
            }
        } else {
            i = -1;
        }
        if (i != -1) {
            return i;
        }
        if (str != null && str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e(TypedValues.MotionScene.NAME, "error in parsing id");
        return i;
    }

    private void parseInclude(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.include_constraintSet) {
                parseInclude(context, obtainStyledAttributes.getResourceId(index, -1));
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: private */
    public int parseInclude(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && CONSTRAINTSET_TAG.equals(name)) {
                    return parseConstraintSet(context, xml);
                }
            }
            return -1;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseConstraintSet(android.content.Context r18, org.xmlpull.v1.XmlPullParser r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            androidx.constraintlayout.widget.ConstraintSet r3 = new androidx.constraintlayout.widget.ConstraintSet
            r3.<init>()
            r4 = 0
            r3.setForceId(r4)
            int r5 = r19.getAttributeCount()
            r6 = -1
            r7 = r4
            r8 = r6
            r9 = r8
        L_0x0017:
            r10 = 1
            if (r7 >= r5) goto L_0x00f4
            java.lang.String r11 = r2.getAttributeName(r7)
            java.lang.String r12 = r2.getAttributeValue(r7)
            boolean r13 = r0.DEBUG_DESKTOP
            if (r13 == 0) goto L_0x0042
            java.io.PrintStream r13 = java.lang.System.out
            java.lang.String r14 = "id string = "
            java.lang.String r15 = java.lang.String.valueOf(r12)
            int r16 = r15.length()
            if (r16 == 0) goto L_0x0039
            java.lang.String r14 = r14.concat(r15)
            goto L_0x003f
        L_0x0039:
            java.lang.String r15 = new java.lang.String
            r15.<init>(r14)
            r14 = r15
        L_0x003f:
            r13.println(r14)
        L_0x0042:
            r11.hashCode()
            int r13 = r11.hashCode()
            r14 = 2
            switch(r13) {
                case -1496482599: goto L_0x0065;
                case -1153153640: goto L_0x005a;
                case 3355: goto L_0x004f;
                default: goto L_0x004d;
            }
        L_0x004d:
            r11 = r6
            goto L_0x006f
        L_0x004f:
            java.lang.String r13 = "id"
            boolean r11 = r11.equals(r13)
            if (r11 != 0) goto L_0x0058
            goto L_0x004d
        L_0x0058:
            r11 = r14
            goto L_0x006f
        L_0x005a:
            java.lang.String r13 = "constraintRotate"
            boolean r11 = r11.equals(r13)
            if (r11 != 0) goto L_0x0063
            goto L_0x004d
        L_0x0063:
            r11 = r10
            goto L_0x006f
        L_0x0065:
            java.lang.String r13 = "deriveConstraintsFrom"
            boolean r11 = r11.equals(r13)
            if (r11 != 0) goto L_0x006e
            goto L_0x004d
        L_0x006e:
            r11 = r4
        L_0x006f:
            switch(r11) {
                case 0: goto L_0x00ec;
                case 1: goto L_0x008d;
                case 2: goto L_0x0074;
                default: goto L_0x0072;
            }
        L_0x0072:
            goto L_0x00f0
        L_0x0074:
            int r8 = r0.getId(r1, r12)
            java.util.HashMap<java.lang.String, java.lang.Integer> r10 = r0.mConstraintSetIdMap
            java.lang.String r11 = stripID(r12)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r8)
            r10.put(r11, r12)
            java.lang.String r10 = androidx.constraintlayout.motion.widget.Debug.getName((android.content.Context) r1, (int) r8)
            r3.mIdString = r10
            goto L_0x00f0
        L_0x008d:
            int r11 = java.lang.Integer.parseInt(r12)     // Catch:{ NumberFormatException -> 0x0095 }
            r3.mRotate = r11     // Catch:{ NumberFormatException -> 0x0095 }
            goto L_0x00f0
        L_0x0095:
            r12.hashCode()
            int r11 = r12.hashCode()
            r13 = 4
            r15 = 3
            switch(r11) {
                case -768416914: goto L_0x00cf;
                case 3317767: goto L_0x00c4;
                case 3387192: goto L_0x00b9;
                case 108511772: goto L_0x00ae;
                case 1954540437: goto L_0x00a3;
                default: goto L_0x00a1;
            }
        L_0x00a1:
            r11 = r6
            goto L_0x00d9
        L_0x00a3:
            java.lang.String r11 = "x_right"
            boolean r11 = r12.equals(r11)
            if (r11 != 0) goto L_0x00ac
            goto L_0x00a1
        L_0x00ac:
            r11 = r13
            goto L_0x00d9
        L_0x00ae:
            java.lang.String r11 = "right"
            boolean r11 = r12.equals(r11)
            if (r11 != 0) goto L_0x00b7
            goto L_0x00a1
        L_0x00b7:
            r11 = r15
            goto L_0x00d9
        L_0x00b9:
            java.lang.String r11 = "none"
            boolean r11 = r12.equals(r11)
            if (r11 != 0) goto L_0x00c2
            goto L_0x00a1
        L_0x00c2:
            r11 = r14
            goto L_0x00d9
        L_0x00c4:
            java.lang.String r11 = "left"
            boolean r11 = r12.equals(r11)
            if (r11 != 0) goto L_0x00cd
            goto L_0x00a1
        L_0x00cd:
            r11 = r10
            goto L_0x00d9
        L_0x00cf:
            java.lang.String r11 = "x_left"
            boolean r11 = r12.equals(r11)
            if (r11 != 0) goto L_0x00d8
            goto L_0x00a1
        L_0x00d8:
            r11 = r4
        L_0x00d9:
            switch(r11) {
                case 0: goto L_0x00e9;
                case 1: goto L_0x00e6;
                case 2: goto L_0x00e3;
                case 3: goto L_0x00e0;
                case 4: goto L_0x00dd;
                default: goto L_0x00dc;
            }
        L_0x00dc:
            goto L_0x00f0
        L_0x00dd:
            r3.mRotate = r15
            goto L_0x00f0
        L_0x00e0:
            r3.mRotate = r10
            goto L_0x00f0
        L_0x00e3:
            r3.mRotate = r4
            goto L_0x00f0
        L_0x00e6:
            r3.mRotate = r14
            goto L_0x00f0
        L_0x00e9:
            r3.mRotate = r13
            goto L_0x00f0
        L_0x00ec:
            int r9 = r0.getId(r1, r12)
        L_0x00f0:
            int r7 = r7 + 1
            goto L_0x0017
        L_0x00f4:
            if (r8 == r6) goto L_0x010e
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            int r4 = r4.mDebugPath
            if (r4 == 0) goto L_0x00ff
            r3.setValidateOnParse(r10)
        L_0x00ff:
            r3.load((android.content.Context) r1, (org.xmlpull.v1.XmlPullParser) r2)
            if (r9 == r6) goto L_0x0109
            android.util.SparseIntArray r1 = r0.mDeriveMap
            r1.put(r8, r9)
        L_0x0109:
            android.util.SparseArray<androidx.constraintlayout.widget.ConstraintSet> r1 = r0.mConstraintSetMap
            r1.put(r8, r3)
        L_0x010e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.parseConstraintSet(android.content.Context, org.xmlpull.v1.XmlPullParser):int");
    }

    public ConstraintSet getConstraintSet(Context context, String str) {
        if (this.DEBUG_DESKTOP) {
            PrintStream printStream = System.out;
            String valueOf = String.valueOf(str);
            printStream.println(valueOf.length() != 0 ? "id ".concat(valueOf) : new String("id "));
            System.out.println(new StringBuilder(16).append("size ").append(this.mConstraintSetMap.size()).toString());
        }
        for (int i = 0; i < this.mConstraintSetMap.size(); i++) {
            int keyAt = this.mConstraintSetMap.keyAt(i);
            String resourceName = context.getResources().getResourceName(keyAt);
            if (this.DEBUG_DESKTOP) {
                System.out.println(new StringBuilder(String.valueOf(resourceName).length() + 41 + String.valueOf(str).length()).append("Id for <").append(i).append("> is <").append(resourceName).append("> looking for <").append(str).append(">").toString());
            }
            if (str.equals(resourceName)) {
                return this.mConstraintSetMap.get(keyAt);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ConstraintSet getConstraintSet(int i) {
        return getConstraintSet(i, -1, -1);
    }

    /* access modifiers changed from: package-private */
    public ConstraintSet getConstraintSet(int i, int i2, int i3) {
        int stateGetConstraintID;
        if (this.DEBUG_DESKTOP) {
            System.out.println(new StringBuilder(14).append("id ").append(i).toString());
            System.out.println(new StringBuilder(16).append("size ").append(this.mConstraintSetMap.size()).toString());
        }
        StateSet stateSet = this.mStateSet;
        if (!(stateSet == null || (stateGetConstraintID = stateSet.stateGetConstraintID(i, i2, i3)) == -1)) {
            i = stateGetConstraintID;
        }
        if (this.mConstraintSetMap.get(i) != null) {
            return this.mConstraintSetMap.get(i);
        }
        String name = Debug.getName(this.mMotionLayout.getContext(), i);
        Log.e(TypedValues.MotionScene.NAME, new StringBuilder(String.valueOf(name).length() + 55).append("Warning could not find ConstraintSet id/").append(name).append(" In MotionScene").toString());
        SparseArray<ConstraintSet> sparseArray = this.mConstraintSetMap;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public void setConstraintSet(int i, ConstraintSet constraintSet) {
        this.mConstraintSetMap.put(i, constraintSet);
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            Transition transition2 = this.mDefaultTransition;
            if (transition2 != null) {
                Iterator it = transition2.mKeyFramesList.iterator();
                while (it.hasNext()) {
                    ((KeyFrames) it.next()).addFrames(motionController);
                }
                return;
            }
            return;
        }
        Iterator it2 = transition.mKeyFramesList.iterator();
        while (it2.hasNext()) {
            ((KeyFrames) it2.next()).addFrames(motionController);
        }
    }

    /* access modifiers changed from: package-private */
    public Key getKeyFrame(Context context, int i, int i2, int i3) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            Iterator<Integer> it2 = keyFrames.getKeys().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Integer next = it2.next();
                    if (i2 == next.intValue()) {
                        Iterator<Key> it3 = keyFrames.getKeyFramesForView(next.intValue()).iterator();
                        while (it3.hasNext()) {
                            Key next2 = it3.next();
                            if (next2.mFramePosition == i3 && next2.mType == i) {
                                return next2;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getTransitionDirection(int i) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (it.next().mConstraintSetStart == i) {
                return 0;
            }
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean hasKeyFramePosition(View view, int i) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().mFramePosition == i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setKeyframe(View view, int i, String str, Object obj) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
                while (it2.hasNext()) {
                    if (it2.next().mFramePosition == i) {
                        if (obj != null) {
                            ((Float) obj).floatValue();
                        }
                        str.equalsIgnoreCase("app:PerpendicularPath_percent");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean supportTouch() {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (it.next().mTouchResponse != null) {
                return true;
            }
        }
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void processTouchEvent(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = this.mMotionLayout.obtainVelocityTracker();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.mLastTouchX = motionEvent.getRawX();
                this.mLastTouchY = motionEvent.getRawY();
                this.mLastTouchDown = motionEvent;
                this.mIgnoreTouch = false;
                if (this.mCurrentTransition.mTouchResponse != null) {
                    RectF limitBoundsTo = this.mCurrentTransition.mTouchResponse.getLimitBoundsTo(this.mMotionLayout, rectF);
                    if (limitBoundsTo == null || limitBoundsTo.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                        RectF touchRegion = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                        if (touchRegion == null || touchRegion.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                            this.mMotionOutsideRegion = false;
                        } else {
                            this.mMotionOutsideRegion = true;
                        }
                        this.mCurrentTransition.mTouchResponse.setDown(this.mLastTouchX, this.mLastTouchY);
                        return;
                    }
                    this.mLastTouchDown = null;
                    this.mIgnoreTouch = true;
                    return;
                }
                return;
            } else if (action == 2 && !this.mIgnoreTouch) {
                float rawY = motionEvent.getRawY() - this.mLastTouchY;
                float rawX = motionEvent.getRawX() - this.mLastTouchX;
                if ((((double) rawX) != 0.0d || ((double) rawY) != 0.0d) && (motionEvent2 = this.mLastTouchDown) != null) {
                    Transition bestTransitionFor = bestTransitionFor(i, rawX, rawY, motionEvent2);
                    if (bestTransitionFor != null) {
                        motionLayout.setTransition(bestTransitionFor);
                        RectF touchRegion2 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                        if (touchRegion2 != null && !touchRegion2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                            z = true;
                        }
                        this.mMotionOutsideRegion = z;
                        this.mCurrentTransition.mTouchResponse.setUpTouchEvent(this.mLastTouchX, this.mLastTouchY);
                    }
                } else {
                    return;
                }
            }
        }
        if (!this.mIgnoreTouch) {
            Transition transition = this.mCurrentTransition;
            if (!(transition == null || transition.mTouchResponse == null || this.mMotionOutsideRegion)) {
                this.mCurrentTransition.mTouchResponse.processTouchEvent(motionEvent, this.mVelocityTracker, i, this);
            }
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
            if (motionEvent.getAction() == 1 && (motionTracker = this.mVelocityTracker) != null) {
                motionTracker.recycle();
                this.mVelocityTracker = null;
                if (motionLayout.mCurrentState != -1) {
                    autoTransition(motionLayout, motionLayout.mCurrentState);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void processScrollMove(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollMove(f, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public void processScrollUp(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollUp(f, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public float getProgressDirection(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getProgressDirection(f, f2);
    }

    /* access modifiers changed from: package-private */
    public int getStartId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetStart;
    }

    /* access modifiers changed from: package-private */
    public int getEndId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetEnd;
    }

    public Interpolator getInterpolator() {
        int access$1500 = this.mCurrentTransition.mDefaultInterpolator;
        if (access$1500 == -2) {
            return AnimationUtils.loadInterpolator(this.mMotionLayout.getContext(), this.mCurrentTransition.mDefaultInterpolatorID);
        }
        if (access$1500 == -1) {
            final Easing interpolator = Easing.getInterpolator(this.mCurrentTransition.mDefaultInterpolatorString);
            return new Interpolator(this) {
                public float getInterpolation(float f) {
                    return (float) interpolator.get((double) f);
                }
            };
        } else if (access$1500 == 0) {
            return new AccelerateDecelerateInterpolator();
        } else {
            if (access$1500 == 1) {
                return new AccelerateInterpolator();
            }
            if (access$1500 == 2) {
                return new DecelerateInterpolator();
            }
            if (access$1500 == 4) {
                return new BounceInterpolator();
            }
            if (access$1500 == 5) {
                return new OvershootInterpolator();
            }
            if (access$1500 != 6) {
                return null;
            }
            return new AnticipateInterpolator();
        }
    }

    public int getDuration() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mDuration;
        }
        return this.mDefaultDuration;
    }

    public void setDuration(int i) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            transition.setDuration(i);
        } else {
            this.mDefaultDuration = i;
        }
    }

    public int gatPathMotionArc() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mPathMotionArc;
        }
        return -1;
    }

    public float getStaggered() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mStagger;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxAcceleration();
    }

    /* access modifiers changed from: package-private */
    public float getMaxVelocity() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxVelocity();
    }

    /* access modifiers changed from: package-private */
    public float getSpringStiffiness() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getSpringStiffness();
    }

    /* access modifiers changed from: package-private */
    public float getSpringMass() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getSpringMass();
    }

    /* access modifiers changed from: package-private */
    public float getSpringDamping() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getSpringDamping();
    }

    /* access modifiers changed from: package-private */
    public float getSpringStopThreshold() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getSpringStopThreshold();
    }

    /* access modifiers changed from: package-private */
    public int getSpringBoundary() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0;
        }
        return this.mCurrentTransition.mTouchResponse.getSpringBoundary();
    }

    /* access modifiers changed from: package-private */
    public int getAutoCompleteMode() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0;
        }
        return this.mCurrentTransition.mTouchResponse.getAutoCompleteMode();
    }

    /* access modifiers changed from: package-private */
    public void setupTouch() {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setupTouch();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return this.mCurrentTransition.mTouchResponse.getMoveWhenScrollAtTop();
    }

    /* access modifiers changed from: package-private */
    public void readFallback(MotionLayout motionLayout) {
        int i = 0;
        while (i < this.mConstraintSetMap.size()) {
            int keyAt = this.mConstraintSetMap.keyAt(i);
            if (hasCycleDependency(keyAt)) {
                Log.e(TypedValues.MotionScene.NAME, "Cannot be derived from yourself");
                return;
            } else {
                readConstraintChain(keyAt, motionLayout);
                i++;
            }
        }
    }

    private boolean hasCycleDependency(int i) {
        int i2 = this.mDeriveMap.get(i);
        int size = this.mDeriveMap.size();
        while (i2 > 0) {
            if (i2 == i) {
                return true;
            }
            int i3 = size - 1;
            if (size < 0) {
                return true;
            }
            i2 = this.mDeriveMap.get(i2);
            size = i3;
        }
        return false;
    }

    private void readConstraintChain(int i, MotionLayout motionLayout) {
        ConstraintSet constraintSet = this.mConstraintSetMap.get(i);
        constraintSet.derivedState = constraintSet.mIdString;
        int i2 = this.mDeriveMap.get(i);
        if (i2 > 0) {
            readConstraintChain(i2, motionLayout);
            ConstraintSet constraintSet2 = this.mConstraintSetMap.get(i2);
            if (constraintSet2 == null) {
                String valueOf = String.valueOf(Debug.getName(this.mMotionLayout.getContext(), i2));
                Log.e(TypedValues.MotionScene.NAME, valueOf.length() != 0 ? "ERROR! invalid deriveConstraintsFrom: @id/".concat(valueOf) : new String("ERROR! invalid deriveConstraintsFrom: @id/"));
                return;
            }
            String valueOf2 = String.valueOf(constraintSet.derivedState);
            String str = constraintSet2.derivedState;
            constraintSet.derivedState = new StringBuilder(String.valueOf(valueOf2).length() + 1 + String.valueOf(str).length()).append(valueOf2).append("/").append(str).toString();
            constraintSet.readFallback(constraintSet2);
        } else {
            constraintSet.derivedState = String.valueOf(constraintSet.derivedState).concat("  layout");
            constraintSet.readFallback((ConstraintLayout) motionLayout);
        }
        constraintSet.applyDeltaFrom(constraintSet);
    }

    public static String stripID(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1);
    }

    public int lookUpConstraintId(String str) {
        Integer num = this.mConstraintSetIdMap.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String lookUpConstraintName(int i) {
        for (Map.Entry next : this.mConstraintSetIdMap.entrySet()) {
            Integer num = (Integer) next.getValue();
            if (num != null && num.intValue() == i) {
                return (String) next.getKey();
            }
        }
        return null;
    }

    public void disableAutoTransition(boolean z) {
        this.mDisableAutoTransition = z;
    }

    static String getLine(Context context, int i, XmlPullParser xmlPullParser) {
        String name = Debug.getName(context, i);
        int lineNumber = xmlPullParser.getLineNumber();
        String name2 = xmlPullParser.getName();
        return new StringBuilder(String.valueOf(name).length() + 22 + String.valueOf(name2).length()).append(".(").append(name).append(".xml:").append(lineNumber).append(") \"").append(name2).append("\"").toString();
    }
}
