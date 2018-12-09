package zjc.healthmanage.activity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantao.coordinatormenu.CoordinatorMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjc.healthmanage.R;
import zjc.healthmanage.fragment.EvaluteFragment;
import zjc.healthmanage.fragment.FindFragment;
import zjc.healthmanage.fragment.TrendFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static CoordinatorMenu mCoordinatorMenu;
    private EvaluteFragment evaluteFragment;
    private TrendFragment trendFragment;
    private FindFragment findFragment;
    @BindView(R.id.tab_evalute_ib) ImageView tab_evalute_ib;
    @BindView(R.id.tab_evalute_ll)
    LinearLayout tab_evalute_ll;
    @BindView(R.id.tab_evalute_tv)
    TextView tab_evalute_tv;
    @BindView(R.id.main_fl)
    FrameLayout main_fl;
    @BindView(R.id.tab_find_ib) ImageView tab_find_ib;
    @BindView(R.id.tab_find_ll)
    LinearLayout tab_find_ll;
    @BindView(R.id.tab_find_tv)
    TextView tab_find_tv;
    @BindView(R.id.tab_trend_ib) ImageView tab_trend_ib;
    @BindView(R.id.tab_trend_ll)
    LinearLayout tab_trend_ll;
    @BindView(R.id.tab_trend_tv)
    TextView tab_trend_tv;
    //@BindView(R.id.change_theme_ll)LinearLayout change_theme_ll;
    //@BindView(R.id.change_theme_iv)ImageView change_theme_iv;
    //@BindView(R.id.change_theme_tv) TextView change_theme_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inListener();

        mCoordinatorMenu=findViewById(R.id.mainactivity_menu);
        setSelect(0);

    }

    @Override
    public void onBackPressed() {
        if (mCoordinatorMenu.isOpened()) {
            mCoordinatorMenu.closeMenu();
        }  else {
            super.onBackPressed();
        }

    }

    //UI_MODE_NIGHT_NO 日间
    //UI_MODE_NIGHT_YES夜间
//    @OnClick(R.id.change_theme_ll)
//    public void change_theme_ll_Onlick(){
//        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        if (mode == Configuration.UI_MODE_NIGHT_YES) {
//            change_theme_iv.setImageResource(R.drawable.night);
//            change_theme_tv.setText("夜间");
//            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
//            change_theme_iv.setImageResource(R.drawable.daytime);
//            change_theme_tv.setText("日间");
//            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        recreate();
//}



    public void inListener(){
        tab_evalute_ll.setOnClickListener(this);
        tab_find_ll.setOnClickListener(this);
        tab_trend_ll.setOnClickListener(this);
    }
    public void setSelect(int i){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction tf=fm.beginTransaction();
        hideFragment(tf);
        switch (i){
            case 0:
                if (evaluteFragment == null) {
                    evaluteFragment = new EvaluteFragment();
                    tf.add(R.id.main_fl, evaluteFragment);
                } else {
                    tf.show(evaluteFragment);
                }

                break;
            case 1:
                if (trendFragment == null) {
                    trendFragment = new TrendFragment();
                    tf.add(R.id.main_fl, trendFragment);
                } else {
                    tf.show(trendFragment);

                }

                break;
            case 2:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    tf.add(R.id.main_fl, findFragment);
                } else {
                    tf.show(findFragment);

                }

                break;
        }
        tf.commit();
    }
    private void hideFragment(FragmentTransaction tf){
        if(trendFragment!=null){
            tf.hide(trendFragment);
        }
        if (findFragment!=null){
            tf.hide(findFragment);
        }
        if (evaluteFragment!=null){
            tf.hide(evaluteFragment);
        }
    }
    private void startShakeByPropertyAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        //TODO 验证参数的有效性

        //先变小后变大
        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
        );
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
        );

        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.3f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, shakeDegrees),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXValuesHolder, scaleYValuesHolder, rotateValuesHolder);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }
    private void resetImgs() {
        //重置icon图标
        tab_evalute_ib.setImageResource(R.drawable.evalute);
        tab_trend_ib.setImageResource(R.drawable.trend);
        tab_find_ib.setImageResource(R.drawable.find);

        //重置文字颜色
        tab_evalute_tv.setTextColor(Color.parseColor("#272727"));
        tab_find_tv.setTextColor(Color.parseColor("#272727"));
        tab_trend_tv.setTextColor(Color.parseColor("#272727"));
    }
    @Override
    public void onClick(View view) {
        resetImgs();
        switch (view.getId()){
            case R.id.tab_evalute_ll:
                tab_evalute_ib.setImageResource(R.drawable.evalute_press);
                startShakeByPropertyAnim(tab_evalute_ib, 0.9f, 1.2f, 10f, 400);
                tab_evalute_tv.setTextColor(Color.parseColor("#FF4081"));
                setSelect(0);
                break;
            case R.id.tab_trend_ll:
                tab_trend_ib.setImageResource(R.drawable.trend_pressgreen);
                startShakeByPropertyAnim(tab_trend_ib,0.9f, 1.2f, 10f, 400);
                tab_trend_tv.setTextColor(Color.parseColor("#FF4081"));
                setSelect(1);
                break;
            case R.id.tab_find_ll:
                tab_find_ib.setImageResource(R.drawable.find_press);
                startShakeByPropertyAnim(tab_find_ib,0.9f, 1.2f, 10f, 400);
                tab_find_tv.setTextColor(Color.parseColor("#FF4081"));
                setSelect(2);
                break;
            default:break;
        }
    }
}
