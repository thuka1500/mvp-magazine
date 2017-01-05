package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/5/17
 */

interface MovieUpComingContract {
    interface Presenter: IPresenter<View> {
        fun requestUpComingMovies()
    }

    interface View: IView, IFragmentView {
        fun obtainMovieBriefList(movieList: List<MovieBriefModel>)
    }
}