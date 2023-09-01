package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.NewsListBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter.NewsAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news.NewsState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.NewsViewModel
import java.util.*


class NewsFragment : Fragment(R.layout.news_list) {

    private val mainViewModel: MainContract.ViewModelNews by sharedViewModel<NewsViewModel>()

    private var _binding: NewsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapter =  NewsAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {

        binding.search.setOnClickListener{
            val url = "http://www.example.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }




    }

    private fun initObservers() {

  /*      mainViewModel.getAllMovies()
        mainViewModel.fetchAllMovies()*/

        mainViewModel.fetchAllMovies()
        mainViewModel.newsState.observe(viewLifecycleOwner) {
            //Timber.e("OVO JE PORUKA SA USERIMA " + it.toString())
            renderState(it)

        }

    }

    private fun renderState(state: NewsState) {
        when (state) {
            is NewsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.new)
                Toast.makeText(context, "News data from cache loaded", Toast.LENGTH_LONG).show()
            }
            is NewsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NewsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh news data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is NewsState.Loading -> {
                showLoadingState(true)
                Toast.makeText(context, "Logging news data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {

        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}