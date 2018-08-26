package com.mdelacruz.imagefindermvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mdelacruz.imagefindermvp.R;
import com.mdelacruz.imagefindermvp.api.model.ImageResult;
import com.mdelacruz.imagefindermvp.base.BaseFragment;
import com.mdelacruz.imagefindermvp.manager.ImageManager;
import com.mdelacruz.imagefindermvp.ui.adapter.ViewModelAdapter;
import com.mdelacruz.imagefindermvp.ui.factory.ImageFactory;
import com.mdelacruz.imagefindermvp.ui.presenter.MainFragmentPresenter;
import com.mdelacruz.imagefindermvp.ui.view.MainFragmentView;
import com.mdelacruz.imagefindermvp.ui.viewmodel.BaseViewModel;
import com.mdelacruz.imagefindermvp.util.SpaceItemDecoration;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;

public class MainFragment extends BaseFragment implements MainFragmentView {

  //==============================================================================================
  // Properties
  //==============================================================================================

  @Inject
  ViewModelAdapter adapter;
  @Inject
  ImageFactory imageFactory;
  @Inject
  MainFragmentPresenter mainFragmentPresenter;

  @BindView(R.id.search_phrase)
  EditText searchView;
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindView(R.id.progress_bar)
  ProgressBar progressBar;
  @BindDimen(R.dimen.image_space)
  int space;

  Unbinder unbinder;

  //==============================================================================================
  // Lifecycle
  //==============================================================================================

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    unbinder = ButterKnife.bind(this, view);

    searchView.setOnEditorActionListener((v, actionId, event) -> {
      if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        mainFragmentPresenter.search(searchView.getText().toString());
        return true;
      }
      return false;
    });

    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new SpaceItemDecoration(space, space, space, space));
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  protected void onFragmentInject() {
    getAppComponent().inject(this);
    mainFragmentPresenter.attachView(this);
  }

  //==============================================================================================
  // Instance Method
  //==============================================================================================

  public void onImageLongPress(String id, String uri) {
    PopUpDialogFragment popUpDialogFragment = new PopUpDialogFragment();

    popUpDialogFragment.setPopupDialogListener(() -> {
      ImageManager.INSTANCE.setSavedImageId(id);
      ImageManager.INSTANCE.setSavedImageUri(uri);
    });

    if (getFragmentManager() != null) {
      popUpDialogFragment.show(getFragmentManager(), getString(R.string.fragment_popup_dialog));
    }

  }

  //==============================================================================================
  // MainFragmentView Implementation
  //==============================================================================================

  @Override
  public void showLoadingIndicator() { progressBar.setVisibility(View.VISIBLE); }

  @Override
  public void hideLoadingIndicator() { progressBar.setVisibility(GONE); }

  @Override
  public void showSearchError() {
    Toast.makeText(getActivity(), getString(R.string.search_error), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void updateImageResult(@NotNull List<? extends ImageResult> images) {
    List<BaseViewModel> viewModels = new ArrayList<>();
    int i = 0;
    for (ImageResult imageResult : images) {
      viewModels.add(imageFactory.createImageViewModel(i++, imageResult, this::onImageLongPress));
    }
    adapter.setViewModels(viewModels);
  }

}