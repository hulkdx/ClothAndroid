package hulkdx.com.features.category.view

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.category.R
import hulkdx.com.domain.entities.Category
import hulkdx.com.features.category.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.features.common.util.ImageLoader
import hulkdx.com.features.common.util.SystemResourceUtils


/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/10/2019.
 *
 * TODO: show Loading
 */
class CategoryFragment : BaseFragment() {

    @Inject internal lateinit var mImageLoader: ImageLoader
    @Inject internal lateinit var mSystemResourceUtils: SystemResourceUtils
    private lateinit var mCategoryViewModel: CategoryViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mCategoryViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CategoryViewModel::class.java)
        mCategoryViewModel.loadCategory()
        mCategoryViewModel.categoryLiveData().observeUseCase {
            loadCategorySuccess(it, requireContext())
        }
    }

    // region Category Callback --------------------------------------------------------------------

    private fun loadCategorySuccess(categories: List<Category>, context: Context) {
        for ((title, categoryItems) in categories) {

            // titleTextView
            val textView = TextView(ContextThemeWrapper(context, R.style.category_headline_text)).apply {
                text = title
                layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            }

            // categoryRecyclerView
            val categoryAdapter = CategoryAdapter(mImageLoader, categoryItems)
            val recyclerView = RecyclerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, mSystemResourceUtils.getScreenHeight()/4)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = categoryAdapter
            }

            categoryContainer.addView(textView)
            categoryContainer.addView(recyclerView)
        }
    }

    // endregion Category Callback -----------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_category
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    