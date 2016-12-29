package taiwan.no1.app.ui.activities

import android.os.Bundle
import butterknife.bindView
import com.roughike.bottombar.BottomBar
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.fragments.MoviePopularFragment
import javax.inject.Inject

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@PerActivity
class MainActivity: BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)

        initFragment(savedInstanceState)
    }

    override fun getFragmentComponent(obj: Any?): FragmentComponent = super.provideFragmentComponent(obj)

    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, MoviePopularFragment.newInstance(), false, null, null)
        }
    }
}