package taiwan.no1.accounting.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.accounting.domain.CreateFakeUseCase
import taiwan.no1.accounting.mvp.contracts.FirstContract
import taiwan.no1.accounting.mvp.models.FakeModel
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class FirstPresenter @Inject constructor(): BasePresenter<FirstContract.View>(), FirstContract.Presenter {
    @Inject
    lateinit var fakeCase: CreateFakeUseCase

    //region Subscribers
    private val fakeSubscriber = subscriber<FakeModel>().onCompleted { }.onError { }.onNext { }
    //endregion

    //region View implementation
    override fun init(view: FirstContract.View) {
        super.init(view)

        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        request.fragmentLifecycle = this.view.getLifecycle()
        this.fakeCase.execute(request, this.fakeSubscriber)
    }
    //endregion
}