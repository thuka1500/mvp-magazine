package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import butterknife.bindView
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.jakewharton.rxbinding.support.v4.view.pageScrollStateChanges
import com.jakewharton.rxbinding.support.v4.view.pageSelections
import taiwan.no1.app.R
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.MainViewPager
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   2/5/17
 */


abstract class ViewPagerMainCtrlFragment: BaseFragment(), IMainFragment {
    companion object Factory {
        // For navigating the fragment's arguments. 
        const val NAVIGATOR_ARG_FRAGMENT = "fragment"
        const val NAVIGATOR_ARG_TAG = "tag"
        const val NAVIGATOR_ARG_SHARED_ELEMENTS = "shared_element_list"
    }

    //region View variables
    protected val vpContainer by bindView<ViewPager>(R.id.vp_container)
    protected val ntsTabMenu by bindView<NavigationTabStrip>(R.id.nts_center)
    //endregion

    //region Local variables
    protected var prevItemPos: Int = -1
    protected var currItemPos: Int = -1
    abstract protected val fragmentList: List<Fragment>
    //endregion

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            this.fragmentList.forEach {
                AppLog.w(it)
                it.childFragmentManager.let {
                    AppLog.w(it.backStackEntryCount)
                }
            }
        }

        this.ntsTabMenu.setViewPager(this.vpContainer.apply {
            var flagClearPrevFragment: Boolean = false

            this.adapter = MainViewPager(context(), fragmentManager, fragmentList)
            // Initial the position.
            currItemPos = this.currentItem
            prevItemPos = this.currentItem
            // View pager's listener.
            this.pageSelections().compose(bindToLifecycle<Int>()).subscribe {
                currItemPos = it
                // After change the page, the flag will be opened for clearing the previous stack fragments.
                flagClearPrevFragment = true
            }
            this.pageScrollStateChanges().compose(bindToLifecycle<Int>()).subscribe {
                // This is a trigger of changing views.
                if (ViewPager.SCROLL_STATE_SETTLING == it)
                    flagClearPrevFragment = false
                // Finished the view changed completely, the previous stack fragments will be cleared.
                else if (ViewPager.SCROLL_STATE_IDLE == it && flagClearPrevFragment) {
                    clearAllChildrenFragment(prevItemPos)
                    prevItemPos = currItemPos
                }
            }
        }, 0)
    }

    /**
     * Clear all of the child fragments.
     *
     * @param index index of the array fragment.
     */
    protected fun clearAllChildrenFragment(index: Int) {
        this.fragmentList[index].childFragmentManager.let { manager ->
            (0..manager.backStackEntryCount - 1).forEach {
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }
}