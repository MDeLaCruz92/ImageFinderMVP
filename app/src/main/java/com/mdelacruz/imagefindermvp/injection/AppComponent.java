package com.mdelacruz.imagefindermvp.injection;

import com.mdelacruz.imagefindermvp.App;
import com.mdelacruz.imagefindermvp.ui.activity.MainActivity;
import com.mdelacruz.imagefindermvp.ui.fragment.MainFragment;
import com.mdelacruz.imagefindermvp.ui.fragment.PopUpDialogFragment;
import com.mdelacruz.imagefindermvp.ui.presenter.MainFragmentPresenter;
import com.mdelacruz.imagefindermvp.ui.presenter.PopUpDialogFragmentPresenter;
import com.mdelacruz.imagefindermvp.util.AppSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
  void inject(App app);

  void inject(MainActivity activity);

  void inject(MainFragment fragment);

  void inject(PopUpDialogFragment fragment);

  void inject(MainFragmentPresenter mainFragmentPresenter);

  void inject(PopUpDialogFragmentPresenter popUpDialogFragmentPresenter);

  void inject(CompositeDisposable compositeDisposable);

  void inject(AppSchedulerProvider schedulerProvider);
}