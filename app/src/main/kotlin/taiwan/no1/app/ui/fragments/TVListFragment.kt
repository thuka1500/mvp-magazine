package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.TvListContract
import taiwan.no1.app.ui.BaseFragment
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */
@PerFragment
class TvListFragment: BaseFragment(), TvListContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TVListFragment.
         */
        fun newInstance(): TvListFragment = TvListFragment().apply {
            this.arguments = Bundle().apply {}
        }
    }
    //endregion

    @Inject
    lateinit var presenter: TvListContract.Presenter

    // Get the arguments from the bundle here.

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java, null).inject(TVListFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tvs

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TVListFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
    }
    //endregion

    //region View implementations
    //endregion
}