package com.example.bugiene.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bugiene.R
import com.example.bugiene.databinding.FragmentDashboardBinding
import com.example.bugiene.model.Article

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private var scrollPosition: Int = 0


    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ImageSliderAdapter
    private val imageResourceIds = listOf(
        R.drawable.pic_two,
        R.drawable.pic_one,
        R.drawable.pic_three
    )
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViewPager()
        setupImageSlider()
        setupRecyclerView()

        if (savedInstanceState != null) {
            scrollPosition = savedInstanceState.getInt("scrollPosition", 0)
            recyclerView.post { recyclerView.scrollToPosition(scrollPosition) }
        }

        val includedView = binding.itemInfoApp.root
        includedView.setOnClickListener {
            startActivity(Intent(requireActivity(),TutorialActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("scrollPosition", (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun initViewPager() {
//        viewPager = binding.viewPager
//        adapter = ImageSliderAdapter(imageResourceIds)
//        viewPager.adapter = adapter
//    }

    private fun setupImageSlider(){
        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.pic_one))
        imageList.add(SlideModel(R.drawable.pic_two))
        imageList.add(SlideModel(R.drawable.pic_three))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvArticle
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val articleList = getListArticles()
        articleAdapter = ArticleAdapter(articleList)
        recyclerView.adapter = articleAdapter

        articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                DetailArticleActivity.start(requireContext(), data)
            }
        })
    }

    private fun getListArticles(): ArrayList<Article> {
        val dataTitle = resources.getStringArray(R.array.data_tittle)
        val dataField = resources.getStringArray(R.array.data_field)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArticle = ArrayList<Article>()
        for (i in dataTitle.indices) {
            val article = Article(dataTitle[i], dataField[i], dataPhoto.getResourceId(i, -1))
            listArticle.add(article)
        }
        return listArticle
    }

    companion object {
        const val DATA = "DATA"
    }
}